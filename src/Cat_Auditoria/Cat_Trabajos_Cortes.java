package Cat_Auditoria;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.Connexion;
import Conexiones_SQL.GuardarTablasModel;
import Obj_Principal.Componentes;
import Obj_Renders.CaveceraTablaRenderer;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Trabajos_Cortes extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
//	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	JButton btnRestaurar = new JButton("Restaurar");
	JButton btnCV = new JButton("A Caja Verde");
	JButton btnGenerar = new JButton("Generar");
	
	JTextField txtDepositos = new JTextField();
	JTextField txtIzacel = new JTextField();
	JTextField txtPlanes = new Componentes().text(new JTextField(), "Igresar Total De Planes", 10, "Double");
	JTextField txtPines = new JTextField();
	JTextField txtCajaVerde = new JTextField();
	
	JTextField txtTotalDelCorte= new JTextField();
	JTextField txtTotalRetiroCliente = new JTextField();
	JTextField txtTotalRecibosDeLuz = new JTextField();
	
	public static String cadenaCajaVerde(){
		String cadena = "";
			if(tabla_c_verde.getRowCount()>0){
					for(int i=0; i<tabla_c_verde.getRowCount(); i++){
						cadena+=tabla_c_verde.getValueAt(i, 1).toString().trim()+"'',''";
					}
				cadena=cadena.substring(0,cadena.length()-5);
			}
		return cadena;
	}
	
    public DefaultTableModel tabla_model_grupos = new DefaultTableModel(null, new String[]{"*","Establecimiento","F.Corte", "F.Asignacion", "Cajero", "Total Efec.", "Retiros prog.", "Cheques", "Vales", "Dolares", "F.Sodas", "PIN-PAD", "Total Corte", "Diferencia", "Retiros Clt.", "TA", "R.Luz","Apartados", "Fecha Corte", "Observacion"} ){
                    
		@SuppressWarnings({ "rawtypes" })
		Class[] types = new Class[]{
                   java.lang.Boolean.class,
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
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
                        case 0  : if(tabla_model_grupos.getValueAt(fila, 1).toString().trim().equals("IZACEL") || Double.valueOf(tabla_model_grupos.getValueAt(fila, 11).toString()) > 0){
                        				return false; 
			                        }else{
			                        	return true; 
			                        }             	
                        case 1  : return false; 
                        case 2  : return false; 
                        case 3  : return false; 
                        case 4  : return false; 
                        case 5  : return false; 
                        case 6  : return false; 
                        case 7  : return false; 
                        case 8  : return false; 
                        case 9  : return false; 
                        case 10 : return false; 
                        case 11 : return false; 
                        case 12 : return false; 
                        case 13 : return false; 
                        case 14 : return false; 
                        case 15 : return false; 
                        case 16 : return false; 
                        case 17 : return false; 
                        case 18 : return false; 
                        case 19 : return false; 
                }
                 return false;
         }
    };
	
    JTable tabla_grupos = new JTable(tabla_model_grupos);
	JScrollPane scroll_grupos = new JScrollPane(tabla_grupos,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public static DefaultTableModel tabla_model_c_verde = new DefaultTableModel(null, new String[]{"Establecimiento","F.Corte", "F.Asignacion", "Cajero", "Total Efec.", "Retiros prog.", "Cheques", "Vales", "Dolares", "F.Sodas", "PIN-PAD", "Total Corte", "Diferencia", "Retiros Clt.", "TA", "R.Luz","Apartados", "Fecha Corte", "Observacion"} ){
        
		@SuppressWarnings({ "rawtypes" })
		Class[] types = new Class[]{
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
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
                        case 5  : return false; 
                        case 6  : return false; 
                        case 7  : return false; 
                        case 8  : return false; 
                        case 9  : return false; 
                        case 10 : return false; 
                        case 11 : return false; 
                        case 12 : return false; 
                        case 13 : return false; 
                        case 14 : return false; 
                        case 15 : return false; 
                        case 16 : return false; 
                        case 17 : return false; 
                }
                 return false;
         }
    };
	
    static JTable tabla_c_verde = new JTable(tabla_model_c_verde);
	JScrollPane scroll = new JScrollPane(tabla_c_verde,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public static DefaultTableModel tabla_model_concentrado = new DefaultTableModel( null, new String[]{"Establecimiento", "Total Efec.", "Retiros prog.", "Cheques", "Vales", "Dolares", "F.Sodas", "PIN-PAD", "Total Corte", "Diferencia", "Retiros Clt.", "TA", "R.Luz","Apartados"} ){
        
		@SuppressWarnings({ "rawtypes" })
		Class[] types = new Class[]{
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
                   java.lang.Object.class, 
                   java.lang.Object.class,
                   java.lang.Object.class, 
                   java.lang.Object.class, 
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
                        case 5  : return false; 
                        case 6  : return false; 
                        case 7  : return false; 
                        case 8  : return false; 
                        case 9  : return false; 
                        case 10 : return false; 
                        case 11 : return false; 
                        case 12 : return false; 
                        case 13 : return false; 
                }
                 return false;
         }
    };
	
    JTable tabla_concentrado = new JTable(tabla_model_concentrado);
	JScrollPane scroll_concentrado = new JScrollPane(tabla_concentrado,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	Border blackline;
	
	Object[] fila_cacl= new Object[14];
	
	DecimalFormat df = new DecimalFormat("#0.00");
	
	int folio_trabajo_realizado=0;
	String grupo_corte="";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Trabajos_Cortes(String grupo_de_concentrado){
		
		grupo_corte = grupo_de_concentrado;
		
		if(new ActualizarSQL().actualizar_tabla_cortes_con_asignaciones()){
			refresh();
//			while(tabla_grupos.getRowCount()>0){
//				tabla_model_grupos.removeRow(0);
//			}
//				String[][] matriz = new BuscarTablasModel().tabla_model_trabajo_de_cortes(cadenaCajaVerde(),grupo_de_concentrado);
//	 
//				String[] fila = new String[20];
//		        for(int i=0; i<matriz.length; i++){
//		        	
//		        	for(int j=0; j<20; j++){
//		        		fila[j] = matriz[i][j]+"";
//		        	}
//		        		tabla_model_grupos.addRow(fila);
//		        }
		}else{
			JOptionPane.showMessageDialog(null, "No se validaron los folios de asignacion","Aviso",JOptionPane.WARNING_MESSAGE);
			return;
		}

		 
		 
		 
//		blackline = BorderFactory.createLineBorder(new Color(255,171,0));
		this.setTitle("Abonos Clientes");
		this.panel.setBorder(BorderFactory.createTitledBorder( "Captura de abonos clientes"));
		
		trsfiltro = new TableRowSorter(tabla_model_grupos); 
		tabla_grupos.setRowSorter(trsfiltro);  
		
		panel.add(btnRestaurar).setBounds(890, 10, 100, 20);
		panel.add(scroll).setBounds(20, 30, 970, 130);
		
//		panel.add(cmbEstablecimiento).setBounds(45, 165, 160, 20);
		panel.add(btnCV).setBounds(890, 165, 100, 20);
		panel.add(scroll_grupos).setBounds(20, 185, 970, 290);
//		panel.add(btnCalcular).setBounds(20, 0, 160, 20);
		panel.add(scroll_concentrado).setBounds(20, 480, 970, 130);
		
		int x=20, y= 615,ancho=90;
		
		panel.add(new JLabel("DEPOSITOS: ")	 ).setBounds(x, y, ancho, 20);           panel.add(new JLabel("TOTAL DEL CORTE: ")		).setBounds(x+250, y, ancho+50, 20);       
		panel.add(txtDepositos 				 ).setBounds(x+ancho+10, y, ancho, 20);  panel.add(txtTotalDelCorte						).setBounds(x+ancho+320, y, ancho, 20);    
		panel.add(new JLabel("IZACEL: ")	 ).setBounds(x, y+=25, ancho, 20);       panel.add(new JLabel("TOTAL RETIRO CLIENTES: ")).setBounds(x+250, y, ancho+50, 20);    
		panel.add(txtIzacel 				 ).setBounds(x+ancho+10, y, ancho, 20);  panel.add(txtTotalRetiroCliente				).setBounds(x+ancho+320, y, ancho, 20);  
		panel.add(new JLabel("PLAN TELCEL: ")).setBounds(x, y+=25, ancho, 20);       panel.add(new JLabel("TOTAL RECIBOS DE LUZ: ")	).setBounds(x+250, y, ancho+50, 20);    
		panel.add(txtPlanes 				 ).setBounds(x+ancho+10, y, ancho, 20);  panel.add(txtTotalRecibosDeLuz 				).setBounds(x+ancho+320, y, ancho, 20);  
		panel.add(new JLabel("PINES: ")		 ).setBounds(x, y+=25, ancho, 20);       panel.add(btnGenerar							).setBounds(x+ancho+320, y, ancho, 20);        
		panel.add(txtPines 					 ).setBounds(x+ancho+10, y, ancho, 20);             
		panel.add(new JLabel("CAJA VERDE: ") ).setBounds(x, y+=25, ancho, 20);
		panel.add(txtCajaVerde				 ).setBounds(x+ancho+10, y, ancho, 20);
		
		llamar_render();
//		calcular();
//		calcular_totales();
		
//		cmbEstablecimiento.addActionListener(opFiltro);
		btnCV.addActionListener(opCajaVerde);
		btnRestaurar.addActionListener(opRestaurar);
		btnGenerar.addActionListener(opGenerar);

		cont.add(panel);
		
		txtDepositos.setEditable(false); 			
		txtIzacel.setEditable(false);			
		txtPines.setEditable(false);
		txtCajaVerde.setEditable(false);
		txtTotalDelCorte.setEditable(false);
		txtTotalRetiroCliente.setEditable(false);
		txtTotalRecibosDeLuz.setEditable(false);

		this.setSize(1024,768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("static-access")
	public void llamar_render(){
		//		tipo de valor = imagen,chb,texto
//		tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
    
		Color fondoEncabezado_c_verde = new Color(3,178,47);
		Color fondoEncabezado_grupos = new Color(255,171,0);
		Color fondoEncabezado_concentrado = new Color(0,154,250);
		
		Color textoEncabezado = Color.black;
		
//		tabla_c_verde.getColumnModel().getColumn(0).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado_c_verde,textoEncabezado,"centro","Arial","negrita",10));
//		tabla_c_verde.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("CHB","izquierda","Arial","negrita",10));
		for(int i = 0; i<tabla_c_verde.getColumnCount(); i++){
			tabla_c_verde.getColumnModel().getColumn(i).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado_c_verde,textoEncabezado,"centro","Arial","negrita",10));
			
			switch(i){
//					case 0: tabla_c_verde.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",11)); break;
					case 0: tabla_c_verde.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",9)); break;
					case 1: tabla_c_verde.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",10)); break;
					case 2: tabla_c_verde.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",9)); break;
					case 3: tabla_c_verde.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",10)); break;
					case 16:tabla_c_verde.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",11)); break;
					case 18:tabla_c_verde.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",11)); break;
					default: tabla_c_verde.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",11)); break;
				}
		}
		
		tabla_grupos.getColumnModel().getColumn(0).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado_grupos,textoEncabezado,"centro","Arial","negrita",10));
		tabla_grupos.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("CHB","izquierda","Arial","negrita",10));
		for(int i = 1; i<tabla_grupos.getColumnCount(); i++){
			tabla_grupos.getColumnModel().getColumn(i).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado_grupos,textoEncabezado,"centro","Arial","negrita",10));
			
			switch(i){
					case 0: tabla_grupos.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",11)); break;
					case 1: tabla_grupos.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",9)); break;
					case 2: tabla_grupos.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",10)); break;
					case 3: tabla_grupos.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",9)); break;
					case 4: tabla_grupos.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",10)); break;
					case 17:tabla_grupos.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",11)); break;
					case 19:tabla_grupos.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",11)); break;
					default: tabla_grupos.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",11)); break;
				}
		}
		
		
		
		tabla_concentrado.getColumnModel().getColumn(0).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado_concentrado,textoEncabezado,"centro","Arial","negrita",10));
		tabla_concentrado.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",10));
		for(int i = 1; i<tabla_concentrado.getColumnCount(); i++){
			tabla_concentrado.getColumnModel().getColumn(i).setHeaderRenderer(new CaveceraTablaRenderer(fondoEncabezado_concentrado,textoEncabezado,"centro","Arial","negrita",10));
			
			switch(i){
					case 0: tabla_concentrado.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",11)); break;
					case 1: tabla_concentrado.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",11)); break;
					case 2: tabla_concentrado.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",11)); break;
					case 3: tabla_concentrado.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",11)); break;
					case 4: tabla_concentrado.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",11)); break;
					default: tabla_concentrado.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",11)); break;
				}
		}
    	
		int x=60;
		
    	this.tabla_c_verde.getTableHeader().setReorderingAllowed(false) ;
    	this.tabla_c_verde.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	this.tabla_c_verde.getColumnModel().getColumn(0 ).setMaxWidth(x*3-50);
    	this.tabla_c_verde.getColumnModel().getColumn(0 ).setMinWidth(x*3-50);
    	this.tabla_c_verde.getColumnModel().getColumn(1 ).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(1 ).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(2 ).setMaxWidth(x+40);
    	this.tabla_c_verde.getColumnModel().getColumn(2 ).setMinWidth(x+40);
    	                                               
    	this.tabla_c_verde.getColumnModel().getColumn(3 ).setMaxWidth(x*4);
    	this.tabla_c_verde.getColumnModel().getColumn(3 ).setMinWidth(x*4);		
    	this.tabla_c_verde.getColumnModel().getColumn(4 ).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(4 ).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(5 ).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(5 ).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(6 ).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(6 ).setMinWidth(x);
    	                                                
    	this.tabla_c_verde.getColumnModel().getColumn(7 ).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(7 ).setMinWidth(x);		
    	this.tabla_c_verde.getColumnModel().getColumn(8 ).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(8 ).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(9 ).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(9 ).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(10).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(10).setMinWidth(x);
    	
    	this.tabla_c_verde.getColumnModel().getColumn(11).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(11).setMinWidth(x);		
    	this.tabla_c_verde.getColumnModel().getColumn(12).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(12).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(13).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(13).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(14).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(14).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(15).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(15).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(16).setMaxWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(16).setMinWidth(x);
    	this.tabla_c_verde.getColumnModel().getColumn(17).setMaxWidth(x*2);
    	this.tabla_c_verde.getColumnModel().getColumn(17).setMinWidth(x*2);
    	this.tabla_c_verde.getColumnModel().getColumn(18).setMaxWidth(x*5);
    	this.tabla_c_verde.getColumnModel().getColumn(18).setMinWidth(x*5);
    	
    	this.tabla_grupos.getTableHeader().setReorderingAllowed(false) ;
    	this.tabla_grupos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	this.tabla_grupos.getColumnModel().getColumn(0 ).setMaxWidth(25);   
    	this.tabla_grupos.getColumnModel().getColumn(0 ).setMinWidth(25);	
    	this.tabla_grupos.getColumnModel().getColumn(1 ).setMaxWidth(x*3-50);   
    	this.tabla_grupos.getColumnModel().getColumn(1 ).setMinWidth(x*3-50);   
    	this.tabla_grupos.getColumnModel().getColumn(2 ).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(2 ).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(3 ).setMaxWidth(x+40); 
    	this.tabla_grupos.getColumnModel().getColumn(3 ).setMinWidth(x+40); 
    	                                               
    	this.tabla_grupos.getColumnModel().getColumn(4 ).setMaxWidth(x*4);
    	this.tabla_grupos.getColumnModel().getColumn(4 ).setMinWidth(x*3-42);		
    	this.tabla_grupos.getColumnModel().getColumn(5 ).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(5 ).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(6 ).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(6 ).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(7 ).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(7 ).setMinWidth(x);
    	                                               
    	this.tabla_grupos.getColumnModel().getColumn(8 ).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(8 ).setMinWidth(x);		
    	this.tabla_grupos.getColumnModel().getColumn(9 ).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(9 ).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(10).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(10).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(11).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(11).setMinWidth(x);
    	
    	this.tabla_grupos.getColumnModel().getColumn(12).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(12).setMinWidth(x);		
    	this.tabla_grupos.getColumnModel().getColumn(13).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(13).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(14).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(14).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(15).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(15).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(16).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(16).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(17).setMaxWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(17).setMinWidth(x);
    	this.tabla_grupos.getColumnModel().getColumn(18).setMaxWidth(x*2);
    	this.tabla_grupos.getColumnModel().getColumn(18).setMinWidth(x*2);
    	this.tabla_grupos.getColumnModel().getColumn(19).setMaxWidth(x*5);
    	this.tabla_grupos.getColumnModel().getColumn(19).setMinWidth(x*5);
    	
    	this.tabla_concentrado.getTableHeader().setReorderingAllowed(false) ;
    	this.tabla_concentrado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	this.tabla_concentrado.getColumnModel().getColumn(0 ).setMaxWidth(x*3-20);
    	this.tabla_concentrado.getColumnModel().getColumn(0 ).setMinWidth(x*3-20);
    	this.tabla_concentrado.getColumnModel().getColumn(1 ).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(1 ).setMinWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(2 ).setMaxWidth(x+10);
    	this.tabla_concentrado.getColumnModel().getColumn(2 ).setMinWidth(x+10);
    	                                                    
    	this.tabla_concentrado.getColumnModel().getColumn(3 ).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(3 ).setMinWidth(x);		
    	this.tabla_concentrado.getColumnModel().getColumn(4 ).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(4 ).setMinWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(5 ).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(5 ).setMinWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(6 ).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(6 ).setMinWidth(x);
    	                                                    
    	this.tabla_concentrado.getColumnModel().getColumn(7 ).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(7 ).setMinWidth(x);		
    	this.tabla_concentrado.getColumnModel().getColumn(8 ).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(8 ).setMinWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(9 ).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(9 ).setMinWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(10).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(10).setMinWidth(x);
    	
    	this.tabla_concentrado.getColumnModel().getColumn(11).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(11).setMinWidth(x);		
    	this.tabla_concentrado.getColumnModel().getColumn(12).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(12).setMinWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(13).setMaxWidth(x);
    	this.tabla_concentrado.getColumnModel().getColumn(13).setMinWidth(x);
    }
	
