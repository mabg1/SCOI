package Obj_Auditoria;

import java.sql.SQLException;

import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;

public class Obj_Retiros_Cajeros {
	
	Integer folio_empleado;
	String nombre;
	String puesto;
	String establecimiento;
	String asignacion;
	String pc;
	float  importe_total;
	
	Integer folio_supervisor;
	String nombre_Supervisor;
	String existe_supervisor;
	String clave;
	
	float retiro_cajero;
	String folio_retiro;
	
	public float getRetiro_cajero() {
		return retiro_cajero;
	}

	public void setRetiro_cajero(float retiro_cajero) {
		this.retiro_cajero = retiro_cajero;
	}

	public String getFolio_retiro() {
		return folio_retiro;
	}

	public void setFolio_retiro(String folio_retiro) {
		this.folio_retiro = folio_retiro;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getExiste_supervisor() {
		return existe_supervisor;
	}

	public void setExiste_supervisor(String existe_supervisor) {
		this.existe_supervisor = existe_supervisor;
	}

	public Integer getFolio_supervisor() {
		return folio_supervisor;
	}

	public void setFolio_supervisor(Integer folio_supervisor) {
		this.folio_supervisor = folio_supervisor;
	}

	public String getNombre_Supervisor() {
		return nombre_Supervisor;
	}

	public void setNombre_Supervisor(String nombre_Supervisor) {
		this.nombre_Supervisor = nombre_Supervisor;
	}

	public float getImporte_total() {
		return importe_total;
	}

	public void setImporte_total(float importe_total) {
		this.importe_total = importe_total;
	}

	public float getImporte_retiros_guardados() {
		return importe_retiros_guardados;
	}

	public void setImporte_retiros_guardados(float importe_retiros_guardados) {
		this.importe_retiros_guardados = importe_retiros_guardados;
	}

	float  importe_retiros_guardados;
	
	public Integer getFolio_empleado() {
		return folio_empleado;
	}


	public void setFolio_empleado(Integer folio_empleado) {
		this.folio_empleado = folio_empleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
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


	public String getPc() {
		return pc;
	}


	public void setPc(String pc) {
		this.pc = pc;
	}


	public Obj_Retiros_Cajeros buscarEmpleado(Integer folio_empleado){ 
		try {
			return new BuscarSQL().datos_cajero(folio_empleado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public Obj_Retiros_Cajeros buscarEmpleado_para_ahorro_cte(Integer folio_empleado){ 
		try {
			return new BuscarSQL().datos_cajero_para_ahorro_cte(folio_empleado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public Obj_Retiros_Cajeros buscarSupervisor(String clave){ 
		try {
			return new BuscarSQL().datos_supervisor_retiro(clave);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public String guardar(String Establecimiento,int Folio_empleado,int folio_supervisor,float importe_retiro,String Asignacion){
		return new GuardarSQL().Guardar_Retiro_Cajero(Establecimiento,Folio_empleado,folio_supervisor, importe_retiro,Asignacion); 
		   }
	
	public String guardar_sesion(String Establecimiento,int Folio_empleado){
		return new GuardarSQL().Guardar_Sesion_Cajero(Establecimiento,Folio_empleado); 
		   }



}
