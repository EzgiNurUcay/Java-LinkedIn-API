<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Bootstrap 4 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
  
<title>LinkedInConnection</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-light navbar-light"> <a
		class="navbar-brand" href="#"> <img src="yourlogo"
		alt="Logo" style="width: 90px;">
	</a>
	<ul class="navbar-nav">
		<li class="nav-item active"><a class="nav-link" href="#">Anasayfa</a>
		</li>
		<li class="nav-item"><a class="nav-link"
			href="#">Son Eklenenler</a></li>
		<li class="nav-item"><a class="nav-link" href="#">İletişim</a></li>
		</li>
	</ul>

	<div class="col-9 d-flex justify-content-end">
		<form class="form-inline" action="loginOperations" method="post">
			<div class="input-group">
				<button class="btn btn-outline-primary" type="submit"
					value="linkedinButton" name="buttonLinked">LinkedIn</button>
				<button class="btn btn-outline-success" type="submit"
					value="anotherButton" name="buttonAnother">Another Login</button>
			</div>
		</form>
	</div>
	</nav>
</body>
</html>
