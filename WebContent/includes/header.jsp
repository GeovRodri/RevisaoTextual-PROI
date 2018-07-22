<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="pt">
<head>
	<meta charset="utf-8">
	<title>LGPR Soluções</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<meta content="" name="keywords">
	<meta content="" name="description">
	
	<!-- Favicons -->
	<link href="<%=request.getContextPath()%>/img/favicon.png" rel="icon">
	<link href="<%=request.getContextPath()%>/img/apple-touch-icon.png" rel="apple-touch-icon">
	
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Raleway:300,400,500,700,800|Montserrat:300,400,700" rel="stylesheet">
	<!-- Bootstrap CSS File -->
	<link href="<%=request.getContextPath()%>/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Libraries CSS Files -->
	<link href="<%=request.getContextPath()%>/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/lib/animate/animate.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/lib/ionicons/css/ionicons.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/lib/magnific-popup/magnific-popup.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/lib/ionicons/css/ionicons.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/lib/jquery/jquery.min.js"></script>
</head>

<body id="body">

	<section id="topbar" class="d-none d-lg-block">
		<div class="container clearfix">
			<div class="social-links float-right">
				<a href="#">Login</a>
			</div>
		</div>
	</section>

	<header id="header">
		<div class="container">

			<div id="logo" class="pull-left">
				<h1> <a href="<%=request.getContextPath()%>/index.jsp" class="scrollto">LGPR <span>Soluções</span> </a> </h1>
			</div>

			<nav id="nav-menu-container">
				<ul class="nav-menu">

					<c:choose>
      					<c:when test="${fn:containsIgnoreCase(pageContext.request.requestURI, 'index.jsp')}">
							<li class="menu-active"> <a href="javascript:void(0)">Página Inicial</a> </li>			
						</c:when>
      					<c:otherwise>
							<li> <a href="<%=request.getContextPath()%>/index.jsp">Página Inicial</a> </li>
						</c:otherwise>
   					</c:choose>
				
					<c:choose>
      					<c:when test="${fn:containsIgnoreCase(pageContext.request.requestURI, 'sobre-nos.jsp')}">
							<li class="menu-active"> <a href="javascript:void(0)">Sobre nós</a> </li>			
						</c:when>
      					<c:otherwise>
							<li> <a href="<%=request.getContextPath()%>/sobre-nos.jsp">Sobre nós</a> </li>
						</c:otherwise>
   					</c:choose>
   					
   					<c:choose>
      					<c:when test="${fn:containsIgnoreCase(pageContext.request.requestURI, 'servicos.jsp')}">
							<li class="menu-active"> <a href="javascript:void(0)">Serviços</a> </li>			
						</c:when>
      					<c:otherwise>
							<li> <a href="<%=request.getContextPath()%>/servicos.jsp">Serviços</a> </li>
						</c:otherwise>
   					</c:choose>
   					
   					<c:choose>
      					<c:when test="${fn:containsIgnoreCase(pageContext.request.requestURI, 'contato.jsp')}">
							<li class="menu-active"> <a href="javascript:void(0)">Contato</a> </li>			
						</c:when>
      					<c:otherwise>
							<li> <a href="<%=request.getContextPath()%>/contato.jsp">Contato</a> </li>
						</c:otherwise>
   					</c:choose>
				</ul>
			</nav>
		</div>
	</header>