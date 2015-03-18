package Obj_Lista_de_Raya;

import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.GuardarTablasModel;

public class Obj_Deducciones_Y_Percepciones_De_Lista_De_Raya {
	Object[][] tabla_model;
	
	public Obj_Deducciones_Y_Percepciones_De_Lista_De_Raya() {
		this.tabla_model = null;
	}

	public Object[][] getTabla_model() {
		return tabla_model;
	}

	public void setTabla_model(Object[][] tabla_model) {
		this.tabla_model = tabla_model;
	}
	
	public Object[][] get_tabla_model(){
		return new BuscarTablasModel().tabla_model_deduccion_y_percepcionesde_lista_de_raya();
	}
	
	public boolean guardar(Object[][] tabla){
		return new GuardarTablasModel().tabla_model_deduccion_y_percepcionesde_lista_de_raya(tabla);
	}

}
