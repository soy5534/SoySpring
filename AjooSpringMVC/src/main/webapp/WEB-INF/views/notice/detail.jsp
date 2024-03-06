<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 상세</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<h1>공지사항 상세조회</h1>
		<ul>
			<li>
				<label>제목</label>
				<span>${notice.noticeSubject }</span>
			</li>
			<li>
				<label>작성자</label>
				<span>${notice.noticeWriter }</span>
			</li>
			<li>
				<label>내용</label>
				<span>${notice.noticeContent }</span>
			</li>
			<li>
				<label>첨부파일</label>
				<span><a href="../resources/nuploadFiles/${notice.noticeFileRename }" download>${notice.noticeFilename }</a></span>
			</li>	
		</ul>
		<br>
		<br>
		<div>
			<button type="button" onclick="showModifyPage();">수정하기</button>
			<button type="button" onclick="deleteNotice(${notice.noticeNo });">삭제하기</button>
			<button type="button" onclick="showNoticeList();">목록으로 이동</button>
		</div>		
		
		<!-- 댓글 목록 -->
		<table width="550" border="1" id="replyTable">
			<tbody></tbody>
		</table>
		
		<!-- 댓글 등록 -->
		<input type="hidden" name="refBoardNo" id="refBoardNo" value="${notice.noticeNo }">
		<table width="500" border = "1">
			<tr>
				<td>
					<input type="text" name="replyContent" id="replyContent" size="50">
				</td>
				<td>
					<button id="rSubmit">등록하기</button>
				</td>
			</tr>
		
		</table>
		
		<script>
			function deleteNotice(noticeNo) {
				if(confirm("삭제하시겠습니까?")) {
					location.href = "/notice/delete.kh?noticeNo="+noticeNo;
				}
			}
			function showModifyPage() {
				var noticeNo = "${notice.noticeNo }";
				location.href = "/notice/modify.kh?noticeNo="+noticeNo;
			}
			function showNoticeList() {
				location.href = "/notice/list.kh";
			}
			
			// 댓글 리스트 출력
			getReplyList();
			function getReplyList(){
				var refBoardNo = $("#refBoardNo").val();
				$.ajax({
					url : "noticereply/list.kh",
					data : { "refBoardNo" : refBoardNo },
					type : "GET",
					success : function(){
						var tableBody = $("#replyTable tbody");
						tableBody.html("");
						var tr;
						var replyWriter;
						var replyContent;
						var rCreateDate;
						var btnArea;
						if(result.length > 0){
							for(var i in result){
								var replyWriterVal = result[i].replyWriter;
								var replyContentVal = result[i].replyContent;
								var rCreateDateVal = result[i].rCreateDate;
								var replyNoVal = result[i].replyNo;
								tr = $("<tr>")
								replyWriter = $("<td>").text(replyWriterVal);
								replyContent = $("<td>").text(replyContentVal);
								rCreateDate = $("<td width='100'>").text(rCreateDateVal);
								btnArea = $("<td width='90'>")
									.append("<a href='javascript:void(0)' onclick='modifyViewReply(this, ) "
											+ replyNoVal + ", \"" + replyContentVal + "\");'>수정</a>")
									.append("<a href='javascript:void(0)' onclick='removeReply(" + replyNoVal + ");'>삭제</a>")
								tr.append(replyWriter);
								tr.append(replyContent);
								tr.append(rCreateDate);
								tr.append(btnArea);
								tableBody.append(tr);
							}
						}
					},
					error : function(){
						alert("ajax 통신 실패, 관리자에게 문의해주세요");
					}
				});
			}
			
			// 댓글 등록
			$("#rSubmit").on("click", function(){
				var refBoardNo = $("#refBoardNo").val();
				var replyContent = $("#replyContent").val();
				$.ajax({
					url : "/boardreply/add.kh",
					data : {"refBoardNo" : refBoardNo, "replyContent" : replyContent},
					type : "POST",
					success : function(result){
						alert("댓글이 등록되었습니다.");
						getReplyList();
						$("#replyContent").val("");
					},
					error : function(){
						alert("ajax 통신 실패! 관리자에게 문의바랍니다.");	
					}
				});
			});
			
			// 댓글 삭제
			
			// 댓글 수정
			
			
		</script>
	</body>
</html>