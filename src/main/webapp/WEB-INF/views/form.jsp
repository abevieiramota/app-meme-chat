<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Memechat</title>
</head>
<body>
	<div>${message}</div>
	<form action="/meme" method="POST" enctype="multipart/form-data">
		<div>
			<label>Imagem</label>
			<input type="file" name="image" />
		</div>
		<button type="submit">Save</button>
	</form>
</body>
</html>