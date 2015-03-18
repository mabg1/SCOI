package Cat_Auditoria;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Container;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.swing.JOptionPane;
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
public class Cat_Filtro_De_Asignacion extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	Object[][] MatrizFiltro ;
	
	DefaultTableModel modeloFiltro = new DefaultTableModel(null,
            new String[]{"Asignacion", "F. Cajero(a)","Nombre Cajera(o)","Total","Cod Estab","Establecimiento","Fecha de Asignacion","Fecha de Liquidacion",""}
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
	
	JTable tablaFiltro = new JTable(modeloFiltro);
	JScrollPane scroll = new JScrollPane(tablaFiltro,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    @SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
    
	JTextField txtFolioAsignacion = new Componentes().text(new JTextField(), "Folio Asignacion", 9, "String");
	JTextField txtFolioCajero = new Componentes().text(new JTextField(), "Folio Cajero", 9, "String");
	JTextField txtNombreCajero = new Componentes().text(new JTextField(), "Nombre Cajero", 40, "String");
	
	JButton btnCargar = new JButton("Cargar");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cat_Filtro_De_Asignacion() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagen/Accounting.png"));
		setTitle("Filtro De Asignaciones");
		campo.setBorder(BorderFactory.createTitledBorder("Asignaciones Liquidadas"));
		
		trsfiltro = new TableRowSorter(modeloFiltro); 
		tablaFiltro.setRowSorter(trsfiltro); 
		
		tablaRender();
		
		campo.add(txtFolioAsignacion).setBounds(10,38,70,20);
		campo.add(txtFolioCajero).setBounds(80,38,70,20);
		campo.add(txtNombreCajero).setBounds(150,38,210,20);
		campo.add(btnCargar).setBounds(925,18,80,30);
		
		campo.add(scroll).setBounds(10,60,995,320);
		
		cont.add(campo);
		
		txtFolioAsignacion.addKeyListener(opFiltroAsignacion);
		txtFolioCajero.addKeyListener(opFiltroFolioCajero);
		txtNombreCajero.addKeyListener(opFiltroNombreCajero);
		
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
          	  txtNombreCajero.requestFocus();
          	enterauto();	  
           }
        });
        
		setSize(1020,450);
		setResizable(false);
		setLocationRelativeTo(null);
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
	
 	     
	@SuppressWarnings("unchecked")
	public void limpiar_filtro(){
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
		trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
		
		txtFolioAsignacion.setText("");
		txtFolioCajero.setText("");
		txtNombreCajero.setText("");
	}
	
	public void tablaRender(){
		
		tablaFiltro.getTableHeader().setReorderingAllowed(false) ;
		
		tablaFiltro.getColumnModel().getColumn(0).setMaxWidth(70);
		tablaFiltro.getColumnModel().getColumn(0).setMinWidth(70);
		tablaFiltro.getColumnModel().getColumn(1).setMaxWidth(70);
		tablaFiltro.getColumnModel().getColumn(1).setMinWidth(70);
		tablaFiltro.getColumnModel().getColumn(2).setMaxWidth(210);
		tablaFiltro.getColumnModel().getColumn(2).setMinWidth(210);
		tablaFiltro.getColumnModel().getColumn(3).setMaxWidth(90);
		tablaFiltro.getColumnModel().getColumn(3).setMinWidth(90);
		tablaFiltro.getColumnModel().getColumn(4).setMaxWidth(70);
		tablaFiltro.getColumnModel().getColumn(4).setMinWidth(70);
		tablaFiltro.getColumnModel().getColumn(5).setMaxWidth(160);
		tablaFiltro.getColumnModel().getColumn(5).setMinWidth(160);
		tablaFiltro.getColumnModel().getColumn(6).setMaxWidth(140);
		tablaFiltro.getColumnModel().getColumn(6).setMinWidth(140);
		tablaFiltro.getColumnModel().getColumn(7).setMaxWidth(140);
		tablaFiltro.getColumnModel().getColumn(7).setMinWidth(140);
		tablaFiltro.getColumnModel().getColumn(8).setMaxWidth(25);
		tablaFiltro.getColumnModel().getColumn(8).setMinWidth(25);

		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				tablaFiltro.setAutoResizeMode(JTable.AUTO_RESIZE_OFF );
				
				Component componente = null;
				
				switch(column){
					case 0: 
						componente = new JLabel(value == null? "": value.toString());
						if(row %2 == 0){
							((JComponent) componente).setOpaque(true); 
							componente.setBackground(new java.awt.Color(177,177,177));	
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
		tablaFiltro.getColumnModel().getColumn(0).setCellRenderer(render); 
		tablaFiltro.getColumnModel().getColumn(1).setCellRenderer(render); 
		tablaFiltro.getColumnModel().getColumn(2).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(3).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(4).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(5).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(6).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(7).setCellRenderer(render);
		tablaFiltro.getColumnModel().getColumn(8).setCellRenderer(render);
		
	}
	
	 	public Object[][] getTablaFiltro(String cadena_asignaciones_en_uso,String Establecimiento){
	 		
		String cadenaFinal="";
		String cadenaAsignacionesGuardadasEnSCOI="";
		
	
	 		String asignacionesSCOI ="select asignacion from tb_tabla_de_asignaciones_para_cortes";
			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery(asignacionesSCOI);
				
				while(rs.next()){	cadenaAsignacionesGuardadasEnSCOI+= "'"+(rs.getString(1).trim())+"',";	}
									cadenaFinal = cadenaAsignacionesGuardadasEnSCOI+cadena_asignaciones_en_uso;
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	 	
////////////RECOPILACION DE ASIGNACIONES////////////////////////////////////////////////////				
	 	String recopilacion_asignaciones="  exec sp_Recopilacion_IZAGAR_de_Asignaciones_y_cajeros  " ;	
		Connection con = new Connexion().conexion_IZAGAR();
		PreparedStatement pstmt = null;
	 	try {
	 		pstmt = con.prepareStatement(recopilacion_asignaciones);
	 		pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("SQLException: "+e.getMessage());
			if(con != null){
				try{
					System.out.println("La transacción ha sido abortada");
					con.rollback();
					JOptionPane.showMessageDialog(null, "Error en Cat_Filtro_De_Asignaciones  sp_Recopilacion_IZAGAR_de_Asignaciones_y_cajeros "+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException ex){
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Filtro_De_Asignaciones  sp_Recopilacion_IZAGAR_de_Asignaciones_y_cajeros "+ex.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
			}
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
////////////////////////////////////////////////////////////////////////	 	
	 	
		String todos ="  DECLARE @fecha_inicial varchar(50)    ,@fecha_final varchar(50) " +
				"                SET @fecha_inicial=convert(varchar(20), getdate()-3" +
				",103)+' 00:00:00' " +
				"  		         SET @fecha_final=convert(varchar(20), getdate(),103)+' 23:59:59'" +
				"             SELECT Asignacion,Cajero,Nombre_Cajero,Total,Cod_Estab,Establecimiento" +
				"                   ,convert(varchar(20),Fecha_Asignacion,103)+' '+ convert(varchar(20), Fecha_Asignacion,108) as Fecha_Asignacion " +
				"                   ,convert(varchar(20),Fecha_Liquidacion,103)+' '+ convert(varchar(20), Fecha_Liquidacion,108) as Fecha_Liquidacion " +
				"                  FROM IZAGAR_Relacion_de_Asignaciones_Liquidadas" +
				"               WHERE (Fecha_Liquidacion>= @fecha_inicial and Fecha_Liquidacion <= @fecha_final) and Establecimiento='"+Establecimiento+ "' and Asignacion not in ("+cadenaFinal+")"+ 
				"		        ORDER BY Fecha_Liquidacion desc "   ;		
		
		cadenaFinal="";
		
		try {
			s = new Connexion().conexion_IZAGAR().createStatement();
			rs = s.executeQuery(todos);
						
			MatrizFiltro = new Object[getFilasIZAGAR(todos)][9];
			int i=0;
			while(rs.next()){
				
				MatrizFiltro[i][0] = "   "+rs.getString(1).trim();
				MatrizFiltro[i][1] = "   "+rs.getString(2).trim();
				MatrizFiltro[i][2] = "   "+rs.getString(3).trim();
				MatrizFiltro[i][3] = "   "+rs.getString(4).trim();
				MatrizFiltro[i][4] = "   "+rs.getString(5).trim();
				MatrizFiltro[i][5] = "   "+rs.getString(6).trim();
				MatrizFiltro[i][6] = "   "+rs.getString(7).trim();
				MatrizFiltro[i][7] = "   "+rs.getString(8).trim();
				MatrizFiltro[i][8] = "false";
				
				i++;
			}
			rs.close();
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
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioAsignacion.getText().toUpperCase(), 0));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	KeyListener opFiltroFolioCajero = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioCajero.getText().toUpperCase(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	KeyListener opFiltroNombreCajero = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombreCajero.getText().toUpperCase(), 2));
		}
		public void keyTyped(KeyEvent arg0) {
//			char caracter = arg0.getKeyChar();
//			if(((caracter < '0') ||
//				(caracter > '9')) &&
//			    (caracter != KeyEvent.VK_BACK_SPACE)){
//				arg0.consume(); 
//			}	
		}
		public void keyPressed(KeyEvent arg0) {}		
	};

	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Filtro_De_Asignacion().setVisible(true);
		}catch(Exception e){
			
		}
	}
}
