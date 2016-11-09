package br.com.gticket.bo;

import java.util.Date;
import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.dao.ModuloDAO;
import br.com.gticket.model.Modulo;

public class ModuloBO implements ValidaFormulario {

	private ModuloDAO dao;

	public void salvar(Modulo modulo) throws ValorEmBrancoException,
			ValorInvalidoException {

		validaCamposObrigatorios(modulo);

		if (inclusao(modulo)) {

			if (dao.buscarPorNome(modulo.getNome())) {
				throw new ValorInvalidoException(
						"Já existe módulo com este nome cadastrado!");
			}

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
	public boolean campoVazio(String campo) {
		// TODO Auto-generated method stub

		if (campo == null || campo.isEmpty()) {
			return true;
		}
		return false;

	}

	@Override
	public boolean campoVazio(Date campo) {
		if (campo == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean campoVazio(Integer campo) {
		// TODO Auto-generated method stub

		if (campo == null || campo == 0) {
			return true;
		}
		return false;

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

}
