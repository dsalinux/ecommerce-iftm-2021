package br.edu.iftm.ecommerce.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenericRepository<E, ID extends Serializable> implements Serializable {
    
    public Class<E> classe;
    
    public GenericRepository(Class<E> classe) {
        this.classe = classe;
    }
    
    public E salvar(E entidade){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        entidade = em.merge(entidade);
        em.getTransaction().commit();
        em.close();
        return entidade;
    }
    
    public void remover(ID id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        E entidade = em.find(classe, id);
        em.remove(entidade);
        em.getTransaction().commit();
        em.close();
    }
    
    public E buscarPorID(ID id){
        EntityManager em = getEntityManager();
        E entidade = em.find(classe, id);
        return entidade;
    }
    
    public List<E> listar() {
        EntityManager em = getEntityManager();

        return em.createQuery("select u from " + classe.getName() + " u").getResultList();
    }
    
    public EntityManager getEntityManager() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerce-iftm-PU");
            EntityManager em = emf.createEntityManager();
            return em;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
