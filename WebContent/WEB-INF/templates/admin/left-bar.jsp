<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<div class="sidebar content-box" style="display: block;">
                <!-- Nav-bar -->
				<ul class="nav">
				    <!-- Main menu -->
				    <li id="index"><a href="${contextPath }/admin"><i class="glyphicon glyphicon-home"></i> Trang chủ</a></li>
				    <li id="category"><a href="${contextPath }/admin/cat"><i class="glyphicon glyphicon-list"></i> Danh mục</a></li>
				    <li id="land"><a href="${contextPath }/admin/land"><i class="glyphicon glyphicon-book"></i> Tin tức</a></li>
				    <li id="user"><a href="${contextPath }/admin/user"><i class="glyphicon glyphicon-user"></i> Người dùng</a></li>
				    <li id="contact"><a href="${contextPath }/admin/contact"><i class="glyphicon glyphicon-plus"></i> Liên hệ</a></li>
				    <li class="submenu">
				         <a href="#">
				            <i class="glyphicon glyphicon-list"></i> Pages
				            <span class="caret pull-right"></span>
				         </a>
				         <!-- Sub menu -->
				         <ul>
				            <li><a href="login.html">Login</a></li>
				            <li><a href="javascript:void(0)">Signup</a></li>
				        </ul>
				    </li>
				</ul>
				<!-- /.nav-bar -->
             </div>