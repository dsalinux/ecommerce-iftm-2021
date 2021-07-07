package br.edu.iftm.ecommerce.repository;

import br.edu.iftm.ecommerce.model.Permissao;
import java.io.Serializable;

public class PermissaoRepository extends GenericRepository<Permissao, Integer>{

    public PermissaoRepository() {
        super(Permissao.class);
    }
   
    
}
