package br.edu.iftm.ecommerce.repository;

import br.edu.iftm.ecommerce.model.Usuario;
import java.io.Serializable;

public class UsuarioRepository extends GenericRepository<Usuario, Integer>{

    public UsuarioRepository() {
        super(Usuario.class);
    }
   
    
}
