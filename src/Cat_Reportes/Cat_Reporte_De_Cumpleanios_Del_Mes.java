package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Conexiones_SQL.Connexion;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


@SuppressWarnings("serial")
public class Cat_Reporte_De_Cumpleanios_Del_Mes extends JDialog{
	 
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		String Meses[] = {"Seleciona Un Mes","Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre","Todo El Año"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbMeses = new JComboBox(Meses);
		
		JButton btngenerar = new JButton("Generar",new ImageIcon("imagen/buscar.png"));
		
		public Cat_Reporte_De_Cumpleanios_Del_Mes(){
			
			panel.setBorder(BorderFactory.createTitledBorder("Reporte De Cumpleaños"));
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/cookies-tarta-de-cumpleanos-icono-9840-16.png"));
			this.setTitle("RePorte De Cumpleaños Por Mes");
					
			panel.add(new JLabel("Seleciona El Mes:")).setBounds(15,35,110,20);
			panel.add(cmbMeses).setBounds(100,35,120,20);
			panel.add(btngenerar).setBounds(70, 70, 100, 20);
			cont.add(panel);
			
			btngenerar.addActionListener(opGenerar);
			
			this.setSize(250, 140);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		
		ActionListener opGenerar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				int mes= cmbMeses.getSelectedIndex();
				new Cat_Genera_Reporte_Cumpleanios_Del_Mes(mes);
			}
		};
		



	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public class Cat_Genera_Reporte_Cumpleanios_Del_Mes extends JFrame {
		
		public Cat_Genera_Reporte_Cumpleanios_Del_Mes(int mes) {
			String query = "exec sp_Reporte_De_Cumpleanios_del_Mes "+mes;
			try {
				
//				jenera el archivo *.jasper en la misma ruta del proyecto
//				JasperCompileManager.compileReportToFile(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Cumpleanios_Del_Mes.jrxml");
				
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Cumpleanios_Del_Mes.jrxml");
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(),new JRResultSetDataSource(new Connexion().conexion().createStatement().executeQuery(query)));
				
//				mostras reporte (comentar para no mostrar)
				JasperViewer.viewReport(print, false);
				
//				imprimir reporte automatico ---------------------------------------------------------------------------------------------------------------
//				false = imprime reporte en impresora predeterminada ---------------------------------------------------------------------------------------
//				true  = muestra ventana de seleccion de impresora -----------------------------------------------------------------------------------------
//				JasperPrintManager.printReport(print, true);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error En La Subclase Cat_Genera_Reporte_Cumpleanios_Del_Mes ", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
		}
				
		
	}

	
		public static void main(String args[]){
			try{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new Cat_Reporte_De_Cumpleanios_Del_Mes().setVisible(true);
			}catch(Exception e){	}
		}

	}

