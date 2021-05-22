package br.edu.iftm.ecommerce.bean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TesteBean extends JSFUtil{
    
    public void testar(){
        addAviso("Sua aplicação foi testada com sucesso.");
    }
    
}
