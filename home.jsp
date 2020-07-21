<!Doctype html>
<html lang='en'>
<head >
<meta charset='utf-8'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel='stylesheet' href="/em/bootstrap-4/css/bootstrap.css">
<title>Employees</title>
<!--client code-->
<script src="/em/js/admin_logout.js"></script>
<script src='/em/js/MyJsLib.js'></script>
<script src='/em/js/home.js'></script>

</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top" >
  <a class="navbar-brand" href="#">Employee Manager</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li id ="manageEmployeesTab" class="nav-item">
        <a class="nav-link" href="home.jsp">Manage Employees</a>
      </li>
      <li id="hireEmployeeTab" class="nav-item">
        <a class="nav-link" href="employee_form.php">Hire Employee</a>
      </li>
      <li id="manageBillsTab" class="nav-item">
        <a class="nav-link" href="manage_bills.php">Manage Bills</a>
      </li>
    </ul>
      <button class="btn btn-outline-warning" onclick = "logoutAdmin()">Logout</button>
  </div>
</nav>
<h4  style='margin-top:70px;margin-left:80px;text-align: center;'>View Employees</h4>
<hr margin-bottom: 8px;>
<div style="margin-top:50px; margin-left:25px;">
<span id='tableSpan' style='margin-top:70px;margin-left:80px;text-align: center;color:red'></span>
<table  id='employeeTable' class="table">
  <thead>
    <tr>
      <th scope="col">Sno</th>
      <th scope="col">Emp_Id</th>
      <th scope="col">Name</th>
      <th scope="col">Title</th>
      <th scope="col">Salary</th>
      <th scope="col">Gender</th>
      <th scope="col">Detail</th>
      <th scope="col">Edit</th>
      <th scope="col">Delete</th>
    </tr>
  </thead>
  <tbody>
  </tbody>
</table>
<!--<button type='button' class="btn- btn-primary" onclick='viewEmployees()'>View Employees</button>-->
</div>
</body>
</html>