package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.border.Border;


import Conexiones_SQL.Generacion_Reportes;

@SuppressWarnings("serial")
public class Cat_Reportes_De_Horarios extends JFrame {
	JButton btnHorariosAsignados = new JButton();
	JButton btnDepositos_Bancos_Por_Establecimiento = new JButton();
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	Border blackline, etched, raisedbevel, loweredbevel, empty;

	
	public Cat_Reportes_De_Horarios() {
		this.setSize(320,200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Reportes De Horarios");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/checador.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Seleccione el Tipo de Reporte"));
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		
		btnHorariosAsignados.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte de</p>" +
				"		<CENTER><p>Horarios Asignados a Empleados</p></CENTER></FONT>" +
				"</html>"); 
		
		btnDepositos_Bancos_Por_Establecimiento.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte de</p>" +
				"		<CENTER><p>Horarios Sin Asignar</p></CENTER></FONT>" +
				"</html>"); 
		
		panel.add(btnHorariosAsignados).setBounds(40,40,240,40);
		panel.add(btnDepositos_Bancos_Por_Establecimiento).setBounds(40,100,240,40);
		btnHorariosAsignados.addActionListener(Reporte_De_Horarios_Asignados);
		btnDepositos_Bancos_Por_Establecimiento.addActionListener(Reporte_De_Horarios_Sin_Asignar);
		cont.add(panel);
	}
	
	String basedatos="2.26";
	String vista_previa_reporte="no";
	int vista_previa_de_ventana=0;
	String comando="";
	String reporte = "";
	
	ActionListener Reporte_De_Horarios_Asignados = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			 reporte = "Obj_Reporte_De_Horarios_De_Empleados.jrxml";
			 comando = "exec sp_Reporte_de_Horarios_Asignados_Completo";
			 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
		}
	};
	
	ActionListener Reporte_De_Horarios_Sin_Asignar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			 reporte = "Obj_Reporte_De_Horarios_De_Empleados.jrxml";
			 comando = "exec sp_Reporte_de_Horarios_Sin_Asignar";
			 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
			 
		}
	};
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_De_Horarios().setVisible(true);
		}catch(Exception e){	}
	}

}
