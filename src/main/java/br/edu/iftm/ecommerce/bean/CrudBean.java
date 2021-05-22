package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.CrudLogic;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class CrudBean<E, L extends  CrudLogic<E>> extends JSFUtil{

    private E entidade;
    private List<E> entidades;
    
    private EstadoTela estadoTela = EstadoTela.BUSCA;
    
    enum EstadoTela {
        BUSCA,
        NOVO,
        EDITA
    }
    
    public void novo(){
        this.estadoTela = EstadoTela.NOVO;
    }
    public void salvar(){
        this.entidade = getLogic().salvar(this.entidade);
        this.estadoTela = EstadoTela.BUSCA;
        addInfo("Salvo com sucesso!");
    }
    public void editar(E entidade){
        this.entidade = entidade;
        this.estadoTela = EstadoTela.EDITA;
    }
    public void deletar(E entidade){
        getLogic().deletar(entidade);
        addInfo("Deletado com sucesso");
    }
    public void listar(){
        if(!EstadoTela.BUSCA.equals(this.estadoTela)){
            this.estadoTela = EstadoTela.BUSCA;
            return;
        }
        this.entidades = getLogic().buscar(null);
        if(this.entidades == null || this.entidades.isEmpty()) {
            addAviso("Nenhum dado encontrado.");
        }
    }
    
    public abstract L getLogic();
}
