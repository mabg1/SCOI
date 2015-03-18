package Obj_Punto_De_Venta;

import java.sql.SQLException;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.GuardarSQL;

public class Obj_Clientes {

	int folio_cliente;
	String nombre;
	String ap_paterno;
	String ap_materno;
	String direccion;
	String telefono;
	
	public Obj_Clientes() {
		this.folio_cliente=0;	this.nombre="";	this.ap_paterno="";	this.ap_materno="";	this.direccion="";	this.telefono="";
	}

	public int getFolio_cliente() {
		return folio_cliente;
	}

	public void setFolio_cliente(int folio_cliente) {
		this.folio_cliente = folio_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAp_paterno() {
		return ap_paterno;
	}

	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
	}

	public String getAp_materno() {
		return ap_materno;
	}

	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Object[][] get_tabla_model(){
		return new BuscarTablasModel().tabla_model_cliente();
	}
	
	public Obj_Clientes buscar_nuevo() throws SQLException{ return new BuscarSQL().Cliente_Nuevo(); }
	
	public Obj_Clientes buscar(int folio_cliente) throws SQLException{ return new BuscarSQL().Cliente(folio_cliente); }
	
	public boolean guardar(){ return new GuardarSQL().Guardar_Cliente(this); }
	
	public boolean actualizar(){ return new ActualizarSQL().Cliente(this); }
	
}
