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
							placeholder="Digite seu CPF" maxlength="14" required="required">
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
				
				<!-- Endereço -->
				<div class="form-row">
					<div class="form-group col-md-1">
						<label>UF</label>
						<input type="text" class="form-control" name="uf" value="${userSession.getUf()}"
							placeholder="UF" maxlength="2" required="required">
					</div>
					
					<div class="form-group col-md-3">
						<label>Localidade</label>
						<input type="text" class="form-control" name="localidade" value="${userSession.getLocalidade()}"
							placeholder="Digite a localidade" required="required">
					</div>
					
					<div class="form-group col-md-2">
						<label>CEP</label>
						<input type="text" class="form-control" name="cep" value="${userSession.getCep()}"
							placeholder="Digite o CEP" required="required">
					</div>
					
					<div class="form-group col-md-3">
						<label>Bairro</label>
						<input type="text" class="form-control" name="bairro" value="${userSession.getBairro()}"
							placeholder="Digite o bairro" required="required">
					</div>
					
					<div class="form-group col-md-2">
						<label>Logradouro</label>
						<input type="text" class="form-control" name="logradouro" value="${userSession.getLogradouro()}"
							placeholder="Digite o logradouro" required="required">
					</div>
					
					<div class="form-group col-md-1">
						<label>Numero</label>
						<input type="number" class="form-control" name="numero" value="${userSession.getNumero()}"
							placeholder="Numero" required="required">
					</div>
				</div>
				
				<div class="form-group float-right">
					<button class="btn btn-danger" onclick="removerUsuario()" type="button">Excluir Conta</button>
					<button type="submit" class="btn btn-primary">Salvar</button>
				</div>
			</form>
		</div>

	</div>
</section>

<%@ include file="../includes/footer.jsp"%>