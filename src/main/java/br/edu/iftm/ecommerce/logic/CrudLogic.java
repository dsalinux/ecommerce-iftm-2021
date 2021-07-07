package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.List;

public interface CrudLogic<E> extends Serializable {
    
    E salvar(E entidade) throws ErroNegocioException, ErroSistemaException;
    void deletar(E entidade) throws ErroNegocioException, ErroSistemaException;
    E buscarPorId(E entidade) throws ErroNegocioException, ErroSistemaException;
    List<E> buscar(E entidade) throws ErroNegocioException, ErroSistemaException;
    
}
