package Cat_IZAGAR;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class Cat_IZAGAR_Selecionar_Nomina_Para_Netos extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	public JTextField txtFolionomina = new JTextField();
	public JButton btnconsultanomina = new JButton("Buscar");

	
 	public Cat_IZAGAR_Selecionar_Nomina_Para_Netos() {
 		this.setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cat_nomina_icon&16.png"));
		setTitle("Traspaso de Netos de Nomina a Bancos");
		campo.setBorder(BorderFactory.createTitledBorder("Traspaso de Netos a Bancos"));
		setSize(300,150);
		setResizable(false);
		setLocationRelativeTo(null);

		campo.add(btnconsultanomina).setBounds(180,30,70,20);
		campo.add(new JLabel("Folio Nomina:")).setBounds(15,30,80,20);
		campo.add(new JLabel("Teclee Por Favor El Numero de Nomina a Consultar")).setBounds(15,80,300,20);
		campo.add(txtFolionomina).setBounds(80,30,100,20);
		txtFolionomina.addKeyListener(valida_numerico);
		cont.add(campo);
	}
  	
	KeyListener valida_numerico = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
		   if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){ e.consume(); }			
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
	};
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_IZAGAR_Selecionar_Nomina_Para_Netos().setVisible(true);
		}catch(Exception e){	}
	}
}