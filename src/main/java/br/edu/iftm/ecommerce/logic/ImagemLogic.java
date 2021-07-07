package br.edu.iftm.ecommerce.logic;

import br.edu.iftm.ecommerce.model.Imagem;
import br.edu.iftm.ecommerce.repository.ImagemRepository;
import br.edu.iftm.ecommerce.util.Assert;
import br.edu.iftm.ecommerce.util.Config;
import br.edu.iftm.ecommerce.util.MimeTypes;
import br.edu.iftm.ecommerce.util.Sha1Hex;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.model.file.UploadedFile;

public class ImagemLogic implements CrudLogic<Imagem> {

    @Inject
    private ImagemRepository repository;

    @Override
    public Imagem salvar(Imagem entidade) throws ErroNegocioException, ErroSistemaException {
        if(Assert.isStringNullOrEmpty(entidade.getNome())) {
            throw new ErroNegocioException("Por favor informe o nome da imagem.");
        }
        if(Assert.isNull(entidade.getDataCriacao())) {
            entidade.setDataCriacao(new Date());
        }
        UploadedFile file = entidade.getFile();
        String nomeArquivo = gerarNomeArquivo(file);
        entidade.setCodigo(nomeArquivo);
        entidade = this.repository.salvar(entidade);
        salvarEmDisco(file, nomeArquivo);
        return entidade;
    }

    private void salvarEmDisco(UploadedFile file, String nomeArquivo) throws ErroSistemaException{
        try {
            OutputStream out = new FileOutputStream(Config.FOLDER_IMAGES + nomeArquivo);
            out.write(file.getContent());
            out.close();
        } catch (IOException ex) {
            throw new ErroSistemaException("Erro ao salvar a imagem em disco.", ex);
        }
    }
    
    public String gerarNomeArquivo(UploadedFile file) throws ErroSistemaException{
        String extesao = MimeTypes.getDefaultExt(file.getContentType());
        String nomeArquivo = Sha1Hex.getHexWithRandomSalt(file.getContent()) + "." + extesao;
        return nomeArquivo;
    }
    
    @Override
    public void deletar(Imagem entidade) throws ErroNegocioException, ErroSistemaException {
        this.repository.remover(entidade.getId());
    }

    @Override
    public Imagem buscarPorId(Imagem entidade) throws ErroNegocioException, ErroSistemaException {
        entidade = this.repository.buscarPorID(entidade.getId());
        return entidade;
    }
    
    @Override
    public List<Imagem> buscar(Imagem entidade) throws ErroNegocioException, ErroSistemaException {
        return this.repository.listar();
    }
    
}
