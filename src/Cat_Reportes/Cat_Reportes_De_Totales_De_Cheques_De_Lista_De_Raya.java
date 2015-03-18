package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;


import Conexiones_SQL.Connexion;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class Cat_Reportes_De_Totales_De_Cheques_De_Lista_De_Raya extends JFrame {
	JButton btnCheque_Lista_Raya_Actual = new JButton();
	JButton btnCheque_Listas_Raya_Pasadas = new JButton();
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	Border blackline, etched, raisedbevel, loweredbevel, empty;

	
	public Cat_Reportes_De_Totales_De_Cheques_De_Lista_De_Raya() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/dinero-icono-8797-32.png"));
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Seleccion Del Reporte De Totales de Cheque Por L.R."));
		this.setTitle("Reportes Totales De Cheque Por Lista De Raya");
		
		btnCheque_Lista_Raya_Actual.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte De Totales</p>" +
				"		<CENTER><p>De Cheque De Lista De Raya Actual</p></CENTER></FONT>" +
				"</html>"); 
		
		btnCheque_Listas_Raya_Pasadas.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte De Totales</p>" +
				"		<CENTER><p>De Cheque De Listas De Raya Pasadas</p></CENTER></FONT>" +
				"</html>"); 
		
		panel.add(btnCheque_Lista_Raya_Actual).setBounds(40,30,240,60);
		
		panel.add(btnCheque_Listas_Raya_Pasadas).setBounds(40,100,240,60);
		
		btnCheque_Lista_Raya_Actual.addActionListener(Reporte_De_Totales_de_Cheques_De_Lista_De_Raya_Actual);
//		btnCheque_Listas_Raya_Pasadas.addActionListener(Reporte_De_Totales_de_Cheques_De_Listas_de_Raya_Pasadas);
		cont.add(panel);
		this.setSize(320,200);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener Reporte_De_Totales_de_Cheques_De_Lista_De_Raya_Actual = new ActionListener(){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e){
			String query = "exec sp_Reporte_De_Totales_De_Cheque_De_Lista_De_Raya_Actual";
			Statement stmt = null;
				try {
					stmt =  new Connexion().conexion().createStatement();
				    ResultSet rs = stmt.executeQuery(query);
					
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Totales_de_Cheques_De_Lista_De_Raya_Actual.jrxml");
					JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
					JasperViewer.viewReport(print, false);
					
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Reporte_De_Totales_de_Cheques_De_Listas_de_Raya_Pasadas  en la funcion [ ActionListener Reporte_De_Totales_de_Cheques_De_Lista_De_Raya_Actual ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
	};
//	ActionListener Reporte_De_Totales_de_Cheques_De_Listas_de_Raya_Pasadas = new ActionListener(){
//		@SuppressWarnings("rawtypes")
//		public void actionPerformed(ActionEvent e){
//				try {
//					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Empleados_Sin_Depositos_En_Banco_Para_Exportar.jrxml");
//					@SuppressWarnings("unchecked")
//					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
//					JasperViewer.viewReport(print, false);
//				} catch (Exception e2) {
//					System.out.println(e2.getMessage());
//					JOptionPane.showMessageDialog(null, "Error en Cat_Reporte_De_Empleados_Sin_Depositos_A_Bancos  en la funcion [ ActionListener Reporte_Sin_Depositos_A_Bancos_Limpio ]   SQLException:  "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
//				}
//		}
//	};
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_De_Totales_De_Cheques_De_Lista_De_Raya().setVisible(true);
		}catch(Exception e){	}
	}

}
