package br.edu.iftm.ecommerce.bean;

import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TesteBean extends JSFUtil{
    
    public void testar(){
        long tempoFinal = new Date().getTime()+3000;
        while(tempoFinal > new Date().getTime()) {
            
        }
        addInfo("OK");
    }
    
}
