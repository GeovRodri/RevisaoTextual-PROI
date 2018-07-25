<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<section>
	<div class="container">
		<div class="section-header">
			<h2>Login Administrador</h2>
		</div>
	
		<div class="form d-flex justify-content-center">
			
			<div class="col-sm-4">
				<form class="form-signin" action="<%=request.getContextPath()%>/login" method="POST">
					<input name="tipo" value="1" style="display: none;">
				
					<div class="form-group">
						<label>Email</label>
						<input placeholder="Email" name="email" class="form-control" type="email">
					</div>
		
					<div class="form-group">
						<label>Senha</label> 
						<input placeholder="Senha" name="senha" class="form-control" type="password">
					</div>
		
					<div class="form-group float-right">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<%@ include file="../includes/footer.jsp"%>