<%@include file="Admin_header.jsp"%>

<main class="main-content">
    <div class="container-fluid">
        <h4>Welcome <%=username%>! </h4>
        <h4>Currently, there are <%=OnlineUserCounter.getOnlineUserNumber()-1 %> user(s) online!</h4>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">User login times</div>
                    <div id="userlog_data" style="display: none">
                        <%
                            ResultSet rs = (ResultSet) request.getAttribute("userlogs_rs");
                            if (rs!= null) {
                                while (rs.next()){
                        %>
                                    <span class="userlog_cell_useranme"><%=rs.getString("username")%></span><span class="userlog_cell_freq"><%=rs.getString("count(1)")%></span>
                        <%
                                }
                            } else {
                        %>
                            <h4>Failed to obtain user log information!</h4>
                        <%
                            }
                        %>
                    </div>
                    <canvas id="userLog_barChart">

                    </canvas>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">User gender ratio</div>
                    <div id="userlog_gender_data" style="display: none">
                        <%
                            ResultSet rs_gender = (ResultSet) request.getAttribute("userlogs_gender_rs");
                            if (rs_gender!= null) {
                                while (rs_gender.next()) {
                                    %>
                                <span class="userlog_gender_cell"><%=rs_gender.getString("Gender")%></span>
                                <span class="userlog_gender_num"><%=rs_gender.getString("GenderNum")%></span>
                        <%
                                }
                            } else {
                            %>
                            <h4>Failed to obtain gender information!</h4>
                        <%
                            }
                        %>
                    </div>
                    <canvas id="userlog_pieChart">

                    </canvas>
                </div>
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

