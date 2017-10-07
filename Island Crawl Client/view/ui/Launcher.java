package ui;

import initialization.GameLoader;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Launcher extends JFrame implements ActionListener {
	// Image image;
	private static final long serialVersionUID = 1L;

	public Launcher() {
		setLayout(new GridLayout(3, 1));
		setSize(300, 400);
		setFocusable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JButton but1 = new JButton("Connect to Default");
		JButton but2 = new JButton("Connect using IP");
		JButton but3 = new JButton("Settings");
		JButton but4 = new JButton("Exit");
		but1.setActionCommand("Start");
		but2.setActionCommand("Connect");
		but3.setActionCommand("Setting");
		but4.setActionCommand("Exit");
		add(but1);
		add(but2);
		add(but3);
		add(but4);
		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);
		but4.addActionListener(this);
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		order(arg0.getActionCommand());
	}

	public void order(String p) {
		remove();
		if (p.equals("Start")) {
				try {
					GameLoader.load(InetAddress.getLocalHost());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
		} else if (p.equals("Connect")) {
			String ip = JOptionPane.showInputDialog(null);
			if (ip != null && !ip.equals("")) {
				try {
					GameLoader.load(InetAddress.getByName(ip));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void remove() {
		setVisible(false);
		dispose();
	}
}