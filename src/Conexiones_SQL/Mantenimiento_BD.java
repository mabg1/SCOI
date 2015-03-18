package Conexiones_SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Mantenimiento_BD {
	//se declara la funcion que sera carga en el objeto
	public boolean reducir_log(){
		//se carga la consulta a una varible tipo string
		String query = "exec sp_reducir_log_de_transacciones";
		//variable distancia a la clase connection
		Connection con = new Connexion().conexion();
        //se declara la variable  que prepara el query primero nula
		PreparedStatement pstmt = null;
	    //intenta o carga el error 	
		
		
			
		try {
	    	//se inicializa ala transaccion en falto hasta el commit
			con.setAutoCommit(false);
			//prepara la coneccion con el query
			pstmt = con.prepareStatement(query);
			//ejecuta el query 
			pstmt.execute();
            //si se ejecuta la transaccion  la marcara como verdadero 		
 			con.commit();
			//cacha el error
 			
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
			
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean agregar_submenus_nuevos(String nombre_submenu, int menu_id, int menuPrincipal){
	
		String query = "exec sp_insert_submenu_nuevo '"+nombre_submenu+"',"+menu_id+","+menuPrincipal;
		Connection con = new Connexion().conexion();
     	PreparedStatement pstmt = null;
			
		try {
				    	
			con.setAutoCommit(false);
		    pstmt = con.prepareStatement(query);
			pstmt.execute();
 			con.commit();
 			JOptionPane.showMessageDialog(null,"Se Guardo Correctamente el Submenu: "+nombre_submenu+"  y se guardo en los Usuarios No Olvides agregar en el Cat_Usuarios  new Obj_CheckBoxNode(SUBMENUS X[POSICION NUEVO SUBMENU], false)","Aviso",JOptionPane.INFORMATION_MESSAGE);
 		 				
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			if (con != null){
				try {
					System.out.println("La transacción agregar_submenus_nuevos ha sido abortada");
					
					con.rollback();
				JOptionPane.showMessageDialog(null,"La transacción agregar_submenus_nuevos ha sido abortada ,procedimiento almacenado sp_insert_submenu_nuevo: SQLException: "+e.getMessage(),"Error al Guardar",JOptionPane.ERROR_MESSAGE);
		
				} catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			} 
			return false;
			
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	
	
}
