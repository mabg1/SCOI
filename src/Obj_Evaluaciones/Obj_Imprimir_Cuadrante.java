package Obj_Evaluaciones;

import java.sql.SQLException;

import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.BuscarTablasModel;


public class  Obj_Imprimir_Cuadrante {
	
	public Object[][] Obj_Obtener_Empleados_Cuadrantes(){
    	return new BuscarTablasModel().tabla_model_filtro_Obtener_Emp_imprimir_cuadrantes();
	}

	public Object Obj_Obtener_folio_empleado_buscar (String nombre) {
		try {
			return new BuscarSQL().Obj_Obtener_Folio_Empleado(nombre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
	
	

	

