package Obj_Lista_de_Raya;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;

public class Obj_Alimentacion_De_Vacaciones {
//	datos del empleado
	int folio_vacaciones;
	int folio_empleado;
	String empleado;
	String establecimiento;
	String puesto;
	String fecha_ingreso;
	String fecha_ingreso_imss;
	float salario_diario_integrado;
	String grupo_vacacional;
	int proximas_vacaciones;
	File imagen;
	
//	alimentacion de vacaciones
	int anios_a_disfrutar;
	String fecha_inicio;
	String fecha_final;
	float vacaciones;
	float prima_vacacional;
	float infonavit;
	float sueldo_semana;
	float corte_de_caja;
	float fuente_de_sodas;
	float prestamo;
	float pension_alimenticia;
	float dias_descanso_vacaciones;
	float total;
	
	boolean status;	
	
	float vacaciones_c;
	float prima_vacacional_c;
	float sueldo_semana_c;
	float gratificacion;
	
	public Obj_Alimentacion_De_Vacaciones() {

//		datos del empleado
		this.folio_vacaciones=0;		this.folio_empleado=0;				this.empleado="";
		this.establecimiento="";		this.puesto="";						this.fecha_ingreso="";
		this.fecha_ingreso_imss="";		this.salario_diario_integrado=0;	this.grupo_vacacional="";
		this.proximas_vacaciones=0;		this.imagen=null;					this.status=false;
		
//		alimentacion de vacaciones
		this.fecha_inicio="";		this.fecha_final="";		      this.vacaciones=0;
		this.prima_vacacional=0;	this.infonavit=0;			      this.sueldo_semana=0;
		this.corte_de_caja=0;		this.fuente_de_sodas=0;		      this.prestamo=0;
		this.pension_alimenticia=0;	this.dias_descanso_vacaciones=0;  this.total=0;
		
//		alimentacion de vacaciones c.
		this.vacaciones_c=0;	this.prima_vacacional_c=0;	this.sueldo_semana_c=0;	this.gratificacion=0;
	}

	public float getDias_descanso_vacaciones() {
		return dias_descanso_vacaciones;
	}

	public void setDias_descanso_vacaciones(float dias_descanso_vacaciones) {
		this.dias_descanso_vacaciones = dias_descanso_vacaciones;
	}

	public int getFolio_vacaciones() {
		return folio_vacaciones;
	}

