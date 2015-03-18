package Cat_Contabilidad;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.Connexion;
import Obj_Renders.tablaRenderer;

	@SuppressWarnings("serial")
	public class Cat_Generar_xml_pdf extends JFrame{
		
		Container contenedor = getContentPane();
		JLayeredPane panelxml = new JLayeredPane();
		
		
		JTextField txtFolioConsultar = new JTextField();
		JButton btnGenerar = new JButton("DESCARGAR",new ImageIcon("imagen/Aplicar.png"));
		
		JButton btnFiltro = new JButton("F");
		
		JRadioButton rb1 = new JRadioButton("Calendario");
		JRadioButton rb2 = new JRadioButton("Dinamico");
		
		ButtonGroup grupo = new ButtonGroup();
		
//		--------------------------------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings("rawtypes")
		Vector miVectorAnios = new Vector();
		@SuppressWarnings("unchecked")
		public String[] listaAnios(){
			
				int anio;
				try {
						anio = new BuscarSQL().AnioActual();
						
						for(int i = 2012; i <= anio; i++){
							miVectorAnios.add(i+"");
						}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			String[] lista = new String[miVectorAnios.size()];
			
			int indice =0;
			while(indice < miVectorAnios.size()){
				lista[indice]= miVectorAnios.get(indice).toString();
				indice++; 
			}
			return lista;
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox cmbAnio = new JComboBox(listaAnios());
		
//		--------------------------------------------------------------------------------------------------------------------------------------------------
		String[] listaMonth = {"TODOS","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox cmbMes = new JComboBox(listaMonth);
//		--------------------------------------------------------------------------------------------------------------------------------------------------
		@SuppressWarnings("rawtypes")
		Vector miVectorDias = new Vector();
//		--------------------------------------------------------------------------------------------------------------------------------------------------
		JDateChooser calendario = new JDateChooser();
		
		Border blackline, etched, raisedbevel, loweredbevel, empty;
		Calendar fecha = new GregorianCalendar();
		
		DefaultTableModel modeloBase = new DefaultTableModel(null,
	            new String[]{"Fol. Factura","Cod. Proveedor", "Proveedor","Ruta"}
				){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
		    	java.lang.String.class,
		    	java.lang.String.class,
		    	java.lang.String.class,
		    	java.lang.String.class
	                                    };
		     @SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
	             return types[columnIndex];
	         }
	         public boolean isCellEditable(int fila, int columna){
	        	 switch(columna){
	        	 	case 0 : return false; 
	        	 	case 1 : return false; 
	        	 	case 2 : return false;
	        	 	case 3 : return false;
	        	 } 				
	 			return false;
	 		}
		};
		
	    JTable tablaBase = new JTable(modeloBase);
	    JScrollPane scrollAsignadoBase = new JScrollPane(tablaBase);
	    
		public Cat_Generar_xml_pdf(){
			this.setTitle("Generar Facturas y XML De Proveedores");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/control_facturas_y_xml.png"));
			blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
			panelxml.setBorder(BorderFactory.createTitledBorder(blackline,"Guardar Facturas y XML De Proveedores"));

			this.calendario.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
			
			grupo.add(rb1);
			grupo.add(rb2);
			
			cmbAnio.setSelectedItem(fecha.get(Calendar.YEAR)+"");
			
			cmbAnio.addActionListener(opFechaDinamica);
			cmbMes.addActionListener(opFechaDinamica);
			
			
			panelxml.add(rb1).setBounds(110,20,90,20); 
			panelxml.add(rb2).setBounds(200,20,90,20); 
			
			panelxml.add(new JLabel("Fecha:")).setBounds(20,50,80,20);
			panelxml.add(calendario).setBounds(70,50,100,20);
			
			panelxml.add(new JLabel("Año:")).setBounds(190,50,40,20); 
			panelxml.add(cmbAnio).setBounds(230,50,150,20);
			panelxml.add(new JLabel("Mes:")).setBounds(190,80,40,20);
			panelxml.add(cmbMes).setBounds(230,80,150,20);
			
			panelxml.add(new JLabel("Fol. Factura:")).setBounds(160,110,70,20);
			panelxml.add(txtFolioConsultar).setBounds(230,110,120,20);
//			btnFiltro
			panelxml.add(btnFiltro).setBounds(350,110,30,20);
			panelxml.add(btnGenerar).setBounds(230,140,150,20);
			
			panelxml.add(getPanelTabla()).setBounds(20,190,995,140);
			
	        this.contenedor.add(panelxml);
	        
	        calendario.setEnabled(false);
			cmbAnio.setEnabled(false);
			cmbMes.setEnabled(false);
	        
			rb1.setSelected(true);
			filtrado();
			
	        rb1.addActionListener(opTipoDeFiltrado);
	        rb2.addActionListener(opTipoDeFiltrado);
	        
	        btnGenerar.addActionListener(opConsulta);
	        btnFiltro.addActionListener(opFiltro);
	        
			this.setSize(1040,380);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			llamar_render();
		}
		
		public void llamar_render(){
			//		tipo de valor = imagen,chb,texto
//			tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
	    
			tablaBase.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("numerico","derecha","Arial","negrita",12)); 
	    	
			tablaBase.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("numerico","derecha","Arial","negrita",12)); 
			
			tablaBase.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12));
			
			tablaBase.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
			
		}
		
		private JScrollPane getPanelTabla()	{		
			
			this.tablaBase.getTableHeader().setReorderingAllowed(false) ;
			this.tablaBase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			tablaBase.getColumnModel().getColumn(0).setMaxWidth(80);
			tablaBase.getColumnModel().getColumn(0).setMinWidth(80);
			tablaBase.getColumnModel().getColumn(1).setMaxWidth(100);
			tablaBase.getColumnModel().getColumn(1).setMinWidth(100);
			tablaBase.getColumnModel().getColumn(2).setMaxWidth(440);
			tablaBase.getColumnModel().getColumn(2).setMinWidth(440);
			tablaBase.getColumnModel().getColumn(3).setMaxWidth(380);
			tablaBase.getColumnModel().getColumn(3).setMinWidth(380);
			
		    JScrollPane scrolBase = new JScrollPane(tablaBase);
		    return scrolBase; 
		}
		
		ActionListener opTipoDeFiltrado = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		
				filtrado();
			}
		};
				public void filtrado(){
					if(rb1.isSelected()){
										calendario.setEnabled(true);
										cmbAnio.setEnabled(false);
										cmbMes.setEnabled(false);
										
										if(cmbMes.getSelectedIndex()>0){
											cmbMes.setSelectedIndex(0);
										}
//										
								}else{
										calendario.setEnabled(false);
										cmbAnio.setEnabled(true);
										cmbMes.setEnabled(true);
										
										calendario.setDate(null);
								}
				}
		
				
		String fecha_compuesta = ((fecha.get(Calendar.DAY_OF_MONTH))+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+(fecha.get(Calendar.YEAR)));
		ActionListener opFechaDinamica = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		
					fecha_compuesta =  "01"  +  "/"  +  ((cmbMes.getSelectedIndex())<10 ? ("0"+(cmbMes.getSelectedIndex())) :(cmbMes.getSelectedIndex()))  +  "/"+  cmbAnio.getSelectedItem();
						
			}
		};

		ActionListener opConsulta = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				String fecha = calendario.getDate()+"";
				
				if(rb1.isSelected()){
					
						if(fecha.equals("null")/*si fecha es null*/){
							
									if(txtFolioConsultar.getText().equals("")){
											JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha del calendario \no ingresar un folio de factura", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
											return;
									}else{
											try {
												new BuscarSQL().buscar_xml_pdf(1,"01/01/1900",txtFolioConsultar.getText());
											} catch (IOException | SQLException e1) {
												e1.printStackTrace();
											}
									}
									
						}else{
									try {
										new BuscarSQL().buscar_xml_pdf(1,new SimpleDateFormat("dd/MM/yyyy").format(calendario.getDate()),txtFolioConsultar.getText());
									} catch (IOException | SQLException e1) {
										e1.printStackTrace();
									}
						}
				}
				
				if(rb2.isSelected()){
							try {
								new BuscarSQL().buscar_xml_pdf(2,"01"  +  "/"  +  ((cmbMes.getSelectedIndex())<10 ? ("0"+(cmbMes.getSelectedIndex())) :(cmbMes.getSelectedIndex()))  +  "/"+  cmbAnio.getSelectedItem(),txtFolioConsultar.getText());
							} catch (SQLException | IOException e1) {
								e1.printStackTrace();
							}
				}
			}
		};
		
		ActionListener opFiltro = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Cat_Filtro_xml_pdf().setVisible(true);
			}
		};
		
		
		public class Cat_Filtro_xml_pdf extends JDialog{
			
			Container cont = getContentPane();
			JLayeredPane panel = new JLayeredPane();
			
			Connexion con = new Connexion();
			
			DefaultTableModel modelo = new DefaultTableModel(null,
		            new String[]{"Fol. Factura","Cod. Proveedor", "Proveedor","fecha","XML","PDF",""}
					){
			     @SuppressWarnings("rawtypes")
				Class[] types = new Class[]{
			    	java.lang.String.class,
			    	java.lang.String.class,
			    	java.lang.String.class,
			    	java.lang.String.class,
			    	javax.swing.ImageIcon.class,
			    	javax.swing.ImageIcon.class,
			    	java.lang.Boolean.class
		                                    };
			     @SuppressWarnings({ "rawtypes", "unchecked" })
				public Class getColumnClass(int columnIndex) {
		             return types[columnIndex];
		         }
		         public boolean isCellEditable(int fila, int columna){
		        	 switch(columna){
		        	 	case 0 : return false; 
		        	 	case 1 : return false; 
		        	 	case 2 : return false;
		        	 	case 3 : return false;
		        	 	case 4 : return false;
		        	 	case 5 : return false;
		        	 	case 6 : return true;
		        	 } 				
		 			return false;
		 		}
			};
			
		    JTable tabla = new JTable(modelo);
		    JScrollPane scrollAsignado = new JScrollPane(tabla);
		    
		    JTextField txtFolioFacturaFiltro = new JTextField();
			JTextField txtCodigoProveedorFiltro = new JTextField();
			JTextField txtProveedorFiltro = new JTextField();
			
			JButton btnCargar = new JButton("Cargar");
			@SuppressWarnings("rawtypes")
			private TableRowSorter trsfiltro;
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Cat_Filtro_xml_pdf(){
				this.setModal(true);
				this.setTitle("Lista De Facturas y XML De Proveedores");
				this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/control_facturas_y_xml.png"));
				blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
				panel.setBorder(BorderFactory.createTitledBorder(blackline,"Lista De Facturas y XML De Proveedores"));
				
				trsfiltro = new TableRowSorter(modelo); 
				tabla.setRowSorter(trsfiltro);
				
				panel.add(txtFolioFacturaFiltro).setBounds(20,33,130,20);
				panel.add(txtCodigoProveedorFiltro).setBounds(130,33,70,20);
				panel.add(txtProveedorFiltro).setBounds(200,33,460,20);
				
				panel.add(btnCargar).setBounds(730,33,80,20);
				
				panel.add(getPanelTabla()).setBounds(20,55,787 ,570);
				
				txtFolioFacturaFiltro.addKeyListener(opFiltroFolioFactura);
				txtCodigoProveedorFiltro.addKeyListener(opFiltroCodProv);
				txtProveedorFiltro.addKeyListener(opFiltroProv);
				
				btnCargar.addActionListener(opDescargarLista);
				
		        this.cont.add(panel);
				this.setSize(832,678);
				this.setResizable(false);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
				llamar_render();
			}
			
			public void llamar_render(){
				//		tipo de valor = imagen,chb,texto
//				tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
		    
				tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("numerico","derecha","Arial","negrita",12)); 
		    	
				tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("numerico","derecha","Arial","negrita",12)); 
				
				tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12));
				
				tabla.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("fecha","centro","Arial","normal",12));
				
				tabla.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer("imagen","","","",0));
				tabla.getColumnModel().getColumn(5).setCellRenderer(new tablaRenderer("imagen","","","",0));
				tabla.getColumnModel().getColumn(6).setCellRenderer(new tablaRenderer("chb","","","",0));
			}
			
			private JScrollPane getPanelTabla()	{		
				
				this.tabla.getTableHeader().setReorderingAllowed(false) ;
				this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
				tabla.getColumnModel().getColumn(0).setMaxWidth(110);
				tabla.getColumnModel().getColumn(0).setMinWidth(110);
				tabla.getColumnModel().getColumn(1).setMaxWidth(70);
				tabla.getColumnModel().getColumn(1).setMinWidth(70);
				tabla.getColumnModel().getColumn(2).setMaxWidth(460);
				tabla.getColumnModel().getColumn(2).setMinWidth(460);
				tabla.getColumnModel().getColumn(3).setMaxWidth(70);
				tabla.getColumnModel().getColumn(3).setMinWidth(70);
				
				tabla.getColumnModel().getColumn(4).setMaxWidth(30);
				tabla.getColumnModel().getColumn(4).setMinWidth(30);
				
				tabla.getColumnModel().getColumn(5).setMaxWidth(30);
				tabla.getColumnModel().getColumn(5).setMinWidth(30);
				
				tabla.getColumnModel().getColumn(6).setMaxWidth(17);
				tabla.getColumnModel().getColumn(6).setMinWidth(17);
			

				Statement s;
				ResultSet rs;
				try {
					s = con.conexion().createStatement();
					
					rs = s.executeQuery("SELECT  folio_factura" +
												" ,cod_prv" +
												" ,proveedor" +
												" ,convert (varchar(20),[fecha_factura],103)as fecha_factura" +
											    " ,case when (xml)is null" +
											    "		then 0" +
											    "		else 1" +
											    "	end as xml" +
											    " ,case when (pdf)is null " +
											    "		then 0 " +
											    "		else 1 " +
											    "	end as pdf " +
											    " ,Status " +
											    " FROM tb_control_de_facturas_y_xml " +
											    " where (len(xml)>0 or len(pdf)>0)" +
											    " order by fecha_factura desc");
				
					while (rs.next())
					{ 
					   Object [] fila = new Object[7];
					   fila[0] = rs.getString(1).trim();
					   fila[1] = rs.getString(2).trim();
					   fila[2] = rs.getString(3).trim(); 
					   fila[3] = rs.getString(4).trim(); 
					   fila[4] = rs.getString(5).trim(); 
					   fila[5] = rs.getString(6).trim(); 
					   fila[6] = "false";
					   modelo.addRow(fila); 
					}	
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error en Cat_Control_De_Facturas_Y_XML_De_Proveedores en la funcion getPanelTabla  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
				 JScrollPane scrol = new JScrollPane(tabla);
			    return scrol; 
			}
			
			KeyListener opFiltroFolioFactura = new KeyListener(){
					@SuppressWarnings("unchecked")
					public void keyReleased(KeyEvent arg0) {
						trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioFacturaFiltro.getText().toUpperCase(), 0));
					}
					public void keyTyped(KeyEvent arg0) {}
					public void keyPressed(KeyEvent arg0) {}	
			    };
			
			KeyListener opFiltroCodProv = new KeyListener(){
					@SuppressWarnings("unchecked")
					public void keyReleased(KeyEvent arg0) {
						trsfiltro.setRowFilter(RowFilter.regexFilter(txtCodigoProveedorFiltro.getText().toUpperCase().trim(), 1));
					}
					public void keyTyped(KeyEvent arg0) {}
					public void keyPressed(KeyEvent arg0) {}		
			    };
			    
			KeyListener opFiltroProv = new KeyListener(){
					@SuppressWarnings("unchecked")
					public void keyReleased(KeyEvent arg0) {
						trsfiltro.setRowFilter(RowFilter.regexFilter(txtProveedorFiltro.getText().toUpperCase().trim(), 2));
					}
					public void keyTyped(KeyEvent arg0) {}
					public void keyPressed(KeyEvent arg0) {}		
			    };
			    
			ActionListener opDescargarLista = new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					
					String[] vector = new String[4];
					
					int bandera =0;
					for(int i=0; i<tabla.getRowCount(); i++){
						
						if(tabla.getValueAt(i, 6).equals(true)){
							
							vector[0] = tabla.getValueAt(i, 0).toString();
							vector[1] = tabla.getValueAt(i, 1).toString();
							vector[2] = tabla.getValueAt(i, 2).toString();
							
							String  ruta = "";
							try {
								Date date = new SimpleDateFormat("dd/MM/yyyy").parse(tabla.getValueAt(i, 3).toString());
								ruta = "C:/concentrado_xml_pdf/"+date.getYear()+"/"+(date.getMonth()+1)+"/"+date.getDay()+"/"+tabla.getValueAt(i, 1).toString();
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							
							vector[3] = ruta;
							
							modeloBase.addRow(vector);
							
							try {
								new BuscarSQL().buscar_xml_pdf(1,"01/01/1900",tabla.getValueAt(i, 0).toString());
							} catch (IOException | SQLException e1) {
								e1.printStackTrace();
							}
								
							bandera++;
						}
					}
					
					if(bandera>0){
						dispose();
						JOptionPane.showMessageDialog(null, "Los archivos se descargaron correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						JOptionPane.showMessageDialog(null, "Seleccionar una factura", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
				}
			};
		}
		

		public static void main(String args[]){
			try{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new Cat_Generar_xml_pdf().setVisible(true);
			}catch(Exception e){	}
		}
}