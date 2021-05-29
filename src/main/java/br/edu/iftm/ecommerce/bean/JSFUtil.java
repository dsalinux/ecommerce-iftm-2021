package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil implements Serializable {
    
    public void addMensagem(FacesMessage.Severity tipoMensagem, String resumo, String detalhe){
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(tipoMensagem, resumo, detalhe));
    }
    public void addInfo(String resumo, String detalhe) {
        addMensagem(FacesMessage.SEVERITY_INFO, resumo, detalhe);
    }
    public void addInfo(String detalhe) {
        addInfo("Informação", detalhe);
    }
    public void addAviso(String resumo, String detalhe) {
        addMensagem(FacesMessage.SEVERITY_WARN, resumo, detalhe);
    }
    public void addAviso(String detalhe) {
        addAviso("Aviso", detalhe);
    }
    public void addAviso(ErroNegocioException ex) {
        addAviso("Aviso", ex.getMessage());
    }
    public void addErro(String resumo, String detalhe) {
        addMensagem(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
    }
    public void addErro(String detalhe) {
        addErro("Erro", detalhe);
    }
    public void addErro(ErroSistemaException ex) {
        addErro("Erro", ex.getMessage());
    }
   
    
}
