package Cat_Auditoria;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarTablasModel;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Agregar_Comentario_A_Corte_Guardado extends JDialog {

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio De Corte", 15, "String");
	JTextField txtCajera = new Componentes().text(new JTextField(), "Nombre De Cajera(o)", 15, "String");
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	static Object[][] cortes_guardados = new BuscarTablasModel().filtro_cortes_guardados();
	DefaultTableModel modelo = new DefaultTableModel(cortes_guardados,
            new String[]{ "Folio De Corte", "Nombre Cajera(o)", "Establecimiento", "Corte Sistema", "Fecha De Corte", "Observaciones"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
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
        	 	case 2 : return false;
        	 	case 3 : return false;
        	 	case 4 : return false;
        	 	case 5 : return false;
        	 	} 				
 			return false;
 		}
	};
	
	JTable tabla = new JTable(modelo);
	JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	Border blackline;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Agregar_Comentario_A_Corte_Guardado(){
		this.setModal(true);
		this.setTitle("Agregar Observacion A Cortes Del Dia");
		this.panel.setBorder(BorderFactory.createTitledBorder(blackline, "Filtrar"));

		
		trsfiltro = new TableRowSorter(modelo); 
		tabla.setRowSorter(trsfiltro);  
		
		panel.add(txtFolio).setBounds(20,20,90,20);
		panel.add(txtCajera).setBounds(110,20,320,20);
		
		panel.add(scroll).setBounds(20,45,970,500);
		
		cont.add(panel);
		
		agregar(tabla);
		
		llamar_render();
		
		txtFolio.addKeyListener(opFiltroAsignacion);
		txtCajera.addKeyListener(opFiltroFolioCajero);
		
		this.setSize(1024,600);
		this.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unchecked")
	public void llamar_render(){
		
		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		
		int x = 90;
		
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(x);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(x);		
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(320);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(320);
		this.tabla.getColumnModel().getColumn(2).setMaxWidth(x+50);
		this.tabla.getColumnModel().getColumn(2).setMinWidth(x+50);
		
		this.tabla.getColumnModel().getColumn(3).setMaxWidth(x);
		this.tabla.getColumnModel().getColumn(3).setMinWidth(x);		
		this.tabla.getColumnModel().getColumn(4).setMaxWidth(x+50);
		this.tabla.getColumnModel().getColumn(4).setMinWidth(x+50);
		this.tabla.getColumnModel().getColumn(5).setMaxWidth(190);
		this.tabla.getColumnModel().getColumn(5).setMinWidth(190);

		this.tabla.setRowSorter(trsfiltro);  
		
		
		tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		
		tabla.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
		tabla.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
		tabla.getColumnModel().getColumn(5).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 2){
	    			int fila = tabla.getSelectedRow();
	    			
	    				String folio_corte =  	tabla.getValueAt(fila, 0).toString().trim();
    						 String cajero =  	tabla.getValueAt(fila, 1).toString().trim();
	    			String establecimiento =  	tabla.getValueAt(fila, 2).toString().trim();
	    					  String corte = 	tabla.getValueAt(fila, 3).toString().trim();
	    					  String fecha =  	tabla.getValueAt(fila, 4).toString().trim();
	    				String Observacion =  	tabla.getValueAt(fila, 5).toString().trim();
//	    			dispose();
	    			new Cat_Comentario_A_Corte_Guardado(folio_corte,cajero,establecimiento,corte,fecha,Observacion).setVisible(true);
	        	}
	        }
        });
    }
	
	KeyListener opFiltroAsignacion = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText().toUpperCase(), 0));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	KeyListener opFiltroFolioCajero = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtCajera.getText().toUpperCase(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	
	
	
	
	
	
	public class Cat_Comentario_A_Corte_Guardado extends JDialog {

		Container cont = getContentPane();
		JLayeredPane panel = new JLayeredPane();
		
		JLabel lblFolio_corte = new JLabel("");
		JLabel lblCajero = new JLabel("");
		JLabel lblEstablecimiento = new JLabel("");
		JLabel lblCorte = new JLabel("");
		JLabel lblFecha = new JLabel("");
		
		
		JTextArea txaObservacion =new Componentes().textArea(new JTextArea(), "Observaciones", 980);
		JScrollPane scrollObservacion = new JScrollPane(txaObservacion,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JButton btnGuardar = new JButton("Guardar");
		
		String Folio_corte = "";
		
		public Cat_Comentario_A_Corte_Guardado(String folio_corte,String cajero,String establecimiento,String corte,String fecha,String observacion){
			this.setModal(true);
			this.setTitle("Observacion A Corte Seleccionado");
			this.panel.setBorder(BorderFactory.createTitledBorder(blackline, "Guardar Observacion del corte"));
			
			Folio_corte = folio_corte;
			
			lblFolio_corte.setText("Folio de corte:  "+folio_corte);
			lblCajero.setText("Cajera(o):  "+cajero);
			lblEstablecimiento.setText("Establecimiento:  "+establecimiento);
			lblCorte.setText("Corte del sistema:  $"+corte);
			lblFecha.setText("Fecha: "+fecha);
			
			int y=20;
			
			panel.add(lblFolio_corte).setBounds(15,y,150,20);
			panel.add(lblFecha).setBounds(300,y,180,20);
			panel.add(lblCajero).setBounds(15,y+=25,500,20);
			panel.add(lblEstablecimiento).setBounds(15,y+=25,440,20);
			panel.add(lblCorte).setBounds(290,y,180,20);
			
			panel.add(new JLabel("Observacion:")).setBounds(15,y+=25,90,20);
			panel.add(btnGuardar).setBounds(370,y,90,20);
			panel.add(scrollObservacion).setBounds(15,y+=25,450,220);
//			
			txaObservacion.setText(observacion);
			
			txaObservacion.setLineWrap(true); 
			txaObservacion.setWrapStyleWord(true);
//			panel.add(scroll).setBounds(20,45,970,500);
			
			cont.add(panel);
			
			btnGuardar.addActionListener(opGuardarObservacion);
			
			this.setSize(500,400);
			this.setLocationRelativeTo(null);
		}
		
		ActionListener opGuardarObservacion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!txaObservacion.getText().equals("")){
					
						if(new ActualizarSQL().Observacion_de_corte_guardado(Folio_corte, txaObservacion.getText().toUpperCase().trim())){
							
							while(tabla.getRowCount()>0)
								modelo.removeRow(0);
							
							Object[][] recargarTabla = new BuscarTablasModel().filtro_cortes_guardados();
							
							 String[] fila = new String[6];
		                     for(int i=0; i<recargarTabla.length; i++){
		                             fila[0] = recargarTabla[i][0]+"";
		                             fila[1] = recargarTabla[i][1]+"";
		                             fila[2] = recargarTabla[i][2]+"";
		                             fila[3] = recargarTabla[i][3]+"";
		                             fila[4] = recargarTabla[i][4]+"";
		                             fila[5] = recargarTabla[i][5]+"";
		                             modelo.addRow(fila);
		                     }
		                     
		                     dispose();
							
							JOptionPane.showMessageDialog(null, "Observacion guardada correctamente", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							dispose();
							JOptionPane.showMessageDialog(null, "No se pudo ingresar la observacion", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}
				}else{
					JOptionPane.showMessageDialog(null, "Ingresar Observacion", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					return;
				}
			}
		};
	}
	
	
	public static void main(String[] args) {
		new Cat_Agregar_Comentario_A_Corte_Guardado().setVisible(true);
	}

}
