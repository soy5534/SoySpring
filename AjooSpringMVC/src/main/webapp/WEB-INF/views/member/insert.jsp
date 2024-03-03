<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 가입 폼</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<form action="/member/register.kh" method="post">
			<fieldset>
				<legend>회원가입</legend>
				<ul>
					<li>
						<label for="">아이디 *</label>
						<input type="text" name="memberId" id="">
					</li>
					<li>
						<label for="">비밀번호 *</label>
						<input type="password" name="memberPw" id="">
					</li>
					<li>
						<label for="">이름 *</label>
						<input type="text" name="memberName" id="">
					</li>
					<li>
						<label for="">나이</label>
						<input type="text" name="memberAge" id="">
					</li>
					<li>
						<label for="">성별</label>
						남 <input type="radio" name="memberGender" id="" value="남">
						여 <input type="radio" name="memberGender" id="" value="여">
					</li>
					<li>
						<label for="">이메일</label>
						<input type="text" name="memberEmail" id="">
					</li>
					<li>
						<label for="">전화번호</label>
						<input type="text" name="memberPhone" id="">
					</li>
					<li>
						<label for="">주소</label>
						<input type="text" name="memberAddress" id="memberAddr" size="50">
						<input type="button" onclick="execDaumPostcode();" value="우편번호 찾기">
					</li>
					<li>
						<label for="">취미</label>
						<input type="text" name="memberHobby" id="">
					</li>
					
				</ul>
			</fieldset>
			<div>
				<input type="submit" value="가입">
			</div>
		</form>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
			function execDaumPostcode() {
				new daum.Postcode({
			        oncomplete: function(data) {
			            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
			            console.log(data.autoJibunAddress + ", " + data.zonecode + ", " + data.buildingName);
			            document.querySelector("#memberAddr").value = data.address + ", " + data.zonecode + ", " + data.buildingName;
			        }
			    }).open();
			}
		</script>
	</body>
</html>




















