 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
alert('${msg}');
location.href = '${url}';
<%
	System.out.println(request.getAttribute("url"));
%>
</script>