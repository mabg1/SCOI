package Obj_Contabilidad;

import Conexiones_SQL.GuardarTablasModel;


public class Obj_Relacion_de_facturas_en_un_periodo {
	private String folio;
	private int Cod_Estab;
	private String Establecimiento;
	private int Cod_Cliente;
	private String Cliente;
	private String Fecha_factura;
	private float Importe;
	private float IVA;
	private float IEPS;
	private float Costo;
	private float Contribucion;
	private float Total;
	private String Folio_Origen;
	private int Cod_Pago;
	private String Condicion_Pago;
	private String Notas;
	private int Cod_Usuario;
	private String Usuario;
	private float Ts0_Importe;
	private float Ts0_IVA;
	private float Ts0_IEPS;
	private float Ts0_Neto;
	private float Ts16_Importe;
	private float Ts16_IVA;
	private float Ts16_IEPS;
	private float Ts16_Neto;
	private float Status;
	private float Fecha_Cancelacion;
	
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public int getCod_Estab() {
		return Cod_Estab;
	}
	public void setCod_Estab(int cod_Estab) {
		Cod_Estab = cod_Estab;
	}
	public String getEstablecimiento() {
		return Establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		Establecimiento = establecimiento;
	}
	public int getCod_Cliente() {
		return Cod_Cliente;
	}
	public void setCod_Cliente(int cod_Cliente) {
		Cod_Cliente = cod_Cliente;
	}
	public String getCliente() {
		return Cliente;
	}
	public void setCliente(String cliente) {
		Cliente = cliente;
	}
	public String getFecha_factura() {
		return Fecha_factura;
	}
	public void setFecha_factura(String fecha_factura) {
		Fecha_factura = fecha_factura;
	}
	public float getImporte() {
		return Importe;
	}
	public void setImporte(float importe) {
		Importe = importe;
	}
	public float getIVA() {
		return IVA;
	}
	public void setIVA(float iVA) {
		IVA = iVA;
	}
	public float getIEPS() {
		return IEPS;
	}
	public void setIEPS(float iEPS) {
		IEPS = iEPS;
	}
	public float getCosto() {
		return Costo;
	}
	public void setCosto(float costo) {
		Costo = costo;
	}
	public float getContribucion() {
		return Contribucion;
	}
	public void setContribucion(float contribucion) {
		Contribucion = contribucion;
	}
	public float getTotal() {
		return Total;
	}
	public void setTotal(float total) {
		Total = total;
	}
	public String getFolio_Origen() {
		return Folio_Origen;
	}
	public void setFolio_Origen(String folio_Origen) {
		Folio_Origen = folio_Origen;
	}
	public int getCod_Pago() {
		return Cod_Pago;
	}
	public void setCod_Pago(int cod_Pago) {
		Cod_Pago = cod_Pago;
	}
	public String getCondicion_Pago() {
		return Condicion_Pago;
	}
	public void setCondicion_Pago(String condicion_Pago) {
		Condicion_Pago = condicion_Pago;
	}
	public String getNotas() {
		return Notas;
	}
	public void setNotas(String notas) {
		Notas = notas;
	}
	public int getCod_Usuario() {
		return Cod_Usuario;
	}
	public void setCod_Usuario(int cod_Usuario) {
		Cod_Usuario = cod_Usuario;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public float getTs0_Importe() {
		return Ts0_Importe;
	}
	public void setTs0_Importe(float ts0_Importe) {
		Ts0_Importe = ts0_Importe;
	}
	public float getTs0_IVA() {
		return Ts0_IVA;
	}
	public void setTs0_IVA(float ts0_IVA) {
		Ts0_IVA = ts0_IVA;
	}
	public float getTs0_IEPS() {
		return Ts0_IEPS;
	}
	public void setTs0_IEPS(float ts0_IEPS) {
		Ts0_IEPS = ts0_IEPS;
	}
	public float getTs0_Neto() {
		return Ts0_Neto;
	}
	public void setTs0_Neto(float ts0_Neto) {
		Ts0_Neto = ts0_Neto;
	}
	public float getTs16_Importe() {
		return Ts16_Importe;
	}
	public void setTs16_Importe(float ts16_Importe) {
		Ts16_Importe = ts16_Importe;
	}
	public float getTs16_IVA() {
		return Ts16_IVA;
	}
	public void setTs16_IVA(float ts16_IVA) {
		Ts16_IVA = ts16_IVA;
	}
	public float getTs16_IEPS() {
		return Ts16_IEPS;
	}
	public void setTs16_IEPS(float ts16_IEPS) {
		Ts16_IEPS = ts16_IEPS;
	}
	public float getTs16_Neto() {
		return Ts16_Neto;
	}
	public void setTs16_Neto(float ts16_Neto) {
		Ts16_Neto = ts16_Neto;
	}
	public float getStatus() {
		return Status;
	}
	public void setStatus(float status) {
		Status = status;
	}
	public float getFecha_Cancelacion() {
		return Fecha_Cancelacion;
	}
	public void setFecha_Cancelacion(float fecha_Cancelacion) {
		Fecha_Cancelacion = fecha_Cancelacion;
	}
	

	public boolean guardar(Object[][] tabla, String fecha){
		return new GuardarTablasModel().guarda_tabla_Seleccion_de_Facturas(tabla,fecha);
		
	}
	
//	public boolean guardar(){ 
//	 return new GuardarSQL().Guardar_Aspecto_de_la_Etapa(this); }
	
	
//	public Obj_Aspectos_De_La_Etapa buscar(int folio) {
//		return new BuscarSQL().ExisteAspecto(folio);	}
//	
//	public boolean actualizar(int folio){ 
//		return new 	ActualizarSQL().Aspectos_de_la_Etapa(this,folio); }
//	


//
//	if(new Obj_IZAGAR_Netos_Nominas().guardar_netos_nominas_temp_individual(folio_empleado, nomina, neto)){
//		RefreshTablas(nomina+"");
//		for(int i=0; i<=tablanomina.getRowCount()-1; i++){
//			tablanomina.setValueAt(false, i, 3);
//		}
//		JOptionPane.showMessageDialog(null,"El Registro Se Guardo Exitosamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//		return;

}
