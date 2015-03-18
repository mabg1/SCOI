package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.toedter.calendar.JDateChooser;


import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.Connexion;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Reportes_De_Vouchers extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento_Empleados();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	

	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio del Corte", 800, "String");
	
	JButton btnReporte_porfecha_estab_Exportar_deun_estab = new JButton("",new ImageIcon("imagen/tarjeta-de-credito-visa-icono-8242-16.png"));
	JButton btnReporte_porfecha_estab_Exportar = new JButton("",new ImageIcon("imagen/hoja-de-calculo-excel-icono-5223-16.png"));
	JButton btnReporte_asignacion = new JButton("",new ImageIcon("imagen/hoja-de-calculo-excel-icono-5223-16.png"));
    JButton btnSeleccionAsignacion =new JButton("",new ImageIcon ("imagen/Filter-List-icon16.png"));
	
	JButton btngenerar = new JButton("Generar",new ImageIcon("imagen/buscar.png"));
	
	JDateChooser cfecha = new JDateChooser();
	
	int tipo_Reporte = 0;
	
	public Cat_Reportes_De_Vouchers(){
		setSize(320,380);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Reportes De Vouchers");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/tarjeta-de-credito-visa-icono-8242-32.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Seleccione el Tipo de Reporte"));
	
		btnReporte_porfecha_estab_Exportar_deun_estab.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Vouchers Por Establecimiento De Una Fecha</p></CENTER></FONT>" +
				"</html>");
		
		btnReporte_porfecha_estab_Exportar.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Vouchers Por Establecimiento De Una Fecha Para Exportar </p></CENTER></FONT>" +
				"</html>");	
		
		btnReporte_asignacion.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Vouchers Por Asignacion Para Exportar</p></CENTER></FONT>" +
				"</html>");	
		
		panel.add(btnReporte_porfecha_estab_Exportar_deun_estab).setBounds(10,25,295,40);
		panel.add(btnReporte_porfecha_estab_Exportar).setBounds(10,75,295,40);
		panel.add(btnReporte_asignacion).setBounds(10,125,295,40);
		
		panel.add(new JLabel("Folio De Asignacion:")).setBounds(10,180,200,20);		
		panel.add(txtFolio).setBounds(110,180,175,20);
		panel.add(btnSeleccionAsignacion).setBounds(285,180,20,20);
		panel.add(new JLabel("Fecha De Corte:")).setBounds(10,220,200,20);
		panel.add(cfecha).setBounds(110,220,195,20);
		panel.add(new JLabel("Establecimiento:")).setBounds(10,260,200,20);
		panel.add(cmbEstablecimiento).setBounds(110,260,195,20);
		
		panel.add(btngenerar).setBounds(100,300,120,30);
	    
	    txtFolio.setEditable(false);
	    cfecha.setEnabled(false);
	    btngenerar.setEnabled(false);
	    btnSeleccionAsignacion.setEnabled(false);
	    cmbEstablecimiento.setEnabled(false);
	    
		cont.add(panel);
		btngenerar.addActionListener(opGenerar);
		btnReporte_porfecha_estab_Exportar_deun_estab.addActionListener(opReporte_Por_Fecha);
		btnReporte_porfecha_estab_Exportar.addActionListener(opReporte_Por_Fecha_para_exportar);
		btnReporte_asignacion.addActionListener(opReporte_por_asignacion);
		btnSeleccionAsignacion.addActionListener(opfiltroLR);
		
		txtFolio.addKeyListener(op_buscar_enter);
		cfecha.addKeyListener(op_buscar_enter);
		
		
     	///filtro de Listas de Raya
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "buscarAsignacion");
          getRootPane().getActionMap().put("buscarLR", new AbstractAction(){
         public void actionPerformed(ActionEvent e)
         {                 	    btngenerar.doClick();
       	    }
     });
		
	}

	KeyListener op_buscar_enter = new KeyListener() {
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btngenerar.doClick();
			}
		}
		public void keyPressed(KeyEvent e) {}
	};
	
	
	
	ActionListener opReporte_Por_Fecha = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(false);
			cfecha.setEnabled(true);
			btngenerar.setEnabled(true);
			btnSeleccionAsignacion.setEnabled(false);
			tipo_Reporte=1;
			cmbEstablecimiento.setSelectedIndex(0);
			cmbEstablecimiento.setEnabled(true);
			cfecha.setDate(null);
			cfecha.requestFocus();
			
		}
	};
	
	ActionListener opReporte_Por_Fecha_para_exportar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(false);
			cfecha.setEnabled(true);
			btngenerar.setEnabled(true);
			btnSeleccionAsignacion.setEnabled(false);
			tipo_Reporte=2;
			cmbEstablecimiento.setEnabled(true);
			cfecha.setDate(null);
			cfecha.requestFocus();
		}
	};

	ActionListener opReporte_por_asignacion = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(true);
			cfecha.setEnabled(false);
			btngenerar.setEnabled(true);
			tipo_Reporte=3;
			btnSeleccionAsignacion.setEnabled(true);
			txtFolio.setText("");
			cfecha.setDate(null);
			txtFolio.requestFocus();
			cmbEstablecimiento.setEnabled(false);
		}
	};
	
	ActionListener opfiltroLR = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setText("");
			new Cat_Filtro_De_Asignaciones().setVisible(true);
		}
	};
	
	
	public String validar_fechas(){
		String error = "";
		@SuppressWarnings("unused")
		String fechafinalNull = cfecha.getDate()+"";
	    if(cfecha.equals("null"))error+= "Fecha\n";
		return error;
	}
	
	
	
	ActionListener opGenerar = new ActionListener() {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e) {
			 String fechaNull = cfecha.getDate()+"";
			if(tipo_Reporte==1){
				
				         if(fechaNull.equals("null")){
								JOptionPane.showMessageDialog(null,"Necesita Selecionar una Fecha o la Fecha tecleada es Incorrecta","Mensaje",JOptionPane.WARNING_MESSAGE);
								return;
				         }
				         else{  
							
							String fecha = new SimpleDateFormat("dd/MM/yyyy").format(cfecha.getDate());		
							String query = "exec sp_Reporte_De_Vouchers_Por_Establecimiento_A_Una_Fecha_Determinada '"+fecha+"','"+cmbEstablecimiento.getSelectedItem().toString()+"','mabg'";
							System.out.println(query);
							Statement stmt = null;
								try {
									stmt =  new Connexion().conexion().createStatement();
								    ResultSet rs = stmt.executeQuery(query);
									JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Vouchers_Por_Establecimiento_De_Una_Fecha.jrxml");
									JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
									JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
									JasperViewer.viewReport(print, false);
									
								} catch (Exception e2) {
									System.out.println(e2.getMessage());
									JOptionPane.showMessageDialog(null, "Error en Generar Reporte de Vouchers Funcion opGenerar tipo_Reporte=1 \n en el Procedimiento sp_Reporte_De_Vouchers_Por_Establecimiento_A_Una_Fecha_Determinada SQLException: \n "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
								}
								return;	
				         }
			}if(tipo_Reporte==2){
				 if(fechaNull.equals("null")){
						JOptionPane.showMessageDialog(null,"Necesita Selecionar una Fecha o la Fecha tecleada es Incorrecta","Mensaje",JOptionPane.WARNING_MESSAGE);
						return;
		         }
		         else{  
					
					String fecha = new SimpleDateFormat("dd/MM/yyyy").format(cfecha.getDate());		
					String query = "exec sp_Reporte_De_Vouchers_Por_Establecimiento_A_Una_Fecha_Determinada '"+fecha+"','"+cmbEstablecimiento.getSelectedItem().toString()+"','mabg'";
					System.out.println(query);
					Statement stmt = null;
						try {
							stmt =  new Connexion().conexion().createStatement();
						    ResultSet rs = stmt.executeQuery(query);
							JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Vouchers_Por_Establecimiento_De_Una_Fecha_Para_Exportar.jrxml");
							JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
							JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
							JasperViewer.viewReport(print, false);
							
						} catch (Exception e2) {
							System.out.println(e2.getMessage());
							JOptionPane.showMessageDialog(null, "Error en Generar Reporte de Vouchers Funcion opGenerar tipo_Reporte=2 \n en el Procedimiento sp_Reporte_De_Vouchers_Por_Establecimiento_A_Una_Fecha_Determinada SQLException: \n "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}
						return;	
		         }
			}if(tipo_Reporte==3){ 
				if(txtFolio.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Necesitas Teclear Una Asignacion","Mensaje",JOptionPane.WARNING_MESSAGE);				
					
				}else{
			
			String query = "exec sp_Reporte_De_Vouchers_Por_Establecimiento_A_Una_Fecha_Determinada '01/01/1900','"+cmbEstablecimiento.getSelectedItem().toString()+"','"+txtFolio.getText().toString().toUpperCase().trim()+"'";
			System.out.println(query);
			Statement stmt = null;
				try {
					stmt =  new Connexion().conexion().createStatement();
				    ResultSet rs = stmt.executeQuery(query);
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Vouchers_Por_Establecimiento_De_Una_Fecha_Para_Exportar.jrxml");
					JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
					JasperViewer.viewReport(print, false);
					
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Generar Reporte de Vouchers Funcion opGenerar tipo_Reporte=3 \n en el Procedimiento sp_Reporte_De_Vouchers_Por_Establecimiento_A_Una_Fecha_Determinada SQLException: \n "+e2.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
				return;	
         }
			}	
			}
		
	};

	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_De_Vouchers().setVisible(true);
		}catch(Exception e){	}
	}
	
	
	
	@SuppressWarnings({ "unchecked" })
	public class Cat_Filtro_De_Asignaciones extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		Connexion con = new Connexion();
		
		public Object[][] get_tabla(){
			return new BuscarTablasModel().tabla_model_asignaciones();
		}
		
	    public DefaultTableModel tabla_model = new DefaultTableModel(
	    		get_tabla(),	new String[]{	"Asignacion",	"Folio de corte", "cajera(o)", "Establecimiento", "*"}){
	                    @SuppressWarnings("rawtypes")
	                    Class[] types = new Class[]{
	                               java.lang.Object.class, 
	                               java.lang.Object.class, 
	                               java.lang.Object.class,  
	                               java.lang.Object.class,  
	                               java.lang.Boolean.class
	                };
	                    @SuppressWarnings({ "rawtypes" })
	                    public Class getColumnClass(int columnIndex) {
	                            return types[columnIndex];
	                    }
	                public boolean isCellEditable(int fila, int columna){
	                            switch(columna){
	                                    case 0  : return false; 
	                                    case 1  : return false; 
	                                    case 2  : return false; 
	                                    case 3  : return false; 
	                                    case 4  : return true; 
	                            }
	                             return false;
	                     }
	            };
		
		JTable tabla = new JTable(tabla_model);
		JScrollPane panelScroll = new JScrollPane(tabla);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolioAsignacion = new JTextField();
		JTextField txtFolioCorte = new JTextField();
		JTextField txtNombre = new JTextField();
		String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
		
		JButton btnCargar = new JButton("Cargar");
		
		@SuppressWarnings("rawtypes")
		JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
		
		@SuppressWarnings({ "rawtypes" })
		public Cat_Filtro_De_Asignaciones()	{
			this.setModal(true);
//			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
			this.setTitle("Filtro de asignaciones por empleado");
			panel.setBorder(BorderFactory.createTitledBorder("Asignaciones de empleados"));

			this.init_tabla();
			
			trsfiltro = new TableRowSorter(tabla_model); 
			tabla.setRowSorter(trsfiltro);  
			
			panel.add(panelScroll).setBounds(15,42,720,327);
			
			panel.add(txtFolioAsignacion).setBounds(15,20,80,20);
			panel.add(txtFolioCorte).setBounds(96,20,79,20);
			panel.add(txtNombre).setBounds(176,20,320,20);
			panel.add(cmbEstablecimientos).setBounds(497,20, 170, 20);
			panel.add(btnCargar).setBounds(668,20, 65, 20);
			
//			agregar(tabla);
			
			cont.add(panel);
			
			txtFolioAsignacion.addKeyListener(opFiltroFolio);
			txtFolioCorte.addKeyListener(opFiltroFolioCorte);
			txtNombre.addKeyListener(opFiltroNombre);
			
			cmbEstablecimientos.addActionListener(opFiltro);
			
			btnCargar.addActionListener(opCadena);
			
			this.setSize(755,415);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		}
		
		ActionListener opCadena = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cadena = "";
				for(int i = 0; i<tabla.getRowCount(); i++){
					
					if(tabla.getValueAt(i, 4).toString().equals("true")){
						cadena+=tabla.getValueAt(i, 0).toString().trim()+"'',''";
					
					}
				}
				txtFolio.setText(cadena.substring(0,cadena.length()-5));
				dispose();
			}
		};
		
		KeyListener opFiltroFolio = new KeyListener(){
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioAsignacion.getText().toUpperCase().trim(), 0));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}
			
		};
		
		KeyListener opFiltroFolioCorte = new KeyListener(){
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioCorte.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}
			
		};
		
		KeyListener opFiltroNombre = new KeyListener(){
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre.getText().toUpperCase().trim(), 2));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}
			
		};
		
		ActionListener opFiltro = new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(cmbEstablecimientos.getSelectedIndex() != 0){
					trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 3));
				}else{
					trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
				}
			}
		};
		
		public void init_tabla(){
	        this.tabla.getTableHeader().setReorderingAllowed(false) ;
	        
	        	   int w=100;
	               int x=80;
	               int y=320;
	               int z=50;
	               
	                this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	                
	                this.tabla.getColumnModel().getColumn(0).setMaxWidth(x);
	                this.tabla.getColumnModel().getColumn(0).setMinWidth(x);
	                this.tabla.getColumnModel().getColumn(1).setMaxWidth(x);
	                this.tabla.getColumnModel().getColumn(1).setMinWidth(x);
	                this.tabla.getColumnModel().getColumn(2).setMaxWidth(y);
	                this.tabla.getColumnModel().getColumn(2).setMinWidth(y);
	                this.tabla.getColumnModel().getColumn(3).setMaxWidth(w+70);
	                this.tabla.getColumnModel().getColumn(3).setMinWidth(w+70);
	                this.tabla.getColumnModel().getColumn(4).setMaxWidth(z);
	                this.tabla.getColumnModel().getColumn(4).setMinWidth(z);
	                
	                this.tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer( "texto" ,"izquierda",	"arial", "normal",12));
	                this.tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer( "texto" ,"izquierda",	"arial", "normal",12));
	                this.tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer( "texto" ,"izquierda",	"arial", "normal",12));
	                this.tabla.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer( "texto" ,"centro",	"arial", "normal",12));
	                this.tabla.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer( "CHB" ,"centro",	"arial", "normal",12));
	}
		
		KeyListener validaCantidad = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();				
				if(((caracter < '0') ||	
				    	(caracter > '9')) && 
				    	(caracter != '.' )){
				    	e.consume();
				    	}
			}
			@Override
			public void keyReleased(KeyEvent e) {	
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}	
		};
		
		KeyListener validaNumericoConPunto = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				if(((caracter < '0') ||	
			    	(caracter > '9')) && 
			    	(caracter != '.')){
			    	e.consume();
			    	}
			    		    		       	
			}
			@Override
			public void keyPressed(KeyEvent e){}
			@Override
			public void keyReleased(KeyEvent e){}
									
		};
	}



}
