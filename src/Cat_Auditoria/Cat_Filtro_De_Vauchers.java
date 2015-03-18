package Cat_Auditoria;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Conexiones_SQL.Connexion;
import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Filtro_De_Vauchers extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	Object[][] MatrizFiltro ;
	
	DefaultTableModel modelo_vaucher_filtro = new DefaultTableModel(null,
            new String[]{"Ticket", "Afiliacion", "Numero De Targeta", "Fecha E.", "Cod. Aut.", "Tipo De Targeta", "Banco Emisor", "Tipo De Operacion", "Fecha Autorizacion", "Importe","Asignacion","Retiro cliente", "*"}
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
        	 	case 12 : return true;
        	 	} 				
 			return false;
 		}
	};
	
	JTable tabla_vaucher_filtro = new JTable(modelo_vaucher_filtro);
	JScrollPane scroll = new JScrollPane(tabla_vaucher_filtro,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    @SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
    
	JTextField txtFolioTicket = new Componentes().text(new JTextField(), "Folio Asignacion", 9, "String");
	
	JButton btnCargar = new JButton("Cargar");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cat_Filtro_De_Vauchers() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagen/tarjeta-de-credito-visa-icono-8242-32.png"));
		setTitle("Filtro De Vouchers");
		campo.setBorder(BorderFactory.createTitledBorder("Seleccione un Voucher"));
		
		trsfiltro = new TableRowSorter(modelo_vaucher_filtro); 
		tabla_vaucher_filtro.setRowSorter(trsfiltro); 
				
		
		tablaRender();
		
		
		campo.add(txtFolioTicket).setBounds(10,38,90,20);
		campo.add(btnCargar).setBounds(915,38,80,20);
		
		campo.add(scroll).setBounds(10,60,985,320);
		
		cont.add(campo);
		
		txtFolioTicket.addKeyListener(opFiltroAsignacion);
		
		setSize(1014,430);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unchecked")
	public void limpiar_filtro(){
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
		
		txtFolioTicket.setText("");
	}
	
	public void tablaRender(){
		
		tabla_vaucher_filtro.getTableHeader().setReorderingAllowed(false) ;
		
		tabla_vaucher_filtro.getColumnModel().getColumn(0).setMaxWidth(90);
		tabla_vaucher_filtro.getColumnModel().getColumn(0).setMinWidth(90);
		tabla_vaucher_filtro.getColumnModel().getColumn(1).setMaxWidth(80);
		tabla_vaucher_filtro.getColumnModel().getColumn(1).setMinWidth(80);
		tabla_vaucher_filtro.getColumnModel().getColumn(2).setMaxWidth(110);
		tabla_vaucher_filtro.getColumnModel().getColumn(2).setMinWidth(110);
		tabla_vaucher_filtro.getColumnModel().getColumn(3).setMaxWidth(60);
		tabla_vaucher_filtro.getColumnModel().getColumn(3).setMinWidth(60);
		tabla_vaucher_filtro.getColumnModel().getColumn(4).setMaxWidth(60);
		tabla_vaucher_filtro.getColumnModel().getColumn(4).setMinWidth(60);
		tabla_vaucher_filtro.getColumnModel().getColumn(5).setMaxWidth(110);
		tabla_vaucher_filtro.getColumnModel().getColumn(5).setMinWidth(110);
		tabla_vaucher_filtro.getColumnModel().getColumn(6).setMaxWidth(100);
		tabla_vaucher_filtro.getColumnModel().getColumn(6).setMinWidth(100);
		tabla_vaucher_filtro.getColumnModel().getColumn(7).setMaxWidth(70);
		tabla_vaucher_filtro.getColumnModel().getColumn(7).setMinWidth(70);
		tabla_vaucher_filtro.getColumnModel().getColumn(8).setMaxWidth(130);
		tabla_vaucher_filtro.getColumnModel().getColumn(8).setMinWidth(130);
		tabla_vaucher_filtro.getColumnModel().getColumn(9).setMaxWidth(70);
		tabla_vaucher_filtro.getColumnModel().getColumn(9).setMinWidth(70);
		tabla_vaucher_filtro.getColumnModel().getColumn(10).setMaxWidth(30);
		tabla_vaucher_filtro.getColumnModel().getColumn(10).setMinWidth(30);
		tabla_vaucher_filtro.getColumnModel().getColumn(11).setMaxWidth(30);
		tabla_vaucher_filtro.getColumnModel().getColumn(11).setMinWidth(30);
		tabla_vaucher_filtro.getColumnModel().getColumn(12).setMaxWidth(25);
		tabla_vaucher_filtro.getColumnModel().getColumn(12).setMinWidth(25);

		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				tabla_vaucher_filtro.setAutoResizeMode(JTable.AUTO_RESIZE_OFF );
				
				Component componente = null;
				
				switch(column){
					case 0: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
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
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
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
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
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
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
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
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
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
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
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
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
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
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
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
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 9:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 10:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 11:
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						if(table.getSelectedRow() == row){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(186,143,73));
						}
						((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
						break;
					case 12: 
						componente = new JCheckBox("",Boolean.parseBoolean(value.toString()));
						if(row%2==0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
						}
						if(Boolean.parseBoolean(modelo_vaucher_filtro.getValueAt(row,3).toString())){
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
		tabla_vaucher_filtro.getColumnModel().getColumn(0).setCellRenderer(render); 
		tabla_vaucher_filtro.getColumnModel().getColumn(1).setCellRenderer(render); 
		tabla_vaucher_filtro.getColumnModel().getColumn(2).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(3).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(4).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(5).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(6).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(7).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(8).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(9).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(10).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(11).setCellRenderer(render);
		tabla_vaucher_filtro.getColumnModel().getColumn(12).setCellRenderer(render);
		
	}
	
	 	public Object[][] getTablaFiltro(String cadenaAsignaciones, String cadena_de_vouchers_en_uso){
//		String todos = "exec sp_Reporte_IZAGAR_de_Asignaciones_del_dia";
//	 		System.out.println(cadenaAsignaciones);
		String todos = "select  autorizaciones_bancarias.folio as Ticket " +
						",equipos_perifericos.borrar as Afiliacion " +
						",'************'+SUBSTRING(autorizaciones_bancarias.numero_tarjeta,4,4) as Numero_Tarjeta " +
						",autorizaciones_bancarias.fecha_expiracion as Fecha_Expiracion " +
						",autorizaciones_bancarias.codigo_autorizacion as Codigo_Autorizacion " +
						",autorizaciones_bancarias.Tipo_tarjeta as Tipo_de_Tarjeta " +
						",autorizaciones_bancarias.banco_emisor as Banco_Emisor " +
						",autorizaciones_bancarias.tipo_operacion as Tipo_Operacion " +
						",CONVERT(VARCHAR(20),autorizaciones_bancarias.fecha,103)+' '+CONVERT(VARCHAR(20),autorizaciones_bancarias.fecha,108) as Fecha_Autorizacion " +
						",autorizaciones_bancarias.monto as Importe " +
						",facremtick.folio_cajero as asignacion " +
						",isnull(liquidaciones_tickets.importe,0) as retiro_cliente  " +
						",'false' as selector " +
						"from autorizaciones_bancarias " +
						"inner join equipos_perifericos_equipo_bms on equipos_perifericos_equipo_bms.equipo_bms=autorizaciones_bancarias.equipo " +
						"inner join equipos_perifericos on equipos_perifericos.equipo_periferico=equipos_perifericos_equipo_bms.equipo_periferico " +
						"inner join facremtick on facremtick.folio=autorizaciones_bancarias.folio " +
						"left outer join liquidaciones_tickets on  liquidaciones_tickets.ticket=  autorizaciones_bancarias.folio and folio_documento = 'RetiroCte' " +
						"where autorizaciones_bancarias.folio in(select folio from facremtick where folio_cajero in ("+cadenaAsignaciones+")) and equipos_perifericos.tipo_periferico='P' " +
						"and autorizaciones_bancarias.folio not in ("+cadena_de_vouchers_en_uso+")";		

		Statement s;
		ResultSet rs;
		try {
			s = new Connexion().conexion_IZAGAR().createStatement();
			rs = s.executeQuery(todos);
			
			MatrizFiltro = new Object[getFilasIZAGAR(todos)][13];
			int i=0;
			while(rs.next()){
				MatrizFiltro[i][0] = "   "+ (rs.getString(1).trim().equals("")?"-":rs.getString(1).trim());
				MatrizFiltro[i][1] = "   "+ (rs.getString(2).trim().equals("")?"-":rs.getString(2).trim());
				MatrizFiltro[i][2] = "   "+ (rs.getString(3).trim().equals("")?"-":rs.getString(3).trim());
				MatrizFiltro[i][3] = "   "+ (rs.getString(4).trim().equals("")?"-":rs.getString(4).trim());
				MatrizFiltro[i][4] = "   "+ (rs.getString(5).trim().equals("")?"-":rs.getString(5).trim());
				MatrizFiltro[i][5] = "   "+ (rs.getString(6).trim().equals("")?"-":rs.getString(6).trim());
				MatrizFiltro[i][6] = "   "+ (rs.getString(7).trim().equals("")?"-":rs.getString(7).trim());
				MatrizFiltro[i][7] = "   "+ (rs.getString(8).trim().equals("")?"-":rs.getString(8).trim());
				MatrizFiltro[i][8] = "   "+ (rs.getString(9).trim().equals("")?"-":rs.getString(9).trim());
				MatrizFiltro[i][9] = "   "+ (rs.getString(10).trim().equals("")?"-":rs.getString(10).trim());
				MatrizFiltro[i][10] = "   "+(rs.getString(11).trim().equals("")?"-":rs.getString(11).trim());
				MatrizFiltro[i][11] = "   "+(rs.getString(10).trim().equals("")?"-":rs.getString(12).trim());
				MatrizFiltro[i][12] = 		(rs.getString(11).trim().equals("")?"-":rs.getString(13).trim());
				
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return MatrizFiltro; 
	}
   	
   	public int getFilasIZAGAR(String qry){
		int filas=0;
		Statement stmt = null;
		try {
			stmt = new Connexion().conexion_IZAGAR().createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}	
	public int getFilasSCOI(String qry){
		int filas=0;
		Statement stmt = null;
		try {
			stmt = new Connexion().conexion_IZAGAR().createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}
	
	KeyListener opFiltroAsignacion = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioTicket.getText().toUpperCase(), 0));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	

	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Filtro_De_Vauchers().setVisible(true);
		}catch(Exception e){
			
		}
	}
}
