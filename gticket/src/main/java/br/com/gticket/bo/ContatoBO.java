package br.com.gticket.bo;

import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.ContatoDAO;
import br.com.gticket.model.Contato;

public class ContatoBO extends BO implements ValidaFormulario {

	private ContatoDAO dao;

	public ContatoBO() {

		dao = new ContatoDAO();

	}

	public void salvar(Contato contato) throws ValorEmBrancoException,
			ValorZeradoException, ValorInvalidoException {

		validaCamposObrigatorios(contato);
		validaCamposUnicos(contato);

		if (inclusao(contato)) {
			validaCamposNaInclusao(contato);
		}

		dao.salvar(contato);

	}

	public Contato buscarPorId(Integer editarId) {
		// TODO Auto-generated method stub
		return dao.buscarPorId(editarId);
	}

	public List<Contato> listar() {
		// TODO Auto-generated method stub
		return dao.listar();
	}

	@Override
	public void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		Contato contato = (Contato) object;

		if (campoVazio(contato.getNome())) {
			throw new ValorEmBrancoException("Campo Nome � Obrigat�rio!");
		}

		if (campoVazio(contato.getDepartamento().toString())) {
			throw new ValorEmBrancoException(
					"Campo Departamento � Obrigat�rio!");
		}

		if (campoVazio(contato.getTelefone())) {
			throw new ValorEmBrancoException("Campo Telefone � Obrigat�rio!");
		}

		if (campoVazio(contato.getEmail())) {
			throw new ValorEmBrancoException("Campo Email � Obrigat�rio!");
		}

	}

	@Override
	public void validaCamposNaInclusao(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		Contato contato = (Contato) object;

		if (dao.jaExisteRegistroComValor(contato.getNome(), contato.getId(),
				"nome")) {
			throw new ValorInvalidoException(
					"J� existe contato com este nome cadastrado!");
		}

		if (dao.jaExisteRegistroComValor(contato.getEmail(), contato.getId(),
				"nome")) {
			throw new ValorInvalidoException(
					"J� existe contato com este email cadastrado!");
		}

	}

	@Override
	public boolean inclusao(Object object) {
		Contato contato = (Contato) object;
		return contato.getId() == null || contato.getId() == 0;
	}

	public void excluir(Integer id) {
		dao.excluir(id);
	}

}
