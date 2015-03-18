package Cat_Contabilidad;

import java.awt.Component;
import java.awt.Container;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.Connexion;

import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Consulta_De_Vouchers_Para_Conciliacion extends JFrame{
	
	Object[][] Matriz_importacion_Vouchers_bms ;
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	Connexion con = new Connexion();
	
	DefaultTableModel modelo = new DefaultTableModel(null,
            new String[]{"Folio Ticket", "Cod.Autorizacion","Folio Compuesto","Monto","Establecimiento","Asignacion","Cajero","Fecha",""}
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
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	JDateChooser txtFecha = new JDateChooser();
	
	JTextField txtFolio_ticket =new Componentes().text(new JTextField(), "Folio del Ticket", 25, "String");
	JTextField txtProveedor = new Componentes().text(new JTextField(), "Proveedor", 200, "String");
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnBuscar = new JButton("Buscar Vouchers");
	JButton btnRecibido = new JButton("Mover A Conciliar");
	
	
	JButton btnDeshacer = new JButton("Deshacer");
	
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnEditar = new JButton("Editar");

	
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	String cod_factura_recibido  = "";
	String proveedor_recibido ="";
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Consulta_De_Vouchers_Para_Conciliacion(){
		this.setSize(1024,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Consulta de Vouchers Para Conciliacion");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Toolbox.png"));
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Consulta De Vouchers Para Conciliacion"));
		
		panel.add(new JLabel("A Partir De La Fecha:")).setBounds(10,20,140,20);
		panel.add(txtFecha).setBounds(115,20,100,20);
		panel.add(btnBuscar).setBounds(230,20,120,20);
		panel.add(btnRecibido).setBounds(640,20,120,20);
		
		btnBuscar.setEnabled(true);
		btnRecibido.setEnabled(false);
		
		btnBuscar.setToolTipText("<F9> Tecla Directa");
		btnRecibido.setToolTipText("<CTRL+R> Mover a Conciliar el Voucher Selecionado");
		
		btnBuscar.addActionListener(Buscar_Vouchers);
		btnRecibido.addActionListener(OpAgregar);
		        
		trsfiltro = new TableRowSorter(modelo); 
		tabla.setRowSorter(trsfiltro);
		
		txtFoliofacturaFiltro.setToolTipText("Filtro Por Folio de Factura del Proveedor");
		txtProveedorFiltro.setToolTipText("Filtro Por Proveedor");
		panel.add(txtFoliofacturaFiltro).setBounds(15,50,50,20);
		panel.add(txtProveedorFiltro).setBounds(110,50,205,20);
		panel.add(getPanelTabla()).setBounds(15,70,990,200);


//     autofiltros de la tabla
		txtFoliofacturaFiltro.addKeyListener(opFiltroFolio);
		txtProveedorFiltro.addKeyListener(opFiltroNombre);
		tabla.addMouseListener(opTablaFiltroSeleccion);
		seleccionar_click(tabla);
		

		
//      asigna el foco al JTextField fecha al arrancar la ventana y agrega al proveedor como nuevo
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){
                	txtFecha.requestFocus();
             }
        });
		
