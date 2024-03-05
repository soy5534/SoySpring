<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<p>
			Ajax는 Asynchoronous Javascript And XML이란 용어로<br> 서버로부터 데이터를 가져와
			전체페이지를 새로 고치지 않고 일부만 로드할 수 있도록 비동기식 요청을 함.
		</p>
		<h3>동기식/비동기식이란?</h3>
		<p>
			동기식은 서버와 클라이언트가 동시에 통신하여 프로세스를 수행 및 종료까지 같이하는 방식 <br> 이에 반해 비동기식은
			페이지 리로딩없이 서버요청 사이사이 추가적인 요청과 처리 가능
		</p>
		<h3>Ajax 구현(Javascript)</h3>
		<h4>1. ajax로 서버에 전송값 보내기</h4>
		<p>버튼 클릭시 전송값을 서버에서 출력</p>
		<input type="text" id="msg-1">
		<button type="button" onclick="jsFuncGet();">보내기(JS)</button>
		
		<h3>Ajax 구현(jQuery)</h3>
		<h4>2. ajax(jQuery)로 서버에 전송값 보내기</h4>
		<p>버튼 클릭시 전송값을 서버에서 출력</p>
		<input type="text" id="msg-2">
		<button type="button" onclick="jQueryFuncGet();">보내기(JS)</button>
		
		<h3>버튼 클릭시 서버에서 보낸 값 수신</h3>
		<button id="jq-btn3">서버에서 보낸 값 확인(받는거임)</button>
		<p id="confirm-area"></p>
		
		<!-- 24/03/05 추가 -->
		<h3>4. 서버로 전송값 보내고 결과 문자열 받아서 처리</h3>
		<h4>숫자 2개를 전송하고 더한 값 받기</h4>
		첫번째 수 : <input type="number" id="num-1">
		두번째 수 : <input type="number" id="num-2">
		<button id="jq-btn4">전송 및 결과확인</button>
		
		<h3>5. 서버로 전송값 보내고 결과 JSON으로 받아서 처리</h3>
		유저 번호 입력 : <input type="text" id="user-num">
		<p id="p5"></p>
		<button id="jq-btn5">실행 및 결과확인</button>
		
		<h3>6. 서버로 전송값을 보내고 JSONArray로 결과 받아서 처리</h3>
		<p>유저 번호를 보내서 해당 유저를 가져오고, 없는 경우 전체리스트 가져오기</p>
		유저 번호 입력 : <input type="text" id="find-num">
		<p id="p6"></p>
		<button id="jq-btn6">실행 및 결과확인</button>
		
		
		
		<br><br><br><br><br><br><br><br><br><br><br><br>
		<br><br><br><br><br><br><br><br><br><br><br><br>
		<br><br><br><br><br><br><br><br><br><br><br><br>
		<br><br><br><br><br><br><br><br><br><br><br><br>
		
		<script>
				
			// 이걸 쓰는 게 복잡하니깐 jQuery를 이용하는 것.
			function jsFuncGet() {
				// 1. XMLHttpRequest 객체 생성 
				var xhttp = new XMLHttpRequest();
				var msg = document.querySelector("#msg-1").value;
				// 2. 요청정보 설정 
				xhttp.open("GET", "/ajax/exone.do?msg=" + msg, true);
				// 3. 데이터 처리에 따른 동작함수 설정 
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) { // '통신이 성공했다면' ( http응답코드 200~성공 400~대부분 오류)
						console.log("서버 전송 성공!");
					} else if (this.readyState == 4 && this.status == 404) {
						console.log("서버 전송 실패!");
					}
				}
				// 4. 전송 
				xhttp.send();
			}
		
			function jQueryFuncGet(){
				var msg = $("#msg-2").val();
				$.ajax({
					url : "/ajax/exone.do",
					data : {"msg" : msg},
					type : "GET",
					success : function(){
						console.log("서버 전송 성공!");
					},
					error : function(){
						console.log("서버 전송 실패");
					}
				});
			}
			
			$("#jq-btn3").on("click", function(){
				// DOM의 addEventListner와 같은 것
// 				alert("clicked");
				$.ajax({
					url : "/ajax/extwo.do",
					type : "GET",
					success : function(data){ // 매개변수 data를 사용하여 전달된 데이터를 받음
						// 서버에서 받은 값을 화면에 표시
					    $("#confirm-area").text("서버에서 받은 값: " + data);
// 						alert(data);
					},
					error : function(){
						alert("Ajax 통신 오류 !!");
					}
				})
			});
			
			// 24.03.05 추가 
			$("#jq-btn4").on("click", function(){ // 이 버튼이 클릭되면 아래 함수 실행
				var num1 = $("#num-1").val();
				var num2 = $("#num-2").val();
				$.ajax({
					// url data type 형식 외워주세요.
					url : "/ajax/exthree.do",
					data : { "num1" : num1, "num2" : num2 }, // : 기준 오른쪽값은 
					type : "GET",
					success : function(data){
						alert(data);
					},
					error : function(){
						alert("서버 전송 실패!");						
					}
				});				
			});
			
			$("#jq-btn5").on("click", function(){
				var userNum = $("#user-num").val();
				$.ajax({
					url : "/ajax/exfour.do",
					data : {"userNum" : userNum},
					type : "GET",
					success : function(data){
						console.log(data);
						$("#p5").html("아이디: " + data.userId + ", 비밀번호 : " + data.userPw);
					},
					error : function(data){
						alert("Ajax 통신 실패! 관리자에게 문의해주세요");
					}
				});
			});
			
			$("#jq-btn6").on("click", function(){
				var findNum = $("#find-num").val();
				$.ajax({
					url : "/ajax/exfive.do",
					data : {"findNum" : findNum}, // 서버에 전송할 데이터를 지정. 여기서는 "findNum" 파라미터에 위에서 가져온 findNum 값을 전송
					type : "GET",
					success : function(data) {
						var str = "";
						for(var i = 0; i < data.length; i++){
							str += "아이디 : " + data[i].userId + ", 비밀번호 : " + data[i].userPw + "<br>"; // += : 누적합
						}
						$("#p6").html(str);
						// $("#p6").html("아이디 : " + data.userId + ", 비밀번호 : " + data.userPw);
						// console.log(data.userId);
						// console.log(data.userPw);
					},						
					error : function(data){
						alert("Ajax 통신 실패! 관리자에게 문의해주세요");
					}
				});
			});
			
		</script>
	</body>
</html>