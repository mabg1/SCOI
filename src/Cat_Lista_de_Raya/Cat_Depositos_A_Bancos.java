package Cat_Lista_de_Raya;

import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import Cat_IZAGAR.Cat_IZAGAR_Pasar_Netos_De_Nomina_A_Bancos;
import Cat_IZAGAR.Cat_IZAGAR_Selecionar_Nomina_Para_Netos;
import Cat_Reportes.Cat_Reporte_De_Empleados_Sin_Deposito_A_Bancos;
import Cat_Reportes.Cat_Reportes_De_Depositos_A_Bancos;
import Cat_Reportes.Cat_Reportes_De_Empleados_Con_Deposito_En_Bancos_Excedido;
import Conexiones_SQL.Connexion;
import IZAGAR_Obj.Obj_IZAGAR_Netos_Nominas;
import Obj_Lista_de_Raya.Obj_Autorizacion_Auditoria;
import Obj_Lista_de_Raya.Obj_Autorizacion_Finanzas;
import Obj_Lista_de_Raya.Obj_Depositos_A_Bancos;

@SuppressWarnings("serial")
public class Cat_Depositos_A_Bancos extends Cat_Root {
	Runtime R = Runtime.getRuntime();
	
	public static JCheckBox chbHabilitarBanamex = new JCheckBox("Habilitar");	
	public static JCheckBox chbHabilitarBanorte = new JCheckBox("Habilitar");
	public JCheckBox chbNegativos = new JCheckBox("Valores Negativos");
	
	public JTextField txtBanamex = new JTextField();
	public JTextField txtBanorte = new JTextField();
	public JTextField txtTotales = new JTextField();
	   
	float numero=0;
	
	public static DefaultTableModel tabla_model = new DefaultTableModel(new Obj_Depositos_A_Bancos().get_tabla_model(),
            new String[]{"Folio", "Nombre Completo", "Establecimientos", "Banamex", "Banorte", "Total a Pagar","Sugerido" }
			){
        
        public boolean isCellEditable(int fila, int columna){
        	switch(columna){
        		case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 :
    	 	if(chbHabilitarBanamex.isSelected()){
    	 				if(tabla_model.getValueAt(fila,4).toString().length() != 0){
    	 					return false;
    	 				}else{
        	 				return true;
    	 				}        	 				
    	 			 }else{
    	 				 return false;
    	 			}        	 			
        	 	case 4 : 
    	 			if(chbHabilitarBanorte.isSelected()){
    	 				if(tabla_model.getValueAt(fila,3).toString().length() != 0){
    	 					return false;
    	 				}else{
        	 				return true;
    	 				}
    	 			 }else{
    	 				 return false;
    	 			 }
        	 	case 5 : return false;
        	 	case 6 : return false;
        	 } 				
 			return false;
 		}
                
	};
	
	public static JTable tabla = new JTable(tabla_model);
	public JScrollPane scroll_tabla = new JScrollPane(tabla);
	
	JButton btn_lay_out = new JButton();
	JButton btn_cargar_nomina = new JButton();
	JButton btn_IDepositosBancLimpio  = new JButton();
	JButton btn_IDepositosBancP_Estab = new JButton();
	JButton btn_EmpleadosS_Dep = new JButton ();
	JButton btn_Empleados_Pagar_Negativo =new JButton ();
	
	JButton btn_aplicar_Sugerido =new JButton ("Aplicar Sugerido",new ImageIcon("imagen/Aplicar.png"));
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TableRowSorter trsfiltro = new TableRowSorter(tabla_model); 
		
