<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록 페이지</title>

<link type="text/css" rel="stylesheet" href="/web/css/board/addBoard.css" />
</head>
<body>
	<div class="container">
		<%-- header 영역 --%>
		<jsp:include page="../template/header.jsp" />

		<p class="title">Spring boot로 만들어보는 게시판</p>
		<form action="/board/new" method="post">
			<table class="form-table">
				<tr>
					<th>제목<em class="red">*</em></th>
					<td colspan="3">
						<input type="text" class="input-normal" name="title" maxlength="100" placeholder="제목을 입력해주세요.">
					</td>
				</tr>
				<tr>
					<th>카테고리<em class="red">*</em></th>
					<td>
						<select name="category" style="width: 100px;">
							<option value="category">전체보기</option>
							<c:forEach items="${categoryList}" var="category">
								<option value="${category}">${category}</option>
							</c:forEach>
						</select>
					</td>
					<th>태그<em class="red">*</em></th>
					<td>
						<input type="text" class="input-normal" name="tag" maxlength="20" placeholder="20자 이하의 비밀번호를 입력해주세요.">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea class="textarea-normal" name="content" placeholder="내용을 입력해주세요."></textarea>
					</td>
				</tr>
				<input type="hidden" name="userName" value="${sessionScope.sUserId}">
			</table>
			<div class="button-area">
				<button type="submit">게시물 등록</button>
				<a class="button" href="/board/list">게시판 목록</a>
			</div>
			
		</form>

		<%-- footer 영역 --%>
		<jsp:include page="../template/footer.jsp" />

	</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		var sUserId = '${sessionScope.sUserId}';
		if (sUserId == null || sUserId === '') {
			alert("로그인 후 이용해주세요.");
			location.href = "/board/list";
		}
	});
</script>
</body>
</html>