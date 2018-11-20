package chatgroup2;


import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.io.IOException;

public class MulticastSniffer {

	public static void main(String[] args) {

		InetAddress ia = null;
		final byte[] buffer = new byte[65509];
		final DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		int port = 12345;
		String group = "224.0.0.1";

		// read the address from the command line
		try {
			ia = InetAddress.getByName(group);
		} catch (Exception e) {
			System.err.println(e);
			System.err.println("Usage: java MulticastSniffer MulticastAddress port");
			System.exit(1);
		}

		try {
			MulticastSocket ms = new MulticastSocket();
			ms.joinGroup(ia);
			while (true) {
				ms.receive(dp);
				final String s = new String(dp.getData(), 0, dp.getLength());
				System.out.println(s);
			}
		} catch (SocketException se) {
			System.err.println(se);
		} catch (IOException ie) {
			System.err.println(ie);
		}
	}
}