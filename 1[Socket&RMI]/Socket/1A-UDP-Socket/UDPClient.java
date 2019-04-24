//UDPClient.java:
import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
     DatagramSocket clientSocket = new DatagramSocket();
     byte[] sendData = new byte[1024];
     byte[] receiveData = new byte[1024];

     BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
     InetAddress IPAddress = InetAddress.getByName("localhost");

      String sentence = inFromUser.readLine();//pune
      sendData = sentence.getBytes(); //con*

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      clientSocket.send(sendPacket);


      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.recieve(receivePacket);

      String modifiedSentence = new String(receivePacket.getData());

      System.out.println("FROM SERVER:" + modifiedSentence);

      clientSocket.close();    

   }
}
