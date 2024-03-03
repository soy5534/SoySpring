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