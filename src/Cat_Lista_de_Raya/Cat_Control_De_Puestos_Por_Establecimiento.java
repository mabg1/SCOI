package Cat_Lista_de_Raya;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Cat_Reportes.Cat_Reporte_De_Plantilla_De_Puestos_Por_Establecimiento;
import Conexiones_SQL.BuscarTablasModel;
import Conexiones_SQL.GuardarTablasModel;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings({ "serial" })
public class Cat_Control_De_Puestos_Por_Establecimiento extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel  = new JLayeredPane();
	
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	JTextField txtFolioPuesto = new Componentes().text(new JTextField(), "Folio de puesto", 5, "Int");
	JTextField txtPuesto = new  JTextField();
	
	JButton btnCargarPuesto = new JButton("Buscar Puesto",new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnQuitarPuesto = new JButton("Quitar Puesto",new ImageIcon("imagen/eliminar-bala-icono-7773-32.png"));
	
	JButton btnCargarDepartamento = new JButton("Buscar Departamento",new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnQuitarDepartamento = new JButton("Quitar Departamento",new ImageIcon("imagen/eliminar-bala-icono-7773-32.png"));
	
	static Object[][] lista_de_establecimientos = new BuscarTablasModel().lista_de_establecimiento();
	DefaultTableModel modelo_establecimiento = new DefaultTableModel(lista_de_establecimientos,
            new String[]{ "Folio", "Establecimiento"}
			){
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
        	 	case 0  : return false; 
        	 	case 1  : return false;
        	 	} 				
 			return false;
 		}
	};
	
	DefaultTableModel modelo_departamento = new DefaultTableModel(null,new String[]{ "Folio", "Departamento"}){
	    
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
        	 	case 0  : return false; 
        	 	case 1  : return false;
        	 	} 				
 			return false;
 		}
	};
	
	DefaultTableModel modelo_puesto = new DefaultTableModel(null, new String[]{ "Folio", " Puesto",  "Cantidad"}){
	    
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
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
        	 	case 0  : return false; 
        	 	case 1  : return false;
        	 	case 2  : return true;
        	 	} 				
 			return false;
 		}
	};
	
	JTable tabla_establecimiento = new JTable(modelo_establecimiento);
	JScrollPane scroll_establecimiento = new JScrollPane(tabla_establecimiento,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JTable tabla_departamento = new JTable(modelo_departamento);
	JScrollPane scroll_departamento = new JScrollPane(tabla_departamento,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JTable tabla_puesto = new JTable(modelo_puesto);
	JScrollPane scroll_puesto = new JScrollPane(tabla_puesto,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
	JButton btnPlantilla = new JButton("Plantilla",new ImageIcon("imagen/Thiago-Silva-Palm-Contacts16.png"));
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Control_De_Puestos_Por_Establecimiento(){
		this.panel.setBorder(BorderFactory.createTitledBorder("Clasificador de puestos por establecimiento"));
		this.setTitle("Control de puestos por establecimientos");
		
		trsfiltro = new TableRowSorter(modelo_puesto); 
		tabla_puesto.setRowSorter(trsfiltro);  
		
		panel.add(btnCargarDepartamento).setBounds(330,20,160,20);
		panel.add(btnQuitarDepartamento).setBounds(500,20,170,20);
		
		panel.add(txtFolioPuesto).setBounds(20,258,66,20);
		panel.add(txtPuesto).setBounds(86,258,315,20);
		panel.add(btnCargarPuesto).setBounds(400,258,135,20);
		panel.add(btnQuitarPuesto).setBounds(535,258,135,20);
		
		panel.add(scroll_establecimiento).setBounds(20,40,280,200);
		panel.add(scroll_departamento).setBounds(330,40,340,200);
		panel.add(scroll_puesto).setBounds(20,280,650,250);
		panel.add(btnPlantilla).setBounds(20,535,100,20);
		panel.add(btnGuardar).setBounds(571,535,100,20);
		
		btnCargarPuesto.setEnabled(false);
		btnCargarDepartamento.setEnabled(false);
		
		llamar_render();
		
		llenarDepartamentos(tabla_establecimiento);
		llenarPuestos(tabla_departamento);
		selecccionarPuestos(tabla_puesto);
		
		txtFolioPuesto.addKeyListener(opFiltroFolio);
		txtPuesto.addKeyListener(opFiltroPuesto);
		
		btnCargarPuesto.addActionListener(opListaDePuestos);
		btnCargarDepartamento.addActionListener(opListaDedepartamentos);
		
		btnQuitarDepartamento.addActionListener(opQuitarDepto);
		btnQuitarPuesto.addActionListener(opQuitarPuesto);
		
		btnGuardar.addActionListener(opGuardarLista);
		btnPlantilla.addActionListener(opReporteDePlantilla);
		
		cont.add(panel);
		this.setSize(705,600);
		this.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unchecked")
	public void llamar_render(){
		
		this.tabla_establecimiento.getTableHeader().setReorderingAllowed(false) ;
		this.tabla_establecimiento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		this.tabla_departamento.getTableHeader().setReorderingAllowed(false) ;
		this.tabla_departamento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		this.tabla_puesto.getTableHeader().setReorderingAllowed(false) ;
		this.tabla_puesto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		int x = 65;
		
		this.tabla_establecimiento.getColumnModel().getColumn(0).setMaxWidth(x-1);
		this.tabla_establecimiento.getColumnModel().getColumn(0).setMinWidth(x-20);		
		this.tabla_establecimiento.getColumnModel().getColumn(1).setMaxWidth(200);
		this.tabla_establecimiento.getColumnModel().getColumn(1).setMinWidth(200);
		
		this.tabla_departamento.getColumnModel().getColumn(0).setMaxWidth(x-1);
		this.tabla_departamento.getColumnModel().getColumn(0).setMinWidth(x-20);		
		this.tabla_departamento.getColumnModel().getColumn(1).setMaxWidth(240);
		this.tabla_departamento.getColumnModel().getColumn(1).setMinWidth(240);
		
		this.tabla_puesto.getColumnModel().getColumn(0).setMaxWidth(x);
		this.tabla_puesto.getColumnModel().getColumn(0).setMinWidth(x);		
		this.tabla_puesto.getColumnModel().getColumn(1).setMaxWidth(520);
		this.tabla_puesto.getColumnModel().getColumn(1).setMinWidth(520);
		this.tabla_puesto.getColumnModel().getColumn(2).setMaxWidth(x);
		this.tabla_puesto.getColumnModel().getColumn(2).setMinWidth(x);

		this.tabla_puesto.setRowSorter(trsfiltro);  
		
		tabla_establecimiento.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
		tabla_establecimiento.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		
		tabla_departamento.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
		tabla_departamento.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		
		tabla_puesto.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
		tabla_puesto.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
		tabla_puesto.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
	}
	
	int folio_establecimiento = 0;
	private void llenarDepartamentos(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 1){
	    			int row = tbl.getSelectedRow();
	    			folio_establecimiento = Integer.valueOf(tabla_establecimiento.getValueAt(row, 0).toString().trim());
	    					   	 
	    			while(tabla_departamento.getRowCount()>0){
	    				modelo_departamento.removeRow(0);
	    			}
	    			
	    			while(tabla_puesto.getRowCount()>0){
	    				modelo_puesto.removeRow(0);
	    			}
                
                Object [][] lista_tabla = new BuscarTablasModel().tabla_departamentos_por_establecimiento(folio_establecimiento);
                String[] fila = new String[2];
                        for(int i=0; i<lista_tabla.length; i++){
                                fila[0] = lista_tabla[i][0]+"";
                                fila[1] = lista_tabla[i][1]+"";
                                modelo_departamento.addRow(fila);
                        }
                        
                        btnCargarDepartamento.setEnabled(true);
	        	}
	        }
        });
    }
	
	int folio_departamento = 0;
	private void llenarPuestos(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 1){
	    			int row = tbl.getSelectedRow();
	    			folio_departamento = Integer.valueOf(tabla_departamento.getValueAt(row, 0).toString().trim());
	    					   	 
	    			while(tabla_puesto.getRowCount()>0){
	    				modelo_puesto.removeRow(0);
                }
                
                Object [][] lista_tabla = new BuscarTablasModel().tabla_puestos_por_establecimiento(folio_establecimiento,folio_departamento);
                String[] fila = new String[3];
                        for(int i=0; i<lista_tabla.length; i++){
                                fila[0] = lista_tabla[i][0]+"";
                                fila[1] = lista_tabla[i][1]+"";
                                fila[2] = lista_tabla[i][2]+"";
                                modelo_puesto.addRow(fila);
                        }
                        
                        btnCargarPuesto.setEnabled(true);
	        	}
	        }
        });
    }
	
	int folio_puesto = 0;
	private void selecccionarPuestos(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount() == 1){
	    			int row = tbl.getSelectedRow();
	    			folio_puesto = Integer.valueOf(tabla_puesto.getValueAt(row, 0).toString().trim());
	        	}
	        }
        });
    }
	
	ActionListener opReporteDePlantilla = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Reporte_De_Plantilla_De_Puestos_Por_Establecimiento().setVisible(true);
		}
	};
	
	ActionListener opListaDedepartamentos = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			String listaEnUso = "";
			if(tabla_departamento.getRowCount()>0){
				for(int i = 0; i<tabla_departamento.getRowCount(); i++){
					listaEnUso += "'"+tabla_departamento.getValueAt(i, 0).toString().trim()+"',";
				}
				listaEnUso = listaEnUso.substring(0,listaEnUso.length()-1);
			}else{
				listaEnUso = "''";
			}
			
			new Cat_Puestos_Disponibles_Por_Establecimiento(listaEnUso,"Departamentos").setVisible(true);
		}
	};
	
	ActionListener opListaDePuestos = new ActionListener() {
		public void actionPerformed(ActionEvent   e) {
			
			String listaEnUso = "";
			if(tabla_puesto.getRowCount()>0){
				for(int i = 0; i<tabla_puesto.getRowCount(); i++){
					listaEnUso += "'"+tabla_puesto.getValueAt(i, 0).toString().trim()+"',";
				}
				listaEnUso = listaEnUso.substring(0,listaEnUso.length()-1);
			}else{
				listaEnUso = "''";
			}
			
			new Cat_Puestos_Disponibles_Por_Establecimiento(listaEnUso,"Puestos").setVisible(true);
		}
	};
	
	ActionListener opQuitarDepto = new ActionListener() {
		public void actionPerformed(ActionEvent   e) {
			
			int filaSeleccionada = tabla_departamento.getSelectedRow();
			
			if(filaSeleccionada >= 0){
				
					if(JOptionPane.showConfirmDialog(null, "El departamento "+tabla_departamento.getValueAt(filaSeleccionada, 1)+" será removido,\nlos puestos dependientes tambien se removeran,\n¿desea continuar?") == 0){
					
							if(new GuardarTablasModel().Borra_departamento_y_puestos_dependientes(folio_establecimiento,folio_departamento,0)){
								
								while(tabla_departamento.getRowCount()>0){modelo_departamento.removeRow(0);}
								while(tabla_puesto.getRowCount()>0){modelo_puesto.removeRow(0);}
								
								 Object [][] lista_tabla = new BuscarTablasModel().tabla_departamentos_por_establecimiento(folio_establecimiento);
					                String[] fila = new String[2];
					                        for(int i=0; i<lista_tabla.length; i++){
					                                fila[0] = lista_tabla[i][0]+"";
					                                fila[1] = lista_tabla[i][1]+"";
					                                modelo_departamento.addRow(fila);
					                        }
					                        
								
								JOptionPane.showMessageDialog(null, "El registro fue eliminado correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							
							}else{
								JOptionPane.showMessageDialog(null, "El registro no pudo ser eliminado","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						
					}
			
			}else{
				JOptionPane.showMessageDialog(null, "Debe seleccionar el departamente que desea eliminar,\nal eliminar un departamento se eliminaran todos\nsus puestos correspondientes al establecimiento\nseleccionado","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opQuitarPuesto = new ActionListener() {
		public void actionPerformed(ActionEvent   e) {
			
			int filaSeleccionada = tabla_puesto.getSelectedRow();
			
			if(filaSeleccionada >= 0){
				
					if(JOptionPane.showConfirmDialog(null, "El puesto "+tabla_puesto.getValueAt(filaSeleccionada, 1)+"\nserá removido ¿desea continuar?") == 0){
					
						
							if(new GuardarTablasModel().Borra_departamento_y_puestos_dependientes(folio_establecimiento,folio_departamento,folio_puesto)){
								
								if(tabla_puesto.getRowCount()==1){
									
										while(tabla_departamento.getRowCount()>0){modelo_departamento.removeRow(0);}
										while(tabla_puesto.getRowCount()>0){modelo_puesto.removeRow(0);}
										
										 Object [][] lista_tabla = new BuscarTablasModel().tabla_departamentos_por_establecimiento(folio_establecimiento);
							                String[] fila = new String[2];
							                        for(int i=0; i<lista_tabla.length; i++){
							                                fila[0] = lista_tabla[i][0]+"";
							                                fila[1] = lista_tabla[i][1]+"";
							                                modelo_departamento.addRow(fila);
							                        }
								}else{
									modelo_puesto.removeRow(filaSeleccionada);
								}
								
								
					                        
								
								JOptionPane.showMessageDialog(null, "El registro fue eliminado correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							
							}else{
								JOptionPane.showMessageDialog(null, "El registro no pudo ser eliminado","Aviso",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						
					}
			
			}else{
				JOptionPane.showMessageDialog(null, "Debe seleccionar el departamente que desea eliminar,\nal eliminar un departamento se eliminaran todos\nsus puestos correspondientes al establecimiento\nseleccionado","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
	};
	
	ActionListener opGuardarLista = new ActionListener() {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));

			if(tabla_puesto.isEditing()){
				tabla_puesto.getCellEditor().stopCellEditing();
			}
			
			    if(new GuardarTablasModel().Guarda_tabla_puestos_por_establecimiento(folio_establecimiento,folio_departamento,guardartabla())){
				    	JOptionPane.showMessageDialog(null, "El registro se guardo correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
						return;
			    }else{
				    	JOptionPane.showMessageDialog(null, "no guardo","error",JOptionPane.ERROR_MESSAGE);
						return;
			    }
		}
	};
	
	public String[][] guardartabla(){
		
		String[][] matriz = new String[tabla_puesto.getRowCount()][tabla_puesto.getColumnCount()];
		
		for(int i=0; i<tabla_puesto.getRowCount(); i++){
				for(int j=0; j<tabla_puesto.getColumnCount(); j++){
						matriz[i][j] = tabla_puesto.getValueAt(i, j).toString().trim();
				}
		}
		return matriz;
	}
	
	KeyListener opFiltroFolio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioPuesto.getText().toUpperCase(), 0));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	KeyListener opFiltroPuesto = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtPuesto.getText().toUpperCase(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	
	public class Cat_Puestos_Disponibles_Por_Establecimiento extends JDialog{
		
		Container cont_f = getContentPane();
		JLayeredPane panel_f  = new JLayeredPane();
		
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro_f;
		
		JTextField txtFolioPuesto_f = new Componentes().text(new JTextField(), "Folio", 5, "Int");
		JTextField txtPuesto_f = new  JTextField();
		
		JButton btnCargarPuesto_f = new JButton("Cargar");
		
		DefaultTableModel modelo_puesto_f = new DefaultTableModel(null,
	            new String[]{ "Folio", " Nombre",  "*"}
				){
		     @SuppressWarnings("rawtypes")
			Class[] types = new Class[]{
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
	        	 	case 0  : return false; 
	        	 	case 1  : return false;
	        	 	case 2  : return true;
	        	 	} 				
	 			return false;
	 		}
		};
		
		JTable tabla_puesto_f = new JTable(modelo_puesto_f);
		JScrollPane scroll_puesto_f= new JScrollPane(tabla_puesto_f,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		String VENTANA = "";
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Cat_Puestos_Disponibles_Por_Establecimiento(String listaEnUso, String ventana){
			this.setModal(true);
			this.setTitle(ventana+" Disponibles");
			
			trsfiltro_f = new TableRowSorter(modelo_puesto_f); 
			tabla_puesto_f.setRowSorter(trsfiltro_f);  
			
			VENTANA = ventana;
			
			panel_f.add(txtFolioPuesto_f).setBounds(20,20,66,20);
			panel_f.add(txtPuesto_f).setBounds(86,20,520,20);
			panel_f.add(btnCargarPuesto_f).setBounds(606,20,65,20);
			
			panel_f.add(scroll_puesto_f).setBounds(20,40,650,400);
			
			while(tabla_puesto_f.getRowCount()>0){
				modelo_puesto_f.removeRow(0);
        }
        
        Object [][] lista_tabla = new BuscarTablasModel().tabla_puestos_disponibles(listaEnUso,ventana);
        
        String[] fila = new String[3];
        
        	for(int i=0; i<lista_tabla.length; i++){
                fila[0] = lista_tabla[i][0]+"";
                fila[1] = lista_tabla[i][1]+"";
                fila[2] = lista_tabla[i][2]+"";
                modelo_puesto_f.addRow(fila);
        	}
			
			llamar_render_f();
			
			txtFolioPuesto_f.addKeyListener(opFiltroFolio_f);
			txtPuesto_f.addKeyListener(opFiltroPuesto_f);
			
			tabla_puesto_f.addKeyListener(opFiltroPuesto_f);
			
			btnCargarPuesto_f.addActionListener(opCargarPuestosSeleccionados);
			
			cont_f.add(panel_f);
			this.setSize(705,528);
			this.setLocationRelativeTo(null);
			setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/comprobar-la-lista-de-tareas-icono-7647-32.png"));
			
		}
		
		ActionListener opCargarPuestosSeleccionados = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String[] fila = new String[3];
				
				if(VENTANA.equals("Departamentos")){
					for(int i = 0; i < tabla_puesto_f.getRowCount(); i++){
						
						if(tabla_puesto_f.getValueAt(i, 2).toString().trim().equals("true")){
								fila[0]=tabla_puesto_f.getValueAt(i, 0).toString().trim();
								fila[1]=tabla_puesto_f.getValueAt(i, 1).toString().trim();
								fila[2]="0";
								modelo_departamento.addRow(fila);
						}
				}
				}else{
					for(int i = 0; i < tabla_puesto_f.getRowCount(); i++){
						
							if(tabla_puesto_f.getValueAt(i, 2).toString().trim().equals("true")){
									fila[0]=tabla_puesto_f.getValueAt(i, 0).toString().trim();
									fila[1]=tabla_puesto_f.getValueAt(i, 1).toString().trim();
									fila[2]="0";
									modelo_puesto.addRow(fila);
							}
					}
				}
					
				dispose();
			}
		};
		
		@SuppressWarnings("unchecked")
		public void llamar_render_f(){
			
			this.tabla_puesto_f.getTableHeader().setReorderingAllowed(false) ;
			this.tabla_puesto_f.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			int x = 65;
			
			this.tabla_puesto_f.getColumnModel().getColumn(0).setMaxWidth(x);
			this.tabla_puesto_f.getColumnModel().getColumn(0).setMinWidth(x);		
			this.tabla_puesto_f.getColumnModel().getColumn(1).setMaxWidth(520);
			this.tabla_puesto_f.getColumnModel().getColumn(1).setMinWidth(520);
			this.tabla_puesto_f.getColumnModel().getColumn(2).setMaxWidth(x-5);
			this.tabla_puesto_f.getColumnModel().getColumn(2).setMinWidth(x-5);

			this.tabla_puesto_f.setRowSorter(trsfiltro_f);  
			
			tabla_puesto_f.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
			tabla_puesto_f.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","negrita",12));
			tabla_puesto_f.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("CHB","centro","Arial","negrita",12));
		}
		
		KeyListener opFiltroFolio_f = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro_f.setRowFilter(RowFilter.regexFilter(txtFolioPuesto_f.getText().toUpperCase(), 0));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		KeyListener opFiltroPuesto_f = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro_f.setRowFilter(RowFilter.regexFilter(txtPuesto_f.getText().toUpperCase(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
	}
	
	public static void main(String [] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Control_De_Puestos_Por_Establecimiento().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
