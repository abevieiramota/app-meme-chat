<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="/resources/img/favicon.ico" type="image/x-icon">
<link rel="icon" href="/resources/img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Memechat</title>
</head>
<body>
	<form action="/meme/render"
		method="GET" enctype="multipart/form-data">
		<div>
			<label>Message</label>
			<textarea name="content" cols="40" rows="10">
			</textarea>
		</div>
		<div>
			<label>Meme</label>
			<select name="id">
				<c:forEach items="${memes}" var="meme">
					<option value="${meme.id}">${meme.filename}</option>
				</c:forEach>
			</select>
		</div>
		<button type="submit">Generate</button>
	</form>
</body>
</html>