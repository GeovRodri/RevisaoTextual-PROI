<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<section>


  		<form action="OrcamentoServlet" method="post" enctype="multipart/form-data">
			<input type="text" name="texto">
			<input type="file" name="file">
			<input type="submit" value="Upload">
		</form>

</section>

<%@ include file="../includes/footer.jsp"%>