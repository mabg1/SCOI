package Cat_Compras;

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
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Conexiones_SQL.Connexion;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Filtro_De_Busqueda_De_Productos extends JDialog {
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	
	public JTextField txtFolio = new Componentes().text(new JTextField(),"Busqueda Por Codigo del Producto",25, "Int");
	public JTextField txtProductoDescripcion = new Componentes().text(new JTextField(),"Busqueda Por Descripcion del Producto",300, "String");
	public JTextField txtFamiliaProducto = new Componentes().text(new JTextField(),"Busqueda Por Familia Del Producto",300, "String");
	public JTextField txtTallaProducto = new Componentes().text(new JTextField(),"Busqueda Por Talla Del Producto",300, "String");

	Object[][] Matriz_Productos ;
	DefaultTableModel Tabla_Productos= new DefaultTableModel(null,new String[]{"Codigo", "Descripcion","Clase Producto","Talla"}
			){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
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
        	 	case 3 : return false; 
        	 } 				
 			return false;
 		}
	};
	
	
	public JTable tabla = new JTable(Tabla_Productos);
	public JScrollPane scroll_tabla = new JScrollPane(tabla);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(Tabla_Productos); 
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	
	String valor_catalogo="";
	
	int bandera_filtro_familia=0;
	
	public Cat_Filtro_De_Busqueda_De_Productos(String bandera_origen_consulta_filro){
		valor_catalogo=bandera_origen_consulta_filro;

		this.setModal(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/Filter-List-icon16.png"));
		this.setTitle("Filtro de Productos");
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		this.panel.setBorder(BorderFactory.createTitledBorder(blackline,"Busqueda y Seleccion De Un Producto"));
		
		this.panel.add(txtFolio).setBounds(10,25,59,20);
		this.panel.add(txtProductoDescripcion).setBounds(70,25,450,20);
		this.panel.add(txtFamiliaProducto).setBounds(520,25,240,20);
		this.panel.add(scroll_tabla).setBounds(10,47,997,511);
		
		this.init_tabla();
		this.tabla.addMouseListener(opAgregar);
		this.tabla.addKeyListener(op_agregar_productoconteclado);

		this.txtFolio.addKeyListener(op_filtro_cod_Prod);
		this.txtProductoDescripcion.addKeyListener(op_filtro_Descripcion);
		this.txtFamiliaProducto.addKeyListener(op_filtro_Familia);
		this.txtTallaProducto.addKeyListener(op_filtro_Talla);
		
		this.cont.add(panel);
		this.setSize(1024,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
//      pone el foco en el txtProductoDescripcion al presionar la tecla scape y limpia lo buscado
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
           KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "foco");
        getRootPane().getActionMap().put("foco", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e)
            {   txtProductoDescripcion.setText("");
                txtProductoDescripcion.requestFocus(); }
        });
