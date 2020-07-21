<%@taglib uri='/WEB-INF/tlds/mytags.tld' prefix='t' %>
<t:isdatabaseExist>
<jsp:forward page='/databaseInstallation.jsp'/>
</t:isdatabaseExist>

<!doctype html>
<html lang='en'>
<head>
<title>Em</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title></title>
<link rel="stylesheet" href="bootstrap-4/css/bootstrap.css">
<link rel="stylesheet" href="/em/css/index.css">
<script src='/em/js/MyJsLib.js'></script>
<script src='/em/js/index.js'></script>
</head>
<body>

<div class="container-fluid h-100">
<div class="row h-100 justify-content-center">
<!--employee-login-form-->
<div class="col-sm-5 indexempDiv" >
<h5 class="empH">Employee Login</h5>
        <hr class="emphr">
            <div id="employeeLoginError" class="text-danger error empDiverror"></div>
            <div class="form-group">
                <label for="userNameInput" '>User Name</label>
                <input type="text" class="form-control" id="employeeUserNameInput" name="userName" placeholder="User Name">
            </div>
            <div class="form-group">
                <label for="passwordInput">Password</label>
                <input type="password" class="form-control" id="employeePasswordInput" name="password" placeholder="Password">
            </div>
            <div class="col-sm-12 emp-btn-Div">
                <button class="btn btn-primary emp-btn" onclick="loginEmployee()">Login</button>
                <div id="employeeLoginError" class="text-danger error" style="text-align:center"></div>
            </div> 
</div>
<!--employee-login-form-end-->

<!--admin-login-form-->
<div class="col-sm-5 indexadminDiv">
        <h5 class='adminH'>Administrator Login</h5>
        <hr class='adminhr'>

            <div id="adminLoginError" class="text-danger error" style="text-align:center"></div>
            <div class="form-group">
                <label for="userNameInput" >User Name</label>
                <input type="text" class="form-control" id="adminUserNameInput" placeholder="Admin User Name">
            </div>
            <div class="form-group">
                <label for="passwordInput">Password</label>
                <input type="password" class="form-control" id="adminPasswordInput" placeholder="Admin Password">
            </div>
            <div class="col-sm-12 admin-btn-Div" >
                <button class="btn btn-primary admin-login-btn"  onclick="loginAdmin()">Login</button>
            </div>
</div>
<!--admin-login-form-end-->

</div>
</div>
</body>
</html>