package Obj_Evaluaciones;

import java.sql.SQLException;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.Cargar_Combo;
import Conexiones_SQL.GuardarSQL;

public class Obj_Jefatura {
	
	private int folio;
	private String descripcion;
	private Boolean status;
	
	public Obj_Jefatura(){
		this.folio=0; this.descripcion=""; this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Obj_Jefatura buscar(int folio){
		try {
			return new BuscarSQL().Jefatura(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Jefatura(this); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Jefatura(this,folio); }
	
	public Obj_Jefatura buscar_nuevo(){
		try {
			return new BuscarSQL().Jefatura_Nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	//Cargamos el combo jefatura
	public String[] Combo_Jefatura(){ 
		try {
			return new Cargar_Combo().Jefatura("tb_jefatura");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
