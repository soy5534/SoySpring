<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
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
				<c:forEach items="${nList }" var="notice" varStatus="i">
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
				<!-- 페이지네이션 위치 -->
				<tr align="center">
					<td colspan="5">
						<c:if test="${pInfo.startNavi ne '1' }">
							<a href="/notice/list.kh?page=${pInfo.startNavi - 1 }">[이전]</a>
						</c:if>
						<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
							<a href="/notice/list.kh?page=${p }">${p }</a>
						</c:forEach>
						<c:if test="${pInfo.endNavi ne pInfo.naviTotalCount }">
							<a href="/notice/list.kh?page=${pInfo.endNavi + 1 }">[다음]</a>
						</c:if>
					</td>
				</tr>
				<tr>
				
				
				
					<!-- 0221 추가 -->
					<td colspan="4">
						<form action="/notice/search.kh" method="get">
							<select name="searchCondition">
								<option value="all" selected>전체</option>
								<option value="writer" >작성자</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
							</select>
							<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요">
							<input type="submit" value="검색">
						</form>
					</td>
				<!-- // 0221 추가 -->
					
					
					
					
					
					
					<td>
						<button type="button" onclick="showInsertForm();">글쓰기</button>
					</td>
				</tr>
			</tfoot>
		</table>
		<script>
			function showInsertForm() {
				// 공지사항 글쓰기 페이지 이동
				location.href="/notice/insert.kh";
			}
		</script>
	</body>
</html>




























