package com.thinking.machines.beans;
import java.io.*;
import java.util.*;
import java.math.*;
import com.thinking.machines.beans.*;
public class EmployeeDataBean implements java.io.Serializable
{
private String employeeId;
private String name;
private String gender;
private String dateOfBirth;
private Boolean isIndian;
private BigDecimal basicSalary;
private String panCardNumber;
private String aadharCardNumber;
private int designationCode;
private DesignationDataBean ddb;
public EmployeeDataBean()
{
this.employeeId="";
this.name="";
this.gender="";
this.dateOfBirth="";
this.isIndian=false;
this.basicSalary=null;
this.panCardNumber="";
this.aadharCardNumber="";
this.designationCode=0;
this.ddb=null;
}

public void setEmployeeId(String employeeId)
{
this.employeeId=employeeId;
}
public String getEmployeeId()
{
return this.employeeId;
}

public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}

public void setGender(String gender)
{
this.gender=gender;
}
public String getGender()
{
return this.gender;
}

public void setDateOfBirth(String dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public String getDateOfBirth()
{
return this.dateOfBirth;
}

public void setIsIndian(Boolean isIndian)
{
this.isIndian=isIndian;
}
public Boolean getIsIndian()
{
return this.isIndian;
}

public void setBasicSalary(BigDecimal basicSalary)
{
this.basicSalary=basicSalary;
}
public BigDecimal getBasicSalary()
{
return this.basicSalary;
}

public void setPanCardNumber(String panCardNumber)
{
this.panCardNumber=panCardNumber;
}
public String getPanCardNumber()
{
return this.panCardNumber;
}

public void setAadharCardNumber(String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public String getAadharCardNumber()
{
return this.aadharCardNumber;
}

public void setDesignationCode(int designationCode)
{
this.designationCode=designationCode;
}
public int getDesignationCode()
{
return this.designationCode;
}

public void setDesignationDataBean(DesignationDataBean ddb)
{
this.ddb=ddb;
}
public DesignationDataBean getDesignationDataBean()
{
return this.ddb;
}

}