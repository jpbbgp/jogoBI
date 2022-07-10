package View;
import java.awt.*;
import javax.swing.*;

public class Tela extends JFrame{
	public final int LARG_DEFAULT=800;
	public final int ALT_DEFAULT=400;
	JPanel p;
	
	public Tela() {
		JButton carregar = new JButton("Carregar partida");
		carregar.setBounds(300, 240, 200, 50);
		add(carregar);
		PainelInicial inicial = new PainelInicial();
		getContentPane().add(inicial);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
