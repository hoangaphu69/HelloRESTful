package chatgroup2;
//package socket.MulticastSocket.WindowChat;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class MulticastChat implements Runnable, WindowListener, ActionListener {
	protected InetAddress group;
	protected int port;

	public MulticastChat(InetAddress group, int port) {
		this.group = group;
		this.port = port;
		initAWT();
	}

	protected Frame frame;
	protected TextArea output;
	protected TextField input;

	protected void initAWT() {
		frame = new Frame("MulticastChat [" + group.getHostAddress() + ":" + port + "]");
		frame.addWindowListener(this);
		output = new TextArea();
		output.setEditable(false);
		input = new TextField();
		input.addActionListener(this);
		frame.setLayout(new BorderLayout());
		frame.add(output, "Center");
		frame.add(input, "South");
		frame.pack();
	}

	protected Thread listener;

	public synchronized void start() throws IOException {
		if (listener == null) {
			initNet();
			listener = new Thread(this);
			listener.start();
			frame.setVisible(true);
		}
	}

	protected MulticastSocket socket;
	protected DatagramPacket outgoing, incoming;

	protected void initNet() throws IOException {
		socket = new MulticastSocket(port);
		socket.setTimeToLive(5);
		socket.joinGroup(group);
		outgoing = new DatagramPacket(new byte[1], 1, group, port);
		incoming = new DatagramPacket(new byte[65508], 65508);
	}

	public synchronized void stop() throws IOException {
		frame.setVisible(false);
		if (listener != null) {
			listener.interrupt();
			listener = null;
			try {
				socket.leaveGroup(group);
			} finally {
				socket.close();
			}
		}
	}

	public void windowOpened(WindowEvent event) {
		input.requestFocus();
	}

	public void windowClosing(WindowEvent event) {
		try {
			stop();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void windowClosed(WindowEvent event) {}
	public void windowIconified(WindowEvent event) {}
	public void windowDeiconified(WindowEvent event) {}
	public void windowActivated(WindowEvent event) {}
	public void windowDeactivated(WindowEvent event) {}

	public void actionPerformed(ActionEvent event) {
		try {
			byte[] utf = event.getActionCommand().getBytes("UTF8");
			outgoing.setData(utf);
			outgoing.setLength(utf.length);
			socket.send(outgoing);
			input.setText("");
		} catch (IOException ex) {
			handleIOException(ex);
		}
	}

	protected synchronized void handleIOException(IOException ex) {
		if (listener != null) {
			output.append(ex + "\n");
			input.setVisible(false);
			frame.validate();
			if (listener != Thread.currentThread()) listener.interrupt();
			listener = null;
			try {
				socket.leaveGroup(group);
			} catch (IOException ignored) {}
			socket.close();
		}
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				incoming.setLength(incoming.getData().length);
				socket.receive(incoming);
				String message = new String(incoming.getData(), 0, incoming.getLength(), "UTF8");
				output.append(message + "\n");
			}
		} catch (IOException ex) {
			handleIOException(ex);
		}
	}

	public static void main(String[] args) throws IOException {
		InetAddress group = InetAddress.getByName("224.0.0.1");
		int port = 12345;
		MulticastChat chat = new MulticastChat(group, port);
		chat.start();
	}
}