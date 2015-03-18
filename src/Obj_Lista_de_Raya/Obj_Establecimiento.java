package Obj_Lista_de_Raya;

import java.sql.SQLException;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.Cargar_Combo;
import Conexiones_SQL.GuardarSQL;

public class Obj_Establecimiento {
	private int folio;
	private String establecimiento;
	private String abreviatura;
	private String serie;
	public int grupo_cheque;
	public int status;
	public int grupo_cortes;
	public int grupo_permitir_nc;
	
	public String domicilio;
	public String razon_social;
	public String rfc;
	public String telefono;
	
//	public Obj_Establecimiento(){
//		folio=0;
//		establecimiento="";
//		abreviatura="";
//		serie="";
//		grupo_cheque="";
//		status="";
//	}

	

		public String[] Combo_Establecimiento_Empleados() {
		try {
			return new Cargar_Combo().Establecimiento_Empleado("tb_establecimiento");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public int getFolio() {
			return folio;
		}

		public void setFolio(int folio) {
			this.folio = folio;
		}

		public String getEstablecimiento() {
			return establecimiento;
		}

		public void setEstablecimiento(String establecimiento) {
			this.establecimiento = establecimiento;
		}

		public String getAbreviatura() {
			return abreviatura;
		}

		public void setAbreviatura(String abreviatura) {
			this.abreviatura = abreviatura;
		}

		public String getSerie() {
			return serie;
		}

		public void setSerie(String serie) {
			this.serie = serie;
		}

		public int getGrupo_cheque() {
			return grupo_cheque;
		}

		public void setGrupo_cheque(int grupo_cheque) {
			this.grupo_cheque = grupo_cheque;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	public String[] Combo_Establecimiento_Entysal() {
		try {
			return new Cargar_Combo().Establecimiento_Empleado_Entysal("establecimientos");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] Combo_Eq_Trabajo() {
		try {
			return new Cargar_Combo().EquipoTrabajo("tb_equipo_trabajo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] Combo_Jefatura() {
		try {
			return new Cargar_Combo().Jefatura("tb_jefatura");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public String[] Combo_Establecimiento(){
		try {
			return new Cargar_Combo().Establecimiento("tb_establecimiento");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; }
	
	public String[] Combo_Establecimiento201(){
		try {
			return new Cargar_Combo().Establecimiento("establecimientos");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; }
	
	public String[] Combo_Establecimiento_desc(){
		try {
			return new Cargar_Combo().Establecimiento_desc("establecimientos");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; }
	
	public String[] Combo_Establecimiento_Cajeras(){
		try {
			return new Cargar_Combo().Establecimiento_Caja();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; }
	
	public Obj_Establecimiento buscar_estab(String nombre){
		try{
			return new BuscarSQL().Establecimiento_buscar_folio_por_nombre(nombre); 
		} catch(SQLException e){
			
		}
		return null;
	}
	
	public Obj_Establecimiento buscar_estab(int folio){
		try{
			return new BuscarSQL().Establ_buscar_nombre(folio); 
		} catch(SQLException e){
			
		}
		return null;
	}

	public int getGrupo_cortes() {
		return grupo_cortes;
	}

	public void setGrupo_cortes(int grupo_cortes) {
		this.grupo_cortes = grupo_cortes;
	}

	public int getGrupo_permitir_nc() {
		return grupo_permitir_nc;
	}

	public void setGrupo_permitir_nc(int grupo_permitir_nc) {
		this.grupo_permitir_nc = grupo_permitir_nc;
	}
	
	////////////////////////////////////////////////////////////////////////////////

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Obj_Establecimiento buscar(int folio) { 
		try {
			return new BuscarSQL().Establecimiento(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public Obj_Establecimiento buscar_existe_nombre_establecimiento(String nombre){
		try{ return new BuscarSQL().buscar_nombre_establecimiento(nombre); 
		         } catch(SQLException e){		}
	  	return null;
	}
	
	public Obj_Establecimiento buscar_existe_abreviatura_establecimiento(String abreviatura){
		try{ return new BuscarSQL().buscar_existe_abreviatura_establecimiento(abreviatura); 
		         } catch(SQLException e){		}
	  	return null;
	}
	
	public Obj_Establecimiento buscar_existe_serie_establecimiento(String serie){
		try{ return new BuscarSQL().buscar_existe_serie_establecimiento(serie); 
		         } catch(SQLException e){		}
	  	return null;
	}
	
	
	public boolean actualizar(int folio){ 
		return new ActualizarSQL().Establecimiento(this,folio); }
	
	public boolean guardar(){
		return new GuardarSQL().Guardar_Establecimiento(this); }
	
	
	public String[] Combo_Grupo_Corte(){
		try {
			return new Cargar_Combo().GruposDeCortes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public String[] Combo_Establecimiento_Concentrado(){
		try {
			return new Cargar_Combo().EstablecimientoConcentrado();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
