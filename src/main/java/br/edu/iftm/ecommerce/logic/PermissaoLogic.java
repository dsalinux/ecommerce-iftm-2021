package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.model.Permissao;
import br.edu.iftm.ecommerce.repository.PermissaoRepository;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class PermissaoLogic implements CrudLogic<Permissao>{

    @Inject
    private PermissaoRepository repository;
    
    @Override
    public Permissao salvar(Permissao entidade) throws ErroNegocioException, ErroSistemaException {
        entidade = repository.salvar(entidade);
        return entidade;
    }

    @Override
    public void deletar(Permissao entidade) throws ErroNegocioException, ErroSistemaException {
        repository.remover(entidade.getId());
    }

    @Override
    public Permissao buscarPorId(Permissao entidade) throws ErroNegocioException, ErroSistemaException {
        entidade = this.repository.buscarPorID(entidade.getId());
        return entidade;
    }

    @Override
    public List<Permissao> buscar(Permissao entidade) throws ErroNegocioException, ErroSistemaException {
        List<Permissao> permissoes = repository.listar();
        return permissoes;
    }
    
}
