package jmspublisher;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {


	private static final String NO_GREETING = "no greeting";

	private String clientId;
	private Connection connection;
	private MessageConsumer messageConsumer;

	public void create(String clientId, String topicName) throws JMSException {
		this.clientId = clientId;

		// create a Connection Factory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

		// create a Connection
		connection = connectionFactory.createConnection();
		connection.setClientID(clientId);

		// create a Session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// create the Topic from which messages will be received
		Topic topic = session.createTopic(topicName);

		// create a MessageConsumer for receiving messages
		messageConsumer = session.createConsumer(topic);

		// start the connection in order to receive messages
		connection.start();
	}

	public void closeConnection() throws JMSException {
		connection.close();
	}

	public String getGreeting(int timeout) throws JMSException {

		String greeting = NO_GREETING;

		// read a message from the topic destination
		Message message = messageConsumer.receive(timeout);

		// check if a message was received
		if (message != null) {
			// cast the message to the correct type
			TextMessage textMessage = (TextMessage) message;

			// retrieve the message content
			String text = textMessage.getText();
			System.out.println(clientId + ": received message with text='{}' : "+ text);

			// create greeting
			greeting = "Hello " + text + "!";
		} else {
			System.out.println(clientId + ": no message received");
		}

		System.out.println("greeting={}  : "+ greeting);
		return greeting;
	}
	public static void main(String[] args) throws JMSException {
		Subscriber subscriber1=new Subscriber();
		Subscriber subscriber2=new Subscriber();
		
		subscriber1.create("client2", "topic1");   //New client name should be used
		subscriber2.create("client3", "topic1");   //and same topic name
		
		subscriber1.getGreeting(30000);
		subscriber2.getGreeting(30000);
		
		subscriber1.closeConnection();
		subscriber2.closeConnection();
	}
}