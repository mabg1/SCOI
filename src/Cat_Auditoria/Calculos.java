package Cat_Auditoria;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Conexiones_SQL.ActualizarSQL;
import Conexiones_SQL.BuscarTablasModel;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Calculos extends JFrame{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JDateChooser calendario = new JDateChooser();
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento_desc();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	static DecimalFormat df = new DecimalFormat("#0.00");
	
	public static Object[][] llenarTabla(double porcentaje,String fecha, String establecimiento){
		
		 Object[][] datosTablaPrincipal = new BuscarTablasModel().traer_tabla_base_calculos(porcentaje, fecha,  establecimiento);
		return datosTablaPrincipal;
	}
	 

		//static Object[][] datosTablaPrincipal = new BuscarTablasModel().denominaciones_apartados();
		public static DefaultTableModel tabla_model_base = new DefaultTableModel( 
				null, 
				new String[]{"Asignacion", "Corte", "Costo Venta", "IVA", "Tasa 0", "Tasa Ex.", "Tasa IEPS", "Tasa IVA", "Total", "Apartados", "Dev y Canc", "Vouchers", "Retiros Cte", "TA-LUZ", "Fecha Asig.", "Fecha liq."} )
			{
		             
				@SuppressWarnings({ "rawtypes" })
				Class[] types = new Class[]{
		                java.lang.Object.class,
		                java.lang.Object.class, 
		                java.lang.Object.class,
		                java.lang.Object.class,
		                java.lang.Object.class, 
		                java.lang.Object.class,
		                java.lang.Object.class,
		                java.lang.Object.class, 
		                java.lang.Object.class,
		                java.lang.Object.class,
		                java.lang.Object.class, 
		                java.lang.Object.class,
		                java.lang.Object.class,
		                java.lang.Object.class, 
		                java.lang.Object.class,
		                java.lang.Object.class
		                 
		 };
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
	     }
		
		 public boolean isCellEditable(int fila, int columna){
		             switch(columna){
		                     case 0   : return false; 
		                     case 1   : return false; 
		                     case 2   : return false; 
		                     case 3   : return false; 
		                     case 4   : return false; 
		                     case 5   : return false; 
		                     case 6   : return false; 
		                     case 7   : return false; 
		                     case 8   : return false; 
		                     case 9   : return false; 
		                     case 10  : return false; 
		                     case 11  : return false; 
		                     case 12  : return false; 
		                     case 13  : return false; 
		                     case 14  : return false; 
		                     case 15  : return false; 
		             }
		              return false;
		      }
	  };

	JTable tabla_base = new JTable(tabla_model_base);
	JScrollPane panelScroll_base = new JScrollPane(tabla_base);

	public static DefaultTableModel tabla_model_calculada = new DefaultTableModel( 
			null, 
			new String[]{"Asignacion", "Corte", "Costo Venta", "IVA", "Tasa 0", "Tasa Ex.", "Tasa IEPS", "Tasa IVA", "Total"} )
		{
	             
			@SuppressWarnings({ "rawtypes" })
			Class[] types = new Class[]{
	                java.lang.Object.class,
	                java.lang.Object.class, 
	                java.lang.Object.class,
	                java.lang.Object.class,
	                java.lang.Object.class, 
	                java.lang.Object.class,
	                java.lang.Object.class,
	                java.lang.Object.class, 
	                java.lang.Object.class
	                 
	 };
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
	             return types[columnIndex];
		     }
			
			 public boolean isCellEditable(int fila, int columna){
			             switch(columna){
			                     case 0   : return false; 
			                     case 1   : return false; 
			                     case 2   : return false; 
			                     case 3   : return false; 
			                     case 4   : return false; 
			                     case 5   : return false; 
			                     case 6   : return false; 
			                     case 7   : return false; 
			                     case 8   : return false; 
			             }
			              return false;
			      }
		};
		
		JTable tabla_calculada = new JTable(tabla_model_calculada);
		JScrollPane panelScroll_calculado = new JScrollPane(tabla_calculada);
	
		JLabel lblTotales = new JLabel("");
			JLabel lblAsignacion = new JLabel("0.0");
