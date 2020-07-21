package com.thinking.machines.servlets;
import com.thinking.machines.annotations.*;
import com.thinking.machines.servlets.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.thinking.machines.beans.*;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.thinking.machines.dao.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import java.lang.annotation.*;
import javax.servlet.http.*;
import java.lang.reflect.*;
import com.google.gson.*;
import javax.servlet.*;
import java.awt.Event;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.sql.*;
import java.io.*;


public class EMInitializer extends HttpServlet
{
 public static Map<String,Object> UrlMap=new HashMap<String,Object>();
 public static ArrayList<String> files=new ArrayList<String>();
 public static String path="";
 static void getAllClassWithPackage(File FileLists[],int index,int level)
 {  
  if(index == FileLists.length) return;
  if(FileLists[index].isFile())
  {  
   if(FileLists[index].getName().endsWith(".class"))
   {
    String pack=path.substring(39);
    String classname=pack.replace("\\",".")+"."+FileLists[index].getName().replace(".class","");
    files.add(new String(classname));
   }
  }
  else if(FileLists[index].isDirectory())
   {
   path="";
   path+=FileLists[index];
   getAllClassWithPackage(FileLists[index].listFiles(), 0, level + 1);
  }
  getAllClassWithPackage(FileLists,++index, level);
 }
public void init()
{
 try
 {
 Document document=new Document();
 PdfWriter pdfWriter=PdfWriter.getInstance(document,new FileOutputStream("c://tomcat-9//webapps//em//Documentation//services.pdf"));
 document.open();
 Font font=new Font(Font.FontFamily.TIMES_ROMAN,16,Font.ITALIC);
 Paragraph paragraph=null;
 Service service=null;
 Object ClassObj="";
 String l="";
 String k="";
 String message="";
 ServletContext sc=getServletContext();
 String filename=sc.getRealPath("");
 filename=filename+"WEB-INF"+File.separator+"script"+File.separator+"DBCreate.dat";
 File f=new File(filename);
 System.out.println("SqlFile Location:"+f.getCanonicalPath());
   if(f.exists()==false)
   {
   EMModel emModel=new EMModel();
   System.out.println(emModel.getDAOConnection());
   sc.setAttribute("Model",emModel);
   }
   else
   {
    int c;
    StringBuilder sb=new StringBuilder();
    f=new File(filename);
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
    if(c==-1)break;
    sb.append((char)c);
    }
    DAOConnection daoConnection=new DAOConnection();
    daoConnection.setConnectionString(sb.toString());
    Connection con=daoConnection.getConnection();
    EMModel emModel=new EMModel();
    emModel.setDAOConnection(daoConnection);
    sc.setAttribute("Model",emModel);
    System.out.println(sc.getAttribute("Model"));
    System.out.println(sb.toString());    
   }
 String folderLocation = sc.getRealPath("")+File.separator+"WEB-INF"+File.separator+"classes";
 File directoryPath = new File(folderLocation);
 if(directoryPath.exists() && directoryPath.isDirectory())
 {
  File FileLists[] = directoryPath.listFiles();
  getAllClassWithPackage(FileLists,0,0);
  for(String fname: files)
  {
     Class c=Class.forName(fname);
     Annotation annotations[] =c.getAnnotations();
    for(Annotation annotation: annotations)
    {
      l="";
     if(annotation instanceof Path)
     {
      Path P=(Path)annotation;
      l=l+P.value();
      Method methods[]=c.getMethods();
      for(Method m:methods)
      {
       if(annotation instanceof Path)
       {
        if(m.getName().equals("wait"))break;
        String ml="";
        ml=ml+m.getAnnotation(Path.class).value();
        l=l+ml;
//print
        paragraph=new Paragraph("Class:"+c.getName(),font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        System.out.println(m.getName());
        paragraph=new Paragraph("Service:"+m.getName(),font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        System.out.println(l);
        paragraph=new Paragraph("URL:"+l,font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        Parameter parameters[] = m.getParameters();
        for(Parameter p:parameters)
        {
        paragraph=new Paragraph("Parameter:"+p.getType().toString(),font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        }    
        paragraph=new Paragraph("ReturnType:"+m.getReturnType(),font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);

        paragraph=new Paragraph("------------------------------------",font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
//
        service=new Service();
        service.setPath(l);
        service.setClass(c);
        service.setMethod(m);  
        UrlMap.put(l,service);
        int intIndex = l.indexOf(ml);
        if(intIndex != - 1) 
        {
        int j=intIndex;
        for (int i = 0; i < j; i++) 
        {
         k=k+l.charAt(i);
        }
       }
        l=k;
        k="";
        ml="";              
       }     
      }
     }
      sc.setAttribute("URLMap",UrlMap);
    }
   }
  }
 document.close();
}catch(Exception e)
 {
 System.out.println(e);
 }
}
}