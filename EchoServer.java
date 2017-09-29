import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                	String address = socket.getInetAddress().getHostAddress();
            		System.out.printf("Client connected: %s%n", address);
            		
            		//Talk back to Client
            		OutputStream os = socket.getOutputStream();
            		PrintStream out = new PrintStream(os, true, "UTF-8");
            		out.printf("Hi %s, thanks for connecting!%n", address);
            		out.println("Type 'Exit' to disconnect from server");
            		
            		//@in InputStream for getting input from client
            		In in = new In(socket);
            		
            		String s;
            		while((s = in.readLine()) != null) {
            			out.println(s);
                  	}

            		// close IO streams and socket
            		System.out.printf("Closing connection: %s%n", address);
            		out.close();
            		in.close();
            		socket.close();
                }
            }
        }
    }
}