//			JLabel lblCorte = new JLabel("0.0");
			JLabel lblCosto = new JLabel("0.0");
			JLabel lblIva = new JLabel("0.0");
			JLabel lblTasa0 = new JLabel("0.0");
			JLabel lblTasaExent = new JLabel("0.0");
			JLabel lblIeps = new JLabel("0.0");
			JLabel lblImporte = new JLabel("0.0");
			JLabel lblTotal = new JLabel("0.0");
			
			int asignaciones = 0;
//			int cortes  = 0;
			double costo = 0;
			double iva = 0;
			double tasa0 = 0;
			double tasaE = 0;
			double ieps = 0;
			double  importe = 0;
			double total = 0;
			
		    JLabel lblDiferiencia = new JLabel("");
			JLabel lblCostoDif = new JLabel("0.0");
			JLabel lblIvaDif = new JLabel("0.0");
			JLabel lblTasa0Dif = new JLabel("0.0");
			JLabel lblTasaExentDif = new JLabel("0.0");
			JLabel lblIepsDif = new JLabel("0.0");
			JLabel lblImporteDif = new JLabel("0.0");
			JLabel lblTotalDif = new JLabel("0.0");
			
			JLabel lblTotalfinal = new JLabel("0.0");
			
			double costo_dif = 0;
			double iva_dif = 0;
			double tasa0_dif = 0;
			double tasaE_dif = 0;
			double ieps_dif = 0;
			double  importe_dif = 0;
			double total_dif = 0;
			
			double total_final=0;
			
	JTextField txtPorcentaje = new Componentes().text(new JTextField(), "Aplicar Porcentaje", 3, "Int");
	JButton btnPorcentaje = new JButton("Aplicar",new ImageIcon("imagen/Aplicar.png"));
	JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
	JButton btnDeshacer = new JButton("Deshacer",new ImageIcon("imagen/deshacer16.png"));
	
	public Calculos(){
		setTitle("Calculo");
		setSize(1010,660);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Border blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/encontrar-busqueda-lupa-de-la-ventana-de-zoom-icono-4008-32.png"));
		
		
		this.lblTotales.setBorder(BorderFactory.createTitledBorder(blackline,"Totales"));
		this.lblDiferiencia.setBorder(BorderFactory.createTitledBorder(blackline,"Diferencias"));
		
		lblAsignacion.setFont(new Font("arial", Font.BOLD, 12));
//		lblCorte.setFont(new Font("arial", Font.BOLD, 10));
		lblCosto.setFont(new Font("arial", Font.BOLD, 12));
		lblIva.setFont(new Font("arial", Font.BOLD, 12));
		lblTasa0.setFont(new Font("arial", Font.BOLD, 12));
		lblTasaExent.setFont(new Font("arial", Font.BOLD, 12));
		lblIeps.setFont(new Font("arial", Font.BOLD, 12));
		lblImporte.setFont(new Font("arial", Font.BOLD, 12));
		lblTotal.setFont(new Font("arial", Font.BOLD, 12));
		
		lblDiferiencia.setFont(new Font("arial", Font.BOLD, 12));
//		lblCorte.setFont(new Font("arial", Font.BOLD, 10));
		lblCostoDif.setFont(new Font("arial", Font.BOLD, 12));
		lblIvaDif.setFont(new Font("arial", Font.BOLD, 12));
		lblTasa0Dif.setFont(new Font("arial", Font.BOLD, 12));
		lblTasaExentDif.setFont(new Font("arial", Font.BOLD, 12));
		lblIepsDif.setFont(new Font("arial", Font.BOLD, 12));
		lblImporteDif.setFont(new Font("arial", Font.BOLD, 12));
		lblTotalDif.setFont(new Font("arial", Font.BOLD, 12));
		
		lblTotalfinal.setFont(new Font("arial", Font.BOLD, 16));
		
		int x = 115;
		int ancho = 66;
		int y = 20;
		
		panel.add(new JLabel("Fecha: ")).setBounds(20,10,80,20);
		panel.add(calendario).setBounds(100,10,170,20);
		
		panel.add(new JLabel("Establecimiento: ")).setBounds(20,y+=20,100,20);
		panel.add(cmbEstablecimiento).setBounds(100,y,170,20);
		
		panel.add(getPanelTablaBase()).setBounds(20,y+=25,965,200);
		
		panel.add(lblTotales).setBounds(15,y+=210,698,50);
		
		panel.add(lblAsignacion).setBounds(20,y+=5,ancho+20,50);
//		panel.add(lblCorte).setBounds(x,y,ancho+10,50);
		panel.add(lblCosto).setBounds(x+=ancho+25,y,ancho-5,50);
		panel.add(lblIva).setBounds(x+=ancho+5,y,ancho-5,50);
		panel.add(lblTasa0).setBounds(x+=ancho+5,y,ancho-5,50);
		panel.add(lblTasaExent).setBounds(x+=ancho+5,y,ancho-5,50);
		panel.add(lblIeps).setBounds(x+=ancho+5,y,ancho-5,50);
		panel.add(lblImporte).setBounds(x+=ancho,y,ancho-5,50);
		panel.add(lblTotal).setBounds(x+=ancho+5,y,ancho-5,50);
		
		panel.add(txtPorcentaje).setBounds(20,y+=50,50,20);
		panel.add(btnPorcentaje).setBounds(75,y,100,20);
		panel.add(btnDeshacer).setBounds(185,y,100,20);
		panel.add(btnGuardar).setBounds(295,y,100,20);
		
		
		panel.add(new JLabel("Diferencia Final: ")).setBounds(830,y-60,150,20);
		panel.add(lblTotalfinal).setBounds(800,y-40,150,20);
		
		panel.add(getPanelTablaCalculada()).setBounds(20,y+=30,688,200);
		
		x = ancho+50;
		
		panel.add(lblDiferiencia).setBounds(15,y+=210,695,50);
		panel.add(lblCostoDif).setBounds(x+=ancho+25,y,ancho-5,50);
		panel.add(lblIvaDif).setBounds(x+=ancho+5,y,ancho-5,50);
		panel.add(lblTasa0Dif).setBounds(x+=ancho+5,y,ancho-5,50);
		panel.add(lblTasaExentDif).setBounds(x+=ancho+5,y,ancho-5,50);
		panel.add(lblIepsDif).setBounds(x+=ancho+5,y,ancho-5,50);
		panel.add(lblImporteDif).setBounds(x+=ancho,y,ancho-5,50);
		panel.add(lblTotalDif).setBounds(x+=ancho+5,y,ancho-5,50);
		
		
		lblAsignacion.setHorizontalAlignment(0);
//		lblCorte.setHorizontalAlignment(0);
		lblCosto.setHorizontalAlignment(0);
		lblIva.setHorizontalAlignment(0);
		lblTasa0.setHorizontalAlignment(0);
		
		lblTasaExent.setHorizontalAlignment(0);
		lblIeps.setHorizontalAlignment(0);
		lblImporte.setHorizontalAlignment(0);
		lblTotal.setHorizontalAlignment(0);
		
		lblCostoDif.setHorizontalAlignment(0);
		lblIvaDif.setHorizontalAlignment(0);
		lblTasa0Dif.setHorizontalAlignment(0);
		
		lblTasaExentDif.setHorizontalAlignment(0);
		lblIepsDif.setHorizontalAlignment(0);
		lblImporteDif.setHorizontalAlignment(0);
		lblTotalDif.setHorizontalAlignment(0);
		
		lblTotalfinal.setHorizontalAlignment(0);
		
		txtPorcentaje.setHorizontalAlignment(0);
		
		cont.add(panel);
		
		calcular_totales();
		
		cmbEstablecimiento.addActionListener(opConsultar);
		btnPorcentaje.addActionListener(opAplicar);
		btnDeshacer.addActionListener(deshacer);
		btnGuardar.addActionListener(guardar);
		txtPorcentaje.addActionListener(opAplicar);
	}
	
	
	public JScrollPane getPanelTablaBase(){
    	
		int texto=90;
		int money=80;
		
    	this.tabla_base.getTableHeader().setReorderingAllowed(false) ;
    	this.tabla_base.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	for(int i = 0; i<tabla_base.getColumnCount(); i++){
    		
    		if(i==0 || i==1 || i==14 || i==15){
    			this.tabla_base.getColumnModel().getColumn(i).setMaxWidth(texto);
            	this.tabla_base.getColumnModel().getColumn(i).setMinWidth(texto);
            	
    			tabla_base.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
    		}else{
    			this.tabla_base.getColumnModel().getColumn(i).setMaxWidth(money);
            	this.tabla_base.getColumnModel().getColumn(i).setMinWidth(money);
            	
    			tabla_base.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
    		}
    	}
    	 JScrollPane scrol = new JScrollPane(tabla_base);
		    return scrol; 
    }
	
	public JScrollPane getPanelTablaCalculada(){
    	
		int texto=90;
		int money=70;
		
    	this.tabla_calculada.getTableHeader().setReorderingAllowed(false) ;
    	this.tabla_calculada.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	
    	for(int i = 0; i<tabla_calculada.getColumnCount(); i++){
    		
    		if(i==0 || i==1 || i==14 || i==15){
    			this.tabla_calculada.getColumnModel().getColumn(i).setMaxWidth(texto);
            	this.tabla_calculada.getColumnModel().getColumn(i).setMinWidth(texto);
            	
            	tabla_calculada.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","centro","Arial","negrita",12));
    		}else{
    			this.tabla_calculada.getColumnModel().getColumn(i).setMaxWidth(money);
            	this.tabla_calculada.getColumnModel().getColumn(i).setMinWidth(money);
            	
            	tabla_calculada.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","derecha","Arial","negrita",12));
    		}
    	}
    	 JScrollPane scrol = new JScrollPane(tabla_calculada);
		    return scrol; 
    }
	
	ActionListener opConsultar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
					while(tabla_base.getRowCount()>0)
		    			tabla_model_base.removeRow(0);

					String fecha = new SimpleDateFormat("dd/MM/yyyy").format(calendario.getDate());
					Object [][] lista_tabla = llenarTabla(1,fecha,cmbEstablecimiento.getSelectedItem()+"");
	
		    		String[] fila = new String[16];
		                    for(int i=0; i<lista_tabla.length; i++){
		                            fila[0] = lista_tabla[i][0]+"";
		                            fila[1] = lista_tabla[i][1]+"";
		                            fila[2] = lista_tabla[i][2]+"";
		                            fila[3] = lista_tabla[i][3]+"";
		                            fila[4] = lista_tabla[i][4]+"";
		                            fila[5] = lista_tabla[i][5]+"";
		                            fila[6] = lista_tabla[i][6]+"";
		                            fila[7] = lista_tabla[i][7]+"";
		                            fila[8] = lista_tabla[i][8]+"";
		                            fila[9] = lista_tabla[i][9]+"";
		                            fila[10] = lista_tabla[i][10]+"";
		                            fila[11] = lista_tabla[i][11]+"";
		                            fila[12] = lista_tabla[i][12]+"";
		                            fila[13] = lista_tabla[i][13]+"";
		                            fila[14] = lista_tabla[i][14]+"";
		                            fila[15] = lista_tabla[i][15]+"";
		                            tabla_model_base.addRow(fila);
		                    }
		                    calcular_totales();
		            		calendario.setEnabled(false);
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
            
			lblAsignacion.setText("");
			lblCosto.setText("");
			lblIva.setText("");
			lblTasa0.setText("");
			lblTasaExent.setText("");
			lblIeps.setText("");
			lblImporte.setText("");
			lblTotal.setText("");
			lblDiferiencia.setText("");
			lblCostoDif.setText("");
			lblIvaDif.setText("");
			lblTasa0Dif.setText("");
			lblTasaExentDif.setText("");
			lblIepsDif.setText("");
			lblImporteDif.setText("");
			lblTotalDif.setText("");
			lblTotalfinal.setText("");
			
			calendario.setDate(null);
			calendario.setEnabled(true);
			cmbEstablecimiento.removeActionListener(opConsultar);
			cmbEstablecimiento.setSelectedIndex(0);
			
			while(tabla_base.getRowCount()>0){
				tabla_model_base.removeRow(0);  }
			
			while(tabla_calculada.getRowCount()>0){
				tabla_model_calculada.removeRow(0);  }
			
			cmbEstablecimiento.addActionListener(opConsultar);
		}
	};
	
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			Object [][] matriz = new Object[tabla_base.getRowCount()][2];
			
			if(tabla_base.getRowCount()<=0){
					JOptionPane.showMessageDialog(null, "No se an cargado datos","Aviso",JOptionPane.INFORMATION_MESSAGE);
					return;
			}else{
					if(txtPorcentaje.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Favor de especificar un porcentaje","Aviso",JOptionPane.INFORMATION_MESSAGE);
							return;
					}else{
							for(int i=0; i<tabla_base.getRowCount(); i++){ 
							    	matriz[i][0] = tabla_base.getValueAt(i, 0);
							    	matriz[i][1] = txtPorcentaje.getText();
						    }
						    
						    if(new ActualizarSQL().Actualizar_IZAGAR_Relacion_de_Asignaciones_Liquidadaso(matriz)){
							    	JOptionPane.showMessageDialog(null, "El registro se actualizo correctamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
									return;
						    }else{
							    	JOptionPane.showMessageDialog(null, "no actualizo","error",JOptionPane.ERROR_MESSAGE);
									return;
						    }
					}
			}
		}
	};
	
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	public void calcular_totales(){
		
		for(int i=0; i<tabla_base.getRowCount(); i++){
			
			asignaciones 	= asignaciones+=1;
//			cortes  		= cortes+=1;
			
//			for(int j=0; j<tabla_base.getColumnCount(); j++){
				
				costo 	= 	costo	+=	Double.valueOf(tabla_base.getValueAt(i,2).toString().trim());
				iva 	=  	iva		+=	Double.valueOf(tabla_base.getValueAt(i,3).toString().trim());
				tasa0 	= 	tasa0	+=	Double.valueOf(tabla_base.getValueAt(i,4).toString().trim());
				tasaE 	=  	tasaE	+=	Double.valueOf(tabla_base.getValueAt(i,5).toString().trim());
				ieps 	=  	ieps	+=	Double.valueOf(tabla_base.getValueAt(i,6).toString().trim());
				importe = 	importe	+=	Double.valueOf(tabla_base.getValueAt(i,7).toString().trim());
				total 	=  	total	+=	Double.valueOf(tabla_base.getValueAt(i,8).toString().trim());
//			}
			
		}
	
		lblAsignacion.setText(asignaciones+"");
//		lblCorte.setText(cortes+"");
		lblCosto.setText(df.format(costo)+"");
		lblIva.setText(df.format(iva)+"");
		lblTasa0.setText(df.format(tasa0)+"");
		lblTasaExent.setText(df.format(tasaE)+"");
		lblIeps.setText(df.format(ieps)+"");
		lblImporte.setText(df.format(importe)+"");
		lblTotal.setText(df.format(total)+"");
		
		asignaciones = 0;
//		cortes  = 0;
		costo = 0;
		iva = 0;
		tasa0 = 0;
		tasaE = 0;
		ieps = 0;
		importe = 0;
		total = 0;
		
//		limpiar tabla_calculada y sus variables
		while(tabla_calculada.getRowCount()>0)
			tabla_model_calculada.removeRow(0);
		
		lblCostoDif.setText("0.0");
		lblIvaDif.setText("0.0");
		lblTasa0Dif.setText("0.0");
		lblTasaExentDif.setText("0.0");
		lblIepsDif.setText("0.0");
		lblImporteDif.setText("0.0");
		lblTotalDif.setText("0.0");
		
		lblTotalfinal.setText("0.0");
		
		costo_dif 	= 0;
		iva_dif 	= 0;
		tasa0_dif 	= 0;
		tasaE_dif 	= 0;
		ieps_dif 	= 0;
		importe_dif = 0;
		total_dif 	= 0;
		
		
	}
	
	ActionListener opAplicar = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			if(tabla_base.getRowCount()<=0){
				JOptionPane.showMessageDialog(null, "No se encontraron datos","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			if(txtPorcentaje.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingresar un porcentaje","Aviso",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
//			el valor sera sustituido por un parametro optenido desde la base de datos
			if(Integer.valueOf(txtPorcentaje.getText())>100){
				JOptionPane.showMessageDialog(null, "El porcentaje aplicado no es valido","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				
					while(tabla_calculada.getRowCount()>0)
		    			tabla_model_calculada.removeRow(0);
				
					String fecha = new SimpleDateFormat("dd/MM/yyyy").format(calendario.getDate());
		    		Object [][] lista_tabla = llenarTabla(1-(Double.valueOf((txtPorcentaje.getText()))/100),fecha,cmbEstablecimiento.getSelectedItem()+"");
	
		    		String[] fila = new String[9];
		                    for(int i=0; i<lista_tabla.length; i++){
		                            fila[0] = lista_tabla[i][0]+"";
		                            fila[1] = lista_tabla[i][1]+"";
		                            fila[2] = lista_tabla[i][2]+"";
		                            fila[3] = lista_tabla[i][3]+"";
		                            fila[4] = lista_tabla[i][4]+"";
		                            fila[5] = lista_tabla[i][5]+"";
		                            fila[6] = lista_tabla[i][6]+"";
		                            fila[7] = lista_tabla[i][7]+"";
		                            fila[8] = lista_tabla[i][8]+"";
		                            tabla_model_calculada.addRow(fila);
		                    }
		                    calcular_Diferiencia();
			}
		}
	};
	
	public void calcular_Diferiencia(){
		
		for(int i=0; i<tabla_calculada.getRowCount(); i++){
			
				costo_dif 	= 	costo_dif	+=	Double.valueOf(tabla_calculada.getValueAt(i,2).toString().trim());
				iva_dif 	=  	iva_dif		+=	Double.valueOf(tabla_calculada.getValueAt(i,3).toString().trim());
				tasa0_dif 	= 	tasa0_dif	+=	Double.valueOf(tabla_calculada.getValueAt(i,4).toString().trim());
				tasaE_dif 	=  	tasaE_dif	+=	Double.valueOf(tabla_calculada.getValueAt(i,5).toString().trim());
				ieps_dif 	=  	ieps_dif	+=	Double.valueOf(tabla_calculada.getValueAt(i,6).toString().trim());
				importe_dif = 	importe_dif	+=	Double.valueOf(tabla_calculada.getValueAt(i,7).toString().trim());
				total_dif 	=  	total_dif	+=	Double.valueOf(tabla_calculada.getValueAt(i,8).toString().trim());
				
				
		}
	
		lblCostoDif.setText(df.format(Double.valueOf(costo_dif+"")));
		lblIvaDif.setText(df.format(Double.valueOf(iva_dif+"")));
		lblTasa0Dif.setText(df.format(Double.valueOf(tasa0_dif+"")));
		lblTasaExentDif.setText(df.format(Double.valueOf(tasaE_dif+"")));
		lblIepsDif.setText(df.format(Double.valueOf(ieps_dif+"")));
		lblImporteDif.setText(df.format(Double.valueOf(importe_dif+"")));
		lblTotalDif.setText(df.format(Double.valueOf(total_dif+"")));
		
		double total_ant = (Double.valueOf(lblTotal.getText()));
		
		lblTotalfinal.setText(df.format(Double.valueOf(total_ant-total_dif)));
		
		costo_dif 	= 0;
		iva_dif 	= 0;
		tasa0_dif 	= 0;
		tasaE_dif 	= 0;
		ieps_dif 	= 0;
		importe_dif = 0;
		total_dif 	= 0;
	}
	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Calculos().setVisible(true);
		}catch(Exception e){	}
		
	}
}
