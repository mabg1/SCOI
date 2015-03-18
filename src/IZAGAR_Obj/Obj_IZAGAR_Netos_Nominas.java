package IZAGAR_Obj;


public class Obj_IZAGAR_Netos_Nominas {

//	guardado inicial defaultt
	public boolean guardar_netos_nominas_temp(Object[][] tabla){
		return new Conexiones_SQL.GuardarTablasModel().IZAGAR_insert_Netos_Nominas_Temp(tabla );
	}

//	guardado individual uno a uno
	public boolean guardar_netos_nominas_temp_individual(int folio_empleado,int nomina,float neto){
		return new Conexiones_SQL.GuardarTablasModel().IZAGAR_insert_Netos_Nominas_Temp_individual(folio_empleado, nomina, neto );
	}
//	remover registro de la tabla conciliados
	public boolean remover_netos_nominas_temp_individual(int folio_empleado, int nomina){
		return new Conexiones_SQL.GuardarTablasModel().IZAGAR_Remove_Netos_Nominas_Temp_individual(folio_empleado, nomina);
	}
	
//	update status de tabla IZAGAR_netos_de_nomina_por_empleado_pre_conciliados
	public void update_IZAGAR_netos_de_nomina_por_empleado_pre_conciliados(){
		new Conexiones_SQL.GuardarTablasModel().update_IZAGAR_netos_de_nomina();
	}
	
//	pasar a bancos
	public boolean guardar_totales_deposito_nomina_bancos(){
		return new Conexiones_SQL.GuardarTablasModel().IZAGAR_insert_totales_deposito_nomina_bancos();
	}
	
}
