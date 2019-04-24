import java.rmi.*;
import java.io.*;

public class HelloClient
{
  public static void main (String[] argv) 
  {
    try 
    {
      HelloInterface hello = (HelloInterface) Naming.lookup("//localhost/Hello");
      System.out.println(hello.say());
    }
    catch (Exception e) 
    {
      System.out.println ("HelloClient exception: " + e);
    }
  }
}
