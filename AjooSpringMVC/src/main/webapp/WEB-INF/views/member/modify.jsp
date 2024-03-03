<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보 수정</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<h1>회원정보 수정</h1>
		<form action="/member/update.kh" method="post">
			<input type="hidden" name="memberId" 	     id="" value="${member.memberId }">
			<fieldset>
				<legend>정보수정</legend>
				<ul>
					<li>
						<label for="">아이디 *</label>
						<span>${member.memberId }</span>
					</li>
					<li>
						<label for="">비밀번호 *</label>
						<input type="password" name="memberPw" 	     id="" value="${member.memberPw }">
					</li>
					<li>
						<label for="">이름 *</label>
						<span>${member.memberName }</span>
					</li>
					<li>
						<label for="">나이</label>
						<span>${member.memberAge }</span>
					</li>
					<li>
						<label for="">성별</label>
						<span>${member.memberGender }</span>
					</li>
					<li>
						<label for="">이메일</label>
						<input type="text" 		name="memberEmail" 	 id="" value="${member.memberEmail }">
					</li>
					<li>
						<label for="">전화번호</label>
						<input type="text" 		name="memberPhone" 	 id="" value="${member.memberPhone }">
					</li>
					<li>
						<label for="">주소</label>
						<input type="text" 		name="memberAddress" id="" value="${member.memberAddress }">
					</li>
					<li>
						<label for="">취미</label>
						<input type="text" 		name="memberHobby"   id="" value="${member.memberHobby }">
					</li>
					
				</ul>
			</fieldset>
			<div>
				<input type="submit" value="수정">
			</div>
		</form>
	</body>
</html>