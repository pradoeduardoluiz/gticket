package br.com.gticket.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gticket.bo.ClienteBO;
import br.com.gticket.model.Cliente;

@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

	ClienteBO bo;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		bo = new ClienteBO();

		if (value != null) {
			for (Cliente cliente : bo.listar()) {
				if (value.equals(cliente.getRazaoSocial())) {
					return cliente;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && !value.equals("")) {
			Cliente cliente = (Cliente) value;
			return cliente.getRazaoSocial();
		}
		return null;
	}

}
