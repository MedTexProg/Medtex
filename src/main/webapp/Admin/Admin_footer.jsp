<script src="<%=basePath%>/asset/vendor/jquery/jquery.min.js"></script>
<script src="<%=basePath%>/asset/vendor/popper.js/umd/popper.min.js"> </script>
<script src="<%=basePath%>/asset/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/asset/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="<%=basePath%>/asset/vendor/chart.js/Chart.min.js"></script>
<script src="<%=basePath%>/asset/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="<%=basePath%>/asset/js/charts-home.js"></script>

<script src="<%=basePath%>/asset/js/front.js"></script>
<script src="<%=basePath%>/asset/vendor/jquery/jquery.min.js"></script>
<script src="<%=basePath%>/asset/vendor/popper.js/umd/popper.min.js"> </script>
<script src="<%=basePath%>/asset/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/asset/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="<%=basePath%>/asset/vendor/chart.js/Chart.min.js"></script>
<script src="<%=basePath%>/asset/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="<%=basePath%>/asset/js/charts-home.js"></script>
<script src="<%=basePath%>/asset/js/front.js"></script>


<script src="<%=basePath%>/asset/vendor/jquery/jquery.min.js"></script>
<script src="<%=basePath%>/asset/vendor/popper.js/umd/popper.min.js"> </script>
<script src="<%=basePath%>/asset/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/asset/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="<%=basePath%>/asset/vendor/chart.js/Chart.min.js"></script>
<script src="<%=basePath%>/asset/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="<%=basePath%>/asset/js/charts-home.js"></script>
<script src="<%=basePath%>/asset/js/front.js"></script>

<script src="<%=basePath%>/asset/vendor/jquery/jquery.min.js"></script>
<script src="<%=basePath%>/asset/vendor/popper.js/umd/popper.min.js"> </script>
<script src="<%=basePath%>/asset/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/asset/vendor/jquery.cookie/jquery.cookie.js"> </script>
<script src="<%=basePath%>/asset/vendor/chart.js/Chart.min.js"></script>
<script src="<%=basePath%>/asset/vendor/jquery-validation/jquery.validate.min.js"></script>
<script src="<%=basePath%>/asset/js/charts-home.js"></script>
<script src="<%=basePath%>/asset/js/front.js"></script>

<script>
    function check(){
        var searchStr = $('#searchBar').val().trim();
        if (searchStr == ""){
            $('#button-addon2').attr('disabled', "true");
        } else {
            $('#button-addon2').removeAttr('disabled');
        }
    }

    function ageCheck(){
        var age = $('#age').val().trim();
        if (!isNaN(age)){
            if (age < 3 || age > 120) {
                $('#addUserBtn').attr("disabled", "true");
            } else {
                $('#addUserBtn').removeAttr('disabled');
            }
        } else {
            $('#addUserBtn').attr("disabled", "true");
        }
    }

    var username_items, username_item = [];
    var freq_items, freq_item = [];

    username_items = document.getElementsByClassName("userlog_cell_useranme");
    freq_items = document.getElementsByClassName("userlog_cell_freq");

    for (var i = 0; i < username_items.length; i++){
        username_item.push(username_items[i].innerHTML);
    }

    for (var i = 0; i < freq_items.length; i++){
        freq_item.push(freq_items[i].innerHTML);
    }

    var ctx = document.getElementById("userLog_barChart").getContext("2d");
    var barChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: username_item,
            datasets:[{
                label: "User login times",
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1,
                data: freq_item,
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var gender, gender_item = [];
    var gender_nums, gender_num = []

    gender = document.getElementsByClassName("userlog_gender_cell");
    gender_nums = document.getElementsByClassName("userlog_gender_num");

    for (var i = 0; i < gender.length; i++){
        gender_item.push(gender[i].innerHTML);
    }

    for (var i = 0; i < gender_nums.length; i++){
        gender_num.push(Number(gender_nums[i].innerHTML));
    }

    var pie_ctx = document.getElementById("userlog_pieChart").getContext('2d');
    var myPieChart = new Chart(pie_ctx, {
        type: 'pie',
        data: {
            labels: gender_item,
            datasets: [{
                label: "The gender distributions of registered users",
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)'],
                bordercolor: ['rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)'],
                borderWidth: 1,
                data: gender_num,
            }]
        },
        options: {
        }
    });
</script>
</body>
</html>
