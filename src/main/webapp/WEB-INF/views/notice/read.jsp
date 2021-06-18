<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='readForm']");
			
			$(".update_btn").on("click", function(){
				formObj.attr("action", "/update");
				formObj.attr("method", "get");
				formObj.submit();
			})
		
			$(".delete_btn").on("click", function(){
				
				var deleteYN = confirm("삭제하시겠습니까?");
			
				if(deleteYN == true) {
					
					formObj.attr("action", "/delete");
					formObj.attr("method", "post");
					formObj.submit();
				}
				
			})
	
			$(".list_btn").on("click", function(){
				location.href = "/home?page=${page}";
			})
			
		})
	</script>
<body>
<div class="container">
		
			<section id="container">
				<form name="readForm" role="form" method="post">
					<input type="hidden" id="seq" name="seq" value="${read.seq }" />
					<input type="hidden" id="page" name="page" value="${page }" />
				</form>	
					
					<div class="form-group">
						<label for="seq" class="col-sm-2 control-label">글 번호</label>
						<input type="text" class="form-control" id="seq" name="seq" value="${read.seq}"  title="제목을 입력하세요" readonly="readonly" />
					</div>				
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">제목</label>
						<input type="text" class="form-control" id="ttl" name="ttl" value="${read.ttl}" readonly="readonly" />
					</div>
					<div class="form-group">									
						<label for="content" class="col-sm-2 control-label">내용</label>
						<textarea id="cont" class="form-control" name="cont" readonly="readonly"><c:out value="${read.cont}" /></textarea>
					</div>
					<div class="form-group">									
						<label for="writer" class="col-sm-2 control-label">작성자</label>
						<input type="text" class="form-control" id="rgstrNm" name="rgstrNm" value="${read.rgstrNm}" readonly="readonly" />
					</div>
					<div class="form-group">									
						<label for="readCnt" class="col-sm-2 control-label">조회수</label>
						<input type="text" class="form-control" id="readCnt" name="readCnt" value="${read.readCnt}" readonly="readonly" />
					</div>
					<div class="form-group" class="col-sm-2 control-label">		
						<label for="regdate">작성날짜</label>
 		                <td>${read.getNotiYmd()}</td>
					</div>				
					
					<c:if test="${read.file ne Null }">
						<tr>
							<label for="file" class="col-sm-2 control-label">첨부파일</label>
							<td align="left"><a href="fileDownload?file=${read.file }">${read.file }</a></td>
						</tr>
					</c:if>
					<div>
						<button type="button" class="update_btn btn btn-warning">수정</button>
						<button type="button" class="delete_btn btn btn-danger">삭제</button>
						<button type="button" class="list_btn btn btn-primary">목록</button>
					</div>
			</section>
			<hr />
			
			<c:if test="${read.prevNum ne 0 }">
				<a href="/read?seq=${read.prevNum }&page=${page}">이전글</a>
			</c:if>
			<c:if test="${read.nextNum ne 0 }">
				<a href="/read?seq=${read.nextNum }&page=${page}">다음글</a>
			</c:if>
</div>
</body>
</html>