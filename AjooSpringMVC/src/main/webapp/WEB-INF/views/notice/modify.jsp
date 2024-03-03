<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 수정</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<h1>공지사항 수정</h1>
		<form action="/notice/modify.kh" method="post" enctype="multipart/form-data">
			<input type="hidden" name="noticeNo" 		 value="${notice.noticeNo }">
			<!--  
				1. 파일있으면 삭제할 수 있도록 함
				2. 첨부파일을 선택하지 않고 수정할 때 null로 입력되는 것을 방지할 수 있음.
			 -->
			<input type="hidden" name="noticeFilename" 	 value="${notice.noticeFilename }">
			<input type="hidden" name="noticeFileRename" value="${notice.noticeFileRename }">
			<input type="hidden" name="noticeFilepath" 	 value="${notice.noticeFilepath }">
			<input type="hidden" name="noticeFilelength" value="${notice.noticeFilelength }">
			<ul>
				<li>
					<label>제목</label>
					<input type="text" name="noticeSubject" value="${notice.noticeSubject }">
<%-- 					<span>${notice.noticeSubject }</span> --%>
				</li>
				<li>
					<label>작성자</label>
<%-- 					<input type="text" value="${notice.noticeWriter }"> --%>
					<span>${notice.noticeWriter }</span>
				</li>
				<li>
					<label>내용</label>
					<textarea rows="4" cols="50" name="noticeContent">${notice.noticeContent }</textarea>
<%-- 					<span>${notice.noticeContent }</span> --%>
				</li>
				<li>
					<label>첨부파일</label>
					<span><a href="../resources/nuploadFiles/${notice.noticeFileRename }" download>${notice.noticeFilename }</a></span>
					<input type="file" name="reloadFile">
				</li>	
			</ul>
			<br>
			<br>
			<div>
				<input type="submit" value="수정하기">
				<button type="button" onclick="showNoticeList();">목록으로 이동</button>
			</div>
		</form>
		<script>
			function showNoticeList() {
				location.href = "/notice/list.kh";
			}
		</script>
	</body>
</html>