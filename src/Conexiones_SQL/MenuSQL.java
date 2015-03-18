package Conexiones_SQL;

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class MenuSQL {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getMenusNivel (int folio){
		Vector vec = new Vector();
		String query = "exec sp_obtener_menu_nivel "+folio ;
		try {				
			Statement s = new Connexion().conexion().createStatement();
			ResultSet rs = s.executeQuery(query);
			while(rs.next())
				vec.add(rs.getString("nivel")+","+rs.getString("folio")+","+rs.getString("nombre")+ "," + rs.getString("Dependiente_Id")+ "," + rs.getString("Dependiente"));

			s.close();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Error en MenusSQL  en la funcion getMenusNivel  procedimiento almacenado sp_obtener_menu_nivel SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return vec;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getSubMenusNivel  (int folio){
		Vector vec = new Vector();
		
		String query = "sp_obtener_sub_menus_de_usuario "+folio;
		try {				
			Statement s = new Connexion().conexion().createStatement();
			ResultSet rs = s.executeQuery(query);
			while(rs.next())
				vec.add(rs.getString("folio")+","+rs.getString("nombre")+","+rs.getString("menu_id"));

			s.close();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Error en MenusSQL  en la funcion getSubMenusNivel  procedimiento almacenado sp_obtener_sub_menus_de_usuario SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		return vec;
	}
}
