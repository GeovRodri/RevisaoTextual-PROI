<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<section>
	<div class="container">
		<div class="section-header">
			<h2>Realizar Orçamento</h2>
		</div>
	
		<div class="form">

			<form action="orcamento" method="post" enctype="multipart/form-data">
				<div class="form-row">	
					<div class="form-group col-md-4">
						<label>Serviço</label>
						<select name="servicoId" class="form-control" required>
							<option value="">Selecione um serviço </option>
							<option value="1">1</option>
						</select>
					</div>
				</div>
				
				<div class="form-row">	
					<div class="form-group col-md-4">
						<label>Forma de cobrança</label>
						<select name="cobranca" class="form-control" required>
							<option value="">Selecione um forma de cobrança </option>
							<option value="0">1</option>
						</select>
					</div>
				</div>
					
				<div class="form-row">
					<div class="form-group col-md-4">
						<label>Arquivo</label>
						<input type="file" name="file" class="form-control-file">
					</div>
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Gerar</button>
				</div>
			</form>
		</div>
	</div>
</section>

<%@ include file="../includes/footer.jsp"%>