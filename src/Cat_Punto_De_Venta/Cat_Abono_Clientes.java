package Cat_Punto_De_Venta;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarSQL;
import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.Connexion;
import Obj_Administracion_del_Sistema.Obj_Usuario;
import Obj_Auditoria.Obj_Retiros_Cajeros;
import Obj_Principal.Componentes;
import Obj_Punto_De_Venta.Obj_Abono_Clientes;
import Obj_Punto_De_Venta.Obj_Clientes;
import Obj_Renders.CaveceraTablaRenderer;
import Obj_Renders.tablaRenderer;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Abono_Clientes extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	int fila = 0;
	int columna = 2;
	
	String establecimiento_de_ticket = "";
	
	JLabel lblF2 = new JLabel("F2  => Abrir Filtro De Clientes");
	JLabel lblF5 = new JLabel("F11 => Imprimir Ticket A Detalle");
	JLabel lblF9 = new JLabel("F9  => Generer Abono");
	JLabel lblLimpiar = new JLabel("Esc => Limpiar");
	
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnGuardarAbono = new JButton("Guardar");
	JButton btnNuevaCuenta = new JButton("Cuenta Nueva");
	
	JButton btnCancelarTicket = new JButton("Cancelar Ticket",new ImageIcon("Iconos/cancelarTicket.png"));
	JButton btnCancelarAbono = new JButton("Cancelar Abono",new ImageIcon("Iconos/cancelarAbono.png"));
	JButton btnLiquidarTicket = new JButton("Liquidar Ticket",new ImageIcon("Iconos/cancelarTicket.png"));
	
//	 static Object[][] data = {
//          {"pesos",new Integer(1), new Integer(0),new Integer(0)},
//          {"dolar",new Double(12.92), new Integer(0),new Integer(0)},
//	 };
//	 
//	 static Object[][] dataTicket = {
//		 {"060000000001", "01/01/1900", "02/01/1900",new Integer(240)},
//         {"060000000002", "01/01/1900", "02/01/1900",new Integer(850)},
//         {"060000000002", "01/01/1900", "02/01/1900",new Integer(850)},
//     };
//	 
//	 static Object[][] dataAbono = {
//         {new Double(45.5), "01/01/1900", "depa","edgar"},
//         {new Double(150), "01/01/1900", "ferre","edgar"},
//         {new Double(45.5), "01/01/1900", "depa","edgar"},
//         {new Double(150), "01/01/1900", "ferre","edgar"},
//         {new Double(150), "01/01/1900", "ferre","edgar"},
//     };
     
	static Object[][] data = new BuscarTablasModel().denominaciones_apartados();
    public static DefaultTableModel tabla_model_cobro = new DefaultTableModel( data, new String[]{"Efectivo", "Valor", "Pago", "Importe"} ){
                    
			@SuppressWarnings({ "rawtypes" })
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
                            case 0  : return false; 
                            case 1  : return false; 
                            case 2  : return true; 
                            case 3  : return false; 
                    }
                     return false;
             }
    };
	
    public static DefaultTableModel tabla_model_ticket = new DefaultTableModel( null , new String[]{"Ticket", "Fecha Inicial", "Fecha Limite", "Saldo"} ){
                    
			@SuppressWarnings({ "rawtypes" })
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
                            case 0  : return false; 
                            case 1  : return false; 
                            case 2  : return false; 
                            case 3  : return false; 
                    }
                     return false;
             }
    };
    
    public static DefaultTableModel tabla_model_abonos = new DefaultTableModel( null , new String[]{"Folio","Cantidad", "Fecha De Abono", "Establecimiento", "Recibio"} ){
                    
			@SuppressWarnings({ "rawtypes" })
			Class[] types = new Class[]{
                       java.lang.Object.class,
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
                            case 0  : return false; 
                            case 1  : return false; 
                            case 2  : return false; 
                            case 3  : return false;
                            case 4  : return false;
                    }
                     return false;
             }
    };
    
    JTable tabla_cobros = new JTable(tabla_model_cobro);
	JScrollPane panelScroll_cobros = new JScrollPane(tabla_cobros);
	
    JTable tabla_ticket = new JTable(tabla_model_ticket);
	JScrollPane panelScroll_ticket = new JScrollPane(tabla_ticket);
	
    JTable tabla_abonos = new JTable(tabla_model_abonos);
	JScrollPane panelScroll_abonos = new JScrollPane(tabla_abonos);
	
