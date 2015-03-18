package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import Conexiones_SQL.Connexion;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Reportes_De_Control_Facturas_Y_XML_Recibidos extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JDateChooser c_inicio = new JDateChooser();
	JDateChooser c_final = new JDateChooser();
	JButton btngenerar = new JButton("Generar",new ImageIcon("imagen/orange-folder-saved-search-icone-8197-32.png"));
	JButton btngenerar2 = new JButton("Generar2",new ImageIcon("imagen/orange-folder-saved-search-icone-8197-32.png"));
	
	public Cat_Reportes_De_Control_Facturas_Y_XML_Recibidos(){

		panel.setBorder(BorderFactory.createTitledBorder("Seleciona el Rango de Fechas Para El Reporte"));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/control_facturas_y_xml.png"));
		this.setTitle("Reportes De Control De Facturas y XML Recibidos");
		panel.add(new JLabel("Fecha Inicio:")).setBounds(15,25,100,20);
		panel.add(c_inicio).setBounds(80,25,100,20);
		panel.add(new JLabel("Fecha Final:")).setBounds(190,25,100,20);
		panel.add(c_final).setBounds(250,25,100,20);
		
		btngenerar.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte General</p>" +
				"		<CENTER><p>De Control De Facturas y XML</p></CENTER></FONT>" +
				"</html>"); 
		
		btngenerar2.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<p>Impresion de Reporte de Facturas</p>" +
				"		<CENTER><p>Faltantes de Recibir PDF y XML</p></CENTER></FONT>" +
				"</html>"); 
		
		panel.add(btngenerar).setBounds(50, 70, 270, 50);
		panel.add(btngenerar2).setBounds(50, 130, 270, 50);
		cont.add(panel);
		btngenerar.addActionListener(opGenerar);
		btngenerar2.addActionListener(opGenerar2);
		this.setSize(370, 230);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	ActionListener opGenerar = new ActionListener() {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {

		if(validar_fechas().equals("")){
			String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
			String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
			
			if(c_inicio.getDate().before(c_final.getDate())){
				new Cat_Reporte_De_Captura_De_Facturas_General(new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate()),new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())).setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
				return;
			}
	 	}else{
			JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fechas(),"Aviso!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		}
	};
	
	ActionListener opGenerar2 = new ActionListener() {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {

		if(validar_fechas().equals("")){
			String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
			String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
			
			if(c_inicio.getDate().before(c_final.getDate())){
				new Cat_Reporte_De_Facturas_Faltantes(new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate()),new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())).setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
				return;
			}
	 	}else{
			JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fechas(),"Aviso!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		}
	};
	
	public String validar_fechas(){
		String error = "";
		String fechainicioNull = c_inicio.getDate()+"";
		String fechafinalNull = c_final.getDate()+"";
	    if(fechainicioNull.equals("null"))error+= "Fecha  inicio\n";
		if(fechafinalNull.equals("null"))error+= "Fecha Final\n";
		return error;
	}
	
	public class Cat_Reporte_De_Captura_De_Facturas_General extends JFrame {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Cat_Reporte_De_Captura_De_Facturas_General(String fecha_inicio, String fecha_final) {
			String query = "exec sp_reporte_de_control_de_captura_de_facturas_y_XML_general '"+fecha_inicio+"','"+fecha_final+"',"+1;
			try {
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Captura_de_Facturas_Completo.jrxml");
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(),new JRResultSetDataSource(new Connexion().conexion().createStatement().executeQuery(query)));
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error En La Subclase Cat_Reporte_De_Captura_De_Facturas_General ", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
		}
	}
	
	public class Cat_Reporte_De_Facturas_Faltantes extends JFrame {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Cat_Reporte_De_Facturas_Faltantes(String fecha_inicio, String fecha_final) {
			String query = "exec sp_reporte_de_control_de_captura_de_facturas_y_XML_general '"+fecha_inicio+"','"+fecha_final+"',"+2;
			try {
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Captura_de_Facturas_Completo.jrxml");
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(),new JRResultSetDataSource(new Connexion().conexion().createStatement().executeQuery(query)));
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error En La Subclase Cat_Reporte_De_Facturas_Faltantes ", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
		}
	}
//	
//	public static void main(String args[]){
//		try{
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			new Cat_Reportes_De_Control_Facturas_Y_XML_Recibidos().setVisible(true);
//		}catch(Exception e){	}
//	}
}
