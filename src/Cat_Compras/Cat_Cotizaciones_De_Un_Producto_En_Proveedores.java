package Cat_Compras;

import java.awt.AWTException;
import java.awt.Container;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


import com.toedter.calendar.JDateChooser;


import Conexiones_SQL.Connexion;
import Obj_Compras.Obj_Cotizaciones_De_Un_Producto;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Cotizaciones_De_Un_Producto_En_Proveedores extends JFrame{
				Container cont = getContentPane();
				JLayeredPane panel = new JLayeredPane();
				
				JTextField txtcod_prod = new Componentes().text(new JTextField(), "Codigo del Producto", 15, "String");
				JCheckBox chbHabilitarVentas = new JCheckBox("Cargar Ventas");	
				JButton btnBuscar_Producto = new JButton("",new ImageIcon("imagen/Filter-List-icon16.png"));
				JButton btnBuscar_Proveedor= new JButton("",new ImageIcon("imagen/Filter-List-icon16.png"));
				JButton btnNuevo = new JButton("Nuevo",new ImageIcon("imagen/Nuevo.png"));
//				JButton btnEditar = new JButton("Editar",new ImageIcon("imagen/editara.png"));
				JButton btnSalir = new JButton("Salir",new ImageIcon("imagen/salir16.png"));
				JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
				JButton btnDeshacer = new JButton("Deshacer",new ImageIcon("imagen/deshacer16.png"));
				JButton btnLimpiar = new JButton("Limpiar",new ImageIcon("imagen/clear-brush-broom-sweeping-change-icone-7230-16.png"));
				
				JLabel Marcoproveedor1= new JLabel();
				JLabel JLBdescripcion= new JLabel();
				JLabel JLBultimo_costo= new JLabel();
				JLabel JLBcosto_promedio= new JLabel();
				JLabel JLBexist_cedis= new JLabel();
				JLabel JLBexist_total= new JLabel();
				JLabel JLvent_total= new JLabel();
				
				JTextField txtFoliocompra1 = new Componentes().text(new JTextField(), "Folio De La Compra",30, "String");
				JTextField txtCod_Prv = new Componentes().text(new JTextField(), "Codigo Del Proveedor",15, "String");
				JTextField txtNom_Proveedor = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
				JTextField txtCant_Requerida_Compra = new Componentes().text(new JTextField(), "Cantidad Requerida De La Compra",15, "Double");
				JTextField txtCosto_Unitario = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",15, "Double");
			
				JTextArea txaCondiciones = new Componentes().textArea(new JTextArea(), "Condiciones De La Compra", 350);
				JScrollPane Condiciones = new JScrollPane(txaCondiciones);
				
				JDateChooser cfecha = new JDateChooser();
				Connexion con = new Connexion();
				
				DefaultTableModel modelo_prv = new DefaultTableModel(null,
			            new String[]{"Compra","Cod.Prv", "Proveedor","Fecha Compra","Unid","Cont","Cantidad","Costo","Costo PZ","Ultimo Costo","Costo Prom","Exist Cedis","Exist Total","Nota de La Negociacion","Cotizo"}
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
			        	 	case 4 : return false;
			        	 	case 5 : return false;
			        	 	case 6 : return false;
			        	 	case 7 : return false;
			        	 	case 8 : return false;
			        	 	case 9 : return false;
			        	 	case 10 : return true;
			        	 	case 11 : return true;
			        	 	case 12 : return true;
			        	 	case 13 : return true;
			        	 	case 14 : return true;
			        	 } 				
			 			return false;
			 		}
				};
			    JTable tabla_Proveedor = new JTable(modelo_prv);
			    JScrollPane scrollAsignado = new JScrollPane(tabla_Proveedor);
			    
			    
			    
			    
				DefaultTableModel modelo_exist_estab = new DefaultTableModel(null,
			            new String[]{"Establecimiento","Exist","Vnt Pzs","Dias Vent","Vent/Dias","Exist/PrVtD","Fecha Agotado"}
						){
				     @SuppressWarnings("rawtypes")
					Class[] types = new Class[]{
				    	java.lang.String.class,
				    	java.lang.String.class,
				    	java.lang.String.class,
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
			        	 	case 4 : return false;
			        	 	case 5 : return false;
			        	 	case 6 : return false;
			        	 } 				
			 			return false;
			 		}
				};
			    JTable tabla_Existencia_Estab = new JTable(modelo_exist_estab);
			    JScrollPane scrollExist_Estab = new JScrollPane(tabla_Existencia_Estab);
			    
			    
				
				Border blackline, etched, raisedbevel, loweredbevel, empty;
				
			  String codigo_producto = "";
	          String Nombre_Catalogo_Para_Filtro="";
	  		  String descripcion="";
			  double ultimo_costo=0;
			  double costo_promedio=0;
			  double exist_cedis=0;
			  double exist_total=0;
			  double venta_total=0;
			  
	public Cat_Cotizaciones_De_Un_Producto_En_Proveedores(String cod_prod){
		codigo_producto=cod_prod+"";
		txtcod_prod.setText(codigo_producto+"");

		setSize(1024,635);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Cotizaciones De Un Producto En Proveedores");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/encontrar-busqueda-lupa-de-la-ventana-de-zoom-icono-4008-32.png"));
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Seleccione el Tipo de Reporte"));
		txaCondiciones.setBorder(BorderFactory.createTitledBorder(blackline));
		txaCondiciones.setLineWrap(true); 
		txaCondiciones.setWrapStyleWord(true);

		int x=10 ;
		int y=20 ;
		int a=20;
		int l=100;
		
		panel.add(txtcod_prod).setBounds(x,y,l,a);
		panel.add(btnBuscar_Producto).setBounds(l+=x,y,a,a);
		panel.add(JLBdescripcion).setBounds(l+x+20,y,l+340,a);
		panel.add(cfecha).setBounds(x,y+=25,l+10,a);
		
		panel.add(new JLabel("Ultimo Costo:")).setBounds(x+=l+20,y,l-40,a);
		panel.add(JLBultimo_costo).setBounds(x+=75,y,l-35,a);
		panel.add(new JLabel("Costo Promedio:")).setBounds(x+=l-45,y,l-30,a);
		panel.add(JLBcosto_promedio).setBounds(x+=85,y,l-35,a);
		panel.add(new JLabel("Venta Total:")).setBounds(x+=80,y,l-20,a);
		panel.add(JLvent_total).setBounds(x+=85,y,l-35,a);
		
		panel.add(chbHabilitarVentas).setBounds(x-523,y+=20,l,a);
		
		panel.add(new JLabel("Existencia Cedis:")).setBounds(x+=-250,y,l-20,a);
		panel.add(JLBexist_cedis).setBounds(x+=85,y,l-35,a);
		panel.add(new JLabel("Existencia Total:")).setBounds(x+=l-30,y,l-20,a);
		panel.add(JLBexist_total).setBounds(x+=85,y,l-35,a);

		
		x=10;
		
		panel.add(new JLabel("No.Cotizacion")).setBounds(x,y+=20,l-20,a);
		panel.add(new JLabel("Cod.Proveedor")).setBounds(x+75,y,l-20,a);
		panel.add(new JLabel("Nombre Del Proveedor")).setBounds(x+220,y,l+150,a);
		panel.add(new JLabel("Costo Unitario")).setBounds(x+400,y,l,a);
		panel.add(new JLabel("Cantidad Piezas" )).setBounds(x+480,y,l,a);
		panel.add(new JLabel("Condiciones")).setBounds(x+10,y+40,l+20,a);
		panel.add(new JLabel("De La Compra")).setBounds(x+10,y+60,l+20,a);

		l=87; 
		panel.add(txtFoliocompra1).setBounds(x,y+=20,l-20,a);
		panel.add(txtCod_Prv).setBounds(x+=67,y,l-20,a);
		panel.add(btnBuscar_Proveedor).setBounds(x+=68,y,a,a);
		panel.add(txtNom_Proveedor).setBounds(x+=20,y,l+150,a);
		panel.add(txtCosto_Unitario).setBounds(x+=235,y,l,a);
		panel.add(txtCant_Requerida_Compra).setBounds(x+=88,y,l,a);
		
		
		
		l=110;
		x=10;
		panel.add(Condiciones).setBounds(x+80,y+25,l+375,40);
		panel.add(btnNuevo).setBounds(x,y+=70,l-10,a);
		panel.add(btnDeshacer).setBounds(x+115,y,l-10,a);
		panel.add(btnGuardar).setBounds(x+227,y,l-10,a);
		panel.add(btnSalir).setBounds(x+339,y,l-10,a);
		panel.add(btnLimpiar).setBounds(x+452,y,l-10,a);
		
		panel.add(Tabla_Proveedor()).setBounds(10,200,1000,400);
		panel.add(Tabla_Exist_Estab()).setBounds(580,13,430,180);
		
		txtFoliocompra1.setEditable(false);
		txtCod_Prv.setEditable(false);
		txtNom_Proveedor.setEditable(false);
		txtCosto_Unitario.setEditable(false);
		txtCant_Requerida_Compra.setEditable(false);
		
		Condiciones.setEnabled(false);
		Condiciones.getHorizontalScrollBar().setEnabled(false);
		Condiciones.getVerticalScrollBar().setEnabled(false);
		Condiciones.getViewport().getView().setEnabled(false);
		
		btnNuevo.setEnabled(false);
		btnDeshacer.setEnabled(false);
