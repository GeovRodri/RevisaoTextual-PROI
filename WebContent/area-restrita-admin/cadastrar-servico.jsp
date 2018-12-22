<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<section>

	<div class="container">
		<div class="section-header">
			<h2>Cadastrar servico</h2>
		</div>

		<div class="form">
			<form action="cadastrar-servico" method="post">

				<div class="form-group col-md-6">
					<label>Descrição</label>
					<textarea id="descricao" name="descricao" class="form-control"
						placeholder="Digite a descrição" rows="3" cols="33"
						required="required"></textarea>
				</div>

				<div class="form-group col-md-6">
					<label>Caracteristica</label>
					<textarea id="caracteristica" name="caracteristica"
						class="form-control" placeholder="Digite as características"
						rows="3" cols="33" required="required"></textarea>
				</div>


				<div class="form-row">
					<div class="form-group col-md-4">
						<label>Valores</label> <input type="number" class="form-control"
							name="valorPagina" value="" placeholder="Digite o valor">

						<input type="number" class="form-control" name="valorLauda"
							value="" placeholder="Digite o valor"> <input
							type="number" class="form-control" name="valorPalavra" value=""
							placeholder="Digite o valor">
					</div>
				</div>
				<div class="form-group float-left">
					<button type="submit" class="btn btn-default">Salvar</button>
				</div>
			</form>
		</div>

	</div>
</section>

<%@ include file="../includes/footer.jsp"%>