package Biblioteca;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.Connexion;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Renders.tablaRenderer;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Filtro_De_Asignaciones extends JDialog {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	public static Object[][] get_tabla(){
		return new BuscarTablasModel().tabla_model_asignaciones();
	}
	
    public static DefaultTableModel tabla_model = new DefaultTableModel(
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
		this.setModal(false);
//		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/sun_icon&16.png"));
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
		
//		agregar(tabla);
		
		cont.add(panel);
		
		txtFolioAsignacion.addKeyListener(opFiltroFolio);
		txtFolioCorte.addKeyListener(opFiltroFolioCorte);
		txtNombre.addKeyListener(opFiltroNombre);
		
		cmbEstablecimientos.addActionListener(opFiltro);
		
		this.setSize(755,415);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	ActionListener opCadena = new ActionListener() {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent arg0) {
			String cadena = "";
			for(int i = 0; i<=tabla.getRowCount(); i++){
				if(tabla.getValueAt(i, 4).toString().equals("true")){
					cadena+=tabla.getValueAt(i, 0).toString().trim();
				}
			}
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
//	public static void main(String [] args){
//		try{
//			UIManager.setLookAndFeel(
//					UIManager.getSystemLookAndFeelClassName());
//		}catch(Exception e){}
//		
//		Cat_Filtro_De_Asignaciones thisClass = new Cat_Filtro_De_Asignaciones();
//		thisClass.setVisible(true);
//
//		//utilizacion del AWTUtilities con el metodo opaque
//		try {
//			   @SuppressWarnings("rawtypes")
//			Class clazz =  Class.forName("com.sun.awt.AWTUtilities");
//			   Method method = clazz.getMethod("setWindowOpaque", java.awt.Window.class, Boolean.TYPE);
//			   method.invoke(clazz,thisClass , false);
//			   } catch (Exception e) 
//			   { }	
//	}
}


