package Cat_Contabilidad;

import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Cat_Reportes.Cat_Reportes_De_Control_Facturas_Y_XML_Recibidos;
import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.Connexion;
import Obj_Contabilidad.Obj_Proveedores;

import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Control_De_Facturas_Y_XML_De_Proveedores extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	DefaultTableModel modelo = new DefaultTableModel(null,
            new String[]{"Cod. P.", "Proveedor","Factura","Fecha Factura","Fecha Ult Mod","Modifico","XML","PDF",""}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
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
        	 	case 6 : return false;
        	 	case 7 : return false;
        	 	case 8 : return true;
        	 	
        	 } 				
 			return false;
 		}

	};
	
	
    JTable tabla = new JTable(modelo);
    JScrollPane scrollAsignado = new JScrollPane(tabla);
	JTextField txtFoliofacturaFiltro = new JTextField();
	JTextField txtProveedorFiltro = new JTextField();
	JTextField txtFacturaFiltro = new JTextField();
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	JTextField txtFolioFactura = new Componentes().text(new JTextField(), "Folio de La Factura", 25, "String");
//	JTextField txtFecha = new Componentes().text(new JTextField(), "Fecha Factura", 25, "String");
	JDateChooser txtFecha = new JDateChooser();
	
	JTextField txtFolioProveedor =new Componentes().text(new JTextField(), "Folio del Proveedor", 25, "String");
	JTextField txtProveedor = new Componentes().text(new JTextField(), "Proveedor", 200, "String");
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnCambProveedorr = new JButton("Cambiar de Proveedor ",new ImageIcon("imagen/buscar.png"));
	JButton btnReportes = new JButton("Reportes de Captura",new ImageIcon("imagen/orange-folder-saved-search-icone-8197-16.png"));
	JButton btnDeshacer = new JButton("Deshacer",new ImageIcon("imagen/deshacer16.png"));
	JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
	JButton btnEditar = new JButton("Editar",new ImageIcon("imagen/editara.png"));
	JButton btnNuevo = new JButton("Nuevo",new ImageIcon("imagen/Nuevo.png"));
	JButton btnRecibido = new JButton("Recibido",new ImageIcon("imagen/Aplicar.png"));
	
	JLabel lblTrue = new JLabel(new ImageIcon("imagen/Aplicar.png"));
	JLabel lblFalse = new JLabel(new ImageIcon("imagen/Aplicar.png"));
	
