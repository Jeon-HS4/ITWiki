<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

		<form action="/board/list/admin" method="post" style="float: right; margin-bottom: 30px">
			<select name="category" style="width: 100px;">
				<option value="category">전체보기</option>
				<c:forEach items="${categoryList}" var="category">
					<option value="${category}">${category}</option>
				</c:forEach>
			</select>
			<select name="sorting">
				<option value="viewCount desc">조회순</option>
				<option value="title">이름순</option>
				<option value="pageUpdate desc">최종수정일순</option>
			</select>
			<input type="text" name="keyword" value="" placeholder="관리자 게시판입니다.">
			<button type="submit" class="button">검색</button>
		</form>
			<p style="font-size: smaller; padding-top: 20px">총 ${totalCount}건의 게시글이 검색되었습니다.</p>
		<table class="form-table">
			<thead>
				<tr>
					<th style="width: 50px">번호</th>
					<th style="width: 100px">페이지 번호</th>
					<th style="width: 200px">요청 일시</th>
					<th>내용</th>
					<th style="width: 100px">확인</th>
					<th style="width: 100px">삭제</th>

				</tr>
			</thead>
			<tbody>
				<c:if test="${empty boardList}">
					<tr>
						<td colspan="6">게시물이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="revision" items="${boardList}">
					<tr>
						<td>${revision.revisionId}</td>
						<td>${revision.pageId}</td>
						<td>${revision.pageUpdate}</td>
						<td>
							<c:out value="${fn:substring(revision.content, 0, 20)}${fn:length(revision.content) > 20 ? '...' : ''}" />
						</td>
						<td><a class="button" href="/board/modify/admin?revisionId=${revision.revisionId}">확인</a></td>
						<td><a class="button" href="/board/delete/admin?revisionId=${revision.revisionId}">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<c:choose>
				<c:when test="${startUnitNum gt 1}">
					<c:set var="beforePage" value="${startUnitNum-1}" />
				</c:when>
				<c:otherwise>
					<c:set var="beforePage" value="1" />
				</c:otherwise>
			</c:choose>
			<a href="/board/list/admin?pageNum=${beforePage}">&lt;</a>
			
			<c:forEach var="page" begin="${startUnitNum}" end="${endUnitNum}">
				<a <c:if test="${page eq pageNum}">class="current"</c:if> href="/board/list/admin?pageNum=${page}">${page}</a>
			</c:forEach>
			
			<c:choose>
				<c:when test="${endUnitNum lt totalPagingNum}">
					<c:set var="afterPage" value="${endUnitNum+1}" />
				</c:when>
				<c:otherwise>
					<c:set var="afterPage" value="${endUnitNum}" />
				</c:otherwise>
			</c:choose>
			<a href="/board/list/admin?pageNum=${afterPage}">&gt;</a>
		</div>

		<%-- footer 영역 --%>
		<jsp:include page="../template/footer.jsp" />

	</div>
</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		var sUserId = '<%= session.getAttribute("sUserId") %>';
		var addBoardButton = document.getElementById('addBoardButton');

		if (sUserId === null) {
			addBoardButton.removeAttribute('href');
			addBoardButton.addEventListener('click', function(event) {
				event.preventDefault();
				alert("로그인 후 이용해주세요.");
			});
		}
	});
</script>

</html>