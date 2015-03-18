package Cat_IZAGAR;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import Conexiones_SQL.Connexion;
import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Consulta_E_Impresion_De_Vouchers extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	JTextField txtFolio = new Componentes().text(new JTextField(),"Teclea el Folio del Ticket o Remision", 50, "String");
 	JTextField txtNombre_Completo = new JTextField();
	JButton btnconsultavoucher = new JButton("Buscar Voucher");
	
	String transaccion[] = {"Remision","Ticket"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbtransaccion = new JComboBox(transaccion);
	
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	
 	public Cat_Consulta_E_Impresion_De_Vouchers() {
 		blackline = BorderFactory.createLineBorder(new java.awt.Color(100,100,100));
 		
 		this.campo.setBorder(BorderFactory.createTitledBorder(blackline, "Consulta e Impresion De Vouchers"));
 		
		setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/cat_nomina_icon&16.png"));
		setTitle("Consulta E Impresion de Vouchers");
		
		setSize(400,180);
		setResizable(false);
		setLocationRelativeTo(null);
		
		campo.add(new JLabel("Teclee el Folio Remision/Ticket:")).setBounds(45,30,160,20);
		campo.add(txtFolio).setBounds(200,30,120,20);
		
		campo.add(new JLabel("Selecciona Remision o Ticket:")).setBounds(45,60,150,20);
		campo.add(cmbtransaccion).setBounds(200,60,120,20);
		
		campo.add(btnconsultavoucher).setBounds(200,90,120,20);
		
		cont.add(campo);
		btnconsultavoucher.addActionListener(buscar);
		txtFolio.addActionListener(buscar);
	}
	
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese El No. De Folio","Mensaje",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				try {	
					Actualizar_Autorizaciones_Bancarias_de_Otros_Servidores();
					int transaccion =0;
					switch(cmbtransaccion.getSelectedIndex()){
					case 0: transaccion=37; break;
					case 1: transaccion=38; break;				
					};
					
					String query = "exec sp_consulta_de_datos_voucher '"+txtFolio.getText().toUpperCase().trim()+"',"+transaccion ;
					Statement stmt = null;
					try {
						stmt =  new Connexion().conexion_IZAGAR().createStatement();
					    ResultSet rs = stmt.executeQuery(query);
						JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\IZAGAR_Obj_Reportes\\Obj_Reporte_IZAGAR_de_Impresion_de_Voucher.jrxml");
						JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
						@SuppressWarnings({ "rawtypes", "unchecked" })
						JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
						JasperViewer.viewReport(print, false);
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
						JOptionPane.showMessageDialog(null, "Error en impresion voucher  en la funcion buscar  procedimiento almacenado sp_consulta_de_datos_voucher SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (NumberFormatException e1) {
					e1.printStackTrace(); 
					JOptionPane.showMessageDialog(null, "Error en impresion voucher  en la funcion buscar  procedimiento almacenado sp_consulta_de_datos_voucher SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);

				} 			
			}
		}
	};
	
	public void Actualizar_Autorizaciones_Bancarias_de_Otros_Servidores(){
		
//		boolean actualizo = false;
		
		String query = "exec Actualizar_Autorizaciones_Bancarias";
		PreparedStatement pstmt = null;
		Connection con = new Connexion().conexion_IZAGAR();
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			con.commit();
//			actualizo = true;
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en impresion voucher  en la funcion buscar  procedimiento almacenado Actualizar_Autorizaciones_Bancarias SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);

			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en impresion voucher  en la funcion buscar  procedimiento almacenado Actualizar_Autorizaciones_Bancarias SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en impresion voucher  en la funcion buscar  procedimiento almacenado Actualizar_Autorizaciones_Bancarias SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}		
//		return actualizo;
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Consulta_E_Impresion_De_Vouchers().setVisible(true);
		}catch(Exception e){	}
	}
}