//	JButton btnSalir = new JButton("Salir",new ImageIcon("imagen/salir16.png"));

	
	
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	String folio_factura_editada=""; 
	String cod_prvrecibido = " ";
	String cod_factura_recibido  = "";
	String proveedor_recibido ="";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Control_De_Facturas_Y_XML_De_Proveedores(String folio, String proveedor){
		this.setTitle("Control De Facturas y XML De Proveedores");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/control_facturas_y_xml.png"));
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Control De Facturas y XML De Proveedores"));
		
		trsfiltro = new TableRowSorter(modelo); 
		tabla.setRowSorter(trsfiltro);
		
		txtFoliofacturaFiltro.setToolTipText("Filtro Por Folio de Factura del Proveedor");
		txtProveedorFiltro.setToolTipText("Filtro Por Proveedor");
		txtFacturaFiltro.setToolTipText("Factura");
		
		panel.add(txtFoliofacturaFiltro).setBounds(20,153,50,20);
		panel.add(txtProveedorFiltro).setBounds(70,153,205,20);
		panel.add(txtFacturaFiltro).setBounds(275,153,90,20);
		
		panel.add(getPanelTabla()).setBounds(20,175,720,520);
		panel.add(new JLabel("Cod. Proveedor:")).setBounds(20,30,100,20);
		panel.add(txtFolioProveedor).setBounds(105,30,270,20);
		txtFolioProveedor.setEditable(false);
		txtFolioProveedor.setText(folio);
		
		panel.add(new JLabel("Nom. Proveedor:")).setBounds(20,60,100,20);
		panel.add(txtProveedor).setBounds(105,60,270,20);
		txtProveedor.setEditable(false);
		txtProveedor.setText(proveedor);
		
		panel.add(new JLabel("Folio Factura:")).setBounds(390,30,80,20);
		panel.add(txtFolioFactura).setBounds(470,30,270,20);
		txtFolioFactura.setEditable(false);
				
		panel.add(new JLabel("Fecha Factura:")).setBounds(390,60,80,20);
		panel.add(txtFecha).setBounds(470,60,270,20);
		txtFecha.setEnabled(false);
		
		panel.add(btnCambProveedorr).setBounds(20,90,170,20);
		panel.add(btnReportes).setBounds(205,90,170,20);
		
		panel.add(btnNuevo).setBounds(470,90,130,20);
		panel.add(btnEditar).setBounds(610,90,130,20);
		
		panel.add(btnDeshacer).setBounds(470,120,130,20);
		panel.add(btnGuardar).setBounds(610,120,130,20);
		
		panel.add(btnRecibido).setBounds(610,150,130,20);

		btnEditar.setEnabled(false);
		btnGuardar.setEnabled(false);
		btnRecibido.setEnabled(false);
		
		btnCambProveedorr.setToolTipText("<F2> Tecla Directa");
		btnEditar.setToolTipText("<ENTER> Despues De Selecionar 1 de La Tabla");
		btnDeshacer.setToolTipText("<ESC> Tecla Directa");
		btnGuardar.setToolTipText("<CTRL+G> Tecla Directa");
		btnRecibido.setToolTipText("<CTRL+R> Guardar Como Recibido la Factura Selecionada");
		
		btnGuardar.addActionListener(guardar);
		btnCambProveedorr.addActionListener(Cambio_de_Proveedor);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		btnRecibido.addActionListener(OpAgregar);
		btnReportes.addActionListener(Reportes);


		txtFoliofacturaFiltro.addKeyListener(opFiltroFolio);
		txtProveedorFiltro.addKeyListener(opFiltroNombre);
		txtFacturaFiltro.addKeyListener(opFiltroFactura);
		tabla.addMouseListener(opTablaFiltroSeleccion);
		seleccionar_click(tabla);
		
		txtFolioFactura.addKeyListener(enterpasarafecha);

		
//      asigna el foco al JTextField folio_factura al arrancar la ventana y agrega al proveedor como nuevo
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){
                    btnNuevo.doClick();
                	txtFolioFactura.requestFocus();
             }
        });
		
