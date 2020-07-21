<!Doctype html>
<html lang='en'>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="bootstrap-4/css/bootstrap.css">
<script src="/em/js/MyJsLib.js"></script>
<script src="/em/js/DatabaseInstallation.js"></script>
</head>

<body style='background:#e2e2e2'>
<div class="container-fluid h-100">
<div class="row h-100 justify-content-center">
<!--DBInstallForm-->
<div class="col-sm-5" id='dbinstallform' style="margin:auto;padding-top:60px; max-width:300px">
<h3 style="text-align: center;">Database Detail Form</h3>
        <hr style="margin-bottom: 8px;">
            <div class="form-group">
                <label for="databaseNameInput" '>DataBase</label>
                <input type="text" class="form-control" id='dbNameInput' name='dbName' placeholder='Database Name'>
                <span id='dbNameError' style='color:red'></span>
            </div>
            <div class="form-group">
                <label for="serverInput">Server</label>
                <input type="text" class="form-control" id='serverInput' name="server" placeholder='127.0.0.1/host'>
                <span id='serverError' style='color:red'></span>
            </div>
            <div class="form-group">
                <label for="portInput">Port</label>
                <input type="text" class="form-control" id="portInput" name="port" placeholder="0000">
                <span id='portError' style='color:red'></span>
            </div>
            <div class="col-sm-12" style="text-align:center;padding-top:20px">
                <button class="btn btn-primary" style="margin:0px 10px" onclick='processData()'>Install</button>
            </div>
        </div>
<!--DBInstallForm-end-->

<!--AdminRegisterForm-->
<div class="col-sm-5" id='adminform' style="margin:auto;padding-top:60px; max-width:300px;display:none">
<h3 style="text-align: center;">Adminstrator Registor Form</h3>
        <hr style="margin-bottom: 8px;">
            <div class="form-group">
                <label for="adminNameInput" '>Admin Name</label>
                <input type="text" class="form-control" id='adminNameInput' name='adminName' placeholder=' Name '>
                <span id='adminNameError' style='color:red'></span>
            </div>
            <div class="form-group">
                <label for="passwordInput">Password</label>
                <input type='password' class="form-control" id='passwordInput' name='password' placeholder='Password'>
                <span id='passwordError' style='color:red'></span>
            </div>
            <div class="form-group">
                <label for="portInput">Re-Enter Password</label>
                <input type='password' class="form-control" id='rePasswordInput' name='rePassword' placeholder='Re-Type' oninput='matchPassword()'>
                <span id='passwordKeyError' style='color:red'></span>
            </div>
            <div class="col-sm-12" style="text-align:center;padding-top:20px">
                <button class="btn btn-primary" style="margin:0px 10px" onclick='processAdminData()'>Register</button>
            </div>
        </div>
<!--AdminRegisterForm-end-->
</div>
</div>
</body>
</html>