//		btnEditar.setEnabled(false);
		btnGuardar.setEnabled(false);
		btnBuscar_Proveedor.setEnabled(false);
		chbHabilitarVentas.setSelected(true);

		
	    cfecha.setEnabled(true);
	    
		cont.add(panel);
		
		
		Nombre_Catalogo_Para_Filtro=this.getClass().getSimpleName();
		btnBuscar_Producto.addActionListener(opBuscar_Producto);
		btnNuevo.addActionListener(nuevo);
		btnDeshacer.addActionListener(deshacer);
		
		btnGuardar.addActionListener(Guardar);
		btnSalir.addActionListener(salir);
		btnLimpiar.addActionListener(limpiar);
		
//		btnEditar.addActionListener(editar);
		
		txtcod_prod.addKeyListener(Buscar_Datos_Producto);
		txtFoliocompra1.addKeyListener(pasa_cod_prv);
		txtCod_Prv.addKeyListener(pasa_nom_prv);
		txtNom_Proveedor.addKeyListener(pasa_costo_unitario);
		txtCosto_Unitario.addKeyListener(pasa_cant_requerida);
		txtCant_Requerida_Compra.addKeyListener(pasa_notas);
		
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "buscarLR");
          getRootPane().getActionMap().put("buscarLR", new AbstractAction(){
         public void actionPerformed(ActionEvent e)
         {         
        	 btnBuscar_Producto.doClick();
       	    }
         });
          
          
          if(codigo_producto.equals("")){
			          this.addWindowListener(new WindowAdapter() {
			                  public void windowOpened( WindowEvent e ){
			                  	txtcod_prod.requestFocus();
			                  	 }
			          });
          }else{
			          this.addWindowListener(new WindowAdapter() {
		                  public void windowOpened( WindowEvent e ){
		                  	txtcod_prod.requestFocus();
		                  	enterauto();
		                  	 }
		              });
          }
          
          
          String fecha = new Obj_Cotizaciones_De_Un_Producto().Hoymenos3meses().getFecha();
          java.util.Date Fecha = null;
		try {
			Fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
          cfecha.setDate(Fecha);
          cfecha.getDateEditor().addPropertyChangeListener(opBusqueda_Con_La_Fecha);
	}
	
	
	public void enterauto(){
		Robot robot;
		try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
     };
	     
	 PropertyChangeListener opBusqueda_Con_La_Fecha = new PropertyChangeListener() {
	     	  public void propertyChange(PropertyChangeEvent e) {
	     	            if ("date".equals(e.getPropertyName())) {
	     	            	
	   	  	            	if(cfecha.getDate() != null){
	   	  	            		
	   	  	            	while(tabla_Existencia_Estab.getRowCount()>0){
								modelo_exist_estab.removeRow(0);  }
							Llenar_Tabla_Exist_Estab ();
							
	   	  	            	while(tabla_Proveedor.getRowCount()>0){
								modelo_prv.removeRow(0);  }
							 Llenar_Tabla_proveedores ();
							 render_proveedor();
	   	  	            	}
	          }
	   	}
	 };
	
	
	ActionListener opBuscar_Producto = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new Cat_Filtro_De_Busqueda_De_Productos(Nombre_Catalogo_Para_Filtro).setVisible(true);
		}
	};
	
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			txtFoliocompra1.setEditable(true);
			txtFoliocompra1.setText("COTIZACION");
			
			txtCod_Prv.setEditable(true);
			txtCod_Prv.setText("NUEVO");
			txtNom_Proveedor.setEditable(true);
			txtCosto_Unitario.setEditable(true);
			txtCant_Requerida_Compra.setEditable(true);
		    btnDeshacer.setEnabled(true); 
		    btnNuevo.setEnabled(false);
		    btnGuardar.setEnabled(true);
			Condiciones.getHorizontalScrollBar().setEnabled(true);
			Condiciones.getVerticalScrollBar().setEnabled(true);
			Condiciones.getViewport().getView().setEnabled(true);
			
			txtNom_Proveedor.requestFocus();
			
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFoliocompra1.setText("");
			txtCod_Prv.setText("");
			txtNom_Proveedor.setText("");
			txtCosto_Unitario.setText("");
			txtCant_Requerida_Compra.setText("");
			txaCondiciones.setText("");
			
			txtFoliocompra1.setEditable(false);
			txtCod_Prv.setEditable(false);
			txtNom_Proveedor.setEditable(false);
			txtCosto_Unitario.setEditable(false);
			txtCant_Requerida_Compra.setEditable(false);
			
			Condiciones.setEnabled(false);
			Condiciones.getHorizontalScrollBar().setEnabled(false);
			Condiciones.getVerticalScrollBar().setEnabled(false);
			Condiciones.getViewport().getView().setEnabled(false);
			
			btnNuevo.setEnabled(true);
			btnDeshacer.setEnabled(false);
