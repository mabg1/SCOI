package Conexiones_SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import Obj_Administracion_del_Sistema.Obj_Usuario;
import Obj_Auditoria.Obj_Alimentacion_De_Cheques;



public class GuardarTablasModel {
	Obj_Usuario usuario = new Obj_Usuario().LeerSession();
	
	public boolean tabla_model_bancos(Object[][] tabla){
		String query_delete = "exec sp_borrado_empleados_dif_1";
		String query = "exec sp_insert_bancos ?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			
			PreparedStatement pstmtDelete = con.prepareStatement(query_delete);
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			pstmtDelete.executeUpdate();
			
			for(int i=0; i<tabla.length; i++){
				
				pstmt.setInt(1, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setFloat(2, Float.parseFloat(tabla[i][3].toString()));
				pstmt.setFloat(3, Float.parseFloat(tabla[i][4].toString()));
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en GuardarSQL  en la funcion tabla_model_bancos /n procedimiento almacenado sp_insert_bancos SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					JOptionPane.showMessageDialog(null, "Error en GuardarSQL  en la funcion tabla_model_bancos /n procedimiento almacenado sp_insert_bancos SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);

					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en GuardarSQL  en la funcion tabla_model_bancos /n procedimiento almacenado sp_insert_bancos SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Error en GuaradarSQL  en la funcion tabla_model_bancos /n procedimiento almacenado sp_insert_bancos SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_inasistencia(Object[][] tabla){
		String query = "exec sp_insert_deducc_inasistencia ?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
        
			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setString(4, tabla[i][3].toString().trim().equals("true") ? "true" : "false");
				pstmt.setString(5, tabla[i][4].toString().trim().equals("true") ? "true" : "false");
				pstmt.setInt(6, Integer.parseInt(tabla[i][5].toString()));
				pstmt.setString(7, tabla[i][6].toString().trim().equals("true") ? "true" : "false");
				pstmt.setString(8, tabla[i][8].toString().trim().equals("true") ? "true" : "false");
				pstmt.setInt(9, Integer.parseInt(tabla[i][9].toString()));
				pstmt.setFloat(10, Float.parseFloat(tabla[i][10].toString()));
				pstmt.executeUpdate();
			
//				System.out.println(tabla[i][0].toString().trim() +"Falta"+tabla[i][5].toString());
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_deduccion_y_percepcionesde_lista_de_raya(Object[][] tabla){
		String query = "exec sp_insert_deducciones_y_precepciones_de_lista_de_raya ?,?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
//				System.out.print(Integer.parseInt(tabla[i][0].toString().trim()));
//				System.out.print("  "+tabla[i][2].toString().trim());
//				System.out.print(tabla[i][3].toString().trim().equals("true")?"  1":"  0");
//				System.out.print(tabla[i][4].toString().trim().equals("true")?"  1":"  0");
//				System.out.print("  "+Integer.parseInt(tabla[i][5].toString().trim()));
//				System.out.print(tabla[i][6].toString().trim().equals("true")?"  1":"  0");
//				System.out.print("  "+Integer.parseInt(tabla[i][7].toString().trim()));
//				System.out.print("  "+Integer.parseInt(tabla[i][8].toString().trim()));
//				System.out.print("  "+Integer.parseInt(tabla[i][9].toString().trim()));
//				System.out.print("  "+Float.parseFloat(tabla[i][10].toString().trim()));
//				System.out.println("  "+tabla[i][11].toString().trim());
				
				pstmt.setInt(	1, Integer.valueOf(tabla[i][0].toString().trim()));
				pstmt.setString(2, tabla[i][2].toString().trim());
				
				pstmt.setInt(3, tabla[i][3].toString().trim().equals("true")?1:0);
				pstmt.setInt(4, tabla[i][4].toString().trim().equals("true")?1:0);
				
				pstmt.setInt(	5, Integer.valueOf(tabla[i][5].toString().trim()));
				
				pstmt.setInt(6, tabla[i][6].toString().trim().equals("true")?1:0);
				
				pstmt.setInt(	7, Integer.valueOf(tabla[i][7].toString().trim()));
				pstmt.setInt(	8, Integer.valueOf(tabla[i][8].toString().trim()));
				pstmt.setFloat(	9, Float.valueOf(tabla[i][9].toString().trim()));
				pstmt.setFloat(	10,Float.valueOf(tabla[i][10].toString().trim()));
				pstmt.setString(11,tabla[i][11].toString().trim());
				
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_persecciones(Object[][] tabla){
		String query = "exec sp_insert_persecciones_extra ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setFloat(4, Float.parseFloat(tabla[i][3].toString()));
				pstmt.setString(5, tabla[i][4].toString().trim().equals("true") ? "true" : "false");
				pstmt.setInt(6, Integer.parseInt(tabla[i][5].toString()));
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion [tabla_model_persecciones] SQLException:sp_insert_persecciones_extra "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion [tabla_model_persecciones] SQLException:sp_insert_persecciones_extra "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion [tabla_model_persecciones] SQLException:sp_insert_persecciones_extra "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}		
		return true;
	}
	
	public boolean tabla_model_lista_raya_update(){
		String query = "exec sp_lista_raya_assets";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tablaTicketFuenteSodas_auxf(Object[][] tabla, int folio, String empleado, int periodo){
		String query = "exec sp_insert_fuente_soda_auxf_de_seleccion_de_ticket ?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, folio);
				pstmt.setString(2, empleado.toUpperCase().trim());
				pstmt.setString(3, tabla[i][0].toString().toUpperCase().trim());
				pstmt.setFloat(4, Float.valueOf(tabla[i][1].toString().trim()));
				pstmt.setString(5, tabla[i][2].toString().trim());
				pstmt.setInt(6, Boolean.parseBoolean(tabla[i][3].toString().trim()) ? 1 : 0);
				pstmt.setString(7, "0");
				pstmt.setString(8, "1");
				pstmt.setInt(9, periodo);
				
				pstmt.executeUpdate();
			}
					
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tablaTicketFuenteSodas_dh(Object[][] tabla, int folio, String empleado){
		String query = "exec sp_insert_fuente_soda_dh_de_seleccion_de_ticket ?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, folio);
				pstmt.setString(2, empleado.toUpperCase().trim());
				pstmt.setString(3, tabla[i][0].toString().toUpperCase().trim());
				pstmt.setFloat(4, Float.valueOf(tabla[i][1].toString().trim()));
				pstmt.setString(5, tabla[i][2].toString().trim());
				pstmt.setInt(6, Boolean.parseBoolean(tabla[i][3].toString().trim()) ? 1 : 0);
				pstmt.setString(7, "0");
				pstmt.setString(8, "1");
				
				pstmt.executeUpdate();
				
				System.out.println(tabla[i][2].toString().trim());
			}
					
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel en la funcion tablaTicketFuenteSodas_dh /n store procedure sp_insert_fuente_soda_dh_de_seleccion_de_ticket  "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);

			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel en la funcion tablaTicketFuenteSodas_dh /n store procedure sp_insert_fuente_soda_dh_de_seleccion_de_ticket  "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_horario_temporado(Object[][] tabla, String fechaIn,String fechaFin,String horarioTemporal){
		String query = "exec sp_insert_horario_temporada ?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setInt(1, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setInt(4, Boolean.parseBoolean(tabla[i][3].toString().trim()) ? 1 : 0);
				pstmt.setString(5, fechaIn);
				pstmt.setString(6, fechaFin);
				pstmt.setString(7, horarioTemporal);
				
				pstmt.executeUpdate();
			}
					
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean borrar_empleado_con_horario_temporal(int folio, String establecimiento,String puesto,String fecha1,String fecha2){
		String query = "exec sp_delete_empleado_horario_temporada "+folio+",'"+establecimiento+"','"+puesto+"','"+fecha1+"','"+fecha2+"';";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_lista_raya(Object[][] tabla, String fecha){
		String query = "exec sp_insert_pre_listaraya ?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setString(1, Boolean.valueOf(tabla[i][0].toString().trim()) ? "true" : "false");
				pstmt.setInt(2, Integer.valueOf(tabla[i][1].toString().trim()));
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setFloat(4, Float.parseFloat(tabla[i][3].toString().trim()));
				pstmt.setString(5, tabla[i][4].toString().trim());
				pstmt.setString(6, tabla[i][5].toString().trim());
				pstmt.setString(7, fecha);
				pstmt.executeUpdate();
			}
					
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	public boolean tabla_model_alimentacion_totales_De_Cheques(Obj_Alimentacion_De_Cheques cheques, int folio_usuario, Object[] tabla){
		String query = "exec sp_insert_cheques_de_cortes ?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try{
				PreparedStatement pstmt = con.prepareStatement(query);
				con.setAutoCommit(false);
				
				for(int i = 0; i<tabla.length; i++){
					pstmt.setString(1, cheques.getFolio_corte());
					pstmt.setInt(2, cheques.getFolio_empleado());
					pstmt.setFloat(3, Float.parseFloat(tabla[i].toString().trim()));
					pstmt.setFloat(4, folio_usuario);
					pstmt.executeUpdate();
				}
				con.commit();
		} catch (Exception e) {
				System.out.println("SQLException: "+e.getMessage());
				if(con != null){
					try{
						System.out.println("La transacción ha sido abortada");
						con.rollback();
					}catch(SQLException ex){
						System.out.println(ex.getMessage());
					}
				}
				return false;
		}finally{
				try {
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
		}		
		return true;
	}
	public boolean tabla_model_alimentacion_totales(Object[][] tabla){
		String query = "exec sp_insert_totales_de_nomina ?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setString(1, tabla[i][0].toString().trim());
				pstmt.setFloat(2,(float) (Float.parseFloat(tabla[i][1].toString().trim())==0.0 ? 0.0001 : Float.parseFloat(tabla[i][1].toString().trim())));
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean tabla_model_lista_raya_generar(Object[][] tabla, String fecha){
		String query2 ="select max(numero_lista)+1 from tb_lista_raya";
		String query = "exec sp_insert_lista_raya ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		int folio_lista = 0 ;
		try {	
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query2);
			
			while(rs.next())
			folio_lista =rs.getInt(1);
			
		     } catch (SQLException e1) {
			   e1.printStackTrace();
		   }
		
		try {
			
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				
//				System.out.print( Integer.parseInt(tabla[i][1].toString().trim())+" ");
//				System.out.print( tabla[i][2].toString().trim()+" ");
//				System.out.print( tabla[i][3].toString().trim()+" ");
//				System.out.print( Float.parseFloat(tabla[i][4].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][5].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][6].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][7].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][8].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][9].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][10].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][11].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][12].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][13].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][14].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][15].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][16].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][17].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][18].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][19].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][20].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][21].toString().trim())+" ");
//				System.out.print( Float.parseFloat(tabla[i][22].toString().trim())+" ");
//				System.out.print( tabla[i][23].toString().trim()+" ");
//				System.out.print( fecha+" ");
//				System.out.print( 1+" ");
//				System.out.println(folio_lista);
				
				
				pstmt.setInt(1, Integer.parseInt(tabla[i][1].toString().trim()));
				pstmt.setString(2, tabla[i][2].toString().trim());
				pstmt.setString(3, tabla[i][3].toString().trim());
				pstmt.setFloat(4, Float.parseFloat(tabla[i][4].toString().trim()));//sueldo
				pstmt.setFloat(5, Float.parseFloat(tabla[i][5].toString().trim()));//bono_complementario
				pstmt.setFloat(6, Float.parseFloat(tabla[i][6].toString().trim()));//saldo_presta_inicial
				pstmt.setFloat(7, Float.parseFloat(tabla[i][7].toString().trim()));//desc_prestamo
				pstmt.setFloat(8, Float.parseFloat(tabla[i][8].toString().trim()));//saldo_prest_final
				pstmt.setFloat(9, Float.parseFloat(tabla[i][9].toString().trim()));//fte_sodas
				pstmt.setFloat(10, Float.parseFloat(tabla[i][10].toString().trim()));//impuntualidad
				pstmt.setFloat(11, Float.parseFloat(tabla[i][11].toString().trim()));//omision
				pstmt.setFloat(12, Float.parseFloat(tabla[i][12].toString().trim()));//faltas
				pstmt.setFloat(13, Float.parseFloat(tabla[i][13].toString().trim()));//insasistencia
				pstmt.setFloat(14, Float.parseFloat(tabla[i][14].toString().trim()));//gafete
				pstmt.setFloat(15, Float.parseFloat(tabla[i][15].toString().trim()));//corte
				pstmt.setFloat(16, Float.parseFloat(tabla[i][16].toString().trim()));//infonavit
				pstmt.setFloat(17, Float.parseFloat(tabla[i][17].toString().trim()));//pension
				pstmt.setFloat(18, Float.parseFloat(tabla[i][18].toString().trim()));//banamex
				pstmt.setFloat(19, Float.parseFloat(tabla[i][19].toString().trim()));//banorte
				pstmt.setFloat(20, Float.parseFloat(tabla[i][20].toString().trim()));//horas_extras
				pstmt.setFloat(21, Float.parseFloat(tabla[i][21].toString().trim()));//extra
				pstmt.setFloat(22, Float.parseFloat(tabla[i][22].toString().trim()));//dias_extra
				pstmt.setFloat(23, Float.parseFloat(tabla[i][23].toString().trim()));//a_pagar
				pstmt.setString(24, tabla[i][24].toString().trim());
				pstmt.setString(25, fecha);
				pstmt.setInt(26, 1);
				pstmt.setInt(27, folio_lista);
				pstmt.setInt(28, usuario.getFolio());
				pstmt.executeUpdate();
			}
					
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion tabla_model_lista_raya_generar /n  procedimiento almacenado sp_insert_lista_raya SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion tabla_model_lista_raya_generar /n procedimiento almacenado sp_insert_lista_raya SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion tabla_model_lista_raya_generar /n procedimiento almacenado sp_insert_lista_raya SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}		
		return true;
	}
	
	public boolean tabla_model_respuesta(String nombre, Object[][] tabla){
		String query_delete = "exec sp_actualizar_tabla_respuesta '"+nombre+"';";
		String query = "exec sp_insert_tabla_respuestas ?,?,?";
		Connection con = new Connexion().conexion();
		
		try {

			PreparedStatement pstmt_delete = con.prepareStatement(query_delete);
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			pstmt_delete.executeUpdate();
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setString(1, nombre.trim());
				pstmt.setInt(2, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmt.setString(3, tabla[i][1].toString().toUpperCase().trim());
				
				pstmt.executeUpdate();
			}
		
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Alimentacion_cuadrante_multiple(Object[][] tabla, Object[][] multipleProyecto){
		String query = "exec sp_insert_multiple_actividades_cuadrante ?,?,?,?,?,?,?,?,?,?,?;";
		String query_proyecto = "exec sp_insert_multiple_actividades_cuadrante_jerarquico ?,?,?,?,?,?,?,?,?,?";
		
		Connection con = new Connexion().conexion();
		try {
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(query);
			PreparedStatement pstmtJerarquico = con.prepareStatement(query_proyecto);
			
			
			System.out.println("longitud de la tabla a: "+tabla.length);
			for(int i=0; i<tabla.length; i++){
				if(!tabla[i][8].toString().trim().equalsIgnoreCase("Respuestas")){
					pstmt.setString(1, tabla[i][0].toString().trim());
					pstmt.setString(2, tabla[i][1].toString().trim());
					pstmt.setString(3, tabla[i][2].toString().trim());
					pstmt.setString(4, tabla[i][3].toString().trim());
					pstmt.setString(5, tabla[i][4].toString().trim());
					pstmt.setString(6, tabla[i][5].toString().trim());
					pstmt.setInt(7, Integer.parseInt(tabla[i][6].toString().trim()));
					pstmt.setString(8, tabla[i][7].toString().trim());
					pstmt.setString(9, tabla[i][8].toString().trim());
					pstmt.setString(10, tabla[i][9].toString().trim());
					pstmt.setString(11, tabla[i][10].toString().toUpperCase());
					pstmt.execute();
				}
			}
			System.out.println("longitud de la tabla b: "+multipleProyecto.length);
			for(int j=0; j<multipleProyecto.length; j++){
				if(!multipleProyecto[j][8].toString().trim().equalsIgnoreCase("Respuestas")){
					pstmtJerarquico.setString(1, multipleProyecto[j][0].toString().trim());
					pstmtJerarquico.setString(2, multipleProyecto[j][1].toString().trim());
					pstmtJerarquico.setString(3, multipleProyecto[j][2].toString().trim());
					pstmtJerarquico.setString(4, multipleProyecto[j][3].toString().trim());
					pstmtJerarquico.setString(5, multipleProyecto[j][4].toString().trim());
					pstmtJerarquico.setString(6, multipleProyecto[j][5].toString().trim());
					pstmtJerarquico.setInt(7, Integer.parseInt(multipleProyecto[j][6].toString().trim()));
					pstmtJerarquico.setString(8, multipleProyecto[j][7].toString().trim());
					pstmtJerarquico.setString(9, multipleProyecto[j][8].toString().trim());
					pstmtJerarquico.setString(10, multipleProyecto[j][9].toString().trim());
					pstmtJerarquico.execute();
				}				
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
			System.out.println("Error: "+e.getCause());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				System.err.println("Error de SQL: "+e.getMessage());
			}
		}		
		return true;
	}
	
	public boolean terminar_cuadrante_multiple(String nombre){
		String query = "exec sp_termina_captura_cuadrante ?;";
		Connection con = new Connexion().conexion();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			pstmt.setString(1, nombre);
			pstmt.execute();
			
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Alimentacion_cuadrante_libre(Object[][] tabla_libre, Object[][] tabla_libre_jerarquico){
		String query = "exec sp_insert_libre_actividades_cuadrante ?,?,?,?,?,?,?,?,?,?,?;";
		String query_proyecto = "exec [sp_insert_libre_actividades_cuadrante_jerarquico] ?,?,?,?,?,?,?,?,?,?";
		
		Connection con = new Connexion().conexion();
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			PreparedStatement pstmtJerarquico = con.prepareStatement(query_proyecto);
			con.setAutoCommit(false);
			
			for(int i=0; i<tabla_libre.length; i++){
				if(!tabla_libre[i][8].toString().trim().equalsIgnoreCase("")){
					pstmt.setString(1, tabla_libre[i][0].toString().trim());
					pstmt.setString(2, tabla_libre[i][1].toString().trim());
					pstmt.setString(3, tabla_libre[i][2].toString().trim());
					pstmt.setString(4, tabla_libre[i][3].toString().trim());
					pstmt.setString(5, tabla_libre[i][4].toString().trim());
					pstmt.setString(6, tabla_libre[i][5].toString().trim());
					pstmt.setInt(7, Integer.parseInt(tabla_libre[i][6].toString().trim()));
					pstmt.setString(8, tabla_libre[i][7].toString().trim());
					pstmt.setString(9, tabla_libre[i][8].toString().trim());
					pstmt.setString(10, tabla_libre[i][9].toString().trim());
					pstmt.setString(11, tabla_libre[i][10].toString().trim());
					pstmt.execute();
				}
			}
			for(int j=0; j<tabla_libre_jerarquico.length; j++){
				if(!tabla_libre_jerarquico[j][8].toString().trim().equalsIgnoreCase("")){
					pstmtJerarquico.setString(1, tabla_libre_jerarquico[j][0].toString().trim());
					pstmtJerarquico.setString(2, tabla_libre_jerarquico[j][1].toString().trim());
					pstmtJerarquico.setString(3, tabla_libre_jerarquico[j][2].toString().trim());
					pstmtJerarquico.setString(4, tabla_libre_jerarquico[j][3].toString().trim());
					pstmtJerarquico.setString(5, tabla_libre_jerarquico[j][4].toString().trim());
					pstmtJerarquico.setString(6, tabla_libre_jerarquico[j][5].toString().trim());
					pstmtJerarquico.setInt(7, Integer.parseInt(tabla_libre_jerarquico[j][6].toString().trim()));
					pstmtJerarquico.setString(8, tabla_libre_jerarquico[j][7].toString().trim());
					pstmtJerarquico.setString(9, tabla_libre_jerarquico[j][8].toString().trim());
					pstmtJerarquico.setString(10, tabla_libre_jerarquico[j][9].toString().trim());
					pstmtJerarquico.execute();
				}	
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Guardar_Cuadrante_Tabla_Real(int folio, int cuadrante, String encargado, String[][] tabla){
		String queryDelete = "exec sp_delete_tabla_cuadrante_nivel_jerarquico "+ folio;
		String querytabla = "exec sp_insert_tabla_cuadrante_real ?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtTabla = null;
		try {
			con.setAutoCommit(false);
			pstmtDelete = con.prepareStatement(queryDelete);
			pstmtDelete.execute();
			
			pstmtTabla = con.prepareStatement(querytabla);
			for(int i=0; i<tabla.length; i++){
				pstmtTabla.setInt(1, folio);
				pstmtTabla.setString(2, procesa_texto(encargado));
				pstmtTabla.setInt(3, cuadrante);
				pstmtTabla.setInt(4, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmtTabla.setString(5, tabla[i][1].toString().trim().toUpperCase());
				pstmtTabla.setString(6, tabla[i][2].toString().trim());
				pstmtTabla.setInt(7, Boolean.parseBoolean(tabla[i][3]) ? 1 : 0);
				pstmtTabla.setString(8, tabla[i][4]);
				pstmtTabla.setString(9, tabla[i][5]);
				pstmtTabla.setString(10, tabla[i][6]);
				pstmtTabla.execute();
			}
			
			con.commit();
		} catch (Exception e) {
			
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada Guardar_Cuadrante_Tabla");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public String procesa_texto(String texto) {
		StringTokenizer tokens = new StringTokenizer(texto);
	    texto = "";
	    while(tokens.hasMoreTokens()){
	    	texto += " "+tokens.nextToken();
	    }
	    texto = texto.toString();
	    texto = texto.trim().toUpperCase();
	     return texto;
	}
	public boolean tabla_IZAGAR_asignaciones_liquidadas(Object[][] tabla){
		String query = "exec IZAGAR_insert_liquidaciones_seleccionadas ?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);

			con.setAutoCommit(false);
			
			for(int i=0; i<tabla.length; i++){
				pstmt.setString(1, tabla[i][0].toString().trim());
				pstmt.setString(2, tabla[i][1].toString().trim());
				pstmt.setString(3, tabla[i][2].toString().trim());
				pstmt.setString(4, tabla[i][3].toString().trim());
				pstmt.setString(5, tabla[i][4].toString().trim());
				pstmt.setString(6, tabla[i][5].toString().trim());
				pstmt.setString(7, tabla[i][6].toString().trim());
				pstmt.setString(8, tabla[i][7].toString().trim());
				pstmt.setInt(9, Boolean.parseBoolean(tabla[i][8].toString().trim()) ? 1 : 0);
	
				
				pstmt.executeUpdate();
			}
					
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}
	public boolean borrar_IZAGAR_asignacion_liquidada(String asignacion){
		String query = "exec IZAGAR_delete_asignaciones_liquidadas '"+asignacion+"';";
		Connection con = new Connexion().conexion();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			con.setAutoCommit(false);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return true;
	}

public boolean IZAGAR_asignaciones_liquidadas_todas(Object[][] tabla){
	String query = "exec IZAGAR_insert_liquidaciones_todas ?,?,?,?,?,?,?,?";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);

		con.setAutoCommit(false);
		
		for(int i=0; i<tabla.length; i++){
			pstmt.setString(1, tabla[i][0].toString().trim());
			pstmt.setString(2, tabla[i][1].toString().trim());
			pstmt.setString(3, tabla[i][2].toString().trim());
			pstmt.setString(4, tabla[i][3].toString().trim());
			pstmt.setString(5, tabla[i][4].toString().trim());
			pstmt.setString(6, tabla[i][5].toString().trim());
			pstmt.setString(7, tabla[i][6].toString().trim());
			pstmt.setString(8, tabla[i][7].toString().trim());
			;
		pstmt.executeUpdate();
		}
				
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return true;
	}
public boolean IZAGAR_insert_valores_por_tasa_todas(Object[][] tabla){
	String query = "exec IZAGAR_insert_valores_por_tasa_todas ?,?,?,?,?";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);

		con.setAutoCommit(false);
		
		for(int i=0; i<tabla.length; i++){
			pstmt.setString(1, tabla[i][0].toString().trim());
			pstmt.setString(2, tabla[i][1].toString().trim());
			pstmt.setString(3, tabla[i][2].toString().trim());
			pstmt.setString(4, tabla[i][3].toString().trim());
			pstmt.setString(5, tabla[i][4].toString().trim());
			;
		pstmt.executeUpdate();
		}
				
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return true;
}
public boolean IZAGAR_insert_Netos_Nominas_Temp(Object[][] tabla){
	String query = "exec IZAGAR_insert_Netos_Nominas_Temp ?,?,?";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		
		for(int i=0; i<tabla.length; i++){
			pstmt.setString(1, tabla[i][0].toString().trim());
			pstmt.setString(2, tabla[i][1].toString().trim());
			pstmt.setString(3, tabla[i][2].toString().trim());
			;
		pstmt.executeUpdate();
		}
				
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Empleado  /n procedimiento almacenado IZAGAR_insert_Netos_Nominas_Temp SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Empleado /n procedimiento almacenado IZAGAR_insert_Netos_Nominas_Temp SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Empleado /n procedimiento almacenado IZAGAR_insert_Netos_Nominas_Temp SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return true;
	}

public boolean IZAGAR_insert_Netos_Nominas_Temp_individual(int folio_empleado,int nomina,float neto){
	String query = "exec IZAGAR_insert_netos_de_nomina_por_empleado_pre_conciliados_individual "+folio_empleado+","+nomina+","+neto+";";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		
		pstmt.executeUpdate();
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion IZAGAR_insert_Netos_Nominas_Temp_individual /n procedimiento almacenado IZAGAR_insert_netos_de_nomina_por_empleado_pre_conciliados_individual SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion IZAGAR_insert_Netos_Nominas_Temp_individual /n procedimiento almacenado IZAGAR_insert_netos_de_nomina_por_empleado_pre_conciliados_individual SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion IZAGAR_insert_Netos_Nominas_Temp_individual /n procedimiento almacenado IZAGAR_insert_netos_de_nomina_por_empleado_pre_conciliados_individual SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return true;
	}

public boolean IZAGAR_Remove_Netos_Nominas_Temp_individual(int folio_empleado,int nomina){
//	cambiar procedimiento borrar registro de la tabla conciliodos
	String query = "exec IZAGAR_remover_netos_de_nomina_por_empleado_pre_conciliados_individual "+folio_empleado+","+nomina+";";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		
		pstmt.executeUpdate();
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion IZAGAR_Remove_Netos_Nominas_Temp_individual /n procedimiento almacenado IZAGAR_remover_netos_de_nomina_por_empleado_pre_conciliados_individual SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion IZAGAR_Remove_Netos_Nominas_Temp_individual /n procedimiento almacenado IZAGAR_remover_netos_de_nomina_por_empleado_pre_conciliados_individual SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion IZAGAR_Remove_Netos_Nominas_Temp_individual /n procedimiento almacenado IZAGAR_remover_netos_de_nomina_por_empleado_pre_conciliados_individual SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion IZAGAR_Remove_Netos_Nominas_Temp_individual /n procedimiento almacenado IZAGAR_remover_netos_de_nomina_por_empleado_pre_conciliados_individual SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}		
	return true;
	}

public void update_IZAGAR_netos_de_nomina(){
//	cambiar procedimiento borrar registro de la tabla conciliodos
	String query = "update IZAGAR_netos_de_nomina_por_empleado_pre_conciliados set status = 0 ;";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		
		pstmt.executeUpdate();
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion update_IZAGAR_netos_de_nomina /n update IZAGAR_netos_de_nomina_por_empleado_pre_conciliados SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion update_IZAGAR_netos_de_nomina /n update IZAGAR_netos_de_nomina_por_empleado_pre_conciliados SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion update_IZAGAR_netos_de_nomina /n update IZAGAR_netos_de_nomina_por_empleado_pre_conciliados SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	}

public boolean IZAGAR_insert_totales_deposito_nomina_bancos(){
	String query = "exec sp_traspaso_de_totales_a_depositos_nomina_bancos;";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		pstmt.executeUpdate();
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion IZAGAR_insert_totales_deposito_nomina_bancos /n procedimiento almacenado sp_traspaso_de_totales_a_depositos_nomina_bancos SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion IZAGAR_insert_totales_deposito_nomina_bancos /n procedimiento almacenado sp_traspaso_de_totales_a_depositos_nomina_bancos SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return true;
	}
public boolean Guarda_tabla_de_vouchers_cargados_desde_fecha_automatico(Object[][] tabla){
	String query = "exec sp_insert_vouchers_importados_automatico_desde_una_fecha_determinada ?,?,?,?,?,?,?,? ";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		for(int i=0; i<tabla.length; i++){
			pstmt.setString(1, tabla[i][0].toString().trim());
			pstmt.setString(2, tabla[i][1].toString().trim());
			pstmt.setString(3, tabla[i][2].toString().trim());
			pstmt.setFloat(4,  Float.valueOf(tabla[i][3].toString().trim()));
			pstmt.setString(5, tabla[i][4].toString().trim());
			pstmt.setString(6, tabla[i][5].toString().trim());
			pstmt.setString(7, tabla[i][6].toString().trim());
			pstmt.setString(8, tabla[i][7].toString().trim());
			
		pstmt.executeUpdate();
		}
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_de_vouchers_cargados_desde_fecha_automatico /n procedimiento almacenado sp_insert_vouchers_importados_automatico_desde_una_fecha_determinada SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_de_vouchers_cargados_desde_fecha_automatico /n procedimiento almacenado sp_insert_vouchers_importados_automatico_desde_una_fecha_determinada SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_de_vouchers_cargados_desde_fecha_automatico /n procedimiento almacenado sp_insert_vouchers_importados_automatico_desde_una_fecha_determinada SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return true;
	}
public boolean guarda_tabla_Seleccion_de_Facturas(Object[][] tabla, String fecha){
	String query = "exec sp_insert_seleccion_de_facturas ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'"+fecha+"'";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		
		for(int i=0; i<tabla.length-1; i++){
			
			pstmt.setString(1, tabla[i][0].toString().trim());                   //folio
			pstmt.setInt(2, Integer.valueOf(tabla[i][1].toString().trim()));     //cod_estab
			pstmt.setString(3, tabla[i][2].toString().trim());                   //Establecimiento
			pstmt.setString(4, tabla[i][3].toString().trim());                   //cod_cliente
			pstmt.setString(5, tabla[i][4].toString().trim());                   //cliente
			pstmt.setString(6, tabla[i][5].toString().trim());                   //Fecha_factura
			pstmt.setFloat(7,Float.parseFloat( tabla[i][6].toString().trim()));  //importe
			pstmt.setFloat(8,Float.parseFloat( tabla[i][7].toString().trim()));  //IVA	
			pstmt.setFloat(9,Float.parseFloat( tabla[i][8].toString().trim()));  //IEPS
			pstmt.setFloat(10,Float.parseFloat( tabla[i][9].toString().trim())); //Costo
			pstmt.setFloat(11,Float.parseFloat( tabla[i][10].toString().trim()));//Contribucion
			pstmt.setFloat(12,Float.parseFloat( tabla[i][11].toString().trim()));//Total
			pstmt.setString(13, tabla[i][12].toString().trim());                 //Folio_Origen  
			pstmt.setInt(14, Integer.valueOf(tabla[i][13].toString().trim()));   //Cod_Pago
			pstmt.setString(15, tabla[i][14].toString().trim());                 //Condicion_Pago
			pstmt.setString(16, tabla[i][15].toString().trim());                 //Notas 
			pstmt.setInt(17, Integer.valueOf(tabla[i][16].toString().trim()));   //Cod_Usuario
			pstmt.setString(18, tabla[i][17].toString().trim());                 //Usuario
			pstmt.setFloat(19,Float.parseFloat( tabla[i][18].toString().trim()));//Ts0_Importe
			pstmt.setFloat(20,Float.parseFloat( tabla[i][19].toString().trim()));//Ts0_IVA
			pstmt.setFloat(21,Float.parseFloat( tabla[i][20].toString().trim()));//Ts0_IEPS
			pstmt.setFloat(22,Float.parseFloat( tabla[i][21].toString().trim()));//Ts0_Neto
			pstmt.setFloat(23,Float.parseFloat( tabla[i][22].toString().trim()));//Ts16_Importe
			pstmt.setFloat(24,Float.parseFloat( tabla[i][23].toString().trim()));//Ts16_IVA
			pstmt.setFloat(25,Float.parseFloat( tabla[i][24].toString().trim()));//Ts16_IEPS
			pstmt.setFloat(26,Float.parseFloat( tabla[i][25].toString().trim()));//Ts16_Neto
			pstmt.setString(27, tabla[i][26].toString().trim());                 //Status
			pstmt.setString(28, tabla[i][27].toString().trim());                 //Fecha_Cancelacion
			pstmt.setString(29, tabla[i][28].toString().trim());                 //booleano
			
			
			pstmt.executeUpdate();
		}
				
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion guarda_tabla_Seleccion_de_Facturas /n procedimiento almacenado sp_insert_seleccion_de_facturas SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion guarda_tabla_Seleccion_de_Facturas /n procedimiento almacenado sp_insert_seleccion_de_facturas SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return true;
}

public boolean Guarda_tabla_puestos_por_establecimiento(int folio_establecimineto, int folio_departamento, Object[][] tabla){
	
	String query = "exec sp_insert_clasificador_de_puestos "+folio_establecimineto+","+folio_departamento+",?,?;";
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		for(int i=0; i<tabla.length; i++){
			
			pstmt.setString(1, tabla[i][0].toString().trim());
			pstmt.setString(2, tabla[i][2].toString().trim());
			
		pstmt.executeUpdate();
		}
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_puestos_por_establecimiento /n procedimiento almacenado sp_insert_clasificador_de_puestos SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_puestos_por_establecimiento /n procedimiento almacenado sp_insert_clasificador_de_puestos SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_de_vouchers_cargados_desde_fecha_automatico /n procedimiento almacenado sp_insert_vouchers_importados_automatico_desde_una_fecha_determinada SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return true;
	}

public boolean Borra_departamento_y_puestos_dependientes(int folio_establecimineto, int folio_departamento,int folio_puesto){
	String query = "";
	
	if(folio_puesto==0){
		query = "DELETE FROM tb_control_de_puestos_por_establecimiento WHERE tb_control_de_puestos_por_establecimiento.folio_establecimiento = "+folio_establecimineto+" " +
				"		and tb_control_de_puestos_por_establecimiento.folio_departamento="+folio_departamento+";";
	}else{
		query = "DELETE FROM tb_control_de_puestos_por_establecimiento WHERE tb_control_de_puestos_por_establecimiento.folio_establecimiento = "+folio_establecimineto+" " +
				"		and tb_control_de_puestos_por_establecimiento.folio_departamento="+folio_departamento+" "+
				"		and tb_control_de_puestos_por_establecimiento.folio_puesto="+folio_puesto+";";

	}
	
	Connection con = new Connexion().conexion();
	
	try {
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
			
		pstmt.executeUpdate();

		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_puestos_por_establecimiento /n procedimiento almacenado sp_insert_clasificador_de_puestos SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_puestos_por_establecimiento /n procedimiento almacenado sp_insert_clasificador_de_puestos SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_de_vouchers_cargados_desde_fecha_automatico /n procedimiento almacenado sp_insert_vouchers_importados_automatico_desde_una_fecha_determinada SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return true;
	}

public int Guarda_tabla_trabajos( Object[][] tb_cv, Object[][] tb_gr,double TotalDelCorte,double TotalRetiroCliente, double TotalRecibosDeLuz, double Izacel, double Planes, double Pines, double Depositos, double CajaVerde){
	
	int folio_usuario = new Obj_Usuario().getFolio();
	
	String queryUpdate = "update tb_folios set folio = (select folio+1 from tb_folios where transaccion = 'Trabajo De Cortes') where transaccion = 'Trabajo De Cortes'";
	String querySelect = "select folio as folio_trabajo_cortes from  tb_folios as folio_trabajo_cortes where transaccion = 'Trabajo De Cortes'";
//	String query = "exec sp_insert_trabajo_de_cortes ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
	String query = "exec sp_update_trabajo_de_cortes ?,?,?,?";
	
	String queryInsertTotales = "exec sp_insert_totales_trabajo_de_cortes ?,?,?,?,?,?,?,?,?";

	Connection con = new Connexion().conexion();
	int folio_trabajo = 0;
	Statement stmt = null;
		
	try {
		
//		actualizar folio
		PreparedStatement pstmtupdate = con.prepareStatement(queryUpdate);
		con.setAutoCommit(false);
		pstmtupdate.executeUpdate();
		
//		seleccionar folio
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(querySelect);

			while(rs.next()){
				folio_trabajo = rs.getInt("folio_trabajo_cortes");
			}
		
//		actualizar corte
		PreparedStatement pstmt = con.prepareStatement(query);
		con.setAutoCommit(false);
		for(int i=0; i<tb_cv.length; i++){
			
			pstmt.setString(1 , tb_cv[i][1 ].toString().trim());
			pstmt.setString(2, "CV");
			pstmt.setInt(3, folio_trabajo);
			pstmt.setInt(4, folio_usuario);
			
			pstmt.executeUpdate();
		}
		
		for(int i=0; i<tb_gr.length; i++){
			
			pstmt.setString(1 , tb_gr[i][1 ].toString().trim());
			pstmt.setString(2, "GR");
			pstmt.setInt(3, folio_trabajo);
			pstmt.setInt(4, folio_usuario);
			
			pstmt.executeUpdate();
		}
		
//		guardar totales de trabajos de corte
		PreparedStatement pstmtInsertTotales = con.prepareStatement(queryInsertTotales);
		con.setAutoCommit(false);
			
		pstmtInsertTotales.setDouble(1 ,TotalDelCorte);
		pstmtInsertTotales.setDouble(2, TotalRetiroCliente);
		pstmtInsertTotales.setDouble(3, TotalRecibosDeLuz);
		pstmtInsertTotales.setDouble(4 ,Izacel);
		pstmtInsertTotales.setDouble(5, Planes);
		pstmtInsertTotales.setDouble(6, Pines);
		pstmtInsertTotales.setDouble(7 ,Depositos);
		pstmtInsertTotales.setDouble(8, CajaVerde);
		pstmtInsertTotales.setDouble(9, folio_trabajo);
			
		pstmtInsertTotales.executeUpdate();
		
		
		
		con.commit();
	} catch (Exception e) {
		System.out.println("SQLException: "+e.getMessage());
		JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_trabajos /n procedimiento almacenado sp_insert_trabajo_de_cortes SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		if(con != null){
			try{
				System.out.println("La transacción ha sido abortada");
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_puestos_por_establecimiento /n procedimiento almacenado sp_insert_trabajo_de_cortes SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				con.rollback();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en GuardarTablasModel  en la funcion Guarda_tabla_de_vouchers_cargados_desde_fecha_automatico /n procedimiento almacenado sp_insert_trabajo_de_cortes SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
		return 0;
	}finally{
		try {
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}		
	return folio_trabajo;
	}

}


