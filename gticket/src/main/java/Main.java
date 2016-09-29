import java.util.Date;

import br.com.gticket.bo.UsuarioBo;
import br.com.gticket.model.Perfil;
import br.com.gticket.model.Usuario;
import br.com.gticket.util.JpaUtil;

public class Main {

	public static void main(String[] args) {

		JpaUtil.init();

		Usuario usuario = new Usuario();
		UsuarioBo bo = new UsuarioBo();

		usuario.setNome("Luiz Eduardo do Prado");
		usuario.setCpf("07981723981");
		usuario.setDataNascimento(new Date());
		usuario.setEmail("prado.eduardo.luiz@gmail.com");
		usuario.setObsercacao("");
		usuario.setPerfil(Perfil.DESENVOLVEDOR);
		usuario.setRg("98640916");
		usuario.setSenha("teste1234");
		usuario.setConfirmacaoDeSenha("teste1234");

		try {
			bo.salvar(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
