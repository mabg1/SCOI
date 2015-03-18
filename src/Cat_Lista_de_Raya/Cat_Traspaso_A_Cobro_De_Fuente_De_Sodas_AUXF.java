package Cat_Lista_de_Raya;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.Connexion;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Traspaso_A_Cobro_De_Fuente_De_Sodas_AUXF extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	public static Object[][] get_tabla(){
		return new BuscarTablasModel().tabla_model_empleados_conpendiente_en_fuente_de_sodas_auxf();
	}
	
    public static DefaultTableModel tabla_model = new DefaultTableModel(
    		get_tabla(),	new String[]{	"Folio",	"Nombre Completo", "Establecimiento", "Puesto", "Saldo"}){
                    @SuppressWarnings("rawtypes")
                    Class[] types = new Class[]{
                               java.lang.Object.class, 
                               java.lang.Object.class, 
                               java.lang.Object.class,  
                               java.lang.Object.class,  
                               java.lang.Object.class
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
                                    case 4  : return false; 
                            }
                             return false;
                     }
            };
//	DefaultTableModel model = new DefaultTableModel(0,5){
//		public boolean isCellEditable(int fila, int columna){
//			if(columna < 0)
//				return true;
//			return false;
//		}
//	};
	
	JTable tabla = new JTable(tabla_model);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
	String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
	@SuppressWarnings("rawtypes")
	JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
	
	JButton btnPeriodo = new JButton("Avanzar",new ImageIcon("imagen/adelante.png"));
	JButton btnRecorrer = new JButton("Recorrer",new ImageIcon("imagen/atras.png"));
	JTextField txtPeriodo = new Componentes().text(new JTextField(), "Periodo", 2, "Int");
	
	JButton btnReporteGeneral = new JButton("Reporte General");
	JButton btnReporte = new JButton("Reporte");
	JTextField txtReporte_Periodo = new Componentes().text(new JTextField(), "Folio de periodo", 2, "Int");
	
	@SuppressWarnings({ "rawtypes" })
	public Cat_Traspaso_A_Cobro_De_Fuente_De_Sodas_AUXF()	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
		this.setTitle("Filtro de empleados con Saldo en fuente de sodas (Auxiliar y Finanzas)");
		panel.setBorder(BorderFactory.createTitledBorder("Empleados consaldo en fuente de sodas"));

		this.init_tabla();
		
		trsfiltro = new TableRowSorter(tabla_model); 
		tabla.setRowSorter(trsfiltro);  
		
		panel.add(panelScroll).setBounds(15,42,800,327);
		
		panel.add(txtFolio).setBounds(15,20,68,20);
		panel.add(txtNombre_Completo).setBounds(85,20,300,20);
		panel.add(cmbEstablecimientos).setBounds(387,20, 180, 20);
		
		panel.add(btnPeriodo).setBounds(572, 20, 100, 20);
		panel.add(txtPeriodo).setBounds(672, 20, 35, 20);
		panel.add(btnRecorrer).setBounds(707, 20, 110, 20);
		
		panel.add(btnReporteGeneral).setBounds(530, 375, 140, 20);
		panel.add(txtReporte_Periodo).setBounds(685, 375, 40, 20);
		panel.add(btnReporte).setBounds(725, 375, 90, 20);
		
		agregar(tabla);
		
		cont.add(panel);
		
		txtFolio.addKeyListener(opFiltroFolio);
		txtNombre_Completo.addKeyListener(opFiltroNombre);
		cmbEstablecimientos.addActionListener(opFiltro);
		
		btnPeriodo.addActionListener(opPeriodo);
		btnRecorrer.addActionListener(opPeriodo);
		
		btnReporte.addActionListener(opReporte);
		btnReporteGeneral.addActionListener(opReporte);
		
		//  abre el filtro de busqueda de periodos al presionar la tecla f2
	    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	       KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "foco");
	    
	    getRootPane().getActionMap().put("foco", new AbstractAction(){
	        @Override
	        public void actionPerformed(ActionEvent e)
	        {
	        	new Cat_Filtro_De_Periodos().setVisible(true);
	        }
	    });
		
		txtPeriodo.setEditable(false);
		txtPeriodo.setHorizontalAlignment(0);
		txtPeriodo.setText(seleccionarPeriodo()+"");
		
		this.setSize(850,445);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public int seleccionarPeriodo(){
		int periodo = 0;
		try {
			periodo = new BuscarSQL().folio_periodo();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return periodo;
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	    			
	        		int fila = tabla.getSelectedRow();
	    			int folio =  Integer.parseInt(tabla.getValueAt(fila, 0)+"");
	    			Object empleado =  tabla.getValueAt(fila, 1);
	    			
	    			new Cat_Filtro_Ticket_Fuente_Sodas_AUXF(folio,empleado+"",Integer.valueOf(txtPeriodo.getText())).setVisible(true);
	        	}
	        }
        });
    }
	
	ActionListener opPeriodo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			try {
				int periodo = new BuscarSQL().periodos();
				
				if(Double.valueOf(txtPeriodo.getText())==periodo && e.getActionCommand().equals("Avanzar")){
					JOptionPane.showMessageDialog(null, "El limite de periodos es ("+periodo+")", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					
					if(Double.valueOf(txtPeriodo.getText())==1 && e.getActionCommand().equals("Recorrer")){
						JOptionPane.showMessageDialog(null, "El limite de periodos es (1)", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}else{
						actualizarPeriodo(e.getActionCommand(),0);
					}
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	};
	
	public void actualizarPeriodo(String accion, int valor){
		
		if(new ActualizarSQL().actualizar_folio_periodo_fs(accion,valor)){
			txtPeriodo.setText(seleccionarPeriodo()+"");
		}else{
			JOptionPane.showMessageDialog(null, "No se pudo generar el periodo", "Aviso", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	
	ActionListener opReporte = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(e.getActionCommand().equals("Reporte")){
				
					if(txtReporte_Periodo.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Ingrese Un Periodo Para Generar Reporte", "Aviso", JOptionPane.ERROR_MESSAGE);
						return;
					}else{
						if(Integer.valueOf(txtReporte_Periodo.getText())<1 || Integer.valueOf(txtReporte_Periodo.getText())>20){
							JOptionPane.showMessageDialog(null, "Periodo Fuera De Rango", "Aviso", JOptionPane.ERROR_MESSAGE);
							return;
						}else{
							Cat_Reporte_De_Periodo(Integer.valueOf(txtReporte_Periodo.getText()));
						}
					}
					
			}else{
				Cat_Reporte_De_Periodo(0);
			}
			
		}
	};
	
	@SuppressWarnings("rawtypes")
	public void Cat_Reporte_De_Periodo(int periodo) {
		
		try {
			JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Periodos_Fuente_De_Sodas.jrxml");
			
			Map parametro = new HashMap();
			parametro.put("periodo", periodo);
			
			JasperPrint print = JasperFillManager.fillReport(report, parametro, new Connexion().conexion());
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error En Cat_Reporte_De_Periodo ", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
		}
		
	}

	KeyListener opFiltroFolio = new KeyListener(){
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
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
	
	KeyListener opFiltroNombre = new KeyListener(){
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	
	ActionListener opFiltro = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
	public void init_tabla(){
        this.tabla.getTableHeader().setReorderingAllowed(false) ;
        
        	   int w=60;
               int x=330;
               int y=160;
               int z=80;
               
                this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                
                this.tabla.getColumnModel().getColumn(0).setMaxWidth(w);
                this.tabla.getColumnModel().getColumn(0).setMinWidth(w);
                this.tabla.getColumnModel().getColumn(1).setMaxWidth(x);
                this.tabla.getColumnModel().getColumn(1).setMinWidth(x);
                this.tabla.getColumnModel().getColumn(2).setMaxWidth(y);
                this.tabla.getColumnModel().getColumn(2).setMinWidth(y);
                this.tabla.getColumnModel().getColumn(3).setMaxWidth(y);
                this.tabla.getColumnModel().getColumn(3).setMinWidth(y);
                this.tabla.getColumnModel().getColumn(4).setMaxWidth(z);
                this.tabla.getColumnModel().getColumn(4).setMinWidth(z);
                
        TableCellRenderer render = new TableCellRenderer() { 
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                boolean hasFocus, int row, int column) { 
                        
                        Component componente = null;
                
                        switch(column){
                                case 0: 
                                        componente = new JLabel(value == null? "": value.toString());
                                        if(row%2==0){
                                                ((JComponent) componente).setOpaque(true); 
                                                componente.setBackground(new java.awt.Color(177,177,177));
                                        }
                                        ((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
                                        break;
                                case 1:
                                        componente = new JLabel(value == null? "": value.toString());
                                        if(row%2==0){
                                                ((JComponent) componente).setOpaque(true); 
                                                componente.setBackground(new java.awt.Color(177,177,177));        
                                        }
                                        ((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
                                        break;
                                case 2: 
                                        componente = new JLabel(value == null? "": value.toString());
                                        if(row%2==0){
                                                ((JComponent) componente).setOpaque(true); 
                                                componente.setBackground(new java.awt.Color(177,177,177));        
                                        }
                                        ((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
                                        break;
                                case 3: 
                                        componente = new JLabel(value == null? "": value.toString());
                                        if(row%2==0){
                                                ((JComponent) componente).setOpaque(true); 
                                                componente.setBackground(new java.awt.Color(177,177,177));        
                                        }
                                        ((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
                                        break;
                                case 4: 
                                        componente = new JLabel(value == null? "": value.toString());
                                        if(row%2==0){
                                                ((JComponent) componente).setOpaque(true); 
                                                componente.setBackground(new java.awt.Color(177,177,177));        
                                        }
                                        ((JLabel) componente).setHorizontalAlignment(SwingConstants.CENTER);
                                        break;
                        }
                        return componente;
                } 
        }; 
        for(int i=0; i<tabla.getColumnCount(); i++){
                this.tabla.getColumnModel().getColumn(i).setCellRenderer(render); 
        }
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
	
	public static void main(String [] args){
		try{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		
		Cat_Traspaso_A_Cobro_De_Fuente_De_Sodas_AUXF thisClass = new Cat_Traspaso_A_Cobro_De_Fuente_De_Sodas_AUXF();
		thisClass.setVisible(true);

		//utilizacion del AWTUtilities con el metodo opaque
		try {
			   @SuppressWarnings("rawtypes")
			Class clazz =  Class.forName("com.sun.awt.AWTUtilities");
			   Method method = clazz.getMethod("setWindowOpaque", java.awt.Window.class, Boolean.TYPE);
			   method.invoke(clazz,thisClass , false);
			   } catch (Exception e) 
			   { }	
	}
	
public class Cat_Filtro_De_Periodos extends JDialog {
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		Connexion con = new Connexion();
		
		DefaultTableModel model = new DefaultTableModel(0,2){
			public boolean isCellEditable(int fila, int columna){
				if(columna < 0)
					return true;
				return false;
			}
		};
		
		JTable tabla = new JTable(model);
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolio = new Componentes().text(new JTextField(),"Teclee Periodo", 2, "Int2");
		JTextField txtFecha = new Componentes().text(new JTextField(),"Teclee Fecha De Periodo", 10, "String");
	    int Catalogo=0;
		@SuppressWarnings({ "rawtypes" })
		public Cat_Filtro_De_Periodos()	{
			
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
			this.setTitle("Periodos De Fuentes De Sodas");

			campo.setBorder(BorderFactory.createTitledBorder("Lista De Peridos Por Fecha"));
			trsfiltro = new TableRowSorter(model); 
			tabla.setRowSorter(trsfiltro);  
			campo.add(getPanelTabla()).setBounds(15,42,220,360);
			campo.add(txtFolio).setBounds(15,20,100,20);
			campo.add(txtFecha).setBounds(116,20,100,20);
			
			llamar_render();
			
			agregar(tabla);
			cont.add(campo);
			
			txtFolio.addKeyListener(opFiltroFolio);
			txtFecha.addKeyListener(opFiltroFecha);
			
			this.setSize(255,445);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		}
		
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		        		int fila = tabla.getSelectedRow();
		    			int periodo = Integer.parseInt(tabla.getValueAt(fila, 0).toString().trim());
		    			
		    			actualizarPeriodo("",periodo);
		    			dispose();
		        	}
		        }
	        });
	    }
		
		
		
		KeyListener opFiltroFolio = new KeyListener(){
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
			}
			public void keyTyped(KeyEvent arg0) {
//				char caracter = arg0.getKeyChar();
//				if(((caracter < '0') ||
//					(caracter > '9')) &&
//				    (caracter != KeyEvent.VK_BACK_SPACE)){
//					arg0.consume(); 
//				}	
			}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		KeyListener opFiltroFecha = new KeyListener(){
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFecha.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		public void llamar_render(){
			
			tabla.getTableHeader().setReorderingAllowed(false) ;
	    	tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			tabla.getColumnModel().getColumn(0).setHeaderValue("Periodo");
			tabla.getColumnModel().getColumn(0).setMaxWidth(100);
			tabla.getColumnModel().getColumn(0).setMinWidth(100);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Fecha");
			tabla.getColumnModel().getColumn(1).setMaxWidth(100);
			tabla.getColumnModel().getColumn(1).setMinWidth(100);
			
			tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
			tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		}
		
	   	private JScrollPane getPanelTabla()	{		
			
			Statement s;
			ResultSet rs;
			try {
				s = con.conexion().createStatement();
				rs = s.executeQuery("select distinct periodo," +
						"            convert(varchar(20),Fecha_Captura,103)" +
						" 			 from tb_fuente_sodas_auxf " +
						"			 where status = 1");
				
				String [] fila = new String[2];
				while (rs.next()) {
				   fila[0] = rs.getString(1)+"  ";
				   fila[1] = "   "+rs.getString(2);
				   
			  	   model.addRow(fila); 
				}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 JScrollPane scrol = new JScrollPane(tabla);
			   
		    return scrol; 
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


