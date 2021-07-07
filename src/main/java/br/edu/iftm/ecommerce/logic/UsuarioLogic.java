package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.model.Usuario;
import br.edu.iftm.ecommerce.repository.UsuarioRepository;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class UsuarioLogic implements CrudLogic<Usuario>{

    @Inject
    private UsuarioRepository repository;
    
    @Override
    public Usuario salvar(Usuario entidade) throws ErroNegocioException, ErroSistemaException {
        if(entidade.getDataCadastro() == null){
            entidade.setDataCadastro(new Date());
        }
        if("".equals(entidade.getNome())) {
            throw new ErroNegocioException("Nome do usuário é obrigatório.");
        }
        entidade = repository.salvar(entidade);
        return entidade;
    }

    @Override
    public void deletar(Usuario entidade) throws ErroNegocioException, ErroSistemaException {
        throw new ErroSistemaException("Erro usuário não pode ser deletado.");
        //repository.remover(entidade.getId());
    }

    @Override
    public Usuario buscarPorId(Usuario entidade) throws ErroNegocioException, ErroSistemaException {
        entidade = this.repository.buscarPorID(entidade.getId());
        return entidade;
    }

    @Override
    public List<Usuario> buscar(Usuario entidade) throws ErroNegocioException, ErroSistemaException {
        List<Usuario> usuarios = repository.listar();
        return usuarios;
    }
    
}
