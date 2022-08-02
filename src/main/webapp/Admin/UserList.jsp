<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@include file="Admin_header.jsp"%>
    <!-- begin::main content -->
<main class="main-content">

    <div class="container-fluid">
        <div class="card">
            <div class="card-body">
                <h3>Welcome <%=username%>! </h3>
                <h4>Currently, there are <%=OnlineUserCounter.getOnlineUserNumber()-1 %> user(s) online!</h4><br/>
                <%
                    List<User> userList = (List<User>) request.getAttribute("userList");
                    List<User> users = (List<User>) request.getAttribute("users");
                %>
                <div class="card-title">
                    <h5><%=users.size()%> users are listed, <%=userList.size()%> users in total.</h5>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered" tabindex="1" style="overflow: hidden; outline: none; touch-action: none;">
                        <tr><th>User name</th>><th>First name</th>><th>Last name</th><th>Email</th><th>Password</th><th colspan="3">Operation</th></tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.getUsername()}</td>
                                <td>${user.getFirstName()}</td>
                                <td>${user.getLastName()}</td>
                                <td>${user.getEmail()}</td>
                                <td>${user.getPassword()}</td>
                                <td><a class="btn btn-primary" href="<%=request.getContextPath()%>/userDetailServlet?username=${user.username}">Detail</a></td>
                                <td><a class="btn btn-info" href="<%=request.getContextPath()%>/userDetailServlet?username=${user.username}" onclick="return window.confirm('Are you sure to update a user?')">Update</a></td>
                                <td><a class="btn btn-danger" href="<%=request.getContextPath()%>/userDeleteServlet?username=${user.username}" onclick="return window.confirm('Are you sure to delete a user?')">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <nav aria-label="...">
                    <ul class="pagination">
                        <!-- pageIndex-1<1 returns true, it should be disabled! -->
                        <c:set var="disabled" value="${pageIndex-1<1?'disabled':''}"></c:set>
                        <li class="page-item ${disabled}">
                            <a class="page-link" href="${basePath}/UserListServlet?pageIndex=${pageIndex-1}">Previous</a>
                        </li>
                        <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                            <c:set var="active" value="${loop.index==pageIndex ? 'active' : ''}"></c:set>
                            <li class="page-item ${active}">
                                <a class="page-link" href="${basePath}/UserListServlet?pageIndex=${loop.index}">${loop.index}</a>
                            </li>
                        </c:forEach>
                        <c:set var="disabled" value="${pageIndex + 1 > totalPages?'disabled':''}"></c:set>
                        <li class="page-item ${disabled}">
                            <a class="page-link" href="${basePath}/UserListServlet?pageIndex=${pageIndex+1 < totalPages ? pageIndex + 1 : totalPages}">Next</a>
                        </li>
                        <li class="page-item ${disabled}">
                            <a class="page-link" href="${basePath}/UserListServlet?pageIndex=${totalPages}">Last</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</main>

<%
            }
            else {
                response.sendRedirect("../index.jsp");
            }
        }
    }
%>
<%@include file="Admin_footer.jsp"%>