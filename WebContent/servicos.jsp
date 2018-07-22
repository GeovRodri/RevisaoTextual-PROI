<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="includes/header.jsp"%>

<section id="services">
	<div class="container">
        <div class="section-header">
          <h2>Serviços</h2>
          <p>Oferecemos os seguintes serviços: </p>
        </div>

        <div class="row" id="list-servicos"> </div>
	</div>
</section>

<script>
  	function getServicos() {
	    $.get('servico', function(data) {
	    	document.getElementById("list-servicos").innerHTML = data;
	    });
    }
  		
  	getServicos();
</script>

<%@ include file="includes/footer.jsp"%>