//      limpia y habre filtro para cambio de proveedor
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
           KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "filtro");
        getRootPane().getActionMap().put("filtro", new AbstractAction(){
            public void actionPerformed(ActionEvent e)
            {   txtFolioFactura.setText("");
          	    txtFecha.setDate(null);
          	    btnCambProveedorr.doClick();
          	    }
        });
        
        		///recibido de factura  con control+R
            	getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R,Event.CTRL_MASK),"recibido");
                getRootPane().getActionMap().put("recibido", new AbstractAction(){
                 public void actionPerformed(ActionEvent e)
                  {                 	    btnRecibido.doClick();
                  	    }
                  });
             
             	///deshacer con escape
                getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
                  getRootPane().getActionMap().put("escape", new AbstractAction(){
                 public void actionPerformed(ActionEvent e)
                 {                 	    btnDeshacer.doClick();
               	    }
             });
             	///guardar con control+G
                 getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G,Event.CTRL_MASK),"guardar");
                   getRootPane().getActionMap().put("guardar", new AbstractAction(){
                      public void actionPerformed(ActionEvent e)
                      {                 	    btnGuardar.doClick();
                    	    }
                 });
               ///guardar con F12
	              getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "guardar");
	                  getRootPane().getActionMap().put("guardar", new AbstractAction(){
	                      public void actionPerformed(ActionEvent e)
	                      {                 	    btnGuardar.doClick();
		                    	    }
	                 });
                  
	            ///nuevo con F9
                 getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "nuevo");
                  getRootPane().getActionMap().put("nuevo", new AbstractAction(){
                      public void actionPerformed(ActionEvent e)
                      {                 	    btnNuevo.doClick();
	                    	    }
                 });
                  
                ///editar con F10
	              getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "editar");
	                  getRootPane().getActionMap().put("editar", new AbstractAction(){
	                      public void actionPerformed(ActionEvent e)
	                      {                 	    btnEditar.doClick();
		                    	    }
	                 });
                ///editar con Ctrl+E
	              getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E,Event.CTRL_MASK), "editar");
	                  getRootPane().getActionMap().put("editar", new AbstractAction(){
	                      public void actionPerformed(ActionEvent e)
	                      {                 	    btnEditar.doClick();
		                    	    }
	                 });
                  
              ///nuevo con control+N
                 getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N,Event.CTRL_MASK),"nuevo");
                   getRootPane().getActionMap().put("nuevo", new AbstractAction(){
                           public void actionPerformed(ActionEvent e)
                       {                 	    btnNuevo.doClick();
	                    	    }
                 });
             
        
        this.cont.add(panel);
		this.setSize(765,740);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		llamar_render();
	
	}
	
	public void llamar_render(){
		//		tipo de valor = imagen,chb,texto
//		tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
    
		tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("numerico","derecha","Arial","negrita",12)); 
    	
		tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12)); 
		tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","centro","Arial","normal",12));
		
		tabla.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("fecha","centro","Arial","normal",12));
		tabla.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer("fecha","centro","Arial","normal",12));
		
		tabla.getColumnModel().getColumn(5).setCellRenderer(new tablaRenderer("","","","",12));
		tabla.getColumnModel().getColumn(6).setCellRenderer(new tablaRenderer("imagen","","","",0));
		tabla.getColumnModel().getColumn(7).setCellRenderer(new tablaRenderer("imagen","","","",0));
		tabla.getColumnModel().getColumn(8).setCellRenderer(new tablaRenderer("chb","","","",0));
	}

	private JScrollPane getPanelTabla()	{		
	
		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
	tabla.getColumnModel().getColumn(0).setMaxWidth(50);
	tabla.getColumnModel().getColumn(0).setMinWidth(90);
	tabla.getColumnModel().getColumn(1).setMaxWidth(460);
	tabla.getColumnModel().getColumn(1).setMinWidth(205);
	tabla.getColumnModel().getColumn(2).setMaxWidth(350);
	tabla.getColumnModel().getColumn(2).setMinWidth(90);
	tabla.getColumnModel().getColumn(3).setMaxWidth(100);
	tabla.getColumnModel().getColumn(3).setMinWidth(60);
	tabla.getColumnModel().getColumn(4).setMaxWidth(100);
	tabla.getColumnModel().getColumn(4).setMinWidth(60);
	tabla.getColumnModel().getColumn(5).setMaxWidth(350);
	tabla.getColumnModel().getColumn(5).setMinWidth(130);
	
	tabla.getColumnModel().getColumn(6).setMaxWidth(30);
	tabla.getColumnModel().getColumn(6).setMinWidth(30);
	
	tabla.getColumnModel().getColumn(7).setMaxWidth(30);
	tabla.getColumnModel().getColumn(7).setMinWidth(30);
	
	tabla.getColumnModel().getColumn(8).setMaxWidth(17);
	tabla.getColumnModel().getColumn(8).setMinWidth(17);
	

		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			
			rs = s.executeQuery("SELECT cod_prv " +
										" ,proveedor" +
										" ,folio_factura" +
										" ,convert (varchar(20),[fecha_factura],103)as fecha_factura" +
										" ,convert(varchar(20),[fecha_modificacion],103)as fecha_modificacion"+
									    " ,(select nombre+' '+ap_paterno+' 'ap_materno from tb_empleado where folio=folio_empleado_modifico)as empleado_modifico" +
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
									    " where status=1 " +
									    " order by tb_control_de_facturas_y_xml.fecha_modificacion");
		
			while (rs.next())
			{ 
			   Object [] fila = new Object[9];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim(); 
			   fila[3] = rs.getString(4).trim(); 
			   fila[4] = rs.getString(5).trim(); 
			   fila[5] = rs.getString(6).trim(); 
			   fila[6] = rs.getString(7).trim();
			   fila[7] = rs.getString(8).trim();
			   fila[8] = "false";
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Cat_Control_De_Facturas_Y_XML_De_Proveedores en la funcion getPanelTabla  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
		 JScrollPane scrol = new JScrollPane(tabla);
	    return scrol; 
	}
	
	private void seleccionar_click(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==2){
					        	    	int fila = tabla.getSelectedRow();
					          	    	Object id = tabla.getValueAt(fila,2);
				
					        		    txtFolioFactura.setText(id.toString().trim());
										try {
											Date fecha = new SimpleDateFormat("dd/mm/yyyy").parse(tabla.getValueAt(fila,3).toString().trim());
											txtFecha.setDate(fecha);
										} catch (ParseException e1) {
											e1.printStackTrace();
										}
										txtFolioProveedor.setText(tabla.getValueAt(fila,0).toString().trim());
										txtProveedor.setText(tabla.getValueAt(fila,1).toString().trim());
										folio_factura_editada=id.toString().trim();
										txtFolioFactura.setEditable(false);
										txtFecha.setEnabled(false);
										btnEditar.setEnabled(true);
										chStatus.setSelected(true);
										btnEditar.requestFocus();
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
	public void actionPerformed(ActionEvent e){
		
	    if(validaCampos()!="") {
	    	                     JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			                   	 return;
	    } else{
			    Obj_Proveedores proveedor = new Obj_Proveedores().buscar(txtFolioFactura.getText().toUpperCase().trim(),txtFolioProveedor.getText().toUpperCase().trim()) ;
			    System.out.println("txtfactura"+txtFolioFactura.getText().toUpperCase().trim());
			    System.out.println("objeto proveedor:"+proveedor.getFolio_factura() );
							            
			                            if(proveedor.getFolio_factura().equals(txtFolioFactura.getText().toUpperCase().trim())){
			                            	System.out.println("Status"+proveedor.getStatus());
			                            	      if(proveedor.getStatus().equals(false)){
			                            		 	    JOptionPane.showMessageDialog(null, "La Factura con el Proveedor Selecionado Ya Existe con Status de Recibida \n  no puede haber dos facturas con el mismo folio del mismo proveedor", "Aviso", JOptionPane.WARNING_MESSAGE);
			                            	      }else{ 
												 	    JOptionPane.showMessageDialog(null, "La Factura con el Proveedor Selecionado Ya Existe con Status de No Recibida \n  En caso de Desear Modificarla Seleccionela de la tabla inferior y Editela", "Aviso", JOptionPane.WARNING_MESSAGE);
											      return; }
										}else{
											      if(folio_factura_editada!=""){
														if(JOptionPane.showConfirmDialog(null, "Esta Seguro de guardar los cambios en la factura:"+folio_factura_editada+" \n  del proveedor:"+txtFolioFactura.getText().toUpperCase().trim()) == 0){
																				proveedor.setFolio_factura(txtFolioFactura.getText().toUpperCase().trim());
																				proveedor.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
																				proveedor.setCod_prv(txtFolioProveedor.getText().toUpperCase().trim());
																				proveedor.setProveedor(txtProveedor.getText().toUpperCase().trim());
																				proveedor.setStatus(chStatus.isSelected());
																				
																	if(proveedor.actualizar(folio_factura_editada)){
																	        	while(tabla.getRowCount()>0){	
																			              modelo.removeRow(0);  }
															                    Object [][] lista_proveedores = new BuscarTablasModel().tabla_model_proveedores_guardados();;
															                    String[] fila = new String[9];
															                            for(int i=0; i<lista_proveedores.length; i++){
															                                    fila[0] = lista_proveedores[i][0]+"";
															                                    fila[1] = lista_proveedores[i][1]+"";
															                                    fila[2] = lista_proveedores[i][2]+"";
															                                    fila[3] = lista_proveedores[i][3]+"";
															                                    fila[4] = lista_proveedores[i][4]+"";
															                                    fila[5] = lista_proveedores[i][5]+"";
															                     			   	fila[6] = lista_proveedores[i][6]+"";
															                     			   	fila[7] = lista_proveedores[i][7]+"";
															                                    fila[8] = "false";
															                                    modelo.addRow(fila);}
															                      btnDeshacer.doClick(); 
																    	JOptionPane.showMessageDialog(null,"El registró se actualizó correctamente","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
																	}else{
																		JOptionPane.showMessageDialog(null,"Error al Actualizar la Factura","Avise al Administrador del Sistema", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
																		return;
																	}					
																	}else{
																	return;}
											       }else{
																proveedor.setFolio_factura(txtFolioFactura.getText().toUpperCase().trim());
						  									    proveedor.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
																proveedor.setCod_prv(txtFolioProveedor.getText().toUpperCase().trim());
											  				    proveedor.setProveedor(txtProveedor.getText().toUpperCase().trim());
									                   if(proveedor.guardar()){
														       while(tabla.getRowCount()>0){
															      modelo.removeRow(0);  }
														       Object [][] lista_proveedores = new BuscarTablasModel().tabla_model_proveedores_guardados();;
									 	                        String[] fila = new String[9];
												                for(int i=0; i<lista_proveedores.length; i++){
												                               fila[0] = lista_proveedores[i][0]+"";
												                               fila[1] = lista_proveedores[i][1]+"";
													                           fila[2] = lista_proveedores[i][2]+"";
													                           fila[3] = lista_proveedores[i][3]+"";
													                           fila[4] = lista_proveedores[i][4]+"";
													                           fila[5] = lista_proveedores[i][5]+"";
													                           fila[6] = lista_proveedores[i][6]+"";
											                     			   fila[7] = lista_proveedores[i][7]+"";
													                           fila[8] = "false";
													                       modelo.addRow(fila);}
																      txtFolioFactura.setText("");
																      txtFolioFactura.requestFocus();
                                                                      btnRecibido.setEnabled(false); 
                                                                      
																JOptionPane.showMessageDialog(null,"El registró se Guardo Correctamente","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
														}else{
														  JOptionPane.showMessageDialog(null,"Error al Guardar","Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
														return;}
											       }
										  	 }
			                         }
		                       }			
};
	
	private String validaCampos(){
		String error="";
		String fechanull = txtFecha.getDate()+"";
		if(txtFolioFactura.getText().equals("")) error+="Folio Factura\n";
		if(fechanull.equals("null")) 			error+= "Fecha de Factura\n";
		if(txtProveedor.getText().equals(""))		error+= "Proveedor\n";
		return error;
	};
	
 	ActionListener OpAgregar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			new Cat_Almacenar_XML().setVisible(true);
					
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFolioFactura.setText("");
			txtFolioFactura.setEditable(false);
			txtFecha.setDate(null);
			txtFecha.setEnabled(false);
			btnNuevo.setEnabled(true);
			btnGuardar.setEnabled(false);
			btnEditar.setEnabled(false);
			chStatus.setSelected(true);
			folio_factura_editada="";
            btnRecibido.setEnabled(false);
			
			while(tabla.getRowCount()>0){
				modelo.removeRow(0);  }
        Object [][] lista_proveedores = new BuscarTablasModel().tabla_model_proveedores_guardados();;
        String[] fila = new String[9];
                for(int i=0; i<lista_proveedores.length; i++){
                        fila[0] = lista_proveedores[i][0]+"";
                        fila[1] = lista_proveedores[i][1]+"";
                        fila[2] = lista_proveedores[i][2]+"";
                        fila[3] = lista_proveedores[i][3]+"";
                        fila[4] = lista_proveedores[i][4]+"";
                        fila[5] = lista_proveedores[i][5]+"";
                        fila[6] = lista_proveedores[i][6]+"";
         			   	fila[7] = lista_proveedores[i][7]+"";
                        fila[8] = "false";
                        modelo.addRow(fila);}
		}
	};
	
	ActionListener Cambio_de_Proveedor = new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    btnDeshacer.doClick();
		    dispose();
			new Cat_Control_De_Facturas_De_Proveedores().setVisible(true);
		}
	};
	
	ActionListener Reportes = new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    btnDeshacer.doClick();
			new Cat_Reportes_De_Control_Facturas_Y_XML_Recibidos().setVisible(true);
		}
	};
	
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			    txtFolioFactura.setText("");
			    txtFecha.setDate(null);
			    chStatus.setSelected(true);
			    txtFecha.setEnabled(true);
			   	txtFolioFactura.setEditable(true);
				txtFolioFactura.requestFocus();
				btnGuardar.setEnabled(true); 
				btnEditar.setEnabled(false);
				btnNuevo.setEnabled(false);
				folio_factura_editada="";
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			btnGuardar.setEnabled(true);
			txtFolioFactura.setEditable(true);
			txtFecha.setEnabled(true);
			btnEditar.setEnabled(false);
			btnNuevo.setEnabled(false);
			txtFolioFactura.requestFocus();
		}		
	};
	
	MouseListener opTablaFiltroSeleccion = new MouseListener() {
		public void mousePressed(MouseEvent e) {
			int fila = tabla.getSelectedRow();
			int columna = tabla.getSelectedColumn();
			
			if(columna==8){
				for(int i=0; i<=tabla.getRowCount()-1; i++){
					tabla.setValueAt(false, i, 8);
				}
					tabla.setValueAt(true, fila, columna);
					
					cod_prvrecibido = tabla.getValueAt(fila, 0).toString();
					cod_factura_recibido  = tabla.getValueAt(fila, 2).toString();
					proveedor_recibido = tabla.getValueAt(fila, 1).toString();
					
					btnRecibido.setEnabled(true);
				}
			}
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
	};
	
	KeyListener opFiltroFolio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFoliofacturaFiltro.getText(), 0));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}	
	    };
	
	KeyListener opFiltroNombre = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtProveedorFiltro.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	    };
	    
	KeyListener opFiltroFactura = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFacturaFiltro.getText().toUpperCase().trim(), 2));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	    };
	
	KeyListener enterpasarafecha = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				txtFecha.transferFocus();
			}
		}
	};

	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Control_De_Facturas_Y_XML_De_Proveedores("1253","ELDORADO").setVisible(true);
		}catch(Exception e){	}
	}
	
	
	String xml_pdf = "";
