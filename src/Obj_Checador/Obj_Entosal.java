package Obj_Checador;

import java.sql.SQLException;
import java.util.Vector;


import Conexiones_SQL.BuscarSQL;

public class Obj_Entosal {
	private String clave;
	 String Valor_Descanso;
	 String Valor_Pc;
	
	
	


	public String getClave() {
		return clave;
	}



	public void setClave(String clave) {
		this.clave = clave;
	}



	public String getValor_Descanso() {
		return Valor_Descanso;
	}



	public void setValor_Descanso(String valor_Descanso) {
		Valor_Descanso = valor_Descanso;
	}



	public String getValor_Pc() {
		return Valor_Pc;
	}



	public void setValor_Pc(String valor_Pc) {
		Valor_Pc = valor_Pc;
	}



	@SuppressWarnings("rawtypes")
	public Vector buscar_hora_entosal(int folio){
		try {
			
			return new BuscarSQL().buscar_entosal(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

	
	
	public Obj_Entosal buscar(){ 
		try {
			return new BuscarSQL().Entosal();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public boolean buscar_colicion(int folio){ 
		try {
			return new BuscarSQL().existeColisionTiempo(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public Obj_Entosal checar_dia_descanso(int folio){ 
			try {
				return new BuscarSQL().IntentaChecarDiaDescanso(folio);

			  
			     
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public boolean checadas_dia_dobla(int folio){ 
		try {
			return new BuscarSQL().obtener_checadas_dia_dobla(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean checa_salida_comer(int folio){ 
		try {
			return new BuscarSQL().valida_si_dobla_y_esta_saliendo_a_comer(folio);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@SuppressWarnings("rawtypes")
	public Vector Obj_Mensaje_respuesta(int folio) {
		try {

			return new BuscarSQL().obtener_mensaje_checador(folio);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	}


