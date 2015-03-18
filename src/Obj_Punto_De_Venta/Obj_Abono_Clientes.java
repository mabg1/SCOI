package Obj_Punto_De_Venta;

import java.sql.SQLException;

import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.GuardarSQL;

public class Obj_Abono_Clientes {
	
	int folio;
	String ticket;
	int folio_cliente;
	String cajero;
	String asignacion;
	int status;
	String fecha_in;
	String fecha_fin;
	String fecha_liquidacion;
	String fecha_cancelacion;
	String establecimiento;
	
	double abono;
	double saldo;
	
	public Obj_Abono_Clientes(){
		this.folio=0;		this.ticket="";			this.folio_cliente=0;		this.cajero=""; 		this.asignacion="";	
		this.status=0;		this.fecha_in="";		this.fecha_fin="";			this.fecha_liquidacion="";	this.fecha_cancelacion="";
		this.abono=0;		this.saldo=0;			this.establecimiento="";
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getFolio_cliente() {
		return folio_cliente;
	}

	public void setFolio_cliente(int folio_cliente) {
		this.folio_cliente = folio_cliente;
	}

	public String getCajero() {
		return cajero;
	}

	public void setCajero(String cajero) {
		this.cajero = cajero;
	}

	public String getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFecha_in() {
		return fecha_in;
	}

	public void setFecha_in(String fecha_in) {
		this.fecha_in = fecha_in;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getFecha_liquidacion() {
		return fecha_liquidacion;
	}

	public void setFecha_liquidacion(String fecha_liquidacion) {
		this.fecha_liquidacion = fecha_liquidacion;
	}

	public String getFecha_cancelacion() {
		return fecha_cancelacion;
	}

	public void setFecha_cancelacion(String fecha_cancelacion) {
		this.fecha_cancelacion = fecha_cancelacion;
	}
	
	public double getAbono() {
		return abono;
	}

	public void setAbono(double abono) {
		this.abono = abono;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	//	guardar ticket y abonos
	public boolean guardarTickets(){ return new GuardarSQL().Guardar_Ticket(this); }
	
//	trae tabla de tickets
	public Object[][] get_tabla_tickets(int folio_cliente){
		return new BuscarTablasModel().tabla_model_tickets(folio_cliente);
	}
	
//	trae tabla de abonos
	public Object[][] get_tabla_abonos(String ticket){
		return new BuscarTablasModel().tabla_model_abonos(ticket);
	}
	
	public String nuevoTicket(String cavecera){
		return new BuscarSQL().nuevo_ticket(cavecera);
	}
	
//	buscar ultimo abono del ticket para su imprecion
	public Obj_Abono_Clientes buscar_ultimo_abono(String ticket){ 
		try {
			return new BuscarSQL().CapturaAbonoCliente_UltimiAbono(ticket);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}

}
