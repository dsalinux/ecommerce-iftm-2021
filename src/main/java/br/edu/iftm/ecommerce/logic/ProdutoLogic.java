package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.model.Produto;
import br.edu.iftm.ecommerce.repository.ProdutoRepository;
import br.edu.iftm.ecommerce.util.Assert;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class ProdutoLogic implements CrudLogic<Produto> {

    @Inject
    private ProdutoRepository repository;

    @Override
    public Produto salvar(Produto entidade) throws ErroNegocioException, ErroSistemaException {
        if(Assert.isStringNullOrEmpty(entidade.getNome())) {
            throw new ErroNegocioException("Por favor informe o Nome da produto.");
        }
        if(Assert.isStringNullOrEmpty(entidade.getDescricao())) {
            throw new ErroNegocioException("Por favor informe a Descição do produto.");
        }
        if(Assert.isNull(entidade.getValor())) {
            throw new ErroNegocioException("Por favor informe o Valor do produto.");
        }
        if(Assert.isNull(entidade.getMarca())) {
            throw new ErroNegocioException("Por favor informe a Marca do produto.");
        }
        if(Assert.isNull(entidade.getDataCriacao())) {
            entidade.setDataCriacao(new Date());
        }
        entidade = this.repository.salvar(entidade);
        return entidade;
    }

    @Override
    public void deletar(Produto entidade) throws ErroNegocioException, ErroSistemaException {
        this.repository.remover(entidade.getId());
    }

    @Override
    public Produto buscarPorId(Produto entidade) throws ErroNegocioException, ErroSistemaException {
        entidade = this.repository.buscarPorID(entidade.getId());
        return entidade;
    }

    @Override
    public List<Produto> buscar(Produto entidade) throws ErroNegocioException, ErroSistemaException {
        return this.repository.listar();
    }
    
}