//			btnEditar.setEnabled(false);
			btnGuardar.setEnabled(false);
			btnBuscar_Proveedor.setEnabled(false);	
			
		}
	};
	
	ActionListener limpiar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			txtFoliocompra1.setText("");
			txtCod_Prv.setText("");
			txtNom_Proveedor.setText("");
			txtCosto_Unitario.setText("");
			txtCant_Requerida_Compra.setText("");
			txtcod_prod.setText("");
			txaCondiciones.setText("");
			
			txtFoliocompra1.setEditable(false);
			txtCod_Prv.setEditable(false);
			txtNom_Proveedor.setEditable(false);
			txtCosto_Unitario.setEditable(false);
			txtCant_Requerida_Compra.setEditable(false);
			
			Condiciones.setEnabled(false);
			Condiciones.getHorizontalScrollBar().setEnabled(false);
			Condiciones.getVerticalScrollBar().setEnabled(false);
			Condiciones.getViewport().getView().setEnabled(false);
			
			
			btnNuevo.setEnabled(false);
			btnDeshacer.setEnabled(false);
//			btnEditar.setEnabled(false);
			btnGuardar.setEnabled(false);
			btnBuscar_Proveedor.setEnabled(false);	
			
			while(tabla_Existencia_Estab.getRowCount()>0){
				modelo_exist_estab.removeRow(0);  }
			
			while(tabla_Proveedor.getRowCount()>0){
				modelo_prv.removeRow(0);  }
			
			JLBdescripcion.setText("");
			JLBultimo_costo.setText("");
			JLBcosto_promedio.setText("");
			JLBexist_cedis.setText("");
			JLBexist_total.setText("");
			JLvent_total.setText("");
		}
	};
	
	ActionListener Guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}else{
                Obj_Cotizaciones_De_Un_Producto cotizacion_prod = new Obj_Cotizaciones_De_Un_Producto();					
				
                cotizacion_prod.setCod_Prod(codigo_producto);
                cotizacion_prod.setFolio_compra(txtFoliocompra1.getText()+"");
                cotizacion_prod.setCod_Prv(txtCod_Prv.getText()+"");
                cotizacion_prod.setProveedor(txtNom_Proveedor.getText()+"");
                cotizacion_prod.setUltimo_Costo(ultimo_costo);
                cotizacion_prod.setCosto_Promedio(costo_promedio);
                cotizacion_prod.setCosto_Nuevo(Double.valueOf(txtCosto_Unitario.getText()));
                cotizacion_prod.setCantidad_Requerida(Double.valueOf(txtCant_Requerida_Compra.getText()));
                cotizacion_prod.setNotas_Negociacion(txaCondiciones.getText().toUpperCase().trim()+"");
                cotizacion_prod.setExistencia_Cedis(exist_cedis);
//                cotizacion_prod.setExistencia_Total(exist_total);
                
                
               if(cotizacion_prod.Guardar_Cotizacion()){
            	   btnDeshacer.doClick();  
            	   while(tabla_Proveedor.getRowCount()>0){
						modelo_prv.removeRow(0);  }
					 Llenar_Tabla_proveedores ();
					 render_proveedor();
            	   JOptionPane.showMessageDialog(null, "Se Guardo Correctamente:","Aviso", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("Imagen//aplicara-el-dialogo-icono-6256-32.png"));
   				return;
               }
   			   JOptionPane.showMessageDialog(null, "Error  en la funcion [ Guardar ] \n if(Obj_Cotizaciones_De_Un_Producto.Guardar_Cotizacion()) ", "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				return;
                
				}
			
		}
	};
	private String validaCampos(){
		String error="";
		if(codigo_producto.equals("")) error +="Codigo de Producto \n";
		if(txtFoliocompra1.getText().equals("")) 		error+= "Folio compra\n";
		if(txtCod_Prv.getText().equals("")) 		error+= "Codigo Proveedor\n";
		if(txtNom_Proveedor.getText().equals(""))	error+= "Nombre Proveedor\n";
		if(txtCosto_Unitario.getText().equals(""))	error+= "Costo Unitario\n";
		if(txtCant_Requerida_Compra.getText().equals(""))	error+= "Cantidad De La Compra\n";
		if(txaCondiciones.getText().equals(""))	error+= "Condiciones De La Compra\n";
		return error;
	}
	
	KeyListener pasa_cod_prv = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				txtCod_Prv.requestFocus();
			}
		}
	};
	
	KeyListener pasa_nom_prv = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				txtNom_Proveedor.requestFocus();
			}
		}
	};
	
	KeyListener pasa_costo_unitario = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				txtCosto_Unitario.requestFocus();
			}
		}
	};
	
	KeyListener pasa_cant_requerida = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				txtCant_Requerida_Compra.requestFocus();
			}
		}
	};
	
	KeyListener pasa_notas= new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				txaCondiciones.requestFocus();
			}
		}
	};
	
	
	
	KeyListener Buscar_Datos_Producto = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				exist_total=0;
				venta_total=0;
				try {
					
					if(new Obj_Cotizaciones_De_Un_Producto().Existe_Producto(txtcod_prod.getText().trim().toUpperCase()+"")){
						
				      Obj_Cotizaciones_De_Un_Producto  Datos_Producto= new Obj_Cotizaciones_De_Un_Producto().buscardatos_producto(txtcod_prod.getText().trim().toUpperCase()+"");
			            
						descripcion=Datos_Producto.getDescripcion_Prod();
						ultimo_costo=Datos_Producto.getUltimo_Costo();
						costo_promedio=Datos_Producto.getCosto_Promedio();
						exist_cedis=Datos_Producto.getExistencia_Cedis();
//						exist_total=Datos_Producto.getExistencia_Total();
						txtcod_prod.setText(Datos_Producto.getCod_Prod());
						btnNuevo.setEnabled(true);
						
						JLBdescripcion.setText("<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLUE><CENTER><b><p>"+descripcion+"</p></b></CENTER></FONT></html>");
						JLBultimo_costo.setText("<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk><p>"+ultimo_costo+"</p></FONT></html>");
						JLBcosto_promedio.setText("<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk><p>"+costo_promedio+"</p></FONT></html>");
						JLBexist_cedis.setText("<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk><p>"+exist_cedis+"</p></FONT></html>");
						
						
						while(tabla_Existencia_Estab.getRowCount()>0){
							modelo_exist_estab.removeRow(0);  }
						Llenar_Tabla_Exist_Estab ();
						render_Exist_Estab();
						
						
						JLBexist_total.setText("<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk><p>"+exist_total+"</p></FONT></html>");
						JLvent_total.setText("<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk><p>"+venta_total+"</p></FONT></html>");
						while(tabla_Proveedor.getRowCount()>0){
							modelo_prv.removeRow(0); }
						 Llenar_Tabla_proveedores ();
						 render_proveedor();
						 
						 codigo_producto=txtcod_prod.getText().trim().toUpperCase()+"";
						 
					}else{
						JOptionPane.showMessageDialog(null, "El Codigo Esta Mal Escrito o El Producto No Existe" , "Aviso", JOptionPane.CANCEL_OPTION);
					
                    }
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error en Cat_Cotizaciones_De_Un_Producto_En_Proveedores  en la funcion existe_Producto \n SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	};

