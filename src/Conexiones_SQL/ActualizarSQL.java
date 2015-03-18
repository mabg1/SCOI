package Conexiones_SQL;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;


import Obj_Administracion_del_Sistema.Obj_Asistencia_Y_Puntualidad;
import Obj_Administracion_del_Sistema.Obj_Usuario;
import Obj_Auditoria.Obj_Actividades_Por_Proyecto;
import Obj_Auditoria.Obj_Actividades_Relacionadas;
import Obj_Auditoria.Obj_Alimentacion_De_Cheques;
import Obj_Auditoria.Obj_Alimentacion_Denominacion;
import Obj_Auditoria.Obj_Denominaciones;
import Obj_Auditoria.Obj_Divisas_Y_Tipo_De_Cambio;
import Obj_Checador.Obj_Alimentacion_De_Permisos_A_Empleados;
import Obj_Checador.Obj_Asignacion_De_Computadoras_Para_Checador_Por_Establecimiento;
import Obj_Checador.Obj_Dias_Inhabiles;
import Obj_Checador.Obj_Horarios;
import Obj_Checador.Obj_Mensaje_Personal;
import Obj_Checador.Obj_Mensajes;
import Obj_Contabilidad.Obj_Proveedores;
import Obj_Evaluaciones.Obj_Actividad;
import Obj_Evaluaciones.Obj_Actividad_Asignadas_Nivel_Jerarquico;
import Obj_Evaluaciones.Obj_Atributos;
import Obj_Evaluaciones.Obj_Cuadrante;
import Obj_Evaluaciones.Obj_Directorios;
import Obj_Evaluaciones.Obj_Empleados_En_Cuadrantes;
import Obj_Evaluaciones.Obj_Equipo_De_Trabajo;
import Obj_Evaluaciones.Obj_Jefatura;
import Obj_Evaluaciones.Obj_Nivel_Critico;
import Obj_Evaluaciones.Obj_Nivel_Jerarquico;
import Obj_Evaluaciones.Obj_Opciones_De_Respuestas;
import Obj_Evaluaciones.Obj_Ponderacion;
import Obj_Evaluaciones.Obj_Temporada;
import Obj_Lista_de_Raya.Obj_Alimentacion_De_Vacaciones;
import Obj_Lista_de_Raya.Obj_Asignacion_Mensajes;
import Obj_Lista_de_Raya.Obj_Autorizacion_Auditoria;
import Obj_Lista_de_Raya.Obj_Autorizacion_Finanzas;
import Obj_Lista_de_Raya.Obj_Bono_Complemento_Sueldo;
import Obj_Lista_de_Raya.Obj_Conceptos_De_Extras_Para_Lista_De_Raya;
import Obj_Lista_de_Raya.Obj_Departamento;
import Obj_Lista_de_Raya.Obj_Diferencia_De_Cortes;
import Obj_Lista_de_Raya.Obj_Empleados;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Lista_de_Raya.Obj_Grupo_De_Vacaciones;
import Obj_Lista_de_Raya.Obj_Totales_De_Cheque;
import Obj_Lista_de_Raya.Obj_Prestamos;
import Obj_Lista_de_Raya.Obj_Puestos;
import Obj_Lista_de_Raya.Obj_Rango_De_Prestamos;
import Obj_Lista_de_Raya.Obj_Sueldos;
import Obj_Lista_de_Raya.Obj_Tabla_De_Vacaciones;
import Obj_Lista_de_Raya.Obj_Tipo_De_Bancos;
import Obj_Lista_de_Raya.Obj_Fue_Sodas_AUXF;
import Obj_Lista_de_Raya.Obj_Fue_Sodas_DH;
import Obj_Matrices.Obj_Aspectos_De_La_Etapa;
import Obj_Matrices.Obj_Etapas;
import Obj_Matrices.Obj_Unidades_de_Inspeccion;
import Obj_Punto_De_Venta.Obj_Clientes;

public class ActualizarSQL {
	String Qbitacora ="exec sp_insert_empleado_en_bitacora ?,?,?,?,?";
	PreparedStatement pstmtb = null;
	Obj_Usuario usuario = new Obj_Usuario().LeerSession();
	
	public boolean Empleado(Obj_Empleados empleado, int folio){
		String query = "exec sp_update_alta_empleado ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";

		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			// insert bitacora
			String pc = InetAddress.getLocalHost().getHostName();
			String ip = InetAddress.getLocalHost().getHostAddress();
			pstmtb = con.prepareStatement(Qbitacora);
			pstmtb.setString(1, pc);
			pstmtb.setString(2, ip);
			pstmtb.setInt(3, usuario.getFolio());
			pstmtb.setInt(4, folio);
			pstmtb.setString(5, "Empleados sp_update_alta_empleado");
			pstmtb.executeUpdate();
			
//			private String telefono_cuadrante;
			int i=1;
			pstmt = con.prepareStatement(query);
			pstmt.setInt   (i,		folio);
			pstmt.setString(i+=1,	empleado.getNo_checador());
			pstmt.setString(i+=1, 	empleado.getNombre().toUpperCase());
			pstmt.setString(i+=1,	empleado.getAp_paterno().toUpperCase());
			pstmt.setString(i+=1,	empleado.getAp_materno().toUpperCase());
			pstmt.setString(i+=1,	empleado.getFecha_nacimiento());
			pstmt.setString(i+=1,	empleado.getCalle().toUpperCase());
			pstmt.setString(i+=1, 	empleado.getColonia().toUpperCase());
			pstmt.setString(i+=1, 	empleado.getPoblacion().toUpperCase());
			pstmt.setString(i+=1, 	empleado.getTelefono_familiar().toUpperCase());
			pstmt.setString(i+=1, 	empleado.getTelefono_propio().toUpperCase());
			pstmt.setString(i+=1, 	empleado.getRfc().toUpperCase());
			pstmt.setString(i+=1, 	empleado.getCurp().toUpperCase());
			pstmt.setInt(i+=1, 		empleado.getSexo());
			
			FileInputStream stream_foto = new FileInputStream(empleado.getFoto());
			pstmt.setBinaryStream(i+=1, stream_foto, empleado.getFoto().length());
			
//			laboral
			pstmt.setInt(i+=1, 		empleado.getHorario());
			pstmt.setInt(i+=1, 		empleado.getHorario2());
			pstmt.setInt(i+=1, 		empleado.getStatus_h1());
			pstmt.setInt(i+=1, 		empleado.getStatus_h2());
			pstmt.setInt(i+=1, 		empleado.getStatus_rotativo());
			pstmt.setString(i+=1, 	empleado.getFecha_ingreso().toUpperCase());
			pstmt.setInt(i+=1, 		empleado.getStatus());	
			pstmt.setInt(i+=1, 		empleado.isCuadrante_parcial() ? 1 : 0);
			pstmt.setInt(i+=1, 		empleado.getDepartameto());	
			pstmt.setString(i+=1, 	empleado.getImss().toUpperCase().trim());
			pstmt.setInt(i+=1, 		empleado.getStatus_imss());
			pstmt.setString(i+=1, 	empleado.getNumero_infonavit().toUpperCase());
			pstmt.setInt(i+=1, 		empleado.getEstablecimiento());
			pstmt.setInt(i+=1, 		empleado.getPuesto());
			
//			percepciones y deducciones
			pstmt.setFloat(i+=1, 	empleado.getSalario_diario());
			pstmt.setFloat(i+=1, 	empleado.getSalario_diario_integrado());
			pstmt.setString(i+=1,	empleado.getForma_pago().toUpperCase());
			pstmt.setInt(i+=1,		empleado.getSueldo());
			pstmt.setInt(i+=1, 		empleado.getBono());
			pstmt.setInt(i+=1, 		empleado.getPrestamo());
			pstmt.setFloat(i+=1, 	empleado.getPension_alimenticia());
			pstmt.setFloat(i+=1,	empleado.getInfonavit());
			pstmt.setString(i+=1, 	empleado.getTargeta_nomina().toUpperCase());
			pstmt.setInt(i+=1, 		empleado.getTipo_banco());
			pstmt.setBoolean(i+=1, (empleado.isGafete())? true: false);
			pstmt.setBoolean(i+=1, (empleado.isFuente_sodas())? true: false);
			pstmt.setString(i+=1, 	empleado.getObservasiones().toUpperCase());
			
			pstmt.setString(i+=1, 	empleado.getFecha_actualizacion().toUpperCase());
			
			
//			cambios extras 
			pstmt.setInt(i+=1,		empleado.getHorario3());
			pstmt.setInt(i+=1, 		empleado.getStatus_h3());
			pstmt.setString(i+=1, 		empleado.getFecha_ingreso_imss());
			pstmt.setString(i+=1, 		empleado.getFecha_vencimiento_licencia());
			pstmt.setInt(i+=1, usuario.getFolio());
			
			pstmt.executeUpdate();
			con.commit();
			
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion Empleado  procedimiento almacenado sp_update_alta_empleado SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Denom(Obj_Alimentacion_Denominacion denom, String asignacion, int folioDenom){
		
		String query = "update tb_alimentacion_denominaciones set asignacion=?, folio_empleado=?, " +
				"folio_denominacion=?, denominacion=?, valor=?, cantidad=?, fecha=? where asignacion='"+asignacion+"' and " +
						"folio_denominacion='"+folioDenom+"'";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;

		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
		
//			pstmt.setString(1, denom.getAsignacion().toUpperCase());
//			pstmt.setInt(2,denom.getFolio_empleado());
//			pstmt.setInt(3, denom.getFolio_denominacion());
//			pstmt.setString(4,denom.getDenominacion().toUpperCase());
//			pstmt.setFloat(5, denom.getValor());
//			pstmt.setFloat(6, denom.getCantidad());
//			pstmt.setString(7, denom.getFecha());
			
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
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Denom ]  SQLException: "+e.getMessage()+" "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Establecimiento(Obj_Establecimiento establecimiento, int folio){
		String query = "update tb_establecimiento set nombre=?, abreviatura=?,serie=?, grupo_para_cheque=?, status=?, " +
						" folio_grupo_para_cortes=?, permitir_nc=?, domicilio=?, razon_social=?, rfc=?, telefono=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, establecimiento.getEstablecimiento().toUpperCase().trim());
			pstmt.setString(2, establecimiento.getAbreviatura().toUpperCase().trim());
			pstmt.setString(3, establecimiento.getSerie().toUpperCase().trim());
			pstmt.setInt(4, establecimiento.getGrupo_cheque());
			pstmt.setInt(5, establecimiento.getStatus());
			pstmt.setInt(6, establecimiento.getGrupo_cortes());
			pstmt.setInt(7, establecimiento.getGrupo_permitir_nc());
			
			pstmt.setString(8, establecimiento.getDomicilio().toUpperCase().trim());
			pstmt.setString(9, establecimiento.getRazon_social().toUpperCase().trim());
			pstmt.setString(10, establecimiento.getRfc().toUpperCase().trim());
			pstmt.setString(11, establecimiento.getTelefono().toUpperCase().trim());
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Establecimiento ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}		
		return true;
	}
	
	
