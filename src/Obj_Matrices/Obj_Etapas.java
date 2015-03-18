package Obj_Matrices;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;


public class Obj_Etapas {
	private int folio;
	private String etapa;
	private String abreviatura;
	private int status;
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
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
	public Obj_Etapas buscar(int folio) {
		return new BuscarSQL().ExisteEtapa(folio);	}
	
	public boolean actualizar(int folio){ 
		return new 	ActualizarSQL().Etapas(this,folio); }

	public boolean guardar(){ 
		 return new GuardarSQL().Guardar_Etapa(this); }
	
}
