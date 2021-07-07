package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.model.Marca;
import br.edu.iftm.ecommerce.repository.MarcaRepository;
import br.edu.iftm.ecommerce.util.Assert;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.List;
import javax.inject.Inject;

public class MarcaLogic implements CrudLogic<Marca> {

    @Inject
    private MarcaRepository repository;

    @Override
    public Marca salvar(Marca entidade) throws ErroNegocioException, ErroSistemaException {
        if(Assert.isStringNullOrEmpty(entidade.getNome())) {
            throw new ErroNegocioException("Por favor informe o nome da marca.");
        }
        entidade = this.repository.salvar(entidade);
        return entidade;
    }

    @Override
    public void deletar(Marca entidade) throws ErroNegocioException, ErroSistemaException {
        this.repository.remover(entidade.getId());
    }

    @Override
    public Marca buscarPorId(Marca entidade) throws ErroNegocioException, ErroSistemaException {
        entidade = this.repository.buscarPorID(entidade.getId());
        return entidade;
    }

    @Override
    public List<Marca> buscar(Marca entidade) throws ErroNegocioException, ErroSistemaException {
        return this.repository.listar();
    }
    
}
