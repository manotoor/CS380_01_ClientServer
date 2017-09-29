import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*THIS FILE WAS TAKEN FROM PRINCETON UNIVERSITY UNDER MIT LICENSE
 * 
 * STRICTLY FOR THE PURPOSE OF CS 380 NETWORKING EXERCISE
 * 
 * I HAVE TAKEN PART OF THE IN CLASS
 */


public final class In{
	
	private static final String CHARSET_NAME = "UTF-8";
	
	private static final Locale LOCALE = Locale.US;
	
	private Scanner scanner;
	//For User Input
	public In() {
		scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
		scanner.useLocale(LOCALE);
	}
	
	public In(Socket socket) {
		final Locale LOCALE = Locale.US;
		
		if (socket == null) throw new IllegalArgumentException("socket argument is null");
		try {
			InputStream is = socket.getInputStream();
			scanner = new Scanner(new BufferedInputStream(is), "UTF-8");
			scanner.useLocale(LOCALE);
		}catch (IOException ioe) {
			throw new IllegalArgumentException("Could not open " + socket, ioe);
		}
	}
	public boolean hasNextLine() {
		return scanner.hasNextLine();
	}
	
	public String readLine() {
		String line;
		try {
			line = scanner.nextLine();
		}catch(NoSuchElementException e) {
			line = null;
		}
		return line;
	}
	public void close() {
		scanner.close();
	}
}