<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세</title>
</head>
<body>
	<h1>게시글 상세</h1>
	<ul>
		<li>
			<label>제목</label>
			<span>${board.boardTitle }</span>
		</li>
		<li>
			<label>작성자</label>
			<span>${board.boardWriter }</span>
		</li>
		<li>
			<label>내용</label>
			<span>${board.boardContent }</span>
		</li>
		<li>
			<label>첨부파일</label>
			<a href="#">${board.boardFilename }</a>
		</li>
	</ul>	
	<div>
		<button>수정하기</button>
		<button>삭제하기</button>
		<button>목록으로</button>
		<button>뒤로가기</button>
	</div>
	
<!-- 	
	아래 코드는 웹 페이지에서 댓글을 등록하고 표시하는 기능을 구현하는 HTML과 JSP 코드입니다. 각 부분을 알기 쉽고 자세하게 설명하겠습니다
	
	댓글 등록 양식:
	
	<form> 태그를 사용하여 댓글을 등록하는 양식을 만듭니다.
	action 속성은 양식이 제출될 URL을 지정합니다. 여기서는 "/reply/add.kh"로 설정되어 있습니다.
	method 속성은 HTTP 요청 방법을 지정합니다. 여기서는 POST 방식을 사용합니다.
	input 태그를 사용하여 댓글 내용과 게시물 번호를 입력 받습니다.
	type="hidden"은 사용자에게 보이지 않는 숨겨진 입력 필드를 생성합니다. 여기서는 게시물 번호를 전달하기 위해 사용됩니다.
	마지막으로 "완료" 버튼을 누르면 입력된 댓글 내용이 제출됩니다.
	*ref = reference(참조)
	
	댓글 목록 표시:
	
	<table width="550" border="1">: 댓글 목록을 표시하기 위한 테이블을 생성합니다. width 속성은 테이블의 너비를, border 속성은 테이블의 테두리 두께를 나타냅니다.

	<c:forEach items="${rList }" var="reply">: JSTL(JavaServer Pages Standard Tag Library)의 <c:forEach> 태그를 사용하여 댓글 목록(rList)을 순회합니다. 각각의 댓글은 reply라는 변수에 저장됩니다.
	
	<tr>: 각 댓글을 표시하기 위한 행을 생성합니다.
	
	<td>${reply.replyWriter }</td>: 댓글의 작성자를 나타내는 셀을 생성합니다. ${reply.replyWriter}는 각 댓글의 작성자를 나타내는 데이터를 출력합니다.
	
	<td>${reply.replyContent }</td>: 댓글의 내용을 나타내는 셀을 생성합니다. ${reply.replyContent}는 각 댓글의 내용을 나타내는 데이터를 출력합니다.
	
	<td>${reply.rCreateDate }</td>: 댓글의 작성일을 나타내는 셀을 생성합니다. ${reply.rCreateDate}는 각 댓글의 작성일을 나타내는 데이터를 출력합니다.
	
	<td>에는 "수정"과 "삭제" 링크가 포함되어 있습니다. 현재는 링크가 "#"로 설정되어 있어 클릭 시 어떠한 동작도 수행되지 않습니다.
	
	</c:forEach>: <c:forEach> 태그의 종료를 나타냅니다.
-->
	
	<!-- 댓글 등록 -->
	<hr>
	<form action="/reply/add.kh" method="post">
		<input type="hidden" name="refBoardNo" value="${board.boardNo }">
		<table width="500" border="1">
			<tr>
				<td>
					<input type="text" name="replyContent" size="20">
				</td>
				<td>
					<input type="submit" value="완료">
				</td>
			</tr>
		</table>
	</form>
	<!-- 댓글 목록 -->
	<table width="550" border="1">
		<c:forEach items="${rList }" var="reply">
		<tr>
			<td>${reply.replyWriter }</td>
			<td>${reply.replyContent }</td>
			<td>${reply.rCreateDate }</td>
			<td>
				<a href="#">수정</a>
				<a href="#">삭제</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>