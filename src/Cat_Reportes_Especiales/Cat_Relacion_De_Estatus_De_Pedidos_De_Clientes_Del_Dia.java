package Cat_Reportes_Especiales;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import Conexiones_SQL.Connexion;


@SuppressWarnings("serial")
public class Cat_Relacion_De_Estatus_De_Pedidos_De_Clientes_Del_Dia extends JFrame{
	
	Object[][] Matriz_pedidos_ctes ;
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	Connexion con = new Connexion();
	Runtime R = Runtime.getRuntime();
	
	
	DefaultTableModel modelo = new DefaultTableModel(null,new String[]{"Establecimiento", "Pedido","Fecha Elaboracion","Fecha Surtido","Tiempo","Notas del Pedido","Cajero"}
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
	
    JTable tabla = new JTable(modelo);
    JScrollPane scrollAsignado = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	JCheckBox chbActivar_Avisos = new JCheckBox("Ventana De Avisos");
	
	JTextField txtEstablecimiento = new JTextField();
	JTextField txtFolio = new JTextField();
	JTextField txtFechaPedido = new JTextField();
	
	JButton btnBuscar = new JButton(new ImageIcon("imagen/refrescar-volver-a-cargar-las-flechas-icono-4094-32.png"));;

	Border blackline, etched, raisedbevel, loweredbevel, empty;
	
    int aux =0;
    int Existen_pedidos_nuevos=0;
    int SeActivoSonido=0;
    
    boolean cerrarhilo = false;
	
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Relacion_De_Estatus_De_Pedidos_De_Clientes_Del_Dia(){
		this.setSize(1024,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Consulta de Pedidos De Clientes");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/lista-icono-7220-32.png"));
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Estatus De Pedidos Clientes"));
		this.cont.add(panel);
		this.addWindowListener(op_cerrar);

		btnBuscar.setEnabled(true);
		btnBuscar.setToolTipText("<F5> Tecla Directa");
		btnBuscar.addActionListener(Buscar_Cambios);
		
		chbActivar_Avisos.setSelected(true);
		
		trsfiltro = new TableRowSorter(modelo); 
		tabla.setRowSorter(trsfiltro);
		txtEstablecimiento.setToolTipText("Filtro Por Nombre De Establecimiento");
		txtFolio.setToolTipText("Filtro Por Folio Del Pedido");
		txtFechaPedido.setToolTipText("Filtro Por Fecha Del Pedido");

		txtEstablecimiento.addKeyListener(opFiltroEstablecimiento);
		txtFolio.addKeyListener(opFiltroFolio);
		txtFechaPedido.addKeyListener(opFiltroFechaPedido);
		
		Hilo_1_Minuto();
		
		panel.add(chbActivar_Avisos).setBounds(420,20,150,20);
		panel.add(btnBuscar).setBounds(973,8,32,32);
		panel.add(txtEstablecimiento).setBounds(15,20,130,20);
		panel.add(txtFolio).setBounds(145,20,50,20);
		panel.add(txtFechaPedido).setBounds(195,20,120,20);
		panel.add(getPanelTabla()).setBounds(15,40,990,650);
             
//     Buscar Con F5
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                     KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "Actualizar");
                  getRootPane().getActionMap().put("Actualizar", new AbstractAction(){
                      public void actionPerformed(ActionEvent e)
                      {        	    btnBuscar.doClick();          	    }
                  });
//     asigna el foco al JTextField fecha al arrancar la ventana
                  this.addWindowListener(new WindowAdapter() {
                          public void windowOpened( WindowEvent e ){
                          	txtFolio.requestFocus();
                       }
                  });
	}
	//////////////////////////////////////////////////////////
	
	TableCellRenderer render = new TableCellRenderer() { 
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
		boolean hasFocus, int row, int column) { 
          		Component componente = null;
					componente = new JLabel(value == null? "": value.toString());
//					if(row %2 == 0){
//						((JComponent) componente).setOpaque(true); 
//						componente.setBackground(new java.awt.Color(177,177,177));	
//					}
					
					if(table.getSelectedRow() == row){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(186,143,73));
					}
					
					if(modelo.getValueAt(row, 3).toString().trim().equals("NO SURTIDO")){
						((JComponent) componente).setOpaque(true); 
						componente.setBackground(new java.awt.Color(252,1,39));
					}
					((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
			return componente;
		} 
	}; 
	
	private JScrollPane getPanelTabla()	{	
		
	tabla.getTableHeader().setReorderingAllowed(false) ;
	tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	tabla.getColumnModel().getColumn(0).setMinWidth(130);
	tabla.getColumnModel().getColumn(0).setMaxWidth(130);
	tabla.getColumnModel().getColumn(1).setMinWidth(50);
	tabla.getColumnModel().getColumn(1).setMaxWidth(50);
	tabla.getColumnModel().getColumn(2).setMinWidth(115);
	tabla.getColumnModel().getColumn(2).setMaxWidth(115);
	tabla.getColumnModel().getColumn(3).setMinWidth(115);
	tabla.getColumnModel().getColumn(3).setMaxWidth(115);
	tabla.getColumnModel().getColumn(4).setMinWidth(50);
	tabla.getColumnModel().getColumn(4).setMaxWidth(50);
	tabla.getColumnModel().getColumn(5).setMinWidth(400);
	tabla.getColumnModel().getColumn(5).setMaxWidth(1200);
	tabla.getColumnModel().getColumn(6).setMinWidth(200);
	tabla.getColumnModel().getColumn(6).setMaxWidth(800);

	tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
	tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
	tabla.getColumnModel().getColumn(2).setCellRenderer(render);
	tabla.getColumnModel().getColumn(3).setCellRenderer(render);
	tabla.getColumnModel().getColumn(4).setCellRenderer(render);
	tabla.getColumnModel().getColumn(5).setCellRenderer(render);
	tabla.getColumnModel().getColumn(6).setCellRenderer(render);

		Statement s;
		ResultSet rs;
		try {
			s = con.conexion_IZAGAR().createStatement();
			rs = s.executeQuery(" select establecimientos.nombre as establecimiento " +
					" ,pedcte.folio as pedido    " +
					" ,convert(varchar(50),pedcte.fecha,103)+' '+convert(varchar(50),pedcte.fecha,108)as elaboracion_pedido " +
					" ,isnull(convert(varchar(50),facremtick.fecha,103)+' '+convert(varchar(50),facremtick.fecha,108),'NO SURTIDO')as surtido_pedido " +
					" ,isnull(DATEDIFF(minute,pedcte.fecha,facremtick.fecha),(datediff(minute,pedcte.fecha,getdate())))as Tiempo_Para_Surtido " +
					" ,pedcte.notas " +
					" ,isnull(cajeros.nombre,'NO SURTIDO') as cajero" +
					" FROM pedcte" +
					" left outer join facremtick on facremtick.folio_origen=pedcte.folio" +
					" left outer join cajeros on cajeros.cajero=facremtick.cajero" +
					" left outer join establecimientos on establecimientos.cod_estab=pedcte.cod_estab " +
					" where pedcte.fecha_elaboracion>convert(varchar(20),getdate()-1,103)+' 00:00:000' and pedcte.cod_estab in(20,15) " +
					" order by pedcte.cod_estab desc,pedcte.fecha desc ");
			while (rs.next())
			{ 
			   String [] fila = new String[7];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim(); 
			   fila[3] = rs.getString(4).trim(); 
			   fila[4] = rs.getString(5).trim(); 
			   fila[5] = rs.getString(6).trim(); 
			   fila[6] = rs.getString(7).trim(); 

			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Cat_Control_De_Facturas_Y_XML_De_Proveedores en la funcion getPanelTabla  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
		 JScrollPane scrol = new JScrollPane(tabla);
	    return scrol; 
	}
	
	KeyListener opFiltroEstablecimiento = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtEstablecimiento.getText(), 0));
			
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}	
	    };
	
	KeyListener opFiltroFolio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	    };
	   
	KeyListener opFiltroFechaPedido = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFechaPedido.getText().toUpperCase().trim(), 2));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		    };
		    
		    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
