package Cat_Checador;


import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.BuscarTablasModel;
import Obj_Lista_de_Raya.Obj_Departamento;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Consideraciones_De_Impuntualidad_En_Asistencia extends JFrame {

	int folio_emp = 0; 	
	String empleado = ""; 	
	String fecha = ""; 	
	String ent_sal = ""; 	
	String tipo_checada = ""; 	
	String Status_Mov="";
	
	int  imp = 0; 	
	int fav  = 0; 	
	String observacion  = "";
	int realizo_consideracion = 0;
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JDateChooser c_inicio = new JDateChooser();
	JDateChooser c_final = new JDateChooser();
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
	String departamento[] = new Obj_Departamento().Combo_Departamento();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbDepartamento = new JComboBox(departamento);
	
	JLabel JLBlinicio= new JLabel(new ImageIcon("Imagen/iniciar-icono-4628-16.png") );
	JLabel JLBfin= new JLabel(new ImageIcon("Imagen/acabado-icono-7912-16.png") );
	JLabel JLBestablecimiento= new JLabel(new ImageIcon("Imagen/folder-home-home-icone-5663-16.png") );
	JLabel JLBdepartamento= new JLabel(new ImageIcon("Imagen/departamento-icono-5365-16.png") );
	
	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio De Corte", 15, "String");
	JTextField txtNombre = new Componentes().text(new JTextField(), "Nombre", 15, "String");
	
	JButton btnGenerar = new JButton("Actualizar",new ImageIcon("Imagen/Actualizar.png"));
	
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
//	static Object[][] cortes_guardados = new BuscarTablasModel().filtro_impuntualidad_a_considerar();
	DefaultTableModel modelo = new DefaultTableModel(null,
            new String[]{ "Folio", "Nombre", "Fecha Mov", "Hora Mov", "Dia",
							"Entrada-Salida", "Tipo Mov", "15m/Comida", "Impuntualidad", "Favor", "Tipo Permiso",
							"Min Imp Considerados", "Min Fav Considerados", "Observaciones", "Realizo Mov", "Estatus Reg.","Estatus Modif."}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.Boolean.class
	    
         };
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0  : return false; 
        	 	case 1  : return false; 
        	 	case 2  : return false;
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
        	 	} 				
 			return false;
 		}
	};
	
	JTable tabla = new JTable(modelo);
	JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	Border blackline;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public Cat_Consideraciones_De_Impuntualidad_En_Asistencia(){
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/check-vcard-icone-9025-32.png"));
		this.setTitle("Consideracion De Impuntualidad Checador");
		this.panel.setBorder(BorderFactory.createTitledBorder(blackline, "Filtrar"));

		this.c_inicio.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
		this.c_final.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
				
		trsfiltro = new TableRowSorter(modelo); 
		tabla.setRowSorter(trsfiltro);  
		
		
		this.panel.add(new JLabel("Fecha Inicio:")).setBounds(15,25,100,20);
		this.panel.add(JLBlinicio).setBounds(75,25,20,20);
		this.panel.add(c_inicio).setBounds(95,25,100,20);
		this.panel.add(new JLabel("Fecha Final:")).setBounds(15,55,100,20);
		this.panel.add(JLBfin).setBounds(75,55,20,20);
		this.panel.add(c_final).setBounds(95,55,100,20);
	    this.panel.add(new JLabel("Establecimiento:")).setBounds(220,25,150,20);
	    this.panel.add(JLBestablecimiento).setBounds(300,25,20,20);
		this.panel.add(cmbEstablecimiento).setBounds(320,25,170,20);
		this.panel.add(new JLabel("Departamento:")).setBounds(225,55,150,20);
		this.panel.add(JLBdepartamento).setBounds(300,55,20,20);
		this.panel.add(cmbDepartamento).setBounds(320,55,170,20);
		
		this.panel.add(btnGenerar).setBounds(900,85,100,20);
		panel.add(txtFolio).setBounds(10,90,40,20);
		panel.add(txtNombre).setBounds(50,90,270,20);
		panel.add(scroll).setBounds(10,110,990,590);
		cont.add(panel);
		
		c_inicio.setEnabled(false);
		agregar(tabla);
		llamar_render();
		cargar_fechas();
		
		txtFolio.addKeyListener(opFiltroAsignacion);
		txtNombre.addKeyListener(opFiltroFolioCajero);
		
		btnGenerar.addActionListener(opGenerarTabla);
		
		this.setSize(1024,768);
		this.setLocationRelativeTo(null);
	}
	
	public void cargar_fechas(){
			int dias=0;
					try {
						dias= new BuscarSQL().dias_para_fecha_revision_consideracion();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
			Date date1 = null;
					  try {
						date1 = new SimpleDateFormat("dd/MM/yyyy").parse(new BuscarSQL().fecha(dias));
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			c_inicio.setDate(date1);
						
		    Date date2 = null;
						  try {
							date2 = new SimpleDateFormat("dd/MM/yyyy").parse(new BuscarSQL().fecha(0));
						} catch (ParseException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			c_final.setDate(date2);
		};

	
	
	
	@SuppressWarnings("unchecked")
	public void llamar_render(){
		
		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		int x = 60;
		
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(x-20);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(x-20);		
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(270);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(270);
		this.tabla.getColumnModel().getColumn(2).setMaxWidth(x+20);
		this.tabla.getColumnModel().getColumn(2).setMinWidth(x+20);
		
		this.tabla.getColumnModel().getColumn(3).setMaxWidth(x);
		this.tabla.getColumnModel().getColumn(3).setMinWidth(x);		
		this.tabla.getColumnModel().getColumn(4).setMaxWidth(x+10);
		this.tabla.getColumnModel().getColumn(4).setMinWidth(x+10);
		this.tabla.getColumnModel().getColumn(5).setMaxWidth(x+20);
		this.tabla.getColumnModel().getColumn(5).setMinWidth(x+20);
		
		this.tabla.getColumnModel().getColumn(6).setMaxWidth(x);
		this.tabla.getColumnModel().getColumn(6).setMinWidth(x);
		this.tabla.getColumnModel().getColumn(7).setMaxWidth(x+10);
		this.tabla.getColumnModel().getColumn(7).setMinWidth(x+10);
		
		this.tabla.getColumnModel().getColumn(8).setMaxWidth(x+20);
		this.tabla.getColumnModel().getColumn(8).setMinWidth(x+20);
		this.tabla.getColumnModel().getColumn(9).setMaxWidth(x);
		this.tabla.getColumnModel().getColumn(9).setMinWidth(x);
		this.tabla.getColumnModel().getColumn(10).setMaxWidth(x+150);
		this.tabla.getColumnModel().getColumn(10).setMinWidth(x+150);
		this.tabla.getColumnModel().getColumn(11).setMaxWidth(x+30);
		this.tabla.getColumnModel().getColumn(11).setMinWidth(x+30);
		this.tabla.getColumnModel().getColumn(12).setMaxWidth(x+30);
		this.tabla.getColumnModel().getColumn(12).setMinWidth(x+50);
		this.tabla.getColumnModel().getColumn(13).setMaxWidth(x+100);
		this.tabla.getColumnModel().getColumn(13).setMinWidth(x+100);
		this.tabla.getColumnModel().getColumn(14).setMaxWidth(270);
		this.tabla.getColumnModel().getColumn(14).setMinWidth(270);
		this.tabla.getColumnModel().getColumn(15).setMaxWidth(x);
		this.tabla.getColumnModel().getColumn(15).setMinWidth(x);
		this.tabla.getColumnModel().getColumn(16).setMaxWidth(x-20);
		this.tabla.getColumnModel().getColumn(16).setMinWidth(x-20);

		this.tabla.setRowSorter(trsfiltro);  
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(5).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		
		tabla.getColumnModel().getColumn(6).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(7).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(8).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(9).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(10).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		tabla.getColumnModel().getColumn(11).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(12).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(13).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(14).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		tabla.getColumnModel().getColumn(15).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		tabla.getColumnModel().getColumn(16).setCellRenderer(new tablaRenderer("CHB","centro","Arial","negrita",12));
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	    			int fila = tabla.getSelectedRow();
	    			
	    			if(tabla.getValueAt(fila, tabla.getColumnCount()-1).toString().equals("true")){
 	  				  JOptionPane.showMessageDialog(null, "Solo Esta Permitido Modificar El Registro Una Vez \n Este Registro Ya A Sido Modificado","Aviso", JOptionPane.ERROR_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
	    				return;
	    			}else{
		    					folio_emp = 	Integer.valueOf(tabla.getValueAt(fila, 0).toString().trim());
	    						 empleado = 	tabla.getValueAt(fila, 1).toString().trim();
	    						 	fecha = 	tabla.getValueAt(fila, 2).toString().trim()+" "+tabla.getValueAt(fila, 3).toString().trim();
		    					  ent_sal = 	tabla.getValueAt(fila, 5).toString().trim();
					   	     tipo_checada = 	tabla.getValueAt(fila, 7).toString().trim();	    					  
		    					   	  imp = 	Integer.valueOf(tabla.getValueAt(fila, 8).toString().trim());
		    					   	  fav = 	Integer.valueOf(tabla.getValueAt(fila, 9).toString().trim());
		    				  observacion = 	tabla.getValueAt(fila, 13).toString().trim();
		    				   Status_Mov =     tabla.getValueAt(fila, 15).toString().trim();
	//	    			dispose();
		    			new Cat_Ventana_Consideracion().setVisible(true);
	    			}
	        	}
	        }
        });
    }
	
	ActionListener opGenerarTabla = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if(c_inicio.getDate()==null || c_final.getDate()==null){
				
				JOptionPane.showMessageDialog(null, "campos nulos", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
				if(c_final.getDate().before(c_inicio.getDate())){
					JOptionPane.showMessageDialog(null, "El rango de fechas esta invertido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}else{
//					JOptionPane.showMessageDialog(null, "rango correcto", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
//					return;
					
					String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
					String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
					String Establecimiento = cmbEstablecimiento.getSelectedItem().toString();
					String Departamento = cmbDepartamento.getSelectedItem().toString();
					String folios_empleados = "Selecciona un Empleado";

					while(tabla.getRowCount()>0)
						modelo.removeRow(0);
					
					Object[][] recargarTabla = new BuscarTablasModel().filtro_impuntualidad_a_considerar(fecha_inicio,fecha_final,Establecimiento,Departamento,folios_empleados);
					
					 String[] fila = new String[17];
                     for(int i=0; i<recargarTabla.length; i++){
                             fila[0] = recargarTabla[i][0]+"";
                             fila[1] = recargarTabla[i][1]+"";
                             fila[2] = recargarTabla[i][2]+"";
                             fila[3] = recargarTabla[i][3]+"";
                             fila[4] = recargarTabla[i][4]+"";
                             fila[5] = recargarTabla[i][5]+"";
                             fila[6] = recargarTabla[i][6]+"";
                             fila[7] = recargarTabla[i][7]+"";
                             fila[8] = recargarTabla[i][8]+"";
                             fila[9] = recargarTabla[i][9]+"";
                             fila[10] = recargarTabla[i][10]+"";
                             fila[11] = recargarTabla[i][11]+"";
                             fila[12] = recargarTabla[i][12]+"";
                             fila[13] = recargarTabla[i][13]+"";
                             fila[14] = recargarTabla[i][14]+"";
                             fila[15] = recargarTabla[i][15]+"";
                             fila[16] = recargarTabla[i][16]+"";
                             modelo.addRow(fila);
                     }
				}
			}
		}
	};
	
	KeyListener opFiltroAsignacion = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText().toUpperCase(), 0));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	KeyListener opFiltroFolioCajero = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre.getText().toUpperCase(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	 ///////VENTANA EMERGENTE
	
	public class Cat_Ventana_Consideracion extends JDialog {
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		JLabel lblFolio_corte = new JLabel("");
		JLabel lblCajero = new JLabel("");
		JLabel lblFecha = new JLabel("");
		JLabel lblMovimiento = new JLabel("");
		
		JTextField txtImp = new Componentes().text(new JTextField(), "Observaciones", 4, "Double");
		JTextField txtFav = new Componentes().text(new JTextField(), "Observaciones", 4, "Double");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbTipo_checada =new JComboBox(new String[]{"No Aplica","Aplica"});
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbOmision =new JComboBox(new String[]{"No Aplica","Aplica"});
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbstatus_checada =new JComboBox(new String[]{"Vigente","Cancelado"});
		
		
		JTextArea txaObservacion =new Componentes().textArea(new JTextArea(), "Observaciones", 150);
		JScrollPane scrollObservacion = new JScrollPane(txaObservacion,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
		
		public Cat_Ventana_Consideracion(){
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setModal(true);
			this.setTitle("Consideracion checador");
			this.panel.setBorder(BorderFactory.createTitledBorder(blackline, "Guardar consideracion de checador seleccionado"));
			
			lblFolio_corte.setText("Folio Empleado:  "+folio_emp);
			lblCajero.setText("Empleado:  "+empleado);
			lblFecha.setText("Fecha:  "+fecha);
			lblMovimiento.setText("Movimiento: "+ent_sal);
			
			txtImp.setText(imp+"");
			txtFav.setText(fav+"");
			txaObservacion.setText(observacion);
			
			if(tipo_checada.equals("-")){
				cmbTipo_checada.setEnabled(false);
				cmbTipo_checada.setSelectedItem("No Aplica");
			}else{
				cmbTipo_checada.setEnabled(true);
				cmbTipo_checada.setSelectedItem("Aplica");
			}
			
			if(ent_sal.equals("FALT.REG.")){
				cmbOmision.setEnabled(true);
				cmbOmision.setSelectedItem("Aplica");
			}else{
				cmbOmision.setEnabled(false);
				cmbOmision.setSelectedItem("No Aplica");
			}
			
			int y=20;
			
			panel.add(lblFolio_corte).setBounds(15,y,150,20);
			panel.add(lblFecha).setBounds(320,y,180,20);
			
			panel.add(lblCajero).setBounds(15,y+=25,500,20);
			panel.add(lblMovimiento).setBounds(320,y,440,20);
			
			panel.add(new JLabel("Observacion:")).setBounds(200,y+=25,70,20);
			panel.add(scrollObservacion).setBounds(200,y+15,270,100);
			
			panel.add(new JLabel("Impuntualidad: ")).setBounds(15,y+=15,90,20);
			panel.add(txtImp).setBounds(100,y,80,20);
			
			panel.add(new JLabel("A Favor: ")).setBounds(15,y+=25,90,20);
			panel.add(txtFav).setBounds(100,y,80,20);
			
			panel.add(new JLabel("Clave Master: ")).setBounds(15,y+=25,90,20);
			panel.add(cmbTipo_checada).setBounds(100,y,80,20);
			
			panel.add(new JLabel("Omision: ")).setBounds(15,y+=25,90,20);
			panel.add(cmbOmision).setBounds(100,y,80,20);
			
			panel.add(new JLabel("Estatus Checada: ")).setBounds(15,y+=25,90,20);
			panel.add(cmbstatus_checada).setBounds(100,y,80,20);
			
			panel.add(btnGuardar).setBounds(370,y+25,100,20);

			txaObservacion.setLineWrap(true); 
			txaObservacion.setWrapStyleWord(true);
//			panel.add(scroll).setBounds(20,45,970,500);
			
			cont.add(panel);
			
			btnGuardar.addActionListener(opGuardarObservacion);
			
			this.setSize(500,270);
			this.setLocationRelativeTo(null);
		}
		
		ActionListener opGuardarObservacion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txaObservacion.getText().equals("")){
//		consideraciones ( mandar parametros para el update)
							int consid_imp = (imp - Integer.valueOf(txtImp.getText()))*-1;
							int consid_fav = (fav - Integer.valueOf(txtFav.getText()))*-1;
							
							System.out.println(imp);
							System.out.println(Integer.valueOf(txtImp.getText()));
							
							
							
							String clave_master = "";
							if(tipo_checada.equals("-")){
									clave_master="";
							}else{
									clave_master = cmbTipo_checada.getSelectedItem().toString().trim().equals("Aplica")?"":cmbTipo_checada.getSelectedItem().toString().trim();
							}
							
							String omision_mod = "";
							if(ent_sal.equals("FALT.REG.")){
								   omision_mod="";
								   
							}else{ omision_mod = cmbOmision.getSelectedItem().toString().trim().equals("Aplica")?"":
								cmbOmision.getSelectedItem().toString().trim();
							if(cmbOmision.isEnabled()){consid_imp=imp*-1;}}
							
							String status_mod = "";
							status_mod = cmbstatus_checada.getSelectedItem().toString().trim();

							if(status_mod.equals("Cancelado")){
								consid_imp=imp*-1;
								consid_fav=fav*-1;
								omision_mod="No Aplica";
								clave_master="";    			}
							
//							System.out.println("aqui es igual a = "+realizo_consideracion);
						if(new ActualizarSQL().consideracion_para_checador(folio_emp, fecha, consid_imp, consid_fav, clave_master, txaObservacion.getText().toUpperCase().trim(),omision_mod,status_mod)){
							
							folio_emp = 0; 	
							empleado = ""; 	
							fecha = ""; 	
							ent_sal = ""; 	
							tipo_checada = ""; 	
							imp = 0; 	
							fav  = 0; 	
							observacion  = "";
							Status_Mov = "";
							
							String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
							String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
							String Establecimiento = cmbEstablecimiento.getSelectedItem().toString();
							String Departamento = cmbDepartamento.getSelectedItem().toString();
							String folios_empleados = "Selecciona un Empleado";

							while(tabla.getRowCount()>0)
								modelo.removeRow(0);
							
							Object[][] recargarTabla = new BuscarTablasModel().filtro_impuntualidad_a_considerar(fecha_inicio,fecha_final,Establecimiento,Departamento,folios_empleados);
							
							 String[] fila = new String[17];
		                     for(int i=0; i<recargarTabla.length; i++){
		                             fila[0] = recargarTabla[i][0]+"";
		                             fila[1] = recargarTabla[i][1]+"";
		                             fila[2] = recargarTabla[i][2]+"";
		                             fila[3] = recargarTabla[i][3]+"";
		                             fila[4] = recargarTabla[i][4]+"";
		                             fila[5] = recargarTabla[i][5]+"";
		                             fila[6] = recargarTabla[i][6]+"";
		                             fila[7] = recargarTabla[i][7]+"";
		                             fila[8] = recargarTabla[i][8]+"";
		                             fila[9] = recargarTabla[i][9]+"";
		                             fila[10] = recargarTabla[i][10]+"";
		                             fila[11] = recargarTabla[i][11]+"";
		                             fila[12] = recargarTabla[i][12]+"";
		                             fila[13] = recargarTabla[i][13]+"";
		                             fila[14] = recargarTabla[i][14]+"";
		                             fila[15] = recargarTabla[i][15]+"";
		                             fila[16] = recargarTabla[i][16]+"";
		                             modelo.addRow(fila);
		                     }
		                     dispose();
							
							JOptionPane.showMessageDialog(null, "Consideracion Guardada Correctamente", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/aplicara-el-dialogo-icono-6256-32.png"));
							return;
						}else{
							dispose();
				  			JOptionPane.showMessageDialog(null,"No Se Pudo Guardar La Consideracion","Aviso", JOptionPane.ERROR_MESSAGE,new ImageIcon("Imagen/usuario-icono-eliminar5252-64.png"));
							return;
						}
				}else{
	  			  JOptionPane.showMessageDialog(null, "Es Necesario Teclear Una Observacion \n El Porque Se Modifico El Registro","Aviso", JOptionPane.ERROR_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
					return;
				}
			}
		};
	}
	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Consideraciones_De_Impuntualidad_En_Asistencia().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
