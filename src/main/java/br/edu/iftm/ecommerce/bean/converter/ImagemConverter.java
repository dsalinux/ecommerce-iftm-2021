package br.edu.iftm.ecommerce.bean.converter;

import br.edu.iftm.ecommerce.model.Imagem;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Imagem.class, value = "imagemConverter")
public class ImagemConverter implements Converter<Imagem>{

    @Override
    public Imagem getAsObject(FacesContext fc, UIComponent uic, String id) {
        System.out.println(id);
        if (id != null && !"".equals(id)) {
            Imagem imagem = (Imagem) uic.getAttributes().get("imagem_" + id);
            return imagem;

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Imagem imagem) {
        if(imagem == null){
            return "";
        }

        if(imagem.getId() != null){
            uic.getAttributes().put("imagem_"+imagem.getId(), imagem);
            return imagem.getId().toString();
        }
        return "";
    }
    
}
