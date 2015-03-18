package Obj_Lista_de_Raya;

import java.sql.SQLException;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;

public class Obj_Autorizacion_Finanzas {
	private boolean autorizar;
	
	public Obj_Autorizacion_Finanzas(){
		this.autorizar=false;
	}

	public boolean isAutorizar() {
		return autorizar;
	}

	public void setAutorizar(boolean autorizar) {
		this.autorizar = autorizar;
	}
	
	public Obj_Autorizacion_Finanzas buscar(){
		try {
			return new BuscarSQL().Autorizar_Finanzas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(){ return new ActualizarSQL().Autorizar_Finanzas(this); }	
	
}
