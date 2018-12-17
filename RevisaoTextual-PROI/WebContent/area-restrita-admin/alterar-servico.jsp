<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>
<!DOCTYPE html>
<section>

	<div class="container">
		<div class="section-header">
			<h2>Alterar Informações dos Serviços</h2>
		</div>

		<div class="form">
			<form action="alterar-servico" method="post">

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
				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Descrição</label> <input type="text" class="form-control"
							name="descricao" value="" placeholder="Digite a descrição"
							required="required">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Características</label> <input type="text"
							class="form-control" name="caracteristica" value=""
							placeholder="Digite as caracteristicas" required="required">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Valor por pagina</label> <input type="number"
							class="form-control" name="valorPagina" value=""
							placeholder="Digite o valor">
					</div>
					<div class="form-group col-md-6">
						<label>Valor por lauda</label> <input type="number"
							class="form-control" name="valorLauda" value=""
							placeholder="Digite o valor">
					</div>
					<div class="form-group col-md-6">
						<label>Valor por palavra</label> <input type="number"
							class="form-control" name="valorPalavra" value=""
							placeholder="Digite o valor">
					</div>


				</div>


				<div class="form-group float-right">
					<button class="btn btn-danger" onclick="removerServico()"
						type="button">Excluir Servico</button>
					<button type="submit" class="btn btn-default">Alterar</button>
				</div>
			</form>
		</div>

	</div>
</section>
<%@ include file="../includes/footer.jsp"%>