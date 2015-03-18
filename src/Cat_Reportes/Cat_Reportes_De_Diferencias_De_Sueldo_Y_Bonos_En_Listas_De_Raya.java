package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import Cat_Lista_de_Raya.Cat_Filtro_De_Listas_De_Raya_Pasadas;
import Conexiones_SQL.Connexion;
import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Reportes_De_Diferencias_De_Sueldo_Y_Bonos_En_Listas_De_Raya extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new Componentes().text(new JTextField(), "Teclee A Partir De Que Lista De Raya Se Va a Hacer La Comparacion:", 15, "String");
	
	JButton btnReporte_porfecha = new JButton("",new ImageIcon("imagen/orange-folder-saved-search-icone-8197-16.png"));
	JButton btnReporte_actual = new JButton("",new ImageIcon("imagen/diferiencia_de_sueldos_entre_listas_de_raya2_16.png"));
    JButton btnSeleccionLR =new JButton("",new ImageIcon ("imagen/Filter-List-icon16.png"));
	
	JButton btngenerar = new JButton("Generar",new ImageIcon("imagen/buscar.png"));
	
	int tipo_Reporte = 0;
	
	public Cat_Reportes_De_Diferencias_De_Sueldo_Y_Bonos_En_Listas_De_Raya(){
		setSize(335,280);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Reportes De Infonavit De Lista De Raya");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/diferiencia_de_sueldos_entre_listas_de_raya2_64.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Seleccione el Tipo de Reporte"));
	
		btnReporte_porfecha.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Diferencias De Sueldos y Bonos Desde Una Lista De Raya Determinada</p></CENTER></FONT>" +
				"</html>");	
		
		btnReporte_actual.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Diferencias De Sueldos y Bonos De Todas Las Listas De Raya</p></CENTER></FONT></html>");	
		
		panel.add(btnReporte_porfecha).setBounds(15,25,280,40);
		panel.add(btnReporte_actual).setBounds(15,75,280,40);
		
		panel.add(new JLabel("A Partir De la Lista De Raya:")).setBounds(15,145,200,20);		
		panel.add(txtFolio).setBounds(150,145,125,20);
		panel.add(btnSeleccionLR).setBounds(275,145,20,20);
		panel.add(btngenerar).setBounds(115,190,120,25);
	    
	    txtFolio.setEditable(false);
	    btngenerar.setEnabled(false);
	    btnSeleccionLR.setEnabled(false);
	    
		cont.add(panel);
		btngenerar.addActionListener(opGenerar);
		btnReporte_porfecha.addActionListener(opReporte_Por_Folio);
		btnReporte_actual.addActionListener(opReporte_Actual);
		btnSeleccionLR.addActionListener(opfiltroLR);
		txtFolio.addKeyListener(generar_enter);
		
     	///filtro de Listas de Raya
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "buscarLR");
          getRootPane().getActionMap().put("buscarLR", new AbstractAction(){
         public void actionPerformed(ActionEvent e)
         {                 	    btnSeleccionLR.doClick();
       	    }
     });
	}
	
	ActionListener opReporte_Por_Folio = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(true);
			btngenerar.setEnabled(true);
			btngenerar.setEnabled(true);
			btnSeleccionLR.setEnabled(true);
			tipo_Reporte=2;
			txtFolio.setText("");
			txtFolio.requestFocus();
		}
	};

	ActionListener opReporte_Actual = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(false);
			btngenerar.setEnabled(false);
			btnSeleccionLR.setEnabled(false);
			txtFolio.setText("");
			Reporte_De_Lista_De_Raya_Actual();
		}
	};
	
	ActionListener opfiltroLR = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			 new Cat_Filtro_De_Listas_De_Raya_Pasadas(5).setVisible(true);
		}
	};
	
	KeyListener generar_enter = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btngenerar.doClick();
			}
		}
	};
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  void obtiene_lista_de_raya_selecionada(final Integer folio){
	 String	Foliorecibido = folio+"";
		String query = "exec sp_Reporte_De_Diferencias_De_Sueldo_Y_Bonos_De_Listas_De_Raya 'Diferiencias De Sueldo y Bono De Empleado Vigentes Desde La Lista De Raya Selecionada','"+Foliorecibido+"'";
		Statement stmt = null;
			try {
				stmt =  new Connexion().conexion().createStatement();
			    ResultSet rs = stmt.executeQuery(query);
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Diferiencias_De_Sueldo_Entre_Listas_De_Raya.jrxml");
				JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
				JasperViewer.viewReport(print, false);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
				JOptionPane.showMessageDialog(null, "Error en Generar Reporte de Diferencias de Sueldo y Bono Entre Listas De Raya procedure Reporte_De_Diferencias_De_Sueldo_Y_Bonos_De_Listas_De_Raya SQLException: \n "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
	   }	
			txtFolio.setText(Foliorecibido);
			return;	
	}
	
	ActionListener opGenerar = new ActionListener() {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e) {
			
			if(tipo_Reporte==2){
						if(!txtFolio.getText().equals("")){
							String query = "exec sp_Reporte_De_Diferencias_De_Sueldo_Y_Bonos_De_Listas_De_Raya 'Diferiencias De Sueldo y Bono De Empleado Vigentes Desde La Lista De Raya Selecionada','"+Integer.valueOf(txtFolio.getText())+"'";
							Statement stmt = null;
								try {
									stmt =  new Connexion().conexion().createStatement();
								    ResultSet rs = stmt.executeQuery(query);
									JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Diferiencias_De_Sueldo_Entre_Listas_De_Raya.jrxml");
									JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
									JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
									JasperViewer.viewReport(print, false);
								} catch (Exception e2) {
									System.out.println(e2.getMessage());
									JOptionPane.showMessageDialog(null, "Error en Generar Reporte de Diferencias de Sueldo y Bono Entre Listas De Raya procedure Reporte_De_Diferencias_De_Sueldo_Y_Bonos_De_Listas_De_Raya SQLException: \n "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
								}	
								return;	
					    }
	      	}
		}
	};
	
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void Reporte_De_Lista_De_Raya_Actual() {
		String query = "exec sp_Reporte_De_Diferencias_De_Sueldo_Y_Bonos_De_Listas_De_Raya 'Diferiencias De Sueldo y Bono De Empleado Vigentes De Todas Las Listas De Raya','0'" ;
		Statement stmt = null;
			try {
				stmt =  new Connexion().conexion().createStatement();
			    ResultSet rs = stmt.executeQuery(query);
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Diferiencias_De_Sueldo_Entre_Listas_De_Raya.jrxml");
				JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error en Generar Reporte de Diferencias de Sueldo y Bono Entre Listas De Raya procedure Reporte_De_Diferencias_De_Sueldo_Y_Bonos_De_Listas_De_Raya SQLException: \n "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_De_Diferencias_De_Sueldo_Y_Bonos_En_Listas_De_Raya().setVisible(true);
		}catch(Exception e){	}
	}
}
