package br.com.gticket.bo;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.AjusteDAO;
import br.com.gticket.model.Ajuste;

public class AjusteBO extends BO implements ValidaFormulario {

	private AjusteDAO dao;

	public AjusteBO() {

		dao = new AjusteDAO();

	}

	public void salvar(Ajuste ajuste) throws ValorEmBrancoException,
			ValorZeradoException, ValorInvalidoException {

		validaCamposObrigatorios(ajuste);

		dao.salvar(ajuste);

	}

	@Override
	public void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		Ajuste ajuste = (Ajuste) object;

		if (campoVazio(ajuste.getComentario())) {
			throw new ValorEmBrancoException("Campo comentário é obrigatório");
		}

		if (campoVazio(ajuste.getProgramas())) {
			throw new ValorEmBrancoException("Campo programas é obrigatório");
		}

	}

	@Override
	public void validaCamposNaInclusao(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {
	}

	@Override
	public void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

	}

	@Override
	public boolean inclusao(Object object) {
		return false;
	}

	public Ajuste buscarPorId(Integer editarId) {
		// TODO Auto-generated method stub
		return null;
	}

}
