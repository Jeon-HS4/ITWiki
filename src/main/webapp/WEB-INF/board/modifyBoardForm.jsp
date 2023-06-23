<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정 페이지</title>

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

table.form-table th em.red {
	margin-left: 3px;
	color: red;
}

table.form-table td {
    padding: 2px 5px 3px 5px;
}

input.input-normal {
    padding: 2px 4px;
    width: 90%;
}

textarea.textarea-normal {
    resize: none;
    width: 97%;
    height: 400px;
    margin: 3px 0;
    padding: 5px;
}

div.button-area {
	text-align: right;
}

div.button-area button, div.button-area a.button {
	font-size: 13px;
    border: 1px solid #000000;
    border-radius: 3px;
    padding: 3px 7px;
    text-decoration: none;
    color: #000000;
    background: #f9f9f9;
    cursor: pointer;
}

div.button-area button:hover, div.button-area a.button:hover {
    font-weight: bold;
    background: #e7e7e7;
}

</style>

</head>
<body>
	<div class="container">
		<%-- header 영역 --%>
		<jsp:include page="../template/header.jsp" />
		<p class="title">Wiki 수정하기</p>
		<form action="/board/modify" method="post">
			<input type="hidden" id="pageId" name="pageId" value="${board.pageId}" />
		
			<table class="form-table">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" class="input-normal" name="title" maxlength="100" value="${board.title}" placeholder="제목을 입력해주세요.">
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						<input type="text" class="input-normal" name="category" readonly maxlength="50" value="${board.category}">
					</td>
				</tr>
				<tr>
					<th>태그</th>
					<td>
						<input type="text" class="input-normal" name="category" readonly maxlength="50" value="${board.tag}">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea class="textarea-normal" name="content" placeholder="내용을 입력해주세요.">${board.content}</textarea>
					</td>
				</tr>
			</table>
			<div class="button-area">
				<button type="submit">게시물 수정</button>
				<a class="button" href="/board/list">게시판 목록</a>
			</div>
			
		</form>
			<%-- footer 영역 --%>
			<jsp:include page="../template/footer.jsp" />
	</div>
</body>
</html>