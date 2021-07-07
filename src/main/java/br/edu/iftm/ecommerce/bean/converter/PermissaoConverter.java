package br.edu.iftm.ecommerce.bean.converter;

import br.edu.iftm.ecommerce.model.Permissao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Permissao.class, value = "permissaoConverter")
public class PermissaoConverter implements Converter<Permissao>{

    @Override
    public Permissao getAsObject(FacesContext fc, UIComponent uic, String id) {
        System.out.println(id);
        if (id != null && !"".equals(id)) {
            Permissao permissao = (Permissao) uic.getAttributes().get("permissao_" + id);
            return permissao;

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Permissao permissao) {
        if(permissao == null){
            return "";
        }

        if(permissao.getId() != null){
            uic.getAttributes().put("permissao_"+permissao.getId(), permissao);
            return permissao.getId().toString();
        }
        return "";
    }
    
}
