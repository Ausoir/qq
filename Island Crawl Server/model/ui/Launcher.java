package ui;

import initialization.ServerLoader;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Launcher extends JFrame implements ActionListener{
	//Image image;
	private static final long serialVersionUID = 1L;
	public Launcher(){
		setLayout(new GridLayout(3,1));
		setSize(300,400);
		setVisible(true);
		setFocusable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton but1 = new JButton("Start Server");
		JButton but2 = new JButton("Settings");
		JButton but3 = new JButton("Exit");
		but1.setActionCommand("Start");
		but2.setActionCommand("Setting");
		but3.setActionCommand("Exit");
		add(but1);
		add(but2);
		add(but3);
		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);
	}
	public void actionPerformed(ActionEvent arg0) {
		order(arg0.getActionCommand());
	}
	public void order(String p){
		System.out.println(p);
		if (p.equals("Start")){
			ServerLoader.load();
		}
		remove();
	}
	public void remove(){
		setVisible(false);
		dispose();
	}
}