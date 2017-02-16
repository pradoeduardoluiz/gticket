package br.com.gticket.bo;

import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.AjusteDAO;
import br.com.gticket.model.Ajuste;
import br.com.gticket.model.Categoria;
import br.com.gticket.model.StatusProgresso;
import br.com.gticket.model.TicketDesenvolvimento;

public class AjusteBO extends BO implements ValidaFormulario {

	private AjusteDAO dao;

	public AjusteBO() {

		dao = new AjusteDAO();

	}

	public void salvar(Ajuste ajuste) throws ValorEmBrancoException,
			ValorZeradoException, ValorInvalidoException {

		validaCamposObrigatorios(ajuste);

		if (inclusao(ajuste)) {
			ajuste.setConcluido(false);
		}

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
	public boolean inclusao(Object objeto) {

		Ajuste ajuste = (Ajuste) objeto;

		return ajuste.getId() == null || ajuste.getId() == 0;

	}

	public Ajuste buscarPorId(Integer editarId) {

		return dao.buscarPorId(editarId);
	}

	public void excluir(Integer id) {

		dao.excluir(id);

	}

	public List<Ajuste> listar(TicketDesenvolvimento ticket) {

		return dao.listar(ticket);
	}

	public void finalizar(Integer id) {

		mudarConclusao(id, true);

	}

	public void reabrir(Integer id) {

		mudarConclusao(id, false);

	}

	private void mudarConclusao(Integer id, boolean b) {
		Ajuste ajuste = dao.buscarPorId(id);

		ajuste.setConcluido(b);

		dao.salvar(ajuste);
	}

}
