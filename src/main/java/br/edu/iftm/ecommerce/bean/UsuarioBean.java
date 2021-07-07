package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.CrudLogic;
import br.edu.iftm.ecommerce.logic.PermissaoLogic;
import br.edu.iftm.ecommerce.logic.UsuarioLogic;
import br.edu.iftm.ecommerce.model.Permissao;
import br.edu.iftm.ecommerce.model.Usuario;
import br.edu.iftm.ecommerce.util.StringHelper;
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
public class UsuarioBean extends CrudBean<Usuario, UsuarioLogic>{

    @Inject
    private UsuarioLogic logic;

    @Inject
    private PermissaoLogic permisssaoLogic;
    
    private List<Permissao> permissoes;
    
    private String senha;
    
    public UsuarioBean() {
        super(Usuario.class);
    }
   
    @Override
    public void salvar() {
        if(StringHelper.isNotEmpty(senha)) {
            getEntidade().setSenha(senha);
        }
        super.salvar();
    }
   
    
    @Override
    public UsuarioLogic getLogic() {
        return this.logic;
    }

    public List<Permissao> getPermissoes(){
        try {
            return permisssaoLogic.buscar(null);
        } catch (ErroNegocioException ex) {
            addAviso(ex);
        } catch (ErroSistemaException ex) {
            addErro(ex);
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
}
