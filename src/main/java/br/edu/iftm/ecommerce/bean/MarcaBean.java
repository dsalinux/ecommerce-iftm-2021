package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.CrudLogic;
import br.edu.iftm.ecommerce.logic.MarcaLogic;
import br.edu.iftm.ecommerce.model.Marca;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class MarcaBean extends CrudBean<Marca, MarcaLogic>{

    @Inject
    private MarcaLogic logic;

    public MarcaBean() {
        super(Marca.class);
    }
    
    @Override
    public MarcaLogic getLogic() {
        return this.logic;
    }
    
}
