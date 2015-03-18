package Biblioteca;

import java.awt.Container;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class Cat_Mapa_De_Acciones_Del_Teclado extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();

	JTextField txt = new JTextField();

	public Cat_Mapa_De_Acciones_Del_Teclado()	{

		panel.add(txt).setBounds( 25, 15, 80, 20);
		cont.add(panel);

		mapeoTeclas();

		this.setSize(180,100);
		this.setLocationRelativeTo(null);
	}

	public static void main(String args[]){
			new Cat_Mapa_De_Acciones_Del_Teclado().setVisible(true);
	}

	// ACCIONES
	public void mapeoTeclas(){

	ActionMap mapaAccion = panel.getActionMap();
	InputMap map = panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

	//F1
	KeyStroke key_F1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1,0);

	// CTRL + O
	KeyStroke ctrl_O = KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK);

	// CTRL + C, CTRL + V
	KeyStroke ctrl_G = KeyStroke.getKeyStroke(KeyEvent.VK_G,Event.CTRL_MASK, true);
	KeyStroke ctrl_V = KeyStroke.getKeyStroke(KeyEvent.VK_V,Event.CTRL_MASK, true);

	//Key Actions
	map.put(key_F1, "accion_F1");
	mapaAccion.put("accion_F1",Accion_F1());

	map.put(ctrl_O , "accion_ctrl_o");
	mapaAccion.put("accion_ctrl_o",Accion_CTRLO());

	map.put(ctrl_G , "accion_ctrl_C");
	mapaAccion.put("accion_ctrl_C",Accion_CTRLC());

	map.put(ctrl_V , "accion_ctrl_V");
	mapaAccion.put("accion_ctrl_V",Accion_CTRLV());
	}

	public AbstractAction Accion_CTRLO(){
	return new AbstractAction() { public void actionPerformed(ActionEvent e) { txt.setText("CTRL + O"); } };
	}
	public AbstractAction Accion_CTRLC(){
	return new AbstractAction() { public void actionPerformed(ActionEvent e) { txt.setText("CTRL + G"); } };
	}
	public AbstractAction Accion_CTRLV(){
	return new AbstractAction() { public void actionPerformed(ActionEvent e) { txt.setText("CTRL + V"); } };
	}
	public AbstractAction Accion_F1(){
	return new AbstractAction() { public void actionPerformed(ActionEvent e) { txt.setText("F1"); } };
	}
}