//	JLabel lblAsignacion = new JLabel("Asignacion: ");
	JLabel lblCajero = new JLabel("Cajera (o): ");
	JLabel lblFolioCli = new JLabel("Folio Cliente: ");
	JLabel lblCliente = new JLabel("Cliente: ");
	JLabel lblTicket = new JLabel("Ticket: ");
	JLabel lblDomicilio = new JLabel("Domicilio: ");
	JLabel lblAbono = new JLabel("Abono: ");
	JLabel lblFechaLim = new JLabel("Fecha Limite: ");
	
	JTextField txtEstablecimiento = new JTextField("");
	JTextField txtCajera = new JTextField("");
	
	JTextField txtFolioCliente = new Componentes().text(new JTextField(), "Folio de Cliente", 10, "Int");
	JTextField txtCliente = new JTextField();
	JTextField txtDomicilio = new JTextField();
	
	JTextField txtTiket = new JTextField();
	JTextField txtAbono = new Componentes().text(new JTextField(), "Cantidad a Abonar", 15, "Double");
	
	JDateChooser fecha = new JDateChooser();
	
	JLabel lblDineroFaltente = new JLabel("Faltante: $");
	JLabel lblFaltente = new JLabel("0.0");
	
	JLabel lblSignoSaldo = new JLabel("Saldo: $");
	JLabel lblSaldo = new JLabel("0.0");
	
	JLabel lblImporteTotal = new JLabel("Importe Total: $");
	JLabel lblImporte = new JLabel("0.0");
	
	JLabel lblSignoCambio = new JLabel("Su Cambio: $");
	JLabel lblCambio = new JLabel("0.0");
	
	DecimalFormat formato = new DecimalFormat("#0.00");
	Border blackline;
	
	JLabel lblSeleccion_de_tabla = new JLabel("Sin datos seleccionados");
	
	String folio_ticket_o_folio_abono = "";
	double cantidad = 0;
	
	String bandera="";
	
	JButton btnaviso = new JButton();
	
	public Cat_Abono_Clientes(){
		int folio_empleado=new Obj_Usuario().LeerSession().getFolio();
		Obj_Retiros_Cajeros datosEmpleado= new Obj_Retiros_Cajeros().buscarEmpleado_para_ahorro_cte(folio_empleado);
		
				if(datosEmpleado.getAsignacion()== null){
					this.setTitle("Aviso !!!");
					
//					JOptionPane.showMessageDialog(null, "El usuario no esta asignado o no se pudo vincular con asignacion\nfavor de comunicarse con el departamento de sistemas", "Aviso", JOptionPane.WARNING_MESSAGE);
					btnaviso.setText(	"<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLUE>" +
							"		<CENTER><p> El usuario no esta asignado o no se pudo vincular con la asignacion, favor de comunicarse con el departamento de sistemas</p></CENTER></FONT></html>"); 
					panel.add(btnaviso).setBounds(1,1,350,90);
					
					cont.add(panel);
					
					this.setSize(360,120);
					this.setResizable(false);
					this.setLocationRelativeTo(null);
    		
				}else{
		            txtEstablecimiento.setText(/*"SUPER V"*/datosEmpleado.getEstablecimiento().trim());
		            txtEstablecimiento.setHorizontalAlignment(0);
					this.setTitle("Abonos Clientes              Folio de asignacion( "+datosEmpleado.getAsignacion().trim()+" )");
				
				this.fecha.setIcon(new ImageIcon("Iconos/calendar_icon&16.png"));
				
				cont.setBackground(new Color(0,17 ,255));
				
				blackline = BorderFactory.createLineBorder(new Color(255,171,0));
		//		this.setTitle("Abonos Clientes");
				this.panel.setBorder(BorderFactory.createTitledBorder(blackline, "Captura de abonos clientes"));
				
				btnCancelarTicket.setToolTipText("Cancelar Ticket");
				btnCancelarAbono.setToolTipText("Cancelar Abono");
				btnLiquidarTicket.setToolTipText("Liquidar Cuenta");
		
		    	tabla_cobros.setRowHeight(30);//tamaño de fila
		    	tabla_ticket.setRowHeight(30);//tamaño de fila
		    	tabla_abonos.setRowHeight(30);//tamaño de fila
		    	
		    	lblF2.setFont(new Font("arial", Font.BOLD, 13));
		    	lblF5.setFont(new Font("arial", Font.BOLD, 13));
		    	lblF9.setFont(new Font("arial", Font.BOLD, 13));
		    	lblLimpiar.setFont(new Font("arial", Font.BOLD, 13));
		    	
		    	lblSeleccion_de_tabla.setFont(new Font("arial", Font.BOLD, 12));
		
				lblSignoCambio.setFont(new Font("arial", Font.BOLD, 25));
				lblCambio.setFont(new Font("arial", Font.BOLD, 25));
				
				lblSignoSaldo.setFont(new Font("arial", Font.BOLD, 25));
				lblSaldo.setFont(new Font("arial", Font.BOLD, 25));
				
				lblImporteTotal.setFont(new Font("arial", Font.BOLD, 25));
				lblImporte.setFont(new Font("arial", Font.BOLD, 25));
				
				lblDineroFaltente.setFont(new Font("arial", Font.BOLD, 25));
				lblFaltente.setFont(new Font("arial", Font.BOLD, 25));
				
				lblSignoCambio.setForeground(Color.white);
				lblCambio.setForeground(Color.white);
				
				lblSignoSaldo.setForeground(Color.white);
				lblSaldo.setForeground(Color.white);
				
				lblImporteTotal.setForeground(Color.white);
				lblImporte.setForeground(Color.white);
				
				lblDineroFaltente.setForeground(Color.white);
				lblFaltente.setForeground(Color.white);
				
		//		lblAsignacion.setForeground(Color.white);
				lblCajero.setForeground(Color.white);
				lblFolioCli.setForeground(Color.white);
				lblCliente.setForeground(Color.white);
				lblTicket.setForeground(Color.white);
				lblDomicilio.setForeground(Color.white);
				lblAbono.setForeground(Color.white);
				lblFechaLim.setForeground(Color.white);
				
				lblF2.setForeground(Color.white);
				lblF5.setForeground(Color.white);
				lblF9.setForeground(Color.white);
				lblLimpiar.setForeground(Color.white);
				
				lblSeleccion_de_tabla.setForeground(Color.red);
		
				init_tabla();
				llamar_render();
				
				int x=20; int y=15; int ancho=80;
				
		//		panel.add(lblAsignacion).setBounds(x, y, ancho, 20);
				panel.add(txtEstablecimiento).setBounds(x,y,ancho+100,20);
				
				panel.add(lblCajero).setBounds(x+195, y, ancho, 20);
				panel.add(txtCajera).setBounds(x+260,y,(ancho*4)-30,20);
				
				panel.add(lblF2).setBounds(x+560,y,ancho*4,20);
				
				panel.add(btnCancelarTicket).setBounds(x+820,y,150,20);
				
				panel.add(lblFolioCli).setBounds(x, y+=25, ancho, 20);
				panel.add(txtFolioCliente).setBounds(x+70,y,ancho,20);
				panel.add(btnBuscar).setBounds(x+150,y,30,20);
				
				panel.add(lblCliente).setBounds(x+195, y, ancho, 20);
				panel.add(txtCliente).setBounds(x+260,y,(ancho*4)-30,20);
				
				panel.add(lblF5).setBounds(x+560,y,ancho*4,20);
				
				panel.add(btnCancelarAbono).setBounds(x+820,y,150,20);
				
				panel.add(lblTicket).setBounds(x, y+=25, ancho, 20);
				panel.add(txtTiket).setBounds(x+70,y,ancho,20);
				
				panel.add(lblDomicilio).setBounds(x+195, y, ancho, 20);
				panel.add(txtDomicilio).setBounds(x+260,y,(ancho*4)-30,20);
				
				panel.add(lblF9).setBounds(x+560,y,ancho*4,20);
		
				panel.add(btnLiquidarTicket).setBounds(x+820,y,150,20);
				
				panel.add(lblAbono).setBounds(x, y+=25, ancho, 20);
				panel.add(txtAbono).setBounds(x+70,y,ancho,20);
				
				panel.add(lblFechaLim).setBounds(x+195, y, ancho, 20);
				panel.add(fecha).setBounds(x+260, y, ancho+10, 20);
				panel.add(btnGuardarAbono).setBounds(x+355,y,ancho,20);
				panel.add(btnNuevaCuenta).setBounds(x+440,y,ancho+30,20);
				
				panel.add(lblLimpiar).setBounds(x+560,y,ancho*4,20);
				
				panel.add(lblSeleccion_de_tabla).setBounds(720,y+10,ancho+180,20);
				
				panel.add(panelScroll_cobros).setBounds(x, y+=30, 970, 170);
				panel.add(panelScroll_ticket).setBounds(x, y+=240, 970, 117);
				panel.add(panelScroll_abonos).setBounds(x, y+=130, 970, 177);
		
				panel.add(lblDineroFaltente).setBounds(x, 290, ancho*3+20, 40);
				panel.add(lblFaltente).setBounds(x+290,290,ancho*2+30,40);
				
				panel.add(lblSignoSaldo).setBounds(x, 320, ancho*3+20, 40);
				panel.add(lblSaldo).setBounds(x+290,320,ancho*2+30,40);
				
				panel.add(lblImporteTotal).setBounds(x+530, 290, ancho*3+20, 40);
				panel.add(lblImporte).setBounds(x+820,290,ancho*2+30,40);
				
				panel.add(lblSignoCambio).setBounds(x+530, 320, ancho*3+20, 40);
				panel.add(lblCambio).setBounds(x+820,320,ancho*2+30,40);
				
				tabla_cobros.setEnabled(false);
		
				txtEstablecimiento.setEditable(false);
				txtCajera.setEditable(false);
				txtCliente.setEditable(false);
				txtDomicilio.setEditable(false);
				txtTiket.setEditable(false);
				txtAbono.setEditable(false);
				
				fecha.setEnabled(false);
				
				btnBuscar.setBackground(new Color(255,171,0));
				btnBuscar.setForeground(new Color(0,17 ,255));
				btnBuscar.setContentAreaFilled(false);
				btnBuscar.setOpaque(true);
				
				btnGuardarAbono.setEnabled(false);
				btnNuevaCuenta.setEnabled(false);
				
				pintar_botones();
		
				lblSeleccion_de_tabla.setHorizontalAlignment(4);
				
				limpiar();
				
		//      asigna el foco al JTextField deseado al arrancar la ventana
		        this.addWindowListener(new WindowAdapter() {
		                public void windowOpened( WindowEvent e ){
		               	 txtFolioCliente.requestFocus();
		             }
		        });
		
				txtAbono.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(txtTiket.getText().equals("")){
							
							JOptionPane.showMessageDialog(null, "Genere ticket nuevo o seleccione uno de la tabla","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
							
						}else{
							
							
							
							
		//					System.out.println(bandera);
		//                  si 		bandera = "" entonces el ticket no es nuevo y se guardara correctamente
		//					else 	pedir fecha limite
							if(bandera.equals("")){
								
									if( txtAbono.getText().equals("") || Integer.valueOf(txtAbono.getText()) <= 0 ){
											JOptionPane.showMessageDialog(null, "Ingrese La Cantidad Que Desea Abonar","Aviso",JOptionPane.INFORMATION_MESSAGE);
											return;
									}else{
										
										btnGuardarAbono.setEnabled(true);
											tabla_cobros.setEnabled(true);
											
		//								if(Integer.valueOf(tabla_cobros.getValueAt(fila, columna)+"")==0){
		//									tabla_cobros.setValueAt("",fila, columna);
		//								}
											
											tabla_cobros.editCellAt(fila, columna);
											Component aComp=tabla_cobros.getEditorComponent();
											aComp.requestFocus();
									}
									
									btnGuardarAbono.setEnabled(true);
									btnNuevaCuenta.setEnabled(false);
									
									btnGuardarAbono.setBackground(new Color(255,171,0));
									btnGuardarAbono.setForeground(new Color(0,17 ,255));
									btnGuardarAbono.setContentAreaFilled(false);
									btnGuardarAbono.setOpaque(true);
									
									
									btnNuevaCuenta.setBackground(new Color(155,131,110));
									btnNuevaCuenta.setForeground(new Color(0,17 ,255));
									btnNuevaCuenta.setContentAreaFilled(false);
									btnNuevaCuenta.setOpaque(true);
									
									
								
							}else{
								String fechaNull = fecha.getDate()+"";
								
								String fecha_parametro = "";
								if(!fechaNull.equals("null")){
									fecha_parametro = new SimpleDateFormat("dd/MM/yyyy").format(fecha.getDate());;
								}
								
								if(fecha_parametro.equals("")){
										JOptionPane.showMessageDialog(null, "Favor de Ingresar una fecha limite","Aviso",JOptionPane.INFORMATION_MESSAGE);
										return;
								}else{
										if( txtAbono.getText().equals("") || Integer.valueOf(txtAbono.getText()) <= 0 ){
											JOptionPane.showMessageDialog(null, "Ingrese La Cantidad Que Desea Abonar","Aviso",JOptionPane.INFORMATION_MESSAGE);
											return;
										}else{
												tabla_cobros.setEnabled(true);
												
												btnGuardarAbono.setEnabled(true);
												btnNuevaCuenta.setEnabled(false);
												
												btnGuardarAbono.setBackground(new Color(255,171,0));
												btnGuardarAbono.setForeground(new Color(0,17 ,255));
												btnGuardarAbono.setContentAreaFilled(false);
												btnGuardarAbono.setOpaque(true);
												
												
												btnNuevaCuenta.setBackground(new Color(155,131,110));
												btnNuevaCuenta.setForeground(new Color(0,17 ,255));
												btnNuevaCuenta.setContentAreaFilled(false);
												btnNuevaCuenta.setOpaque(true);
												
		//										if(Integer.valueOf(tabla_cobros.getValueAt(fila, columna)+"")==0){
		//											tabla_cobros.setValueAt("",fila, columna);
		//										}
												
												tabla_cobros.editCellAt(fila, columna);
												Component aComp=tabla_cobros.getEditorComponent();
												aComp.requestFocus();
										}
								}
									
							}
							
						}
					}
				});
				
				tabla_cobros.addKeyListener(new KeyListener() {
					public void keyTyped(KeyEvent arg0) {}
					public void keyReleased(KeyEvent arg0) {
								if(CalcularImporte()==false){
										JOptionPane.showMessageDialog(null, "Se introdujo un valor no valido","Aviso",JOptionPane.INFORMATION_MESSAGE);
										tabla_cobros.setValueAt("", fila, columna);
										return;
								}
					}
					public void keyPressed(KeyEvent arg0) {}
				});
				
				//  filtro cliente
			    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
			       KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "filtrar");
			    
			    getRootPane().getActionMap().put("filtrar", new AbstractAction(){
			        public void actionPerformed(ActionEvent e)
			        {
			        	new Cat_Filtro_Clientes().setVisible(true);
			        }	    
			    });
			    
				//  cobrar 
			    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
			       KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "cobrar");
			    
			    getRootPane().getActionMap().put("cobrar", new AbstractAction(){
			        @Override
			        public void actionPerformed(ActionEvent e)
			        {
			        	
			        	if(txtCliente.getText().equals("")){
			        		JOptionPane.showMessageDialog(null, "No se ha seleccionado un cliente aun","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
			        	}else{
			        		if(txtTiket.getText().equals("")){
			        			JOptionPane.showMessageDialog(null, "Genere ticket nuevo o seleccione uno de la tabla","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
			        		}else{
		//	                  si 		bandera = "" entonces el ticket no es nuevo y se guardara correctamente
		//						else 	pedir fecha limite
//								if(bandera.equals("")){
									
										if( txtAbono.getText().equals("") || Integer.valueOf(txtAbono.getText()) <= 0 ){
												JOptionPane.showMessageDialog(null, "Ingrese La Cantidad Que Desea Abonar","Aviso",JOptionPane.INFORMATION_MESSAGE);
												return;
								}else{
										if(fecha.getDate()==null){
												JOptionPane.showMessageDialog(null, "Favor de Ingresar una fecha limite","Aviso",JOptionPane.INFORMATION_MESSAGE);
												return;
										}else{
												if( txtAbono.getText().equals("") || Integer.valueOf(txtAbono.getText()) <= 0 ){
														JOptionPane.showMessageDialog(null, "Ingrese La Cantidad Que Desea Abonar","Aviso",JOptionPane.INFORMATION_MESSAGE);
														return;
												}else{
														abonar();
												}
										}
								}
			        		}
			        	}
			        }
			    });
			    
				//  cobrar 
			    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
			       KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "limpiar_pantalla");
			    
			    getRootPane().getActionMap().put("limpiar_pantalla", new AbstractAction(){
			        @Override
			        public void actionPerformed(ActionEvent e)
			        {
				        	limpiar();
			        }
			    });
			    
			//  Ticket a detalle 
			    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
			       KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "ticketDetalle");
			    
			    getRootPane().getActionMap().put("ticketDetalle", new AbstractAction(){
			        @Override
			        public void actionPerformed(ActionEvent e)
			        {
			        	if(tabla_ticket.getSelectedRow()<0){
			        		
			        		JOptionPane.showMessageDialog(null, "Debe selecccionar el ticket que desea reimprimir", "Aviso !!!", JOptionPane.INFORMATION_MESSAGE);
							return;
			        	}else{
		//    		    	imprimir ticket 
							new Cat_Genera_Ticket_De_Abono_Cliente(folio_ticket_o_folio_abono,"abono");
			        	}
			        }
			    });
			    
			    txtFolioCliente.addActionListener(opBuscar);
				btnBuscar.addActionListener(opBuscar);
				btnNuevaCuenta.addActionListener(opGenerarNuevaCuenta);
				btnGuardarAbono.addActionListener(opGenerarAbono);
				
				btnCancelarTicket.addActionListener(opCancelarTicket);
				btnCancelarAbono.addActionListener(opCancelarAbono);
				btnLiquidarTicket.addActionListener(opLiquidarTicket);
				
		//    	FUNCION PARA AGREGAR UNA ACCION AL SELECCIONAR UNA FECHA
		        fecha.getDateEditor().addPropertyChangeListener(opFecha);
		        
				CargarCajero();
				
				SELECCION_TICKET(tabla_ticket);
				SELECCION_ABONO(tabla_abonos);
				
				cont.add(panel);
				
				this.setSize(1024,720);
				this.setResizable(false);
				this.setLocationRelativeTo(null);
			}	
		
	}

	ActionListener opCancelarTicket = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			if(tabla_abonos.getRowCount()>0){
//				si tiene abono (JOptinPane(debe cancelar los abonos primeros))
				JOptionPane.showMessageDialog(null, "Debe cancelar los abonos primeros", "Aviso !!!", JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				new Cat_Cancelacion_De_Tickets_C_Ahorro_Clientes("Ticket", folio_ticket_o_folio_abono, cantidad, txtCajera.getText().toString().trim()).setVisible(true);
			}
			
		}
	};
	
	ActionListener opCancelarAbono = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			new Cat_Cancelacion_De_Tickets_C_Ahorro_Clientes("Abono", folio_ticket_o_folio_abono, cantidad, txtCajera.getText().toString().trim()).setVisible(true);
		}
	};
	
	ActionListener opLiquidarTicket = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			new Cat_Cancelacion_De_Tickets_C_Ahorro_Clientes("Liquidar", folio_ticket_o_folio_abono, 0, txtCajera.getText().toString().trim()).setVisible(true);
		}
	};
	
	public void abonar(){
			if(CalcularImporte()==false){
				
				JOptionPane.showMessageDialog(null, "Se introdujo un valor no valido","Aviso",JOptionPane.INFORMATION_MESSAGE);
				tabla_cobros.setValueAt(0, fila, columna);
				return;
				
			}else{
				
		        	double abono = Double.valueOf(txtAbono.getText());
		        	double importeTotal = Double.valueOf(lblImporte.getText());
		        	
		        	
//          quite edicion de celda de jtable
		            tabla_cobros.putClientProperty ("terminateEditOnFocusLost", Boolean.TRUE) ;
	            
				        	if(importeTotal<abono){
				        		
					        		tabla_cobros.editCellAt(fila, columna);
									Component aComp=tabla_cobros.getEditorComponent();
									aComp.requestFocus();
									
					        		JOptionPane.showMessageDialog(null, "El importe es insuficiente","Aviso",JOptionPane.INFORMATION_MESSAGE);
									return;
				        	}else{
				        			lblCambio.setText((importeTotal-abono)+"");
				        	
				        	
	        	
				        	Obj_Abono_Clientes abonar = new Obj_Abono_Clientes();
				        	
				        	abonar.setTicket(txtTiket.getText().toUpperCase().trim());
				        	abonar.setAbono(Double.valueOf(txtAbono.getText().trim()));
				        	abonar.setEstablecimiento(txtEstablecimiento.getText());
				        	abonar.setCajero(txtCajera.getText().toUpperCase().trim());
			        	
				        	if(fecha.getDate() == null){
				        			abonar.setFecha_fin("01/01/1900 00:00");
				        	}else{
				        			abonar.setFecha_fin(new SimpleDateFormat("dd/MM/yyyy").format(fecha.getDate()));
				        	}
		        	
				        	abonar.setFolio_cliente(Integer.valueOf(txtFolioCliente.getText().trim()));
	        	
			        	if(abonar.guardarTickets()){
			        		
//    		    	imprimir ticket 
			        		String ticket = txtTiket.getText();
							 	
//							 	for(int i = 0; i < tabla_cobros.getRowCount(); i++){
//							 		tabla_cobros.setValueAt("", i, 2);
//							 	}
			        		
//        			quite edicion de celda de jtable
//							 	tabla_cobros.putClientProperty ("terminateEditOnFocusLost", Boolean.TRUE) ;
			
				        		txtFolioCliente.setText("");
				        		txtCliente.setText("");
				        		txtDomicilio.setText("");
				        		txtTiket.setText("");
				        		txtAbono.setText("");
				        		fecha.setDate(null);
				        		lblSeleccion_de_tabla.setText("Sin datos seleccionados");
				        		lblFaltente.setText("0.0");
				        		lblSaldo.setText("0.0");
				        		lblImporte.setText("0.0");
				        		lblCambio.setText("0.0");
				        		
				    			tabla_cobros.removeEditor();
				    			
				    			tabla_cobros.setEnabled(false);
				    			
								txtEstablecimiento.setEditable(false);
								txtCajera.setEditable(false);
								txtCliente.setEditable(false);
								txtDomicilio.setEditable(false);
								txtTiket.setEditable(false);
								txtAbono.setEditable(false);
								
								fecha.setEnabled(false);
								
								btnBuscar.setBackground(new Color(255,171,0));
								btnBuscar.setForeground(new Color(0,17 ,255));
								btnBuscar.setContentAreaFilled(false);
								btnBuscar.setOpaque(true);
								
								btnGuardarAbono.setEnabled(false);
								btnNuevaCuenta.setEnabled(false);
								
								pintar_botones();
			        		
				        		while(tabla_cobros.getRowCount()>0)
				        			tabla_model_cobro.removeRow(0);
				        		while(tabla_ticket.getRowCount()>0)
				        			tabla_model_ticket.removeRow(0);
				        		while(tabla_abonos.getRowCount()>0)
				        			tabla_model_abonos.removeRow(0);
				        		
//				        		Object [][] lista_tabla = data;
			
				        		String[] fila = new String[4];
				                        for(int i=0; i<data.length; i++){
				                                fila[0] = data[i][0]+"";
				                                fila[1] = data[i][1]+"";
				                                fila[2] = data[i][2]+"";
				                                fila[3] = data[i][3]+"";
				                                tabla_model_cobro.addRow(fila);
				                        }
				                        
				                 txtFolioCliente.requestFocus();       
				                        new Cat_Genera_Ticket_De_Abono_Cliente(ticket,"abono");
			                        
			        	}else{
			        		JOptionPane.showMessageDialog(null, "El abono no a sido realizado con exito","Error",JOptionPane.ERROR_MESSAGE);
							return;
			        	}
			        	tabla_cobros.setEnabled(false);
			       }
			}
	}
	
	PropertyChangeListener opFecha = new PropertyChangeListener() {
	  	  public void propertyChange(PropertyChangeEvent e) {
	  	            if ("date".equals(e.getPropertyName())){
		  	            		txtAbono.requestFocusInWindow();	
	  	            }
	  		}
    };
	
	public void limpiar(){
		
		txtFolioCliente.setText("");
		txtTiket.setText("");
		txtAbono.setText("");
		txtCliente.setText("");
		txtDomicilio.setText("");
		fecha.setDate(null);
		
		while(tabla_ticket.getRowCount()>0)
			tabla_model_ticket.removeRow(0);
		while(tabla_abonos.getRowCount()>0)
			tabla_model_abonos.removeRow(0);
		
		txtFolioCliente.requestFocus();
	}
	
	public void pintar_botones(){
		btnCancelarTicket.setEnabled(false);
		btnCancelarTicket.setBackground(new Color(155,131,110));
		btnCancelarTicket.setForeground(new Color(0,17 ,255));
		btnCancelarTicket.setContentAreaFilled(false);
		btnCancelarTicket.setOpaque(true);
		
        btnCancelarAbono.setEnabled(false);
		btnCancelarAbono.setBackground(new Color(155,131,110));
		btnCancelarAbono.setForeground(new Color(0,17 ,255));
		btnCancelarAbono.setContentAreaFilled(false);
		btnCancelarAbono.setOpaque(true);
		
		btnLiquidarTicket.setEnabled(false);
		btnLiquidarTicket.setBackground(new Color(155,131,110));
		btnLiquidarTicket.setForeground(new Color(0,17 ,255));
		btnLiquidarTicket.setContentAreaFilled(false);
		btnLiquidarTicket.setOpaque(true);
		
		btnGuardarAbono.setBackground(new Color(155,131,110));
		btnGuardarAbono.setForeground(new Color(0,17 ,255));
		btnGuardarAbono.setContentAreaFilled(false);
		btnGuardarAbono.setOpaque(true);
		
		btnNuevaCuenta.setBackground(new Color(155,131,110));
		btnNuevaCuenta.setForeground(new Color(0,17 ,255));
		btnNuevaCuenta.setContentAreaFilled(false);
		btnNuevaCuenta.setOpaque(true);
	}
	
	@SuppressWarnings("unused")
	private boolean Validar(int fila, int columna) { 
		
			String valor=""; 
			double numero =0;
			
			if(tabla_cobros.getValueAt(fila,columna).toString().equals("")) {
				numero =0;
				return true; 
			}else{ 
				
				
				
				try{
						numero = Double.valueOf(tabla_cobros.getValueAt(fila, columna).toString().trim());
						return true;
				}catch(NumberFormatException e){
						return false;
				}
			} 
	}
	
	public boolean CalcularImporte(){
		
		boolean valor=false;
		
		if(Validar(fila,columna)){
			
				int cantidadDeFilas = tabla_cobros.getRowCount();
				
				fila+=1;

				if(fila == cantidadDeFilas){
					fila=0;
				}
				
//				if(tabla_cobros.getValueAt(fila, columna).equals("")){
//					tabla_cobros.setValueAt(0,fila, columna);
//				}
		
				tabla_cobros.editCellAt(fila, columna);
				Component aComp=tabla_cobros.getEditorComponent();
				aComp.requestFocus();
		
				double totalDelImporte=0;
				double pago = 0;
				for(int i=0; i<=tabla_cobros.getRowCount()-1; i++){
					pago = tabla_cobros.getValueAt(i, 2).toString().trim().equals("")?0:Double.valueOf(tabla_cobros.getValueAt(i, 2).toString().trim());
						tabla_cobros.setValueAt((Double.valueOf(tabla_cobros.getValueAt(i, 1).toString().trim())*pago), i, 3);
						totalDelImporte = totalDelImporte + Double.valueOf(tabla_cobros.getValueAt(i, 3).toString().trim());
				}
				lblImporte.setText(totalDelImporte+"");
				
				if(totalDelImporte - Double.valueOf(txtAbono.getText()) < 0){
					
						lblDineroFaltente.setForeground(Color.red);
						lblFaltente.setForeground(Color.red);
						lblSignoCambio.setForeground(Color.white);
						lblCambio.setForeground(Color.white);
						
						 lblCambio.setText("0.0");
						 lblFaltente.setText(""+formato.format((totalDelImporte - Double.valueOf(txtAbono.getText()))*-1));
				}else{
					
						lblDineroFaltente.setForeground(Color.white);
						lblFaltente.setForeground(Color.white);
						lblSignoCambio.setForeground(Color.black);
						lblCambio.setForeground(Color.black);
						
						 lblCambio.setText(""+(totalDelImporte - Double.valueOf(txtAbono.getText())));
						 lblFaltente.setText("0.0");
				}
				
				valor = true;
		}
		return valor;
	}
	
	ActionListener opGenerarAbono = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			abonar();
		}
	};
	
	ActionListener opBuscar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if(txtFolioCliente.getText().equals("")){
				new Cat_Filtro_Clientes().setVisible(true);
				btnNuevaCuenta.setEnabled(false);
				fecha.setEnabled(false);
			}else{
				bandera="";
				txtTiket.setText("");
//				ingresar folio_cliente directo
				buscar_cliente(Integer.valueOf(txtFolioCliente.getText()));
				btnNuevaCuenta.setEnabled(true);
				fecha.setEnabled(true);
				
				btnNuevaCuenta.setBackground(new Color(255,171,0));
				btnNuevaCuenta.setForeground(new Color(0,17 ,255));
				btnNuevaCuenta.setContentAreaFilled(false);
				btnNuevaCuenta.setOpaque(true);
				

			}
		}
	};
	
	ActionListener opGenerarNuevaCuenta = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if(txtCliente.getText().equals("")){
				JOptionPane.showMessageDialog(null, "No se ha seleccionado un cliente aun","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
//				enviar de parametro el establecimiento donde se capturo ("NS")
					String nuevoTicket = new Obj_Abono_Clientes().nuevoTicket(txtEstablecimiento.getText());
					txtTiket.setText(nuevoTicket);
					
					btnNuevaCuenta.setEnabled(false);
            		
    				btnNuevaCuenta.setBackground(new Color(155,131,110));
    				btnNuevaCuenta.setForeground(new Color(0,17 ,255));
    				btnNuevaCuenta.setContentAreaFilled(false);
    				btnNuevaCuenta.setOpaque(true);
    				
//                  para validar si pide fecha o no
					bandera="cuenta_nueva";
			}
			txtAbono.requestFocus();
		}
	};
	
	public void llamar_render(){
		//		tipo de valor = imagen,chb,texto
//		tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
    
		Color fondoEncabezado = new Color(255,171,0);
		Color textoEncabezado = Color.black;
		
		for(int i = 0; i<tabla_cobros.getColumnCount(); i++){
			tabla_cobros.getColumnModel().getColumn(i).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado,textoEncabezado,"centro","Arial","negrita",18));
			tabla_cobros.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("VENTA","centro","Arial","negrita",25));
		}
		for(int i = 0; i<tabla_ticket.getColumnCount(); i++){
			tabla_ticket.getColumnModel().getColumn(i).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado,textoEncabezado,"centro","Arial","negrita",18));
			tabla_ticket.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("VENTA","centro","Arial","negrita",20));
		}
		for(int i = 0; i<tabla_abonos.getColumnCount(); i++){
			tabla_abonos.getColumnModel().getColumn(i).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado,textoEncabezado,"centro","Arial","negrita",18));
			tabla_abonos.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("VENTA","centro","Arial","negrita",16));
		}
	}
	
	public void init_tabla(){
    	
		int x=250;
		int y=120;
		int z=350;
		
    	this.tabla_cobros.getTableHeader().setReorderingAllowed(false) ;
    	
    	this.tabla_cobros.getColumnModel().getColumn(0).setMaxWidth(x);
    	this.tabla_cobros.getColumnModel().getColumn(0).setMinWidth(x);		
    	this.tabla_cobros.getColumnModel().getColumn(1).setMaxWidth(x);
    	this.tabla_cobros.getColumnModel().getColumn(1).setMinWidth(x);
    	this.tabla_cobros.getColumnModel().getColumn(2).setMaxWidth(x);
    	this.tabla_cobros.getColumnModel().getColumn(2).setMinWidth(x);
    	this.tabla_cobros.getColumnModel().getColumn(3).setMaxWidth(x);
    	this.tabla_cobros.getColumnModel().getColumn(3).setMinWidth(x);
    	
    	this.tabla_ticket.getTableHeader().setReorderingAllowed(false) ;
    	
    	this.tabla_ticket.getColumnModel().getColumn(0).setMaxWidth(x);
    	this.tabla_ticket.getColumnModel().getColumn(0).setMinWidth(x);		
    	this.tabla_ticket.getColumnModel().getColumn(1).setMaxWidth(x);
    	this.tabla_ticket.getColumnModel().getColumn(1).setMinWidth(x);
    	this.tabla_ticket.getColumnModel().getColumn(2).setMaxWidth(x);
    	this.tabla_ticket.getColumnModel().getColumn(2).setMinWidth(x);
    	this.tabla_ticket.getColumnModel().getColumn(3).setMaxWidth(x);
    	this.tabla_ticket.getColumnModel().getColumn(3).setMinWidth(x);
    	
    	this.tabla_abonos.getTableHeader().setReorderingAllowed(false) ;
    	
    	this.tabla_abonos.getColumnModel().getColumn(0).setMaxWidth(y);
    	this.tabla_abonos.getColumnModel().getColumn(0).setMinWidth(y);		
    	this.tabla_abonos.getColumnModel().getColumn(1).setMaxWidth(x-120);
    	this.tabla_abonos.getColumnModel().getColumn(1).setMinWidth(x-120);
    	this.tabla_abonos.getColumnModel().getColumn(2).setMaxWidth(x-90);
    	this.tabla_abonos.getColumnModel().getColumn(2).setMinWidth(x-90);
    	this.tabla_abonos.getColumnModel().getColumn(3).setMaxWidth(x-40);
    	this.tabla_abonos.getColumnModel().getColumn(3).setMinWidth(x-40);
    	this.tabla_abonos.getColumnModel().getColumn(4).setMaxWidth(z);
    	this.tabla_abonos.getColumnModel().getColumn(4).setMinWidth(z);
    }
	
	public static void main(String [] arg){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Abono_Clientes().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
	
	public void buscar_cliente(int folio_cliente){
			try {
				Obj_Clientes cliente = new Obj_Clientes().buscar(folio_cliente);
			
				if(cliente.getFolio_cliente() == folio_cliente){
					
//BUSCAR CUANTAS CUENTAS TIENE 			(EL PUNTO (1.-) ACER UNA FUNCION PORQUE TAMBIEN FUNCIONARA CON BTNNUEVOTICKET)
//1.- SI NO TIENE CUENTE GENERAR NUMERO DE TIKET NUEVO AUTOMATICO       				Y DESBLOQUEAR EL TXTABONO	PASAR EL FOCO AL ABONO
//2.- SI TIENE 1 PONERLO EN EL TXTFOLIOTICKET   (LLENAR TABLA TICKET Y TABLA ABONOS)   Y DESBLOQUEAR EL TXTABONO  PASAR EL FOCO AL ABONO
//3.- SI TIENE MAS    TXTFOLIOTICKET=""     (LLENAR TABLA TICKET Y TABLA ABONOS=vacio)      Y    BLOQUEAR EL TXTABONO	NO PASAR EL FOCO AL ABONO
					
//AL SELECCIONAR UN TICKET DE DICHA TABLA   PASAR EL PARAMETRO AL TXTFOLIOTICKET  Y DESBLOQUEAR TXTaBONO (LLENAR TABLA ABONOS) 
					txtFolioCliente.setText(cliente.getFolio_cliente()+"");
					txtCliente.setText(cliente.getNombre()+" "+cliente.getAp_paterno()+" "+cliente.getAp_materno());
					txtDomicilio.setText(cliente.getDireccion());
					
	        		while(tabla_ticket.getRowCount()>0)
	        			tabla_model_ticket.removeRow(0);
	        		while(tabla_abonos.getRowCount()>0)
	        			tabla_model_abonos.removeRow(0);
	        		
//	        buscar ticket del cliente
	            Object [][] lista_ticket = new Obj_Abono_Clientes().get_tabla_tickets(folio_cliente);
	            
	            if(lista_ticket.length>0){
	            	String[] filaT = new String[4];
                    for(int i=0; i<lista_ticket.length; i++){
	                    	  filaT[0] = lista_ticket[i][0]+"";
	                    	  filaT[1] = lista_ticket[i][1]+"";
	                    	  filaT[2] = lista_ticket[i][2]+"";
	                    	  filaT[3] = lista_ticket[i][3]+"";
                            tabla_model_ticket.addRow(filaT);
                    }
	            }
	      		
	                      
					switch(tabla_ticket.getRowCount()){
						case 0: 
//							enviar de parametro el establecimiento donde se capturo ("NS")
//							String nuevoTicket = new Obj_Abono_Clientes().nuevoTicket("NS");
//							txtTiket.setText(nuevoTicket);
							
							while(tabla_ticket.getRowCount()>0){tabla_model_ticket.removeRow(0);}
							while(tabla_abonos.getRowCount()>0){tabla_model_abonos.removeRow(0);}
							
//							mensaje (no tiene un numero de ticket, generar uno nuevo)
							JOptionPane.showMessageDialog(null, "El cliente no tiene cuenta abierta","Aviso",JOptionPane.INFORMATION_MESSAGE);
						break;
						
						case 1: 
			    			txtTiket.setText(tabla_ticket.getValueAt(0, 0).toString().trim());	
			    			
							while(tabla_abonos.getRowCount()>0){tabla_model_abonos.removeRow(0);}
//			              	buscar abonos del cliente
		                    Object [][] lista_abonos = new Obj_Abono_Clientes().get_tabla_abonos(txtTiket.getText());
		            		String[] filaA = new String[5];
		                            for(int i=0; i<lista_abonos.length; i++){
			                            	filaA[0] = lista_abonos[i][0]+"";
			                            	filaA[1] = lista_abonos[i][1]+"";
			                            	filaA[2] = lista_abonos[i][2]+"";
			                            	filaA[3] = lista_abonos[i][3]+"";
			                            	filaA[4] = lista_abonos[i][4]+"";
		                                    tabla_model_abonos.addRow(filaA);
		                            }
						break;
						
						default: 
							txtTiket.setText("");
							while(tabla_abonos.getRowCount()>0){tabla_model_abonos.removeRow(0);}
						break;
					}
					
					txtAbono.setEditable(true);
					txtAbono.requestFocus();
				}else{
					JOptionPane.showMessageDialog(null,"El Cliente No Esta Dado De Alta","Aviso",JOptionPane.INFORMATION_MESSAGE);
					txtFolioCliente.setText("");
					return;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	private void SELECCION_TICKET(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	
//	        	if (e.getButton() == MouseEvent.BUTTON1) {
    				
	        		int rowButton1 = tabla_ticket.getSelectedRow();
	        		
                    folio_ticket_o_folio_abono =  tabla_ticket.getValueAt(rowButton1, 0).toString().trim();
                    cantidad = Double.valueOf(tabla_ticket.getValueAt(rowButton1, 3).toString());
                    
//             buacar establecimiento del ticket seleccionado
                    try {
						establecimiento_de_ticket = new BuscarSQL().establecimiento_ticket_selecionado(folio_ticket_o_folio_abono);
						
						cargarAbonos(folio_ticket_o_folio_abono);
						
						if(establecimiento_de_ticket.equals(txtEstablecimiento.getText().toString().trim())){
							
	//		                  para validar si pide fecha o no
			                    bandera="";
			                	
			                    lblSeleccion_de_tabla.setText("El ticket   "+ folio_ticket_o_folio_abono+"   esta seleccionado");
			                    
				    			txtTiket.setText(folio_ticket_o_folio_abono);							

			                            tabla_cobros.setEnabled(true);

										txtAbono.setEditable(true);
										fecha.setEnabled(true);

										btnGuardarAbono.setEnabled(true);
										btnNuevaCuenta.setEnabled(true);
										
										btnGuardarAbono.setBackground(new Color(255,171,0));
										btnGuardarAbono.setForeground(new Color(0,17 ,255));
										btnGuardarAbono.setContentAreaFilled(false);
										btnGuardarAbono.setOpaque(true);
										
										btnBuscar.setBackground(new Color(255,171,0));
										btnBuscar.setForeground(new Color(0,17 ,255));
										btnBuscar.setContentAreaFilled(false);
										btnBuscar.setOpaque(true);
										
										pintar_botones();
										
			                      txtAbono.requestFocus();
			                      
			                      	btnCancelarTicket.setEnabled(true);
			                		btnCancelarTicket.setBackground(new Color(255,171,0));
			                		btnCancelarTicket.setContentAreaFilled(false);
			                		btnCancelarTicket.setOpaque(true);
			                		
			                      	btnCancelarAbono.setEnabled(false);
				                    btnCancelarAbono.setBackground(new Color(155,131,110));
				              		btnCancelarAbono.setContentAreaFilled(false);
				            		btnCancelarAbono.setOpaque(true);
			            		
				            		btnLiquidarTicket.setEnabled(true);
				            		btnLiquidarTicket.setBackground(new Color(255,171,0));
				            		btnLiquidarTicket.setContentAreaFilled(false);
				            		btnLiquidarTicket.setOpaque(true);
				            		
				            		btnNuevaCuenta.setEnabled(true);
				            		
				    				btnNuevaCuenta.setBackground(new Color(255,171,0));
				    				btnNuevaCuenta.setForeground(new Color(0,17 ,255));
				    				btnNuevaCuenta.setContentAreaFilled(false);
				    				btnNuevaCuenta.setOpaque(true);
			            		
						}else{
							
							tabla_cobros.setEnabled(false);

							txtAbono.setEditable(false);
							fecha.setEnabled(false);

							btnGuardarAbono.setEnabled(false);
							btnNuevaCuenta.setEnabled(false);
							
							btnGuardarAbono.setBackground(new Color(255,171,0));
							btnGuardarAbono.setForeground(new Color(0,17 ,255));
							btnGuardarAbono.setContentAreaFilled(false);
							btnGuardarAbono.setOpaque(true);
							
							btnBuscar.setBackground(new Color(255,171,0));
							btnBuscar.setForeground(new Color(0,17 ,255));
							btnBuscar.setContentAreaFilled(false);
							btnBuscar.setOpaque(true);
							
							btnNuevaCuenta.setBackground(new Color(255,171,0));
							btnNuevaCuenta.setForeground(new Color(0,17 ,255));
							btnNuevaCuenta.setContentAreaFilled(false);
							btnNuevaCuenta.setOpaque(true);
							
							pintar_botones();
							
//							cargarAbonos(txtTiket.getText());
							
//							txtTiket.setText("");
							JOptionPane.showMessageDialog(null, "El Ticket que desea abonar fue dado de alta en el establecimiento: "+establecimiento_de_ticket+"\nFavor de informar al cliente que realice su abono en dicho establecimiento", "Aviso !!!", JOptionPane.INFORMATION_MESSAGE);
//							return;
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
//	        	}
	        }
        });
    }
	
	public void cargarAbonos(String tick){
		
		while(tabla_abonos.getRowCount()>0){tabla_model_abonos.removeRow(0);}
		
//			              	buscar abonos del cliente
        Object [][] lista_abonos = new Obj_Abono_Clientes().get_tabla_abonos(tick);
		String[] filaA = new String[5];
                for(int i=0; i<lista_abonos.length; i++){
                    	filaA[0] = lista_abonos[i][0]+"";
                    	filaA[1] = lista_abonos[i][1]+"";
                    	filaA[2] = lista_abonos[i][2]+"";
                    	filaA[3] = lista_abonos[i][3]+"";
                    	filaA[4] = lista_abonos[i][4]+"";
                        tabla_model_abonos.addRow(filaA);
                }
	}
	
	private void SELECCION_ABONO(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	
	        		int rowButton1 = tabla_abonos.getSelectedRow();
	        		
	        		folio_ticket_o_folio_abono =  tabla_abonos.getValueAt(rowButton1, 0).toString().trim();
                    cantidad = Double.valueOf(tabla_abonos.getValueAt(rowButton1, 1).toString());

                    lblSeleccion_de_tabla.setText("El abono   "+ folio_ticket_o_folio_abono+"   esta seleccionado");
                    
                      	btnCancelarTicket.setEnabled(false);
                      	btnCancelarAbono.setEnabled(true);
                      	btnLiquidarTicket.setEnabled(false);
                      	
                      	btnCancelarTicket.setBackground(new Color(155,131,110));
                      	btnCancelarTicket.setContentAreaFilled(false);
                      	btnCancelarTicket.setOpaque(true);
                      	
                      	btnCancelarAbono.setBackground(new Color(255,171,0));
                      	btnCancelarAbono.setContentAreaFilled(false);
                      	btnCancelarAbono.setOpaque(true);
                      	
                      	btnLiquidarTicket.setBackground(new Color(155,131,110));
                      	btnLiquidarTicket.setContentAreaFilled(false);
                      	btnLiquidarTicket.setOpaque(true);
	        }
        });
    }
	
	public void CargarCajero()
	{
		  File archivo = null;
 	      FileReader fr = null;
 	      BufferedReader br = null;
		 try {
 	         archivo = new File ("Config/users");
 	         fr = new FileReader (archivo);
 	         br = new BufferedReader(fr);
 	         String linea;
 	         while((linea=br.readLine())!=null)
 	        	txtCajera.setText(linea);
 	      }
 	      catch(Exception e){
 	         e.printStackTrace();
 	      }finally{
 	         try{                   
 	            if( null != fr ){  
 	               fr.close();    
 	            }                 
 	         }catch (Exception e2){
 	            e2.printStackTrace();
 	         }
 	      }
	}
	
	//Filtro Clientes
	public class Cat_Filtro_Clientes extends JFrame{
		
		Container cont = getContentPane();
		JLayeredPane campo = new JLayeredPane();
		
		Connexion con = new Connexion();
		
		DefaultTableModel model = new DefaultTableModel(0,3){
			public boolean isCellEditable(int fila, int columna){
				if(columna < 0)
					return true;
				return false;
			}
		};
		
		JTable tabla = new JTable(model);
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JLabel lblBuscar = new JLabel("BUSCAR : ");
		JTextField txtBuscar = new Componentes().text(new JTextField(), "Buscar", 130, "String");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Cat_Filtro_Clientes(){
			this.setTitle("Filtro Empleados");
			
			cont.setBackground(new Color(0,17 ,255));
			lblBuscar.setForeground(Color.white);
			
			txtBuscar.addKeyListener(new KeyAdapter() { 
				public void keyReleased(final KeyEvent e) { 
	                filtro(); 
	            } 
	        });
		
			trsfiltro = new TableRowSorter(model); 
			tabla.setRowSorter(trsfiltro);  
			
			campo.add(getPanelTabla()).setBounds(10,70,365,450);
			
			agregar(tabla);
			
			campo.add(lblBuscar).setBounds(30,30,70,20);
			campo.add(txtBuscar).setBounds(95,30,215,20);
			
			cont.add(campo);
			
			this.setSize(390,570);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			llamar_render();
		}
		
		public void llamar_render(){
			//		tipo de valor = imagen,chb,texto
//			tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
	    
			Color fondoEncabezado = new Color(255,171,0);
			Color textoEncabezado = Color.black;
			
			for(int i = 0; i<tabla.getColumnCount(); i++){
				tabla.getColumnModel().getColumn(i).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado,textoEncabezado,"centro","Arial","negrita",16));
				tabla.getColumnModel().getColumn(i ).setCellRenderer(new tablaRenderer("VENTA","centro","Arial","negrita",12));
			}
		}
		
		private void agregar(final JTable tbl) {
	        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		        		
		        		bandera="";
		        		txtTiket.setText("");
		        		
		        		int fila = tabla.getSelectedRow();
		    			String folio =  tabla.getValueAt(fila, 0).toString().trim();
		    			dispose();

		    			//buscar cliente para abonos
		    			buscar_cliente(Integer.valueOf(folio));
		    			btnNuevaCuenta.setEnabled(true);
		    			fecha.setEnabled(true);
		    			
						btnNuevaCuenta.setBackground(new Color(255,171,0));
						btnNuevaCuenta.setForeground(new Color(0,17 ,255));
						btnNuevaCuenta.setContentAreaFilled(false);
						btnNuevaCuenta.setOpaque(true);
						

		        	}
		        }
	        });
	    }
		
		@SuppressWarnings("unchecked")
		public void filtro() { 
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1));
		}  
		
		private JScrollPane getPanelTabla()	{		
			new Connexion();
			
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			
			tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla.getColumnModel().getColumn(0).setMaxWidth(70);
			tabla.getColumnModel().getColumn(0).setMinWidth(70);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Cliente");
			tabla.getColumnModel().getColumn(1).setMaxWidth(185);
			tabla.getColumnModel().getColumn(1).setMinWidth(185);
			tabla.getColumnModel().getColumn(2).setHeaderValue("Direccion");
			tabla.getColumnModel().getColumn(2).setMaxWidth(100);
			tabla.getColumnModel().getColumn(2).setMinWidth(100);
			
			Statement s;
			ResultSet rs;
			try {
				s = con.conexion().createStatement();
				rs = s.executeQuery("select folio_cliente as folio_cliente, " +
									"nombre+' '+ap_paterno+' '+ap_materno as cliente, " +
									"direccion as direccion " +
									"from tb_clientes" );
				
				while (rs.next())
				{ 
				   String [] fila = new String[3];
				   fila[0] = rs.getString(1).trim();
				   fila[1] = rs.getString(2).trim();
				   fila[2] = rs.getString(3).trim();
				   
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
	

	
	
	public class Cat_Genera_Ticket_De_Abono_Cliente extends JFrame {
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Cat_Genera_Ticket_De_Abono_Cliente(String ticket,String movimiento) {
			
			String ruta = "";
			String query = "";
			
			
			
			if(movimiento.equals("liquidacion")){
				
					System.out.println(ticket+" - "+movimiento);
					                                
						ruta = "\\src\\Obj_Reportes\\Obj_Liquidar_Ticket_C_Ahorro_Cte.jrxml";
						query = "exec sp_tickets_abonos_c_ahorro_cte '"+ticket+"','L'";
						
//						System.out.println(query);
				}else{	
						if(movimiento.equals("abono")){
								ruta = "\\src\\Obj_Reportes\\Obj_Ticket_C_Ahorro_Cte.jrxml";
								query = "exec sp_tickets_abonos_c_ahorro_cte '"+ticket+"','V'";
						}else{					
								ruta = "\\src\\Obj_Reportes\\Obj_Ticket_C_Ahorro_Cte_A_Detalle.jrxml";
								query = "exec sp_tickets_abonos_c_ahorro_cte_a_detalle '"+ticket+"'";
						}
			}
					
			try {
				
//				jenera el archivo *.jasper en la misma ruta del proyecto
//				JasperCompileManager.compileReportToFile(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Cumpleanios_Del_Mes.jrxml");
				
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+ruta);
				JasperPrint print = JasperFillManager.fillReport(report, new HashMap(),new JRResultSetDataSource(new Connexion().conexion().createStatement().executeQuery(query)));
				
//				mostras reporte (comentar para no mostrar)
//				JasperViewer.viewReport(print, false);
				
//				imprimir reporte automatico ---------------------------------------------------------------------------------------------------------------
//				false = imprime reporte en impresora predeterminada ---------------------------------------------------------------------------------------
//				true  = muestra ventana de seleccion de impresora -----------------------------------------------------------------------------------------
				JasperPrintManager.printReport(print, false);
				JasperPrintManager.printReport(print, false);
				
				ruta= "";
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error En La Subclase Cat_Abono_Clientes ", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
		}
				
		
	}
	
	public class Cat_Cancelacion_De_Tickets_C_Ahorro_Clientes extends JDialog{
		
		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		JLabel lblCancelarFolio = new JLabel("");
		JLabel lblCancelacionComplemento = new JLabel("");
		
		JLabel lblClave = new JLabel("Clave de autorizacion:");
		JPasswordField txtClave = new JPasswordField();
		JTextField txtNombre_supervisor = new JTextField();
		
		JButton btnAceptarCancelacion = new JButton("");
		
		String Cancelacion_ticket_abono_o_liquidacion = "";
		String Folio_ticket_o_abono = "";
		
		String Cajero = "";
		
		public Cat_Cancelacion_De_Tickets_C_Ahorro_Clientes(String cancelacion_ticket_abono_o_liquidacion,String folio, double cantidad, String cajera){
			this.setModal(true);
			blackline = BorderFactory.createLineBorder(Color.darkGray);
			
			Cancelacion_ticket_abono_o_liquidacion = cancelacion_ticket_abono_o_liquidacion;
			Folio_ticket_o_abono = folio;
			
			Cajero=cajera;
			
			lblCancelarFolio.setFont(new Font("arial",Font.BOLD,12));
			lblCancelacionComplemento.setFont(new Font("arial",Font.BOLD,12));
			lblClave.setFont(new Font("arial",Font.BOLD,12));
		
			btnAceptarCancelacion.setVisible(false);
			
//			Liquidar Abono Ticket
			switch(Cancelacion_ticket_abono_o_liquidacion){
					case "Ticket": 	
							this.setTitle("Cancelacion de ticket");
							panel.setBorder(BorderFactory.createTitledBorder(blackline, "Cancelacion de ticket"));
							lblCancelarFolio.setText("El Tickect   "+folio+"   sera cancelado,");
							lblCancelacionComplemento.setText("llamar a supervisor(a) para su autorizacion.");
							btnAceptarCancelacion.setText("Cancelar Ticket");
					break;
					
					case "Abono": 
							this.setTitle("Cancelacion de abono");
							panel.setBorder(BorderFactory.createTitledBorder(blackline, "Cancelacion de abono"));
							lblCancelarFolio.setText("El Abono   "+folio+"   con la cantidad de   $"+cantidad+"   sera cancelado,");
							lblCancelacionComplemento.setText("llamar a supervisor(a) para su autorizacion.");
							btnAceptarCancelacion.setText("Cancelar Abono");
					break;
					
					case "Liquidar": 
							this.setTitle("Liquidaciones");
							panel.setBorder(BorderFactory.createTitledBorder(blackline, "Liquidacion de cuenta"));
							lblCancelarFolio.setText("El Tickect   "+folio+"   sera liquidado,");
							lblCancelacionComplemento.setText("llamar a supervisor(a) para su autorizacion.");
							btnAceptarCancelacion.setText("Liquidar Ticket");					
					;break;
			}
			
			int y = 20;
			panel.add(lblCancelarFolio).setBounds(20,y,410,20);
			panel.add(lblCancelacionComplemento).setBounds(20,y+=25,410,20);
		
			panel.add(lblClave).setBounds(100,y+=35,130,20);
			panel.add(txtClave).setBounds(250,y,100,20);
			panel.add(txtNombre_supervisor).setBounds(15,y+=25,410,20);
			
			panel.add(btnAceptarCancelacion).setBounds(145,y+=25,140,30);
			
			cont.add(panel);
			
			txtNombre_supervisor.setEditable(false);
			txtNombre_supervisor.setHorizontalAlignment(0);
			
			lblCancelarFolio.setHorizontalAlignment(0);
			lblCancelacionComplemento.setHorizontalAlignment(0);
			
			txtClave.addActionListener(opClave);
			
			btnAceptarCancelacion.addActionListener(opConfirmar);
			
			this.setSize(450,200);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
		}
		
		ActionListener opClave = new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				if(txtClave.getText().equals("")){
					
						btnAceptarCancelacion.setVisible(false);
						txtNombre_supervisor.setText("");
						JOptionPane.showMessageDialog(null, "Pasar gafete de supervisor(a)", "Aviso !!!", JOptionPane.INFORMATION_MESSAGE);
						return;
						
				}else{
						String persona_autorizada = new BuscarSQL().permiso_cancelar_ticket_o_abono(txtClave.getText());
						
						txtClave.setText("");
						
						if(persona_autorizada.equals("EMPLEADO NO ENCONTRADO") || persona_autorizada.equals("")){
							
								txtNombre_supervisor.setText("");
								btnAceptarCancelacion.setVisible(false);
								JOptionPane.showMessageDialog(null, "El usuario no esta autorizado para cancelar", "Aviso !!!", JOptionPane.INFORMATION_MESSAGE);
								return;
							
						}else{
//								si tiene permiso    (activar boton de confirmacion)
								txtNombre_supervisor.setText(persona_autorizada);
								btnAceptarCancelacion.setVisible(true);
						}
				}	
			}
		};
		
		ActionListener opConfirmar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					switch(Cancelacion_ticket_abono_o_liquidacion){
							case "Ticket": 	
									if(new ActualizarSQL().Actualizar_Cancelar_Ticket_o_Abono(Folio_ticket_o_abono,  txtNombre_supervisor.getText(),  "TICKET", Cajero)){
											
											dispose();
											buscar_cliente(Integer.valueOf(txtFolioCliente.getText()));
											lblSeleccion_de_tabla.setText("Sin datos seleccionados");
											pintar_botones();
											JOptionPane.showMessageDialog(null, "El ticket   "+Folio_ticket_o_abono+"   se cancelo correctamente", "Aviso !!!", JOptionPane.INFORMATION_MESSAGE);
									}
							break;
							
							case "Abono": 
									if(new ActualizarSQL().Actualizar_Cancelar_Ticket_o_Abono(Folio_ticket_o_abono,  txtNombre_supervisor.getText(), 	"ABONO", Cajero)){
										
											new Cat_Genera_Ticket_De_Abono_Cliente(Folio_ticket_o_abono,"cancelacion");
											
											dispose();
											buscar_cliente(Integer.valueOf(txtFolioCliente.getText()));
											lblSeleccion_de_tabla.setText("Sin datos seleccionados");
											pintar_botones();
											JOptionPane.showMessageDialog(null, "El abono   "+Folio_ticket_o_abono+"   se cancelo correctamete", "Aviso !!!", JOptionPane.INFORMATION_MESSAGE);
									}
							break;
							
							case "Liquidar": 
									if(new ActualizarSQL().Actualizar_Cancelar_Ticket_o_Abono(Folio_ticket_o_abono,  txtNombre_supervisor.getText(),  "LIQUIDAR", Cajero)){
										
										new Cat_Genera_Ticket_De_Abono_Cliente(Folio_ticket_o_abono,"liquidacion");
										
											dispose();
											buscar_cliente(Integer.valueOf(txtFolioCliente.getText()));
											lblSeleccion_de_tabla.setText("Sin datos seleccionados");
											pintar_botones();
											JOptionPane.showMessageDialog(null, "El ticket   "+Folio_ticket_o_abono+"   se liquido correctamente", "Aviso !!!", JOptionPane.INFORMATION_MESSAGE);
									}	
							break;
					}
			}
		};
	}
}
