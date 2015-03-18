package Obj_Lista_de_Raya;

import java.sql.SQLException;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;



public class Obj_Conceptos_De_Extras_Para_Lista_De_Raya {
	private int folio;
	private String concepto;
	private String abreviatura;
	private String status;

	public Obj_Conceptos_De_Extras_Para_Lista_De_Raya(){
		this.folio=0; this.concepto=""; this.abreviatura =""; this.status="";
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Obj_Conceptos_De_Extras_Para_Lista_De_Raya buscar(int folio){
		try {
			return new BuscarSQL().Concepto_Extra(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	

	public Obj_Conceptos_De_Extras_Para_Lista_De_Raya buscar_nuevo(){
		try {
			return new BuscarSQL().Concepto_extra_nuevo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Concepto_Extra(this); }
	
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Concepto_extra(this,folio); }
	
}
