package Cat_Reportes;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JFrame;

import Conexiones_SQL.Connexion;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class Cat_Reporte_De_Depositos_Cortes extends JFrame {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cat_Reporte_De_Depositos_Cortes(String folio_corte) {
			
			String query_efectivo = "exec sp_select_reporte_deposito_de_cortes '"+folio_corte+"';";
			Statement stmt_efectivo = null;
			
		try {	
//			efectivo
			stmt_efectivo =  new Connexion().conexion().createStatement();
		    ResultSet rs_efectivo = stmt_efectivo.executeQuery(query_efectivo);
		    
			JasperReport report_efectivo = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Depositos_Para_Cortes.jrxml");
			JRResultSetDataSource resultSetDataSource_efectivo = new JRResultSetDataSource(rs_efectivo);
			JasperPrint print_efectivo = JasperFillManager.fillReport(report_efectivo, new HashMap(), resultSetDataSource_efectivo);
			JasperViewer.viewReport(print_efectivo, false);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

