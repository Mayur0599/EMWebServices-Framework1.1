package com.thinking.machines.dao;
import java.lang.annotation.*;
import java.lang.reflect.*;

public class Service implements java.io.Serializable
{
private String path;
private Class c;
private Method method;
private Object obj;

public Service()
{
this.path="";
this.c=null;
this.method=null;
this.obj=null;
}

public void setPath(String path)
{
this.path=path;
}
public String getPath()
{
return this.path;
}

public void setClass(Class c)
{
this.c=c;
}
public Class getMyClass()
{
return this.c;
}

public void setMethod(Method method)
{
this.method=method;
}
public Method getMethod()
{
return this.method;
}

public void setInstance(Object obj)
{
this.obj=obj;
}
public Object getInstance()
{
return this.obj;
}
}
