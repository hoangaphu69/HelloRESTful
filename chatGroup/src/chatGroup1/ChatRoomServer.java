package chatGroup1;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatRoomServer {
	private static String message = "";

	public static void main(String[] args) {
		try {
			final ServerSocket svr = new ServerSocket(10008);
			System.out.println("Chatroom server starting...");
			final ArrayList<Socket> listSoc = new ArrayList<Socket>();
			while (true) {
				final Socket soc = svr.accept();
				System.out.println ("Connection Socket Created");
				listSoc.add(soc);
				System.out.println(soc);
				System.out.println("number of client is " + listSoc.size());
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Scanner in = new Scanner(soc.getInputStream());
							while (in.hasNextLine()) {
								message = in.nextLine();
								for (Socket s : listSoc) {
									if (s == null)
										listSoc.remove(s);
									else if (!s.equals(soc)) {
										PrintWriter out = new PrintWriter(
												s.getOutputStream(), true);
										out.println(message);
									}
								}
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
