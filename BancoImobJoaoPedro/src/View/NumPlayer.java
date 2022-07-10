package View;

import javax.swing.*;
import java.awt.*;

public class NumPlayer extends JFrame{
	JButton p2 = new JButton("-");
	JButton p3 = new JButton("+");
	
	public NumPlayer(Container p) {
		pack();
		p.add(p2);
		p.add(p3);
		JLabel texto=new JLabel("Isso é um B-777");
		p.setBackground(Color.WHITE);
		p.add(texto);
		pack();
		setSize(300,300);
		setVisible(true);

	}
		
}
