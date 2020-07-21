package com.thinking.machines.tags;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import com.thinking.machines.dao.*;

public class DatabaseInstallationRequiredTagHandler extends TagSupport
{
public int doStartTag() throws JspException
{
 try
 { 
   HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
   JspWriter out=pageContext.getOut();

   String filename=request.getRealPath("");
   filename=filename+"WEB-INF"+File.separator+"script"+File.separator+"DBCreate.dat";
   File f=new File(filename);
   if(f.exists())
   {
     out.println("<h1>Employee Management<h1>");  
     return SKIP_BODY;
   }
  else
  {
   System.out.println(request.getAttribute("Model"));
   if(request.getAttribute("Model")!=null)
   {
     out.println("<h1>Hello <h1>");  
     return SKIP_BODY;
   }
  }
 }catch(Exception e)
 {
  e.printStackTrace();
 }
 return EVAL_BODY_INCLUDE;
}
public int doEndTag()
{
return EVAL_PAGE;
}
}