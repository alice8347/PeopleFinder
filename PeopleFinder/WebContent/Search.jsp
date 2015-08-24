<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>People Finder</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
<h3>Find Customers Information (Case Sensitive)</h3>
<br />
<form class="form-horizontal" role="form" action="Finder" method="post">
<div class="form-group">
<label class="control-label col-sm-3" for="lastName">Last name / company name:</label>
<div class="col-sm-9">
<input type="text" class="form-control" name="lastName" id="lastName" placeholder="Enter last name">
</div>
</div>

<div class="form-group"> 
<div class="col-sm-offset-3 col-sm-9">
<button type="submit" class="btn btn-default">Submit</button>
</div>
</div>
</form>
</div>
${message}
</body>
</html>