    public Cat_Depositos_A_Bancos(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Iconos/money_icon&16.png"));
		this.setTitle("Bancos");
			
		this.panel.add(cmbEstablecimientos).setBounds(385,35,190,20);
		this.panel.add(chbHabilitarBanamex).setBounds(580,35,90,20);
		this.panel.add(chbHabilitarBanorte).setBounds(700,35,90,20);
		this.panel.add(chbNegativos).setBounds(800,35,130,20);
		this.panel.add(btn_aplicar_Sugerido).setBounds(930,35,140,20);
		
		panel.remove(txtNombre_Completo);
		this.panel.add(txtNombre_Completo).setBounds(101,35,280,20);
		
		this.panel.add(scroll_tabla).setBounds(30,60,1035,615);
		this.panel.add(btn_lay_out).setBounds(1085,160,40,40);
		this.panel.add(new JLabel("Generar Lay Out")).setBounds(1150,170,130,20);
		this.panel.add(btn_cargar_nomina).setBounds(1085,210,40,40);
		this.panel.add(new JLabel("Cargar Nomina")).setBounds(1150,220,130,20);
		this.panel.add(btn_IDepositosBancLimpio).setBounds(1085,260,40,40);
		this.panel.add(new JLabel("Imprimir Reporte Para Exportar a Excel")).setBounds(1150,270,250,20);
		this.panel.add(btn_IDepositosBancP_Estab).setBounds(1085,310,40,40);
		this.panel.add(new JLabel("Imprimir Reporte Por Establecimiento")).setBounds(1150,320,250,20);
		this.panel.add(btn_EmpleadosS_Dep).setBounds(1085,360,40,40);
		this.panel.add(new JLabel("Reportes de Empleados Sin Depositos A Bancos")).setBounds(1150,370,300,20);
		this.panel.add(btn_Empleados_Pagar_Negativo).setBounds(1085,410,40,40);
		this.panel.add(new JLabel("Reportes de Empleados Con Deposito Excedido")).setBounds(1150,420,300,20);
		
		this.panel.add(new JLabel("Total Banamex:")).setBounds(1080,70,100,20);
		this.panel.add(txtBanamex).setBounds(1160,70,120,20);
		this.txtBanamex.setEditable(false);
		this.txtBanamex.setFont(new Font("",0,14));
		
		this.panel.add(new JLabel("Total Banorte:")).setBounds(1080,95,250,20);
		this.panel.add(txtBanorte).setBounds(1160,95,120,20);
		this.txtBanorte.setEditable(false);
		this.txtBanorte.setFont(new Font("",0,14));
		
		this.panel.add(new JLabel("Totales:")).setBounds(1080,120,250,20);
		this.panel.add(txtTotales).setBounds(1160,120,120,20);
		
		ImageIcon imagLayOut = new ImageIcon(System.getProperty("user.dir")+"/Iconos/PAG5.png");
	    btn_lay_out.setIcon(new ImageIcon(imagLayOut.getImage().getScaledInstance(btn_lay_out.getWidth()-4,btn_lay_out.getHeight()-4, Image.SCALE_DEFAULT)));	
		
		ImageIcon imagnomina = new ImageIcon(System.getProperty("user.dir")+"/Iconos/TAR5.png");
		btn_cargar_nomina.setIcon(new ImageIcon(imagnomina.getImage().getScaledInstance(btn_cargar_nomina.getWidth()-4,btn_cargar_nomina.getHeight()-4, Image.SCALE_DEFAULT)));	
		
		ImageIcon imaglimpio = new ImageIcon(System.getProperty("user.dir")+"/Iconos/hoja-de-calculo-excel-icono-8804-48.png");
	    btn_IDepositosBancLimpio.setIcon(new ImageIcon(imaglimpio.getImage().getScaledInstance(btn_lay_out.getWidth()-4,btn_lay_out.getHeight()-4, Image.SCALE_DEFAULT)));	
		
	    ImageIcon imagCompleto = new ImageIcon(System.getProperty("user.dir")+"/Iconos/hoja-de-calculo-excel-invoice-icono-5449-48.png");
	    btn_IDepositosBancP_Estab.setIcon(new ImageIcon(imagCompleto.getImage().getScaledInstance(btn_lay_out.getWidth()-4,btn_lay_out.getHeight()-4, Image.SCALE_DEFAULT)));	
	    
	    ImageIcon imagempleadoSinBanco = new ImageIcon(System.getProperty("user.dir")+"/Imagen/usuario-de-alerta-icono-4069-64.png");
	    btn_EmpleadosS_Dep.setIcon(new ImageIcon(imagempleadoSinBanco.getImage().getScaledInstance(btn_lay_out.getWidth()-4,btn_lay_out.getHeight()-4, Image.SCALE_DEFAULT)));
	    
	    ImageIcon imagempleadoExcedido = new ImageIcon(System.getProperty("user.dir")+"/Imagen/rebicionTotales.png");
	    btn_Empleados_Pagar_Negativo.setIcon(new ImageIcon(imagempleadoExcedido.getImage().getScaledInstance(btn_lay_out.getWidth()-4,btn_lay_out.getHeight()-4, Image.SCALE_DEFAULT)));
	    
	    
		this.txtTotales.setEditable(false);
		this.txtTotales.setFont(new Font("",0,14));
		
		this.cont.add(panel);
		
		this.init_tabla();
		
		this.btn_guardar.addActionListener(op_guardar);
		this.btn_lay_out.addActionListener(op_lay_out);
		this.btn_cargar_nomina.addActionListener(op_123);
		this.btn_IDepositosBancLimpio.addActionListener(Reporte_Depositos_Bancos_limpio);
		this.btn_IDepositosBancP_Estab.addActionListener(Reporte_Depositos_Bancos_);
		this.btn_EmpleadosS_Dep.addActionListener(Reporte_Empleados_Sin_Depositos_A_Bancos_);
		this.btn_Empleados_Pagar_Negativo.addActionListener(Reporte_Empleados_Con_Depositos_A_Bancos_Excedido);
		this.btn_aplicar_Sugerido.addActionListener(aplicarSugerido);
		this.btn_refrescar.setVisible(false);
			
		btn_lay_out.setToolTipText("Generar Lay Out");
		btn_cargar_nomina.setToolTipText("Cargar Nomina");		
		
		this.txtFolio.addKeyListener(op_filtro_folio);
		this.txtNombre_Completo.addKeyListener(op_filtro_nombre);
		this.cmbEstablecimientos.addActionListener(op_filtro_establecimiento);
		this.chbNegativos.addActionListener(op_negativos);
		
		calcularSugerido();
		
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		this.setLocationRelativeTo(null);
		this.addWindowListener(op_cerrar);
	}
    
