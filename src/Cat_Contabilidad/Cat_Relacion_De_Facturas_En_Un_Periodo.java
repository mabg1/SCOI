package Cat_Contabilidad;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Conexiones_SQL.Connexion;
import Obj_Contabilidad.Obj_Relacion_de_facturas_en_un_periodo;
import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Relacion_De_Facturas_En_Un_Periodo extends JDialog{
	
	DefaultTableModel modelo = new DefaultTableModel(null,
            new String[]{"Folio","C.E.","Establecimiento","C.C.","Cliente","Fecha Factura","Importe","Iva","IEPS","Costo","Contribucion","Total",
			             "Folio_Origen","CP","N_Cond_Pago","Notas","CU","N Usuario","Ts0 Importe","Ts0 IVA","Ts0 IEPS","Ts0 Neto",
			             "Ts16 Importe","Ts16 IVA","Ts16 IEPS","Ts16 Neto","Status","Fecha Cancelacion",""}
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
        	 	case 8 : return false;
        	 	case 9 : return false;
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
        	 	case 20 : return false;
        	 	case 21 : return false;
        	 	case 22 : return false;
        	 	case 23 : return false;
        	 	case 24 : return false;
        	 	case 25 : return false;
        	 	case 26 : return false;
        	 	case 27 : return false;
        	 	case 28 : return true;
        	 } 				
 			return false;
 		}
	};
    JTable tablaFacturas = new JTable(modelo);
    JScrollPane scrollAsignado = new JScrollPane(tablaFacturas);
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    DefaultTableModel modelotdd = new DefaultTableModel(null,
            new String[]{"Tbj del Dia","C.E.","Establecimiento","IVA","IEPS","Costo","Contribucion","Total","Ts0 Importe","Ts0 IVA","Ts0 IEPS","Ts0 Neto"
    	                 ,"Ts16 Importe","Ts16 IVA","Ts16 IEPS","Ts16 Neto"}
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
        	 	case 7 : return false;
        	 	case 8 : return false;
        	 	case 9 : return false;
        	 	case 10 : return false;
        	 	case 11 : return false;
        	 	case 12 : return false;
        	 	case 13 : return false;
        	 	case 14 : return false;
        	 	case 15 : return false;
        	 } 				
 			return false;
 		}
	};
    JTable tabla_Trabajo_del_dia = new JTable(modelotdd);
    JScrollPane scroll_trabajo_del_dia = new JScrollPane(tabla_Trabajo_del_dia);
    
    
    
    
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
    JTextField txtFoliofiltro = new Componentes().text(new JTextField(), "Folio de la Factura", 15, "String");
    JTextField txtcod_estabfiltro = new Componentes().text(new JTextField(), "Codigo de Establecimiento", 2, "Integer");
    JTextField txtEstablecimientofiltro = new Componentes().text(new JTextField(), "Establecimiento", 70, "String");
	
	JDateChooser c_inicio = new JDateChooser();
	JDateChooser c_final = new JDateChooser();
	JDateChooser c_dia_trabajo = new JDateChooser();
	
	JButton btngenerar = new JButton("Buscar",new ImageIcon("imagen/buscar.png"));
	JButton btnagregar = new JButton("Pasar Selecionadas",new ImageIcon("imagen/Aplicar.png"));
	JButton btnseleccionar_todo = new JButton("Seleccionar Todo",new ImageIcon("imagen/double-arrow-icone-3883-16.png"));
	
    @SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
    
    Border blackline, etched, raisedbevel, loweredbevel, empty;

     
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Relacion_De_Facturas_En_Un_Periodo(){
		
		panel.add(new JLabel("Fecha Inicio:")).setBounds(15,20,100,20);
		panel.add(c_inicio).setBounds(80,20,100,20);
		panel.add(new JLabel("Fecha Final:")).setBounds(200,20,100,20);
		panel.add(c_final).setBounds(260,20,100,20);		
		panel.add(btngenerar).setBounds(400, 20, 100, 20);
		
		panel.add(txtFoliofiltro).setBounds(15,50,50,20);
		panel.add(txtcod_estabfiltro).setBounds(65,50,31,20);
		panel.add(txtEstablecimientofiltro).setBounds(96,50,123,20);
		panel.add(new JLabel("Trabajo Del Dia:")).setBounds(520,50,120,20);
		panel.add(c_dia_trabajo).setBounds(600,50,100,20);
		panel.add(btnseleccionar_todo).setBounds(710, 50, 145, 20);
		panel.add(btnagregar).setBounds(860, 50, 145, 20);
		
		panel.add(ObtenerPanelTabla()).setBounds(15,70,990,350);
		panel.add(ObtenerPanelTabla_Trabajos_del_dia()).setBounds(15,480,990,250);
		refrestabla_Trabajo_del_dia();
		
		
		cont.add(panel);
		
		btngenerar.addActionListener(Buscar);
		btnagregar.addActionListener(Agregar);
		btnseleccionar_todo.addActionListener(Seleccionar_Todo);
		
		btnagregar.setToolTipText("<CTRL+R> Guardar las Facturas Selecionadas");
		
		btnagregar.setEnabled(false);
		btnseleccionar_todo.setEnabled(false);
		c_dia_trabajo.setEnabled(false);
		
		trsfiltro = new TableRowSorter(modelo); 
		tablaFacturas.setRowSorter(trsfiltro);
		
		txtFoliofiltro.addKeyListener(filtrofolio);
		txtcod_estabfiltro.addKeyListener(filtrocodestab);
		txtEstablecimientofiltro.addKeyListener(filtroestablecimiento);
		
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/archivo-icono-8809-32.png"));
		this.blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		this.panel.setBorder(BorderFactory.createTitledBorder(blackline,"Relacion De Facturas En Un Periodo Con Valores Por Tasas"));
		this.setTitle("Relacion De Facturas En Un Periodo");
		this.setSize(1024, 768);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	 
	private JScrollPane ObtenerPanelTabla()	{	
	tablaFacturas.getTableHeader().setReorderingAllowed(false) ;
	tablaFacturas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	
	tablaFacturas.getColumnModel().getColumn(0).setMaxWidth(50);	
	tablaFacturas.getColumnModel().getColumn(0).setMinWidth(45);
	tablaFacturas.getColumnModel().getColumn(1).setMaxWidth(32);
	tablaFacturas.getColumnModel().getColumn(1).setMinWidth(20);
	tablaFacturas.getColumnModel().getColumn(2).setMaxWidth(130);
	tablaFacturas.getColumnModel().getColumn(2).setMinWidth(120);
	tablaFacturas.getColumnModel().getColumn(3).setMaxWidth(42);
	tablaFacturas.getColumnModel().getColumn(3).setMinWidth(32);
	tablaFacturas.getColumnModel().getColumn(4).setMinWidth(250);
	tablaFacturas.getColumnModel().getColumn(4).setMaxWidth(400);
	tablaFacturas.getColumnModel().getColumn(5).setMinWidth(90);
	tablaFacturas.getColumnModel().getColumn(5).setMaxWidth(150);
	tablaFacturas.getColumnModel().getColumn(6).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(6).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(7).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(7).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(8).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(8).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(9).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(9).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(10).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(10).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(11).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(11).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(12).setMinWidth(60);
	tablaFacturas.getColumnModel().getColumn(12).setMaxWidth(70);
	tablaFacturas.getColumnModel().getColumn(13).setMinWidth(10);
	tablaFacturas.getColumnModel().getColumn(13).setMaxWidth(10);
	tablaFacturas.getColumnModel().getColumn(14).setMinWidth(50);
	tablaFacturas.getColumnModel().getColumn(14).setMaxWidth(50);
	tablaFacturas.getColumnModel().getColumn(15).setMinWidth(50);
	tablaFacturas.getColumnModel().getColumn(15).setMaxWidth(150);
	tablaFacturas.getColumnModel().getColumn(16).setMinWidth(15);
	tablaFacturas.getColumnModel().getColumn(16).setMaxWidth(15);
	tablaFacturas.getColumnModel().getColumn(17).setMinWidth(70);
	tablaFacturas.getColumnModel().getColumn(17).setMaxWidth(250);
	tablaFacturas.getColumnModel().getColumn(18).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(18).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(19).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(19).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(20).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(20).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(21).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(21).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(22).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(22).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(23).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(23).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(24).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(24).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(25).setMinWidth(55);
	tablaFacturas.getColumnModel().getColumn(25).setMaxWidth(65);
	tablaFacturas.getColumnModel().getColumn(26).setMinWidth(15);
	tablaFacturas.getColumnModel().getColumn(26).setMaxWidth(15);
	tablaFacturas.getColumnModel().getColumn(27).setMinWidth(80);
	tablaFacturas.getColumnModel().getColumn(27).setMaxWidth(120);
	tablaFacturas.getColumnModel().getColumn(28).setMinWidth(20);
	tablaFacturas.getColumnModel().getColumn(28).setMaxWidth(20);

    TableCellRenderer render = new TableCellRenderer() { 
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
		boolean hasFocus, int row, int column) { 
          		Component componente = null;
				componente = new JLabel(value == null? "": value.toString());
				
				if (column<5){
 				 ((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
				}else{ ((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
				}
          		
          		if(row %2 == 0){
					((JComponent) componente).setOpaque(true); 
					componente.setBackground(new java.awt.Color(177,177,177));	
					}
				if(table.getSelectedRow() == row){
				((JComponent) componente).setOpaque(true); 
				componente.setBackground(new java.awt.Color(186,143,73));
				}
			return componente;
		} 
	}; 
	
	for(int i=0; i<tablaFacturas.getColumnCount()-1; i++){
	    tablaFacturas.getColumnModel().getColumn(i).setCellRenderer(render); 
	}
	
		 JScrollPane scrol = new JScrollPane(tablaFacturas);
	    return scrol; 
	}
	
private void refrestabla(String FI, String FF){
		Statement s;
		ResultSet rs;
		Connexion con = new Connexion();
		try {
			s = con.conexion_IZAGAR().createStatement();
			rs = s.executeQuery("exec sp_reporte_de_facturas_en_un_periodo_con_tasas '"+FI+"','"+FF+"'");
			while (rs.next())
			{ 
			   Object [] fila = new Object[29];
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
			   fila[15] = rs.getString(16).trim();
			   fila[16] = rs.getString(17).trim();
			   fila[17] = rs.getString(18).trim();
			   fila[18] = rs.getString(19).trim();
			   fila[19] = rs.getString(20).trim();
			   fila[20] = rs.getString(21).trim();
			   fila[21] = rs.getString(22).trim();
			   fila[22] = rs.getString(23).trim();
			   fila[23] = rs.getString(24).trim();
			   fila[24] = rs.getString(25).trim();
			   fila[25] = rs.getString(26).trim();
			   fila[26] = rs.getString(27).trim();
			   fila[27] = rs.getString(28).trim();
			   fila[28] = false;
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Cat_Relacion_De_Facturas_En_Un_Periodo en la funcion refrestabla  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
	}
	
private JScrollPane ObtenerPanelTabla_Trabajos_del_dia()	{	
tabla_Trabajo_del_dia.getTableHeader().setReorderingAllowed(false) ;
tabla_Trabajo_del_dia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

tabla_Trabajo_del_dia.getColumnModel().getColumn(0).setMaxWidth(60);	
tabla_Trabajo_del_dia.getColumnModel().getColumn(0).setMinWidth(60);
tabla_Trabajo_del_dia.getColumnModel().getColumn(1).setMaxWidth(20);
tabla_Trabajo_del_dia.getColumnModel().getColumn(1).setMinWidth(20);
tabla_Trabajo_del_dia.getColumnModel().getColumn(2).setMaxWidth(130);
tabla_Trabajo_del_dia.getColumnModel().getColumn(2).setMinWidth(120);
tabla_Trabajo_del_dia.getColumnModel().getColumn(3).setMaxWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(3).setMinWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(4).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(4).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(5).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(5).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(6).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(6).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(7).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(7).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(8).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(8).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(9).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(9).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(10).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(10).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(11).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(11).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(12).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(12).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(13).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(13).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(14).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(14).setMaxWidth(75);
tabla_Trabajo_del_dia.getColumnModel().getColumn(15).setMinWidth(70);
tabla_Trabajo_del_dia.getColumnModel().getColumn(15).setMaxWidth(75);

TableCellRenderer render_2 = new TableCellRenderer() { 
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
	boolean hasFocus, int row, int column) { 
      		Component componente = null;
			componente = new JLabel(value == null? "": value.toString());
			
			if (column<3){
				 ((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
			}else{ ((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
			}
      		
      		if(row %2 == 0){
				((JComponent) componente).setOpaque(true); 
				componente.setBackground(new java.awt.Color(177,177,177));	
				}
			if(table.getSelectedRow() == row){
			((JComponent) componente).setOpaque(true); 
			componente.setBackground(new java.awt.Color(186,143,73));
			}
		return componente;
	} 
}; 

for(int i=0; i<tabla_Trabajo_del_dia.getColumnCount(); i++){
	tabla_Trabajo_del_dia.getColumnModel().getColumn(i).setCellRenderer(render_2); 
}

	 JScrollPane scrol_2 = new JScrollPane(tabla_Trabajo_del_dia);
    return scrol_2; 
}

//private String cadena(){
//	Statement s;
//	ResultSet rs;
//	Connexion con = new Connexion();
//	
//	String cadenaDeFolios = "";
//	try{
//		
//		s = con.conexion().createStatement();
//		rs = s.executeQuery("select folio as Folio FROM IZAGAR_trabajo_dia_facturas ORDER BY fecha_trabajo_del_dia DESC");
//		
//		while(rs.next()){
//			cadenaDeFolios = cadenaDeFolios += "'"+rs.getString(1)+"'','";
//		}
//		
//		if(cadenaDeFolios.length()<2){
//			cadenaDeFolios="''";
//		}
//		else{
//			cadenaDeFolios = cadenaDeFolios.substring( 0, cadenaDeFolios.length()-3);
//		}
//		
//		
//	}catch (SQLException e1) {
//		e1.printStackTrace();
//	}
//	return cadenaDeFolios;
//} 


private void refrestabla_Trabajo_del_dia(){
	Statement s;
	ResultSet rs;
	Connexion con = new Connexion();
	try {
		s = con.conexion().createStatement();
		rs = s.executeQuery("exec sp_listado_de_trabajos_de_dia_por_establecimiento");
		while (rs.next())
		{ 
		   Object [] fila = new Object[16];
		   
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
		   fila[15] = rs.getString(16).trim();
		   modelotdd.addRow(fila); 
		}	
	} catch (SQLException e1) {
		e1.printStackTrace();
		JOptionPane.showMessageDialog(null, "Error en Cat_Relacion_De_Facturas_En_Un_Periodo en la funcion refrestabla_Trabajo_del_dia  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
	}
}

	ActionListener Buscar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

		if(validar_fechas().equals("")){
			String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
			String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
			
			if(c_inicio.getDate().before(c_final.getDate())){
				while(tablaFacturas.getRowCount()>0){
					modelo.removeRow(0);  }
				refrestabla(fecha_inicio, fecha_final);
				btngenerar.setEnabled(false);
				c_inicio.setEnabled(false);
				c_final.setEnabled(false);
				
				btnagregar.setEnabled(true);
				btnseleccionar_todo.setEnabled(true);
				c_dia_trabajo.setEnabled(true);
				
			 }else{
				JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
				return;
			}
	 	}else{
			JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fechas(),"Aviso!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		}
	};
	
	ActionListener Agregar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(validar_fecha_dia_trabajo().equals("")){
			String dia_trabajo = new SimpleDateFormat("dd/MM/yyyy").format(c_dia_trabajo.getDate())+" 00:00:00";
//			String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
//			String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
			
			if(tablaFacturas.isEditing()){
				tablaFacturas.getCellEditor().stopCellEditing();
			}
	  	        if(JOptionPane.showConfirmDialog(null,"Las Facturas Seleccionadas \n Van Hacer Pasadas Al Trabajo del Dia: Confirmar?") == 0){
	  	    			Obj_Relacion_de_facturas_en_un_periodo facturas_en_un_periodo = new Obj_Relacion_de_facturas_en_un_periodo();
	  	    				
	  	    			
				  	    	if(facturas_en_un_periodo.guardar(tabla_guardar(),dia_trabajo)){
				  	    		
				  	    	      while(tablaFacturas.getRowCount()>0){ modelo.removeRow(0);  }
				  	    	      while(tabla_Trabajo_del_dia.getRowCount()>0){ modelotdd.removeRow(0);  }
				  	    	      btnagregar.setEnabled(false);
				  	    	      btnseleccionar_todo.setEnabled(false);
				  	    	      btngenerar.setEnabled(false);
				  	    	      c_inicio.setEnabled(false);
				  	    	      c_final.setEnabled(false);
				  	    	      c_dia_trabajo.setEnabled(false);
				  	    	      
				  	    	    refrestabla_Trabajo_del_dia();
							      
						     	  JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
				  	    	}else{
								JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
								return;
				  	    	}
		  	    }else{
				 JOptionPane.showMessageDialog(null,"Se Cancelo El Traspaso De La Factura","Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				 return;
			    }	
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fecha_dia_trabajo(),"Aviso!", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	};
	
	private Object[][] tabla_guardar(){
		Object[][] matriz = new Object[tablaFacturas.getRowCount()][29];
		for(int i=0; i<tablaFacturas.getRowCount()-1; i++){
			
			matriz[i][0] = tablaFacturas.getValueAt(i,0).toString().trim();                     //folio
			matriz[i][1] = Integer.parseInt(tablaFacturas.getValueAt(i,1).toString().trim());   //cod_estab
			matriz[i][2] = tablaFacturas.getValueAt(i,2).toString().trim();                     //Establecimiento
			matriz[i][3] = tablaFacturas.getValueAt(i,3).toString().trim();                     //cod_cliente
			matriz[i][4] = tablaFacturas.getValueAt(i,4).toString().trim();                     //cliente
			matriz[i][5] = tablaFacturas.getValueAt(i,5).toString().trim();                     //Fecha_factura
			matriz[i][6] = Float.parseFloat(tablaFacturas.getValueAt(i,6).toString().trim());   //importe
            matriz[i][7] = Float.parseFloat(tablaFacturas.getValueAt(i,7).toString().trim());   //IVA	
            matriz[i][8] = Float.parseFloat(tablaFacturas.getValueAt(i,8).toString().trim());   //IEPS
            matriz[i][9] = Float.parseFloat(tablaFacturas.getValueAt(i,9).toString().trim());   //Costo
            matriz[i][10] = Float.parseFloat(tablaFacturas.getValueAt(i,10).toString().trim()); //Contribucion
            matriz[i][11] = Float.parseFloat(tablaFacturas.getValueAt(i,11).toString().trim()); //Total
            matriz[i][12] = tablaFacturas.getValueAt(i,12).toString().trim();                   //Folio_Origen
            matriz[i][13] = Integer.parseInt(tablaFacturas.getValueAt(i,13).toString().trim()); //Cod_Pago
            matriz[i][14] = tablaFacturas.getValueAt(i,14).toString().trim();                   //Condicion_Pago
            matriz[i][15] = tablaFacturas.getValueAt(i,15).toString().trim();                   //Notas
            matriz[i][16] = Integer.parseInt(tablaFacturas.getValueAt(i,16).toString().trim()); //Cod_Usuario
            matriz[i][17] = tablaFacturas.getValueAt(i,17).toString().trim();                   //Usuario
            matriz[i][18] = Float.parseFloat(tablaFacturas.getValueAt(i,18).toString().trim()); //Ts0_Importe
            matriz[i][19] = Float.parseFloat(tablaFacturas.getValueAt(i,19).toString().trim()); //Ts0_IVA
            matriz[i][20] = Float.parseFloat(tablaFacturas.getValueAt(i,20).toString().trim()); //Ts0_IEPS
            matriz[i][21] = Float.parseFloat(tablaFacturas.getValueAt(i,21).toString().trim()); //Ts0_Neto
            matriz[i][22] = Float.parseFloat(tablaFacturas.getValueAt(i,22).toString().trim()); //Ts16_Importe
            matriz[i][23] = Float.parseFloat(tablaFacturas.getValueAt(i,23).toString().trim()); //Ts16_IVA
            matriz[i][24] = Float.parseFloat(tablaFacturas.getValueAt(i,24).toString().trim()); //Ts16_IEPS
            matriz[i][25] = Float.parseFloat(tablaFacturas.getValueAt(i,25).toString().trim()); //Ts16_Neto
            matriz[i][26] = tablaFacturas.getValueAt(i,26).toString().trim();                   //Status
            matriz[i][27] = tablaFacturas.getValueAt(i,27).toString().trim();                   //Fecha_Cancelacion
			matriz[i][28] = tablaFacturas.getValueAt(i, 28).toString().trim();                  //Boleano  
		}
		return matriz;
	}
	
	public String validar_fecha_dia_trabajo(){
		String error = "";
		String fechadia_trabajoNull = c_dia_trabajo.getDate()+"";
	    if(fechadia_trabajoNull.equals("null"))error+= "Fecha del >>Trabajo del Dia<<\n";
		return error;
	}
	
	public String validar_fechas(){
		String error = "";
		String fechainicioNull = c_inicio.getDate()+"";
		String fechafinalNull = c_final.getDate()+"";
	    if(fechainicioNull.equals("null"))error+= ">>Fecha  inicio<<\n";
		if(fechafinalNull.equals("null"))error+= ">>Fecha Final<<\n";
		return error;
	}
	
	ActionListener Seleccionar_Todo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			for(int i=0; i<tablaFacturas.getRowCount(); i++){
				
				 modelo.setValueAt(Boolean.parseBoolean("true"), i, 28);
			}
		  }
		};
	
		
	KeyListener filtrofolio = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFoliofiltro.getText(),0));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}	
		    };
	    
	KeyListener filtrocodestab = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtcod_estabfiltro.getText(),1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}	
		    };
		    
	KeyListener filtroestablecimiento = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtEstablecimientofiltro.getText(),2));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}	
		    };
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Relacion_De_Facturas_En_Un_Periodo().setVisible(true);
		}catch(Exception e){	}
	}
}
