package Obj_Lista_de_Raya;

import java.sql.SQLException;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;

public class Obj_Totales_De_Cheque {
	int numero_listaraya;
	String establecimiento;
	String nomina;
	String pago_linea;
	String cheque_nomina;
	String lista_raya;
	String diferencia;
	String fecha;
	private boolean autorizar;
	
	public boolean isAutorizar() {
		return autorizar;
	}

	public void setAutorizar(boolean autorizar) {
		this.autorizar = autorizar;
	}

	public Obj_Totales_De_Cheque(){
		this.numero_listaraya=0; 		this.establecimiento="";
		this.nomina="";		this.pago_linea="";
		this.cheque_nomina="";		this.lista_raya="";
		this.diferencia="";		this.fecha="";
	}
	
	public int getNumero_listaraya() {
		return numero_listaraya;
	}

	public void setNumero_listaraya(int numero_listaraya) {
		this.numero_listaraya = numero_listaraya;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getNomina() {
		return nomina;
	}

	public void setNomina(String nomina) {
		this.nomina = nomina;
	}

	public String getPago_linea() {
		return pago_linea;
	}

	public void setPago_linea(String pago_linea) {
		this.pago_linea = pago_linea;
	}

	public String getCheque_nomina() {
		return cheque_nomina;
	}

	public void setCheque_nomina(String cheque_nomina) {
		this.cheque_nomina = cheque_nomina;
	}

	public String getLista_raya() {
		return lista_raya;
	}

	public void setLista_raya(String lista_raya) {
		this.lista_raya = lista_raya;
	}

	public String getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public int MaxListaRaya(){
		return new BuscarSQL().MaximoListaRaya();
	}
	
	public int returnMaximo(){
		return new BuscarSQL().getMaximoNomina();
	}
	
	public boolean Guardar(){ return new GuardarSQL().guardar_total_cheques(this); }
	
	public boolean Actualizar(String Establecimiento, int Folio){ return new ActualizarSQL().Actualizar(this,Establecimiento,Folio); }
	
	public boolean actualizar(){ return new ActualizarSQL().Autorizar_Nomina(this); }	
	
	public Obj_Totales_De_Cheque buscar_autorizacion(){
		try {
			return new BuscarSQL().Autorizar_nomina();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}
