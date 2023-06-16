<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록 페이지</title>
	<jsp:include page="../template/script.jsp" />
	<link type="text/css" rel="stylesheet" href="/web/css/board/common.css" />
</head>
<body>
	<div class="container">
		<%-- header 영역 --%>
		<jsp:include page="../template/header.jsp" />

		<p class="title">Spring boot로 만들어보는 게시판</p>
		
		<table class="form-table">
			<thead>
				<tr>
					<th style="width: 50px">번호</th>
					<th style="width: 100px">분류</th>
					<th style="width: 200px">태그</th>
					<th>제목</th>
					<th style="width: 80px">조회수</th>
					<th style="width: 140px">수정일시</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td>${board.boardNo}</td>
						<td>${board.category}</td>
						<td>${board.tag}</td>
						<td style="text-align: left;">
							<a href="/board/detail?boardSeq=${board.pageId}">${board.title}</a>
						</td>
						<td>${board.viewCount}</td>
						<td>${board.pageUpdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="button-area">
			<a class="button" href="/board/form">게시물 등록</a>
		</div>
		<div class="paging">
			<c:choose>
				<c:when test="${startUnitNum gt 1}">
					<c:set var="beforePage" value="${startUnitNum-1}" />
				</c:when>
				<c:otherwise>
					<c:set var="beforePage" value="1" />
				</c:otherwise>
			</c:choose>
			<a href="/board/list?pageNum=${beforePage}">&lt;</a>
			
			<c:forEach var="page" begin="${startUnitNum}" end="${endUnitNum}">
				<a <c:if test="${page eq pageNum}">class="current"</c:if> href="/board/list?pageNum=${page}">${page}</a>
			</c:forEach>
			
			<c:choose>
				<c:when test="${endUnitNum lt totalPagingNum}">
					<c:set var="afterPage" value="${endUnitNum+1}" />
				</c:when>
				<c:otherwise>
					<c:set var="afterPage" value="${endUnitNum}" />
				</c:otherwise>
			</c:choose>
			<a href="/board/list?pageNum=${afterPage}">&gt;</a>	
		</div>

		<%-- footer 영역 --%>
		<jsp:include page="../template/footer.jsp" />

	</div>
</body>
</html>