<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp"%>

<section>
	<div class="container">
		<div class="section-header">
			<h2>Orçamento</h2>
		</div>													
	
		<div class="col-sm-12">
			<c:choose>
				<c:when test="${valorPorPagina != null}">
					<div class="form-check form-check-inline">
					  	<input class="form-check-input" type="radio" name="cobranca" id="radioPorPagina" value="${valorPorPagina}">
					  	<label class="form-check-label" for="radioPorPagina">Por pagina <fmt:formatNumber value="${valorPorPagina}" type="currency"/></label>
					</div>
				</c:when>
			</c:choose>
			<c:choose>
		 		<c:when test="${valorPorLauda != null}">
					<div class="form-check form-check-inline">
					  	<input class="form-check-input" type="radio" name="cobranca" id="radioPorLauda" value="${valorPorLauda}">
					  	<label class="form-check-label" for="radioPorLauda">Por lauda <fmt:formatNumber value="${valorPorLauda}" type="currency"/></label>
					</div>
				</c:when>
			</c:choose>
				
			<c:choose>	
				<c:when test="${valorPorPalavra != null}">
					<div class="form-check form-check-inline">
					  	<input class="form-check-input" type="radio" name="cobranca" id="radioPorPalavra" value="${valorPorPalavra}">
					  	<label class="form-check-label" for="radioPorPalavra">Por palavra <fmt:formatNumber value="${valorPorPalavra}" type="currency"/></label>
					</div>
				</c:when>
			</c:choose>
		</div>
		
		<div class="col-sm-12">
			<div class="float-right">
				<a type="button" class="btn btn-primary" href="pagamento.jsp">Requisitar Serviço</a>
			</div>
		</div>
	</div>
	
</section>

<%@ include file="../includes/footer.jsp"%>