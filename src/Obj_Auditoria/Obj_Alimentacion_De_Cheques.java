package Obj_Auditoria;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.GuardarTablasModel;

public class Obj_Alimentacion_De_Cheques {

	String folio_corte;
	int folio_empleado;
	public Obj_Alimentacion_De_Cheques() {
		folio_corte="";
		folio_empleado=0;
	}
	public String getFolio_corte() {
		return folio_corte;
	}
	public void setFolio_corte(String folio_corte) {
		this.folio_corte = folio_corte;
	}
	public int getFolio_empleado() {
		return folio_empleado;
	}
	public void setFolio_empleado(int folio_empleado) {
		this.folio_empleado = folio_empleado;
	}
	
	
	public boolean GuardarTotalesDeCheques(int folio_usuario, Object[] tabla){
		return new GuardarTablasModel().tabla_model_alimentacion_totales_De_Cheques(this, folio_usuario, tabla);
	}
	
	public boolean ActualizarTotalesDeCheques(int folio_usuario,Object[] tabla){
		return new ActualizarSQL().tabla_model_alimentacion_totales_De_Cheques(this, folio_usuario, tabla);
	}
}