////DISPOSE MATA EL HILO Y DETIENE EL SONIDO SI SONO ALGUNA VEZ		    
 AudioClip sonido;
WindowListener op_cerrar = new WindowListener() {
				public void windowOpened(WindowEvent e) {}
				public void windowIconified(WindowEvent e) {}
				public void windowDeiconified(WindowEvent e) {}
				public void windowDeactivated(WindowEvent e) {}
				@SuppressWarnings({ "unchecked" })
				public void windowClosing(WindowEvent e) {
						trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
						trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
						trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
						
						txtEstablecimiento.setText("");
						txtFolio.setText("");
						txtFechaPedido.setText("");
						
					 if(SeActivoSonido!=0){
						 	sonido.stop();}
							cerrarhilo=true;
							dispose();
				}
				public void windowClosed(WindowEvent e) {}
				public void windowActivated(WindowEvent e) {}
			};
			
			
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////ACTUALIZAR

	ActionListener Buscar_Cambios = new ActionListener(){
		@SuppressWarnings({ "unchecked", "deprecation" })
		public void actionPerformed(ActionEvent e){
				try {
			
					Existen_pedidos_nuevos = ObtenerPedidoNuevo();
			   
				if(Existen_pedidos_nuevos != aux){
		      //////Limpiar	Filtros
					trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
					trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
					trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
					
					txtEstablecimiento.setText("");
					txtFolio.setText("");
					txtFechaPedido.setText("");
              //////Limpiar Tabla
					while(tabla.getRowCount()>0){	modelo.removeRow(0);	}
              //////llenar arreglo desde funcion
					Object[][] getTabla = ObtenerValoresPedClientes();
					Object[] fila = new Object[7];
              ////// llenar tabla
			         for(int i=0; i<getTabla.length; i++){
			                 fila[0] = getTabla[i][0]+"";
			                 fila[1] = getTabla[i][1]+"";
			                 fila[2] = getTabla[i][2]+"";
			                 fila[3] = getTabla[i][3]+"";
			                 fila[4] = getTabla[i][4]+"";
			                 fila[5] = getTabla[i][5]+"";
			                 fila[6] = getTabla[i][6]+"";
			                 modelo.addRow(fila); }
			         
			     	tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
			    	tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
			    	tabla.getColumnModel().getColumn(2).setCellRenderer(render);
			    	tabla.getColumnModel().getColumn(3).setCellRenderer(render);
			    	tabla.getColumnModel().getColumn(4).setCellRenderer(render);
			    	tabla.getColumnModel().getColumn(5).setCellRenderer(render);
			    	tabla.getColumnModel().getColumn(6).setCellRenderer(render);
			    	
			           txtFolio.requestFocus();
			         
						if(chbActivar_Avisos.isSelected()){
							   File f=new File("M:\\SISTEMA DE CONTROL OPERATIVO IZAGAR\\SCOI\\voz\\Nuevo_Pedido.wav");//archivo de audio
				        	    URL u=f.toURL();//lo convertimos a url
				        	    sonido=JApplet.newAudioClip(u); //Bueno de la AudioClip no se puede instancias por eso esto
  				        	    sonido.play();//para que suene
  				        	    SeActivoSonido=1;
  				        	    
  				        	//   apartado para configurar el uso de la pantalla de avisos--------------------------------
                                JDialog frame = new JDialog();
                                 String ruta= "prueba mensaje";//fila_mensaje.get(3).toString().trim();
                    		    frame.setUndecorated(true);
                    		    new Cat_Avisos_De_Pedido(frame,ruta);
                    		    frame.setVisible(true);
						}
		        	 // se asigna a auxiliar el ultimi valor de los pedidos para que se compare en la siguiente consulta
		        	 aux = Existen_pedidos_nuevos; 
		        	}
				
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Consulta_De_Status_De_Pedidos_De_Clientes en la funcion Buscar_Cambios   SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	
	/////////OBTIENE PEDIDOS PARA AVISAR QUE HAY PEDIDO NUEVO 
   	public int ObtenerPedidoNuevo(){
   		int pedidos =0;
   		
		String todos = ("select count(establecimientos.nombre) FROM pedcte " +
						"left outer join establecimientos on establecimientos.cod_estab=pedcte.cod_estab " +
						"where pedcte.fecha_elaboracion>convert(varchar(20),getdate(),103)+' 00:00:000' and pedcte.cod_estab in(20,15)");
		Statement s;
		ResultSet rs2;
		
		try {
			s = new Connexion().conexion_IZAGAR().createStatement();
			rs2 = s.executeQuery(todos);
			
			while(rs2.next()){
				pedidos = rs2.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Cat_Consulta_De_Status_De_Pedidos_De_Clientes  en la funcion [ ObtenerValoresPedClientes ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
	    return pedidos; 
	}
   	
	/////////OBTIENE DATOS DESDE IZAGAR PARA LLENAR TABLA CON LOS NUEVOS CAMBIOS
   	public Object[][] ObtenerValoresPedClientes(){
   		
		String todos = (" select establecimientos.nombre as establecimiento " +
		        " ,pedcte.folio as pedido    " +
				" ,convert(varchar(50),pedcte.fecha,103)+' '+convert(varchar(50),pedcte.fecha,108)as elaboracion_pedido " +
				" ,isnull(convert(varchar(50),facremtick.fecha,103)+' '+convert(varchar(50),facremtick.fecha,108),'NO SURTIDO')as surtido_pedido " +
				" ,isnull(DATEDIFF(minute,pedcte.fecha,facremtick.fecha),(datediff(minute,pedcte.fecha,getdate())))as Tiempo_Para_Surtido " +
				" ,pedcte.notas " +
				" ,isnull(cajeros.nombre,'NO SURTIDO') as cajero" +
				" FROM pedcte" +
				" left outer join facremtick on facremtick.folio_origen=pedcte.folio" +
				" left outer join cajeros on cajeros.cajero=facremtick.cajero" +
				" left outer join establecimientos on establecimientos.cod_estab=pedcte.cod_estab " +
				" where pedcte.fecha_elaboracion>convert(varchar(20),getdate(),103)+' 00:00:000' and pedcte.cod_estab in(20,15) " +
				" order by pedcte.cod_estab desc,pedcte.fecha asc ");
		
		Statement s;
		ResultSet rs2;

		try {
			s = new Connexion().conexion_IZAGAR().createStatement();
			rs2 = s.executeQuery(todos);
			Matriz_pedidos_ctes = new Object[LlenadoFilasPedCte(todos)][7];
			int i=0;
			while(rs2.next()){
				Matriz_pedidos_ctes[i][0] = "   "+rs2.getString(1).trim();
				Matriz_pedidos_ctes[i][1] = "   "+rs2.getString(2).trim();
				Matriz_pedidos_ctes[i][2] = "   "+rs2.getString(3).trim();
				Matriz_pedidos_ctes[i][3] = "   "+rs2.getString(4).trim();
				Matriz_pedidos_ctes[i][4] = "   "+rs2.getString(5).trim();
				Matriz_pedidos_ctes[i][5] = "   "+rs2.getString(6).trim();
				Matriz_pedidos_ctes[i][6] = "   "+rs2.getString(7).trim();
								i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Cat_Consulta_De_Status_De_Pedidos_De_Clientes  en la funcion [ ObtenerValoresPedClientes ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
	    return Matriz_pedidos_ctes; 
	}
  public int LlenadoFilasPedCte(String qry){
		int filas=0;
		Statement stmt = null;
		try {stmt = new Connexion().conexion_IZAGAR().createStatement();
			ResultSet rs2 = stmt.executeQuery(qry);
			while(rs2.next()){filas++;}
		} catch (SQLException e1) {	e1.printStackTrace();}
		return filas;
	}
/////////////////////////////////////////////////////////////////////////////////////
////////////HILO REVISION AUTOMATICA DE PEDIDOS CADA 60 SEGUNDOS
	public void Hilo_1_Minuto() {
			segundero seg = new segundero();
			seg.start();
		    	}
		    	int reconsultar=0;
		    	public class segundero extends Thread {
		    		public void run() {
		    			while(cerrarhilo !=true){
		    					try {
		    						Thread.sleep(1000);
		    						reconsultar+=1;
		    						if(reconsultar==60)////cambiar a 60 segundos
		    						{
		    						   reconsultar=0;
		    						   btnBuscar.doClick();
		    						}
		    					} catch (InterruptedException e) {
		    		                 JOptionPane.showMessageDialog(null, "Error en Cat_Hilo_1_Minuto en la funcion segundero  SQLException: "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		    						System.err.println("Error: "+ e.getMessage());
		    				}
		    			}
		    	}
		    }
//////////////////////////////////////////////////////////////////////////////////
///////////CATALOO EMERGENTE DE AVISO		    	
		    	
	  	public class Cat_Avisos_De_Pedido extends JComponent {
		    		
		    		private Image background;
		    		
		    		JLabel lblAviso = new JLabel();
		    		String fileFoto = System.getProperty("user.dir")+"/imagen/avisos/Hay_Un_Pedido_1.png";
		    		ImageIcon tmpIconAuxFoto = new ImageIcon(fileFoto);

		    		public Cat_Avisos_De_Pedido(final JDialog frame,String ruta) {
		    			
		    			//fileFoto=ruta;
		    			frame.setModal(true);
		    			updateBackground( );
		    			frame.add(lblAviso).setBounds(0, 0, 500, 400);
		    			 Icon iconoFoto = new ImageIcon(tmpIconAuxFoto.getImage().getScaledInstance(lblAviso.getWidth(), lblAviso.getHeight(), Image.SCALE_DEFAULT));
		                 lblAviso.setIcon(iconoFoto);
		                 
		    			frame.setLayout(new BorderLayout( ));
		    			frame.getContentPane( ).add("Center",this);
		    			frame.pack( );
		    			frame.setAlwaysOnTop( true );
		    			frame.setSize(500,400);
		    			frame.setLocationRelativeTo(null);
		    		    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		    				       KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "foco");
		    		    
		    		    getRootPane().getActionMap().put("foco", new AbstractAction(){
		    		        @Override
		    		        public void actionPerformed(ActionEvent e)
		    		        {
		    		        	frame.dispose();
		    		        }
		    		    });
		    		}
		    		
		    		
		    		public void updateBackground( ) {
		    		try {
		    		Robot rbt = new Robot( );
		    		Toolkit tk = Toolkit.getDefaultToolkit( );
		    		Dimension dim = tk.getScreenSize( );
		    		background = rbt.createScreenCapture(
		    		new Rectangle(0,0,(int)dim.getWidth( ),
		    		(int)dim.getHeight( )));
		    		} catch (Exception ex) {
		    		ex.printStackTrace( );
		    		}
		    		}
		    		
		    		public void paintComponent(Graphics g) {
		    		Point pos = this.getLocationOnScreen( );
		    		Point offset = new Point(-pos.x,-pos.y);
		    		g.drawImage(background,offset.x,offset.y,null);
		    		repaint();
		    		}

		    	}
		    	
///////////////////////////////////////////////////////////////////////////////////		    	
		    
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Relacion_De_Estatus_De_Pedidos_De_Clientes_Del_Dia().setVisible(true);
		}catch(Exception e){	}
	}
}