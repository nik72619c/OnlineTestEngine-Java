import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;

public class test {
	
	

	public static void main(String[] args) throws IOException {
		
		//ipadddress demo and practice
		
		// TODO Auto-generated method stub
//		InetAddress inet = InetAddress.getLocalHost();
//		System.out.println("inet received is "+ inet);
//		Scanner scanner= new Scanner(System.in);
//		System.out.println("enter a hostname ");
//		String hostname=scanner.next();
//		
//		InetAddress addresses[]=InetAddress.getAllByName(hostname);
//		for(InetAddress address: addresses) {
//			
//			System.out.println(address);
//		}
		
		//web scraping with java demo
		
		System.out.println("enter a webasite url");
		Scanner scanner= new Scanner(System.in);
		String urlStr= scanner.next();
		
		URL url= new URL(urlStr);
		
		URLConnection connection=url.openConnection();
		connection.connect();
		InputStream is=connection.getInputStream();
		BufferedInputStream bs= new BufferedInputStream(is);
		StringBuilder sb= new StringBuilder();
		sb.ensureCapacity(10);
		int singleByte=bs.read();
		
		while(singleByte!=-1) {
			
			sb.append((char)singleByte);
			singleByte=bs.read();
		}
		
		System.out.println("the html obtained is:-");
		FileWriter fw= new FileWriter("F:\\javaCodes\\TestEngine\\file.txt");
		fw.write(sb.toString());
		System.out.println("done");
		
		fw.close();
		bs.close();
		is.close();
		

	}

}