//      asigna el foco al JTextField del nombre deseado al arrancar la ventana
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){  	txtProductoDescripcion.requestFocus();   }
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
    			dispose();
    			
    			
    			switch(valor_catalogo){
				case "Cat_Cotizaciones_De_Un_Producto_En_Proveedores":		new Cat_Cotizaciones_De_Un_Producto_En_Proveedores(folio.toString().trim()).setVisible(true);
			           	dispose();
				break;
				
				case "125":		System.out.println("prueba");
	           	dispose();
		           break;
		           
		          default: 	 JOptionPane.showMessageDialog(null, "Error Avise al Administrador del Sistema"+valor_catalogo, "Aviso", JOptionPane.ERROR_MESSAGE);
				    break;
    			
    			
    			}
        	}
		}
	};
	
	KeyListener op_agregar_productoconteclado = new KeyListener() {
		@SuppressWarnings("static-access")
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
				if(caracter==e.VK_ENTER){
			int fila=tabla.getSelectedRow()-1;
			String folio = tabla.getValueAt(fila,0).toString().trim();
			
			switch(valor_catalogo){
			case "Cat_Cotizaciones_De_Un_Producto_En_Proveedores":		new Cat_Cotizaciones_De_Un_Producto_En_Proveedores(folio.toString().trim()).setVisible(true);
		           	dispose();
			break;
			
			case "125":		System.out.println("prueba");
           	dispose();
	           break;
	           
	          default: 	 JOptionPane.showMessageDialog(null, "Error Avise al Administrador del Sistema No Se Enuentra el Catalogo:"+valor_catalogo, "Aviso", JOptionPane.ERROR_MESSAGE);
			    break;
 			}
			}
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	
	
	
	
	@SuppressWarnings({ "unchecked" })
	public void init_tabla(){
/////////////////LLENADO DE TABLAS/////////////////////////////////////////////////////////////////////////////

		while(Tabla_Productos.getRowCount()>0){	Tabla_Productos.removeRow(0);	}

Object[][] getTablaNomina = llenarTablaProductos();

Object[] fila = new Object[4];

 for(int i=0; i<getTablaNomina.length; i++){
         fila[0] = getTablaNomina[i][0]+"";
         fila[1] = getTablaNomina[i][1]+"";
         fila[2] = getTablaNomina[i][2]+"";
         fila[3] = getTablaNomina[i][3]+"";
         Tabla_Productos.addRow(fila); }
 
	    this.tabla.getTableHeader().setReorderingAllowed(false) ;
	    this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    this.tabla.setRowSorter(trsfiltro);  
 
    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(60);
    	this.tabla.getColumnModel().getColumn(0).setMinWidth(60);		
    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(1500);
    	this.tabla.getColumnModel().getColumn(1).setMinWidth(450);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(1500);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(240);
    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(1500);
    	this.tabla.getColumnModel().getColumn(3).setMinWidth(240);
    	
    	render();
				
    }
	public void render(){
//		tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
		tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12)); 
		tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12)); 
		tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12));
		tabla.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("fecha","izquierda","Arial","normal",12));
	}
	
	
   	public Object[][] llenarTablaProductos(){
   		
		String todos = "select productos.cod_prod,productos.descripcion,case when clases_productos.nombre is null then '' else clases_productos.nombre end as clase_producto,tallas.nombre as talla" +
				"  from productos with (nolock) left outer join clases_productos on clases_productos.clase_producto= productos.clase_producto inner join tallas on tallas.talla=productos.talla" +
				"     order by descripcion ";

		Statement s;
		ResultSet rs2;
		try {
			s = new Connexion().conexion_IZAGAR().createStatement();
			rs2 = s.executeQuery(todos);
			Matriz_Productos = new Object[getFilasProveedores(todos)][4];
			int i=0;
			while(rs2.next()){
				Matriz_Productos[i][0] = "   "+rs2.getString(1).trim();
				Matriz_Productos[i][1] = "   "+rs2.getString(2).trim();
				Matriz_Productos[i][2] = "   "+rs2.getString(3).trim();
				Matriz_Productos[i][3] = "   "+rs2.getString(4).trim();
								i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Cat_Filtro_De_Busqueda_De_Productos  en la funcion llenarTablaProductos  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
	    return Matriz_Productos; 
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
	
	
	
	 KeyListener op_filtro_cod_Prod = new KeyListener(){
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
		
		KeyListener op_filtro_Descripcion = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				if(bandera_filtro_familia==1){
					
					trsfiltro.setRowFilter(RowFilter.regexFilter(txtFamiliaProducto.getText().toUpperCase().trim(), 2));
					
					trsfiltro.setRowFilter(RowFilter.regexFilter(txtProductoDescripcion.getText().toUpperCase().trim(), 1));
					
					
				}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtProductoDescripcion.getText().toUpperCase().trim(), 1));
				}
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		KeyListener op_filtro_Familia = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFamiliaProducto.getText().toUpperCase().trim(), 2));
				bandera_filtro_familia=1;
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		KeyListener op_filtro_Talla = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtTallaProducto.getText().toUpperCase().trim(), 3));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		
		
		
	
		
		
		public static void main(String args[]){
			try{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new Cat_Filtro_De_Busqueda_De_Productos("Cotizaciones_De_Un_Producto").setVisible(true);
			}catch(Exception e){	}
		}
	
}
