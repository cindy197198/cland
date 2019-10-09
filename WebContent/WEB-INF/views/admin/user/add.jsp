<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<div class="row">
	<form action="${contextPath }/admin/user/add" method="post">
		<div class="col-md-12 panel-info">
			<div class="content-box-header panel-heading">
				<div class="panel-title ">Thêm người dùng</div>
			</div>
			<div class="content-box-large box-with-header">
				<div>
					<div class="row mb-10">
						<form:errors cssStyle="color:red;font-weight:bold;"
							path="objUser.*"></form:errors>
						<p style="color: red; font-style: italic;">${msg}</p>
					</div>

					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="name">Tên đăng nhập</label> <input type="text"
									name="username" class="form-control"
									placeholder="Nhập tên đăng nhập">
							</div>
							<div class="form-group">
								<label for="name">Họ tên</label> <input type="text" name="fullname"
									class="form-control" placeholder="Nhập họ tên">
							</div>
							<div class="form-group">
								<label for="name">Mật khẩu</label> <input type="text"
									name="password" class="form-control" placeholder="Nhập mật khẩu">
							</div>
						</div>

					</div>

					<hr>

					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Thêm" class="btn btn-success" /> <input
								type="reset" value="Nhập lại" class="btn btn-default" />
						</div>
					</div>

				</div>
			</div>
		</div>
	</form>
</div>
<!-- /.row col-size -->
<script>
	document.getElementById("user").classList.add('current');
</script>