package Obj_Matrices;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;



public class Obj_Unidades_de_Inspeccion {
	private int folio;
	private String unidades_de_inspeccion;
	private String abreviatura;
	private int status;
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public String getunidades_de_inspeccion() {
		return unidades_de_inspeccion;
	}
	public void setunidades_de_inspeccion(String unidades_de_inspeccion) {
		this.unidades_de_inspeccion = unidades_de_inspeccion;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Obj_Unidades_de_Inspeccion buscar(int folio) {
		return new BuscarSQL().ExisteUnidaddeInspeccion(folio);	}
	
	public boolean actualizar(int folio){ 
		return new 	ActualizarSQL().Unidades_de_Inspeccion(this,folio); }

	public boolean guardar(){ 
		 return new GuardarSQL().Guardar_Unidad_de_Inspeccion(this); }
	
}
