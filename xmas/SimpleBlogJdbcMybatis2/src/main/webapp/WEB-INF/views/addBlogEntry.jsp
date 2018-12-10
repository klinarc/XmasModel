<%--
/**
 * Add Entry Form.
 * 
 * <p>
 * IM430 Hypermedia Frameworks.<br>
 * <a href="http://www.fh-ooe.at/im">Interactive Media</a>.
 * </p>
 * 
 * @author Rimbert Rudisch-Sommer
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SimpleBlog Admin Add</title>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<h1>SimpleBlog Administration: Add</h1>

<form name="admin" action="admin" method="get">

<label for="contents">Contents:</label><br>
<textarea name="contents" cols="80" rows="10" id="contents"></textarea>
<br>

<input type=hidden name="cmd" value="">

<input type="hidden" name="token" value='${requestScope["token"]}'>

<input type="button" value="Add Blog Entry" onClick="admin.cmd.value='do-add';admin.submit()">
<input type="button" value="Abort" onClick="admin.cmd.value='menu';admin.submit()">

</form>
</body>
</html>
