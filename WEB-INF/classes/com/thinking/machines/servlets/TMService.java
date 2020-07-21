package com.thinking.machines.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.lang.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import com.google.gson.*;
import com.thinking.machines.beans.*;
import com.thinking.machines.dao.*;
import com.thinking.machines.servlets.*;
import com.thinking.machines.annotations.*;

public class TMService extends HttpServlet
{
public static Map<String,Object> QueryParm=new HashMap<String,Object>();
public void doPost(HttpServletRequest request,HttpServletResponse response)
{ 
  Object ClassObj="";
  Object ParmObj="";
  Class c=null;
  Method m=null;
  Service service=null;
  String k="";
  String l="";
  String message="";
  String JSONString="";
  String type="";

try
{
    ServletContext sc=getServletContext();
    BufferedReader br=request.getReader();
    StringBuilder sb=new StringBuilder();
    String line;
    while(true)
    {
     line=br.readLine();
     if(line==null)break;
     sb.append(line);
    }
    String jsonString=sb.toString();    

    Gson gson= new Gson();

    String url=request.getRequestURI();
    if(request.getQueryString()!=null)
    {
     url+="?"+request.getQueryString();
    }
    int intIndex = url.indexOf("/service");
    if(intIndex == - 1) 
    {
    System.out.println("Not found");
    } else
     {
     int j=intIndex+8;
     for (int i = j; i < url.length(); i++) 
     {
      if(url.charAt(i)=='?')break;
      k=k+url.charAt(i);
     }
    }
   NumberWrapperBean nwb;
   Map URLMap=(Map)sc.getAttribute("URLMap");
  if(URLMap.containsKey(k))
 {
  service=(Service)URLMap.get(k);
  if(service.getInstance()==null)
  {
    c=service.getMyClass();
    ClassObj=c.newInstance();
    service.setInstance(ClassObj);
    m=service.getMethod(); 
    Parameter parameters[] = m.getParameters();
    for(Parameter p:parameters)
    {
     type=p.getType().toString();
     int x=type.indexOf(" ");
      String T="";
     int a=x+1;
     for(;a<type.length();a++)
     {
      T=T+type.charAt(a);
     }
     if(T.indexOf("thinking")==4)
     {
     Class t=Class.forName(T);
     ParmObj =gson.fromJson(jsonString,t);                    
     }
    }
    System.out.println(m.getName());
    if(parameters.length==2)
    {
    JSONString=(String)m.invoke(ClassObj,request,response);            
    }
    else
    {
    JSONString=(String)m.invoke(ClassObj,ParmObj,request,response);   
    }
   }
  else
  {
    ClassObj=service.getInstance();
    m=service.getMethod();
    Parameter parameters[] = m.getParameters();
    for(Parameter p:parameters)
    {
     type=p.getType().toString();
     int x=type.indexOf(" ");
      String T="";
     int a=x+1;
     for(;a<type.length();a++)
     {
      T=T+type.charAt(a);
     }
     if(T.indexOf("thinking")==4)
     {
     Class t=Class.forName(T);
     ParmObj =gson.fromJson(jsonString,t);                    
     }
    }
    System.out.println(m.getName());
    if(parameters.length==2)
    {
     JSONString=(String)m.invoke(ClassObj,request,response);            
    }
    else
    {
       JSONString=(String)m.invoke(ClassObj,ParmObj,request,response);  
    }          
  }
    service.setInstance(ClassObj);
    m=service.getMethod(); 
  response.setContentType("text/html");
  PrintWriter pw=response.getWriter();
  String ReturnType= m.getAnnotation(ResponseType.class).value();
  if(ReturnType.equals("Nothing"))pw.print(""); 
  if(ReturnType.equals("JSON"))pw.print(JSONString); 
  if(ReturnType.equals("html/text"))pw.print(message); 
}
else
{
System.out.println("Not Working ...");
}
}catch(Exception e)
{
//System.out.println(e);
e.printStackTrace();
}
}
}
