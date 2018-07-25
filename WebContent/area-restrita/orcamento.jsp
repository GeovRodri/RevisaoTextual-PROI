<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp"%>

<section>
	<div class="container">
		<div class="section-header">
			<h2>Orçamento</h2>
		</div>													
	
		<div class="form">

			<form action="requisitar" method="post">
				
				<div class="form-row">	
					<c:choose>
						<c:when test="${valorPorPagina != null}">
							<div class="form-check form-check-inline">
							  	<input class="form-check-input" type="radio" name="valor" id="radioPorPagina" value="${valorPorPagina}" ${cobrancaSelecionada.equals("0") ? 'checked' : ''}>
							  	<label class="form-check-label" for="radioPorPagina">Por pagina <fmt:formatNumber value="${valorPorPagina}" type="currency"/></label>
							</div>
						</c:when>
					</c:choose>
					<c:choose>
				 		<c:when test="${valorPorLauda != null}">
							<div class="form-check form-check-inline">
							  	<input class="form-check-input" type="radio" name="valor" id="radioPorLauda" value="${valorPorLauda}" ${cobrancaSelecionada.equals("1") ? 'checked' : ''}>
							  	<label class="form-check-label" for="radioPorLauda">Por lauda <fmt:formatNumber value="${valorPorLauda}" type="currency"/></label>
							</div>
						</c:when>
					</c:choose>
						
					<c:choose>	
						<c:when test="${valorPorPalavra != null}">
							<div class="form-check form-check-inline">
							  	<input class="form-check-input" type="radio" name="valor" id="radioPorPalavra" value="${valorPorPalavra}" ${cobrancaSelecionada.equals("2") ? 'checked' : ''}>
							  	<label class="form-check-label" for="radioPorPalavra">Por palavra <fmt:formatNumber value="${valorPorPalavra}" type="currency"/></label>
							</div>
						</c:when>
					</c:choose>
				</div>
				
				<div class="float-right">
					<button type="submit" class="btn btn-primary">Requisitar Serviço</button>
				</div>
			</form>
		</div>
	</div>
	
</section>

<%@ include file="../includes/footer.jsp"%>