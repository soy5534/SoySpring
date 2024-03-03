<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<!-- 0221 추가 -->
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
	<h1>공지사항 리스트</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성날짜</th>
				<th>첨부파일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sList }" var="notice" varStatus="i">
				<tr>
					<td>${i.count }</td>
					<td><a href="/notice/detail.kh?noticeNo=${notice.noticeNo }">${notice.noticeSubject }</a></td>
					<td>${notice.noticeWriter }</td>
					<td>${notice.noticeDate }</td>
					<c:if test="${notice.noticeFilename ne null }">
						<td>O</td>
					</c:if>
					<c:if test="${notice.noticeFilename eq null }">
						<td>X</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
<!-- 		// 페이징 영역 -->
			<tr align="center">
				<td colspan="5"><c:if test="${pInfo.startNavi ne '1' }">
						<a
							href="/notice/search.kh?page=${pInfo.startNavi - 1 }&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">[이전]</a>
						<!--@RequestParam("searchCondition") String searchCondition
							, @RequestParam("searchKeyword") String searchKeyword
							이거 없어서 오류 발생하니까  						
							&searchCondition=&searchKeyword= 이거 넣어서 쿼리 스트링을 맞춰준다-->
					</c:if> <c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }"
						var="p">
						
						<a href="/notice/search.kh?page=${p }&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">${p }</a>
					</c:forEach> <c:if test="${pInfo.endNavi ne pInfo.naviTotalCount }">
						<a
							href="/notice/search.kh?page=${pInfo.endNavi + 1 }&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">[다음]</a>
						<!-- 						search로 바꿔줘야 검색한 결과값 사이에서 넘어감 -->
					</c:if></td>
			</tr>
			<tr>
				<td colspan="4">
					<form action="/notice/search.kh" method="get">
<!-- 						검색조건/필터영역/ 셀렉박스 -->
						<select name="searchCondition">
						<!-- selected를 선택해두면, 셀렉박스 기본값이 '작성자'로 변경됨 -->
							<option value="all"		<c:if test="${searchCondition eq 'all' }">selected</c:if>>		전체</option>
							<option value="writer" 	<c:if test="${searchCondition eq 'writer' }">selected</c:if>>	작성자</option>
							<option value="title" 	<c:if test="${searchCondition eq 'title' }">selected</c:if>>	제목</option>
							<option value="content" <c:if test="${searchCondition eq 'content' }">selected</c:if>>	내용</option>
						</select> <input type="text" name="searchKeyword" placeholder="검색어를 입력하세요" value="${searchKeyword}">
						<input type="submit" value="검색">
					</form>
				</td>
				<td>
					<button type="button" onclick="showInsertForm();">글쓰기</button>
				</td>
			

		</tfoot>
	</table>
	<script>
		function showInsertForm() {
			// 공지사항 글쓰기 페이지 이동
			location.href = "/notice/insert.kh";
		}
	</script>
</body>
</html>




























