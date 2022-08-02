<%@include file="Admin_header.jsp"%>
    <!-- Sidebar Navigation end-->
    <!-- begin::main content -->
    <main class="main-content">

        <div class="container-fluid">
            <div class="card">
                <div class="card-body">
                    <h3>Welcome <%=username%>! </h3>
                    <h3>Currently, there are <%=OnlineUserCounter.getOnlineUserNumber()-1 %> user(s) online!</h3><br/>
                    <div class="card-title">
                        <h4>User Detail</h4>
                    </div>

                    <%
                        ResultSet rs = (ResultSet) request.getAttribute("userDetail");
                        if (rs!=null){
                    %>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered" tabindex="1" style="overflow: hidden; outline: none; touch-action: none;">
                            <form method="post" action="${pageContext.request.contextPath}/UpdateCompleteServlet?Id=<%=rs.getString("Id")%>">
                            <tr><td>Username</td><td><input type="text" name="username" value='<%=rs.getString("username")%>'></td></tr>
                            <tr><td>Password</td><td><input type="text" name="password" value='<%=rs.getString("password")%>'></td></tr>
                            <tr><td>Email</td><td><input type="text" name="email" value='<%=rs.getString("email")%>'></td></tr>
                            <tr><td>First Name</td><td><input type="text" name="firstname" value='<%=rs.getString("first_name")%>'></td></tr>
                            <tr><td>Last Name</td><td><input type="text" name="lastname" value='<%=rs.getString("last_name")%>'></td></tr>
                            <tr><td>Gender</td><td><input type="text" name="gender" value='<%=rs.getString("gender")%>'></td></tr>
                            <tr><td>Birthday</td><td><input type="text" name="birthday" value='<%=rs.getDate("birthday")%>'></td></tr>
                            <tr><td>Age</td><td><input type="text" name="age" value='<%=rs.getLong("age")%>'></td></tr>
                            <tr><td>Mobile</td><td><input type="text" name="mobile" value='<%=rs.getString("mobile")%>'></td></tr>
                            <tr><td>Address</td><td><input type="text" name="address" value='<%=rs.getString("address")%>'></td></tr>
                            <tr><td><input type="submit" value="Update" class="btn btn-primary"></td><td><input type="button" value="Back" class="btn btn-primary"> </td></tr>
                            </form>
                        </table>
                    </div>
                    <%
                    } else {
                    %>
                    <h5>Failed to obtain the information of <%=username%>!</h5>
                    <%
                        }
                    %>
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