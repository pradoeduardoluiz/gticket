package br.com.gticket.bo;

import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.ClienteDAO;
import br.com.gticket.model.Cliente;

public class ClienteBO extends BO implements ValidaFormulario {

	private ClienteDAO dao;

	public ClienteBO() {
		dao = new ClienteDAO();
	}

	public void salvar(Cliente cliente) throws ValorEmBrancoException,
			ValorInvalidoException, ValorZeradoException {

		validaCamposObrigatorios(cliente);
		validaCamposUnicos(cliente);

		if (inclusao(cliente)) {
			validaCamposNaInclusao(cliente);
		}

		dao.salvar(cliente);

	}

	@Override
	public void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException {

		Cliente cliente = (Cliente) object;

		if (campoVazio(cliente.getRazaoSocial())) {
			throw new ValorEmBrancoException(
					"Campo Raz�o Social � Obrigat�rio!");
		}

		if (campoVazio(cliente.getNomeFantasia())) {
			throw new ValorEmBrancoException(
					"Campo Nome Fantasia � Obrigat�rio!");
		}

		if (campoVazio(cliente.getCnpj())) {
			throw new ValorEmBrancoException("Campo CNPJ � Obrigat�rio!");
		}

		if (!cnpjValido(cliente.getCnpj())) {
			throw new ValorEmBrancoException("Campo CNPJ � inv�lido!");
		}

		if (campoVazio(cliente.getTelefone1())) {
			throw new ValorEmBrancoException("Campo Telefone 1 � Obrigat�rio!");
		}

		if (campoVazio(cliente.getEndereco().getCep())) {
			throw new ValorEmBrancoException("Campo CEP � Obrigat�rio!");
		}

		if (campoVazio(cliente.getEndereco().getRua())) {
			throw new ValorEmBrancoException("Campo Rua � Obrigat�rio!");
		}

		if (campoVazio(cliente.getEndereco().getNumero())) {
			throw new ValorEmBrancoException("Campo N�mero � Obrigat�rio!");
		}

		if (campoVazio(cliente.getEndereco().getBairro())) {
			throw new ValorEmBrancoException("Campo Bairro � Obrigat�rio!");
		}

		if (campoVazio(cliente.getEndereco().getCidade())) {
			throw new ValorEmBrancoException("Campo Cidade � Obrigat�rio!");
		}

		if (campoVazio(cliente.getEndereco().getEstado())) {
			throw new ValorEmBrancoException("Campo Estado � Obrigat�rio!");
		}

		if (campoVazio(cliente.getContrato().getNumeroContrato())) {
			throw new ValorEmBrancoException(
					"Campo N�mero do Contrato � Obrigat�rio!");
		}

		if (campoVazio(cliente.getContrato().getNumeroDeLicencas())) {
			throw new ValorEmBrancoException(
					"Campo N�mero de Licen�as � Obrigat�rio!");
		}

		if (campoVazio(cliente.getContrato().getNumeroDeFiliais())) {
			throw new ValorEmBrancoException(
					"Campo N�mero de Filiais � Obrigat�rio!");
		}

		if (campoVazio(cliente.getContrato().getDataInicio())) {
			throw new ValorEmBrancoException(
					"Campo data de Inicio de Contrato � Obrigat�rio!");
		}

	}

	@Override
	public boolean inclusao(Object object) {
		// TODO Auto-generated method stub

		Cliente cliente = (Cliente) object;

		return cliente.getId() == null || cliente.getId() == 0;
	}

	public Cliente buscarPorId(Integer editarId) {
		// TODO Auto-generated method stub
		return dao.buscarPorId(editarId);
	}

	@Override
	public void validaCamposNaInclusao(Object object)
			throws ValorInvalidoException, ValorEmBrancoException {

		Cliente cliente = (Cliente) object;

	}

	@Override
	public void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		Cliente cliente = (Cliente) object;

		if (dao.jaExisteRegistroComValor(cliente.getRazaoSocial(),
				cliente.getId(), "razaoSocial")) {
			throw new ValorInvalidoException(
					"J� existe cliente com esta Raz�o Social cadastrada!");
		}

		if (dao.jaExisteRegistroComValor(cliente.getNomeFantasia(),
				cliente.getId(), "nomeFantasia")) {
			throw new ValorInvalidoException(
					"J� existe cliente com este Nome Fantasia cadastrado!");
		}

		if (dao.jaExisteRegistroComValor(cliente.getCnpj(), cliente.getId(),
				"cnpj")) {
			throw new ValorInvalidoException(
					"J� existe cliente com este CNPJ cadastrado!");
		}

	}

	public List<Cliente> listar() {
		return dao.listar();
	}
}
