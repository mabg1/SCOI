package Cat_Reportes;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;


import Conexiones_SQL.Connexion;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class Cat_Reporte_De_Empleados_Sin_Deposito_A_Bancos extends JFrame {
	JButton btnDepositos_A_Bancos_Limpio = new JButton();
	JButton btnDepositos_Bancos_Por_Establecimiento = new JButton();
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	Border blackline, etched, raisedbevel, loweredbevel, empty;

	
	public Cat_Reporte_De_Empleados_Sin_Deposito_A_Bancos() {
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Seleccion Del Reporte De Empleados Sin Depositos En Bancos"));
		this.setTitle("Reportes de Empleados Sin Depositos En Bancos");
		
		btnDepositos_A_Bancos_Limpio.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte De Empleados</p>" +
				"		<CENTER><p>Sin Depositos En Bancos Para Exportar a Excel</p></CENTER></FONT>" +
				"</html>"); 
		
		btnDepositos_Bancos_Por_Establecimiento.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte De Empleados</p>" +
				"		<CENTER><p>Sin Depositos En Bancos Por Establecimiento</p></CENTER></FONT>" +
				"</html>"); 
		
		panel.add(btnDepositos_A_Bancos_Limpio).setBounds(40,30,240,60);
		
		panel.add(btnDepositos_Bancos_Por_Establecimiento).setBounds(40,100,240,60);
		
		btnDepositos_A_Bancos_Limpio.addActionListener(Reporte_Sin_Depositos_A_Bancos_Limpio);
		btnDepositos_Bancos_Por_Establecimiento.addActionListener(Reporte_Sin_Depositos_A_Bancos_Por_Establecimiento);
		cont.add(panel);
		this.setSize(320,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener Reporte_Sin_Depositos_A_Bancos_Por_Establecimiento = new ActionListener(){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Empleados_Sin_Depositos_En_Banco.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
					JasperViewer.viewReport(print, false);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Reporte_De_Empleados_Sin_Depositos_A_Bancos  en la funcion [ ActionListener Reporte_Sin_Depositos_A_Bancos_Por_Establecimiento ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	ActionListener Reporte_Sin_Depositos_A_Bancos_Limpio = new ActionListener(){
		@SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Empleados_Sin_Depositos_En_Banco_Para_Exportar.jrxml");
					@SuppressWarnings("unchecked")
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
					JasperViewer.viewReport(print, false);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Reporte_De_Empleados_Sin_Depositos_A_Bancos  en la funcion [ ActionListener Reporte_Sin_Depositos_A_Bancos_Limpio ]   SQLException:  "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
		}
	};
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reporte_De_Empleados_Sin_Deposito_A_Bancos().setVisible(true);
		}catch(Exception e){	}
	}

}
