<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<div class="container-fluid">
	<c:if test="${not empty successMsg}">
		<div class="alert alert-success" role="alert">${successMsg}</div>
	</c:if>

	<div class="table-responsive">
		<table class="table table-bordered" id="dataTable">
			<thead>
				<tr>
					<th>Id</th>
					<th>Descricao</th>
					<th>Caracteristicas</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="servico" items="${servicos}" varStatus="id">
					<tr>
						<td>${servico.id}</td>
						<td>${servico.descricao}</td>
						<td>${servico.caracteristicas}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	<button type="submit" class="btn btn-default" onclick="redirecionar()">Alterar
		Servicos</button>
</div>
<script>
function redirecionar(){
	window.location.href = "http://localhost:8080/RevisaoTextual-PROI/area-restrita-admin/alterar-servico.jsp";

}
</script>

<%@ include file="../includes/footer.jsp"%>