//	public boolean Usuario(Obj_Usuario usuario, int folio){
//		String query = "update tb_usuario set nombre_completo=?,contrasena=?, permiso_id=?, fecha_actu=?, status=? where folio=" + folio;
//		Connection con = new Connexion().conexion();
//		PreparedStatement pstmt = null;
//		try {
//			con.setAutoCommit(false);
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, usuario.getNombre_completo().toUpperCase());
//			pstmt.setString(2, usuario.getContrasena());
//			pstmt.setInt(3, usuario.getPermiso_id());
//			String fecha = new Date().toString();
//			pstmt.setString(4, fecha);
//			pstmt.setInt(5, usuario.getStatus());
//			pstmt.executeUpdate();
//			con.commit();
//		} catch (Exception e) {
//			System.out.println("SQLException: "+e.getMessage());
//			if(con != null){
//				try{
//					System.out.println("La transacción ha sido abortada");
//					con.rollback();
//					
//					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Usuario ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
//				}catch(SQLException ex){
//					System.out.println(ex.getMessage());
//				}
//			}
//			return false;
//		}finally{
//			try {
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}		
//		return true;
//	}
	
	public boolean Bono(Obj_Bono_Complemento_Sueldo bono, int folio){
		String query = "update tb_bono set bono=?, abreviatura=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setFloat(1, bono.getBono());
			pstmt.setString(2, bono.getAbreviatura().toUpperCase());
			pstmt.setString(3, (bono.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Bono ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Puesto(Obj_Puestos puesto, int folio){
		String query = "update tb_puesto set nombre=?, abreviatura=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, puesto.getPuesto().toUpperCase().trim());
			pstmt.setString(2, puesto.getAbreviatura().toUpperCase().trim());
			pstmt.setString(3, (puesto.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Puesto ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean DiaInHabil(Obj_Dias_Inhabiles diaIA, int folio){
		String query = "update tb_dias_inhabiles set fecha=?, descripcion=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, diaIA.getFecha());
			pstmt.setString(2, diaIA.getDescripcion().toUpperCase());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ DiaInHabil ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Atributos(Obj_Atributos atrib, int folio){
		String query = "update tb_atributo set descripcion=?, valor=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, atrib.getDescripcion().toUpperCase());
			pstmt.setFloat(2, atrib.getValor());
			pstmt.setString(3, (atrib.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Atributos ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Atributos ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Jefatura(Obj_Jefatura jefat, int folio){
		String query = "update tb_jefatura set descripcion=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, jefat.getDescripcion().toUpperCase());
			pstmt.setString(2, (jefat.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Jefatura ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Jefatura ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Eq_Trabajo(Obj_Equipo_De_Trabajo EqTrabajo, int folio){
		String query = "update tb_equipo_trabajo set descripcion=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, EqTrabajo.getDescripcion().toUpperCase());
			pstmt.setString(2, (EqTrabajo.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Eq_Trabajo ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Eq_Trabajo ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Ponderacion(Obj_Ponderacion pond, int folio){
		String query = "update tb_ponderacion set descripcion=?, valor=?, fecha_in=?, fecha_fin=?, domingo=?, lunes=?, martes=?, miercoles=?, jueves=?, viernes=?, sabado=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pond.getDescripcion().toUpperCase());
			pstmt.setFloat (2, pond.getValor());
			pstmt.setString(3, pond.getFechaIn()+"");
			pstmt.setString(4, pond.getFechaFin()+"");
			pstmt.setString(5, pond.getStatus()?"1":"0");
			pstmt.setString(6, pond.isDomingo()?"1":"0");
			pstmt.setString(7, pond.isLunes()?"1":"0");
			pstmt.setString(8, pond.isMartes()?"1":"0");
			pstmt.setString(9, pond.isMiercoles()?"1":"0");
			pstmt.setString(10,pond.isJueves()?"1":"0");
			pstmt.setString(11,pond.isViernes()?"1":"0");
			pstmt.setString(12,pond.isSabado()?"1":"0");
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Ponderacion ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Ponderacion ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Nivel(Obj_Nivel_Critico nc, int folio){
		String query = "update tb_nivel_critico set descripcion=?, valor=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nc.getDescripcion().toUpperCase());
			pstmt.setFloat(2, nc.getValor());
			pstmt.setString(3, (nc.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Nivel ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Nivel ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Tipo_Banco(Obj_Tipo_De_Bancos banck, int folio){
		String query = "update tb_tipo_banco set nombre=?, abreviatura=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, banck.getBanco().toUpperCase());
			pstmt.setString(2, banck.getAbreviatura().toUpperCase());
			pstmt.setString(3, (banck.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Tipo_Banco ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Tipo_Banco ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}

			
	public boolean Divisas(Obj_Divisas_Y_Tipo_De_Cambio divisas, int folio){
		String query = "update tb_divisas_tipo_de_cambio set nombre_divisas=?, valor=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, divisas.getNombre().toUpperCase());
			pstmt.setFloat(2, divisas.getValor());
			pstmt.setString(3, (divisas.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Divisas ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Divisas ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Denominaciones(Obj_Denominaciones denominaciones, int folio){
		String query = "update tb_denominaciones set nombre=?, moneda=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, denominaciones.getDenominacion().toUpperCase());
			pstmt.setString(2, denominaciones.getMoneda());
			pstmt.setString(3, (denominaciones.isStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Denominaciones ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Denominaciones ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Sueldo(Obj_Sueldos sueldo, int folio){
		String query = "update tb_sueldo set sueldo=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setFloat(1, sueldo.getSueldo());
			pstmt.setString(2, (sueldo.getStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Sueldo ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Sueldo ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean eliminarListaFuenteSodas(int id){
		String query = "update tb_fuente_sodas_rh set status=? where folio="+id;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ eliminarListaFuenteSodas ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ eliminarListaFuenteSodas ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean eliminarListaFuenteSodas_auxf(int id){
		
		String query = "sp_update_fuente_de_sodas_auxf "+id;
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ eliminarListaFuenteSodas_auxf ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ eliminarListaFuenteSodas_auxf ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean eliminarPrestamo(int id){
		String query = "update tb_prestamo set status=? where folio="+id;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "0");
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ eliminarPrestamo ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ eliminarPrestamo ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean fuente_sodas(Obj_Fue_Sodas_DH ftsds, int folio){
		String query = "update tb_fuente_sodas_rh set fecha=?, cantidad=? where folio="+folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, ftsds.getFecha());
			pstmt.setDouble(2, ftsds.getCantidad());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ fuente_sodas ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ fuente_sodas ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean fuente_sodas_Rh(){
        String query = "exec sp_update_comprobacion_fuente_de_sodas";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ fuente_sodas_Rh ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ fuente_sodas_Rh ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ fuente_sodas_Rh ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}		
		return true;
	}
		
	public boolean fuente_sodas_auxf(Obj_Fue_Sodas_AUXF ftsds, int folio){
		String query = "update tb_fuente_sodas_auxf set fecha=?, cantidad=? where folio="+folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, ftsds.getFecha());
			pstmt.setDouble(2, ftsds.getCantidad());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ fuente_sodas_auxf ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ fuente_sodas_auxf ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}	
	
	public boolean prestamo(Obj_Prestamos pres, int folio){
		String query = "update tb_prestamo set fecha=?, cantidad=?, descuento=?, status=? ,tipo_prestamo=? where folio="+folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pres.getFecha());
			pstmt.setDouble(2, pres.getCantidad());
			pstmt.setDouble(3, pres.getDescuento());
			pstmt.setInt(4, pres.getStatus());
			pstmt.setInt(5,pres.getTipo_prestamo());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ prestamo ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ prestamo ]  \n Confirmacion update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}	
	
	public boolean Rango_Prestamos(Obj_Rango_De_Prestamos rango_prestamo, int folio){
		String query = "update tb_rango_prestamos set minimo=?, maximo=?, descuento=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setDouble(1, rango_prestamo.getPrestamo_minimo());
			pstmt.setDouble(2, rango_prestamo.getPrestamo_maximo());
			pstmt.setDouble(3, rango_prestamo.getDescuento());
			pstmt.setString(4, (rango_prestamo.isStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Rango_Prestamos ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Rango_Prestamos ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Asistecia_Puntualidad(Obj_Asistencia_Y_Puntualidad asistencia_puntualidad, int folio){
		String query = "update tb_asistencia_puntualidad set asistencia=?, puntualidad=?, gafete=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setFloat(1, asistencia_puntualidad.getValorAsistencia());
			pstmt.setFloat(2, asistencia_puntualidad.getValorPuntualidad());
			pstmt.setFloat(3, asistencia_puntualidad.getValorGafete());
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Asistecia_Puntualidad ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Asistecia_Puntualidad ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Actualizar(Obj_Diferencia_De_Cortes pres, int folio){
		String query = "update tb_diferencia_cortes set fecha=?, cantidad=?, descuento=?, status=?, status_descuento=? where folio="+folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pres.getFecha());
			pstmt.setDouble(2, pres.getCantidad());
			pstmt.setDouble(3, pres.getDescuento());
			pstmt.setInt(4, pres.getStatus());
			pstmt.setInt(5, pres.getStatus_descuento());
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}	
	
	public boolean Departamento(Obj_Departamento departamento, int folio){
		String query = "update tb_departamento set departamento=?, abreviatura=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, departamento.getDepartamento().toUpperCase().trim());
			pstmt.setString(2, departamento.getAbreviatura().toUpperCase().trim());
			pstmt.setString(3, (departamento.isStatus())?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Departamento ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Departamento ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Configurar_Sistema(Obj_Configuracion_Del_Sistema configs){
		String query = "update tb_configuracion_sistema set bono_10_12=?, bono_dia_extra=?, guardar_horario=?, guardar_departamento=?";
				
		System.out.println(query);
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, (configs.isBono_10_12())? "true" : "false");
			pstmt.setString(2, (configs.isBono_dia_extra())? "true" : "false");
			pstmt.setString(3, (configs.isGuardar_horario())? "true" : "false");
			pstmt.setString(4, (configs.isGuardar_departamento())? "true" : "false");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Configurar_Sistema ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Configurar_Sistema ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Auditoria(Obj_Autorizacion_Auditoria auditoria){
		String query = "update tb_autorizaciones set autorizar_auditoria=? ";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, (auditoria.isAutorizar())? "true" : "false");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Auditoria ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Auditoria ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Autorizar_Finanzas(Obj_Autorizacion_Finanzas auditoria){
		String query = "update tb_autorizaciones set autorizar_finanzas=? ";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, (auditoria.isAutorizar())? "true" : "false");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Autorizar_Finanzas ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Autorizar_Finanzas ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Autorizar_Nomina(Obj_Totales_De_Cheque nomina){
		String query = "update tb_autorizaciones set autorizar_nomina=? ";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, (nomina.isAutorizar())? "true" : "false");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Autorizar_Nomina ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Autorizar_Nomina ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	
	@SuppressWarnings("rawtypes")
	public boolean PermisoUsuario(int folio_empleado, Vector Permisos){
		String update ="update tb_permisos_submenus_usuarios set acceso= ? " +
				"from tb_permisos_submenus_usuarios inner join tb_submenu on tb_submenu.folio=tb_permisos_submenus_usuarios.folio_submenu " +
				"where folio_empleado="+folio_empleado+" and  nombre=?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(update);
			for(int i=0; i<Permisos.size(); i++){
				String[] arreglo = Permisos.get(i).toString().split("/");
				pstmt.setString(1, arreglo[1]);
				pstmt.setString(2, arreglo[0]);
				pstmt.execute();
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ PermisoUsuario ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ PermisoUsuario ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
		
	public boolean GuardaNuevaContrasena(int folio_empleado ,String nuevacontrasena){
		String query = "update tb_empleado set contrasena = '"+ nuevacontrasena +"' where folio=" + folio_empleado;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			System.out.println(nuevacontrasena);
			System.out.println(folio_empleado);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException en GuardaNuevaContrasena: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción GuardaNuevaContrasena ha sido abortada :");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ GuardaNuevaContrasena ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ GuardaNuevaContrasena ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean GuardarOpciones_Clonadas(int folio_empleado ,String empleado_de_clonar){
		String query = "exec sp_clonar_permisos_submenus_usuarios "+folio_empleado+",'"+empleado_de_clonar+"'";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			System.out.println(empleado_de_clonar);
			System.out.println(folio_empleado);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException en GuardarOpciones_Clonadas: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción GuardaNuevaContrasena ha sido abortada :");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ GuardarOpciones_Clonadas ] update  SQLException:sp_clonar_permisos_submenus_usuarios "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ GuardarOpciones_Clonadas ] update  SQLException:sp_clonar_permisos_submenus_usuarios "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Actualizar(Obj_Totales_De_Cheque nomina, String Establecimiento, int Folio){
		String update = "update tb_totales_cheques_lista_raya set nomina = ?, pago_linea = ?, cheque_nomina = ?, lista_raya = ?, diferecia = ? where establecimiento = '"+Establecimiento+"' and folio_lista ="+Folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(update);
			pstmt.setString(1, nomina.getNomina());
			pstmt.setString(2, nomina.getPago_linea());
			pstmt.setString(3, nomina.getCheque_nomina());
			pstmt.setString(4, nomina.getLista_raya());
			pstmt.setString(5, nomina.getDiferencia());
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean TemporadaActualizar(Obj_Temporada temporada, int folio){
		String query = "update tb_temporada set descripcion=?, fecha_in=?, fecha_fin=?, dia=?, status=? where folio=" + folio;
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, temporada.getDescripcion());
			pstmt.setString(2, temporada.getFecha_in());
			pstmt.setString(3, temporada.getFecha_fin());
			pstmt.setString(4, temporada.getDia());
			pstmt.setInt(5, temporada.isStatus() ? 1 : 0);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ TemporadaActualizar ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ TemporadaActualizar ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean ActualizarTelefono(Obj_Directorios directorio, int folio){
		String query = "update tb_direccion_telefonicos set numero=? where folio_empleado=" + folio;
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
		
			pstmt.setString(1, directorio.getTelefono());
							
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ ActualizarTelefono ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ ActualizarTelefono ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean NivelG(Obj_Nivel_Jerarquico pond, int folio){
		String query = "update tb_nivel_gerarquico set descripcion=?, puestoprincipal=?, puestodependiente=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pond.getDescripcion().toUpperCase());
			pstmt.setString(2, pond.getPuesto_principal());
			pstmt.setString(3, pond.getPuesto_dependiente());
			pstmt.setString(4, pond.isStatus()?"1":"0");
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ NivelG ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ NivelG ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Actualizar_Actividad(Obj_Actividad actividad, int folio){
		
		String query = "exec sp_update_actividad ?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, actividad.getActividad().toUpperCase());
			pstmt.setString(2, actividad.getDescripcion().toUpperCase());
			pstmt.setString(3, actividad.getRespuesta());
			pstmt.setString(4, actividad.getAtributos());
			pstmt.setString(5, actividad.getNivel_critico());
			pstmt.setString(6, actividad.getTemporada());
			pstmt.setInt(7, actividad.isCarga()? 1 : 0);
			pstmt.setInt(8, actividad.getRepetir());
			pstmt.setInt(9, folio);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Actividad ]   SQLException: sp_update_actividad "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Actividad ]   SQLException: sp_update_actividad "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Actualizar_Actividad_Nivel_Jerarquico(Obj_Actividad_Asignadas_Nivel_Jerarquico actividad, int folio, String nombre){
		
		String query = "exec sp_update_actividad_nivel_jerarquico ?,?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			System.out.println(actividad.getDescripcion().toUpperCase());
			pstmt.setString(1, actividad.getActividad().toUpperCase());
			pstmt.setString(2, actividad.getDescripcion().toUpperCase());
			pstmt.setString(3, actividad.getRespuesta());
			pstmt.setString(4, actividad.getAtributos());
			pstmt.setString(5, actividad.getNivel_critico());
			pstmt.setString(6, actividad.getTemporada());
			pstmt.setInt(7, actividad.isCarga()? 1 : 0);
			pstmt.setInt(8, actividad.getRepetir());
			pstmt.setInt(9, folio);
			pstmt.setString(10, nombre);
			pstmt.setInt(11, actividad.isStatus()? 1:0);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Actividad_Nivel_Jerarquico ] update  SQLException: sp_update_actividad_nivel_jerarquico "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Actividad_Nivel_Jerarquico ] update  SQLException: sp_update_actividad_nivel_jerarquico "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
		
	public boolean Relacion_Actividad(Obj_Actividades_Relacionadas relacion, String[][] tabla){
		String queryDelete ="delete tb_tabla_relacion_actividad where folio_proyecto = ?";
		String query = "exec sp_update_relacion_actividad ?,?,?,?,?";
		String querytabla = "exec sp_insert_tabla_relacion_actividad ?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtTabla = null;
		
		try {
			con.setAutoCommit(false);
			
			// Elimina primero la lista de cuadrante
			pstmtDelete = con.prepareStatement(queryDelete);
			pstmtDelete.setInt(1, relacion.getFolio());
			pstmtDelete.execute();
			// Actualiza el Cuadrante
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, relacion.getFolio());
			pstmt.setString(2, relacion.getProyecto().toUpperCase().trim());
			pstmt.setString(3, relacion.getDescripcion().toUpperCase().trim());
			pstmt.setString(4, relacion.getNivel_critico().trim());
			pstmt.setInt(5, relacion.getStatus());
			pstmt.execute();
			// Inserta valores a la tabla
			pstmtTabla = con.prepareStatement(querytabla);
			
			for(int i=0; i<tabla.length; i++){
				pstmtTabla.setInt(1, relacion.getFolio());
				pstmtTabla.setInt(2, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmtTabla.setString(3, tabla[i][3].toString().trim().toUpperCase());
				pstmtTabla.setString(4, tabla[i][4].toString().trim());
				pstmtTabla.setInt(5, Boolean.parseBoolean(tabla[i][2]) ? 1 : 0);
				pstmtTabla.executeUpdate();
			}

			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada Actualizar - Actividad relacionada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Relacion_Actividad ] update  SQLException: sp_update_relacion_actividad,sp_insert_tabla_relacion_actividad "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Relacion_Actividad ] update  SQLException: sp_update_relacion_actividad,sp_insert_tabla_relacion_actividad "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Proyecto(Obj_Actividades_Por_Proyecto proyect, String[][] tabla){
		String queryDelete ="delete tb_tabla_proyecto_cuadrante where folio_proyecto = ?";
		String query = "exec sp_update_proyecto ?,?,?,?,?,?,?";
		String querytabla = "exec sp_insert_tabla_proyecto ?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtTabla = null;
		
		try {
			con.setAutoCommit(false);
			
			// Elimina primero la lista de cuadrante
			pstmtDelete = con.prepareStatement(queryDelete);
			pstmtDelete.setInt(1, proyect.getFolio());
			pstmtDelete.execute();
			
			// Actualiza el Cuadrante
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, proyect.getFolio());
			pstmt.setString(2, proyect.getProyecto().toUpperCase().trim());
			pstmt.setString(3, proyect.getDescripcion().toUpperCase().trim());
			pstmt.setString(4, proyect.getNivel_critico().trim());
			pstmt.setInt(5, proyect.getStatus());
			pstmt.setString(6, proyect.getFecha_inicial());
			pstmt.setString(7, proyect.getFecha_final());
			pstmt.execute();
			
			// Inserta valores a la tabla
			pstmtTabla = con.prepareStatement(querytabla);
			
			for(int i=0; i<tabla.length; i++){
				pstmtTabla.setInt(1, proyect.getFolio());
				pstmtTabla.setInt(2, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmtTabla.setString(3, tabla[i][3].toString().trim().toUpperCase());
				pstmtTabla.setString(4, tabla[i][4].toString().trim());
				pstmtTabla.setInt(5, Boolean.parseBoolean(tabla[i][2]) ? 1 : 0);
				pstmtTabla.executeUpdate();
			}

			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada Actualizar - Proyecto");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Proyecto ] update  SQLException: sp_update_proyecto,sp_insert_tabla_proyecto "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Proyecto ] update  SQLException: sp_update_proyecto,sp_insert_tabla_proyecto "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Cuadrante(Obj_Cuadrante cuadrante, String[][] tabla){
		String queryDelete ="delete tb_tabla_cuadrante where folio_cuadrante = ?";
		String query = "exec sp_update_cuadrante ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		String querytabla = "exec sp_insert_tabla_cuadrante ?,?,?,?,?,?,?,?";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtTabla = null;
		try {
			con.setAutoCommit(false);
			
			// Elimina primero la lista de cuadrante
			pstmtDelete = con.prepareStatement(queryDelete);
			pstmtDelete.setInt(1, cuadrante.getFolio());
			pstmtDelete.execute();
			
			// Actualiza el Cuadrante
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cuadrante.getCuadrante().toUpperCase());
			pstmt.setString(2, cuadrante.getPerfil().toUpperCase());
			pstmt.setString(3, cuadrante.getJefatura());
			pstmt.setString(4, cuadrante.getNivel_gerarquico());
			pstmt.setString(5, cuadrante.getEquipo_trabajo());
			pstmt.setString(6, cuadrante.getEstablecimiento());
			pstmt.setInt(7, cuadrante.getDomingo());
			pstmt.setInt(8, cuadrante.getLunes());
			pstmt.setInt(9, cuadrante.getMartes());
			pstmt.setInt(10, cuadrante.getMiercoles());
			pstmt.setInt(11, cuadrante.getJueves());
			pstmt.setInt(12, cuadrante.getViernes());
			pstmt.setInt(13, cuadrante.getSabado());
			pstmt.setInt(14, cuadrante.getStatus());
			pstmt.setInt(15, cuadrante.getFolio());
			pstmt.execute();
			
			// Inserta valores a la tabla
			pstmtTabla = con.prepareStatement(querytabla);
			
			for(int i=0; i<tabla.length; i++){
				pstmtTabla.setString(1, cuadrante.getCuadrante().toUpperCase());
				pstmtTabla.setInt(2, Integer.parseInt(tabla[i][0].toString().trim()));
				pstmtTabla.setString(3, tabla[i][1].toString().trim());
				pstmtTabla.setString(4, tabla[i][2].toString().trim());
				pstmtTabla.setInt(5, Boolean.parseBoolean(tabla[i][3]) ? 1 : 0);
				pstmtTabla.setString(6, tabla[i][4]);
				pstmtTabla.setString(7, tabla[i][5]);
				pstmtTabla.setString(8, tabla[i][6]);
				pstmtTabla.execute();
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada Actualizar - Cuadrante");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Cuadrante ] update  SQLException: sp_update_cuadrante,sp_insert_tabla_cuadrante "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Cuadrante ] update  SQLException: sp_update_cuadrante,sp_insert_tabla_cuadrante "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean mensajePersonal(Obj_Mensaje_Personal msjPersonal, int folio){
		 
		String queryDEP = "exec sp_update_mensaje_personal  ?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtabla = null;
		try {
			    con.setAutoCommit(false);
  			    pstmtabla = con.prepareStatement(queryDEP);
				pstmtabla.setInt (1, folio);
				pstmtabla.setString (2, msjPersonal.getFechaInicial());
				pstmtabla.setString (3, msjPersonal.getFechaFin());
				pstmtabla.setString (4, msjPersonal.getAsunto());
				pstmtabla.setString (5, msjPersonal.getMensaje());
				pstmtabla.setString (6,(msjPersonal.getStatus())?"1":"0");
				pstmtabla.executeUpdate();
				con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ mensajePersonal ] update  SQLException: sp_update_mensaje_personal "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ mensajePersonal ] update  SQLException: sp_update_mensaje_personal "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ mensajePersonal ] update  SQLException: sp_update_mensaje_personal "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
			}
		return true;
	}
	
	public boolean permiso(Obj_Alimentacion_De_Permisos_A_Empleados Permiso, int folio){

		String queryDEP = "exec sp_update_permiso_checador  ?,?,?,?,?,?,?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtabla = null;
		try {
			con.setAutoCommit(false);
			pstmtabla = con.prepareStatement(queryDEP);
			pstmtabla.setInt (1,folio);
			pstmtabla.setInt (2, Permiso.getFolio_empleado());
			pstmtabla.setInt (3, Permiso.getFolio_usuario());			
			pstmtabla.setString(4,Permiso.getFecha());
			pstmtabla.setInt(5, Permiso.getTipo_de_permiso());
			pstmtabla.setString(6, Permiso.getMotivo().toUpperCase().trim());
			pstmtabla.setBoolean(7, (Permiso.isStatus())? true: false);
			pstmtabla.setInt(8, Permiso.getDescanso());
			pstmtabla.setString(9, Permiso.getTiempo_comida());
			pstmtabla.setInt(10, Permiso.getFolio_empleado_optener_turno());
			pstmtabla.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ permiso ] update  SQLException: sp_update_permiso_checador "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ permiso ] update  SQLException: sp_update_permiso_checador "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		return true;
	}
	
	public boolean nivelGerarquico2(Obj_Nivel_Jerarquico niv, String[][]tabla){
		
		String queryDelete="delete from tb_tabla_nivel_jerarquico where tb_tabla_nivel_jerarquico.folio_tb_nivel_jerarquico = "+niv.getFolio();
		String query = "exec sp_insert_tabla_nivel_jerarquico ?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtabla = null;
		try {
			con.setAutoCommit(false);
			pstmtDelete= con.prepareStatement(queryDelete);
			pstmtDelete.executeUpdate();
			pstmtabla = con.prepareStatement(query);
			for (int i = 0; i < tabla.length; i++) {
				pstmtabla.setInt (1, niv.getFolio());
				System.out.print(tabla[i][0] +"   ");	System.out.println(tabla[i][1]);
				pstmtabla.setString (2, tabla[i][0]);
				pstmtabla.setString (3, tabla[i][1]);
				pstmtabla.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ nivelGerarquico2 ] update  SQLException: sp_insert_tabla_nivel_jerarquico "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ nivelGerarquico2 ] update  SQLException: sp_insert_tabla_nivel_jerarquico "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		return true;
	}

	public boolean mensajePersonal2(Obj_Mensaje_Personal msjPersonal, String[] tabla){
		String queryClear = "delete from tb_tabla_empleado_mensaje_personal where folio_mensaje = "+msjPersonal.getFolioMensaje();
		String query = "exec sp_insert_tabla_empleado_mensaje ?,?";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		PreparedStatement pstmtabla = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(queryClear);
			pstmtabla = con.prepareStatement(query);
			pstmt.executeUpdate();
			for (int i = 0; i < tabla.length; i++) {
				pstmtabla.setInt (1, msjPersonal.getFolioMensaje());
				pstmtabla.setString (2, tabla[i]);
				pstmtabla.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ mensajePersonal2 ] update  SQLException: sp_insert_tabla_empleado_mensaje "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ mensajePersonal2 ] update  SQLException: sp_insert_tabla_empleado_mensaje "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		return true;
	}
	
	public boolean EmpleadoCuadrante(Obj_Empleados_En_Cuadrantes empleado_cuadrante, String[] tabla){
		String queryClear = "exec sp_borrar_empleados_en_cuadrantes "+empleado_cuadrante.getFolio();
		String queryUpdate = "exec sp_update_tb_empleado_cuadrante ?,?,?";
		String querytabla = "exec sp_insert_tabla_empleado_cuadrante ?,?";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtDelete = null;
		PreparedStatement pstmtUpdate = null;
		PreparedStatement pstmtTabla = null;
		try {
			con.setAutoCommit(false);
			pstmtDelete = con.prepareStatement(queryClear);
			pstmtDelete.executeUpdate();
			pstmtUpdate = con.prepareStatement(queryUpdate);
	    	pstmtUpdate.setString(1, empleado_cuadrante.getCuadrante());
			pstmtUpdate.setInt(2, empleado_cuadrante.isStatus() ? 1 : 0);
			pstmtUpdate.setInt(3, empleado_cuadrante.getFolio());
			pstmtUpdate.executeUpdate();
			pstmtTabla = con.prepareStatement(querytabla);
			for(int i=0; i<tabla.length; i++){
				System.out.println(empleado_cuadrante.getCuadrante().toUpperCase().trim());
				System.out.println(Integer.parseInt(tabla[i]));
				System.out.println(empleado_cuadrante.getFolio());
				pstmtTabla.setString(1, empleado_cuadrante.getCuadrante().toUpperCase().trim());
				pstmtTabla.setInt(2, Integer.parseInt(tabla[i]));
				pstmtTabla.executeUpdate();
			}
						
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ EmpleadoCuadrante ] update  SQLException: sp_borrar_empleados_en_cuadrantes,sp_update_tb_empleado_cuadrante,sp_insert_tabla_empleado_cuadrante "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ EmpleadoCuadrante ] update  SQLException: sp_borrar_empleados_en_cuadrantes,sp_update_tb_empleado_cuadrante,sp_insert_tabla_empleado_cuadrante "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
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
	
	public boolean opcion_respuesta(Obj_Opciones_De_Respuestas respuesta, int folio){
		String query = "exec sp_update_opcion_respuesta ?,?,?,?;";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, respuesta.getNombre().toUpperCase().trim());
			pstmt.setInt(2, respuesta.getTipo_opcion().equals("Opción Libre") ? 0 : 1);
			pstmt.setInt(3, respuesta.isStatus() ? 1 : 0);
			pstmt.setInt(4, folio);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ opcion_respuesta ] update  SQLException: sp_update_opcion_respuesta "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ opcion_respuesta ] update  SQLException: sp_update_opcion_respuesta "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
			public boolean ActualizarMensajes(Obj_Mensajes msj, int folio){
				String query = "update tb_mensajes set mensaje=? where folio=" + folio;
				
				Connection con = new Connexion().conexion();
				PreparedStatement pstmt = null;
				try {
					con.setAutoCommit(false);
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, msj.getMensaje());
					pstmt.executeUpdate();
					con.commit();
				} catch (Exception e) {
					System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ ActualizarMensajes ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ ActualizarMensajes ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}
					}
					return false;
				}finally{
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}		
				return true;
			}
	
			public boolean ActualizarAsignacion(Obj_Asignacion_Mensajes msj, int folio){
				String query = "update tb_asignacion_mensaje set mensaje=?,mensajearea=?,puesto=?,equipo=?,jefatura=?,empleado=? where folio=" + folio;
				
				Connection con = new Connexion().conexion();
				PreparedStatement pstmt = null;
				try {
					con.setAutoCommit(false);
					pstmt = con.prepareStatement(query);
					pstmt.setInt(1,msj.getNo_mensajes());
					pstmt.setString(2, msj.getMensaje());
					pstmt.setString(3,msj.getPuesto());
					pstmt.setString(4,msj.getEquipo());
					pstmt.setString(5,msj.getJefatura());
					pstmt.setString(6, msj.getEmpleado());
					pstmt.executeUpdate();
					con.commit();
				} catch (Exception e) {
					System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ ActualizarAsignacion ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ ActualizarAsignacion ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}
					}
					return false;
				}finally{
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}		
				return true;
			}

			public boolean Horario(Obj_Horarios horario_emp, int folio){

				String query = "exec sp_update_horarios ?,?,?,?,?,?,?,?,?,?," +
													"	?,?,?,?,?,?,?,?,?,?," +
													"	?,?,?,?,?,?,?,?,?,?," +
													"	?,?,?,?,?,?,?,?,?,?," +
													"	?,?,?";

				Connection con = new Connexion().conexion();
				PreparedStatement pstmt = null;
				try {
					con.setAutoCommit(false);
					pstmt = con.prepareStatement(query);
								
					int i=1;	
					pstmt.setInt(i, folio);
					pstmt.setString(i+=1, horario_emp.getNombre());
					pstmt.setInt(i+=1, horario_emp.getDescanso());
					pstmt.setInt(i+=1, horario_emp.getDiaDobla());
					pstmt.setInt(i+=1, horario_emp.getDiaDobla2());
					pstmt.setInt(i+=1, horario_emp.getDiaDobla3());

					//////////////////////////////////////////////////////////////
//					pstmt.setString(i+=1, "DOMINGO");
					pstmt.setString(i+=1, horario_emp.getDomingo1());
					pstmt.setString(i+=1, horario_emp.getDomingo2());
					pstmt.setString(i+=1, horario_emp.getDomingo3());
					pstmt.setString(i+=1, horario_emp.getDomingo4());
					pstmt.setString(i+=1, horario_emp.getDomingo5());
					
//					pstmt.setString(i+=1, "LUNES");
					pstmt.setString(i+=1, horario_emp.getLunes1());
					pstmt.setString(i+=1, horario_emp.getLunes2());
					pstmt.setString(i+=1, horario_emp.getLunes3());
					pstmt.setString(i+=1, horario_emp.getLunes4());
					pstmt.setString(i+=1, horario_emp.getLunes5());
					
//					pstmt.setString(i+=1, "MARTES");
					pstmt.setString(i+=1, horario_emp.getMartes1());
					pstmt.setString(i+=1, horario_emp.getMartes2());
					pstmt.setString(i+=1, horario_emp.getMartes3());
					pstmt.setString(i+=1, horario_emp.getMartes4());
					pstmt.setString(i+=1, horario_emp.getMartes5());
					
//					pstmt.setString(i+=1, "MIERCOLES");
					pstmt.setString(i+=1, horario_emp.getMiercoles1());
					pstmt.setString(i+=1, horario_emp.getMiercoles2());
					pstmt.setString(i+=1, horario_emp.getMiercoles3());
					pstmt.setString(i+=1, horario_emp.getMiercoles4());
					pstmt.setString(i+=1, horario_emp.getMiercoles5());
					
//					pstmt.setString(i+=1, "JUEVES");
					pstmt.setString(i+=1, horario_emp.getJueves1());
					pstmt.setString(i+=1, horario_emp.getJueves2());
					pstmt.setString(i+=1, horario_emp.getJueves3());
					pstmt.setString(i+=1, horario_emp.getJueves4());
					pstmt.setString(i+=1, horario_emp.getJueves5());
					
//					pstmt.setString(i+=1, "VIERNES");
					pstmt.setString(i+=1, horario_emp.getViernes1());
					pstmt.setString(i+=1, horario_emp.getViernes2());
					pstmt.setString(i+=1, horario_emp.getViernes3());
					pstmt.setString(i+=1, horario_emp.getViernes4());
					pstmt.setString(i+=1, horario_emp.getViernes5());
					
//					pstmt.setString(i+=1, "SABADO");
					pstmt.setString(i+=1, horario_emp.getSabado1());
					pstmt.setString(i+=1, horario_emp.getSabado2());
					pstmt.setString(i+=1, horario_emp.getSabado3());
					pstmt.setString(i+=1, horario_emp.getSabado4());
					pstmt.setString(i+=1, horario_emp.getSabado5());
					pstmt.setInt(i+=1, horario_emp.getRecesoDiarioExtra());
					pstmt.setInt(i+=1, horario_emp.getHorarioDeposito());
					pstmt.executeUpdate();
					con.commit();
				} catch (Exception e) {
					System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Horario ] update  SQLException: sp_update_horarios "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Horario ] update  SQLException: sp_update_horarios "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}
					}
					return false;
				}finally{
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}		
				return true;
			}
			
			public boolean Actualizar_Alimentacion_Efectivo(Obj_Alimentacion_Denominacion alim_denom, int folio_usuario, Object[][] tabla){
				
				String query_delete = "delete from tb_alimentacion_efectivo_cortes where folio_corte ='"+alim_denom.getFolio_corte()+"'";
				String query ="exec sp_insert_alimentacion_de_efectivo_cortes ?,?,?,?,?,?,?,?";
				Connection con = new Connexion().conexion();
				
				try {
					PreparedStatement pstmtDelete = con.prepareStatement(query_delete);
					PreparedStatement pstmt = con.prepareStatement(query);
					con.setAutoCommit(false);
//					pstmtDelete.setString(1, alim_denom.getAsignacion());
					pstmtDelete.executeUpdate();
					
					for(int i=0; i<tabla.length; i++){
						pstmt.setString(1, alim_denom.getFolio_corte().toUpperCase());
						pstmt.setString(2, alim_denom.getEmpleado().toUpperCase().trim());
						pstmt.setString(3, alim_denom.getEstablecimiento().toUpperCase());
						pstmt.setInt(4, Integer.parseInt(tabla[i][0].toString().trim()));
						pstmt.setFloat(5, Float.parseFloat(tabla[i][2].toString().trim()));
						pstmt.setFloat(6,Float.parseFloat(tabla[i][3].toString().trim()));
						pstmt.setFloat(7,Float.parseFloat(tabla[i][4].toString().trim()));
						pstmt.setInt(8, folio_usuario);
						pstmt.executeUpdate();
					}
							
					con.commit();
				} catch (Exception e) {
					System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Alimentacion_denominacion ] update  SQLException: sp_insert_denominaciones "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Alimentacion_denominacio ] update  SQLException: sp_insert_denominaciones "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
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
			
			public boolean Actualizar_Alimentacion_deposito(Obj_Alimentacion_Denominacion alim_denom, int folio_usuario, Object[][] tabla){
				
				String query_delete = "delete from tb_alimentacion_deposito where folio_corte ='"+alim_denom.getEstablecimiento()+"'";
				String query ="exec sp_insert_deposito ?,?,?,?,?";
				Connection con = new Connexion().conexion();
				
				try {
					PreparedStatement pstmtDelete = con.prepareStatement(query_delete);
					PreparedStatement pstmt = con.prepareStatement(query);
					con.setAutoCommit(false);
					pstmtDelete.executeUpdate();
					for(int i=0; i<tabla.length; i++){
						pstmt.setString(1, alim_denom.getEstablecimiento().toUpperCase().trim());
						pstmt.setString(2, alim_denom.getEmpleado().toUpperCase().trim());
						pstmt.setString(3, tabla[i][0].toString().trim());
						pstmt.setFloat (4, Float.parseFloat(tabla[i][1].toString().trim()));
						pstmt.setInt (5, folio_usuario);
						pstmt.executeUpdate();
					}
					con.commit();
				} catch (Exception e) {
					System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Alimentacion_deposito ] update  SQLException: sp_insert_deposito "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Alimentacion_deposito ] update  SQLException: sp_insert_deposito "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
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
				
				
//				cambiar procedimientos borrado e insercion de datos 
				String query_delete = "delete from tb_tabla_de_cheques_para_cortes where folio_corte = '"+cheques.getFolio_corte().toUpperCase().trim()+"'";
				String query = "exec sp_insert_cheques_de_cortes ?,?,?,?";
				
				Connection con = new Connexion().conexion();
				
				try{
					
					
					con.setAutoCommit(false);
					System.out.println(query_delete+" borrado xxxxx");
					
					PreparedStatement pstmtDelete = con.prepareStatement(query_delete);
					pstmtDelete.executeUpdate();
					
					PreparedStatement pstmt = con.prepareStatement(query);
						
						for(int i = 0; i<tabla.length; i++){
							pstmt.setString(1, cheques.getFolio_corte());
							pstmt.setInt(2, cheques.getFolio_empleado());
							pstmt.setFloat(3, Float.parseFloat(tabla[i].toString().trim()));
							pstmt.setInt(4, folio_usuario);
							pstmt.executeUpdate();
						}
						con.commit();
				} catch (Exception e) {
							System.out.println("SQLException: "+e.getMessage());
							if(con != null){
								try{
									System.out.println("La transacción ha sido abortada");
									con.rollback();
									JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Alimentacion_Cheques ] update  SQLException: sp_insert_cheques_de_cortes "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
								}catch(SQLException ex){
									System.out.println(ex.getMessage());
									JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Alimentacion_Cheques ] update  SQLException: sp_insert_cheques_de_cortes "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
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
	
		public boolean status_solicitud_empleados(int folioSolicitud,int status){
				String query = "exec sp_update_solicitud_empleados "+folioSolicitud+","+status+";";
				Connection con = new Connexion().conexion();
				PreparedStatement pstmt = null;
				try {
					con.setAutoCommit(false);
//					int i=1;
					pstmt = con.prepareStatement(query);
//					pstmt.setInt   (i,		folio);
					pstmt.executeUpdate();
					con.commit();
				} catch (Exception e) {
					System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ status_solicitud_empleados ] update  SQLException: sp_update_solicitud_empleados "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ status_solicitud_empleados ] update  SQLException: sp_update_solicitud_empleados "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}
					}
					return false;
				}finally{
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}		
				return true;
			}
		
	public boolean Grupo_Vacaciones(Obj_Grupo_De_Vacaciones grupo, int folio){
		String query = "update tb_grupo_de_vacaciones set descripcion=?, status=? where folio=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, grupo.getDescripcion().toUpperCase());
			pstmt.setInt(2, (grupo.isStatus())?1:0);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Grupo_Vacaciones ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Grupo_Vacaciones ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Actualizar_Grupo_De_Vacaciones(Obj_Tabla_De_Vacaciones grupo_vacacionesObj,String grupo_vacaciones,int anios,int dias,int prima){
		String query = "exec sp_update_tabla_grupos_de_vacaciones ?,?,?,?,?,?,?,?;";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			int i=1;
			pstmt.setString(i,	grupo_vacacionesObj.getGrupo().toUpperCase().trim());
			pstmt.setInt(i+=1, 	grupo_vacacionesObj.getAnios_trabajados());
			pstmt.setInt(i+=1,	grupo_vacacionesObj.getDias_correspondientes());
			pstmt.setInt(i+=1,	grupo_vacacionesObj.getPrima_vacacional());
			pstmt.setString(i+=1,	grupo_vacaciones.toUpperCase().trim());
			pstmt.setInt(i+=1, 		anios);
			pstmt.setInt(i+=1,		dias);
			pstmt.setInt(i+=1,		prima);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Grupo_De_Vacaciones ] update  SQLException: sp_update_tabla_grupos_de_vacaciones "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Grupo_De_Vacaciones ] update  SQLException: sp_update_tabla_grupos_de_vacaciones "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Actualizar_Vacaciones(Obj_Alimentacion_De_Vacaciones alimentacion,int folio_vacaciones){
		String query = "exec sp_update_vacaciones "+folio_vacaciones+",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?;";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			int i=1;
			
			pstmt.setString			(i, alimentacion.getFecha_inicio());
			pstmt.setString			(i+=1, alimentacion.getFecha_final());
			
			pstmt.setFloat			(i+=1, alimentacion.getVacaciones());
			pstmt.setFloat			(i+=1, alimentacion.getPrima_vacacional());
			pstmt.setFloat			(i+=1, alimentacion.getInfonavit());
			pstmt.setFloat			(i+=1, alimentacion.getSueldo_semana());
			pstmt.setFloat			(i+=1, alimentacion.getCorte_de_caja());
			pstmt.setFloat			(i+=1, alimentacion.getFuente_de_sodas());
			pstmt.setFloat			(i+=1, alimentacion.getPrestamo());
			pstmt.setFloat			(i+=1, alimentacion.getPension_alimenticia());
			pstmt.setFloat			(i+=1, alimentacion.getTotal());
			pstmt.setInt			(i+=1, alimentacion.isStatus()?1:0);
			pstmt.setFloat          (i+=1, alimentacion.getVacaciones_c());
			pstmt.setFloat          (i+=1, alimentacion.getPrima_vacacional_c());
			pstmt.setFloat          (i+=1, alimentacion.getSueldo_semana_c());
			pstmt.setFloat          (i+=1, alimentacion.getGratificacion()); 
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Vacaciones ] update  SQLException: sp_update_vacaciones "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Vacaciones ] update  SQLException: sp_update_vacaciones "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Vacaciones ] update  SQLException: sp_update_vacaciones "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	public boolean Factura_Provedores_xml(Obj_Proveedores Proveedores, String folio_factura){
		String query = "update tb_control_de_facturas_y_xml set folio_factura=?, fecha_factura=?, status=? , fecha_modificacion=getdate() where folio_factura='" + folio_factura+"' and cod_prv='"+Proveedores.getCod_prv()+"'";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			System.out.println(Proveedores.getFolio_factura().toUpperCase());
			System.out.println(Proveedores.getCod_prv());
			System.out.println(Proveedores.getFecha().toUpperCase());
			System.out.println(query);
			
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Proveedores.getFolio_factura().toUpperCase());
			pstmt.setString(2, Proveedores.getFecha().toUpperCase());
			pstmt.setInt(3, Proveedores.getStatus()?1:0);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Puesto ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Cliente(Obj_Clientes cliente){
		String query = "exec sp_update_alta_cliente ?,?,?,?,?,?";

		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
//			// insert bitacora
//			String pc = InetAddress.getLocalHost().getHostName();
//			String ip = InetAddress.getLocalHost().getHostAddress();
//			pstmtb = con.prepareStatement(Qbitacora);
//			pstmtb.setString(1, pc);
//			pstmtb.setString(2, ip);
//			pstmtb.setInt(3, usuario.getFolio());
//			pstmtb.setInt(4, folio);
//			pstmtb.setString(5, "Empleados sp_update_alta_empleado");
//			pstmtb.executeUpdate();
			
//			private String telefono_cuadrante;
			int i=1;
			pstmt = con.prepareStatement(query);
			pstmt.setInt   (i,		cliente.getFolio_cliente());
			pstmt.setString(i+=1,	cliente.getNombre().toUpperCase().trim());
			
			pstmt.setString(i+=1,	cliente.getAp_paterno().toUpperCase().trim());
			pstmt.setString(i+=1,	cliente.getAp_materno().toUpperCase().trim());
			pstmt.setString(i+=1,	cliente.getDireccion().toUpperCase().trim());
			pstmt.setString(i+=1, 	cliente.getTelefono().trim());
			
			pstmt.executeUpdate();
			con.commit();
			
		}catch(SQLException ex){
			
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Cliente ] update  SQLException: sp_update_alta_cliente "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex1){
					System.out.println(ex1.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Cliente ] update  SQLException: sp_update_alta_cliente "+ex1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Cliente ] update  SQLException: sp_update_alta_cliente "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
			}
		return true;
	}	
			
	public boolean marcar_c_recibido_factura(String cod_prov_recibido, String folio_factura_recibido,String tipo_archivo,File xml_pdf){
		
		String query = "exec sp_update_captura_de_archivos_xml_pdf ?,?,?,?";
		Connection con = new Connexion().conexion();
		PreparedStatement pstmtabla = null;
		try {
			
			    con.setAutoCommit(false);
  			    pstmtabla = con.prepareStatement(query);
  			    
  				pstmtabla.setString(1, tipo_archivo);
  				
  				FileInputStream stream_pdf = new FileInputStream(xml_pdf);
  				pstmtabla.setBinaryStream(2, stream_pdf);
  				
  				pstmtabla.setString(3, cod_prov_recibido);
  				pstmtabla.setString(4, folio_factura_recibido);
  				
				pstmtabla.executeUpdate();
				con.commit();
				
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ marcar_c_recibido_factura ] update  SQLException: tb_control_de_facturas_y_xml "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ marcar_c_recibido_factura ] update  SQLException: tb_control_de_facturas_y_xml "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ marcar_c_recibido_factura ] update  SQLException: tb_control_de_facturas_y_xml "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
			}
		return true;
	}
	
	public boolean Etapas(Obj_Etapas etapas, int folio){
		String query = "update tb_etapas set etapa=?, abreviatura=?, status=?,ultima_modificacion=getdate() where folio_etapa=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, etapas.getEtapa().toUpperCase().trim());
			pstmt.setString(2, etapas.getAbreviatura().toUpperCase().trim());
			pstmt.setInt(3, etapas.getStatus());
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ etapas ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ etapas ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Aspectos_de_la_Etapa(Obj_Aspectos_De_La_Etapa aspecto, int folio){
		String query = "update tb_aspectos_de_la_etapa set aspecto_de_la_etapa=?, abreviatura=?, status=?,ultima_modificacion=getdate() where folio_aspecto=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, aspecto.getAspecto().toUpperCase().trim());
			pstmt.setString(2, aspecto.getAbreviatura().toUpperCase().trim());
			pstmt.setInt(3, aspecto.getStatus());
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Aspectos_de_la_Etapa ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Aspectos_de_la_Etapa ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Unidades_de_Inspeccion(Obj_Unidades_de_Inspeccion unidades_de_inspeccion, int folio){
		String query = "update tb_unidades_de_inspeccion set unidad_de_inspeccion=?, abreviatura=?, status=?,ultima_modificacion=getdate() where folio_unidad_de_inspeccion=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, unidades_de_inspeccion.getunidades_de_inspeccion().toUpperCase().trim());
			pstmt.setString(2, unidades_de_inspeccion.getAbreviatura().toUpperCase().trim());
			pstmt.setInt(3, unidades_de_inspeccion.getStatus());
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Unidades_de_Inspeccion ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Unidades_de_Inspeccion ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Actualizar_Captura_FS(String folio_corte, int folio_usuario, Object[][] tabla){
		String queryReset ="exec sp_reset_captura_fs ?";
		String query ="exec sp_update_captura_fs ?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			
			con.setAutoCommit(false);
			
			PreparedStatement pstmtReset = con.prepareStatement(queryReset);
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmtReset.setString(1, folio_corte);
			pstmtReset.executeUpdate();
			
			
			for(int i=0; i<tabla.length; i++){
				
				System.out.print("conneccion "+folio_corte+"   ");
				System.out.print(tabla[i][0].toString()+"   ");
				System.out.println(tabla[i][1].toString());
				
				pstmt.setString(1, folio_corte);
				pstmt.setString (2, tabla[i][0].toString());
				pstmt.setString (3, tabla[i][1].toString());
				pstmt.setInt (4, folio_usuario);
				pstmt.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
				System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Captura_FS ] update  SQLException: sp_update_captura_fs "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Captura_FS ] update  SQLException: sp_update_captura_fs "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
					return false;
					}	
		}finally{
				try {
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
		}		
		return true;
	
	}	

	public boolean Actualizar_Cancelar_Ticket_o_Abono(String folio_ticket_abono, String usuario_cancelo, String movimiento, String cajero){
		String query ="exec sp_update_cancelar_ticket_o_abono ?,?,?,?";
		Connection con = new Connexion().conexion();
		
		try {
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(query);
			
				System.out.println(folio_ticket_abono);
				System.out.println(usuario_cancelo);
				System.out.println(movimiento);
				System.out.println(cajero);
				
				pstmt.setString(1, folio_ticket_abono);
				pstmt.setString (2, usuario_cancelo);
				pstmt.setString (3, movimiento);
				pstmt.setString(4, cajero);
				pstmt.executeUpdate();

				con.commit();
		} catch (Exception e) {
				System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Captura_FS ] update  SQLException: sp_update_captura_fs "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_Captura_FS ] update  SQLException: sp_update_captura_fs "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
					return false;
					}	
		}finally{
				try {
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
		}		
		return true;
	}	
	
	public boolean Actualizar_IZAGAR_Relacion_de_Asignaciones_Liquidadaso(Object[][] matriz){
		String query =("update IZAGAR_Relacion_de_Asignaciones_Liquidadas set  usuario= "+usuario.getFolio()+", set porcentaje = ?, set fecha =getdate()"+
                		" where asignacion =?");

		Connection con = new Connexion().conexion_IZAGAR();
		PreparedStatement pstmt= null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			
			for(int i = 0; i<matriz.length; i++){
					pstmt.setInt(1,Integer.valueOf(matriz[i][1].toString()));
				    pstmt.setString(2, matriz[i][0].toString().trim());
					
					System.out.println(query);
					
	//				pstmt.executeUpdate();
			}
				con.commit();
		} catch (Exception e) {
				System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_IZAGAR_Relacion_de_Asignaciones_Liquidadaso ] update  SQLException:  "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Actualizar_IZAGAR_Relacion_de_Asignaciones_Liquidadaso ] update  SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
					return false;
					}	
		}finally{
				try {
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
		}		
		return true;
	}
	
	public boolean Concepto_extra(Obj_Conceptos_De_Extras_Para_Lista_De_Raya concepto, int folio){
		String query = "update tb_conceptos_de_extra_de_lista_de_raya set concepto_extra=?, abreviatura=?, status=? where folio_concepto_extra=" + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, concepto.getConcepto().toUpperCase().trim());
			pstmt.setString(2, concepto.getAbreviatura().toUpperCase().trim());
			pstmt.setString(3, (concepto.getStatus().equals("VIGENTE"))?"1":"0");
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();

					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Concepto_extra ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Observacion_de_corte_guardado(String folio_corte, String observacion){
		String update =" update tb_alimentacion_de_cortes set observacion= ?"+
						" where folio_corte = ?";
		Connection con = new Connexion().conexion();
		
		try {
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(update);
			
				 pstmt.setString(1, observacion);
				 pstmt.setString(2, folio_corte);
				pstmt.executeUpdate();

				con.commit();
		} catch (Exception e) {
				System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							 JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Observacion_de_corte_guardado ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							 JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Observacion_de_corte_guardado ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
					return false;
					}	
		}finally{
				try {
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
		}		
		return true;
	}
	
	public boolean consideracion_para_checador(int folio_emp, String fecha, int consid_imp, int consid_fav, String clave_master, String observacion,String omision_mod, String status_mod){
		String query ="exec sp_update_consideracion_para_checador "+folio_emp+",'"+fecha+"',"+consid_imp+","+consid_fav+",'"+clave_master+"','"+observacion+"',"+usuario.getFolio()+",'"+omision_mod+"','"+status_mod+"'";
		
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
						System.out.println(query);
				pstmt.executeUpdate();

				con.commit();
		} catch (Exception e) {
				System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							 JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ consideracion_para_checador ] sp_update_consideracion_para_checador  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							 JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ consideracion_para_checador ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
					return false;
					}	
		}finally{
				try {
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
		}		
		return true;
	}
	
	public boolean actualizar_tabla_cortes_con_asignaciones(){

		String query_asignaciones = "exec sp_asignaciones_en_cortes;";
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query_asignaciones);
			pstmt.executeUpdate();

				con.commit();
		} catch (Exception e) {
				System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							 JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ actualizar_tabla_cortes_con_asignaciones ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							 JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ actualizar_tabla_cortes_con_asignaciones ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
					return false;
					}	
		}finally{
				try {
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
		}		
		return true;
	}
	
	public boolean actualizar_folio_periodo_fs(String accion,int valor){
		String query = "";
		if(accion.equals("")){
			query = "update tb_folios set folio = "+valor+" where transaccion = 'Periodos Captura Fuente de Sodas'";
		}else{
			if(accion.equals("Avanzar")){
				query = "update tb_folios set folio = (select folio+1 from tb_folios where transaccion = 'Periodos Captura Fuente de Sodas') where transaccion = 'Periodos Captura Fuente de Sodas'";
			}else{
				query = "update tb_folios set folio = (select folio-1 from tb_folios where transaccion = 'Periodos Captura Fuente de Sodas') where transaccion = 'Periodos Captura Fuente de Sodas'";
			}
		}
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();

				con.commit();
		} catch (Exception e) {
				System.out.println("SQLException: "+e.getMessage());
					if(con != null){
						try{
							System.out.println("La transacción ha sido abortada");
							con.rollback();
							 JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ actualizar_folio_periodo_fs ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}catch(SQLException ex){
							System.out.println(ex.getMessage());
							 JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ actualizar_folio_periodo_fs ] update  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
					return false;
					}	
		}finally{
				try {
					con.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
		}		
		return true;
	}
		
	public boolean PCAsignadasaEstab(Obj_Asignacion_De_Computadoras_Para_Checador_Por_Establecimiento pcnombre, int folio){
		String query = " exec sp_actualizar_pc_asignada_a_establecimiento_para_checador ?,?,?," + folio;
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pcnombre.getNombre_Pc().toUpperCase().trim());
			pstmt.setString(2, pcnombre.getEstablecimiento().toUpperCase().trim());
			pstmt.setInt(3, pcnombre.getStatus());
			
			
			
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ PCAsignadasaEstab ] update  SQLException: sp_actualizar_pc_asignada_a_establecimiento_para_checador "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ PCAsignadasaEstab ] update  SQLException: sp_actualizar_pc_asignada_a_establecimiento_para_checador "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
	
	public boolean Aceptar_Negar_Sueldo_o_Bono(Object[][] guardarAN_sueldo_bono){
		int  folio_usuario= usuario.getFolio();
		String query = " exec sp_actualizar_negacion_o_aceptacion_del_sueldo_y_bono ?,?,?,?,?,?";
		
		int i=0;
		
		Connection con = new Connexion().conexion();
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);

			for(i=0;i<guardarAN_sueldo_bono.length;i++){
				if(guardarAN_sueldo_bono[i][5].toString().equals("true")){ 
			
					System.out.println(guardarAN_sueldo_bono[i][4].toString().trim());
					
					
			pstmt.setInt(1, Integer.valueOf(guardarAN_sueldo_bono[i][0].toString()));
			pstmt.setFloat(2, Float.valueOf(guardarAN_sueldo_bono[i][1].toString()));
			pstmt.setFloat(3, Float.valueOf(guardarAN_sueldo_bono[i][2].toString()));
			pstmt.setString(4, String.valueOf(guardarAN_sueldo_bono[i][3].toString().trim()));
			pstmt.setInt(5, Integer.valueOf(guardarAN_sueldo_bono[i][4].toString().trim()));
			pstmt.setInt(6, folio_usuario);
			
			
				pstmt.executeUpdate();	
				}
			}
			
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Aceptar_Negar_Sueldo_o_Bono ] update  SQLException:\n Procedimiento Almacenado: "+query+
							"\n Valores Enviados Que Provocaron El Error: " +
							"\nValor 1:"+guardarAN_sueldo_bono[i][0] +
							"\nValor 2: "+guardarAN_sueldo_bono[i][1] +
							"\nValor 3: "+guardarAN_sueldo_bono[i][2] +
							"\nValor 4: "+guardarAN_sueldo_bono[i][3] +
							"\nValor 5: "+guardarAN_sueldo_bono[i][4] +
							"\n Error Mostrado SQL: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en ActualizarSQL  en la funcion [ Aceptar_Negar_Sueldo_o_Bono ] update  SQLException:\n sp_actualizar_negacion_o_aceptacion_del_sueldo_y_bono "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return true;
	}
}