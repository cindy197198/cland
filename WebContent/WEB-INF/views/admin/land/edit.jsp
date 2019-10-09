<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<div class="row">
	<div class="col-md-12 panel-info">
		<div class="content-box-header panel-heading">
			<div class="panel-title ">Sửa tin tức</div>
		</div>
		<div class="content-box-large box-with-header">
			<div>
				<div class="row mb-10"></div>
				<form action="${contextPath }/admin/land/edit/${objLand.lid}"
					method="POST" enctype="multipart/form-data">
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="name">Tên tin</label> <input type="text"
									class="form-control" value="${objLand.lname }" name="lname">
								<form:errors cssStyle="color:red;font-weight:bold;"
									path="objLand.lname"></form:errors>
							</div>

							<div class="form-group">
								<label>Danh mục tin</label> <select class="form-control"
									name="cat.cid">
									<option value="">Chọn danh mục</option>
									<c:forEach items="${listCat }" var="objCat">
										<option value="${objCat.cid }">${objCat.cname }</option>
									</c:forEach>
								</select>
								<form:errors cssStyle="color:red;font-weight:bold;"
									path="objLand.cat.cid"></form:errors>
							</div>

							<div class="form-group">
								<label>Thêm hình ảnh</label> <input type="file"
									class="btn btn-default" id="exampleInputFile1"
									name="pictureReview">
								<p class="help-block">
									<em>Định dạng: jpg, png, jpeg,...</em>
								</p>
								<div>
									<p style="color: red; font-style: italic;">${msg}</p>
								</div>
							</div>
							<div class="form-group">
								<label for="name">Diện tích</label> <input type="text"
									class="form-control" value="${objLand.area }" name="area">
								<form:errors cssStyle="color:red;font-weight:bold;"
									path="objLand.area"></form:errors>
							</div>
							<div class="form-group">
								<label for="name">Địa chỉ</label> <input type="text"
									class="form-control" value="${objLand.address }" name="address">
								<form:errors cssStyle="color:red;font-weight:bold;"
									path="objLand.address"></form:errors>
							</div>

						</div>

						<div class="col-sm-6"></div>

						<div class="col-sm-12">
							<div class="form-group">
								<label>Mô tả</label>
								<textarea class="form-control" rows="7" name="description"
									id="description">${objLand.description }</textarea>
								<form:errors cssStyle="color:red;font-weight:bold;"
									path="objLand.description"></form:errors>
								<script>
									CKEDITOR.replace('description');
								</script>
							</div>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Sửa" class="btn btn-success" /> <input
								type="reset" value="Nhập lại" class="btn btn-default" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- /.row col-size -->
<script>
	document.getElementById("land").classList.add('current');
</script>