function employee()
{
this.employeeid="";
this.name="";
this.gender="";
this.dateOfBirth="";
this.isIndian=false;
this.basicSalary="";
this.panCardNumber="";
this.aadharCardNumber="";
this.designationCode="";
this.title="";
}
function Service()
{
this.getService=function(success,failure)
{
$$$.postJSON({
"url" : "/em/service/employee/getEmployees",
"success" : success,
"exception" : function(exception)
{
alert(exception);
},
"failed" : failure
});
}
}
function viewEmployees()
{
var s=new Service();
s.getService(success,failure);
}
function createDeleteEventHandler(x)
{
alert(x);
}

function success(ResponseData)
{
var EmployeeData=ResponseData.response;
if(!EmployeeData.length)
{
$$$('tableSpan').html("No Record Found");
}
else
{
i=0;
tableBody=document.getElementById('employeeTable');
tableBody.classList.add("table-hover"); 
while(i<EmployeeData.length)
{
tr = document.createElement('tr');
tr.id=EmployeeData[i].employeeId;

 td = document.createElement('td');
 textnode=document.createTextNode(i+1);
 td.appendChild(textnode);
 tr.append(td);

 td = document.createElement('td');
 textnode=document.createTextNode(EmployeeData[i].employeeId);
 td.appendChild(textnode);
 tr.append(td);
 
 td = document.createElement('td');
 textnode=document.createTextNode(EmployeeData[i].name);
 td.appendChild(textnode);
 tr.append(td);

 td = document.createElement('td');
 textnode=document.createTextNode(EmployeeData[i].ddb.title);
 td.appendChild(textnode);
 tr.append(td);

 td = document.createElement('td');
 textnode=document.createTextNode(EmployeeData[i].basicSalary);
 td.appendChild(textnode);
 tr.append(td);

 td = document.createElement('td');
 textnode=document.createTextNode(EmployeeData[i].gender);
 td.appendChild(textnode);
 tr.append(td);

 td=document.createElement('td');
 btn=document.createElement('input');
 btn.setAttribute('type','button');
 //btn.setAttribute('onclick','createDetailEventHandler(this)');
 btn.setAttribute('value','Detail');
 td.appendChild(btn);
 tr.append(td);

 td=document.createElement('td');
 btn=document.createElement('input');
 btn.setAttribute('type','button');
 //btn.setAttribute('onclick','createEditEventHandler(i)');
 btn.setAttribute('value','edit');
 td.appendChild(btn);
 tr.append(td);

 td=document.createElement('td');
 btn=document.createElement('input');
 btn.setAttribute('type','button');
 //btn.setAttribute('onclick','createDeleteEventHandler(i)');
 btn.setAttribute('value','Delete');
 td.appendChild(btn);
 tr.append(td);

 tableBody.appendChild(tr);
 i++;
}
}
}
function failure()
{
alert("A failure has occured");
}
window.addEventListener("load",viewEmployees);
