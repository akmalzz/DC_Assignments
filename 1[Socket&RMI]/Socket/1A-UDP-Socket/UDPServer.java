//UDPServer.java
import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         // constructs the DatagramSocket serverSocket at port 9876
         DatagramSocket serverSocket = new DatagramSocket(9876);
         
         //Create a byte array buffer to store incoming data
            byte[] receiveData = new byte[1024];
            
         //Create a byte array buffer to store outgoing data
            byte[] sendData = new byte[1024];
            
            while(true)
               {
				   
	// create a datagram packet to receive clients's request of given length
                  DatagramPacket receivePacket = 
					new DatagramPacket(receiveData, receiveData.length);
    
    // receive packet through server side socket 
                  serverSocket.receive(receivePacket);
                  
    // Return the data buffer using getData() to string type object
    // converting an array of bytes into the string
                  String sentence = new String( receivePacket.getData());
                  
    // display contents recieved on console
                  System.out.println("RECEIVED: " + sentence);
                  
    // extracts the IP address of client
                  InetAddress IPAddress = receivePacket.getAddress();
                  
    // extracts the client port number 
    // which is chosen by the client and is different from the server port number 9876
                  int port = receivePacket.getPort();
                  
    // convert into uppercase 
                  String capitalizedSentence = sentence.toUpperCase();
                  
    // getBytes() encodes a given String into a sequence of bytes and returns an array of bytes.
                  sendData = capitalizedSentence.getBytes();
   
    // create datagram packet with appropriate data
                  DatagramPacket sendPacket = 
          new DatagramPacket(sendData, sendData.length, IPAddress, port);
    
    // send datagram packet to client containing response
                  serverSocket.send(sendPacket);               
				serverSocket.close();
               }
    // close socket connection
      
      }
      
}
