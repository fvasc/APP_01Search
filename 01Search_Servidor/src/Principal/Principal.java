package Principal;

import java.net.InetAddress;

import javax.xml.ws.Endpoint;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		InetAddress ia;  
		try {  
		    ia = InetAddress.getLocalHost();  

			String ip = ia.getHostAddress();
			
			String ip2 = "192.168.1.1";
			
			Endpoint.publish( "http://" + ip + ":8060/WebService/projeto", new ServidorWS());
			
			System.out.println(ip);
			
		} catch (Exception e) {  
		    e.printStackTrace();  
		}				
	}
	
}
