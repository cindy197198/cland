<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/templates/taglib.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<div class="clearfix content">

	<div class="contact_us">

		<h1>Liên hệ với chúng tôi</h1>

		<p>
			TRUNG TÂM ĐÀO TẠO LẬP TRÌNH VINAENTER EDU<br /> Trụ sở: 154 Phạm Như
			Xương, Liên Chiểu, Đà Nẵng<br /> Web: <a
				href="http://vinaenter.edu.vn" title="">www.vinaenter.edu.vn</a>
		</p>
		<div>
			<p style="color: red; font-style: italic;">${msg}</p>
		</div>
		<form:errors cssStyle="color:red;font-style:italic;"
			path="objContact.*"></form:errors>
		<form action="${contextPath }/lien-he" method="post">
			<p>
				<input name="fullname" type="text" class="wpcf7-text"
					placeholder="Họ tên *" />
			</p>
			<p>
				<input name="email" type="text" class="wpcf7-email"
					placeholder="Email *" />
			</p>
			<p>
				<input name="subject" type="text" class="wpcf7-text"
					placeholder="Chủ đề *" />
			</p>
			<p>
				<textarea name="content" class="wpcf7-textarea"
					placeholder="Nội dung *"></textarea>
			</p>
			<p>
				<input type="Submit" class="wpcf7-submit" value="Gửi liên hệ" />
			</p>
		</form>

	</div>

</div>