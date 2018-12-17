<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>
<!DOCTYPE html>
<section>

	<div class="container">
		<div class="section-header">
			<h2>Deletar Serviço</h2>
		</div>

		<div class="form">
			<form action="deletar-servico" method="post">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Serviço</label> <select name="servicoId"
							class="form-control" required>
							<option value="">Selecione um serviço</option>
							<c:forEach var="sevicoItem" items="${servicos}" varStatus="id">
								<option value="${sevicoItem.id}">${sevicoItem.descricao}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group float-right">
					<button class="btn btn-danger" type="submit"
						class="btn btn-default">Deletar</button>
				</div>
			</form>
		</div>

	</div>
</section>
<%@ include file="../includes/footer.jsp"%>