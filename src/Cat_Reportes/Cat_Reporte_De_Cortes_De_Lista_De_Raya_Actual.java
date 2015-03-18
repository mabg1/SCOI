package Cat_Reportes;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Conexiones_SQL.Connexion;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class Cat_Reporte_De_Cortes_De_Lista_De_Raya_Actual extends JDialog {
	JButton btncortes_Limpio = new JButton();
	JButton btnPrestamos_Por_Establecimiento = new JButton("Impresion de Reporte De Prestamos Por Establecimiento");
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	//declaracion de Bordes
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	TitledBorder title4; 

	public Cat_Reporte_De_Cortes_De_Lista_De_Raya_Actual() {
		
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Seleccion Del Reporte de Prestamos de Lista de Raya Actual"));
		this.setTitle("Reportes de Prestamos");
		
		btncortes_Limpio.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte De Cortes</p>" +
				"		<CENTER><p>Para Exportar a Excel</p></CENTER></FONT>" +
				"</html>"); 
		
		btnPrestamos_Por_Establecimiento.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte De cortes</p>" +
				"		<CENTER><p>Por Establecimiento</p></CENTER></FONT>" +
				"</html>"); 
		
		
		panel.add(btncortes_Limpio).setBounds(40,40,240,40);
		
		panel.add(btnPrestamos_Por_Establecimiento).setBounds(40,100,240,40);
		
		btncortes_Limpio.addActionListener(Reporte_Cortes_Lista_de_Raya_Actual_limpio);
		btnPrestamos_Por_Establecimiento.addActionListener(Reporte_Cortes_Lista_de_Raya_Actual_Por_Establecimiento);
		cont.add(panel);
		this.setSize(320,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	ActionListener Reporte_Cortes_Lista_de_Raya_Actual_Por_Establecimiento = new ActionListener(){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e){
			try {
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Cortes_De_Lista_De_Raya_Actual.jrxml");
				
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
				JasperViewer.viewReport(print, false);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				JOptionPane.showMessageDialog(null, "Error en Cat_Reporte_De_Cortes_De_Lista_De_Raya_Actual  en la funcion [ ActionListener Reporte_Cortes_Lista_de_Raya_Actual_Por_Establecimiento ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);

			}
		}
	};
	ActionListener Reporte_Cortes_Lista_de_Raya_Actual_limpio = new ActionListener(){
		@SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Cortes_De_Lista_De_Raya_Actual_Para_Exportar.jrxml");
					@SuppressWarnings("unchecked")
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
					JasperViewer.viewReport(print, false);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Reporte_De_Cortes_De_Lista_De_Raya_Actual  en la funcion [ ActionListener Reporte_Cortes_Lista_de_Raya_Actual_limpio ]   SQLException:  "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
		}
	};
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reporte_De_Cortes_De_Lista_De_Raya_Actual().setVisible(true);
		}catch(Exception e){	}
	}


}
