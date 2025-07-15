<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="common/head.jsp" %>
</head>
<body>
	<head>
		<div class="bg-primary p-2 " ><p class="text-white  fw-bold fs-3 text-center">채팅 연습</p></div>
	</head>
	<div class="container my-2">
		<textarea  id="chatBox" readonly class="form-control resize-none" rows="10"></textarea>
		<form id="inputForm" class="input-group mt-3">
			<input class="form-control" placeholder="메세지 작성"><button class="btn btn-primary">발송</button>
		</form>
	</div>
	<script>
		let socket;

			$(function () {
				
			socket = new WebSocket(`ws://\${location.host}${cp}/echo`)
			
			socket.onopen = () => console.log("서버에 접속됨");
			socket.onmessage = function(event) {
				$("#chatBox").val(function(i, text) {
					return text += event.data + "\n"
				})
			}
			socket.onerror = () => console.log("서버 접속 실패");

			$("#inputForm").submit (function(){
				event.preventDefault();
				socket.send($(this).find("input").val())
				$(this).find("input").val("");

				})
			})
			
	
		
	</script>
</body>
</html>