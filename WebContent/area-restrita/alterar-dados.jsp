<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<section>

	<div class="container">
		<div class="section-header">
			<h2>Alterar Informações</h2>
		</div>
	
		<div class="form">
			<form action="usuario" method="post">

				<div class="form-row">
					<input type="number" name="id" value="${userSession.getId()}" style="display: none;">
					
					<div class="form-group col-md-6">
						<label>Nome</label>
						<input type="text" name="nome" class="form-control" value="${userSession.getNome()}"
							placeholder="Digite seu nome" required="required">
					</div>
					
					<div class="form-group col-md-6">
						<label>Email</label>
						<input type="email" class="form-control" name="email" value="${userSession.getEmail()}"
							 placeholder="Digite seu email" required="required">
					</div>
				</div>
				
				<div class="form-row">
					<div class="form-group col-md-4">
						<label>CPF</label>
						<input type="text" class="form-control" name="cpf" value="${userSession.getCpf()}"
							placeholder="Digite seu CPF" maxlength="14">
					</div>
					
					<div class="form-group col-md-4">
						<label>Senha</label>
						<input type="password" class="form-control" name="senha" placeholder="************">
					</div>
					
					<div class="form-group col-md-4">
						<label>Confirme sua senha</label>
						<input type="password" class="form-control" name="confirmacao-senha" placeholder="************">
					</div>
				</div>
				
				<div class="form-group float-right">
					<button type="submit" class="btn btn-default">Salvar</button>
				</div>
			</form>
		</div>

	</div>
</section>

<%@ include file="../includes/footer.jsp"%>