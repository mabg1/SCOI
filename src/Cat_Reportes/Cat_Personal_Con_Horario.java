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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import Conexiones_SQL.Connexion;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class Cat_Personal_Con_Horario extends JFrame{

	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	JButton btngenerar = new JButton("Generar");
	
	public Cat_Personal_Con_Horario(){
	
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/plan-icono-5073-16.png"));
		this.setTitle("Reporte de Plantilla");
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		panel.setBorder(BorderFactory.createTitledBorder("Plantilla de Personal con Horario"));
		
		panel.add(cmbEstablecimiento).setBounds(10, 50, 220, 20);
		panel.add(btngenerar).setBounds(70, 120, 100, 20);
		
		cont.add(panel);
		
		btngenerar.addActionListener(opGenerar);
		
		this.setSize(250, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	ActionListener opGenerar = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			String query = "exec sp_Reporte_de_Plantilla_de_Personal_con_Horario '"+cmbEstablecimiento.getSelectedItem()+"';";
				Statement stmt = null;
				try {
					stmt =  new Connexion().conexion().createStatement();
				    ResultSet rs = stmt.executeQuery(query);
				    
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Personal_Con_Horario.jrxml");
					JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
					@SuppressWarnings({ "rawtypes", "unchecked" })
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
					JasperViewer.viewReport(print, false);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
			
		
	};
	
	public static void main(String [] arg){
		new Cat_Personal_Con_Horario().setVisible(true);
	}
}
