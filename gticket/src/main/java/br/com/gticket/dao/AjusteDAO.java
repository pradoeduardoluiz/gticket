package br.com.gticket.dao;

import br.com.gticket.model.Ajuste;

public class AjusteDAO extends DAO {

	public void salvar(Ajuste ajuste) {

		getEntityManager().merge(ajuste);

	}

}
