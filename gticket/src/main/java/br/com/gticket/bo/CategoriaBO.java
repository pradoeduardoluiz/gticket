package br.com.gticket.bo;

import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.CategoriaDAO;
import br.com.gticket.model.Categoria;

public class CategoriaBO extends BO implements ValidaFormulario {

	private CategoriaDAO dao;

	public CategoriaBO() {
		dao = new CategoriaDAO();
	}

	public void salvar(Categoria categoria) throws ValorEmBrancoException,
			ValorInvalidoException, ValorZeradoException {

		validaCamposObrigatorios(categoria);
		validaCamposUnicos(categoria);

		if (inclusao(categoria)) {

			validaCamposNaInclusao(categoria);

		}

		dao.salvar(categoria);

	}

	public CategoriaDAO getDao() {
		return dao;
	}

	public void setDao(CategoriaDAO dao) {
		this.dao = dao;
	}

	@Override
	public void validaCamposObrigatorios(Object objeto)
			throws ValorEmBrancoException {

		Categoria moduloValidado = (Categoria) objeto;

		if (campoVazio(moduloValidado.getNome())) {
			throw new ValorEmBrancoException("Campo Nome é Obrigatório!");
		}

	}

	@Override
	public boolean inclusao(Object objeto) {

		Categoria moduloValidado = (Categoria) objeto;

		return moduloValidado.getId() == null || moduloValidado.getId() == 0;

	}

	public List<Categoria> listar() {
		return dao.listar();
	}

	public Categoria buscarPorId(Integer id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Integer id) {
		dao.excluir(id);
	}

	@Override
	public void validaCamposNaInclusao(Object object)
			throws ValorInvalidoException, ValorEmBrancoException {
		// TODO Auto-generated method stub

		Categoria modulo = (Categoria) object;

	}

	@Override
	public void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		Categoria modulo = (Categoria) object;

		if (dao.jaExisteRegistroComValor(modulo.getNome(), modulo.getId(),
				"nome")) {
			throw new ValorInvalidoException(
					"Já existe categoria com este nome cadastrado!");
		}

	}

}