//	ActionListener opFiltro = new ActionListener(){
//		@SuppressWarnings("unchecked")
//		public void actionPerformed(ActionEvent arg0){
//			if(cmbEstablecimiento.getSelectedIndex() != 0){
//				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimiento.getSelectedItem()+"", 1));
//			}else{
//				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
//			}
//			calcular();
//			calcular_caja_verde();
//		}
//	};
	
	ActionListener opCajaVerde = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		
			Object[] vector = new Object[20];
			
			for(int i =0; i<tabla_grupos.getRowCount(); i++){
				
				if(tabla_grupos.getValueAt(i, 0).toString().trim().equals("true")){
					
					for(int j=0; j<tabla_grupos.getColumnCount()-1; j++){
							vector[j] = tabla_grupos.getValueAt(i, j+1);
					}
					tabla_model_c_verde.addRow(vector);
				}
			}
			refresh();
		}
	};
	
	ActionListener opRestaurar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			int filaSeleccionada = tabla_c_verde.getSelectedRow();
			
			if(filaSeleccionada<0){
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun registro de la caja verde","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				tabla_model_c_verde.removeRow(filaSeleccionada);
				refresh();
			}
		}
	};
	
	ActionListener opGenerar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
//			cmbEstablecimiento.setSelectedIndex(0);
			
			if(txtPlanes.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Campo Planes Se encuentra Vacio.\nIngrese Una Cantidad Para Seguir","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				
			}
			
			if(guardarTrabajo( tabla_cv(), tabla_gr())>0){
				Cat_Reporte_De_Trabajo_De_Crotes(folio_trabajo_realizado);
			}else{
				JOptionPane.showMessageDialog(null, "El trabajo no pudo ser guardado","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
		}
	};
	
	public Object[][] tabla_cv(){
		Object[][] matrizCV = new Object[tabla_c_verde.getRowCount()][tabla_c_verde.getColumnCount()];
		
		for(int i =0; i<tabla_c_verde.getRowCount(); i++){
			for(int j =0; j<tabla_c_verde.getColumnCount(); j++){
				matrizCV[i][j] = tabla_c_verde.getValueAt(i, j).toString();
			}
		}
		
		return matrizCV;
	}
	
	public Object[][] tabla_gr(){
		
		Object[][] matrizGr = new Object[tabla_grupos.getRowCount()][tabla_grupos.getColumnCount()];
		
		for(int i =0; i<tabla_grupos.getRowCount(); i++){
			for(int j =0; j<tabla_grupos.getColumnCount()-1; j++){
				matrizGr[i][j] = tabla_model_grupos.getValueAt(i, j+1).toString().trim();
			}
		}
		return matrizGr;
	}
	
	public int guardarTrabajo( Object[][] tb_CV, Object[][] tb_Gr){
		folio_trabajo_realizado=new GuardarTablasModel().Guarda_tabla_trabajos(tb_CV, tb_Gr, Double.valueOf(txtTotalDelCorte.getText()), Double.valueOf(txtTotalRetiroCliente.getText()), Double.valueOf(txtTotalRecibosDeLuz.getText()), Double.valueOf(txtIzacel.getText()), Double.valueOf(txtPlanes.getText()), Double.valueOf(txtPines.getText()), Double.valueOf(txtDepositos.getText()), Double.valueOf(txtCajaVerde.getText())); 
		return folio_trabajo_realizado;
	}
	
	public void refresh(){
		
		while(tabla_grupos.getRowCount()>0){
			tabla_model_grupos.removeRow(0);
		}
			String[][] matriz = new BuscarTablasModel().tabla_model_trabajo_de_cortes(cadenaCajaVerde(),grupo_corte);
 
			String[] fila = new String[20];
	        for(int i=0; i<matriz.length; i++){
	        	
	        	for(int j=0; j<20; j++){
	        		fila[j] = matriz[i][j]+"";
	        	}
	        		tabla_model_grupos.addRow(fila);
	        }
			calcular();
			calcular_caja_verde();
			calcular_totales();
	}
	
	public void calcular(){
		
		String estab     ="";
		String estab_aux ="";
		
		double t_efect   = 0;
		double r_prog    = 0;
		double cheques   = 0;
		double vales     = 0;
		double dolares   = 0;
		double f_sodas   = 0;
		double pin_pad   = 0;
		double t_corte   = 0;
		double dif       = 0;
		double r_clt     = 0;
		double ta        = 0;
		double luz       = 0;
		double apartados = 0;
		
		while(tabla_concentrado.getRowCount()>0){
			tabla_model_concentrado.removeRow(0);
		}
		
		if(tabla_grupos.getRowCount()<=0){
				while(tabla_concentrado.getRowCount()>0){
					tabla_model_concentrado.removeRow(0);}
				JOptionPane.showMessageDialog(null, "No existen cortes en el establecimiento seleccionado, por lo cual no puede realizarce un calculo","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
		}else{
			
			estab = tabla_grupos.getValueAt(0, 1).toString().trim();
			
				for(int i =0; i<tabla_grupos.getRowCount(); i++){
					
					estab_aux = estab;
					estab = tabla_grupos.getValueAt(i, 1).toString().trim();
					
					if(estab.equals(estab_aux)){
						
						t_efect 	+= Double.valueOf(tabla_grupos.getValueAt(i, 5 ).toString().trim());
						r_prog 		+= Double.valueOf(tabla_grupos.getValueAt(i, 6 ).toString().trim());
						cheques 	+= Double.valueOf(tabla_grupos.getValueAt(i, 7 ).toString().trim());
						vales 		+= Double.valueOf(tabla_grupos.getValueAt(i, 8 ).toString().trim());
						dolares 	+= Double.valueOf(tabla_grupos.getValueAt(i, 9 ).toString().trim());
						f_sodas 	+= Double.valueOf(tabla_grupos.getValueAt(i, 10).toString().trim());
						pin_pad 	+= Double.valueOf(tabla_grupos.getValueAt(i, 11).toString().trim());
						t_corte 	+= Double.valueOf(tabla_grupos.getValueAt(i, 12).toString().trim());
						dif 		+= Double.valueOf(tabla_grupos.getValueAt(i, 13).toString().trim());
						r_clt 		+= Double.valueOf(tabla_grupos.getValueAt(i, 14).toString().trim());
						ta 			+= Double.valueOf(tabla_grupos.getValueAt(i, 15).toString().trim());
						luz 		+= Double.valueOf(tabla_grupos.getValueAt(i, 16).toString().trim());
						apartados 	+= Double.valueOf(tabla_grupos.getValueAt(i, 17).toString().trim());
						
							if(i==tabla_grupos.getRowCount()-1){
								
								fila_cacl[0] = estab_aux.toString() ;
								fila_cacl[1] = df.format(t_efect    )    ; 
								fila_cacl[2] = df.format(r_prog     )    ; 
								fila_cacl[3] = df.format(cheques    )    ; 
								fila_cacl[4] = df.format(vales      )    ; 
								fila_cacl[5] = df.format(dolares    )    ; 
								fila_cacl[6] = df.format(f_sodas    )    ; 
								fila_cacl[7] = df.format(pin_pad    )    ; 
								fila_cacl[8] = df.format(t_corte    )    ; 
								fila_cacl[9] = df.format(dif        )    ; 
								fila_cacl[10] =df.format(r_clt      )    ; 
								fila_cacl[11] =df.format(ta         )    ; 
								fila_cacl[12] =df.format(luz        )    ; 
								fila_cacl[13] =df.format(apartados  )    ; 
								tabla_model_concentrado.addRow(fila_cacl);
							}
					}else{
						
						fila_cacl[0]  = estab_aux ;
						fila_cacl[1] = df.format(t_efect    )    ; 
						fila_cacl[2] = df.format(r_prog     )    ; 
						fila_cacl[3] = df.format(cheques    )    ; 
						fila_cacl[4] = df.format(vales      )    ; 
						fila_cacl[5] = df.format(dolares    )    ; 
						fila_cacl[6] = df.format(f_sodas    )    ; 
						fila_cacl[7] = df.format(pin_pad    )    ; 
						fila_cacl[8] = df.format(t_corte    )    ; 
						fila_cacl[9] = df.format(dif        )    ; 
						fila_cacl[10] =df.format(r_clt      )    ; 
						fila_cacl[11] =df.format(ta         )    ; 
						fila_cacl[12] =df.format(luz        )    ; 
						fila_cacl[13] =df.format(apartados  )    ; 
						tabla_model_concentrado.addRow(fila_cacl);
						
						t_efect   = 0;
						r_prog    = 0;
						cheques   = 0;
						vales     = 0;
						dolares   = 0;
						f_sodas   = 0;
						pin_pad   = 0;
						t_corte   = 0;
						dif       = 0;
						r_clt     = 0;
						ta        = 0;
						luz       = 0;
						apartados = 0;
						
						t_efect 	+= Double.valueOf(tabla_grupos.getValueAt(i, 5 ).toString().trim());
						r_prog 		+= Double.valueOf(tabla_grupos.getValueAt(i, 6 ).toString().trim());
						cheques 	+= Double.valueOf(tabla_grupos.getValueAt(i, 7 ).toString().trim());
						vales 		+= Double.valueOf(tabla_grupos.getValueAt(i, 8 ).toString().trim());
						dolares 	+= Double.valueOf(tabla_grupos.getValueAt(i, 9 ).toString().trim());
						f_sodas 	+= Double.valueOf(tabla_grupos.getValueAt(i, 10).toString().trim());
						pin_pad 	+= Double.valueOf(tabla_grupos.getValueAt(i, 11).toString().trim());
						t_corte 	+= Double.valueOf(tabla_grupos.getValueAt(i, 12).toString().trim());
						dif 		+= Double.valueOf(tabla_grupos.getValueAt(i, 13).toString().trim());
						r_clt 		+= Double.valueOf(tabla_grupos.getValueAt(i, 14).toString().trim());
						ta 			+= Double.valueOf(tabla_grupos.getValueAt(i, 15).toString().trim());
						luz 		+= Double.valueOf(tabla_grupos.getValueAt(i, 16).toString().trim());
						apartados 	+= Double.valueOf(tabla_grupos.getValueAt(i, 17).toString().trim());
					}
				}
		}
	}
	
	public void calcular_caja_verde(){
		
		double t_efect   = 0;
		double r_prog    = 0;
		double cheques   = 0;
		double vales     = 0;
		double dolares   = 0;
		double f_sodas   = 0;
		double pin_pad   = 0;
		double t_corte   = 0;
		double dif       = 0;
		double r_clt     = 0;
		double ta        = 0;
		double luz       = 0;
		double apartados = 0;
		
		if(tabla_c_verde.getRowCount()>0){
			
				for(int i =0; i<tabla_c_verde.getRowCount(); i++){
					
						t_efect 	+= Double.valueOf(tabla_c_verde.getValueAt(i, 4 ).toString().trim());
						r_prog 		+= Double.valueOf(tabla_c_verde.getValueAt(i, 5 ).toString().trim());
						cheques 	+= Double.valueOf(tabla_c_verde.getValueAt(i, 6 ).toString().trim());
						vales 		+= Double.valueOf(tabla_c_verde.getValueAt(i, 7 ).toString().trim());
						dolares 	+= Double.valueOf(tabla_c_verde.getValueAt(i, 8 ).toString().trim());
						f_sodas 	+= Double.valueOf(tabla_c_verde.getValueAt(i, 9).toString().trim());
						pin_pad 	+= Double.valueOf(tabla_c_verde.getValueAt(i, 10).toString().trim());
						t_corte 	+= Double.valueOf(tabla_c_verde.getValueAt(i, 11).toString().trim());
						dif 		+= Double.valueOf(tabla_c_verde.getValueAt(i, 12).toString().trim());
						r_clt 		+= Double.valueOf(tabla_c_verde.getValueAt(i, 13).toString().trim());
						ta 			+= Double.valueOf(tabla_c_verde.getValueAt(i, 14).toString().trim());
						luz 		+= Double.valueOf(tabla_c_verde.getValueAt(i, 15).toString().trim());
						apartados 	+= Double.valueOf(tabla_c_verde.getValueAt(i, 16).toString().trim());
					}
				
				fila_cacl[0] = "TOTAL CAJA VERDE" ;
				fila_cacl[1] = df.format(t_efect    )    ;
				fila_cacl[2] = df.format(r_prog     )    ;
				fila_cacl[3] = df.format(cheques    )    ;
				fila_cacl[4] = df.format(vales      )    ;
				fila_cacl[5] = df.format(dolares    )    ;
				fila_cacl[6] = df.format(f_sodas    )    ;
				fila_cacl[7] = df.format(pin_pad    )    ;
				fila_cacl[8] = df.format(t_corte    )    ;
				fila_cacl[9] = df.format(dif        )    ;
				fila_cacl[10] =df.format(r_clt      )    ;
				fila_cacl[11] =df.format(ta         )    ;
				fila_cacl[12] =df.format(luz        )    ;
				fila_cacl[13] =df.format(apartados  )    ;
				
				if(tabla_concentrado.getRowCount() > 0){
					if(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 0).equals("TOTAL CAJA VERDE")){
						tabla_model_concentrado.removeRow(tabla_concentrado.getRowCount()-1);
					}	
				}
				
				tabla_model_concentrado.addRow(fila_cacl);
		}
	}
	
	public void calcular_totales(){
		
//		double t_efect   = 0;
//		double r_prog    = 0;
//		double cheques   = 0;
//		double vales     = 0;
//		double dolares   = 0;

		double pin_pad   = 0;
		double t_corte   = 0;
		double r_clt     = 0;
//		double ta        = 0;
		double luz       = 0;
//		double apartados = 0;
		
		double total_izacel    = 0;
		double total_ta		   = 0;
		double total_f_sodas   = 0;
		double total_dif       = 0;
		double total_corte_cv  = 0;
		
////		double total_cheques   = 0;
////		double total_vales	   = 0;
		double total_dolar	   = 0;
		
		double total_ta_cv 	  = 0;
		double total_rluz_cv  = 0;
		double total_apart_cv = 0;
		

		
			int filasCV = 0;
			if(tabla_c_verde.getRowCount()>0){
				filasCV = 1;
			}

			for(int i =0; i<tabla_concentrado.getRowCount()-filasCV; i++){
						
////							total_cheques+= Double.valueOf(tabla_concentrado.getValueAt(i, 3 ).toString().trim());
////							total_vales	 += Double.valueOf(tabla_concentrado.getValueAt(i, 4 ).toString().trim());
							total_dolar	 += Double.valueOf(tabla_concentrado.getValueAt(i, 5 ).toString().trim());
							total_f_sodas+= Double.valueOf(tabla_concentrado.getValueAt(i, 6 ).toString().trim());
							
							pin_pad 	 += Double.valueOf(tabla_concentrado.getValueAt(i, 7 ).toString().trim());
							t_corte 	 += Double.valueOf(tabla_concentrado.getValueAt(i, 8 ).toString().trim());
							total_dif    += Double.valueOf(tabla_concentrado.getValueAt(i, 9 ).toString().trim());
							r_clt 		 += Double.valueOf(tabla_concentrado.getValueAt(i, 10).toString().trim());
							total_ta	 += Double.valueOf(tabla_concentrado.getValueAt(i, 11).toString().trim());
							luz 		 += Double.valueOf(tabla_concentrado.getValueAt(i, 12).toString().trim());
							
							if(tabla_concentrado.getValueAt(i, 0 ).toString().trim().equals("IZACEL")){
//								System.out.println(total_izacel+"  "+total_ta+"  "+tabla_concentrado.getValueAt(i, 1 ).toString().trim());
								total_izacel 	+= Double.valueOf(tabla_concentrado.getValueAt(i, 8 ).toString().trim());
							}
							
//							if(tabla_concentrado.getValueAt(i, 0 ).toString().trim().equals("TOTAL CAJA VERDE")){
//								total_corte_cv 	+= Double.valueOf(tabla_concentrado.getValueAt(i, 8 ).toString().trim());
//							}
						}
					
			if(filasCV==1){
//				txtTotalDelCorte.setText(		df.format(t_corte-Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 8 ).toString().trim())));
//				txtTotalRetiroCliente.setText(	df.format(r_clt-Double.valueOf  (tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 10).toString().trim())));
				
//				en esta parte se les suma los totales de caja verde
////				total_cheques += Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 3 ).toString().trim());
////				total_vales	  += Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 4 ).toString().trim());
				total_dolar	  += Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 5 ).toString().trim());
				total_f_sodas += Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 6 ).toString().trim());
				total_dif     += Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 9 ).toString().trim());
				
				total_corte_cv 	= Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 8 ).toString().trim());
				total_ta_cv		= Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 11).toString().trim());
