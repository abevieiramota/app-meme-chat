<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Memechat</title>
</head>
<body>
	<form action="${s:mvcUrl('MC#generate').build()}" method="POST"
		enctype="multipart/form-data">
		<div>
			<label>Message</label>
			<textarea name="content">
			</textarea>
		</div>
		<button type="submit">Generate</button>
	</form>
</body>
</html>