package Inicio;

import java.awt.Color;
import javax.swing.*;

public class TelaInic extends JFrame{
	JButton b1 = new JButton("Novo Jogo");
	JButton b2 = new JButton("Carregar Jogo");
	JPanel p = new JPanel();
	
	public TelaInic() {
		super("Tela Inicial Banco Imobiliario");
		p.add(b1);
		p.add(b2);
		p.setBackground(Color.WHITE);
		getContentPane().add(p);
		setSize(400,300);
		}

}