//				total_rluz_cv 	= Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 12).toString().trim());
				total_apart_cv 	= Double.valueOf(tabla_concentrado.getValueAt(tabla_concentrado.getRowCount()-1, 13).toString().trim());
				
				
				txtCajaVerde.setText(df.format(	total_corte_cv
												- total_f_sodas
												- total_dolar
												+ (total_dif) ));
				
			}else{
//				txtTotalDelCorte.setText(		df.format(t_corte));
//				txtTotalRetiroCliente.setText(	df.format(r_clt));
				txtCajaVerde.setText("0.00");
			}
					
			txtTotalDelCorte.setText( df.format(t_corte + total_ta_cv + total_rluz_cv + total_apart_cv) );
			txtTotalRetiroCliente.setText(	df.format(r_clt));
			
			txtTotalRecibosDeLuz.setText(	df.format(luz));
					
//					System.out.println(total_izacel+"  "+total_ta);
					txtIzacel.setText( df.format(total_izacel + total_ta + total_ta_cv) );
					
//					pendiente
//					txtPlanes.setText(df.format(0));
					
					txtPines.setText(df.format(pin_pad+r_clt));
					
					txtDepositos.setText(df.format( Double.valueOf(txtTotalDelCorte.getText())-
													 pin_pad+
													  Double.valueOf(txtIzacel.getText())/*+
													   Double.valueOf(txtPlanes.getText())+
													   Double.valueOf(txtPines.getText())*/
							));

	}
	

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void Cat_Reporte_De_Trabajo_De_Crotes(int folio_trabajo_de_corte) {
			
//			String query_corte_caja = "exec sp_Reporte_De_Trabajo_De_Cortes "+folio_trabajo_de_corte;
//			Statement stmt = null;
			
			try {
				
//				stmt =  new Connexion().conexion().createStatement();
////			    ResultSet rs = 
//			    		stmt.executeQuery(query_corte_caja);
			    
				JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_Para_Trabajo_De_Cortes.jrxml");
				
				Map parametro = new HashMap();
				parametro.put("folio_trabajo", folio_trabajo_de_corte);
				
				JasperPrint print = JasperFillManager.fillReport(report, parametro, new Connexion().conexion());
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error En Cat_Reporte_De_Corte_De_Caja ", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
			
		}
	
//	public static void main(String[] args) {
//		try{
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			new Cat_Trabajos_Cortes("CONCENTRADO 1").setVisible(true);
//		}catch(Exception e){
//			System.err.println("Error :"+ e.getMessage());
//		}
//	}
}
