package Cat_Lista_de_Raya;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Cat_Reportes.Cat_Reportes_De_Lista_De_Raya;
import Conexiones_SQL.Connexion;
import Obj_Administracion_del_Sistema.Obj_Usuario;
import Obj_Lista_de_Raya.Obj_Autorizacion_Auditoria;
import Obj_Lista_de_Raya.Obj_Autorizacion_Finanzas;
import Obj_Lista_de_Raya.Obj_Fue_Sodas_DH;
import Obj_Lista_de_Raya.Obj_Revision_De_Lista_Raya;
import Obj_Lista_de_Raya.Obj_Totales_De_Cheque;

@SuppressWarnings("serial")
/** CTRL EN CAT_ROOT_LISTA_RAYA PARA AGREGAR BOTON **/
public class Cat_Revision_De_Lista_Raya extends Cat_Root_Lista_Raya {
	 boolean acceso = null != null;
	 int cantidad_sueldos_mod =0;	
	private DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Revision_De_Lista_Raya().get_tabla_model(),
		new String[]{"","Folio", "Nombre Completo", "Establecimiento", "Sueldo",
			"Bono", "P.Saldo ini", "Desc.Prest", "P.Saldo Fin", "F.Sodas",
			"Imp.","Omi.", "Faltas", "Ina.", "Gafete", "Cortes", 
			"Infvt", "Pension", "Banamex", "Banorte", "Hrs Ext", "Extra", 
			"Día Ext", "A Pagar", "Observaciones D.H.", "Observaciones II" }){
			
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
		   	java.lang.Boolean.class,
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		    
		   	java.lang.Object.class,
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class,  
		   	java.lang.Object.class,  
		   	java.lang.Object.class, 
		    	
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class,  
		   	java.lang.Object.class,  
		   	java.lang.Object.class, 
		    	
		   	java.lang.Object.class, 
		   	java.lang.Object.class, 
		   	java.lang.Object.class,  
		   	java.lang.Object.class,  
		   	java.lang.Object.class,
		   	
		   	java.lang.Object.class,
		   	java.lang.Object.class,  
		   	java.lang.Object.class,  
		   	java.lang.Object.class,
		   	java.lang.Object.class
		    	
	    };
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
	    public boolean isCellEditable(int fila, int columna){
	       	 switch(columna){
	       	 	case 0  : return true; 
	      	 	case 1  : return false; 
	       	 	case 3  : return false; 
	       	 	case 4  : return false; 
	       	 	
	       	 	case 5  : return false;
	       	 	case 6  : return false; 
	       	 	case 7  : return false; 
	       	 	case 8  : return false; 
	       	 	case 9  : return false; 
	        	 	
	       	 	case 10 : return false; 
	       	 	case 11 : return false; 
	       	 	case 12 : return false; 
	       	 	case 13 : return false; 
	       	 	case 14 : return false; 
	        	 	
	       	 	case 15 : return false; 
	       	 	case 16 : return false; 
	       	 	case 17 : return false; 
	       	 	case 18 : return false; 
	       	 	case 19 : return false;
	        	 	
	       	 	case 20 : return false; 
	       	 	case 21 : return false; 
	       	 	case 22 : return false; 
	       	    case 23 : return false; 
	       	 	case 24 : return true; 
	       	 	case 25 : return acceso;
	       	 	
	       	 }
	 		return false;
	 	}
	   
	};
		
	private JTable tabla = new JTable(tabla_model);
	private JScrollPane scroll_tabla = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JLabel JLBcambios_sueldo= new JLabel();	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(tabla_model); 
	
	/* EL CONSTRUCTOR TIENE EL NOMBRE PUBLIC Y SEGUIDO DEL NOMBRE DE LA CLASE */
	public Cat_Revision_De_Lista_Raya(){
		this.setTitle("Revisión lista raya");
		
		this.panel.add(scroll_tabla).setBounds(30,60,1300,620);
		cont.add(panel);
		
		panel.add(new JLabel("Autorizacion Auditoria:")).setBounds(730,20,120,20);
		panel.add(JLBAutoriazacion_Auditoria).setBounds(840,20,20,20);
		panel.add(new JLabel("Traspaso Fuente Sodas:")).setBounds(870,20,120,20);
		panel.add(JLBGuardado_Fte_Sodas).setBounds(1000,20,20,20);
		
		panel.add(new JLabel("Autorizacion Finanzas:")).setBounds(730,40,120,20);
		panel.add(JLBAutorizacion_finanzas).setBounds(840,40,20,20);
		panel.add(new JLabel("Totales Nomina y Cheques:")).setBounds(870,40,135,20);
		panel.add(JLBTotales_Nomina).setBounds(1000,40,20,20);
		
		
		
		panel.add(JLBcambios_sueldo).setBounds(1050,40,350,20);
//		if(cantidad_sueldos_mod>0){ 
//		JLBcambios_sueldo.setText("<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLUE><CENTER><b><p>Sueldos Pendientes de Auditoria Por Autorizar: "+cantidad_sueldos_mod+"</p></b></CENTER></FONT></html>");
//        btn_guardar.setEnabled(false);
//		}
		
		this.init_tabla();
		this.init_component();
		cargar_autorizaciones();
		txtCalendario.setEnabled(false);
		
		btn_imprimir.setEnabled(false);
		btn_nomina.setEnabled(false);
		tabla.addMouseListener(opTablaFiltroSeleccion);
		
		this.btn_guardar.addActionListener(op_guardar);
		this.btn_imprimir.addActionListener(op_imprimir);
		this.btn_nomina.addActionListener(op_totales_cheque);
		this.btn_refrescar.addActionListener(op_refrescar);
		this.btn_generar.addActionListener(op_generar);

		this.btn_lista_raya_pasadas.addActionListener(op_consulta_lista);
		
		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtNombre_Completo.addKeyListener(op_filtro_nombre);
		this.cmbEstablecimientos.addActionListener(op_filtro_establecimiento);
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.addWindowListener(op_cerrar);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		busqueda_Observaciones_auditoria();
		
		
		
//      asigna el foco al JTextField 
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){
                    txtNombre_Completo.requestFocus();
             }
        });
		

	}

	/**
	 *  SE DIO DE ALTA LA ACCION LISTENER OP_CONSULTA LISTA
	 *  SE LE DARA CLICK ALA OPERACION Y MOSTRARA LA OPCION ADD METOD
	**/
	ActionListener op_consulta_lista = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Consulta_Lista_de_Raya_Pasadas().setVisible(true);	
		}
		
	};
	
	
	WindowListener op_cerrar = new WindowListener() {
		public void windowOpened(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		@SuppressWarnings("unchecked")
		public void windowClosing(WindowEvent e) {
			if(JOptionPane.showConfirmDialog(null, "¿Desea guardar antes de cerrar?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
				
				txtFolio.setText("");
				txtNombre_Completo.setText("");
				cmbEstablecimientos.setSelectedIndex(0);
				
				if(tabla.isEditing()){
					tabla.getCellEditor().stopCellEditing();
				}
				
				new Obj_Revision_De_Lista_Raya().guardar(tabla_guardar(),new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
			}
		}
		public void windowClosed(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
	};
	
	
	public boolean  busqueda_Observaciones_auditoria() {
		Connexion con = new Connexion();
		Obj_Usuario user = new Obj_Usuario().LeerSession();
		 int folio= user.getFolio();
		String query = "if(select departamento from tb_empleado where folio="+folio  + ")=(Select departamento_mod_observacion_II_lista_raya from tb_configuracion_sistema)"+
                            " select 'true' as acceso   else   select 'false' as acceso ";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				 acceso =(rs.getBoolean(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "Error en Cat_Revision_lista_de_Raya  en la funcion busqueda_Observaciones_auditoria"+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		finally{
			 if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error en Cat_Revision_lista_de_Raya  en la funcion busqueda_Observaciones_auditoria"+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} }
		}
		return acceso;
		
	}
	
	public int  Checar_Cambios_De_Sueldo_Pendientes_De_Autorizar() {
		Connexion con = new Connexion();
		String query = "	if (select validacion_sueldo_auditoria from tb_configuracion_sistema)='true' "+
		               "        select count(folio_empleado) from tb_historico_sueldos_empleados where status='N'" +
		               " 	else select 0  ";
		
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				cantidad_sueldos_mod =(rs.getInt(1));
				
				if(cantidad_sueldos_mod>0){ 
					JLBcambios_sueldo.setText("<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=RED><CENTER><b><p>Sueldos Pendientes de Auditoria Por Autorizar: "+cantidad_sueldos_mod+"</p></b></CENTER></FONT></html>");
			        btn_guardar.setEnabled(false);
			        btn_imprimir.setEnabled(false);
					}
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "Error en Cat_Autorizacion_De_Cambio_De_Sueldo_O_Bono en la funcion Checar_Activo()"+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE,new ImageIcon("imagen/usuario-icono-eliminar5252-64.png"));
			return cantidad_sueldos_mod ;
		}
		finally{
			 if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error en Cat_Autorizacion_De_Cambio_De_Sueldo_O_Bono en la funcion Checar_Activo()"+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE,new ImageIcon("imagen/usuario-icono-eliminar5252-64.png"));
				e.printStackTrace();
			} }
		}
		return cantidad_sueldos_mod;
			}
	
  
		
		
		
	public void cargar_autorizaciones(){
		

		
		if((new Obj_Fue_Sodas_DH().busquedaautoizacionfs().isStatus_autorizacion()))
		  {btn_imprimir.setEnabled(false);
		   JLBGuardado_Fte_Sodas.setEnabled(false);
		  }else{btn_imprimir.setEnabled(true);
			    JLBGuardado_Fte_Sodas.setEnabled(true);}
		
		if(new Obj_Autorizacion_Auditoria().buscar().isAutorizar()){
			JLBAutoriazacion_Auditoria.setEnabled(true);
			 }else{JLBAutoriazacion_Auditoria.setEnabled(false);}
		
		if(new Obj_Autorizacion_Finanzas().buscar().isAutorizar()){
			JLBAutorizacion_finanzas.setEnabled(true);
			 }else{JLBAutorizacion_finanzas.setEnabled(false);}
	    
	    if(new Obj_Totales_De_Cheque().buscar_autorizacion().isAutorizar()){
	    	JLBTotales_Nomina.setEnabled(true);
		    }else{JLBTotales_Nomina.setEnabled(false);}
	    
	    Checar_Cambios_De_Sueldo_Pendientes_De_Autorizar();
		};

		
	
	ActionListener op_generar = new ActionListener() {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
			
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			cmbEstablecimientos.setSelectedIndex(0);
			
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			
			if(valida_error() != ""){
				JOptionPane.showMessageDialog(null, "Los siguientes datos son requeridos:\n"+valida_error(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(new Obj_Revision_De_Lista_Raya().init_autorizacion()){
					if(new Obj_Revision_De_Lista_Raya().init_totales_nomina()){
						 if(new Obj_Totales_De_Cheque().buscar_autorizacion().isAutorizar()){
						    	
						Obj_Revision_De_Lista_Raya lista_raya = new Obj_Revision_De_Lista_Raya();
						if(lista_raya.generar(tabla_generar(),new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()))){
							
							JOptionPane.showMessageDialog(null, "La Lista De Raya Se Generó Con Éxito \n Se Cerrara-Abrira y Le Pedira Guardar La Nueva Pre Lista De Raya ","Aviso",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("imagen/aplicara-el-dialogo-icono-6256-32.png"));
							dispose();
							new Cat_Revision_De_Lista_Raya().setVisible(true);
							btn_guardar.doClick();
							return;
						}else{
							JOptionPane.showMessageDialog(null, "La lista de raya no se pudo generar","Error",JOptionPane.ERROR_MESSAGE);
							return;
						}
						 }else{
							 
								JOptionPane.showMessageDialog(null, "Antes De Generar La Lista De Raya Tiene Que Guardar Los Totales De Cheque", "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
								return;
						 }
					  }else{
						  
						JOptionPane.showMessageDialog(null, "Antes De Generar La Lista De Raya Tiene Que Guardar Los Totales De Nomina", "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
						return;
					}
				}else{
					JOptionPane.showMessageDialog(null, "La Lista De Raya No Esta Autorizada:", "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
					return;
				}
			}
		}
	};
	
	private Object[][] tabla_generar(){
		Object[][] matriz = new Object[tabla.getRowCount()][tabla.getColumnCount()];
		for(int i=0; i<tabla.getRowCount(); i++){
			for(int j=0; j<=tabla.getColumnCount(); j++){
				switch(j){
/*folio*/				case 1 : matriz[i][j] = Integer.parseInt(tabla_model.getValueAt(i,j).toString().trim());		break;
/*empleado*/			case 2 : matriz[i][j] = tabla_model.getValueAt(i,j).toString().trim();							break;
/*establecimiento*/		case 3 : matriz[i][j] =tabla_model.getValueAt(i,j).toString().trim();							break;
/*sueldo*/				case 4 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*bono*/					case 5 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*P.Saldo ini*/					case 6 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Desc.Prest*/					case 7 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*P.Saldo Fin*/				case 8 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*F.Sodas*/				case 9 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Imp.*/				case 10 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Omi.*/					case 11 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Faltas*/					case 12 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Ina.*/					case 13 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Gafete*/					case 14 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Cortes*/					case 15 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Infvt*/					case 16 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Pension*/					case 17 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Banamex*/					case 18 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Banorte*/					case 19 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Horas Ext*/					case 20 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Extra*/					case 21 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Dia Ext*/					case 22 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*A Pagar*/					case 23 :
						if(tabla_model.getValueAt(i,j).toString().length() == 0){
							matriz[i][j] = Float.parseFloat("0");
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
/*Obs D.H.*/			case 24 : matriz[i][j] = tabla_model.getValueAt(i,j).toString().trim();			break;
/*Obs II*/
					
				}

			}

		}
		return matriz;
	}
	
	ActionListener op_refrescar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			actualizar();
			JOptionPane.showMessageDialog(null, "Se guardó y se refrescaron los datos correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
		}
	};
	
	@SuppressWarnings("unchecked")
	public void actualizar(){
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
		
		txtFolio.setText("");
		txtNombre_Completo.setText("");
		cmbEstablecimientos.setSelectedIndex(0);
		
		if(tabla.isEditing()){
			tabla.getCellEditor().stopCellEditing();
		}
		
		Obj_Revision_De_Lista_Raya lista_raya = new Obj_Revision_De_Lista_Raya();
		
		if(lista_raya.guardar(tabla_guardar(),new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()))){
			while(tabla.getRowCount() > 0){
				tabla_model.removeRow(0);
			}
			
			Object[][] Tabla = new Obj_Revision_De_Lista_Raya().get_tabla_model();
			Object[] fila = new Object[tabla.getColumnCount()];
			for(int i=0; i<Tabla.length; i++){
				tabla_model.addRow(fila); 
				for(int j=0; j<tabla.getColumnCount(); j++){
					tabla_model.setValueAt(Tabla[i][j], i,j);
				}
			}
		}		
	}
	
	ActionListener op_guardar = new ActionListener() {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
			
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			cmbEstablecimientos.setSelectedIndex(0);
			
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
			
			if(valida_error() != ""){
				JOptionPane.showMessageDialog(null, "Los siguientes datos son requeridos:\n"+valida_error(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea Guardar La  Pre Lista De Raya?") == 0){
					Obj_Revision_De_Lista_Raya lista_raya = new Obj_Revision_De_Lista_Raya();
					if(lista_raya.guardar(tabla_guardar(),new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()))){
							 Obj_Totales_De_Cheque autorizacion = new Obj_Totales_De_Cheque();
							 autorizacion.setAutorizar(false);
							if(autorizacion.actualizar()){
								cargar_autorizaciones();
	
								btn_nomina.setEnabled(true);
								JOptionPane.showMessageDialog(null, "La Tabla De Pre Lista De Raya Se Guardo Exitosamente!!!","Aviso",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("imagen/aplicara-el-dialogo-icono-6256-32.png"));
								return;
						}else{
							JOptionPane.showMessageDialog(null, "Error Al Guardar ", "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}else{
					return;
				}
			}
		}
	};
	
	ActionListener op_imprimir = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			update_autorizar_finanzas();
			cargar_autorizaciones();
			new Cat_Reportes_De_Lista_De_Raya().setVisible(true);
		}
	};
	
	ActionListener op_totales_cheque = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new Cat_Totales_De_Nomina().setVisible(true);
		}
	};
	
	public void update_autorizar_finanzas(){
		String todos = "update tb_autorizaciones set autorizar_finanzas='true' ";
		PreparedStatement pstmt = null;
		Connection con = new Connexion().conexion();
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(todos);
		
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en Cat_Imprimir_Lista_de_Raya  en la funcion [ update_autorizar_finanzas ] SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					JOptionPane.showMessageDialog(null, "Error en Cat_Imprimir_Lista_de_Raya  en la funcion [ update_autorizar_finanzas ] SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Imprimir_Lista_de_Raya  en la funcion [ update_autorizar_finanzas ] SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}
	private Object[][] tabla_guardar(){
		Object[][] matriz = new Object[tabla.getRowCount()][6];
		for(int i=0; i<tabla.getRowCount(); i++){
			matriz[i][0] = Boolean.parseBoolean(tabla_model.getValueAt(i,0).toString().trim());
			matriz[i][1] = Integer.parseInt(tabla_model.getValueAt(i,1).toString().trim());
			matriz[i][2] = tabla_model.getValueAt(i,3).toString().trim();
			matriz[i][3] = tabla_model.getValueAt(i,23).toString().trim().equals("") ? 0 : Float.parseFloat(tabla_model.getValueAt(i,23).toString().trim());
			matriz[i][4] = tabla_model.getValueAt(i,24).toString().trim();
			matriz[i][5] = tabla_model.getValueAt(i,25).toString().trim();
		}
		return matriz;
	}
	
	public String valida_error(){
		String error="";
			String fechaNull = txtCalendario.getDate()+"";
			if(fechaNull.equals("null"))error+= "Fecha de la lista de raya\n";
		return error;
	}
	
	public void init_component(){
		String fecha = new Obj_Revision_De_Lista_Raya().get_fecha();
		
		if(fecha != ""){
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
				txtCalendario.setDate(date);
				txtCalendario.setEnabled(false);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}				
			}
	}
	
	@SuppressWarnings("unchecked")
	public void init_tabla(){
		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(16);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(16);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(40);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(40);
		this.tabla.getColumnModel().getColumn(2).setMaxWidth(260);
		this.tabla.getColumnModel().getColumn(2).setMinWidth(260);
		this.tabla.getColumnModel().getColumn(3).setMaxWidth(110);
		this.tabla.getColumnModel().getColumn(3).setMinWidth(110);
		this.tabla.getColumnModel().getColumn(4).setMaxWidth(45);
		this.tabla.getColumnModel().getColumn(4).setMinWidth(45);
		
		this.tabla.getColumnModel().getColumn(5).setMaxWidth(40);
		this.tabla.getColumnModel().getColumn(5).setMinWidth(40);
		this.tabla.getColumnModel().getColumn(6).setMaxWidth(60);
		this.tabla.getColumnModel().getColumn(6).setMinWidth(60);
		this.tabla.getColumnModel().getColumn(7).setMaxWidth(62);
		this.tabla.getColumnModel().getColumn(7).setMinWidth(62);
		this.tabla.getColumnModel().getColumn(8).setMaxWidth(65);
		this.tabla.getColumnModel().getColumn(8).setMinWidth(65);
		this.tabla.getColumnModel().getColumn(9).setMaxWidth(50);
		this.tabla.getColumnModel().getColumn(9).setMinWidth(50);
		
		this.tabla.getColumnModel().getColumn(10).setMaxWidth(35);
		this.tabla.getColumnModel().getColumn(10).setMinWidth(35);
		this.tabla.getColumnModel().getColumn(11).setMaxWidth(35);
		this.tabla.getColumnModel().getColumn(11).setMinWidth(35);
		this.tabla.getColumnModel().getColumn(12).setMaxWidth(45);
		this.tabla.getColumnModel().getColumn(12).setMinWidth(45);
		this.tabla.getColumnModel().getColumn(13).setMaxWidth(35);
		this.tabla.getColumnModel().getColumn(13).setMinWidth(35);
		this.tabla.getColumnModel().getColumn(14).setMaxWidth(45);
		this.tabla.getColumnModel().getColumn(14).setMinWidth(45);
		
		this.tabla.getColumnModel().getColumn(15).setMaxWidth(45);
		this.tabla.getColumnModel().getColumn(15).setMinWidth(45);
		this.tabla.getColumnModel().getColumn(16).setMaxWidth(45);
		this.tabla.getColumnModel().getColumn(16).setMinWidth(45);
		this.tabla.getColumnModel().getColumn(17).setMaxWidth(50);
		this.tabla.getColumnModel().getColumn(17).setMinWidth(50);
		this.tabla.getColumnModel().getColumn(18).setMaxWidth(55);
		this.tabla.getColumnModel().getColumn(18).setMinWidth(55);
		this.tabla.getColumnModel().getColumn(19).setMaxWidth(55);
		this.tabla.getColumnModel().getColumn(19).setMinWidth(55);
		
		this.tabla.getColumnModel().getColumn(20).setMaxWidth(45);
		this.tabla.getColumnModel().getColumn(20).setMinWidth(45);
		this.tabla.getColumnModel().getColumn(21).setMaxWidth(50);
		this.tabla.getColumnModel().getColumn(21).setMinWidth(50);
		this.tabla.getColumnModel().getColumn(22).setMaxWidth(55);
		this.tabla.getColumnModel().getColumn(22).setMinWidth(55);
		this.tabla.getColumnModel().getColumn(23).setMaxWidth(55);
		this.tabla.getColumnModel().getColumn(23).setMinWidth(55);
		this.tabla.getColumnModel().getColumn(24).setMaxWidth(350);
		this.tabla.getColumnModel().getColumn(24).setMinWidth(350);
		this.tabla.getColumnModel().getColumn(25).setMaxWidth(230);
		this.tabla.getColumnModel().getColumn(25).setMinWidth(230);
    	
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				Component componente = null;
			
				switch(column){
					case 0: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
						break;
					case 1: 
						
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 2:
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 3: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 4: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 5: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 6: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 7: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 8: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 9: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 10: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 11:
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 12: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 13: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 14: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 15: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 16: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 17: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 18: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 19: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 20:
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 21: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 22: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 23: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
						break;
					case 24: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 25: 
						componente = new JLabel(value == null? "": value.toString());
						if(Boolean.parseBoolean(tabla.getValueAt(row,0)+"")== true){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
						
				}
				return componente;
			} 
		}; 
		for(int i=0; i<tabla.getColumnCount(); i++){
			this.tabla.getColumnModel().getColumn(i).setCellRenderer(render); 
		}
		this.tabla.setRowSorter(trsfiltro);  
    }
	
	
	MouseListener opTablaFiltroSeleccion = new MouseListener() {
		public void mousePressed(MouseEvent e) {
				btn_imprimir.setEnabled(false);
				btn_nomina.setEnabled(false);
			}
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
	};
	
	KeyListener op_filtro_folio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 1));
		}
		public void keyTyped(KeyEvent arg0) {
			char caracter = arg0.getKeyChar();
			if(((caracter < '0') ||
				(caracter > '9')) &&
			    (caracter != KeyEvent.VK_BACK_SPACE)){
				arg0.consume(); 
			}	
		}
		public void keyPressed(KeyEvent arg0) {}
	};
	
	KeyListener op_filtro_nombre = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 2));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	ActionListener op_filtro_establecimiento = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 3));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
	public static void main(String [] arg){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Revision_De_Lista_Raya().setVisible(true);
		}catch(Exception e){	}
	}

}
