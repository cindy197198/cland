<%@page import="vn.edu.vinaenter.defines.FileDefine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<div class="content-box-large">
	<div class="row">
		<div class="panel-heading">
			<div class="panel-title ">Quản lý tin</div>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-8">
			<a href="${contextPath }/admin/land/add" class="btn btn-success"><span
				class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Thêm</a>
		</div>
		<div class="col-md-4">
			<div class="input-group form">
				<input type="text" class="form-control" placeholder="Search...">
				<span class="input-group-btn">
					<button class="btn btn-primary" type="button">Search</button>
				</span>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="panel-body">
		<div>
			<p style="color: red; font-style: italic;">${msg}</p>
		</div>
			<table cellpadding="0" cellspacing="0" border="0"
				class="table table-striped table-bordered" id="example">
				<thead>
					<tr>
						<th>ID</th>
						<th width="20%">Tên</th>
						<th>Ngày đăng</th>
						<th>Danh mục</th>
						<th>Hình ảnh</th>
						<th>Diện tích</th>
						<th width="15%">Địa chỉ</th>
						<th>Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listLand }" var="objLand">
						<c:set var="urlEdit"
							value="${contextPath}/admin/land/edit/${objLand.lid }"></c:set>
						<c:set var="urlDel"
							value="${contextPath}/admin/land/del/${objLand.lid }"></c:set>
						<tr class="odd gradeX">
							<td>${objLand.lid }</td>
							<td width="20%">${objLand.lname }</td>
							<td>${objLand.date_create }</td>
							<td>${objLand.cat.cname }</td>
							<td class="center text-center"><c:choose>
									<c:when test="${not empty objLand.picture }">
										<img width="150px"
											src="${contextPath}/fileUpload/${objLand.picture}" />
									</c:when>
									<c:otherwise>
										<p>Không có hình ảnh</p>
									</c:otherwise>
								</c:choose></td>
							<td>${objLand.area }</td>
							<td class="center" width="15%">${objLand.address }</td>

							<td class="center text-center"><a href="${urlEdit }"
								title="" class="btn btn-primary"><span
									class="glyphicon glyphicon-pencil "></span> Sửa</a> <a
								href="${urlDel }" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc muốn xoá?')"><span
									class="glyphicon glyphicon-trash"></span> Xoá</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- Pagination -->
			<nav class="text-center" aria-label="...">
				<ul class="pagination">
					<c:choose>
							<c:when test="${page==1}">
								<c:set var="previous" value="disabled"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="previous" value=""></c:set>
							</c:otherwise>
					</c:choose>
					<li class="${previous}"><a href="${contextPath}/admin/land/${page-1}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
					<c:forEach begin="1" end="${sumPage }" var="i">
						<c:choose>
							<c:when test="${i== page}">
								<c:set var="active" value="active"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="active" value=""></c:set>
							</c:otherwise>
						</c:choose>
						<li class="${active }"><a
							href="${contextPath}/admin/land/${i}">${i}</a></li>
					</c:forEach>
					<c:choose>
							<c:when test="${page==sumPage}">
								<c:set var="next" value="disabled"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="next" value=""></c:set>
							</c:otherwise>
					</c:choose>
					<li class="${next }"><a href="${contextPath}/admin/land/${page+1}" aria-label="Next"><span aria-hidden="true">»</span></a></li>					
				</ul>
			</nav>
			<!-- /.pagination -->

		</div>
	</div>
	<!-- /.row -->
</div>
<!-- /.content-box-large -->

<script>
	document.getElementById("land").classList.add('current');
</script>