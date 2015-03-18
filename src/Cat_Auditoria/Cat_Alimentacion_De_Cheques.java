package Cat_Auditoria;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

//guardar denominaciones
@SuppressWarnings("serial")
public class Cat_Alimentacion_De_Cheques extends JDialog {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	int filaCheque = 0;
	int columnaCheque = 1;
	
	public JToolBar menu_toolbar = new JToolBar();
	JButton btn_guardar= new JButton(new ImageIcon("Iconos/save_icon&16.png"));
	
//	JLabel lblEmpleado = new JLabel();
	JTextField txtTotal = new JTextField();
	String columnNames[] = { "Orden", "Cantidad"};
	
	
	DefaultTableModel tabla_model_cheques = new DefaultTableModel(null, columnNames) {
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
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
       	 		case 1 : return true; 
       	 	} 				
        	return false;
		}
	};
	
	JTable tabla_cheques = new JTable(tabla_model_cheques);
	JScrollPane scroll_tabla_cheques = new JScrollPane(tabla_cheques);
	
	public Cat_Alimentacion_De_Cheques(){
//		cont.setBackground(new Color(86,161,85));
		Constructor();
		
//      asigna el foco al JTextField deseado al arrancar la ventana
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){
                	tabla_cheques.setEnabled(true);
        			tabla_cheques.editCellAt(filaCheque, columnaCheque);
        			Component aComp=tabla_cheques.getEditorComponent();
        			aComp.requestFocus();
        			
             }
        });
	}
	
	public void Constructor(){
		this.setModal(true);
		this.setTitle("Captura de Cheques");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("imagen/Icono_cheque.png"));
		
		
		this.panel.add(menu_toolbar).setBounds(0,0,150,25);
//		this.panel.add(lblEmpleado).setBounds(30,35,350,20);
		
		this.panel.add(scroll_tabla_cheques).setBounds(20,60,315,420);
		
		this.panel.add(new JLabel("Total de Cantidades:")).setBounds(20,485,120,20);
		this.panel.add(txtTotal).setBounds(140,485,180,20);
		
		this.menu_toolbar.add(btn_guardar);
		this.menu_toolbar.setEnabled(false);
		this.txtTotal.setEditable(false);
		
		this.init_tabla();
		
		this.cont.add(panel);
		
		this.setSize(360,550);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void init_tabla(){
		this.tabla_cheques.getTableHeader().setReorderingAllowed(false) ;
		
    	this.tabla_cheques.getColumnModel().getColumn(0).setMaxWidth(120);
    	this.tabla_cheques.getColumnModel().getColumn(0).setMinWidth(120);		
    	this.tabla_cheques.getColumnModel().getColumn(1).setMaxWidth(290);
    	this.tabla_cheques.getColumnModel().getColumn(1).setMinWidth(290);
    	
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
				switch(column){
					case 0 : lbl.setHorizontalAlignment(SwingConstants.CENTER); break;
					case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
				}
			return lbl; 
			} 
		}; 

		this.tabla_cheques.getColumnModel().getColumn(0).setCellRenderer(render); 
		this.tabla_cheques.getColumnModel().getColumn(1).setCellRenderer(render); 
		
		float suma = 0;
		for(int i=0; i<tabla_cheques.getRowCount(); i++){
			if(tabla_model_cheques.getValueAt(i,1).toString().length() == 0){
				suma = suma + 0;
			}else{
				suma += Float.parseFloat(tabla_model_cheques.getValueAt(i,1).toString());
			}
		}
		txtTotal.setText(suma+"");
    }
	
//    private boolean isNumeric(String cadena){
//    	try {
//    		if(cadena.equals("")){
//        		return true;
//    		}else{
//    			Float.parseFloat(cadena);
//        		return true;
//    		}
//    	} catch (NumberFormatException nfe){
//    		return false;
//    	}
//    }
    
}
