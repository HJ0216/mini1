<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링부트 웹서비스</title>
    <%--부트스트랩 css 추가--%>
    <link rel="stylesheet" href="/css/lib/bootstrap.min.css">
</head>
<body>
	<h1>스프링부트로 시작하는 웹 서비스</h1>

    <div class="col-md-12">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#savePostsModal">
          Write a post
        </button>

        <!-- 목록 출력 영역 -->
        <table class="table table-horizontal table-bordered">
            <thead class="thead-strong">
                <tr>
                    <th>게시글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>최종수정일</th>
                </tr>
            </thead>
            <tbody id="tbody">
                <%while(rs.next()){ %>
                    <tr>
                        <td><%=rs.getInt("id") %></td>
                        <td><%=rs.getString("title") %></td>
                        <td><%=rs.getString("author") %></td>
                        <td><%=rs.getString("content") %></td>
                        <td><%=rs.getTimestamp("date") %></td>
                    </tr>
                <%} %>
            </tbody>
        </table>
    </div>

<!-- Modal -->
<div class="modal fade" id="savePostsModal" tabindex="-1" aria-labelledby="savePostsLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="savePostsLabel">게시글 등록</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
            <form>
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요">
                </div>
                <div class="form-group">
                    <label for="author"> 작성자 </label>
                    <input type="text" class="form-control" id="author" placeholder="작성자를 입력하세요">
                </div>
                <div class="form-group">
                    <label for="content"> 내용 </label>
                    <textarea class="form-control" id="content" placeholder="내용을 입력하세요"></textarea>
                </div>
            </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary" id="btn-save">Submit</button>
      </div>
    </div>
  </div>
</div>



    <%-- 부트스트랩 js, jquery 추가
    bootstrap.js가 jquery에 의존
    bootstrap.js의 경우 jquery가 꼭 있어야만 하기 때문에 bootstrap보다 먼저 호출되도록 코드 작성
    --%>
    <script src="/js/lib/jquery.min.js"></script>
    <script src="/js/lib/bootstrap.min.js"></script>

    <%-- custom js 추가 --%>
    <script src="/js/app/main.js"></script>
</body>
</html>

<%-- CDN을 사용하지 않는 이유
결국은 외부 서비스에 우리 서비스가 의존하게 되버려서, CDN을 서비스하는 곳에 문제가 생기면 덩달아 같이 문제가 생기기 때문
--%>

<%-- 호출 주소가 /로 시작하는 이유
SpringBoot는 기본적으로 src/main/resources/static은 URL에서 / 로 지정
--%>

<%-- css, js 위치가 다른 이유
HTML은 최상단에서부터 코드가 실행되기 때문에 head가 다 실행되고서야 body가 실행됩니다.
즉, head가 다 불러지지 않으면 사용자 쪽에선 백지 화면만 노출됩니다.
특히 js의 용량이 크면 클수록 body 부분의 실행이 늦어지기 때문에 js는 body 하단에 두어 화면이 다 그려진 뒤에 호출하는것이 좋습니다.

반면 css는 화면을 그리는 역할을 하기 때문에 head에서 불러오는것이 좋습니다.
--%>