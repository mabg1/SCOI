package Cat_Reportes;

import javax.swing.JFrame;

import Conexiones_SQL.Generacion_Reportes;


@SuppressWarnings("serial")
public class Cat_Reporte_De_Corte_De_Caja extends JFrame {

	public Cat_Reporte_De_Corte_De_Caja(String folio_corte) {
		String basedatos="2.26";
		String vista_previa_reporte="no";
		int vista_previa_de_ventana=0;
		String comando="";
		String reporte = "";
		
		 comando = "exec sp_select_reporte_corte_de_caja '"+folio_corte+"';";
		 reporte="Obj_Reporte_De_Corte_De_Caja.jrxml";
		 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
		 
		 comando = "exec sp_Reporte_de_Corte_por_Dia_por_Asignacion '"+folio_corte+"';";
		 reporte="Obj_Reporte_De_Corte_De_Caja_Por_Dia.jrxml";
		 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
	}
}