    public void calcularSugerido(){
 		for(int i=0; i<tabla.getRowCount(); i++){
 			if(tabla_model.getValueAt(i,3).toString().equals("")&&tabla_model.getValueAt(i,4).toString().equals("")){
 				tabla_model.setValueAt("", i, 6);
 			}else{
 				
	 		if(tabla_model.getValueAt(i,5).toString().equals("")){numero=0;}else{ numero=Float.valueOf(tabla_model.getValueAt(i,5).toString());}
	 			
	 			if(numero < 0){
        	 		if(tabla_model.getValueAt(i,3).toString().equals("")){ 	 		        	
	 		        	tabla_model.setValueAt(Float.valueOf(tabla_model.getValueAt(i,4).toString())+numero+"", i, 6) ;
	 		           }else{
	 		        	  tabla_model.setValueAt(Float.valueOf(tabla_model.getValueAt(i,3).toString())+numero+"", i, 6) ;
	 		           }
	        }else{ tabla_model.setValueAt("", i, 6);
	        }
	        }
 		}
    }
    
    ActionListener aplicarSugerido = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
 		for(int i=0; i<tabla.getRowCount(); i++){
 			if(!tabla_model.getValueAt(i,6).toString().equals("")){		
	 			if(tabla_model.getValueAt(i,3).toString().equals("")){tabla_model.setValueAt(Float.valueOf(tabla_model.getValueAt(i,6).toString()), i, 4); }
															 	 else{tabla_model.setValueAt(Float.valueOf(tabla_model.getValueAt(i,6).toString()), i, 3); }
	 			}
 		}
     }
    };
    
    WindowListener op_cerrar = new WindowListener() {
		public void windowOpened(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			if(JOptionPane.showConfirmDialog(null, "¿Desea guardar antes de cerrar?", "Aviso!", JOptionPane.YES_NO_OPTION) == 0){
				new Obj_Depositos_A_Bancos().guardar(tabla_guardar());
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
				JOptionPane.showMessageDialog(null, "La Lista De Raya Fue Autorizada No Puede Ser Modificado Ningun Deposito a Bancos......"
				       +" Hasta Que Se Genere Por D.H o Se Desautorize por Finanzas o Auditoria ","Aviso",JOptionPane.WARNING_MESSAGE);
				
			}else{
				
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 3));
			
			txtFolio.setText("");
			txtNombre_Completo.setText("");
			cmbEstablecimientos.setSelectedIndex(0);
			chbNegativos.setSelected(false);
			
			if(tabla.isEditing()){
				tabla.getCellEditor().stopCellEditing();
			}
		
			if(valida_tabla() != ""){
				JOptionPane.showMessageDialog(null, "Las siguientes celdas están mal en su formato:\n"+valida_tabla(),"Error",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				if(JOptionPane.showConfirmDialog(null, "¿Desea guardar la lista de bancos?") == 0){
					
					Obj_Depositos_A_Bancos banco = new Obj_Depositos_A_Bancos();
					
					if(banco.guardar(tabla_guardar())){
						
						while(tabla.getRowCount()>0){
							tabla_model.removeRow(0);
						}
						
						Object [][] lista = new Obj_Depositos_A_Bancos().get_tabla_model();
						
						for(Object[] ps : lista){
							tabla_model.addRow(ps);
						}
						
							txtBanamex.setText("$ "+returnBanamex());
							txtBanorte.setText("$ "+returnBanorte());
							txtTotales.setText("$ "+returnTotales());
							
							calcularSugerido();
							
							JOptionPane.showMessageDialog(null, "La tabla bancos se guardó exitosamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
							
					}else{
						JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar guardar la tabla","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}else{
					return;
				}
			}
		 }
		}
	};
	
    ActionListener op_lay_out = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			new Cat_Lay_Out().setVisible(true);
		}
	};
	
	   ActionListener op_123 = new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				txtFolio.setText("");
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolio.getText(), 0));
				txtNombre_Completo.setText("");
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
				new filtroSeleccion().setVisible(true);
			}
		};
	
		public Object[][] tabla_guardar(){
			
			Object[][] matriz = new Object[tabla.getRowCount()][6];
			
			for(int i=0; i<tabla.getRowCount(); i++){
				
				for(int j=0; j<tabla.getColumnCount()-1; j++){
					
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
							if(tabla_model.getValueAt(i,j).toString().equals("")){
								matriz[i][j] = Float.parseFloat("0.0");
							}else{
								matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString());
							}
							break;
						case 4: 
							if(tabla_model.getValueAt(i,j).toString().equals("")){
								matriz[i][j] = Float.parseFloat("0.0");
							}else{
								matriz[i][j] = Float.parseFloat(tabla_model.getValueAt(i,j).toString());
							}
							break;
					}
				}
			}
			return matriz;
		}
	
		public String valida_tabla(){
		
			String error = "";
			
			for(int i=0; i<tabla.getRowCount(); i++){
			
				for(int j=3; j<tabla.getColumnCount()-1; j++){
					
					try{
						if(!isNumeric(tabla_model.getValueAt(i,j).toString())){
							switch(j){
								case 3: 
									error += "   La celda de la columna Banamex no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
									break;
								case 4:
									error += "   La celda de la columna Banorte no es un número en el [Folio: "+tabla_model.getValueAt(i,0)+"]\t\n";
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
		
    @SuppressWarnings({ "unchecked", "static-access" })
	public void init_tabla(){
    	
    	this.tabla.getTableHeader().setReorderingAllowed(false) ;
    	
    	this.tabla.getColumnModel().getColumn(0).setMaxWidth(72);
    	this.tabla.getColumnModel().getColumn(0).setMinWidth(72);		
    	this.tabla.getColumnModel().getColumn(1).setMaxWidth(280);
    	this.tabla.getColumnModel().getColumn(1).setMinWidth(280);
    	this.tabla.getColumnModel().getColumn(2).setMaxWidth(180);
    	this.tabla.getColumnModel().getColumn(2).setMinWidth(180);
    	this.tabla.getColumnModel().getColumn(3).setMaxWidth(120);
    	this.tabla.getColumnModel().getColumn(3).setMinWidth(120);
    	this.tabla.getColumnModel().getColumn(4).setMaxWidth(120);
    	this.tabla.getColumnModel().getColumn(4).setMinWidth(120);		
    	this.tabla.getColumnModel().getColumn(5).setMaxWidth(130);
    	this.tabla.getColumnModel().getColumn(5).setMinWidth(130);
    	this.tabla.getColumnModel().getColumn(6).setMaxWidth(110);
    	this.tabla.getColumnModel().getColumn(6).setMinWidth(110);
    	
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
								case 0 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
								case 1 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
								case 2 : lbl.setHorizontalAlignment(SwingConstants.LEFT); break;
								case 3 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
								case 4 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
								case 5 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
								case 6 : lbl.setHorizontalAlignment(SwingConstants.RIGHT); break;
							}
							return lbl; 
					} 
			}; 

		for(int x = 0; x<tabla.getColumnCount(); x++){
			this.tabla.getColumnModel().getColumn(x).setCellRenderer(render); 
		}
		
		this.tabla.setRowSorter(trsfiltro);  
		
		this.txtBanamex.setText("$ "+returnBanamex());
		this.txtBanorte.setText("$ "+returnBanorte());
		this.txtTotales.setText("$ "+returnTotales());
    }
    
    public static boolean isNumeric(String cadena){
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
	
	ActionListener op_negativos = new ActionListener(){
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(chbNegativos.isSelected()){
				trsfiltro.setRowFilter(RowFilter.regexFilter("-", 5));
			}else{
				trsfiltro.setRowFilter(RowFilter.regexFilter("", 5));
			}
		}
		
	};
    
	DecimalFormat formato = new DecimalFormat("#0.00");
	public float returnBanamex(){
		float valor = 0;
		
		for(int i=0; i<tabla.getRowCount(); i++){
			if(tabla_model.getValueAt(i, 3).toString().length() != 0){
				valor = valor + Float.parseFloat(formato.format(tabla_model.getValueAt(i,3))+"");
			}				
		}
		return valor;
	}
	
	public float returnBanorte(){
		float valor = 0;
		for(int i=0; i<tabla.getRowCount(); i++){
			if(tabla_model.getValueAt(i,4).toString().length() != 0){
				valor = valor + Float.parseFloat(formato.format(tabla_model.getValueAt(i,4))+"");
			}				
		}
		return valor;
	}
	
	public float returnTotales(){
		
		float valor = 0;
		for(int i=0; i<tabla.getRowCount(); i++){
			if(tabla_model.getValueAt(i,3).toString().length() != 0){
				valor = valor + Float.parseFloat(tabla_model.getValueAt(i,3)+"");
			}	
			if(tabla_model.getValueAt(i,4).toString().length() != 0){
				valor = valor + Float.parseFloat(formato.format(tabla_model.getValueAt(i,4))+"");
			}		
		}
		return valor;
	}
	
	public static void main (String [] arg) {
		new Cat_Depositos_A_Bancos().setVisible(true);
	}
	
	public class filtroSeleccion extends Cat_IZAGAR_Selecionar_Nomina_Para_Netos{
		
		public filtroSeleccion(){
			this.txtFolionomina.addActionListener(optBuscar);
			this.btnconsultanomina.addActionListener(optBuscar);
		}
		
		ActionListener optBuscar = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(txtFolionomina.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}else{
					try{
						new asignarBancos(txtFolionomina.getText()).setVisible(true);
						dispose();
					}catch (NumberFormatException e1){
						e1.getStackTrace();
					}
				}
			}
		};
	}
	
	ActionListener Reporte_Depositos_Bancos_ = new ActionListener(){
		public void actionPerformed(ActionEvent e){
				new Cat_Reportes_De_Depositos_A_Bancos().setVisible(true);
		}
	};
	
	ActionListener Reporte_Empleados_Sin_Depositos_A_Bancos_ = new ActionListener(){
		public void actionPerformed(ActionEvent e){
				new Cat_Reporte_De_Empleados_Sin_Deposito_A_Bancos().setVisible(true);
		}
	};
	
	ActionListener Reporte_Empleados_Con_Depositos_A_Bancos_Excedido = new ActionListener(){
		public void actionPerformed(ActionEvent e){
				new Cat_Reportes_De_Empleados_Con_Deposito_En_Bancos_Excedido().setVisible(true);
		}
	};
	
	ActionListener Reporte_Depositos_Bancos_limpio = new ActionListener(){
		@SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e){
				try {
					JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_Depositos_A_Bancos_Para_Exportar.jrxml");
					@SuppressWarnings("unchecked")
					JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), new Connexion().conexion());
					JasperViewer.viewReport(print, false);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Error en Cat_Depositos_A_Bancos  en la funcion [ ActionListener Reporte_Depositos_Bancos_limpio ]   SQLException:  "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				}
		}
	};
	
	public class asignarBancos extends Cat_IZAGAR_Pasar_Netos_De_Nomina_A_Bancos{

		public asignarBancos(String folio_nomina) {
			super(folio_nomina);
			System.out.println("actionlsitener aplicar");
			btnAplicar.addActionListener(optAplicar);
			
		}
		
			ActionListener optAplicar = new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					
				
					Obj_Autorizacion_Auditoria auditoria = new Obj_Autorizacion_Auditoria().buscar();
					Obj_Autorizacion_Finanzas finanzas = new Obj_Autorizacion_Finanzas().buscar();
					
					boolean auditoriaBoolean = auditoria.isAutorizar();
					boolean finanzasBoolean = finanzas.isAutorizar();
					
					if((auditoriaBoolean == true)  || (finanzasBoolean == true)){
						JOptionPane.showMessageDialog(null, "La Lista De Raya Fue Autorizada No Puede Ser Modificado Ningun Deposito a Bancos......"
						       +" Hasta Que Se Genere Por D.H o Se Desautorize por Finanzas o Auditoria ","Aviso",JOptionPane.WARNING_MESSAGE);
						
					}else{
					
							if(new Obj_IZAGAR_Netos_Nominas().guardar_totales_deposito_nomina_bancos()){
								dispose();
//	borrar la tabla							
//	busca los valores y carga la tabla de nuevo		
//	carga la tabla nuevamente	
								while(tabla.getRowCount()>0){tabla_model.removeRow(0);}
								Object [][] lista = new Obj_Depositos_A_Bancos().get_tabla_model();
									for(Object[] ps : lista){tabla_model.addRow(ps);}
//  ace un guardado de la tabla automatico
//	borra la tabla nuevamente
//	busca la tabla nuevamente por los posibles cambios 
//	carga la tabla nuevamente				
								new Obj_Depositos_A_Bancos().guardar(tabla_guardar());
								while(tabla.getRowCount()>0){tabla_model.removeRow(0);}
								Object [][] lista2 = new Obj_Depositos_A_Bancos().get_tabla_model();
									for(Object[] ps : lista2){tabla_model.addRow(ps);}
// se calculan los totales nuevamente
										txtBanamex.setText("$ "+returnBanamex());
										txtBanorte.setText("$ "+returnBanorte());
										txtTotales.setText("$ "+returnTotales());
										calcularSugerido();
											
								JOptionPane.showMessageDialog(null,"El Transpaso Se a Realizado Exitosamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
								return;
								
							}else{
								JOptionPane.showMessageDialog(null,"No Se A Realizado El Transpaso", "Error", JOptionPane.WARNING_MESSAGE);
								return;
							}
				 	}
			  }	
		};
	}
}