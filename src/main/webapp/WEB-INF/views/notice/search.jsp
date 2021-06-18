<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- 합쳐지고 최소화된 최신 CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <!-- 부가적인 테마 -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
        <title>Home</title>
    </head>

    <body>
        <div class="container">
            <section id="container">
                <div class="row">
                    <div>
                        <h1 class="text-center">Home</h1>
                    </div>
                </div>
                <div class="row justify-content-end">
                    <div class="col align-self-end">
                        <a style="text-align: right" href="/home">목록</a>
                        <a style="text-align: right" href="/write">글 작성</a>
                    </div>
                </div>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">게시물 번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">내용</th>
                            <th scope="col">글쓴이</th>
                            <th scope="col">조회수</th>
                            <th scope="col">생성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${page.getResults()}" var="notice">
                            <tr>
                                <th scope="row">${notice.getSeq()}</th>
                    	        <td><a href="/read?seq=${notice.getSeq() }"><c:out value="${notice.getTtl() }"/></a></td>
                                <td>${notice.getCont()}</td>
                                <td>${notice.getRgstrNm()}</td>
                                <td>${notice.getReadCnt()}</td>
                                <td>${notice.getNotiYmd()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <nav aria-label="Page navigation example">
                    <div class="container">

                        <div class="row">
                            <div class="col col align-self-center">
                                <ul class="pagination">
                                    <c:if test="${page.isPrev()}">
                                    <li class="page-item">
                                        </c:if>
                                        <c:if test="${page.isPrev() eq false}">
                                    <li class="page-item disabled">
                                        </c:if>
                                        <c:set var="curPage" value="${page.getCurrentPage()}"></c:set>
                                        <a class="page-link" href="/search?page=${curPage - 1}&type=${type}&searchValue=${searchValue}" tabindex="-1">Previous</a>
                                    </li>
                                    <c:forEach var="i" begin="${page.getFirstPage()}" end="${page.getEndPage()}">
                                        <c:if test="${page.getCurrentPage() eq i}">
                                            <li class="page-item active" aria-current="page">
                                        </c:if>
                                        <c:if test="${page.getCurrentPage() ne i}">
                                            <li class="page-item " aria-current="page">
                                        </c:if>
                                        <a class="page-link" href="/search?page=${i}&type=${type}&searchValue=${searchValue}">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${page.isNext()}">
                                    <li class="page-item">
                                        </c:if>
                                        <c:if test="${page.isNext() eq false}">
                                    <li class="page-item disabled">
                                        </c:if>
                                        <c:set var="curPage" value="${page.getCurrentPage()}"></c:set>
                                        <a class="page-link" href="/search?page=${curPage + 1}&type=${type}&searchValue=${searchValue}">Next</a>
                                    </li>
                                </ul>
                            </div>
                            <form class="d-flex" method="get" action="/search">
                                <select style="height: 30px" name="type">
                                    <option value="">--Please choose an option--</option>
                                    <option name="type" value="글쓴이">글쓴이</option>
                                    <option name="type" value="제목">제목</option>
                                    <option name="type" value="제목+내용">제목+내용</option>
                                </select>
                                <input style="height: 30px" name="searchValue">
                                <button style="height: 30px" type="submit">검색</button>
                            </form>
                        </div>
                    </div>
                </nav>
            </section>
        </div>
    </body>
</html>