package Cat_Lista_de_Raya;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import Obj_Lista_de_Raya.Obj_Autorizacion_Auditoria;
import Obj_Lista_de_Raya.Obj_Autorizacion_Finanzas;
import Obj_Lista_de_Raya.Obj_Persecciones_Extra;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Percepciones_Extras extends Cat_Root {

	Runtime R = Runtime.getRuntime();
	private JCheckBox chb_habilitar = new JCheckBox("Habilitar");
	private JCheckBox chb_todos = new JCheckBox("Seleccionar");
    
	private String lista[] = {"                   S/N","1","2","3","4","5","6","7"};
	private String lista1[] = {"","1","2","3","4","5","6","7"};
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public JComboBox cmb_layaout_dia = new JComboBox(lista);
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public JComboBox cmb_tabla_dia = new JComboBox(lista1);
    
	int fila = 0;
	int columna = 3;
	
	public DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Persecciones_Extra().get_tabla_model(),
            new String[]{"Folio", "Nombre Completo", "Establecimiento", "Bono", "Día Extra", "Cantidad Dias"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.Object.class,
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Object.class, 
	    	java.lang.Boolean.class, 
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
        	 	case 3 : 
        	 		if(chb_habilitar.isSelected()){
        	 			return true; 
        	 		}
        	 		return false;
        	 	case 4 : 
         	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,4).toString()) != true){
        	 			tabla_model.setValueAt(1, fila, 5);
        	 			return true;
        	 		}else {
        	 			tabla_model.setValueAt("", fila, 5);
        	 			return true;
        	 		}
        	 	case 5 : 
        	 		if(Boolean.parseBoolean(tabla_model.getValueAt(fila,4).toString()) == true){
        	 			return true;
        	 		}
        	 		return false;
        	 } 				
 			return false;
 		}
		
	};
	
	public JTable tabla = new JTable(tabla_model);
	public JScrollPane scroll_tabla = new JScrollPane(tabla);
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(tabla_model); 
	
	public TableColumn columna_dia = tabla.getColumnModel().getColumn(5);
    
    public Cat_Percepciones_Extras(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/hand_pro_icon&16.png"));
		this.setTitle("Percepciones Extras");
		
		this.panel.add(cmbEstablecimientos).setBounds(463,35,210,20);
		this.panel.add(chb_habilitar).setBounds(715,35,90,20);
		this.panel.add(chb_todos).setBounds(820,35,80,20);
		this.panel.add(cmb_layaout_dia).setBounds(905,35,140,20);
		
		this.columna_dia.setCellEditor(new javax.swing.DefaultCellEditor(cmb_tabla_dia));
		
		this.panel.add(scroll_tabla).setBounds(30,60,1035,615);
		
		this.cont.add(panel);
		
		this.llamar_render();
		this.init_tabla();
		this.agregar(tabla);
		
		this.btn_guardar.addActionListener(op_guardar);
		this.btn_refrescar.setVisible(false);
		
		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtNombre_Completo.addKeyListener(op_filtro_nombre);
		this.cmbEstablecimientos.addActionListener(op_filtro_establecimiento);
		this.cmb_layaout_dia.addActionListener(op_dia);
		this.chb_todos.addActionListener(op_todos);
		
		this.chb_habilitar.addActionListener(opChbBono);
		
		tabla.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {
				
						if(Validar(fila, columna)==false){
								JOptionPane.showMessageDialog(null, "Se introdujo un valor no valido","Aviso",JOptionPane.INFORMATION_MESSAGE);
								tabla.setValueAt("", fila, columna);
								tabla.editCellAt(fila, columna);
								Component aComp=tabla.getEditorComponent();
								aComp.requestFocus();
								return;
						}else{
								if(chb_habilitar.isSelected()){
								
								int cantidadDeFilas = tabla.getRowCount();
								fila+=1;
								
								if(fila == cantidadDeFilas){	fila=0;		}
								
										tabla.editCellAt(fila, columna);
										Component aComp=tabla.getEditorComponent();
										aComp.requestFocus();
								}
						}
			}
			public void keyPressed(KeyEvent arg0) {}
		});
		
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
		this.addWindowListener(op_cerrar);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
    
	@SuppressWarnings("unused")
	private boolean Validar(int fila, int columna) { 
		
			String valor=""; 
		
			if(tabla.getValueAt(fila,columna)==null) { 
				return false; 
			}else{ 
				double numero =0;
				
				try{
						numero = Double.valueOf(tabla.getValueAt(fila, columna).toString().trim());
						return true;
				}catch(NumberFormatException e){
					if(tabla.getValueAt(fila, columna).toString().trim().equals("")){
						return true;
					}else{
						return false;
					}
						
				}
			} 
	}
	
	ActionListener opChbBono = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			if(chb_habilitar.isSelected()){
//				tabla.setEnabled(true);
				fila=0;
				tabla.editCellAt(fila, columna);
				Component aComp=tabla.getEditorComponent();
				aComp.requestFocus();
			}
			
		}
	};
    
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	if(tbl.getSelectedColumn()!=3){
	        		chb_habilitar.setSelected(false);
	        		fila=0;
	        	}else{
	        		fila= tbl.getSelectedRow();
	        	}
	        	
	        }
        });
    }
	
    WindowListener op_cerrar = new WindowListener() {
		public void windowOpened(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			if(JOptionPane.showConfirmDialog(null, "¿Desea guardar antes de cerrar?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
				new Obj_Persecciones_Extra().guardar(tabla_guardar());
			}
		}
		public void windowClosed(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
	};
	
    ActionListener op_guardar = new ActionListener() {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			Obj_Autorizacion_Auditoria auditoria = new Obj_Autorizacion_Auditoria().buscar();
			Obj_Autorizacion_Finanzas finanzas = new Obj_Autorizacion_Finanzas().buscar();
			
			boolean auditoriaBoolean = auditoria.isAutorizar();
			boolean finanzasBoolean = finanzas.isAutorizar();
			
			if((auditoriaBoolean == true)  || (finanzasBoolean == true)){
				
				JOptionPane.showMessageDialog(null, "La Lista De Raya Fue Autorizada No Puede Ser Modificado Ninguna Percepcion Extra  .."
				       +" Hasta Que Se Genere Por D.H o Se Desautorize por Finanzas o Auditoria <<Al dar Click en Aceptar SCOI se Cerrará>>","Aviso",JOptionPane.WARNING_MESSAGE);
				
				try {	R.exec("taskkill /f /im javaw.exe"); } catch (IOException e1) {	e1.printStackTrace(); }		
				
			}else{
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
			
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
		
			if(valida_tabla() != ""){
				JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de percepciones extra?") == 0){
					Obj_Persecciones_Extra persecciones = new Obj_Persecciones_Extra();
					if(persecciones.guardar(tabla_guardar())){
						JOptionPane.showMessageDialog(null, "La tabla de percepciones se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
					}else{
						JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla Percepciones Extras op_guardar","Error Avisa al Administrador",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}else{
					return;
				}
			}
		}
	}	
	};
	
	private Object[][] tabla_guardar(){
		Object[][] matriz = new Object[tabla.getRowCount()][11];
		for(int i=0; i<tabla.getRowCount(); i++){
			for(int j=0; j<tabla.getColumnCount(); j++){
				switch(j){
					case 0: 
						matriz[i][j] = Integer.parseInt(tabla_model.getValueAt(i,j).toString().trim());
						break;
					case 1: 
						matriz[i][j] = tabla_model.getValueAt(i,j).toString().trim();
						break;
					case 2: 
						matriz[i][j] = tabla_model.getValueAt(i,j).toString().trim();
						break;
					case 3: 
						if(tabla_model.getValueAt(i,j).toString().trim().length() == 0){
							matriz[i][j] = Float.parseFloat("0"); 
						}else{
							matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
					case 4: 
						if(tabla_model.getValueAt(i,j).toString().equals("")){
							matriz[i][j] = Boolean.parseBoolean("false");
						}else{
							matriz[i][j] = Boolean.parseBoolean(tabla_model.getValueAt(i,j).toString());
						}
						break;
					case 5: 
						if(tabla_model.getValueAt(i,j).toString().trim().length() == 0){
							matriz[i][j] = Integer.parseInt("0"); 
						}else{
							matriz[i][j] = Integer.parseInt(tabla_model.getValueAt(i,j).toString().trim());
						}
						break;
											
				}
			}
		}
		return matriz;
	}
	
	private String valida_tabla(){
		String error = "";
		for(int i=0; i<tabla.getRowCount(); i++){
			for(int j=3; j<tabla.getColumnCount()-1; j++){
				try{
					if(!isNumeric(tabla_model.getValueAt(i,j).toString())){
						switch(j){
							case 3: 
								error += "   La celda de la columna Bono no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
							break;
							case 5:
								error += "   La celda de la columna Cantidad de días no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
							break;
						}
					}
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, "La tabla tiene una celda con texto en lugar de un valor numérico: \n"+e,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		return error;
	}
    
	public void llamar_render(){
		tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
		tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		tabla.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
		tabla.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer("CHB","izquierda","Arial","negrita",12));
		tabla.getColumnModel().getColumn(5).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
	}
	
	@SuppressWarnings("unchecked")
	public void init_tabla(){
		
		this.tabla.getTableHeader().setReorderingAllowed(false) ;
		
		this.tabla.getColumnModel().getColumn(0).setMaxWidth(72);
		this.tabla.getColumnModel().getColumn(0).setMinWidth(72);
		this.tabla.getColumnModel().getColumn(1).setMaxWidth(360);
		this.tabla.getColumnModel().getColumn(1).setMinWidth(360);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(210);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(210);
    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(150);
    	this.tabla.getColumnModel().getColumn(3).setMinWidth(150);
    	this.tabla.getColumnModel().getColumn(4).setMaxWidth(80);
    	this.tabla.getColumnModel().getColumn(4).setMinWidth(80);
    	this.tabla.getColumnModel().getColumn(5).setMaxWidth(135);
    	this.tabla.getColumnModel().getColumn(5).setMinWidth(135);
    	

		this.tabla.setRowSorter(trsfiltro);  
		
	}
	
	private static boolean isNumeric(String cadena){
		try {
			if(cadena.equals("")){
				return true;
			}else{
				Float.parseFloat(cadena);
				return true;
			}
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	KeyListener op_filtro_folio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
		}
		public void keyTyped(KeyEvent arg0) {
			char caracter = arg0.getKeyChar();
			if(((caracter < '0') ||
					(caracter > '9')) &&
					(caracter != KeyEvent.VK_BACK_SPACE)){
				arg0.consume(); 
			}	
		}
		public void keyPressed(KeyEvent arg0) {}
	};
		
	KeyListener op_filtro_nombre = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
		
	ActionListener op_filtro_establecimiento = new ActionListener(){
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0){
			if(cmbEstablecimientos.getSelectedIndex() != 0){
				trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			}
		}
	};
	
	ActionListener op_dia = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			for(int i=0; i<tabla.getRowCount(); i++) {
				if(Boolean.parseBoolean(tabla_model.getValueAt(i,4).toString()) != true){
					tabla_model.setValueAt("", i,5);
				}else{
					if(cmb_layaout_dia.getSelectedIndex() == 0){
						tabla_model.setValueAt("", i,5);
					}else{
						tabla_model.setValueAt(cmb_layaout_dia.getSelectedIndex(), i,5);
					}
					
				}

			}			
		}
	};
	
	ActionListener op_todos = new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(chb_todos.isSelected()){
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("true"), j,4);
				}
			}else{
				for(int j=0; j<tabla.getRowCount(); j++){
					tabla_model.setValueAt(Boolean.parseBoolean("false"), j,4);
					tabla_model.setValueAt("", j,5);
				}
			}
			
		}
	};
	
}
