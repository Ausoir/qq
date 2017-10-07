package connection;

import java.net.InetAddress;
import java.net.SocketTimeoutException;

import connection.UDPClient;

public class Receiver implements Host {
	private UDPClient udp;
	private InetAddress ip;
	public Receiver(UDPClient pudp){
		udp = pudp;
	}
	public String receive(){
		String cmd = null;
		try{
			cmd = udp.receive();
			ip = udp.getIP();
//			System.out.println("Receiver.receive(): ip:"+udp.getIP()+" Cmd:"+cmd); //must go after receive()
		}catch (SocketTimeoutException e){
			System.out.println("Receiver: Socket timed out. Set to ignore?");
			System.exit(1);
		}catch (Exception e){
			System.out.println("Receiver: UDP packet receiving failed. Set to ignore?");
			System.exit(1);
		}
		//System.out.println(cmd);
		return cmd;
	}
	public InetAddress getIp(){
		return ip;
	}
}