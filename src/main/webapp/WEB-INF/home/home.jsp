<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>

<link type="text/css" rel="stylesheet" href="/web/css/common.css" />
</head>
<body>

<div class="container">
	<%-- header 영역 --%>
	<jsp:include page="../template/header.jsp" />
	
	<%-- 페이지 내용 영역 --%>
	<div class="contents">
		<div class="dummy" style="margin-bottom: 10px; padding: 50px 0;"><a href="/board/list">BANNER</a></div>
		<div class="dummy" style="padding: 200px 0;">CONTENTS</div>
	</div>

	<%-- footer 영역 --%>
	<jsp:include page="../template/footer.jsp" />

</div>

</body>
</html>