package Cat_Auditoria;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Conexiones_SQL.BuscarTablasModel;
import Obj_Lista_de_Raya.Obj_Establecimiento;


@SuppressWarnings("serial")
public class Cat_Concentrado_Cortes extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	String[] vector = new Obj_Establecimiento().Combo_Establecimiento_Concentrado();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbConcentrado = new JComboBox(vector);
	
//	JDateChooser calendario = new JDateChooser();
//	JTextField txtPlanes = new Componentes().text(new JTextField(), "Planes", 15, "Double");
	
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cat_Concentrado_Cortes(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/filter_icon&16.png"));
		this.setTitle("Concentrado para cortes");
		panel.setBorder(BorderFactory.createTitledBorder("Concentrado de cortes"));
		trsfiltro = new TableRowSorter(modelo_establecimiento_para_concentrado); 
		tabla_establecimiento_para_concentrado.setRowSorter(trsfiltro);
		
//		panel.add(new JLabel("Fecha: ")).setBounds(10,20,40,20);
//		panel.add(calendario).setBounds(60,20,90,20);
		
		panel.add(new JLabel("Concentrado: ")).setBounds(60,20,80,20);
		panel.add(cmbConcentrado).setBounds(150,20,220,20);
		
		panel.add(scroll_establecimiento_para_concentrado).setBounds(60,50,310,250);
		
//		panel.add(new JLabel("Planes:")).setBounds(10,310,70,20);
//		panel.add(txtPlanes).setBounds(60,310,70,20);
		
		panel.add(btnGenerar).setBounds(290,310,80,20);
		
		tablaRender();
		
		cmbConcentrado.addActionListener(opFiltro);
		btnGenerar.addActionListener(opGenerar);
		
		cont.add(panel);
		this.setSize(400,380);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
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
			
			if(cmbConcentrado.getSelectedIndex() == 0 || tabla_establecimiento_para_concentrado.getRowCount() == 0){
				JOptionPane.showMessageDialog(null, "Seleccionar un concentrado que contenga establecimientos clasificados", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				new Cat_Trabajos_Cortes(cmbConcentrado.getSelectedItem().toString()).setVisible(true);
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
	
	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Concentrado_Cortes().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}

}
