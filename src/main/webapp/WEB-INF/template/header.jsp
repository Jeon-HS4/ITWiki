<!-- header.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link type="text/css" rel="stylesheet" href="/web/css/header.css" />

<div class="header">
    <div class="menu-wrap">
        <div class="logo">
            <a href="/">
                <img src="/web/images/ITWikiLogo.png" style="width: 400px; height: 56px" alt="Main Logo"></a>
        </div>
        <c:choose>
            <c:when test="${sessionScope.sUserId ne null}">
                <c:choose>
                    <c:when test="${sessionScope.sRole != 3}">
                        <a href="/board/list/admin" class="menu" style="color: red">관리자 페이지</a>
                        <a href="/mypage" class="menu"><strong>${sessionScope.sUsername}</strong>님 안녕하세요.</a>
                        <a href="/logout" class="menu">로그아웃</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/mypage" class="menu"><strong>${sessionScope.sUsername}</strong>님 안녕하세요.</a>
                        <a href="/logout" class="menu">로그아웃</a>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <div class="login_links">
                    <a href="/login" class="menu">로그인</a>
                    <a href="/signUp" class="menu">회원가입</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
