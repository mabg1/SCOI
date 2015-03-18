package Obj_Auditoria;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.GuardarSQL;

public class Obj_Alimentacion_Cortes {
	                                                      
		private String folio_corte;                  	private float corte_sistema;
		private int usuario;                      	 	private float tiempo_aire;
		private int folio_empleado;                  	private float recibo_luz;
		private String establecimiento_de_corte; 		private float deposito;
		private String fecha_de_corte;               	private float efectivo;
		private String nombre;                       	private float cheques;
		private String puesto;                       	private float total_de_vauchers;                      
		private String establecimiento;              	private float diferencia_corte;                      
													 	private boolean status;                      
													 	private String comentario;     
													 	private float apartado;
													 	private float abono;

	public Obj_Alimentacion_Cortes(){
		
		this.folio_corte="";		             		this.corte_sistema=0;
		this.usuario=0;                         		this.tiempo_aire=0;
		this.folio_empleado=0;                   		this.recibo_luz=0;
		this.establecimiento_de_corte="";   			this.deposito=0;
		this.fecha_de_corte="";                  		this.efectivo=0;
		this.nombre="";                          		this.cheques=0;
		this.puesto="";                         		this.total_de_vauchers=0;
		this.establecimiento="";                		this.diferencia_corte=0;
														this.status=false;
														this.comentario="";
														this.apartado=0; 
														this.abono=0;
	} 

	public String getFolio_corte() {
		return folio_corte;
	}

	public void setFolio_corte(String folio_corte) {
		this.folio_corte = folio_corte;
	}

	public float getCorte_sistema() {
		return corte_sistema;
	}

	public void setCorte_sistema(float corte_sistema) {
		this.corte_sistema = corte_sistema;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getFolio_empleado() {
		return folio_empleado;
	}

	public void setFolio_empleado(int folio_empleado) {
		this.folio_empleado = folio_empleado;
	}

	public String getEstablecimiento_de_corte() {
		return establecimiento_de_corte;
	}

	public void setEstablecimiento_de_corte(String establecimiento_de_corte) {
		this.establecimiento_de_corte = establecimiento_de_corte;
	}

	public float getDeposito() {
		return deposito;
	}

	public void setDeposito(float deposito) {
		this.deposito = deposito;
	}

	public String getFecha_de_corte() {
		return fecha_de_corte;
	}

	public void setFecha_de_corte(String fecha_de_corte) {
		this.fecha_de_corte = fecha_de_corte;
	}

	public float getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(float efectivo) {
		this.efectivo = efectivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCheques() {
		return cheques;
	}

	public void setCheques(float cheques) {
		this.cheques = cheques;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public float getTotal_de_vauchers() {
		return total_de_vauchers;
	}

	public void setTotal_de_vauchers(float total_de_vauchers) {
		this.total_de_vauchers = total_de_vauchers;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public float getDiferencia_corte() {
		return diferencia_corte;
	}

	public void setDiferencia_corte(float diferencia_corte) {
		this.diferencia_corte = diferencia_corte;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public float getTiempo_aire() {
		return tiempo_aire;
	}

	public void setTiempo_aire(float tiempo_aire) {
		this.tiempo_aire = tiempo_aire;
	}

	public float getRecibo_luz() {
		return recibo_luz;
	}

	public void setRecibo_luz(float recibo_luz) {
		this.recibo_luz = recibo_luz;
	}
	public float getApartado() {
		return apartado;
	}

	public void setApartado(float apartado) {
		this.apartado = apartado;
	}
	
	public float getAbono() {
		return abono;
	}

	public void setAbono(float abono) {
		this.abono = abono;
	}

	//	public Obj_Alimentacion_Cortes buscar_tiket(String Clave)
//	{
//		Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes();
//		try{
//			corte = new Archivos().leerTiket(Clave);
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//		return corte;
//	}
	public boolean generar_folio_corte(){ return new GuardarSQL().Guardar_Folio_Corte(); }
	public String buscar(String establecimiento){ return new BuscarSQL().Folio_Nuevo(establecimiento); }
	
	public double retiroCajero(int cajero,String establecimiento){ return new BuscarSQL().total_retiro_cajero(cajero,establecimiento); }

	public boolean buscar_folio_corte(String folio_corte){ return new BuscarSQL().Folio_Corte(folio_corte); }
	
	public boolean guardar( Object[][] tb_asignaciones, Object[][] tb_vauchers, Object[][] tb_totales_por_fecha, Object[] lista_de_asignaciones_en_uso){
			return new GuardarSQL().Guardar_Corte(this, tb_asignaciones, tb_vauchers, tb_totales_por_fecha, lista_de_asignaciones_en_uso); 
	}
	
	public boolean eliminar(String folio_corte){ return new GuardarSQL().Borrar_Corte_completo(folio_corte); }

	public Obj_Alimentacion_Cortes buscar_folio_corte_deposito(String folio_corte) {return new BuscarSQL().Folio_Corte_Deposito(folio_corte);}
	
	public boolean buscar_folio_corte_cheques(String folio_corte){ return new BuscarSQL().Folio_Corte_Cheques(folio_corte); }
	
	public boolean actualizarCapturaFS(String folio_corte, int folio_usuario, Object[][] tabla){ return new ActualizarSQL().Actualizar_Captura_FS(folio_corte, folio_usuario, tabla); }
	
}
