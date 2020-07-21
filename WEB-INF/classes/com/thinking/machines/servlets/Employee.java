package com.thinking.machines.servlets;
import com.thinking.machines.annotations.*;
import com.thinking.machines.servlets.*;
import com.thinking.machines.beans.*;
import com.thinking.machines.dao.*;
import javax.servlet.annotation.*;
import java.lang.annotation.*;
import javax.servlet.http.*;
import java.lang.reflect.*;
import com.google.gson.*;
import javax.servlet.*;
import java.util.*;
import java.math.*;
import java.sql.*;
import java.io.*;

@Path("/employee")
public class Employee
{

@ResponseType("JSON")
@Path("/getEmployees")
public String getEmployees(HttpServletRequest request,HttpServletResponse response)
{
 String responseString="";
 try
 {
 ServletContext sc=request.getServletContext();
 EMModel emModel=(EMModel)sc.getAttribute("Model");
 DAOConnection daoConnection=emModel.getDAOConnection();
 Connection con=daoConnection.getConnection();
 ResultSet r;
 Statement s=con.createStatement();
 r=s.executeQuery("select * from employeeView");
 EmployeeDataBean employee;
 DesignationDataBean designation;
 List<EmployeeDataBean> employeeList=new ArrayList<EmployeeDataBean>();
 while(r.next())
 {
 String empId=r.getString("employee_id");
 String name=r.getString("name");
 String gender=r.getString("gender");
 String dob=r.getString("date_of_birth");
 Boolean indian=r.getBoolean("is_indian");
 BigDecimal basicSalary=r.getBigDecimal("basic_salary");
 String pan=r.getString("pan_card_number");
 String aadhar=r.getString("aadhar_card_number");
 int designationCode=r.getInt("designation_code");
 String title=r.getString("title");
 //System.out.println(empId+","+name+","+gender+","+dob+","+indian+","+basicSalary+","+pan+","+aadhar+","+designationCode+","+title);
 employee=new EmployeeDataBean();
 employee.setEmployeeId(empId);
 employee.setName(name);
 employee.setGender(gender);
 employee.setDateOfBirth(dob);
 employee.setIsIndian(indian);
 employee.setBasicSalary(basicSalary);
 employee.setPanCardNumber(pan);
 employee.setAadharCardNumber(aadhar);
 employee.setDesignationCode(designationCode);
 designation=new DesignationDataBean();
 designation.setCode(designationCode);
 designation.setTitle(title);
 employee.setDesignationDataBean(designation);
 employeeList.add(employee);
 }
 r.close(); 
 s.close();
 con.close();
 
  AJAXResponseBean ajaxResponse = new AJAXResponseBean();
  ajaxResponse.setResponse(employeeList);
  ajaxResponse.setSuccess(true);
  ajaxResponse.setException("");
  response.setContentType("application/json");
  PrintWriter pw=response.getWriter();
  Gson gson= new Gson();
  responseString=gson.toJson(ajaxResponse);
  return responseString;   
  }catch(Exception e)
  {
   e.printStackTrace();
  }
  return responseString;   
}

@ResponseType("JSON")
@Path("/createDB")
public String createDB(DataBaseBean dbb,HttpServletRequest request,HttpServletResponse response)
{
 String responseString="";
 try
 {
//Convert jsonstring to Object
 Gson gson= new Gson();
 String dbName=dbb.dbName;
 String server=dbb.server;
 int port=dbb.port;
 String connStr="jdbc:derby://"+server+":"+port+"/"+dbName+";create=true";

//placing model object in servletscontext object
 ServletContext sc=request.getServletContext();
 DAOConnection daoConnection=new DAOConnection();
 daoConnection.setConnectionString(connStr);
 Connection con=daoConnection.getConnection();

 EMModel emModel=new EMModel();
 emModel.setDAOConnection(daoConnection);
 sc.setAttribute("Model",emModel);
 sc.setAttribute("ConnectionString",connStr);
 String filename=request.getRealPath("");
 filename=filename+"WEB-INF"+File.separator+"script"+File.separator;
 int c;
 StringBuilder sbf=new StringBuilder();

//creating database 
 File f=new File(filename+"createTableStmt.sql");
 System.out.println("SqlFile Location:"+f.getCanonicalPath());
 if(f.exists()==false)
 {
 System.out.println("Invalid File");
 throw new Exception("Invalid File");
 }
 RandomAccessFile raf=new RandomAccessFile(f,"rw"); 
 if(raf.length()==0)
 {
 System.out.println("Invalid File name");
 throw new Exception("Invalid File");
 }
 while(raf.getFilePointer()<raf.length())
 {
 c=raf.read();
 if((char)c==';')
  {
   Statement s=con.createStatement();
   s.executeUpdate(sbf.toString());
   s.close();
   System.out.println("Table created");
   sbf=new StringBuilder();
   c=raf.read();
   if(c==-1)break;
  }
  sbf.append((char)c);
}
   con.close();

  AJAXResponseBean ajaxResponse = new AJAXResponseBean();
  ajaxResponse.setResponse("DataTable is Created..");
  ajaxResponse.setSuccess(true);
  ajaxResponse.setException("");
  response.setContentType("application/json");
  responseString=gson.toJson(ajaxResponse);
  return responseString;   
  }catch(Exception e)
  {
   e.printStackTrace();
  }
  return responseString;   
}

@ResponseType("JSON")
@Path("/registerAdmin")
public String registerAdmin(AdminDataBean adb,HttpServletRequest request,HttpServletResponse response)
{
 String responseString="";
 try
 {
//Convert jsonstring to Object
 Gson gson= new Gson();
 String name=adb.name.trim();
 String password=adb.password.trim();
  
//inserting detail
 ServletContext sc=request.getServletContext();
 System.out.println(sc.getAttribute("Model")); 
 EMModel emModel=(EMModel)sc.getAttribute("Model");
 String connStr=(String)sc.getAttribute("ConnectionString");
 emModel.getDAOConnection();
 DAOConnection daoConnection=emModel.getDAOConnection();
 Connection con=daoConnection.getConnection();
 Statement s=con.createStatement();
 s.executeUpdate("insert into adminstrator(username,password)values('"+name+"','"+password+"')");
 s.close();
 con.close();
 String filename=request.getRealPath("");
 filename=filename+"WEB-INF"+File.separator+"script"+File.separator;
 String fileName=filename+"DBCreate.dat";
 File f=new File(fileName);
 RandomAccessFile tmpraf=new RandomAccessFile(f,"rw");
 tmpraf.writeBytes(connStr);
 System.out.println("Database Setup");

//sending response
  AJAXResponseBean ajaxResponse = new AJAXResponseBean();
  ajaxResponse.setResponse("success Fully Registered..");
  ajaxResponse.setSuccess(true);
  ajaxResponse.setException("");

  response.setContentType("application/json");
  responseString=gson.toJson(ajaxResponse);
  return responseString;   
  }catch(Exception e)
  {
   e.printStackTrace();
  }
  return responseString;   
}


@ResponseType("JSON")
@Path("/getAdminLogin")
public String getAdminLogin(AdminDataBean adb,HttpServletRequest request,HttpServletResponse response)
{
 String responseString="";
 try
 {
//Convert jsonstring to Object
 Gson gson= new Gson();
 String name=adb.name.trim();
 String password=adb.password.trim();
//validating admin data
 ServletContext sc=request.getServletContext();
 EMModel emModel=(EMModel)sc.getAttribute("Model");
 emModel.getDAOConnection();
 DAOConnection daoConnection=emModel.getDAOConnection();
 Connection con=daoConnection.getConnection();
 ResultSet r;
 String UserName="";
 String Password="";
 Statement s=con.createStatement();
 r=s.executeQuery("select * from adminstrator where username='"+name+"'");
 while(r.next())
 {
 UserName=r.getString("username");
 Password=r.getString("Password");
 }
 r.close(); 
 s.close();
 con.close();

//sending response
 AJAXResponseBean ajaxResponse = new AJAXResponseBean();
 if(password.equals(Password))
 {
  ajaxResponse.setResponse("success Fully Registered..");
  ajaxResponse.setSuccess(true);
  ajaxResponse.setException("");
 }
 else
 {
  ajaxResponse.setResponse("Invalid Credencial..");
  ajaxResponse.setSuccess(false);
  ajaxResponse.setException("");
 }
  responseString=gson.toJson(ajaxResponse);
  return responseString;   
  }catch(Exception e)
  {
   e.printStackTrace();
  }
  return responseString;   
}

@ResponseType("JSON")
@Path("/addEmployee")
public String addEmployee(EmployeeDataBean employee,HttpServletRequest request,HttpServletResponse response)
{
 String responseString="";
 try
 {
 ServletContext sc=request.getServletContext();
 EMModel emModel=(EMModel)sc.getAttribute("Model");
 DAOConnection daoConnection=emModel.getDAOConnection();
 Connection con=daoConnection.getConnection();
 ResultSet r;
 Statement s=con.createStatement();
 r=s.executeQuery("select * from employeeView");
// EmployeeDataBean employee;
 DesignationDataBean designation;
 List<EmployeeDataBean> employeeList=new ArrayList<EmployeeDataBean>();
 while(r.next())
 {
 String empId=r.getString("employee_id");
 String name=r.getString("name");
 String gender=r.getString("gender");
 String dob=r.getString("date_of_birth");
 Boolean indian=r.getBoolean("is_indian");
 BigDecimal basicSalary=r.getBigDecimal("basic_salary");
 String pan=r.getString("pan_card_number");
 String aadhar=r.getString("aadhar_card_number");
 int designationCode=r.getInt("designation_code");
 String title=r.getString("title");
 //System.out.println(empId+","+name+","+gender+","+dob+","+indian+","+basicSalary+","+pan+","+aadhar+","+designationCode+","+title);
 employee=new EmployeeDataBean();
 employee.setEmployeeId(empId);
 employee.setName(name);
 employee.setGender(gender);
 employee.setDateOfBirth(dob);
 employee.setIsIndian(indian);
 employee.setBasicSalary(basicSalary);
 employee.setPanCardNumber(pan);
 employee.setAadharCardNumber(aadhar);
 employee.setDesignationCode(designationCode);
 designation=new DesignationDataBean();
 designation.setCode(designationCode);
 designation.setTitle(title);
 employee.setDesignationDataBean(designation);
 employeeList.add(employee);
 }
 r.close(); 
 s.close();
 con.close();
 
  AJAXResponseBean ajaxResponse = new AJAXResponseBean();
  ajaxResponse.setResponse(employeeList);
  ajaxResponse.setSuccess(true);
  ajaxResponse.setException("");
  response.setContentType("application/json");
  PrintWriter pw=response.getWriter();
  Gson gson= new Gson();
  responseString=gson.toJson(ajaxResponse);
  return responseString;   
  }catch(Exception e)
  {
   e.printStackTrace();
  }
  return responseString;   
}

@ResponseType("JSON")
@Path("/editEmployee")
public String editEmployee(EmployeeDataBean employee,HttpServletRequest request,HttpServletResponse response)
{
 String responseString="";
 try
 {
 ServletContext sc=request.getServletContext();
 EMModel emModel=(EMModel)sc.getAttribute("Model");
 DAOConnection daoConnection=emModel.getDAOConnection();
 Connection con=daoConnection.getConnection();
 ResultSet r;
 Statement s=con.createStatement();
 r=s.executeQuery("select * from employeeView");
// EmployeeDataBean employee;
 DesignationDataBean designation;
 List<EmployeeDataBean> employeeList=new ArrayList<EmployeeDataBean>();
 while(r.next())
 {
 String empId=r.getString("employee_id");
 String name=r.getString("name");
 String gender=r.getString("gender");
 String dob=r.getString("date_of_birth");
 Boolean indian=r.getBoolean("is_indian");
 BigDecimal basicSalary=r.getBigDecimal("basic_salary");
 String pan=r.getString("pan_card_number");
 String aadhar=r.getString("aadhar_card_number");
 int designationCode=r.getInt("designation_code");
 String title=r.getString("title");
 //System.out.println(empId+","+name+","+gender+","+dob+","+indian+","+basicSalary+","+pan+","+aadhar+","+designationCode+","+title);
 employee=new EmployeeDataBean();
 employee.setEmployeeId(empId);
 employee.setName(name);
 employee.setGender(gender);
 employee.setDateOfBirth(dob);
 employee.setIsIndian(indian);
 employee.setBasicSalary(basicSalary);
 employee.setPanCardNumber(pan);
 employee.setAadharCardNumber(aadhar);
 employee.setDesignationCode(designationCode);
 designation=new DesignationDataBean();
 designation.setCode(designationCode);
 designation.setTitle(title);
 employee.setDesignationDataBean(designation);
 employeeList.add(employee);
 }
 r.close(); 
 s.close();
 con.close();
 
  AJAXResponseBean ajaxResponse = new AJAXResponseBean();
  ajaxResponse.setResponse(employeeList);
  ajaxResponse.setSuccess(true);
  ajaxResponse.setException("");
  response.setContentType("application/json");
  PrintWriter pw=response.getWriter();
  Gson gson= new Gson();
  responseString=gson.toJson(ajaxResponse);
  return responseString;   
  }catch(Exception e)
  {
   e.printStackTrace();
  }
  return responseString;   
}

@ResponseType("JSON")
@Path("/deleteEmployee")
public String deleteEmployee(EmployeeDataBean employee,HttpServletRequest request,HttpServletResponse response)
{
 String responseString="";
 try
 {
 ServletContext sc=request.getServletContext();
 EMModel emModel=(EMModel)sc.getAttribute("Model");
 DAOConnection daoConnection=emModel.getDAOConnection();
 Connection con=daoConnection.getConnection();
 ResultSet r;
 Statement s=con.createStatement();
 r=s.executeQuery("select * from employeeView");
// EmployeeDataBean employee;
 DesignationDataBean designation;
 List<EmployeeDataBean> employeeList=new ArrayList<EmployeeDataBean>();
 while(r.next())
 {
 String empId=r.getString("employee_id");
 String name=r.getString("name");
 String gender=r.getString("gender");
 String dob=r.getString("date_of_birth");
 Boolean indian=r.getBoolean("is_indian");
 BigDecimal basicSalary=r.getBigDecimal("basic_salary");
 String pan=r.getString("pan_card_number");
 String aadhar=r.getString("aadhar_card_number");
 int designationCode=r.getInt("designation_code");
 String title=r.getString("title");
 //System.out.println(empId+","+name+","+gender+","+dob+","+indian+","+basicSalary+","+pan+","+aadhar+","+designationCode+","+title);
 employee=new EmployeeDataBean();
 employee.setEmployeeId(empId);
 employee.setName(name);
 employee.setGender(gender);
 employee.setDateOfBirth(dob);
 employee.setIsIndian(indian);
 employee.setBasicSalary(basicSalary);
 employee.setPanCardNumber(pan);
 employee.setAadharCardNumber(aadhar);
 employee.setDesignationCode(designationCode);
 designation=new DesignationDataBean();
 designation.setCode(designationCode);
 designation.setTitle(title);
 employee.setDesignationDataBean(designation);
 employeeList.add(employee);
 }
 r.close(); 
 s.close();
 con.close();
 
  AJAXResponseBean ajaxResponse = new AJAXResponseBean();
  ajaxResponse.setResponse(employeeList);
  ajaxResponse.setSuccess(true);
  ajaxResponse.setException("");
  response.setContentType("application/json");
  PrintWriter pw=response.getWriter();
  Gson gson= new Gson();
  responseString=gson.toJson(ajaxResponse);
  return responseString;   
  }catch(Exception e)
  {
   e.printStackTrace();
  }
  return responseString;   
}

@ResponseType("JSON")
@Path("/getEmployeeById")
public String getEmployeeById(EmployeeDataBean employee,HttpServletRequest request,HttpServletResponse response)
{
 String responseString="";
 try
 {
 ServletContext sc=request.getServletContext();
 EMModel emModel=(EMModel)sc.getAttribute("Model");
 DAOConnection daoConnection=emModel.getDAOConnection();
 Connection con=daoConnection.getConnection();
 ResultSet r;
 Statement s=con.createStatement();
 r=s.executeQuery("select * from employeeView");
// EmployeeDataBean employee;
 DesignationDataBean designation;
 List<EmployeeDataBean> employeeList=new ArrayList<EmployeeDataBean>();
 while(r.next())
 {
 String empId=r.getString("employee_id");
 String name=r.getString("name");
 String gender=r.getString("gender");
 String dob=r.getString("date_of_birth");
 Boolean indian=r.getBoolean("is_indian");
 BigDecimal basicSalary=r.getBigDecimal("basic_salary");
 String pan=r.getString("pan_card_number");
 String aadhar=r.getString("aadhar_card_number");
 int designationCode=r.getInt("designation_code");
 String title=r.getString("title");
 //System.out.println(empId+","+name+","+gender+","+dob+","+indian+","+basicSalary+","+pan+","+aadhar+","+designationCode+","+title);
 employee=new EmployeeDataBean();
 employee.setEmployeeId(empId);
 employee.setName(name);
 employee.setGender(gender);
 employee.setDateOfBirth(dob);
 employee.setIsIndian(indian);
 employee.setBasicSalary(basicSalary);
 employee.setPanCardNumber(pan);
 employee.setAadharCardNumber(aadhar);
 employee.setDesignationCode(designationCode);
 designation=new DesignationDataBean();
 designation.setCode(designationCode);
 designation.setTitle(title);
 employee.setDesignationDataBean(designation);
 employeeList.add(employee);
 }
 r.close(); 
 s.close();
 con.close();
 
  AJAXResponseBean ajaxResponse = new AJAXResponseBean();
  ajaxResponse.setResponse(employeeList);
  ajaxResponse.setSuccess(true);
  ajaxResponse.setException("");
  response.setContentType("application/json");
  PrintWriter pw=response.getWriter();
  Gson gson= new Gson();
  responseString=gson.toJson(ajaxResponse);
  return responseString;   
  }catch(Exception e)
  {
   e.printStackTrace();
  }
  return responseString;   
}

}
