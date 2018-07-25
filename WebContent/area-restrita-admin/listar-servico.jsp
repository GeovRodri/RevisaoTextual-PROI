<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<div class="container-fluid">
	<c:if test="${not empty successMsg}">
		<div class="alert alert-success" role="alert">
		  ${successMsg}
		</div>
	</c:if>

	<div class="table-responsive">
		<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			<thead>
				<tr>
					<th>Id</th>
					<th>Descrição</th>
					<th>Caracteristicas</th>
					<th>Valor página</th>
					<th>Valor lauda</th>
					<th>Valor palavra</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="servico" items="${listAll}" varStatus="id" method="get">
					<tr>
						<td>${servico.id}</td>
						<td>${servico.descricao}</td>
						<td>${servico.caracteristicas}</td>
						<td>${servico.valores.pagina}</td>
						<td>${servico.valores.lauda}</td>
						<td>${servico.valores.palavra}</td>
						
						<td style="text-align: center;">
							<button onClick=" removerServico()" type="button">
							</button>
							
							<button type="submit" class="btn btn-default">Salvar</button>
        
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>


<%@ include file="../includes/footer.jsp"%>
