package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.CrudLogic;
import br.edu.iftm.ecommerce.logic.MarcaLogic;
import br.edu.iftm.ecommerce.logic.ProdutoLogic;
import br.edu.iftm.ecommerce.model.Marca;
import br.edu.iftm.ecommerce.model.Produto;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ProdutoBean extends CrudBean<Produto, ProdutoLogic>{

    @Inject
    private ProdutoLogic logic;
    
    @Inject
    private MarcaLogic marcaLogic;

    public ProdutoBean() {
        super(Produto.class);
    }
    
    @Override
    public ProdutoLogic getLogic() {
        return this.logic;
    }
    
    public List<Marca> getMarcas() {
        try {
            return marcaLogic.buscar(null);
        } catch (ErroNegocioException ex) {
            addAviso(ex);
        } catch (ErroSistemaException ex) {
            addErro(ex);
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
}
