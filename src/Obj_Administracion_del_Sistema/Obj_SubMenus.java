package Obj_Administracion_del_Sistema;

import java.sql.SQLException;

import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.Cargar_Combo;
import Conexiones_SQL.SubMenusSQL;

public class Obj_SubMenus {
	String nombre;
	public Obj_SubMenus(){
		this.nombre="";
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre.toString();
	}
	public String[] Relacion_de_SubMenus(int menu_pricipal) {
		try {
			return new SubMenusSQL().Relacion_de_SubMenus(menu_pricipal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	public String[][] UsuarioMatriz(){
		return new BuscarSQL().getUsuarioPermisos();
	}
	
	public String[] Combo_Usuarios(){
		try {
			return new Cargar_Combo().Usuario();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; }
	

}
