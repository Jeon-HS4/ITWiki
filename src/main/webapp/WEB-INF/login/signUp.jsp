<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>

<c:if test="${sessionScope.sMemberId ne null}">

	<script type="text/javascript">
		alert("잘못된 접근입니다.");
		location.href = "/";
	</script>

</c:if>

<head>
<meta charset="UTF-8">
<title>메인 페이지</title>

<jsp:include page="../template/script.jsp" />
<link type="text/css" rel="stylesheet" href="/web/css/common/login/signup.css" />

</head>
<body>

<div class="container">
	<%-- 헤더 영역 --%>
		<jsp:include page="../template/header.jsp" />
	
	<%-- 페이지 내용 영역 --%>
	<div class="contents">
		<div class="signup-wrap">
			<div class="signup">
				<form id="signup_form" method="post">
					<div>
						<span class="input_info">아이디 </span>
						<input type="text" id="id_input" class="required" name="userId" placeholder="아이디를 입력해주세요" title="아이디" />
						<div class="alarm">아이디를 입력해주세요.</div>
					</div>
					<div>
						<span class="input_info">비밀번호 </span>
						<input type="password" id="pwd_input" class="required" name="password" placeholder="비밀번호를 입력해주세요" title="비밀번호" />
						<div class="alarm">비밀번호를 입력해주세요.</div>
					</div>
					<div>
						<span class="input_info">비밀번호 확인 </span>
						<input type="password" id="pwd_check" class="required" name="passwordCheck" placeholder="비밀번호를 다시 입력해주세요" title="비밀번호확인" />
						<div class="alarm">비밀번호가 맞지 않습니다.</div>
					</div>
					<div>
						<span class="input_info">사용자이름 </span>
						<input type="text" id="name_input" class="required" name="username" placeholder="사용할 이름을 입력해주세요" title="이름" />
						<div class="alarm">사용자이름을 입력해주세요.</div>
					</div>
					<div>
						<span class="input_info">이메일 </span>
						<input type="text" id="email_input" class="required" name="email" placeholder="이메일을 입력해주세요" title="이메일" />
						<div class="alarm">이메일을 입력해주세요.</div>
					</div>
				</form>
				
				<a href="/signUp" id="btn_signup" class="signup-button">회원가입</a>
			</div>
			<div class="sub-menu">
				<a href="/login" class="menu">로그인하기</a>
				<a href="/" class="menu">메인화면</a>
			</div>
		</div>
	</div>
	<%-- footer 영역 --%>
	<jsp:include page="../template/footer.jsp" />
</div>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	<%-- 엔터키 - 로그인 버튼 클릭 처리 --%>
	$('#signup_form input').on('keyup',function (e){
		if(e.keyCode == 13){
			e.preventDefault();

			if($(this).attr('id') == 'pwd_input'){
				$('#btn_signup').click();
			}

		}
	});

	<%-- 로그인 버튼 클릭 --%>
	$('#btn_signup').on('click',function (){
		$('#btn_signup').on('click', function() {
			<%-- form validation --%>
			var validTF = true;
			$('#signup_form .required').each(function () {
				if ($.trim($(this).val()) == '') {
					alert($(this).attr('title') + ' 항목은 필수값입니다.');
					$(this).siblings('div.alarm').show();
					validTF = false;
					return false;
				} else {
					$(this).siblings('div.alarm').hide();
				}
			});
			if (!validTF) {
				return false;
			}
		});
		$.ajax({
			url: '/signup',
			type: 'POST',
			data: $('#signup_form').serialize(),
			success: function (data){
				var result_cd = data.result_cd;
				if(result_cd == '00'){
					alert("회원가입이 완료되었습니다!");
					location.href = '/';
				}else{
					var result_msg = data.result_msg;
					alert(result_msg);
				}
			},
			error: function (error){
				alert(error);
				console.log(error);
			}
		});
	});
});


</script>
</body>
</html>