package br.com.gticket.bo;

import java.util.Date;
import java.util.List;

import javax.mail.Session;

import org.apache.commons.mail.EmailException;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.controller.LoginMB;
import br.com.gticket.dao.TicketContatoDAO;
import br.com.gticket.model.TicketContato;
import br.com.gticket.model.Usuario;
import br.com.gticket.util.EmailUtil;
import br.com.gticket.util.SessionUtil;
import br.com.gticket.util.Util;

public class TicketContatoBO extends TicketBO {

	private TicketContatoDAO dao;

	public TicketContatoBO() {
		dao = new TicketContatoDAO();
	}

	public TicketContato salvar(TicketContato ticket) throws Exception {

		validaCamposObrigatorios(ticket);
		TicketContato ticketSalvo = null;
		boolean inclusao = false;

		if (inclusao(ticket)) {
			inicializar(ticket);
			inclusao = true;
		}

		ticketSalvo = dao.salvar(ticket);

		if (inclusao) {
			if (ticket.getEnviarEmail()) {
				try {
					EmailUtil.enviarEmailTicketAbertura(ticketSalvo);
				} catch (EmailException e) {

					throw new Exception("Ticket #" + ticketSalvo.getId()
							+ "Foi criado mas não foi possível enviar e-mail!");
				}
			}
		}

		return ticketSalvo;

	}

	private void inicializar(TicketContato ticket) {
		ticket.setAtivo(true);
		ticket.setUsuarioInclusao((Usuario) SessionUtil
				.getParam("usuarioLogado"));
		ticket.setDataDeFinalizacao(new Date());
	}

	public TicketContato buscarPorId(Integer id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Integer id) {
		dao.excluir(id);
	}

	public List<TicketContato> listar() {
		return dao.listar();
	}

}
