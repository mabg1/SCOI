package Obj_Contabilidad;

import java.io.File;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;


public class Obj_Proveedores {
	 String folio_factura;
	 String fecha;
	 String cod_prv;
	 String proveedor;
	 Boolean status;

	 public Obj_Proveedores(){
		this.folio_factura=""; this.fecha=""; this.cod_prv ="";this.proveedor=""; this.status=false;
	}
	
	public String getFolio_factura() {
		return folio_factura;
	}


	public void setFolio_factura(String folio_factura) {
		this.folio_factura = folio_factura;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getCod_prv() {
		return cod_prv;
	}


	public void setCod_prv(String cod_prv) {
		this.cod_prv = cod_prv;
	}


	public String getProveedor() {
		return proveedor;
	}


	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public Obj_Proveedores buscar(String folio_factura, String cod_prv){
		try {
			return new BuscarSQL().Buscar_factura_proveedor_control_xml(folio_factura,cod_prv);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Objeto Obj_Proveedores  en la funcion Buscar SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
		return null; 
	}
	
	public boolean guardar(){
		return new GuardarSQL().Guardar_Control_Factura_xml(this);
		}
	
	public boolean actualizar(String folio_Factura_Editada){
		return new ActualizarSQL().Factura_Provedores_xml(this,folio_Factura_Editada); 
		}
	
	public boolean marcar_recibido_factura(String cod_prov_recibido, String folio_factura_recibido,String tipo_archivo,File xml_pdf){
		return new ActualizarSQL().marcar_c_recibido_factura(cod_prov_recibido, folio_factura_recibido,tipo_archivo,xml_pdf); 
		}
	
}
