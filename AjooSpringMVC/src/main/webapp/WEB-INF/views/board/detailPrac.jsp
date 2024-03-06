<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
		<button>수정</button>
		<button>삭제</button>
		<button>목록으로</button>
		<button>뒤로가기</button>
	</div>
	
	
	
	<!-- 댓글 등록 -->
	<hr>
	<input type="hidden" name="refBoardNo" id="refBoardNo" value="${board.boardNo }">
	<table width="500" border="1">
		<tr>
			<td>
				<input type="text" name="replyContent" id="replyContent" size="50">
			</td>
			<td>
				<button id="rSubmit">등록하기</button>
			</td>
		</tr>
	</table>
	
	<!-- 댓글 목록 -->
	<table width="500" border="1">
		<c:forEach items="${rList }" var="reply">
		<tr> <!-- tr = 행 -->
			<td>${reply.replyWriter }</td> <!-- td = 열 -->
			<td>${reply.replyContent }</td>
			<td>${reply.rCreateDate }</td>
			<td>
				<a href="#">수정</a>
				<a href="#">삭제</a>
			</td>
		</tr>		
		</c:forEach>
	</table>
	
	<script>
		$("rSubmit").on("click", function(){
			var refBoardNo = $("#refBoardNo").val();
			var replyContent = $("#replyContent").val();
			$.ajax({
				url : "/reply/add.kh",
				data : {"refBoardNo" : refBoardNo, "replyContent" : replyContent},
				type : "POST",
				success : function(result){
					alert("댓글이 등록되었습니다.");
					location.href = "/board/detail.kh?boardNo" + refBoardNo;
				},
				error : function(){
					alert("Ajax 통신 실패! 관리자에게 문의바랍니다.");
				}
			});
		});
	</script>
	
</body>
</html>