package Cat_Reportes;

import java.util.HashMap;

import Conexiones_SQL.Connexion;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Cat_Reporte_De_Empleados_No_Contratables {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Cat_Reporte_De_Empleados_No_Contratables() {
			try {
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Empleados_No_Contratables.jrxml");
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

