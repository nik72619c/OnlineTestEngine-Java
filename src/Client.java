import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		Socket client= new Socket("localhost", 9999);
		Scanner scanner= new Scanner(System.in);
		System.out.println("enter the message to be sent to server....");
		String message= scanner.nextLine();
		
		OutputStream os=client.getOutputStream();
		DataOutputStream ds = new DataOutputStream(os);
		
		ds.writeUTF(message);
		
		ds.close();
		os.close();
		client.close();
	}

}
