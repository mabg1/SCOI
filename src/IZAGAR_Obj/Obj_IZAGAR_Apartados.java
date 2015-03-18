package IZAGAR_Obj;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Obj_IZAGAR_Apartados {
	private String Asignacion;

	
	public Obj_IZAGAR_Apartados(){
		this.Asignacion=""; 
	}


	public String getAsignacion() {
		return Asignacion;
	}


	public void setAsignacion(String asignacion) {
		Asignacion = asignacion;
	}
	
	public boolean  guardar(Obj_IZAGAR_Apartados a) {
		String query = "update IZAGAR set asignacion= ?";
		
		Connection con = new Conexiones_SQL.Connexion().conexion_IZAGAR();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, a.getAsignacion());		
			pstmt.executeUpdate();
			con.commit();
			
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

	
}
