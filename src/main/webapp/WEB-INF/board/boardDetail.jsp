<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세 페이지</title>

<style type="text/css">
div.container {
	padding: 0 10px;
}

p.title {
	border: 1px solid #000000;
    padding: 20px 10px;
    text-align: center;
    font-weight: bold;
    background: #f9f9f9;
}

table.form-table {
	width: 100%;
    border: 1px solid #000000;
    border-collapse: collapse;
    margin-bottom: 10px;
}

table.form-table th,table.form-table td {
	border: 1px solid #000000;
}

table.form-table th {
	width: 25%;
}

table.form-table td {
    padding: 2px 5px 3px 5px;
}

table.form-table td.long-text {
	white-space: pre-line;
}

div.button-area {
	text-align: right;
}

div.button-area a.button {
	font-size: 13px;
    border: 1px solid #000000;
    border-radius: 3px;
    padding: 3px 7px;
    text-decoration: none;
    color: #000000;
    background: #f9f9f9;
    cursor: pointer;
}

div.button-area a.button:hover {
    font-weight: bold;
    background: #e7e7e7;
}

div.check-wrap {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgb(0,0,0,.5);
    display: none;
}

div.check-wrap div.check-form {
    margin: 140px 20px 20px 20px;
    background: #FFFFFF;
    padding: 30px;
}

</style>

</head>
<body>
	<div class="container">
		<%-- header 영역 --%>
		<jsp:include page="../template/header.jsp" />
		<p class="title">Wiki 상세보기</p>

		<table class="form-table">
			<tr>
				<th>제목</th>
				<td>${board.title}</td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>${board.category}</td>
			</tr>
			<tr>
				<th>태그</th>
				<td>${board.tag}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td class="long-text">${board.content}</td>
			</tr>
		</table>
		<div class="button-area">
			<a class="button" id="vote" href="/board/vote">즐겨찾기</a>
			<a class="button" href="/board/modifyForm?pageId=${board.pageId}">수정</a>
			<a class="button" href="/board/list">게시판 목록</a>
		</div>
	</div>
	<%-- footer 영역 --%>
	<jsp:include page="../template/footer.jsp" />
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var voteButton = document.getElementById("vote");
			voteButton.removeAttribute('href');
			voteButton.addEventListener('click', function(event) {
				event.preventDefault();
				alert("추가 예정인 기능입니다.");
			});
		});
	</script>
</body>
</html>