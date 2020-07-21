package com.thinking.machines.beans;
public class SquareAndFactorialBean implements java.io.Serializable
{
private int square;
private int factorial;
public SquareAndFactorialBean()
{
this.square=0;
this.factorial=0;
}
public void setSquare(int square)
{
this.square=square;
}
public int getSquare()
{
return square;
}
public void setFactorial(int factorial)
{
this.factorial=factorial;
}
public int getFactorial()
{
return factorial;
}
}