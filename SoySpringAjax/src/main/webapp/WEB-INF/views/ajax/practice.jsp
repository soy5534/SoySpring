<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<title>Insert title here</title>
</head>
	<body>
		<h3>Ajax 구현(jQuery)</h3>
		<h4>Ajax(jQuery)로 서버에 전송값 보내기</h4>
		<p>버튼 클릭시 전송값을 서버에서 출력</p>
		<input type="text" id="msg-2">
		<button type="button" onclick="jQueryFuncGet();">보내기(JavaScript)</button>
		
		<h3>버튼 클릭시 서버에서 보낸 값 수신</h3>	
		
		<script>
			function jQueryFuncGet(){
				var msg = $("#msg-2").val(); 	// html문서 내에 id가 msg-2인 요소의 값을 가져와서 msg 변수에 할당함.
				$.ajax({ 						// jQuery의 ajax 함수를 호출하여 AJAX요청을 보냄.
					url : "/ajax/prone.do", 	// AJAX요청을 보낼 서버의 URL을 지정.   
					data : {"msg" : msg},		// AJAX요청으로 서버에 보낼 데이터를 설정. 여기서는 msg 변수의 값을 "msg"라는 이름으로 서버에 전송.
					type : "GET",				// http 요청 방식을 설정. 여기서는 GET 방식으로 으로 설정. 데이터를 URL에 포함하여 서버에 전송함.
					success : function(){
						console.log("서버 전송 성공!");
					},
					error : function(){
						console.log("서버 전송 실패")
					}
				});
			}
			
			
			// 이 코드는 jQuery를 사용하여 AJAX 요청을 보내는 기능을 구현합니다. 
			// #jq-btn3라는 버튼을 클릭할 때 AJAX 요청이 발생하며, 해당 요청에 대한 응답을 받아와서 화면에 표시하는 기능입니다.
			$("#jq-btn3").on("click", function(){ // id가 "jq-btn3"인 요소를 클릭했을 때의 이벤트를 처리합니다.
				$.ajax({
					url : "/ajax/extwo.do",
					type : "GET",
					success : function(data){ // ajax요청이 성공한 경우에 실행할 함수를 정의함, 서버로부터 받은 응답 데이터를 매개변수로 받음, 받은 데이터를 사용하여 화면에 표시함.
						$("#confirm-area").text("서버에서 받은 값:" + data); // id가 "confirm-area"인 요소의 텍스트를 변경하여 화면에 서버로부터 받은 데이터를 표시함. 이전의 내용은 모두 대체됨.
					},
					error : function(){
						alert("Ajax 통신 오류 !");
					}
				})
			});
			
			
		</script>
			
	</body>
</html>