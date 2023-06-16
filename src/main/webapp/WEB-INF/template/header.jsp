<!-- header.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link type="text/css" rel="stylesheet" href="/web/css/header.css" />

<div class="header">
    <div class="menu-wrap">
        <div class="logo">
            <a href="/">
                <img src="web/images/test_img.png" style="width: 5rem; height: 50px" alt="Main Logo"></a>
        </div>
        <c:choose>
            <c:when test="${sessionScope.sMemberId ne null}">
                <a href="/logout" class="menu">로그아웃</a>
            </c:when>
            <c:otherwise>
                <div class="login_links">
                    <a href="/login" class="menu">로그인</a>
                    <a href="/sign_up" class="menu">회원가입</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