//      busca por fecha
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
           KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "filtro");
        getRootPane().getActionMap().put("filtro", new AbstractAction(){
            public void actionPerformed(ActionEvent e)
            {  
            	btnBuscar.doClick();
          	    txtFecha.setDate(null);
          	    
          	    }
        });
        ///guardar con control+G
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G,Event.CTRL_MASK),"guardar");
             getRootPane().getActionMap().put("guardar", new AbstractAction(){
                 public void actionPerformed(ActionEvent e)
                 {                 	    btnGuardar.doClick();
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
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                     KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
                  getRootPane().getActionMap().put("escape", new AbstractAction(){
                      public void actionPerformed(ActionEvent e)
                      {                 	    btnDeshacer.doClick();
                    	    }
                  });
        
        this.cont.add(panel);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JScrollPane getPanelTabla()	{	
		
	tabla.getColumnModel().getColumn(0).setMinWidth(80);
	tabla.getColumnModel().getColumn(0).setMaxWidth(200);
	tabla.getColumnModel().getColumn(1).setMinWidth(80);
	tabla.getColumnModel().getColumn(1).setMaxWidth(200);
	tabla.getColumnModel().getColumn(2).setMinWidth(80);
	tabla.getColumnModel().getColumn(2).setMaxWidth(200);
	tabla.getColumnModel().getColumn(3).setMinWidth(80);
	tabla.getColumnModel().getColumn(3).setMaxWidth(200);
	tabla.getColumnModel().getColumn(4).setMinWidth(110);
	tabla.getColumnModel().getColumn(4).setMaxWidth(200);
	tabla.getColumnModel().getColumn(5).setMinWidth(80);
	tabla.getColumnModel().getColumn(5).setMaxWidth(200);
	tabla.getColumnModel().getColumn(6).setMinWidth(200);
	tabla.getColumnModel().getColumn(6).setMaxWidth(350);
	tabla.getColumnModel().getColumn(7).setMinWidth(130);
	tabla.getColumnModel().getColumn(7).setMaxWidth(200);
	tabla.getColumnModel().getColumn(8).setMaxWidth(17);
	tabla.getColumnModel().getColumn(8).setMinWidth(17);
	
	TableCellRenderer render = new TableCellRenderer() { 
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
		boolean hasFocus, int row, int column) { 
			
			Component componente = null;
			
			switch(column){
				case 0: 
					componente = new JLabel(value == null? "": value.toString());
					if(row %2 == 0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					if(Boolean.parseBoolean(modelo.getValueAt(row,2).toString())){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
					break;
				case 1: 
					componente = new JLabel(value == null? "": value.toString());
					if(row %2 == 0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					if(Boolean.parseBoolean(modelo.getValueAt(row,2).toString())){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
					break;
				case 2: 
					componente = new JLabel(value == null? "": value.toString());
					if(row %2 == 0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					if(Boolean.parseBoolean(modelo.getValueAt(row,2).toString())){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
					break;
				case 3: 
					componente = new JLabel(value == null? "": value.toString());
					if(row %2 == 0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					if(Boolean.parseBoolean(modelo.getValueAt(row,2).toString())){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
					break;
				case 4: 
					componente = new JLabel(value == null? "": value.toString());
					if(row %2 == 0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					if(Boolean.parseBoolean(modelo.getValueAt(row,2).toString())){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
					break;
				case 5: 
					componente = new JLabel(value == null? "": value.toString());
					if(row %2 == 0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					if(Boolean.parseBoolean(modelo.getValueAt(row,2).toString())){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
					break;
				case 6: 
					componente = new JLabel(value == null? "": value.toString());
					if(row %2 == 0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					if(Boolean.parseBoolean(modelo.getValueAt(row,2).toString())){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
					break;
				case 7: 
					componente = new JLabel(value == null? "": value.toString());
					if(row %2 == 0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					if(Boolean.parseBoolean(modelo.getValueAt(row,2).toString())){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
					break;
				case 8: 
					componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
					if(row%2==0){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(177,177,177));	
					}
					if(Boolean.parseBoolean(modelo.getValueAt(row,2).toString())){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					((AbstractButton) componente).setHorizontalAlignment(SwingConstants.CENTER);
					break;
			}
			return componente;
		} 
	}; 
	
		            	tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(2).setCellRenderer(render);
						tabla.getColumnModel().getColumn(3).setCellRenderer(render);
						tabla.getColumnModel().getColumn(4).setCellRenderer(render);
						tabla.getColumnModel().getColumn(5).setCellRenderer(render);
						tabla.getColumnModel().getColumn(6).setCellRenderer(render);
						tabla.getColumnModel().getColumn(7).setCellRenderer(render);
						tabla.getColumnModel().getColumn(8).setCellRenderer(render);
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("SELECT folio_ticket,codigo_autorizacion,folio_compuesto,monto,establecimiento,asignacion,cajero,fecha  FROM tb_vouchers_ticrem where status_conciliacion=0 ");
			while (rs.next())
			{ 
			   String [] fila = new String[9];
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
	
	
	

	
	
	ActionListener guardar = new ActionListener(){
	public void actionPerformed(ActionEvent e){
		
//	    else{
//			    Obj_Proveedores proveedor = new Obj_Proveedores().buscar(txtFolioFactura.getText().toUpperCase().trim(),txtFolioProveedor.getText().toUpperCase().trim()) ;
//			    System.out.println("txtfactura"+txtFolioFactura.getText().toUpperCase().trim());
//			    System.out.println("objeto proveedor:"+proveedor.getFolio_factura() );
//							            
//			                            if(proveedor.getFolio_factura().equals(txtFolioFactura.getText().toUpperCase().trim())){
//			                            	System.out.println("Status"+proveedor.getStatus());
//			                            	      if(proveedor.getStatus().equals(false)){
//			                            		 	    JOptionPane.showMessageDialog(null, "La Factura con el Proveedor Selecionado Ya Existe con Status de Recibida \n  no puede haber dos facturas con el mismo folio del mismo proveedor", "Aviso", JOptionPane.WARNING_MESSAGE);
//			                            	      }else{ 
//												 	    JOptionPane.showMessageDialog(null, "La Factura con el Proveedor Selecionado Ya Existe con Status de No Recibida \n  En caso de Desear Modificarla Seleccionela de la tabla inferior y Editela", "Aviso", JOptionPane.WARNING_MESSAGE);
//											      return; }
//										}else{
//											      if(folio_factura_editada!=""){
//														if(JOptionPane.showConfirmDialog(null, "Esta Seguro de guardar los cambios en la factura:"+folio_factura_editada+" \n  del proveedor:"+txtFolioFactura.getText().toUpperCase().trim()) == 0){
//																				proveedor.setFolio_factura(txtFolioFactura.getText().toUpperCase().trim());
//																				proveedor.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
//																				proveedor.setCod_prv(txtFolioProveedor.getText().toUpperCase().trim());
//																				proveedor.setProveedor(txtProveedor.getText().toUpperCase().trim());
//																				proveedor.setStatus(chStatus.isSelected());
//																				
//																	if(proveedor.actualizar(folio_factura_editada)){
//																	        	while(tabla.getRowCount()>0){	
//																			              modelo.removeRow(0);  }
//															                    Object [][] lista_proveedores = new BuscarTablasModel().tabla_model_proveedores_guardados();;
//															                    String[] fila = new String[7];
//															                            for(int i=0; i<lista_proveedores.length; i++){
//															                                    fila[0] = lista_proveedores[i][0]+"";
//															                                    fila[1] = lista_proveedores[i][1]+"";
//															                                    fila[2] = lista_proveedores[i][2]+"";
//															                                    fila[3] = lista_proveedores[i][3]+"";
//															                                    fila[4] = lista_proveedores[i][4]+"";
//															                                    fila[5] = lista_proveedores[i][5]+"";
//															                                    fila[6] = "false";
//															                                    modelo.addRow(fila);}
//															                      btnDeshacer.doClick(); 
//																    	JOptionPane.showMessageDialog(null,"El registró se actualizó correctamente","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
//																	}else{
//																		JOptionPane.showMessageDialog(null,"Error al Actualizar la Factura","Avise al Administrador del Sistema", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
//																		return;
//																	}					
//																	}else{
//																	return;}
//											       }else{
//																proveedor.setFolio_factura(txtFolioFactura.getText().toUpperCase().trim());
//						  									    proveedor.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate()));
//																proveedor.setCod_prv(txtFolioProveedor.getText().toUpperCase().trim());
//											  				    proveedor.setProveedor(txtProveedor.getText().toUpperCase().trim());
//									                   if(proveedor.guardar()){
//														       while(tabla.getRowCount()>0){
//															      modelo.removeRow(0);  }
//														       Object [][] lista_proveedores = new BuscarTablasModel().tabla_model_proveedores_guardados();;
//									 	                        String[] fila = new String[7];
//												                for(int i=0; i<lista_proveedores.length; i++){
//												                               fila[0] = lista_proveedores[i][0]+"";
//												                               fila[1] = lista_proveedores[i][1]+"";
//													                           fila[2] = lista_proveedores[i][2]+"";
//													                           fila[3] = lista_proveedores[i][3]+"";
//													                           fila[4] = lista_proveedores[i][4]+"";
//													                           fila[5] = lista_proveedores[i][5]+"";
//													                           fila[6] = "false";
//													                       modelo.addRow(fila);}
//																      txtFolioFactura.setText("");
//																      txtFolioFactura.requestFocus();
//                                                                      btnRecibido.setEnabled(false); 
//                                                                      
//																JOptionPane.showMessageDialog(null,"El registró se Guardo Correctamente","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
//														}else{
//														  JOptionPane.showMessageDialog(null,"Error al Guardar","Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
//														return;}
//											       }
//										  	 }
//			                         }
		                       }			
};
	

 	ActionListener OpAgregar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if(JOptionPane.showConfirmDialog(null,"De El Proveedor:"+proveedor_recibido+" La Factura:"+cod_factura_recibido+" \n Va Hacer Marcada Como Recibida: Confirmar?") == 0){
//				boolean proveedorr = new Obj_Proveedores().marcar_recibido_factura(cod_prvrecibido.trim(), cod_factura_recibido.trim());
				
			while(tabla.getRowCount()>0){
					modelo.removeRow(0);  }
            Object [][] lista_proveedores = new BuscarTablasModel().tabla_model_proveedores_guardados();;
            String[] fila = new String[9];
                    for(int i=0; i<lista_proveedores.length; i++){
                            fila[0] = lista_proveedores[i][1]+"";
                            fila[1] = lista_proveedores[i][2]+"";
                            fila[2] = lista_proveedores[i][3]+"";
                            fila[3] = lista_proveedores[i][4]+"";
                            fila[4] = lista_proveedores[i][5]+"";
                            fila[5] = lista_proveedores[i][6]+"";
                            fila[6] = lista_proveedores[i][7]+"";
                            fila[7] = lista_proveedores[i][8]+"";
                            fila[8] = "false";
                            modelo.addRow(fila);}
			JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
			}else{
				JOptionPane.showMessageDialog(null,"Se Cancelo La Marcacion De La Factura","Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}					
		}
	};
	
	/////////OBTIENE DATOS DESDE IZAGAR PARA GUARDAR EN SCOI AUTO
   	public Object[][] ObtenerValoresTablaGuardadoAutoVouchers(String fechaIn){
   		
		String todos = "exec sp_Relacion_de_Vouchers_A_Partir_De_Una_Fecha '" +fechaIn+"'";
		Statement s;
		ResultSet rs2;

		try {
			s = new Connexion().conexion_IZAGAR().createStatement();
			rs2 = s.executeQuery(todos);
			Matriz_importacion_Vouchers_bms = new Object[LlenadoFilasVoucherDesdeIZAGAR(todos)][8];
			int i=0;
			while(rs2.next()){
				Matriz_importacion_Vouchers_bms[i][0] = "   "+rs2.getString(1).trim();
				Matriz_importacion_Vouchers_bms[i][1] = "   "+rs2.getString(2).trim();
				Matriz_importacion_Vouchers_bms[i][2] = "   "+rs2.getString(3).trim();
				Matriz_importacion_Vouchers_bms[i][3] = "   "+rs2.getString(4).trim();
				Matriz_importacion_Vouchers_bms[i][4] = "   "+rs2.getString(5).trim();
				Matriz_importacion_Vouchers_bms[i][5] = "   "+rs2.getString(6).trim();
				Matriz_importacion_Vouchers_bms[i][6] = "   "+rs2.getString(7).trim();
				Matriz_importacion_Vouchers_bms[i][7] = "   "+rs2.getString(8).trim();
								i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Cat_Consulta_De_Vouchers_Para_Conciliacion  en la funcion [ ObtenerValoresTablaGuardadoAutoVouchers ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
	    return Matriz_importacion_Vouchers_bms; 
	}
  public int LlenadoFilasVoucherDesdeIZAGAR(String qry){
		int filas=0;
		Statement stmt = null;
		try {stmt = new Connexion().conexion_IZAGAR().createStatement();
			ResultSet rs2 = stmt.executeQuery(qry);
			while(rs2.next()){filas++;}
		} catch (SQLException e1) {	e1.printStackTrace();}
		return filas;
	}
  
  
  ////CARGA LAS AUTORIZACIONES BANCARIASS DE LOS OTROS SERVIDORES AL PRINCIPAL
	public void Actualizar_Autorizaciones_Bancarias_de_Otros_Servidores(){
		String query = "exec Actualizar_Autorizaciones_Bancarias";
		PreparedStatement pstmt = null;
		Connection con = new Connexion().conexion_IZAGAR();
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			JOptionPane.showMessageDialog(null, "Error en Cat_Consulta_De_Vouchers_Para_Conciliacion en la funcion Actualizar_Autorizaciones_Bancarias_de_Otros_Servidores  procedimiento almacenado Actualizar_Autorizaciones_Bancarias SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);

			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Consulta_De_Vouchers_Para_Conciliacion  en la funcion Actualizar_Autorizaciones_Bancarias_de_Otros_Servidores  procedimiento almacenado Actualizar_Autorizaciones_Bancarias SQLException: "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error en Cat_Consulta_De_Vouchers_Para_Conciliacionr  en la funcion Actualizar_Autorizaciones_Bancarias_de_Otros_Servidores  procedimiento almacenado Actualizar_Autorizaciones_Bancarias SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}		

	}
  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	ActionListener Buscar_Vouchers = new ActionListener(){
		public void actionPerformed(ActionEvent e){
		String fecha_inical = txtFecha.getDate()+"";
			if(fecha_inical.equals("null")){
				JOptionPane.showMessageDialog(null, "Ingrese Una Fecha Inicial Para Poder Buscar","Mensaje",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				
				try {	
					String fechaIn = new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate());
					Actualizar_Autorizaciones_Bancarias_de_Otros_Servidores();
	           //////tabla asegurarse que este limpia y guardado automatico//////////////
              //////limpiar tabla
					while(tabla.getRowCount()>0){	modelo.removeRow(0);	}
              //////llenar arreglo desde funcion
					Object[][] getTablaauto_guardado_voucher = ObtenerValoresTablaGuardadoAutoVouchers(fechaIn);
					Object[] fila = new Object[9];
              ////// llenar tablanomina
			         for(int i=0; i<getTablaauto_guardado_voucher.length; i++){
			                 fila[0] = getTablaauto_guardado_voucher[i][0]+"";
			                 fila[1] = getTablaauto_guardado_voucher[i][1]+"";
			                 fila[2] = getTablaauto_guardado_voucher[i][2]+"";
			                 fila[3] = getTablaauto_guardado_voucher[i][3]+"";
			                 fila[4] = getTablaauto_guardado_voucher[i][4]+"";
			                 fila[5] = getTablaauto_guardado_voucher[i][5]+"";
			                 fila[6] = getTablaauto_guardado_voucher[i][6]+"";
			                 fila[7] = getTablaauto_guardado_voucher[i][7]+"";
			                 fila[8] = "true";
			                 modelo.addRow(fila); }
			         
			         guarda_auto_vouchers_desde_fecha();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Consulta_De_Vouchers_Para_Conciliacion en la funcion Buscar_Voucher en else despues de la comparacion de la fecha  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	};
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	///////PREPARANDO DATOS PARA GUARDAR TABLA AUTO DE LOS VOUCHERS
	private Object[][] tabla_guardar_vouchers_desde_fecha_auto(){
		String[][] matriz = new String[tabla.getRowCount()][8];
		for(int i=0; i<tabla.getRowCount(); i++){
			
				matriz[i][0] = modelo.getValueAt(i,0).toString().trim();
				matriz[i][1] = modelo.getValueAt(i,1).toString().trim();
				matriz[i][2] = modelo.getValueAt(i,2).toString().trim();
				matriz[i][3] = modelo.getValueAt(i,3).toString().trim();
				matriz[i][4] = modelo.getValueAt(i,4).toString().trim();
				matriz[i][5] = modelo.getValueAt(i,5).toString().trim();
				matriz[i][6] = modelo.getValueAt(i,6).toString().trim();
				matriz[i][7] = modelo.getValueAt(i,7).toString().trim();
		}
		return matriz;
	}
		
	 //GUARDADO AUTOMATICO DE VOUCHERS OBTENIDOS DE  LA OTRA BASE DE DATOS//  
	  public void guarda_auto_vouchers_desde_fecha(){
				if(tabla.isEditing()){
		 			tabla.getCellEditor().stopCellEditing();
				}
				if(guardar_vouchers_de_la_fecha(tabla_guardar_vouchers_desde_fecha_auto())){
					            
										while(tabla.getRowCount()>0){	modelo.removeRow(0);	}
					              //////llenar arreglo desde funcion
										String fechaIn = new SimpleDateFormat("dd/MM/yyyy").format(txtFecha.getDate());
										Object[][] getTablaauto_guardado_voucher = ObtenerValoresTablaGuardadoAutoVouchers(fechaIn);
										Object[] fila = new Object[9];
					              ////// llenar tabla
								         for(int i=0; i<getTablaauto_guardado_voucher.length; i++){
								                 fila[0] = getTablaauto_guardado_voucher[i][0]+"";
								                 fila[1] = getTablaauto_guardado_voucher[i][1]+"";
								                 fila[2] = getTablaauto_guardado_voucher[i][2]+"";
								                 fila[3] = getTablaauto_guardado_voucher[i][3]+"";
								                 fila[4] = getTablaauto_guardado_voucher[i][4]+"";
								                 fila[5] = getTablaauto_guardado_voucher[i][5]+"";
								                 fila[6] = getTablaauto_guardado_voucher[i][6]+"";
								                 fila[7] = getTablaauto_guardado_voucher[i][7]+"";
								                 fila[8] = "false";
								                 modelo.addRow(fila); }
								        
//						        RefreshTablas(folio_nomina);
							}else{
								JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla vouchers desde la fecha: /n automatico","Error",JOptionPane.ERROR_MESSAGE);
								return;
							}
		};
		///coneccion para guardado
		public boolean guardar_vouchers_de_la_fecha(Object[][] tabla){
			
			return new Conexiones_SQL.GuardarTablasModel().Guarda_tabla_de_vouchers_cargados_desde_fecha_automatico(tabla);
		};
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		private void seleccionar_click(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
				@SuppressWarnings("unused")
				public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount()==2){
						        	    	int fila = tabla.getSelectedRow();
						          	    	Object id = tabla.getValueAt(fila,2);
					
											try {
												Date fecha = new SimpleDateFormat("dd/mm/yyyy").parse(tabla.getValueAt(fila,3).toString().trim());
												txtFecha.setDate(fecha);
											} catch (ParseException e1) {
												e1.printStackTrace();
											}
											txtFolio_ticket.setText(tabla.getValueAt(fila,0).toString().trim());
//											txtProveedor.setText(tabla.getValueAt(fila,1).toString().trim());
											txtFecha.setEnabled(false);
											btnEditar.setEnabled(true);
											chStatus.setSelected(true);
											btnEditar.requestFocus();
		        	}
		        }
	        });
	    }		
			

	MouseListener opTablaFiltroSeleccion = new MouseListener() {
		public void mousePressed(MouseEvent e) {
			int fila = tabla.getSelectedRow();
			int columna = tabla.getSelectedColumn();
			if(columna==6){
				for(int i=0; i<=tabla.getRowCount()-1; i++){
					tabla.setValueAt(false, i, 8);
				}
				tabla.setValueAt(true, fila, columna);

				cod_factura_recibido  =(String) tabla.getValueAt(fila, 2);
				proveedor_recibido =(String) tabla.getValueAt(fila, 1);
				
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
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFoliofacturaFiltro.getText(), 2));
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
	
//	KeyListener enterpasarafecha = new KeyListener() {
//		public void keyTyped(KeyEvent e){}
//		public void keyReleased(KeyEvent e) {}
//		public void keyPressed(KeyEvent e) {
//			if(e.getKeyCode()==KeyEvent.VK_ENTER){
//				txtFecha.transferFocus();
//			}
//		}
//	};

	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Consulta_De_Vouchers_Para_Conciliacion().setVisible(true);
		}catch(Exception e){	}
	}
}