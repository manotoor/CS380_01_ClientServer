
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;


public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
        	//User Input
            In userInput = new In();
            
            //Input from server
            In in = new In(socket);
            
            //Output to server
            OutputStream os = socket.getOutputStream();
    		PrintStream out = new PrintStream(os, true, "UTF-8");
            
    		//First read through of server
    		System.out.println(in.readLine());
    		System.out.println(in.readLine());

            
            //While no user input
            while(true) {
            	//read from client
            	String s = userInput.readLine();
            	if(s.equalsIgnoreCase("exit")) break;
            	//Send over to socket
            	out.println(s);
            	
            	//get reply from server and print it out
            	System.out.println("Server: " + in.readLine());
            }
        }catch (IOException e) {
        	System.out.println(e);
        }

    }
}