	public void setFolio_vacaciones(int folio_vacaciones) {
		this.folio_vacaciones = folio_vacaciones;
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getFecha_ingreso_imss() {
		return fecha_ingreso_imss;
	}

	public void setFecha_ingreso_imss(String fecha_ingreso_imss) {
		this.fecha_ingreso_imss = fecha_ingreso_imss;
	}

	public float getSalario_diario_integrado() {
		return salario_diario_integrado;
	}

	public void setSalario_diario_integrado(float salario_diario_integrado) {
		this.salario_diario_integrado = salario_diario_integrado;
	}

	public String getGrupo_vacacional() {
		return grupo_vacacional;
	}

	public void setGrupo_vacacional(String grupo_vacacional) {
		this.grupo_vacacional = grupo_vacacional;
	}

	public int getProximas_vacaciones() {
		return proximas_vacaciones;
	}

	public void setProximas_vacaciones(int proximas_vacaciones) {
		this.proximas_vacaciones = proximas_vacaciones;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(String fecha_final) {
		this.fecha_final = fecha_final;
	}

	public float getVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(float vacaciones) {
		this.vacaciones = vacaciones;
	}

	public float getPrima_vacacional() {
		return prima_vacacional;
	}

	public void setPrima_vacacional(float prima_vacacional) {
		this.prima_vacacional = prima_vacacional;
	}

	public float getInfonavit() {
		return infonavit;
	}

	public void setInfonavit(float infonavit) {
		this.infonavit = infonavit;
	}

	public float getSueldo_semana() {
		return sueldo_semana;
	}

	public void setSueldo_semana(float sueldo_semana) {
		this.sueldo_semana = sueldo_semana;
	}

	public float getCorte_de_caja() {
		return corte_de_caja;
	}

	public void setCorte_de_caja(float corte_de_caja) {
		this.corte_de_caja = corte_de_caja;
	}

	public float getFuente_de_sodas() {
		return fuente_de_sodas;
	}

	public void setFuente_de_sodas(float fuente_de_sodas) {
		this.fuente_de_sodas = fuente_de_sodas;
	}

	public float getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(float prestamo) {
		this.prestamo = prestamo;
	}

	public float getPension_alimenticia() {
		return pension_alimenticia;
	}

	public void setPension_alimenticia(float pension_alimenticia) {
		this.pension_alimenticia = pension_alimenticia;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	public File getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {
		this.imagen = imagen;
	}
	
	public int getAnios_a_disfrutar() {
		return anios_a_disfrutar;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setAnios_a_disfrutar(int anios_a_disfrutar) {
		this.anios_a_disfrutar = anios_a_disfrutar;
	}
	
	public float getVacaciones_c() {
		return vacaciones_c;
	}

	public void setVacaciones_c(float vacaciones_c) {
		this.vacaciones_c = vacaciones_c;
	}

	public float getPrima_vacacional_c() {
		return prima_vacacional_c;
	}

	public void setPrima_vacacional_c(float prima_vacacional_c) {
		this.prima_vacacional_c = prima_vacacional_c;
	}

	public float getSueldo_semana_c() {
		return sueldo_semana_c;
	}

	public void setSueldo_semana_c(float sueldo_semana_c) {
		this.sueldo_semana_c = sueldo_semana_c;
	}

	public float getGratificacion() {
		return gratificacion;
	}

	public void setGratificacion(float gratificacion) {
		this.gratificacion = gratificacion;
	}

	//	resive parametro del filtro para un nuevo regitro y lo busca aqui solo alimenta la informacion del empleado
	public Obj_Alimentacion_De_Vacaciones buscar(int folio){ 
		try {
			return new BuscarSQL().Empleado_En_Vacaciones(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
//	busca al empleado y calcula sus vacaciones automaticas desde el calendario
	public Obj_Alimentacion_De_Vacaciones buscar_vacaciones(int folio_empleado, Date fecha_inicio_vacaciones){ 
		try {
			return new BuscarSQL().calcular_vacaciones(folio_empleado,  new SimpleDateFormat("dd/MM/yyyy").format(fecha_inicio_vacaciones));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
//	mandar folio del empleado para buscar y ediatar sus ultimas vacacioenes
	public Obj_Alimentacion_De_Vacaciones buscar_vacaciones_guardadas(int folio_empleado){ 
		try {
			return new BuscarSQL().vacaciones_guardadas(folio_empleado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
//	guardar vacaciones calculadas
	public boolean guardar_vacaciones_calculadas(){
		return new GuardarSQL().Guardar_Vacaciones_Calculadas(this); 
	}
	
	
	public boolean buscar_vacaciones_para_update(int folio_vacaciones){
		return new BuscarSQL().validacion_de_vacaciones_para_btnGuardar(folio_vacaciones);
	}
	
	public boolean actualizar(int folio_vacaciones){ return new ActualizarSQL().Actualizar_Vacaciones(this,folio_vacaciones); }


	
//	buscar empleado para alimentar ultimas vacaciones
	public Obj_Alimentacion_De_Vacaciones buscar_empleado_para_vacaciones(int folio_empleado){ 
		try {
			return new BuscarSQL().empleado_para_ultimas_vacaciones(folio_empleado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
//	guardar vacaciones pasadas manualmente
	public boolean guardar_ultimas_vacaciones(){
		return new GuardarSQL().Guardar_Vacaciones_Pasadas(this); 
	}


}