public void render_proveedor(){
			//		tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
			    
					tabla_Proveedor.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",10)); 
					tabla_Proveedor.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("fecha","izquierda","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer("texto","centro","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(5).setCellRenderer(new tablaRenderer("texto","centro","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(6).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(7).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(8).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(9).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(10).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(11).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(12).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
					tabla_Proveedor.getColumnModel().getColumn(13).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",10));
					tabla_Proveedor.getColumnModel().getColumn(14).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",10));
				}
				
				
		private JScrollPane Tabla_Proveedor()	{		
		
			    this.tabla_Proveedor.getTableHeader().setReorderingAllowed(false) ;
				this.tabla_Proveedor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
				tabla_Proveedor.getColumnModel().getColumn(0).setMaxWidth(65);
				tabla_Proveedor.getColumnModel().getColumn(0).setMinWidth(65);
				tabla_Proveedor.getColumnModel().getColumn(1).setMaxWidth(65);
				tabla_Proveedor.getColumnModel().getColumn(1).setMinWidth(65);
				tabla_Proveedor.getColumnModel().getColumn(2).setMaxWidth(300);
				tabla_Proveedor.getColumnModel().getColumn(2).setMinWidth(300);
				tabla_Proveedor.getColumnModel().getColumn(3).setMaxWidth(80);
				tabla_Proveedor.getColumnModel().getColumn(3).setMinWidth(80);
				tabla_Proveedor.getColumnModel().getColumn(4).setMaxWidth(35);
				tabla_Proveedor.getColumnModel().getColumn(4).setMinWidth(35);
				tabla_Proveedor.getColumnModel().getColumn(5).setMaxWidth(35);
				tabla_Proveedor.getColumnModel().getColumn(5).setMinWidth(35);
				tabla_Proveedor.getColumnModel().getColumn(6).setMaxWidth(70);
				tabla_Proveedor.getColumnModel().getColumn(6).setMinWidth(60);
				tabla_Proveedor.getColumnModel().getColumn(7).setMaxWidth(70);
				tabla_Proveedor.getColumnModel().getColumn(7).setMinWidth(60);
				tabla_Proveedor.getColumnModel().getColumn(8).setMaxWidth(70);
				tabla_Proveedor.getColumnModel().getColumn(8).setMinWidth(60);
				tabla_Proveedor.getColumnModel().getColumn(9).setMaxWidth(70);
				tabla_Proveedor.getColumnModel().getColumn(9).setMinWidth(60);
				tabla_Proveedor.getColumnModel().getColumn(10).setMaxWidth(70);
				tabla_Proveedor.getColumnModel().getColumn(10).setMinWidth(60);
				tabla_Proveedor.getColumnModel().getColumn(11).setMaxWidth(70);
				tabla_Proveedor.getColumnModel().getColumn(11).setMinWidth(60);
				tabla_Proveedor.getColumnModel().getColumn(12).setMaxWidth(70);
				tabla_Proveedor.getColumnModel().getColumn(12).setMinWidth(60);
				tabla_Proveedor.getColumnModel().getColumn(13).setMaxWidth(500);
				tabla_Proveedor.getColumnModel().getColumn(13).setMinWidth(90);
				tabla_Proveedor.getColumnModel().getColumn(14).setMaxWidth(250);
				tabla_Proveedor.getColumnModel().getColumn(14).setMinWidth(90);
			
					
					 JScrollPane scrol = new JScrollPane(tabla_Proveedor);
				    return scrol; 
				}
				
				public void Llenar_Tabla_proveedores (){
					Statement s;
					ResultSet rs;
					
					try {
						
					/////ORIGEN COMPRAS
						s = con.conexion_IZAGAR().createStatement();
						String cod_producto=txtcod_prod.getText().trim().toUpperCase()+"";
						String fecha_busqueda =new SimpleDateFormat("dd/MM/yyyy").format(cfecha.getDate());
						
						rs = s.executeQuery("DECLARE @cod_prod varchar(35) set @cod_prod='"+cod_producto+"'" +
								" 	SELECT entysal.folio" +
								" 		  ,proveedores.cod_prv" +
								" 		  ,proveedores.razon_social as proveedor" +
								"   	  ,convert(varchar(15),entysal.fecha,103) as fecha_compra" +
								" 		  ,entysal.unidad" +
								"		  ,convert(numeric(10,2),productos.contenido) as contenido" +
								"		  ,convert(numeric(10,2),entysal.cantidad) as cantidad" +
								"		  ,convert(numeric(10,2),entysal.costo) as costo" +
								"		  ,convert(numeric(10,2),round((entysal.importe/entysal.cantidad),2)) as costo_pz" +
								"   	  ,0 AS ultimo_costo" +
								"		  ,0 as costo_promedio" +
								"   	  ,0 as exist_cedis" +
								"         ,0 as exist_total" +
								"		  ,'' as notas" +
								"         ,'' as cotizo "+
								"         ,entysal.fecha as fecha_order_by "+    
								"   FROM entysal with(nolock)  " +
								"	     inner join productos on productos.cod_prod=entysal.cod_prod" +
								"	     inner join proveedores on proveedores.cod_prv=entysal.cod_prv" +
								" 	 WHERE entysal.transaccion='44'  and entysal.cod_prod=@cod_prod and entysal.fecha>'"+fecha_busqueda+"'   " +
								"     UNION ALL" +
								"  SELECT folio_compra as folio" +
								" 		  ,cod_prv" +
								"		  ,proveedor" +
								"		  ,convert(varchar(15),fecha_de_cotizacion,103) as fecha_compra" +
								"		  ,'PZ' as unidad" +
								"		  ,1 as contenido" +
								"		  ,cantidad_requerida as cantidad" +
								"		  ,convert(numeric(30,2),cantidad_requerida*costo_nuevo) as costo" +
								"		  ,convert(numeric(10,2),costo_nuevo) as costo_pz" +
								"		  ,convert(numeric(10,2),ultimo_costo) as ultimo_costo" +
								"		  ,convert(numeric(10,2),costo_promedio) as costo_promedio" +
								"		  ,exist_cedis" +
								"		  ,exist_total" +
								"		  ,notas" +
								"         ,folio_empleado_cotizo"+
								"         ,fecha_de_cotizacion as fecha_order_by "+
								"   FROM IZAGAR_captura_cotizaciones_de_un_producto_en_proveedores as cotizacion" +
								"	 WHERE cod_prod=@cod_prod and fecha_de_cotizacion>'"+fecha_busqueda+"' order by fecha_order_by desc")   ;
						
						
						
						while (rs.next())
						{ 
						   Object [] fila = new Object[15];
						   fila[0] = rs.getString(1).trim();
						   fila[1] = rs.getString(2).trim();
						   fila[2] = rs.getString(3).trim(); 
						   fila[3] = rs.getString(4).trim(); 
						   fila[4] = rs.getString(5).trim(); 
						   fila[5] = rs.getString(6).trim(); 
						   fila[6] = rs.getString(7).trim();
						   fila[7] = rs.getString(8).trim();
						   fila[8] = rs.getString(9).trim();
						   fila[9] = rs.getString(10).trim();
						   fila[10] = rs.getString(11).trim();
						   fila[11] = rs.getString(12).trim();
						   fila[12] = rs.getString(13).trim();
						   fila[13] = rs.getString(14).trim();
						   fila[14] = rs.getString(15).trim();
						   
						   
						   modelo_prv.addRow(fila); 
						}	

					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error en Cat_Cotizaciones_De_Un_Producto_En_Proveedores en la funcion Llenar_Tabla_proveedores  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
					}
}
	
public void render_Exist_Estab(){
					//		tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
							
	tabla_Existencia_Estab.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",10)); 
	tabla_Existencia_Estab.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
	tabla_Existencia_Estab.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
	tabla_Existencia_Estab.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
	tabla_Existencia_Estab.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
	tabla_Existencia_Estab.getColumnModel().getColumn(5).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
	tabla_Existencia_Estab.getColumnModel().getColumn(6).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12));
						}
        private JScrollPane Tabla_Exist_Estab()	{		
				
			this.tabla_Existencia_Estab.getTableHeader().setReorderingAllowed(false) ;
			this.tabla_Existencia_Estab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
			tabla_Existencia_Estab.getColumnModel().getColumn(0).setMaxWidth(108);
			tabla_Existencia_Estab.getColumnModel().getColumn(0).setMinWidth(108);
			tabla_Existencia_Estab.getColumnModel().getColumn(1).setMaxWidth(55);
			tabla_Existencia_Estab.getColumnModel().getColumn(1).setMinWidth(55);
			tabla_Existencia_Estab.getColumnModel().getColumn(2).setMaxWidth(60);
			tabla_Existencia_Estab.getColumnModel().getColumn(2).setMinWidth(60);
			tabla_Existencia_Estab.getColumnModel().getColumn(3).setMaxWidth(55);
			tabla_Existencia_Estab.getColumnModel().getColumn(3).setMinWidth(55);
			tabla_Existencia_Estab.getColumnModel().getColumn(4).setMaxWidth(60);
			tabla_Existencia_Estab.getColumnModel().getColumn(4).setMinWidth(60);
			tabla_Existencia_Estab.getColumnModel().getColumn(5).setMaxWidth(60);
			tabla_Existencia_Estab.getColumnModel().getColumn(5).setMinWidth(60);
			tabla_Existencia_Estab.getColumnModel().getColumn(6).setMaxWidth(120);
			tabla_Existencia_Estab.getColumnModel().getColumn(6).setMinWidth(120);
				
				 JScrollPane scrol = new JScrollPane(tabla_Existencia_Estab);
			    return scrol; 
			}
		
		public void Llenar_Tabla_Exist_Estab (){
			Statement s;
			ResultSet rs;
			String cod_producto=txtcod_prod.getText().trim().toUpperCase()+"";
			
			try {
				s = con.conexion_IZAGAR().createStatement();
				String fecha_busqueda =new SimpleDateFormat("dd/MM/yyyy").format(cfecha.getDate());
				rs=null;
				
				if(chbHabilitarVentas.isSelected()){
										rs = s.executeQuery("SELECT  establecimientos.nombre as establecimiento" +
												"                   ,convert(numeric(10,2),isnull(sum(case when (productos.contenido)<>1 then((productos.contenido*prodestab.exist_unidades)+exist_piezas) else (prodestab.exist_piezas)end),0)) as existencia_pz" +
												"                   ,convert(numeric(10,2),isnull(mab.venta_pzas,0)) as venta_pzas" +
										        "                   ,datediff(day,'"+fecha_busqueda+"',getdate()) as dias_de_venta"+
										        "                   ,convert(numeric(10,2),(isnull(mab.venta_pzas,0) /datediff(day,'"+fecha_busqueda+"',getdate()))) as promedio_de_venta_diaria"+
										        "                   ,convert(numeric(10,2),(isnull(sum(case when (productos.contenido)<>1 then((productos.contenido*prodestab.exist_unidades)+exist_piezas) else (prodestab.exist_piezas)end),0))/(isnull(mab.venta_pzas,1) /datediff(day,'"+fecha_busqueda+"',getdate())))   as Proyeccion_de_Venta_prom"+
												"                   ,isnull(convert(varchar(20),prodestab.fecha_agotado,103),'Sin Fecha Agotado')as fecha_agotado" +
												"              FROM prodestab with (nolock) " +
												"                 inner join productos on productos.cod_prod=prodestab.cod_prod" +
												"                 inner join establecimientos on establecimientos.cod_estab=prodestab.cod_estab and establecimientos.cod_estab not in(15,19,20)" +
												"  			      left outer join	(SELECT Entysal.cod_prod ,sum(case when entysal.unidad <> 'U' then entysal.cantidad else 0 end) as venta_pzas ,facremtick.cod_estab" +
												" 									   FROM Entysal with (nolock)  " +
												"  									      INNER JOIN facremtick  with (nolock) ON Entysal.folio = facremtick.folio AND Entysal.transaccion = facremtick.transaccion " +
												"  										  INNER JOIN productos on entysal.cod_prod = productos.cod_prod " +
												" 									   WHERE (entysal.cod_prod ='"+cod_producto+"') AND  entysal.fecha between '"+fecha_busqueda+"'"+
												"                                                and getdate()  AND Entysal.transaccion in ('36','37','38')" +
												"									 GROUP BY Entysal.cod_prod,facremtick.cod_estab)mab on mab.cod_estab=prodestab.cod_estab" +
												"			   WHERE prodestab.cod_prod='"+cod_producto+"'"+
												"            GROUP BY establecimientos.nombre,convert(varchar(20),prodestab.fecha_agotado,103),mab.venta_pzas order by establecimiento asc")           ;
			      }else{
									rs = s.executeQuery("SELECT  establecimientos.nombre as establecimiento" +
											"                   ,convert(numeric(10,2),isnull(sum(case when (productos.contenido)<>1 then((productos.contenido*prodestab.exist_unidades)+exist_piezas) else (prodestab.exist_piezas)end),0)) as existencia_pz" +
											"                   ,0 as venta_pzas" +
											"                   ,0 as dias_de_venta"+
											"                   ,0 as promedio_venta_diaria"+ 
											"                   ,0 as Proyeccion_de_Venta_prom "+
											"                   ,isnull(convert(varchar(20),prodestab.fecha_agotado,103),'Sin Fecha Agotado')as fecha_agotado" +
											"              FROM prodestab with (nolock) " +
											"                 inner join productos on productos.cod_prod=prodestab.cod_prod" +
											"                 inner join establecimientos on establecimientos.cod_estab=prodestab.cod_estab and establecimientos.cod_estab not in(15,19,20)" +
											"			   WHERE prodestab.cod_prod='"+cod_producto+"'"+
											"            GROUP BY establecimientos.nombre,convert(varchar(20),prodestab.fecha_agotado,103) order by establecimiento asc")           ;
				}
			
				while (rs.next())
				{ 
				   Object [] fila = new Object[7];
				   fila[0] = rs.getString(1).trim();
				   fila[1] = rs.getString(2).trim();
				   fila[2] = rs.getString(3).trim();
				   fila[3] = rs.getString(4).trim();
				   fila[4] = rs.getString(5).trim();
				   fila[5] = rs.getString(6).trim();
				   fila[6] = rs.getString(7).trim();
				   modelo_exist_estab.addRow(fila); 
				   
				   exist_total+=Double.valueOf(rs.getString(2).trim());
				   venta_total+=Double.valueOf(rs.getString(3).trim());
				   
				}	
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en Cat_Cotizaciones_De_Un_Producto_En_Proveedores en la funcion Llenar_Tabla_Exist_Estab  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
}
	
	
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Cotizaciones_De_Un_Producto_En_Proveedores("").setVisible(true);
		}catch(Exception e){	}
	}
}
