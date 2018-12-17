<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>
<section>

	<div class="container">
		<div class="section-header" style="text-align: center;">
			<h2
				style="color: navy; font-family: 'Times New Roman', Times, serif;">Area
				Restrita Admin</h2>
			<h3
				style="color: navy; font-family: 'Times New Roman', Times, serif;">WELCOME</h3>
		</div>
		<div style="position: relative; text-align: center">
			<button class="btn btn-info" onclick="cadastrarServico()"
				type="button">Cadastrar Servico</button>
			<button class="btn btn-info" onclick="listarServico()" type="button">Listar
				Servico</button>
			<button class="btn btn-info" onclick="alterarServico()" type="button">Alterar
				Servicos</button>
			<button class="btn btn-info" onclick="deletarServico()" type="button">Deletar
				Servicos</button>
		</div>
	</div>

</section>
<script>
	function cadastrarServico(){
		alert("Desculpe, problemas para realizar cadastros");
	}
	
	function listarServico(){
		window.location.href = "http://localhost:8080/RevisaoTextual-PROI/area-restrita-admin/listar-servico.jsp";
	}
	
	function alterarServico(){
		window.location.href = "http://localhost:8080/RevisaoTextual-PROI/area-restrita-admin/alterar-servico.jsp";
	}
	
	function deletarServico(){
		window.location.href = "http://localhost:8080/RevisaoTextual-PROI/area-restrita-admin/deletar-servico.jsp";
	}
</script>
<%@ include file="../includes/footer.jsp"%>