package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.model.Produto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@ApplicationScoped
@Getter @Setter
public class ProdutoSiteBean extends JSFUtil {
    
    private List<Produto> produtos;
    private Long tempoEntreAtualizacaoes = 20000L;
    private Long ultimaAtualizacao;
    
    @PostConstruct
    public void init(){
        this.ultimaAtualizacao = new Date().getTime() - this.tempoEntreAtualizacaoes;
    }
    
    private Long getProximaAtualizacao(){
        return this.ultimaAtualizacao + this.tempoEntreAtualizacaoes;
    }
    private boolean atualizarLista(){
        //O if abaixo pode ser substituido por: return getProximaAtualizacao() < new Date().getTime();
        if(getProximaAtualizacao() < new Date().getTime() ){
            return true;
        } else {
            return false;
        }
    }
    public List<Produto> getProdutos(){
        if(this.atualizarLista()){
            //Aqui é só um exemplo para retornar uma lista
            this.produtos = new ArrayList<>();
            this.produtos.add(new Produto(1, "Computador I7 8GB", "Computador com processador Intel Core I7 de 10ª geração SSD M2 500GB.", null, new BigDecimal(5000f), null, null, null));
            this.produtos.add(new Produto(2, "Computador I5 8GB", "Computador com processador Intel Core I5 de 10ª geração.", null, new BigDecimal(4000f), null, null, null));
            this.produtos.add(new Produto(3, "Computador I5 4GB", "Computador com processador Intel Core I5 de 10ª geração.", null, new BigDecimal(3500f), null, null, null));
            this.produtos.add(new Produto(4, "Computador I3 4GB", "Computador com processador Intel Core I3", null, new BigDecimal(3000f), null, null, null));
        }
        return this.produtos;
    }
    
    
    
}
