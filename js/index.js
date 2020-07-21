var xhr=new XMLHttpRequest;
function AdminDataWrapper()
{
this.name='';
this.password='';
}
function loginEmployee()
{
    var employeeUserName = document.getElementById("employeeUserNameInput").value.trim();
    var employeePassword = document.getElementById("employeePasswordInput").value.trim();
    if(!employeeUserName && !employeePassword )
    {
        document.getElementById('employeeLoginError').innerHTML = "Username & Password Required";
        return ;
    }
    if(!employeePassword)
    {
        document.getElementById('employeeLoginError').innerHTML = "Password Required";
        return ;
    }
    if(!employeeUserName)
    {
        document.getElementById('employeeLoginError').innerHTML = "Username Required";
        return ;
    }
    document.getElementById('employeeLoginError').innerHTML ="";
    xhr.onreadystatechange = function(){
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var response = JSON.parse(this.responseText);
            if(response.success){
                window.location = 'employee_profile.php';           
            }
            else{
                document.getElementById('employeeLoginError').innerHTML = response.payload;
            }
        } 
    }
    xhr.open("GET", "login_Employee.php?" + encodeURI('employeeUserName='+employeeUserName + "&employeePassword=" + employeePassword), true);
    xhr.send();

}
function loginAdmin()
{
    var adminUserName = document.getElementById("adminUserNameInput").value.trim();
    var adminPassword = document.getElementById("adminPasswordInput").value.trim();
    if(!adminUserName && !adminPassword )
    {
        document.getElementById('adminLoginError').innerHTML = "Username & Password Required";
        return ;
    }
    if(!adminUserName){
        document.getElementById('adminLoginError').innerHTML = "Username Required";
        return;
    }
    if(!adminPassword){
        document.getElementById('adminLoginError').innerHTML = "Password Required";
        return;
    }
    document.getElementById('adminLoginError').innerHTML = "";
var adw=new AdminDataWrapper();
adw.name=$$$("adminUserNameInput").val();
adw.password=$$$("adminPasswordInput").val();
$$$.postJSON(
{
"url":"/em/service/employee/getAdminLogin",
"success":function(response)
{
 if(response.success)
 {
   alert(response.response);
   window.location="/em/home.jsp";
 }
 else
  {
   document.getElementById('adminLoginError').innerHTML = response.response ;
   return;
  }
},
"data":adw,
"exception":function(exception)
{
alert(exception);
},
"failed":function()
{
alert("unable to load page due to server issue");
}
});
}