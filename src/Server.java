import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		final int PORT=9999;
		ServerSocket server= new ServerSocket(PORT);
		System.out.println("wating fo client to connect");
		
		Socket socket=server.accept();
		System.out.println("client connected");
		
		InputStream is = socket.getInputStream();
		DataInputStream di= new DataInputStream(is);
		System.out.println("hault here");
		System.out.println(di.readUTF());
		
		di.close();
		is.close();
		socket.close();

	}

}
