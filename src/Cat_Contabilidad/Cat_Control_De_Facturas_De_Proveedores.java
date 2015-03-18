package Cat_Contabilidad;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Conexiones_SQL.Connexion;
import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Control_De_Facturas_De_Proveedores extends JDialog {
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	
	public JTextField txtFolio = new Componentes().text(new JTextField(),"Busqueda Por Folio del Proveedor",25, "String");
	public JTextField txtProveedor = new JTextField();

	Object[][] Matriz_proveedores ;
	DefaultTableModel tabla_Proveedores= new DefaultTableModel(null,new String[]{"Folio", "Nombre de Proveedor", "Descripción"}
			){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class
         };
	     
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
        public boolean isCellEditable(int fila, int columna){
        	switch(columna){
        		case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 
        	 } 				
 			return false;
 		}
                
	};
	
	public JTable tabla = new JTable(tabla_Proveedores);
	public JScrollPane scroll_tabla = new JScrollPane(tabla);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(tabla_Proveedores); 
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	
	public Cat_Control_De_Facturas_De_Proveedores(){
		
		this.setModal(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		this.setTitle("Filtro de Proveedores");
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		this.panel.setBorder(BorderFactory.createTitledBorder(blackline,"Busqueda y Seleccion del Proveedor"));
		
		this.panel.add(txtFolio).setBounds(30,35,69,20);
		this.panel.add(txtProveedor).setBounds(101,35,300,20);
		this.panel.add(scroll_tabla).setBounds(30,60,800,335);
		
		this.init_tabla();
		this.tabla.addMouseListener(opAgregar);
		this.tabla.addKeyListener(seleccionEmpleadoconteclado);

		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtProveedor.addKeyListener(op_filtro_nombre);
		this.cont.add(panel);
		this.setSize(870,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
//      pone el foco en el txtproveedor al presionar la tecla scape y limpia lo buscado
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
           KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "foco");
        getRootPane().getActionMap().put("foco", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e)
            {   txtProveedor.setText("");
                txtProveedor.requestFocus(); }
        });
//      asigna el foco al JTextField del nombre deseado al arrancar la ventana
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){  	txtProveedor.requestFocus();   }
        });
        
	}
	
	MouseListener opAgregar = new MouseListener() {
		public void mouseReleased(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount() == 2){
    			int fila = tabla.getSelectedRow();
    			Object folio =  tabla.getValueAt(fila, 0);
    			Object Proveedor =  tabla.getValueAt(fila, 1);
    			dispose();
    			new Cat_Control_De_Facturas_Y_XML_De_Proveedores(folio.toString().trim(),Proveedor.toString().trim()).setVisible(true);
        	}
		}
	};
	
	@SuppressWarnings({ "unchecked" })
	public void init_tabla(){
/////////////////LLENADO DE TABLAS/////////////////////////////////////////////////////////////////////////////
//limpiar tablanomina
while(tabla_Proveedores.getRowCount()>0){	tabla_Proveedores.removeRow(0);	}
//llenar arreglo desde funcion
Object[][] getTablaNomina = llenarTablaProveedores();
Object[] fila = new Object[4];

// llenar tablanomina
 for(int i=0; i<getTablaNomina.length; i++){
         fila[0] = getTablaNomina[i][0]+"";
         fila[1] = getTablaNomina[i][1]+"";
         fila[2] = getTablaNomina[i][2]+"";
         fila[3] = getTablaNomina[i][3];
         tabla_Proveedores.addRow(fila); }
 
    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(70);
    	this.tabla.getColumnModel().getColumn(0).setMinWidth(30);		
    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(1500);
    	this.tabla.getColumnModel().getColumn(1).setMinWidth(300);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(1500);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(410);
    	this.tabla.getTableHeader().setReorderingAllowed(false) ;
    	this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				if(row%2==0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
				} 
				if(table.getSelectedRow() == row){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(186,143,73));
				}
			
				switch(column){
					case 0 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
					case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
			return lbl; 
			} 
		}; 
		this.tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tabla.getColumnModel().getColumn(2).setCellRenderer(render);

		this.tabla.setRowSorter(trsfiltro);  
				
    }
	
   	public Object[][] llenarTablaProveedores(){
   		
		String todos = "select cod_prv as folio,razon_social as proveedor,calle+' No. EXTERIOR:'+num_exterior+' '+colonia+' C.P:'+cod_postal+' '+pobmunedo+' TELS:'+tel1+' FAX:'+fax as Domicilio from proveedores where status_proveedor =1 order by proveedor asc";
		Statement s;
		ResultSet rs2;
		try {
			s = new Connexion().conexion_IZAGAR().createStatement();
			rs2 = s.executeQuery(todos);
			Matriz_proveedores = new Object[getFilasProveedores(todos)][4];
			int i=0;
			while(rs2.next()){
				Matriz_proveedores[i][0] = "   "+rs2.getString(1).trim();
				Matriz_proveedores[i][1] = "   "+rs2.getString(2).trim();
				Matriz_proveedores[i][2] = "   "+rs2.getString(3).trim();
								i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Cat_Filtro_Proveedores  en la funcion llenarTablaProveedores  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
	    return Matriz_proveedores; 
	}
   	
	public int getFilasProveedores(String qry){
		int filas=0;
		Statement stmt = null;
		try {stmt = new Connexion().conexion_IZAGAR().createStatement();
			ResultSet rs2 = stmt.executeQuery(qry);
			while(rs2.next()){filas++;}
		} catch (SQLException e1) {	e1.printStackTrace();}
		return filas;
	}
	
	 KeyListener op_filtro_folio = new KeyListener(){
			@SuppressWarnings("unchecked")
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
		
		KeyListener op_filtro_nombre = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtProveedor.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		KeyListener seleccionEmpleadoconteclado = new KeyListener() {
			@SuppressWarnings("static-access")
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
					if(caracter==e.VK_ENTER){
				int fila=tabla.getSelectedRow()-1;
				String folio = tabla.getValueAt(fila,0).toString().trim();
				String proveedor = tabla.getValueAt(fila,1).toString().trim();
				
				new Cat_Control_De_Facturas_Y_XML_De_Proveedores(folio,proveedor).setVisible(true);
				
				dispose();
				}
			}
			@Override
			public void keyPressed(KeyEvent e){}
			@Override
			public void keyReleased(KeyEvent e){}
									
		};
		public static void main(String args[]){
			try{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new Cat_Control_De_Facturas_De_Proveedores().setVisible(true);
			}catch(Exception e){	}
		}
	
}
