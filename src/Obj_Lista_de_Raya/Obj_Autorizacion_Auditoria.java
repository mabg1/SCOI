package Obj_Lista_de_Raya;

import java.sql.SQLException;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;

public class Obj_Autorizacion_Auditoria {
	private boolean autorizar;
	
	public Obj_Autorizacion_Auditoria(){
		this.autorizar=false;
	}

	public boolean isAutorizar() {
		return autorizar;
	}

	public void setAutorizar(boolean autorizar) {
		this.autorizar = autorizar;
	}
	
	public Obj_Autorizacion_Auditoria buscar(){
		try {
			return new BuscarSQL().Autorizar_Audi();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean actualizar(){ return new ActualizarSQL().Auditoria(this); }	
	
}
