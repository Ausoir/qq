package connection;

import java.net.InetAddress;

public interface Host {
	public String receive();
	public InetAddress getIp();
}
