<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
	<h1>게시물 목록</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성날짜</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bList }" var="board" varStatus="i">
				<tr>
					<td>${i.count }</td>
					<c:url var="detailUrl" value="/board/detail.kh">
						<c:param name="boardNo" value="${board.boardNo }"></c:param>
						</c:url>
							<td><a href="${detailUrl}">${board.boardTitle }</a></td>
							<td>${board.boardWriter }</td>
							<td>${board.bCreateDate }</td>
							<td>O</td>
							<td>${board.boardCount }</td>
				</tr>
			</c:forEach>
		</tbody>

		<tfoot>
			<tr align="center">
				<td colspan="5"><c:if test="${pInfo.startNavi != 1 }">
					
					<!-- 이전버튼 0228 추가-->
						<c:url var="prevUrl" value="/board/list.kh">
							<c:param name="page" value="${pInfo.startNavi-1 }"></c:param>
						</c:url>
						<a href="/board/list.kh?page=${pInfo.startNavi - 1 }">이전</a>
						</c:if> 
					
					
					<!-- 페이지버튼 -->
					<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
						<c:url var="pageUrl" value="/board/list.kh">
							<c:param name="page" value="${p }"></c:param>
						</c:url>
						<a href="/board/list.kh?page=${pageUrl }">${p }</a>
					</c:forEach> 
					
					
					<!-- 다음버튼 -->
					<c:if test="${pInfo.endNavi != pInfo.naviTotalCount }">
						<c:url var="nextUrl" value="/board/list.kh">
							<c:param name="page" value="${pInfo.endNavi+1 }"></c:param>
						</c:url>
						<a href="${nextUrl }">다음</a>
					</c:if>
					
					
					
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>











