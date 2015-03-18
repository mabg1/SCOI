package Biblioteca;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Conexiones_SQL.BuscarTablasModel;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class PruebaInternalFrame extends JFrame{

	Container cont = getContentPane();
	JDesktopPane dp = new JDesktopPane();
	
	JInternalFrame internal = new JInternalFrame("Filtrar");
	JLayeredPane panel = new JLayeredPane();
	
	JInternalFrame internal2 = new JInternalFrame("Un Internal Frame");
	JLayeredPane panel2 = new JLayeredPane();
	
	
	JDateChooser calendario = new JDateChooser();
	
	String[] vector = new Obj_Establecimiento().Combo_Establecimiento_Concentrado();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbConcentrado = new JComboBox(vector);
	
	JTextField txtPlanes = new Componentes().text(new JTextField(), "Planes", 15, "Double");
	
	JButton btnGenerar = new JButton("Generar");
	
	DefaultTableModel modelo_establecimiento_para_concentrado = new DefaultTableModel(new BuscarTablasModel().tabla_establecimientos_para_concentrado(), new String[]{"Establecimiento", "grupo"}){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
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
        	 	} 				
 			return false;
 		}
	};
	
	JTable tabla_establecimiento_para_concentrado = new JTable(modelo_establecimiento_para_concentrado);
	JScrollPane scroll_establecimiento_para_concentrado = new JScrollPane(tabla_establecimiento_para_concentrado,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PruebaInternalFrame()
	{
//		Se llenal el panel con los componentes
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		this.setTitle("Concentrado para cortes");
		panel.setBorder(BorderFactory.createTitledBorder("Concentrado de cortes"));
		trsfiltro = new TableRowSorter(modelo_establecimiento_para_concentrado); 
		tabla_establecimiento_para_concentrado.setRowSorter(trsfiltro);
		
		panel.add(new JLabel("Fecha: ")).setBounds(10,20,40,20);
		panel.add(calendario).setBounds(60,20,90,20);
		
		panel.add(new JLabel("Concentrado: ")).setBounds(160,20,80,20);
		panel.add(cmbConcentrado).setBounds(240,20,130,20);
		
		panel.add(scroll_establecimiento_para_concentrado).setBounds(60,50,310,250);
		
		panel.add(new JLabel("Planes:")).setBounds(10,310,70,20);
		panel.add(txtPlanes).setBounds(60,310,70,20);
		
		panel.add(btnGenerar).setBounds(290,310,80,20);
		
		// Es importante darle tamaño -pack()- al JInternalFrame,
		// porque si no, tendrá tamaño 0,0 y no lo veremos.
//		internal.pack();
		internal.setSize(400,380);
		internal2.setSize(Toolkit.getDefaultToolkit().getScreenSize().width-15,Toolkit.getDefaultToolkit().getScreenSize().height-490);
		
		internal2.setLocation(0, 380);
		
		// Se visualiza el JInternalFrame 
		internal.setVisible(true);
		internal2.setVisible(true);
		
//		Por defecto el JInternalFrame no es redimensionable ni
//		tiene el botón de cerrar, así que se lo ponemos.
		
		internal.setResizable(true);//modificar tamaño de ventana interna
		internal.setIconifiable(true);//minimizar
		internal.setClosable(true);//cerrar
		
		internal2.setResizable(true);//modificar tamaño de ventana interna
		internal2.setIconifiable(true);//minimizar
		internal2.setClosable(true);//cerrar
		
		internal2.setResizable(true);
//		internal2.setClosable(true);
		
		tablaRender();
		
//		se agrega  el panel lleno al JInternalFrame
		internal.add(panel);
		internal2.add(panel2);
//		 Se mete el JInternalFrame en el JDesktopPane
		dp.add(internal);
		dp.add(internal2);
//		 Se mete el JDesktopPane en el Container
		cont.add(dp);
		
		cmbConcentrado.addActionListener(opFiltro);
		btnGenerar.addActionListener(opGenerar);
		
		// Se visualiza todo.
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-40);
//		this.setSize(100,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	ActionListener opFiltro = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbConcentrado.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbConcentrado.getSelectedItem()+"", 1));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			}
		}
	};
	
	ActionListener opGenerar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(cmbConcentrado.getSelectedIndex() != 0){
				txtPlanes.setText(tabla_establecimiento_para_concentrado.getRowCount()+"");
			}else{
				txtPlanes.setText(tabla_establecimiento_para_concentrado.getRowCount()+"");
			}
		}
	};
	
	public void tablaRender(){
		
		tabla_establecimiento_para_concentrado.getTableHeader().setReorderingAllowed(false) ;
		 
		tabla_establecimiento_para_concentrado.getColumnModel().getColumn(0).setMaxWidth(150);
		tabla_establecimiento_para_concentrado.getColumnModel().getColumn(0).setMinWidth(150);
		tabla_establecimiento_para_concentrado.getColumnModel().getColumn(1).setMaxWidth(140);
		tabla_establecimiento_para_concentrado.getColumnModel().getColumn(1).setMinWidth(140);
		
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				
				tabla_establecimiento_para_concentrado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF );
				tabla_establecimiento_para_concentrado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF );
				
				Component componente = null;
				
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
				return componente;
			} 
		}; 
		tabla_establecimiento_para_concentrado.getColumnModel().getColumn(0).setCellRenderer(render); 
		tabla_establecimiento_para_concentrado.getColumnModel().getColumn(1).setCellRenderer(render); 
		
	}

	/** Instancia esta clase */
	public static void main(String[] args) {
		new PruebaInternalFrame();
	}
}