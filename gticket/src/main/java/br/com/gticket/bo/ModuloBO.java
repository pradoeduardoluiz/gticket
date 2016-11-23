package br.com.gticket.bo;

import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.ModuloDAO;
import br.com.gticket.model.Modulo;

public class ModuloBO extends BO implements ValidaFormulario {

	private ModuloDAO dao;

	public ModuloBO() {
		dao = new ModuloDAO();
	}

	public void salvar(Modulo modulo) throws ValorEmBrancoException,
			ValorInvalidoException, ValorZeradoException {

		validaCamposObrigatorios(modulo);
		validaCamposUnicos(modulo);

		if (inclusao(modulo)) {

			validaCamposNaInclusao(modulo);

		}

		dao.salvar(modulo);

	}

	public ModuloDAO getDao() {
		return dao;
	}

	public void setDao(ModuloDAO dao) {
		this.dao = dao;
	}

	@Override
	public void validaCamposObrigatorios(Object objeto)
			throws ValorEmBrancoException {

		Modulo moduloValidado = (Modulo) objeto;

		if (campoVazio(moduloValidado.getNome())) {
			throw new ValorEmBrancoException("Campo Nome é Obrigatório!");
		}

		if (campoVazio(moduloValidado.getDescricao())) {
			throw new ValorEmBrancoException("Campo Descrição é Obrigatório!");
		}

	}

	@Override
	public boolean inclusao(Object objeto) {

		Modulo moduloValidado = (Modulo) objeto;

		return moduloValidado.getId() == null || moduloValidado.getId() == 0;

	}

	public List<Modulo> listar() {
		return dao.listar();
	}

	public Modulo buscarPorId(Integer id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Integer id) {
		dao.excluir(id);
	}

	@Override
	public void validaCamposNaInclusao(Object object)
			throws ValorInvalidoException, ValorEmBrancoException {
		// TODO Auto-generated method stub

		Modulo modulo = (Modulo) object;

	}

	@Override
	public void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		Modulo modulo = (Modulo) object;

		if (dao.jaExisteRegistroComValor(modulo.getNome(), modulo.getId(),
				"nome")) {
			throw new ValorInvalidoException(
					"Já existe módulo com este nome cadastrado!");
		}

	}

}
