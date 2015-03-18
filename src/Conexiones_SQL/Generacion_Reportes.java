package Conexiones_SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Obj_Administracion_del_Sistema.Obj_Usuario;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Generacion_Reportes {
	String reporte = "";
	String comando="";
	String vista_previa_reporte="";
	String vista_previa_de_ventana="";
	String basedatos="";
		
		public void  Reporte(String reporte,String comando,String basedatos,String vista_previa_reporte, int vista_previa_de_ventana){
			    Obj_Usuario usuario = new Obj_Usuario();
                String query =comando ;
				Statement stmt = null;
				
				if (vista_previa_de_ventana==0){
					vista_previa_reporte=usuario.buscar(usuario.LeerSession().getFolio()).getVista_previa_impresion();
				}
				
				
				if (basedatos=="2.200"){
					try {
						stmt =  new Connexion().conexion_IZAGAR().createStatement();
					} catch (SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error En La Coneccion Con el Servidor:"+basedatos+" 1 \n "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try {
						stmt =  new Connexion().conexion().createStatement();
					} catch (SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error En La Coneccion Con el Servidor:"+basedatos+" 1 \n "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE,new ImageIcon("Imagen/configuracion-de-usuario-de-configuracion-de-la-herramienta-de-ocio-icono-7245-64.png"));
					}
				}
				
			try {
			    ResultSet rs = stmt.executeQuery(query);
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\"+reporte);
				JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
				@SuppressWarnings({ "rawtypes", "unchecked" })
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
				
				if (vista_previa_reporte.equals("si")){
                  //Vista Previa
				   JasperViewer.viewReport(print, false);
				}else{
					  if(vista_previa_reporte.equals("no")){
						//imprimir con seleccion de impresora
						 JasperPrintManager.printReport(print, true);
				      }else{ 
				    	 if(vista_previa_reporte.equals("id")){
  					   //imprimime directo en impresora determinada en la pc
					      JasperPrintManager.printReport(print, true);
		    	       }else{
					  JOptionPane.showMessageDialog(null, "Error en La Clase Generacion Reportes En El Valor De La Variable Vista Previa \n Valor No Identificado De Los Siguientes: <<<si,no,id>>> \n Valor Recibido:"+vista_previa_reporte,"Avisa Al Administrador Del Sistema", JOptionPane.ERROR_MESSAGE,new ImageIcon("Imagen/configuracion-de-usuario-de-configuracion-de-la-herramienta-de-ocio-icono-7245-64.png"));
                      return;
		    	       }
		     	 }
			 	 }
				return;
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				JOptionPane.showMessageDialog(null, "Error Al Intentar Generar El Reporte: \n En La Clase Generacion Reportes Reporte:"+reporte+"\n Comando: "+comando+"\n Mensaje Exception: "+e1.getMessage(), "Avisa Al Administrador Del Sistema", JOptionPane.ERROR_MESSAGE,new ImageIcon("Imagen/configuracion-de-usuario-de-configuracion-de-la-herramienta-de-ocio-icono-7245-64.png"));
			}
		}
}
