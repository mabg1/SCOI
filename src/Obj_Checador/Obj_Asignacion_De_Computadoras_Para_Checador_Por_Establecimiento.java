package Obj_Checador;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;


public class Obj_Asignacion_De_Computadoras_Para_Checador_Por_Establecimiento {
	private int folio;
	private String Nombre_Pc;
	private String Establecimiento;
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public String getNombre_Pc() {
		return Nombre_Pc;
	}
	public void setNombre_Pc(String nombre_Pc) {
		Nombre_Pc = nombre_Pc;
	}
	public String getEstablecimiento() {
		return Establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		Establecimiento = establecimiento;
	}
	public int getStatus() {
		return status;
	}

	private int status;

	
	
	
	public void setStatus(int status) {
		this.status = status;
	}
	public Obj_Asignacion_De_Computadoras_Para_Checador_Por_Establecimiento buscar(int folio) {
		return new BuscarSQL().Existepc_establecimiento(folio);	}
	
	public boolean actualizar(int folio){ 
		return new 	ActualizarSQL().PCAsignadasaEstab(this,folio); }
	

	public boolean guardar(){ 
		 return new GuardarSQL().Guardar_Nombre_PC(this); }
	
}
