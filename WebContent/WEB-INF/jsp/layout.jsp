<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<link type="text/css" rel="stylesheet" href="css/main.css" />
</head>
<body>
<table border="1" cellpadding="0" cellspacing="0" align="center" width="100%">
    <tr>
        <td><tiles:insertAttribute name="header" />
        </td>
    </tr>
    <tr>
        <td colspan="2" height="700px"><tiles:insertAttribute name="body" /></td>
    </tr>
</table>
</body>
</html>