//	String pdf = "";
	public class Cat_Almacenar_XML extends JDialog{
		
		Container contenedor = getContentPane();
		JLayeredPane panelxml = new JLayeredPane();
		
		JLabel lblCodigoProveedor = new JLabel("");
		JLabel lblProveedor = new JLabel("");
		JLabel lblNoFactura = new JLabel("");
		JLabel lblFechaFactura = new JLabel("");
		
		JLabel lblXml = new JLabel("");
		JLabel lblCampoDeBusqueda = new JLabel("");
		
		String[] tipoDeArchivo = {"Seleccione Un Tipo de Archivo","XML","PDF"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbTipo = new JComboBox(tipoDeArchivo);
		
		JButton btnXML_PDF = new JButton("Buscar Archivo",new ImageIcon("imagen/Nuevo.png"));
		JButton btnGuardarFacXml = new JButton("Guardar Archivo",new ImageIcon("imagen/Aplicar.png"));
		
		public Cat_Almacenar_XML(){
			this.setModal(true);
			this.setTitle("Guardar Facturas y XML De Proveedores");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/control_facturas_y_xml.png"));
			blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
			panelxml.setBorder(BorderFactory.createTitledBorder(blackline,"Guardar Facturas y XML De Proveedores"));
			
			lblCampoDeBusqueda.setBorder(BorderFactory.createTitledBorder(blackline,"Buscar Archivo"));
			
			lblCodigoProveedor.setFont(new Font("arial",Font.BOLD,12));
			lblProveedor.setFont(new Font("arial",Font.BOLD,12));
			lblNoFactura.setFont(new Font("arial",Font.BOLD,12));
			lblFechaFactura.setFont(new Font("arial",Font.BOLD,12));
			
			lblXml.setFont(new Font("arial",Font.BOLD,14));
			lblXml.setForeground(Color.DARK_GRAY);
			
			panelxml.add(lblCodigoProveedor).setBounds(15,30,220,20);
			panelxml.add(lblProveedor).setBounds(15,60,570,20);
			panelxml.add(lblNoFactura).setBounds(15,90,220,20);
			panelxml.add(lblFechaFactura).setBounds(15,120,220,20);
			
			panelxml.add(lblCampoDeBusqueda).setBounds(5,155,585,100);
			panelxml.add(cmbTipo).setBounds(45,175,180,20);
			panelxml.add(btnXML_PDF).setBounds(380,175,180,35);
			panelxml.add(lblXml).setBounds(15,218,570,20);
			
			panelxml.add(btnGuardarFacXml).setBounds(380,260,180,35);
			
			lblCodigoProveedor.setText( "Cod. Proveedor:         "	   + ( tabla.getValueAt(tabla.getSelectedRow(), 0).toString().trim() ) 	);
			lblProveedor.setText(	    "Proveedor:                  " + ( tabla.getValueAt(tabla.getSelectedRow(), 1).toString().trim() ) 	);
			lblNoFactura.setText(	    "No. Factura:                " + ( tabla.getValueAt(tabla.getSelectedRow(), 2).toString().trim() ) 	);
			lblFechaFactura.setText(    "Fecha de Factura:     "	   + ( tabla.getValueAt(tabla.getSelectedRow(), 3).toString().trim() )	);
			
	        this.contenedor.add(panelxml);
	        
	        btnXML_PDF.addActionListener(opExaminarXML_PDF);
//	        btnPDF.addActionListener(opExaminarPDF);
	        btnGuardarFacXml.addActionListener(opGauardarXMLpdf);
	        
			this.setSize(600,335);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		
	ActionListener opGauardarXMLpdf = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			if(JOptionPane.showConfirmDialog(null,"De El Proveedor:"+proveedor_recibido+" La Factura:"+cod_factura_recibido+" \n Va Hacer Marcada Como Recibida: Confirmar?") == 0){
				
//				boolean proveedorr =
						new Obj_Proveedores().marcar_recibido_factura(cod_prvrecibido.trim(), cod_factura_recibido.trim(),cmbTipo.getSelectedItem().toString(),new File(xml_pdf));
				
				while(tabla.getRowCount()>0){
						modelo.removeRow(0);  }
			    Object [][] lista_proveedores = new BuscarTablasModel().tabla_model_proveedores_guardados();;
			    String[] fila = new String[9];
			            for(int i=0; i<lista_proveedores.length; i++){
			                    fila[0] = lista_proveedores[i][0]+"";
			                    fila[1] = lista_proveedores[i][1]+"";
			                    fila[2] = lista_proveedores[i][2]+"";
			                    fila[3] = lista_proveedores[i][3]+"";
			                    fila[4] = lista_proveedores[i][4]+"";
			                    fila[5] = lista_proveedores[i][5]+"";
			                    fila[6] = lista_proveedores[i][6]+"";
                 			   	fila[7] = lista_proveedores[i][7]+"";
			                    fila[8] = "false";
			                    modelo.addRow(fila);
			              }
			            
	            xml_pdf ="";
				JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
				dispose();
			}else{
				JOptionPane.showMessageDialog(null,"Se Cancelo La Marcacion De La Factura","Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}
		}
	};
		
		ActionListener opExaminarXML_PDF = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				   
					if(cmbTipo.getSelectedIndex()==0){
		                   	JOptionPane.showMessageDialog(null,"No se ha seleccionado el tipo de archivo que guardara","Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
		       				return;
	                }else{
	   				 
		                	JFileChooser elegir = new JFileChooser();
		                	int opcion = elegir.showOpenDialog(btnXML_PDF);
		            
			                 //Si presionamos el boton ABRIR en pathArchivo obtenemos el path del archivo
			                 if (opcion == JFileChooser.APPROVE_OPTION) {
			                 	
			                     String pathArchivo = elegir.getSelectedFile().getPath(); //Obtiene path del archivo
			                     String nombre = elegir.getSelectedFile().getName(); //obtiene nombre del archivo
			 				    	
			                         if(pathArchivo.toUpperCase().substring(pathArchivo.length()-3, pathArchivo.length()).equals(cmbTipo.getSelectedItem().toString().toUpperCase().substring(cmbTipo.getSelectedItem().toString().length()-3,cmbTipo.getSelectedItem().toString().length()))){
			                         	xml_pdf = pathArchivo;
			                         	lblXml.setText(nombre);
			                         }else{
			                         	xml_pdf = "";
			                         	lblXml.setText("");
			                         	JOptionPane.showMessageDialog(null,"El archivo seleccionado es de tipo ("+pathArchivo.substring(pathArchivo.length()-3, pathArchivo.length()).trim()+") \nCuando se requiere uno de tipo ("+cmbTipo.getSelectedItem().toString().toLowerCase()+")","Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			             				return;
			                         }  
			                 }
	                }
			}
		};
	}
}

//		ActionListener opExaminarPDF = new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
//				
//				 JFileChooser elegir = new JFileChooser();
//                 int opcion = elegir.showOpenDialog(btnXML);
//            
//                 //Si presionamos el boton ABRIR en pathArchivo obtenemos el path del archivo
//                 if (opcion == JFileChooser.APPROVE_OPTION) {
//                	 
//                     String pathArchivo = elegir.getSelectedFile().getPath(); //Obtiene path del archivo
//                     String nombre = elegir.getSelectedFile().getName(); //obtiene nombre del archivo
//                	 
//                     if(pathArchivo.substring(pathArchivo.length()-4, pathArchivo.length()).equals(".pdf")){
//                     	pdf = pathArchivo;
//                     	lblPdf.setText(nombre);
//                     }else{
//                     	pdf = "";
//                     	lblPdf.setText("");
//                     	JOptionPane.showMessageDialog(null,"El archivo seleccionado es de tipo ("+pathArchivo.substring(pathArchivo.length()-4, pathArchivo.length()).trim()+") \nCuando se requiere uno de tipo (.pdf)","Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
//         				return;
//                     }
//                 }
//			}
//		};
