package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Cat_Lista_de_Raya.Cat_Filtro_De_Listas_De_Raya_Pasadas;
import Conexiones_SQL.Connexion;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class Cat_Reporte_De_Prestamos_De_Lista_De_Raya extends JFrame {
	JButton btnPrestamos_Limpio = new JButton();
	JButton btnPrestamos_Por_Establecimiento = new JButton("Impresion de Reporte De Prestamos Por Establecimiento");
	JButton btnPrestamos_Listaraya_pasadas = new JButton();
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	//declaracion de Bordes
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	TitledBorder title4; 

	public Cat_Reporte_De_Prestamos_De_Lista_De_Raya() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/dinero-en-efectivo-cartera-monedero-icono-7127-32.png"));
		
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Seleccion Del Reporte de Prestamos de Lista de Raya Actual"));
		this.setTitle("Reportes de Prestamos");
		
		btnPrestamos_Limpio.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte de Prestamos Para</p>" +
				"		<CENTER><p>Exp Por Empleado de Lista de Raya Actual</p></CENTER></FONT>" +
				"</html>"); 
		
		btnPrestamos_Por_Establecimiento.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte De Prestamos Por</p>" +
				"		<CENTER><p>Establecimiento de Lista de Raya Actual</p></CENTER></FONT>" +
				"</html>"); 
		
		btnPrestamos_Listaraya_pasadas.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte De Prestamos Por</p>" +
				"		<CENTER><p>Establecimiento de Listas de Raya Pasadas</p></CENTER></FONT>" +
				"</html>"); 
		
		
		panel.add(btnPrestamos_Limpio).setBounds(40,40,280,40);
		panel.add(btnPrestamos_Por_Establecimiento).setBounds(40,100,280,40);
		panel.add(btnPrestamos_Listaraya_pasadas).setBounds(40,160,280,40);
		
		
		btnPrestamos_Limpio.addActionListener(Reporte_Prestamos_Lista_de_Raya_Actual_limpio);
		btnPrestamos_Por_Establecimiento.addActionListener(Reporte_Prestamos_Lista_de_Raya_Actual_Por_Establecimiento);
		btnPrestamos_Listaraya_pasadas.addActionListener(Reporte_Prestamos_de_Listas_de_Raya_Pasadas_Por_Establecimiento);
		cont.add(panel);
		this.setSize(365,260);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	ActionListener Reporte_Prestamos_Lista_de_Raya_Actual_Por_Establecimiento = new ActionListener(){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e){
			try {
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Prestamos_De_Lista_De_Raya_Actual.jrxml");
				
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
				JasperViewer.viewReport(print, false);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				JOptionPane.showMessageDialog(null, "Error en Cat_Reporte_De_Prestamos_De_Lista_De_Raya_Actual  en la funcion [ ActionListener Reporte_Prestamos_Lista_de_Raya_Actual_Por_Establecimiento ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);

			}
		}
	};
	
	
	ActionListener Reporte_Prestamos_Lista_de_Raya_Actual_limpio = new ActionListener(){
		@SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Prestamos_De_Lista_De_Raya_Actual_Para_Exportar.jrxml");
					@SuppressWarnings("unchecked")
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
					JasperViewer.viewReport(print, false);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Reporte_De_Prestamos_De_Lista_De_Raya_Actual  en la funcion [ ActionListener Reporte_Depositos_Bancos_limpio ]   SQLException:  "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
		}
	};
	
	
	ActionListener Reporte_Prestamos_de_Listas_de_Raya_Pasadas_Por_Establecimiento = new ActionListener(){
		public void actionPerformed(ActionEvent e){
            new Cat_Filtro_De_Listas_De_Raya_Pasadas(2).setVisible(true);     
		}
	};
	
	
	
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void Impresion_de_Reporte_Prestamos_LRPasadas(int folio) {
			
			String query_corte_caja = "exec sp_consulta_de_prestamos_de_listas_de_raya_pasadas '"+folio+"';";
			Statement stmt = null;
			try {
				stmt =  new Connexion().conexion().createStatement();
			    ResultSet rs = stmt.executeQuery(query_corte_caja);
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Prestamos_De_Listas_De_Raya_Pasadas.jrxml");
				JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error En Cat_Reporte_De_Prestamos en la funcion Impresion_de_Reporte_Prestamos_LRPasadas ", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
		 }
//	}

	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reporte_De_Prestamos_De_Lista_De_Raya().setVisible(true);
		}catch(Exception e){	}
	}
}
