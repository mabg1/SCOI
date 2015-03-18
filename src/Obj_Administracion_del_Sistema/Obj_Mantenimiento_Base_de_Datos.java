package Obj_Administracion_del_Sistema;

import Conexiones_SQL.Mantenimiento_BD;

public class Obj_Mantenimiento_Base_de_Datos {
          //se declara la funcion
	  public boolean reducir_Log(){
		  //se retorna el valor devuelto por la funcion reducir log
	 	return new Mantenimiento_BD().reducir_log();
		                             }
       
	  public boolean agregar_submenus_nuevos(String Nombre_submenu,int Menu_id,int MenuPrincipal){
    	 return new Mantenimiento_BD().agregar_submenus_nuevos(Nombre_submenu,Menu_id,MenuPrincipal);
    	
                                                }


	

	
	
	
	
}
