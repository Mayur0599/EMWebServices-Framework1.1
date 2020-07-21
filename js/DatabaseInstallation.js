function matchPassword()
{
document.getElementById('passwordKeyError').innerHTML="";
var pw=document.getElementById('passwordInput').value;
var rw=document.getElementById('rePasswordInput').value;
 console.log(rw);
if(rw!=pw)
{
 document.getElementById('passwordKeyError').innerHTML="ReEnter Password Does'nt Match";
 return 0;
}
return 1;
}
function validateDB()
{
document.getElementById('dbNameError').innerHTML="";
document.getElementById('serverError').innerHTML="";
document.getElementById('portError').innerHTML="";
var dbn=document.getElementById('dbNameInput').value;
if(dbn.length==0)
{
document.getElementById('dbNameError').innerHTML="Required";
return 0;
}
var s=document.getElementById('serverInput').value;
if(s.length==0)
{
document.getElementById('serverError').innerHTML="Required";
return 0;
}
var p=document.getElementById('portInput').value;
if(p.length==0)
{
document.getElementById('portError').innerHTML="Required";
return 0;
}
return 1;
}
function validateAdmin()
{
document.getElementById('adminNameError').innerHTML="";
document.getElementById('passwordError').innerHTML="";
var an=document.getElementById('adminNameInput').value;
if(an.length==0)
{
document.getElementById('adminNameError').innerHTML="Required";
return 0;
}
var pw=document.getElementById('passwordInput').value;
if(pw.length==0)
{
document.getElementById('passwordError').innerHTML="Required";
return 0;
}
if(matchPassword()==0)return 0;
return 1;
}
function AdminDataWrapper()
{
this.name='';
this.password='';
}
function processAdminData()
{
if(validateAdmin()==0)return 0;
var adw=new AdminDataWrapper();
adw.name=$$$("adminNameInput").val();
adw.password=$$$('passwordInput').val();
$$$.postJSON({
"url":"/em/service/employee/registerAdmin",
"success":function(response)
{
 if(response.success)
 {
    alert(response.response);
   window.location='/em/index.jsp';
 }
 else 
  {
       alert(response.response);
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
function DatabaseWrapper()
{
this.dbName='';
this.server='';
this.port=0;
}
function processData()
{
if(validateDB()==0)return;
var dbw=new DatabaseWrapper();
dbw.dbName=$$$("dbNameInput").val();
dbw.server=$$$("serverInput").val();
dbw.port=$$$("portInput").val();
$$$.postJSON({
"url":"/em/service/employee/createDB",
"success":function(response)
{
 if(response.success)
 {
 alert(response.response);
 var dbinstallform=document.getElementById('dbinstallform');
 dbinstallform.style.display='none'; 
 var adminform=document.getElementById('adminform');
 adminform.style.display='block'; 
 }
  else
  {
    alert(response.response);
   return;
  }
},
"data":dbw,
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
