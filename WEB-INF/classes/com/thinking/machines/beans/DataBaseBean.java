package com.thinking.machines.beans;
public class DataBaseBean implements java.io.Serializable
{
public String dbName;
public String server;
public int port;
public DataBaseBean()
{
this.dbName="";
this.server="";
this.port=0;
}
public void setDBName(String dbName)
{
this.dbName=dbName;
}
public String getDBName()
{
return this.dbName;
}
public void setServer(String server)
{
this.server=server;
}
public String getServer()
{
return this.server;
}
public void setPort(int port)
{
this.port=port;
}
public int getPort()
{
return this.port;
}
}