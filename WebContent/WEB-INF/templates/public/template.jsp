<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<title>CLand | VinaEnter Edu</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!--Oswald Font -->
		<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css' />
		<link rel="stylesheet" type="text/css" href="${contextPath}/resources/public/css/tooltipster.css" />
		<!-- home slider-->
		<link href="${contextPath}/resources/public/css/pgwslider.css" rel="stylesheet" />
		<!-- Font Awesome -->
		<link rel="stylesheet" href="${contextPath}/resources/public/css/font-awesome.min.css" />
		<link href="${contextPath}/resources/public/css/style.css" rel="stylesheet" media="screen" />	
		<link href="${contextPath}/resources/public/css/responsive.css" rel="stylesheet" media="screen" />		
		<tiles:insertAttribute name="css" />
	</head>

	<body>
		<tiles:insertAttribute name="header" />
		
		<section id="content_area">
			<div class="clearfix wrapper main_content_area">
			
				<div class="clearfix main_content floatleft">
					<tiles:insertAttribute name="content" />
				</div>
				<div class="clearfix sidebar_container floatright">
					<tiles:insertAttribute name="right-bar" />
				</div>
				</div>
			</div>
		</section>
		<tiles:insertAttribute name="footer" />
		
		<script type="text/javascript" src="${contextPath}/resources/public/js/jquery-1.7.0.min.js"></script>	
		<script type="text/javascript" src="${contextPath}/resources/public/js/jquery.tooltipster.min.js"></script>		
		<script type="text/javascript">
			$(document).ready(function() {
				$('.tooltip').tooltipster();
			});
		</script>
		 <script type="text/javascript" src="${contextPath}/resources/public/js/selectnav.min.js"></script>
		<script type="text/javascript">
			selectnav('nav', {
			  label: '-Navigation-',
			  nested: true,
			  indent: '-'
			});
		</script>		
		<script src="${contextPath}/resources/public/js/pgwslider.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('.pgwSlider').pgwSlider({
					
					intervalDuration: 5000
				
				});
			});
		</script>
		<script type="text/javascript" src="${contextPath}/resources/public/js/placeholder_support_IE.js"></script>
		<tiles:insertAttribute name="js" />
	</body>
</html>

		
