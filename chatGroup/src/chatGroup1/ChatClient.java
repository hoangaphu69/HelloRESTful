package chatGroup1;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatClient {
	public static void main(String[] args) {
		try {
			final Socket soc = new Socket("localhost", 10008);
			System.out.println ("Attemping to connect to host " +
					soc.getInetAddress().getHostName() + " on port " + soc.getPort());
			System.out.println ("Type Message (\"bye.\" to quit)");
			while (true) {

				new Thread(new Runnable() {
					Scanner sc = new Scanner(System.in);
					String msg = sc.nextLine();

					@Override
					public void run() {
						try {
							if (msg.equalsIgnoreCase("bye"))
								System.exit(1);
							PrintWriter out = new PrintWriter(
									soc.getOutputStream(), true);
							out.println(msg);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Scanner in = new Scanner(soc.getInputStream());
							while (in.hasNextLine()) {
								System.out.println("Client : "+ in.nextLine());
							}
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

