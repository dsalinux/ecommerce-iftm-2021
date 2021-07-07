package br.edu.iftm.ecommerce.bean;

import br.edu.iftm.ecommerce.logic.ImagemLogic;
import br.edu.iftm.ecommerce.model.Imagem;
import br.edu.iftm.ecommerce.util.Config;
import br.edu.iftm.ecommerce.util.MimeTypes;
import br.edu.iftm.ecommerce.util.Sha1Hex;
import br.edu.iftm.ecommerce.util.exception.ErroNegocioException;
import br.edu.iftm.ecommerce.util.exception.ErroSistemaException;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FilesUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.util.SerializableSupplier;

@Named
@SessionScoped
@Getter
@Setter
public class ImagemBean extends JSFUtil {

    @Inject
    private ImagemLogic logic;
    private List<Imagem> imagens;

    @PostConstruct
    public void init(){
        listar();
    }
    
    public void upload(FilesUploadEvent event) {
        try {            
            for(UploadedFile upFile : event.getFiles().getFiles()) {
                Imagem imagem = new Imagem();
                String nomeOriginal = upFile.getFileName();
                //Lê o nome do arquivo original e corta o nome para no máximo 45 caracteres
                nomeOriginal = nomeOriginal.length() < 45? nomeOriginal: nomeOriginal.substring(0, 45);
                imagem.setNome(nomeOriginal);
                imagem.setFile(upFile);
                logic.salvar(imagem);
            }
            addInfo(event.getFiles().getFiles().size() + " arquivos carregados com sucesso.");
            listar();
        } catch (ErroNegocioException ex) {
            addAviso(ex);
        } catch (ErroSistemaException ex) {
            addErro(ex);
            Logger.getLogger(ImagemBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void listar(){
        try {
            this.imagens = this.logic.buscar(null);
        } catch (ErroNegocioException ex) {
            addAviso(ex);
        } catch (ErroSistemaException ex) {
            addErro(ex);
            Logger.getLogger(ImagemBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
