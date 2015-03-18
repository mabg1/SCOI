package Obj_Compras;

import java.sql.SQLException;

import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;


public class Obj_Cotizaciones_De_Un_Producto {
	
	String Cod_Prod;
	String Descripcion_Prod;
	double Ultimo_Costo;
	double Costo_Promedio;
	double Existencia_Cedis;
	double Existencia_Total;
	String folio_compra;
	String Cod_Prv;
	String Proveedor;
	double Costo_Nuevo;
	double    Cantidad_Requerida;
	


	String Notas_Negociacion;
	
	String fecha;
	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCod_Prod() {
		return Cod_Prod;
	}

	public void setCod_Prod(String cod_Prod) {
		Cod_Prod = cod_Prod;
	}

	public String getDescripcion_Prod() {
		return Descripcion_Prod;
	}

	public void setDescripcion_Prod(String descripcion_Prod) {
		Descripcion_Prod = descripcion_Prod;
	}

	public double getUltimo_Costo() {
		return Ultimo_Costo;
	}

	public void setUltimo_Costo(double ultimo_Costo) {
		Ultimo_Costo = ultimo_Costo;
	}

	public double getCosto_Promedio() {
		return Costo_Promedio;
	}

	public void setCosto_Promedio(double costo_Promedio) {
		Costo_Promedio = costo_Promedio;
	}

	public double getExistencia_Cedis() {
		return Existencia_Cedis;
	}

	public void setExistencia_Cedis(double existencia_Cedis) {
		Existencia_Cedis = existencia_Cedis;
	}

	public double getExistencia_Total() {
		return Existencia_Total;
	}

	public void setExistencia_Total(double existencia_Total) {
		Existencia_Total = existencia_Total;
	}

	public String getCod_Prv() {
		return Cod_Prv;
	}

	public void setCod_Prv(String cod_Prv) {
		Cod_Prv = cod_Prv;
	}
	
	public String getFolio_compra() {
		return folio_compra;
	}

	public void setFolio_compra(String folio_compra) {
		this.folio_compra = folio_compra;
	}

	public String getProveedor() {
		return Proveedor;
	}

	public void setProveedor(String proveedor) {
		Proveedor = proveedor;
	}
	
	public double getCantidad_Requerida() {
		return Cantidad_Requerida;
	}

	public void setCantidad_Requerida(double cantidad_Requerida) {
		Cantidad_Requerida = cantidad_Requerida;
	}

	public double getCosto_Nuevo() {
		return Costo_Nuevo;
	}

	public void setCosto_Nuevo(double costo_Nuevo) {
		Costo_Nuevo = costo_Nuevo;
	}


	public String getNotas_Negociacion() {
		return Notas_Negociacion;
	}

	public void setNotas_Negociacion(String notas_Negociacion) {
		Notas_Negociacion = notas_Negociacion;
	}
	
	public boolean Existe_Producto(String cod_prod) throws SQLException{ 
		return new BuscarSQL().existe_Producto(cod_prod);
	}
	
	public Obj_Cotizaciones_De_Un_Producto buscardatos_producto(String cod_prod){ 
		try {
			return new BuscarSQL().datos_producto(cod_prod);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public Obj_Cotizaciones_De_Un_Producto Hoymenos3meses(){ 
		try {
			return new BuscarSQL().Hoymenos3meses();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public boolean Guardar_Cotizacion(){ return new GuardarSQL().Guardar_Cotizacion_Producto(this); }
	


}
