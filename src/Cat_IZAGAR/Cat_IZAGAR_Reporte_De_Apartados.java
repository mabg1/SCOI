package Cat_IZAGAR;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import Conexiones_SQL.Connexion;
import IZAGAR_Obj.Obj_IZAGAR_Apartados;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class Cat_IZAGAR_Reporte_De_Apartados extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtAsignacion = new JTextField();

	JButton btnGenerar = new JButton("Generar",new ImageIcon("imagen/buscar.png"));
	JButton btnApartadoAsignacion =new JButton("",new ImageIcon("imagen/Accounting.png"));
	JButton btnApartadoFecha =new JButton("",new ImageIcon("imagen/Calendar.png"));
	
	JDateChooser cfecha = new JDateChooser();
	
	int tipo_Reporte = 0;
	
	public Cat_IZAGAR_Reporte_De_Apartados(){
		cont.add(panel);
		
		setSize(305,280);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Reporte de Apartados y Abonos");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagen/documentos-de-gabinete-icono-4840-32.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Seleccione el Tipo de Reporte Asignacion-Fecha"));
		
		
		btnApartadoAsignacion.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Apartado Por Asignacion</p></CENTER></FONT>" +
				"</html>");
		
		btnApartadoFecha.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Apartado Por Fecha     </p></CENTER></FONT>" +
				"</html>");

		
		panel.add(btnApartadoAsignacion).setBounds(20,25,260,30);
		panel.add(btnApartadoFecha).setBounds(20,75,260,30);
		panel.add(new JLabel("Asignacion:")).setBounds(20,130,200,20);		
		panel.add(txtAsignacion).setBounds(80,130,195,20);
		panel.add(new JLabel("Fecha:")).setBounds(20,170,200,20);		
		panel.add(cfecha).setBounds(80,170,195,20);
	    panel.add(btnGenerar).setBounds(100,200,120,30);
		 
		
	    txtAsignacion.setEditable(false);
	    cfecha.setEnabled(false);
	    btnGenerar.setEnabled(false);
		 
		btnGenerar.addActionListener(opGenerar) ;
		btnApartadoAsignacion.addActionListener(opReporte_Por_Asignacion);
		btnApartadoFecha.addActionListener(opReporte_Por_Fecha);
		 

	}
	
	ActionListener opSalir = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	ActionListener opReporte_Por_Asignacion = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtAsignacion.setEditable(true);
			cfecha.setEnabled(false);
			btnGenerar.setEnabled(true);
			tipo_Reporte=1;
			cfecha.setDate(null);
		}
	};
	
	ActionListener opReporte_Por_Fecha = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtAsignacion.setEditable(false);
			cfecha.setEnabled(true);
			btnGenerar.setEnabled(true);
			tipo_Reporte=2;
			txtAsignacion.setText("");
			
		}
	};
	
	public String validar_fechas(){
		String error = "";
		@SuppressWarnings("unused")
		String fechafinalNull = cfecha.getDate()+"";
	    if(cfecha.equals("null"))error+= "Fecha\n";
		return error;
	}
	
	
	
	ActionListener opGenerar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			 
			Obj_IZAGAR_Apartados objasignacion = new Obj_IZAGAR_Apartados();
			
			objasignacion.setAsignacion(txtAsignacion.getText());
			
			if(tipo_Reporte==1){
				
			if(txtAsignacion.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Necesita Teclear Una Asignacion","Mensaje",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
			
			if(new Obj_IZAGAR_Apartados().guardar(objasignacion)){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\IZAGAR_Obj_Reportes\\Obj_Reporte_IZAGAR_de_Apartados.jrxml");
										
					@SuppressWarnings({ "rawtypes", "unchecked" })
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion_IZAGAR());
					JasperViewer.viewReport(print, false);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}	
			}
			else{
				 
				JOptionPane.showMessageDialog(null,"Ocurrio un error al Generar el Reporte por Asignacion opGenerar","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			}
		   }else{
			   String fechaNull = cfecha.getDate()+"";
			   if(fechaNull.equals("null")){
				   
					JOptionPane.showMessageDialog(null,"Necesita Selecionar una Fecha o la Fecha tecleada es Incorrecta","Mensaje",JOptionPane.WARNING_MESSAGE);
					return;
				   }else{
					   String fecha = new SimpleDateFormat("dd/MM/yyyy").format(cfecha.getDate())+" 00:00:00";
					   String query = "exec IZAGAR_consulta_de_apartados '"+fecha+"'" ;
						Statement stmt = null;
						
						try {
							stmt =  new Connexion().conexion_IZAGAR().createStatement();
						    ResultSet rs = stmt.executeQuery(query);
							JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\IZAGAR_Obj_Reportes\\Obj_Reporte_IZAGAR_de_Apartados_Por_Fecha.jrxml");
							JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
							@SuppressWarnings({ "rawtypes", "unchecked" })
							JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
							JasperViewer.viewReport(print, false);
						} catch (Exception e1) {
							System.out.println(e1.getMessage());
							JOptionPane.showMessageDialog(null, "Error en Generar Reporte de Apartados Por Fecha  procedure sp_consulta_de_datos_voucher SQLException: \n "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}
						return;					   
				   }
			}
			
		}
	};

	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_IZAGAR_Reporte_De_Apartados().setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
