import mpi.*;

public class MPI_Scatter_Gather_Demo 
{
    public static void main(String[] args) throws Exception 
    {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank() ;
        int size = MPI.COMM_WORLD.Size() ; 
        
        int unitsize=4;
        int root=0;     
		int send_buffer [] = null;

		//if (rank == root) 
	//	{
			send_buffer = new int [unitsize * size];
	//	}
		
		int recv_buffer[] = new int[unitsize];
		
		
		MPI.COMM_WORLD.Scatter(
		/*java.lang.Object sendbuf*/	send_buffer,
		/*int sendoffset*/				0,
		/*int sendcount*/				unitsize,
		/*Datatype sendtype*/			MPI.INT,
		/*java.lang.Object recvbuf*/	recv_buffer,
		/*int recvoffset*/				0,
		/*int recvcount*/				unitsize,
		/*Datatype recvtype*/			MPI.INT,
		/*int root*/					root
		);
/*
Inverse of the operation Gather.
sendbuf			send buffer array
sendoffset		initial offset in send buffer
sendcount		number of items to send
sendtype		datatype of each item in send buffer
recvbuf			receive buffer array
recvoffset		initial offset in receive buffer
recvcount		number of items to receive
recvtype		datatype of each item in receive buffer
root			rank of sending process
*/		
		if(rank != root)
		{
			for(int i=0; i<unitsize; i++)
			{
				recv_buffer[i] = rank; 		// recv_buffer[0] = 0 ... n-1	
			}
		}
		
		MPI.COMM_WORLD.Gather(
		  /*java.lang.Object sendbuf,*/	recv_buffer,
          /*int sendoffset,*/			0,
          /*int sendcount,*/			unitsize,
          /*Datatype sendtype,*/		MPI.INT,
          /*java.lang.Object recvbuf,*/ send_buffer,
          /*int recvoffset,*/			0,
          /*int recvcount,*/			unitsize,
          /*Datatype recvtype,*/		MPI.INT,
          /*int root*/					root
           );
		
/*
Each process sends the contents of its send buffer to the root process.
sendbuf			send buffer array
sendoffset		initial offset in send buffer
sendcount		number of items to send
sendtype		datatype of each item in send buffer
recvbuf			receive buffer array
recvoffset		initial offset in receive buffer
recvcount		number of items to receive
recvtype		datatype of each item in receive buffer
root			rank of receiving process
*/		
		if(rank == root)
		{
			for(int i=0; i < (unitsize * size); i++)
			{
				System.out.println(send_buffer[i] + " "); 			
			}
		}		
        MPI.Finalize();
    }   
}

/*
comp2@comp2:~/Desktop/mpj-user-sss$ javac -cp /home/comp2/Desktop/mpj-v0_44/lib/mpj.jar MPI_Scatter_Gather_Demo.java 

comp2@comp2:~/Desktop/mpj-user-sss$ mpjrun.sh -np 4 MPI_Scatter_Gather_Demo

MPJ Express (0.44) is started in the multicore configuration
0 0 0 0 1 1 1 1 2 2 2 2 3 3 3 3 
*/
