package Obj_Auditoria;

import java.sql.SQLException;

import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;

public class Obj_Movimiento_De_Asignacion {
	int folio_empleado;
	String empleado;
	String establecimiento;
	String asignacion;
	String fechaIn;
	String fechaFin;
	int status;
	
	public Obj_Movimiento_De_Asignacion(){
		this.folio_empleado=0;	this.empleado="";	this.establecimiento="";
		this.asignacion="";		this.fechaIn="";	this.fechaFin="";
		this.status=0;
	}
	
	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folio_empleado) {
		this.folio_empleado = folio_empleado;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(String asignacion) {
		this.asignacion = asignacion;
	}

	public String getFechaIn() {
		return fechaIn;
	}

	public void setFechaIn(String fechaIn) {
		this.fechaIn = fechaIn;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Obj_Movimiento_De_Asignacion buscarAsignacion(int folio_empleado,String establecimiento){
		try {
			return new BuscarSQL().buscar_asignacion_para_asignar(folio_empleado,establecimiento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean buscar_empleado_vigente_en_asignacion(int folio_empleado, String establecimiento){
		return new BuscarSQL().buscar_emp_vigente_en_asignacion(folio_empleado,establecimiento);
	}
	
	public boolean guardarAsignacion(){
		return new GuardarSQL().Guardar_Asignacion_De_Cajero(this);
	}
}
