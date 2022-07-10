package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class PainelInicial extends JFrame{  
	public final int LARG_DEFAULT=800;
	public final int ALT_DEFAULT=600;
	JPanel p = new JPanel(); //*
	
	JButton b1 = new JButton("Carregar Partida");
	JButton b2 = new JButton("Novo Jogo");

	public PainelInicial() {
		super("Isso aqui é o titulo da janela!"); //*
		setSize(LARG_DEFAULT,ALT_DEFAULT);//*
		p.setLayout(null);
		b1.setBounds(200, ALT_DEFAULT/2, 150, 50);
		//b1.addActionListener((ActionListener) new LoadGame(this));
		b2.setBounds(400, ALT_DEFAULT/2, 150, 50);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NumPlayer(p);
			}
			});
		p.add(b1);//*
		p.add(b2);//*
		getContentPane().add(p);
		pack();
		setSize(LARG_DEFAULT,ALT_DEFAULT);//*
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
