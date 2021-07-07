package br.edu.iftm.ecommerce.bean.converter;

import br.edu.iftm.ecommerce.model.Marca;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Marca.class)
public class MarcaConverter implements Converter<Marca>{

    @Override
    public Marca getAsObject(FacesContext fc, UIComponent uic, String id) {
        System.out.println(id);
        if (id != null && !"".equals(id)) {
            Marca marca = (Marca) uic.getAttributes().get("marca_" + id);
            return marca;

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Marca marca) {
        if(marca == null){
            return "";
        }

        if(marca.getId() != null){
            uic.getAttributes().put("marca_"+marca.getId(), marca);
            return marca.getId().toString();
        }
        return "";
    }
    
}
