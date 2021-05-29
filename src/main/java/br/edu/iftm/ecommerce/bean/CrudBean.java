package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.CrudLogic;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CrudBean<E, L extends CrudLogic<E>> extends JSFUtil {

    private Class<E> classeEntidade;
    private E entidade;
    private List<E> entidades;
    

    private EstadoTela estadoTela = EstadoTela.BUSCA;

    enum EstadoTela {
        BUSCA,
        NOVO,
        EDITA
    }
    
    public CrudBean(Class<E> classeEntidade){
        this.classeEntidade = classeEntidade;
    }

    public void novo() {
        try {
            this.entidade = classeEntidade.getDeclaredConstructor().newInstance();
            this.estadoTela = EstadoTela.NOVO;
        } catch (SecurityException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            addErro("Erro ao iniciar usuário.");
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            this.entidade = getLogic().salvar(this.entidade);
            this.estadoTela = EstadoTela.BUSCA;
            addInfo("Salvo com sucesso!");
        } catch (ErroNegocioException ex) {
            addAviso(ex);
        } catch (ErroSistemaException ex) {
            addErro(ex);
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editar(E entidade) {
        this.entidade = entidade;
        this.estadoTela = EstadoTela.EDITA;
    }

    public void deletar(E entidade) {
        try {
            getLogic().deletar(entidade);
            addInfo("Deletado com sucesso");
        } catch (ErroNegocioException ex) {
            addAviso(ex);
        } catch (ErroSistemaException ex) {
            addErro(ex);
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listar() {
        if (!EstadoTela.BUSCA.equals(this.estadoTela)) {
            this.estadoTela = EstadoTela.BUSCA;
            return;
        }
        try {
            this.entidades = getLogic().buscar(null);
            if (this.entidades == null || this.entidades.isEmpty()) {
                addAviso("Nenhum dado encontrado.");
            }
        } catch (ErroNegocioException ex) {
            addAviso(ex);
        } catch (ErroSistemaException ex) {
            addErro(ex);
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public abstract L getLogic();
}
