<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<section>
	<c:choose>
		<c:when test="${valorPorPagina != null}">
			<span>Valor por pagina: ${valorPorPagina}</span>
		</c:when>
	</c:choose>
	<c:choose>
 		<c:when test="${valorPorLauda != null}">
			<span>Valor por lauda: ${valorPorLauda}</span>
		</c:when>
	</c:choose>
		
	<c:choose>	
		<c:when test="${valorPorPalavra != null}">
			<span>Valor por palavra: ${valorPorPalavra}</span>
		</c:when>
	</c:choose>
	
</section>

<%@ include file="../includes/footer.jsp"%>