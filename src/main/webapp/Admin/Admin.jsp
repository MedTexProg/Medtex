<%@include file="Admin_header.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Medy
  Date: 4/10/2022
  Time: 7:39 PM
  To change this template use File | Settings | File Templates.
--%>
    <!-- Sidebar Navigation end-->
<main class="main-content">

    <div class="container-fluid">
        <h3>Welcome <%=username%>! </h3>
        <h3>Currently, there are <%=OnlineUserCounter.getOnlineUserNumber()-1 %> user(s) online!</h3>
    </div>

    <div class="card">
        <div class="card-body">
            <%
                ResultSet rs = (ResultSet)request.getAttribute("searchResult");
                ResultSet rsSearchCount = (ResultSet) request.getAttribute("rsSearchCount");
                if ((rs != null) && (rsSearchCount != null)) {
                    while (rsSearchCount.next()){
            %>
            <div class="card-title"><h5>Search Result: <%=rsSearchCount.getString("count(*)")%> result(s) found!</h5></div>
            <div class="table-responsive" tabindex="1" style="overflow: hidden; outline: none">
                <table class="table table-striped table-bordered">
                    <thead>
                    <th>Username</th><th>Email</th><th>First name</th><th>Last name</th><th colspan="3">Operation</th>
                    </thead>
                    <tbody>
                    <%
                        while (rs.next()){
                    %>
                    <tr>
                        <td><%=rs.getString("username")%></td>
                        <td><%=rs.getString("email")%></td>
                        <td><%=rs.getString("first_name")%></td>
                        <td><%=rs.getString("last_name")%></td>
                        <td>
                            <a class="btn btn-primary" href='<%=request.getContextPath()%>/userDetailServlet?username=<%=rs.getString("username")%>'>Detail</a>
                        </td>
                        <td>
                            <a class="btn btn-danger" href='<%=request.getContextPath()%>/userDetailServlet?username=<%=rs.getString("username")%>'>Update</a>
                        </td>
                        <td>
                            <a class="btn btn-info" href='<%=request.getContextPath()%>/UserDeleteServlet?username=<%=rs.getString("username")%> ' onclick="return window.confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                    <%
                        }
                        }
                    } else {
                    %>

                    </tbody>
                </table>
            </div>
            <h5>No result found!</h5>

            <%
                }
            %>
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