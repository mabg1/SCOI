package Conexiones_SQL;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import Obj_Administracion_del_Sistema.Obj_Configuracion_Base_de_Datos;

public class Connexion {
	Connection conn = null;	
	
	public Connection conexion(){
		Connection conn = null;	
		try{
			Obj_Configuracion_Base_de_Datos configs = new Obj_Configuracion_Base_de_Datos().buscar();
				
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://"+configs.getDireccionIPV4()+":1433;databaseName="+configs.getNombreBD(), configs.getUsuario(), configs.getContrasena());
			System.out.println("Se ha establecido la conexion con la base de datos: '"+conn.getCatalog()+"' exitosamente");		
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al realizar la conexion con la Base de Datos \n" +
												"Verifique el cable de red � las asignaciones de IP`s.\n "+ e.toString().substring(0,48)+"\n"+
												e.toString().substring(48,88),"Error",JOptionPane.WARNING_MESSAGE);
			
		}
		return conn;
	}
	
	public Connection conexion_IZAGAR(){
		Connection conn = null;	
		try{
							
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.2.201:1433;databaseName=BMSIZAGAR", "sa","ragazi*12345");
			System.out.println("Se ha establecido la conexion con la base de datos: '"+conn.getCatalog()+"' exitosamente");		
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al realizar la conexion con la Base de Datos \n" +
												"Verifique el cable de red � las asignaciones de IP`s.\n "+ e.toString().substring(0,48)+"\n"+
												e.toString().substring(48,88),"Error",JOptionPane.WARNING_MESSAGE);
			
		}
		return conn;
	}
	
	public boolean init(){
		if(conexion() != null){
			return true;
		}else{
			return false;
		}
	}
}
