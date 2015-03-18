package Obj_Lista_de_Raya;

import java.sql.SQLException;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.Cargar_Combo;
import Conexiones_SQL.GuardarSQL;

public class Obj_Sueldos {
	private int folio;
	private float sueldo;
	private int puesto;
	private boolean status;
	
	
	public Obj_Sueldos(){
		this.folio=0; this.sueldo=0; this.puesto=0; this.status=false;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String[] Combo_Sueldo(){
		try {
			return new Cargar_Combo().Sueldo("tb_sueldo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Obj_Sueldos buscar(int folio){
		try {
			return new BuscarSQL().Sueldo(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Sueldo(this); }
	
	public Obj_Sueldos buscar_nuevo() throws SQLException{ return new BuscarSQL().Sueldo_Nuevo(); }
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Sueldo(this,folio); }
	
}
