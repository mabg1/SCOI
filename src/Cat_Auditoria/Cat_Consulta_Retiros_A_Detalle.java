package Cat_Auditoria;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class Cat_Consulta_Retiros_A_Detalle extends JDialog {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	int filaEfec = 0;
	int columnaEfect = 4;
	
	public JToolBar menu_toolbar = new JToolBar();
	JButton btn_guardar= new JButton(new ImageIcon("Iconos/save_icon&16.png"));
	
	JLabel lblEmpleado = new JLabel();
	JTextField txtTotal = new JTextField();
	
	DefaultTableModel model_retiros = new DefaultTableModel( null, new String[]{"Folio Retiro","$ Cantidad", "Fecha", "Supervisor(a) Realizo"}) {
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class,
	    	java.lang.Object.class,
	    	java.lang.Object.class
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
        	 } 				
 			return false;
 		}
	};
	
	JTable tabla_retiros = new JTable(model_retiros);
	JScrollPane scroll_tabla = new JScrollPane(tabla_retiros);
	
	public Cat_Consulta_Retiros_A_Detalle(){
		Constructor();
	}
	
	public void Constructor(){
		this.setModal(true);
		this.setTitle("Retiros a Detalle");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/boveda-de-dinero-en-efectivo-de-seguridad-icono-6192-32.png"));
		
		this.panel.add(scroll_tabla).setBounds(10,10,525,300);
		
		this.menu_toolbar.add(btn_guardar);
		this.menu_toolbar.setEnabled(false);
		this.txtTotal.setEditable(false);
		
		this.init_tabla();
		
		this.cont.add(panel);
		
		this.setSize(550,350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void init_tabla(){
		this.tabla_retiros.getTableHeader().setReorderingAllowed(false) ;
		
    	this.tabla_retiros.getColumnModel().getColumn(0).setMaxWidth(90);
    	this.tabla_retiros.getColumnModel().getColumn(0).setMinWidth(90);		
    	this.tabla_retiros.getColumnModel().getColumn(1).setMaxWidth(90);
    	this.tabla_retiros.getColumnModel().getColumn(1).setMinWidth(90);
    	this.tabla_retiros.getColumnModel().getColumn(2).setMaxWidth(120);
    	this.tabla_retiros.getColumnModel().getColumn(2).setMinWidth(120);		
    	this.tabla_retiros.getColumnModel().getColumn(3).setMaxWidth(320);
    	this.tabla_retiros.getColumnModel().getColumn(3).setMinWidth(320);
    	
		TableCellRenderer render = new TableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				if(row%2==0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(177,177,177));
				} 
				if(table.getSelectedRow() == row){
					lbl.setOpaque(true); 
					lbl.setBackground(new java.awt.Color(186,143,73));
				}
				
				lbl.setHorizontalAlignment(SwingConstants.LEFT);
			return lbl; 
			} 
		}; 

		this.tabla_retiros.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tabla_retiros.getColumnModel().getColumn(1).setCellRenderer(render); 
		this.tabla_retiros.getColumnModel().getColumn(2).setCellRenderer(render); 
		this.tabla_retiros.getColumnModel().getColumn(3).setCellRenderer(render); 
    }
    
}
