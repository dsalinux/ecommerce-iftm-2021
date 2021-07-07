package br.edu.iftm.ecommerce.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter @Setter
public class TesteBean extends JSFUtil{
    
    public void testar(){
        long tempoFinal = new Date().getTime()+3000;
        while(tempoFinal > new Date().getTime()) {
            
        }
        addInfo("OK");
    }
    
}
