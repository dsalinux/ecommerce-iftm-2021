package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.model.Usuario;
import br.edu.iftm.ecommerce.repository.UsuarioRepository;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class UsuarioLogic implements CrudLogic<Usuario>{

    @Inject
    private UsuarioRepository repository;
    
    @Override
    public Usuario salvar(Usuario entidade) {
        if(entidade.getDataCadastro() == null){
            entidade.setDataCadastro(new Date());
        }
        entidade = repository.salvar(entidade);
        return entidade;
    }

    @Override
    public void deletar(Usuario entidade) {
        repository.remover(entidade.getId());
    }

    @Override
    public void buscarPorId(Usuario entidade) {
        
    }

    @Override
    public List<Usuario> buscar(Usuario entidade) {
        List<Usuario> usuarios = repository.listar();
        return usuarios;
    }
    
}
