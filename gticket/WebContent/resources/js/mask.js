window.onload = function() {
	$(document).ready(function() {
		$(function() {
			$("#input_telefone").mask("(99) 9999-9999");
			$("#input_telefone1").mask("(99) 9999-9999");
			$("#input_telefone2").mask("(99) 9999-9999");
			$("#input_celular").mask("(99) 99999-9999");
			$("#input_fax").mask("(99) 9999-9999");
			$("#input_cep").mask("99999-999");
			$("#input_data").mask("99/99/9999");
			$("#input_cpf").mask("999.999.999-99");
			$("#input_cnpj").mask("99.999.999/9999-99");
			$("#input_tempoDesenvolvimento").mask("99:99");
		});
	});
}
