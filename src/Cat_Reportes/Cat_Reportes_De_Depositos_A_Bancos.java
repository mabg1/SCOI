package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
public class Cat_Reportes_De_Depositos_A_Bancos extends JFrame {
	
	
	JButton btnDepositos_A_Bancos_Limpio = new JButton(new ImageIcon("imagen/hoja-de-calculo-excel-icono-5223-16.png"));
	JButton btnDepositos_Bancos_Por_Establecimiento = new JButton(new ImageIcon("imagen/plan-icono-5073-16.png"));
	
	
	JButton btnDepositos_A_Bancos_Limpio_Excedido = new JButton(new ImageIcon("imagen/hoja-de-calculo-excel-icono-5223-16.png"));
	JButton btnDepositos_Bancos_Por_Estab_Excedido = new JButton(new ImageIcon("imagen/plan-icono-5073-16.png"));
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	Border blackline, etched, raisedbevel, loweredbevel, empty;

	
	public Cat_Reportes_De_Depositos_A_Bancos() {
		
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Seleccion Del Reporte Depositos A Bancos"));
		this.setTitle("Reportes Depositos A Bancos");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/tarjeta-de-credito-visa-icono-8242-32.png"));
		
		btnDepositos_A_Bancos_Limpio.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Reporte De Depositos A Bancos</p>" +
				"		<CENTER><p>Para Exportar A Excel De LR. Actual</p></CENTER></FONT>" +
				"</html>"); 
		
		btnDepositos_Bancos_Por_Establecimiento.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Reporte De Depositos A Bancos</p>" +
				"		<CENTER><p>Por Establecimiento De LR. Actual</p></CENTER></FONT>" +
				"</html>"); 
		
		btnDepositos_A_Bancos_Limpio_Excedido.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Reporte De Empleados Con Deposito</p>" +
				"		<CENTER><p>En Bancos Excedido Para Exportar a Excel</p></CENTER></FONT>" +
				"</html>"); 
		
		btnDepositos_Bancos_Por_Estab_Excedido.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Reporte De Empleados Con Deposito</p>" +
				"		<CENTER><p>En Bancos Excedido Por Establecimiento</p></CENTER></FONT>" +
				"</html>"); 
		
		panel.add(btnDepositos_A_Bancos_Limpio).setBounds(30,40,300,40);
		panel.add(btnDepositos_Bancos_Por_Establecimiento).setBounds(30,100,300,40);
		panel.add(btnDepositos_A_Bancos_Limpio_Excedido).setBounds(30,190,300,40);
		panel.add(btnDepositos_Bancos_Por_Estab_Excedido).setBounds(30,250,300,40);
		
		btnDepositos_A_Bancos_Limpio.addActionListener(Reporte_Depositos_A_Bancos_Limpio);
		btnDepositos_Bancos_Por_Establecimiento.addActionListener(Reporte_Depositos_A_Bancos_Por_Establecimiento);
		btnDepositos_A_Bancos_Limpio_Excedido.addActionListener(Reporte_Con_Deposito_A_Bancos_Limpio_excedido);
		btnDepositos_Bancos_Por_Estab_Excedido.addActionListener(Reporte_Con_Deposito_A_Bancos_Por_Establecimiento_excedido);
		
		cont.add(panel);
		this.setSize(360,350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	ActionListener Reporte_Depositos_A_Bancos_Por_Establecimiento = new ActionListener(){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_Depositos_A_Bancos.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
					JasperViewer.viewReport(print, false);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Depositos_A_Bancos  en la funcion [ ActionListener Reporte_Depositos_A_Bancos_Por_Establecimiento ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);

			}
		}
	};
	ActionListener Reporte_Depositos_A_Bancos_Limpio = new ActionListener(){
		@SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_Depositos_A_Bancos_Para_Exportar.jrxml");
					@SuppressWarnings("unchecked")
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
					JasperViewer.viewReport(print, false);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Depositos_A_Bancos  en la funcion [ ActionListener Reporte_Depositos_A_Bancos_Limpio ]   SQLException:  "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
		}
	};
	
	ActionListener Reporte_Con_Deposito_A_Bancos_Por_Establecimiento_excedido = new ActionListener(){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Empleados_Con_Deposito_a_Bancos_Excedido.jrxml");
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
					JasperViewer.viewReport(print, false);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Reportes_De_Empleados_Con_Deposito_En_Bancos_Excedido  en la funcion [ ActionListener Reporte_Con_Deposito_A_Bancos_Excedido ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	
	ActionListener Reporte_Con_Deposito_A_Bancos_Limpio_excedido = new ActionListener(){
		@SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Empleados_Con_Deposito_a_Bancos_Excedido_Para_Exportar.jrxml");
					@SuppressWarnings("unchecked")
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
					JasperViewer.viewReport(print, false);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Reportes_De_Empleados_Con_Deposito_En_Bancos_Excedido  en la funcion [ ActionListener Reporte_Con_Deposito_A_Bancos_Limpio ]   SQLException:  "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
		}
	};
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_De_Depositos_A_Bancos().setVisible(true);
		}catch(Exception e){	}
	}

}
