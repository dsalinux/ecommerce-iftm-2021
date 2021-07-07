package br.edu.iftm.ecommerce.repository;

import br.edu.iftm.ecommerce.model.Produto;
import java.io.Serializable;

public class ProdutoRepository extends GenericRepository<Produto, Integer>{
    
    public ProdutoRepository() {
        super(Produto.class);
    }
    
}
