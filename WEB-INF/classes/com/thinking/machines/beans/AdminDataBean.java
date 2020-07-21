package com.thinking.machines.beans;
public class AdminDataBean implements java.io.Serializable
{
public String name;
public String password;
public AdminDataBean()
{
this.name="";
this.password="";
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setPassword(String password)
{
this.password=password;
}
public String getPassword()
{
return this.password;
}
}