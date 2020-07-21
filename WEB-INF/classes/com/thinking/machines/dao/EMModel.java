package com.thinking.machines.dao;
import com.thinking.machines.dao.*;
public class EMModel
{
private  DAOConnection daoConnection;
public EMModel()
{
daoConnection=null;
}
public void setDAOConnection(DAOConnection daoConnection)
{
this.daoConnection=daoConnection;
}
public DAOConnection getDAOConnection()
{
return this.daoConnection;
}
}
