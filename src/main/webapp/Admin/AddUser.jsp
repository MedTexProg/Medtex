<%@include file="Admin_header.jsp"%>
<!-- begin::main content -->
<main class="main-content">
    <div class="container-fluid">

        <h3>Welcome <%=username%>! </h3>
        <h3>Currently, there are <%=OnlineUserCounter.getOnlineUserNumber()-1 %> user(s) online!</h3>
    </div>
    <div class="card">
        <div class="card-body">
            <div class="card-title">
                <h4>Add A User</h4>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/AddUserServlet">
                <div class="form-group">
                    <label>Username:</label>
                    <input type="text" name="username">
                    <small id="userExistence" class="form-text text-muted"></small>
                </div>
                <div class="form-group">
                    <label> First name:</label> <input type="text" name="firstname">
                </div>
                <div class="form-group">
                    <label> Last name:</label>  <input type="text" name="lastname"></div>
                <div class="form-group">
                    <label> Email:</label> <input type="text" name="email"></div>
                <div class="form-group">
                    <label>Password:</label> <input type="text" name="password"></div>
                <div class="form-group">
                    <label>Mobile:</label> <input type="text" name="mobile"></div>
                <div class="form-group">
                    <label>Age:</label> <input type="text" name="age" id="age" onkeyup="ageCheck();"></div>
                <div class="form-group">
                    <label> <input type="radio" value="female" name="gender"> Female</label>
                    <label><input value="male" type="radio" name="gender"> Male</label>
                <div class="form-group">
                    <label>Birthday: </label><input type="text" name="birthday"></div>
                <div class="form-group">
                    <label>Address:</label> <textarea name="address"></textarea></div>
                <input id="addUserBtn" type="submit" class="btn btn-primary" value="Add User!" disabled/> <input type="reset" class="btn btn-primary" value="Reset" />
            </form>
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
<!-- end::main content -->
<%@include file="Admin_footer.jsp"%>
