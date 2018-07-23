<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="includes/header.jsp"%>

<section id="intro">

	<div class="intro-content">
		<h2>Faça sua revisão textual aqui!</h2>
		<div>
			<c:choose>
   				<c:when test="${userSession != null}">
   					<a href="area-restrita/area-cliente.jsp" class="btn-get-started scrollto">Realizar agora</a>
				</c:when>
    			<c:otherwise>
					<a href="javascript:void(0)" onclick="openPopoverLogin()" class="btn-get-started scrollto">Realizar agora</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div id="intro-carousel" class="owl-carousel">
		<div class="item" style="background-image: url('img/intro-carousel/1.jpg');"></div>
		<div class="item" style="background-image: url('img/intro-carousel/2.jpg');"></div>
		<div class="item" style="background-image: url('img/intro-carousel/3.jpg');"></div>
		<div class="item" style="background-image: url('img/intro-carousel/4.jpg');"></div>
		<div class="item" style="background-image: url('img/intro-carousel/5.jpg');"></div>
	</div>
</section>

<%@ include file="includes/footer.jsp"%>