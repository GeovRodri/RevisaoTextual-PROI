<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp"%>

<section>
	<div class="container">
		<div class="section-header">
			<h2>Pagamento</h2>
		</div>													
		
		<div class="row">
			<div class="col-sm-12">
				<span>Valor a ser pago: <fmt:formatNumber value="${valor}" type="currency"/></span>
			</div>
		</div>
		
		<div class="float-right">
			<a type="button" class="btn btn-primary" href="gerar-boleto">Realizar Pagamento via Boleto</a>
		</div>
	</div>
	
</section>

<%@ include file="../includes/footer.jsp"%>