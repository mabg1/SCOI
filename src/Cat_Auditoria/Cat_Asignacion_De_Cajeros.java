package Cat_Auditoria;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Conexiones_SQL.Connexion;
import Obj_Auditoria.Obj_Movimiento_De_Asignacion;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Asignacion_De_Cajeros extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	DefaultTableModel model = new DefaultTableModel(0,2){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento_Empleados();
	@SuppressWarnings("rawtypes")
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	JTextField txtBuscar = new Componentes().text(new JTextField(), "Nombre de Empleado", 50, "String");
	
	Border blackline;
	
	int folio = 0;
			
	@SuppressWarnings("rawtypes")
	public Cat_Asignacion_De_Cajeros()	{
		this.setTitle("Seleccion De Cajero Para Asignacion");
		
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		this.panel.setBorder(BorderFactory.createTitledBorder(blackline, "Filtrar"));
		
		trsfiltro = new TableRowSorter(model); 
		tabla.setRowSorter(trsfiltro);  
		
		panel.add(new JLabel("Establecimineto: ")).setBounds(20,15,100,20);
		panel.add(cmbEstablecimiento).setBounds(120, 15, 305, 20);
		panel.add(new JLabel("Buscar: ")).setBounds(20,40,70,20);
		panel.add(txtBuscar).setBounds(85,40,340,20);
		panel.add(getPanelTabla()).setBounds(10,65,415,450);
		
		cont.add(panel);
		
		txtBuscar.addKeyListener(opReduccion);
		
		agregar(tabla);
		
		this.setSize(440,560);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	        		if(cmbEstablecimiento.getSelectedIndex()==0){
	        			JOptionPane.showMessageDialog(null, "Seleccione un Establecimineto", "Aviso!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
	    				return;
	        		}else{
	        			int fila = tabla.getSelectedRow();
	        			
	        			String estab = cmbEstablecimiento.getSelectedItem()+"";
	        			folio =  Integer.valueOf(tabla.getValueAt(fila, 0).toString().trim());

	        			new Cat_Estender_Movimientos_De_Asignacion(folio,estab).setVisible(true);
	        		}
	        	}
	        }
        });
    }
	
	KeyAdapter opReduccion = new KeyAdapter() { 
		public void keyReleased(final KeyEvent e) { 
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1));
        } 
    };
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMaxWidth(70);
		tabla.getColumnModel().getColumn(0).setMinWidth(70);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(1).setMaxWidth(335);
		tabla.getColumnModel().getColumn(1).setMinWidth(335);
		
		TableCellRenderer render = new TableCellRenderer() 
		{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
		
				if(row%2==0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
				} 
			return lbl; 
			} 
		}; 
						tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select tb_empleado.folio as Folio," +
					"					tb_empleado.nombre + ' ' +	tb_empleado.ap_paterno + ' ' +  tb_empleado.ap_materno as NombreCompleto" +
					"					from tb_empleado where tb_empleado.status=1 and puesto_id=32" ); // 32 = puesto de cajera(o)
			
			while (rs.next())
			{ 
			   String [] fila = new String[2];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	KeyListener validaCantidad = new KeyListener() {
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();				
			if(((caracter < '0') ||	
			    	(caracter > '9')) && 
			    	(caracter != '.' )){
			    	e.consume();
			    	}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent arg0) {}	
	};
	
	KeyListener validaNumericoConPunto = new KeyListener() {
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.')){
		    	e.consume();
		    	}
		}
		public void keyPressed(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
	};
	
	public class Cat_Estender_Movimientos_De_Asignacion extends Cat_Movimiento_De_Asignacion{

		public Cat_Estender_Movimientos_De_Asignacion(int folio,String establecimiento) {
//			super(folio, establecimiento);
			
			try {
				
				Obj_Movimiento_De_Asignacion asignacion = new Obj_Movimiento_De_Asignacion().buscarAsignacion(folio, establecimiento);
				
				txtEmpleado.setText(asignacion.getEmpleado());
				txtAsignacion.setText(asignacion.getAsignacion());
				
				Date fechaIn = new SimpleDateFormat("dd/MM/yyyy").parse(asignacion.getFechaIn());
				fechaInicio.setDate(fechaIn);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			btnGuardar.addActionListener(opGuardar);
		}
		
		ActionListener opGuardar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validaCampos() != ""){
					JOptionPane.showMessageDialog(null, "Los Siguientes Campos Son Requeridos:\n"+validaCampos(), "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}else{
					
					Obj_Movimiento_De_Asignacion movimiento = new Obj_Movimiento_De_Asignacion();
					movimiento.setFolio_empleado(folio);
					movimiento.setEstablecimiento(cmbEstablecimiento.getSelectedItem()+"");
					movimiento.setAsignacion(txtAsignacion.getText());
					movimiento.setFechaIn(new SimpleDateFormat("dd/MM/yyyy").format(fechaInicio.getDate()));
					movimiento.setFechaFin(new SimpleDateFormat("dd/MM/yyyy").format(fechaFinal.getDate()));
					
					if(movimiento.buscar_empleado_vigente_en_asignacion(folio,cmbEstablecimiento.getSelectedItem().toString().trim())){
						
						JOptionPane.showMessageDialog(null,"No Puede Realizar La Asignacion\n Ya Que El Empleado Se Encuentra Asignado","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
						
					}else{
					
						if(movimiento.guardarAsignacion()){
							
							JOptionPane.showMessageDialog(null,"Asignacion Guardada","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
							
						}else{
							
							JOptionPane.showMessageDialog(null, "La Asignacion No Pudo Darse De Alta", "Error", JOptionPane.ERROR_MESSAGE);
							return;
							
						}
					}
				}
			}
		};
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Asignacion_De_Cajeros().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
