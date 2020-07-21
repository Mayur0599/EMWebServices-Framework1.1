package com.thinking.machines.dao;
import java.sql.*;
public class DAOConnection
{
 private Connection c;
 private String connectionString;
public DAOConnection()
{
this.c=null;
this.connectionString="";
}
public void setConnectionString(String connectionString)
{
this.connectionString=connectionString;
}
public String getConnectionString()
{
return this.connectionString;
}

public  Connection getConnection() throws DAOException
{
try
{
Class.forName("org.apache.derby.jdbc.ClientDriver");
//this.c=DriverManager.getConnection("jdbc:derby://"+this.server+":"+this.port+"/"+this.dbName+";create="+this.type+"");
this.c=DriverManager.getConnection(getConnectionString());
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return this.c;
}
}