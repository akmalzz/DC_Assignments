//UDPClient.java:
import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
	  //creates the object clientSocket of type DatagramSocket
      DatagramSocket clientSocket = new DatagramSocket();
      
      //It doesn't matter what port the client is bound to...
  
      //an input stream to the program; it is attached to the standard input, i.e., the keyboard
      BufferedReader inFromUser = 
						new BufferedReader(new InputStreamReader(System.in));
     
      // Determines the IP address of a host, given the host's name.
      InetAddress IPAddress = InetAddress.getByName("localhost");
      
      //Create a byte array buffer to store outgoing data
      byte[] sendData = new byte[1024];
      
      //Create a byte array buffer to store incoming data
      byte[] receiveData = new byte[1024];
      
      // get the data from console into string type object
      String sentence = inFromUser.readLine();
      
      //getBytes() encodes a given String into a sequence of bytes and returns an array of bytes.
      sendData = sentence.getBytes();
      
      // create datagram packet with appropriate data
      DatagramPacket sendPacket = 
					new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      
      // send datagram packet through client socket 
      clientSocket.send(sendPacket);
      
      //
      
      // wait till server response comes
      
      //
      
      // create a datagram packet to receive server's response of given length
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      
      // receive response datagram packet through client socket
      clientSocket.receive(receivePacket);
      
      // Return the data buffer using getData() to string type object
      // converting an array of bytes into the string
      String modifiedSentence = new String(receivePacket.getData());
      
      // display output to console
      System.out.println("FROM SERVER:" + modifiedSentence);
      
      // close socket connection
      clientSocket.close();
   }
}
