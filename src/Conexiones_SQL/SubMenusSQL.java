package Conexiones_SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class SubMenusSQL {
	Connexion con = new Connexion();
	@SuppressWarnings("rawtypes")
	Vector miVector = new Vector();
	@SuppressWarnings("unchecked")
	public String[] Relacion_de_SubMenus(int menu_principal) throws SQLException{
		String query = "select nombre from tb_submenu where menu_principal = "+menu_principal +" order by nombre asc";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				miVector.add(rs.getString("nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(stmt!=null){stmt.close();}
		}
		int i=0;
		String[] pila= new String[miVector.size()];
		while(i < miVector.size()){
			pila[i]= miVector.get(i).toString();
			i++;
		}
		return pila;	
	}
	
//	@SuppressWarnings("unchecked")
//	public String[] Administracion_del_Sistema() throws SQLException{
//		String query = "select nombre from tb_submenu where menu_principal = "+menu_principal 1 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public String[] Auditoria() throws SQLException{
//		String query = "select nombre from tb_submenu where menu_principal = 2 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}


	
//	@SuppressWarnings("unchecked")
//	public String[] Cuadrantes_Alimentacion() throws SQLException{
//		String query = "select nombre from tb_submenu where menu_principal = 3 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public String[] Cuadrantes_Catalogo() throws SQLException{
//		String query = "select nombre from tb_submenus where menu_id = 5 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public String[] Cuadrantes_Reportes() throws SQLException{
//		String query = "select nombre from tb_submenus where menu_id = 6 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public String[] Lista_Raya_Alimentacion () throws SQLException{
//		String query = "select nombre from tb_submenus where menu_id = 7 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public String[] Lista_Raya_Autorizaciones() throws SQLException{
//		String query = "select nombre from tb_submenus where menu_id = 8 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public String[] Lista_Raya_Comparaciones() throws SQLException{
//		String query = "select nombre from tb_submenus where menu_id = 9 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public String[] Lista_Raya_Departamento_Cortes() throws SQLException{
//		String query = "select nombre from tb_submenus where menu_id = 10 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}
//	
//
//	@SuppressWarnings("unchecked")
//	public String[] Checador() throws SQLException{
//		String query = "select nombre from tb_submenus where menu_id = 12 order by nombre asc";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			
//			while(rs.next()){
//				miVector.add(rs.getString("nombre"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally{
//			if(stmt!=null){stmt.close();}
//		}
//		int i=0;
//		String[] pila= new String[miVector.size()];
//		while(i < miVector.size()){
//			pila[i]= miVector.get(i).toString();
//			i++;
//		}
//		return pila;	
//	}
//	
//	
//	
}
