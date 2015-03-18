package Obj_Matrices;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;




public class Obj_Aspectos_De_La_Etapa {
	private int folio;
	private String aspecto;
	private String abreviatura;
	private int status;
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public String getAspecto() {
		return aspecto;
	}
	public void setAspecto(String aspecto) {
		this.aspecto = aspecto;
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
	public Obj_Aspectos_De_La_Etapa buscar(int folio) {
		return new BuscarSQL().ExisteAspecto(folio);	}
	
	public boolean actualizar(int folio){ 
		return new 	ActualizarSQL().Aspectos_de_la_Etapa(this,folio); }
	
	public boolean guardar(){ 
		 return new GuardarSQL().Guardar_Aspecto_de_la_Etapa(this); }
	
}
