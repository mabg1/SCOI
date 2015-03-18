package Cat_Lista_de_Raya;

import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Conexiones_SQL.Connexion;
import Obj_Lista_de_Raya.Obj_Empleados;
import Obj_Lista_de_Raya.Obj_Prestamos;
import Obj_Lista_de_Raya.Obj_Rango_De_Prestamos;

@SuppressWarnings({ "serial", "unchecked" })
public class Cat_Filtro_De_Prestamos extends JFrame {
	
	Double rangoIn;
	Double rangoFin;

	int folio_empleado = 0;
	float saldo = 0;
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	DefaultTableModel	 modelo       = new DefaultTableModel(0,8)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla                   = new JTable(modelo);
	JScrollPane panelScroll        = new JScrollPane(tabla);
	
	JLabel txtFolio_Empleado = new JLabel();
	JLabel txtNombre_Completo = new JLabel();
	
	String rango_prestamo[] = new Obj_Rango_De_Prestamos().Combo_Prestamos();
	JLabel lblRango = new JLabel();
	JLabel lblEtiquetaRango = new JLabel("Limite de Prestamo:");
	
	JTextField txtCantidad = new JTextField();
	JTextField txtDescuento = new JTextField();
	JTextField txtSaldo = new JTextField();
	JTextField txtAbonos = new JTextField();

	
	String status[] = {"Vigente","Cancelado Temporal"};
	@SuppressWarnings("rawtypes")
	JComboBox cmbStatus = new JComboBox(status);
	
	String tipo_prestamo[] = {"Contable","No Contable"};
	@SuppressWarnings({ "rawtypes"})
	JComboBox cmbtipo_prestamo = new JComboBox(tipo_prestamo);
	
	com.toedter.calendar.JDateChooser txtCalendario = new com.toedter.calendar.JDateChooser();
	JLabel lblTotal = new JLabel("");
	
	JButton btnFiltro  = new JButton("Buscar Otro Empleado",new ImageIcon("imagen/Text preview.png"));
	JButton btnEditar  = new JButton("Editar"              ,new ImageIcon("imagen//Modify.png"));
	JButton btnGuardar = new JButton("Guardar"             ,new ImageIcon("imagen//Guardar.png"));
	JButton btnDeshacer = new JButton("Deshacer"           ,new ImageIcon("imagen/deshacer16.png"));
	
	String empleado="";
	
	public Cat_Filtro_De_Prestamos(String algo) {
		empleado=algo;
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/prestamo.png"));
		this.setTitle("Prestamos");
		int x = 20,	y=20, ancho=100,a=20;
		txtCantidad.requestFocus();
		panel.setBorder(BorderFactory.createTitledBorder("Prestamo"));		
		
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Fecha");
		tabla.getColumnModel().getColumn(1).setMinWidth(80);
		tabla.getColumnModel().getColumn(1).setMaxWidth(80);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Cantidad");
		tabla.getColumnModel().getColumn(2).setMinWidth(70);
		tabla.getColumnModel().getColumn(2).setMaxWidth(70);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Descuento");
		tabla.getColumnModel().getColumn(3).setMinWidth(70);
		tabla.getColumnModel().getColumn(3).setMaxWidth(70);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Saldo");
		tabla.getColumnModel().getColumn(4).setMinWidth(70);
		tabla.getColumnModel().getColumn(4).setMaxWidth(70);
		tabla.getColumnModel().getColumn(5).setHeaderValue("Total Abonos");
		tabla.getColumnModel().getColumn(5).setMinWidth(80);
		tabla.getColumnModel().getColumn(5).setMaxWidth(80);
		tabla.getColumnModel().getColumn(6).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(6).setMinWidth(55);
		tabla.getColumnModel().getColumn(6).setMaxWidth(55);
		tabla.getColumnModel().getColumn(7).setHeaderValue("Tipo Prestamo");
		tabla.getColumnModel().getColumn(7).setMinWidth(90);
		tabla.getColumnModel().getColumn(7).setMaxWidth(90);
		
		agregar(tabla);
		
		panel.add(new JLabel("Folio Empleado:")).setBounds(x,y,ancho-20,a);
		panel.add(txtFolio_Empleado).setBounds(x+=80,y,ancho-40,a);
		
		panel.add(new JLabel("Nombre:")).setBounds(x+=40,y,ancho-50,a);
		panel.add(txtNombre_Completo).setBounds(x+=45,y,ancho*2,a);
		
		panel.add(lblEtiquetaRango).setBounds(x+=220,y,ancho+40,a);
		panel.add(lblRango).setBounds(x+ancho+25,y,ancho+40,a);
		
		x=a;
		panel.add(new JLabel("Fecha:")).setBounds(x,y+=30,ancho-50,a);
		panel.add(txtCalendario).setBounds(x+=60,y,ancho-15,a);
		
		panel.add(new JLabel("Estatus:")).setBounds(x+=160,y,ancho-50,a);
		panel.add(cmbStatus).setBounds(x+=50,y,ancho-15,a);
		
		panel.add(new JLabel("Tipo Prestamo:")).setBounds(x+=165,y,ancho,a);
		panel.add(cmbtipo_prestamo).setBounds(x+=ancho-25,y,ancho-15,a);
		
		
		x=a;
		panel.add(new JLabel("Cantidad:")).setBounds(x,y+=30,ancho,a);
		panel.add(txtCantidad).setBounds(x+=60,y,ancho-15,a);
		
		panel.add(new JLabel("Saldo:")).setBounds(x+=160,y,ancho,a);
		panel.add(txtSaldo).setBounds(x+=50,y,ancho-15,a);
		
		
		x=a;
		panel.add(new JLabel("Descuento:")).setBounds(x,y+=25,ancho,a);
		panel.add(txtDescuento).setBounds(x+=60,y,ancho-15,a);
		
		panel.add(new JLabel("Abonos:")).setBounds(x+=160,y,ancho,a);
		panel.add(txtAbonos).setBounds(x+=50,y,ancho-15,a);
		
		x=a;
		panel.add(lblTotal).setBounds(x+350,y, 400, 200);

		
		panel.add(panelScroll).setBounds(x,y+=25+10,ancho+495,90);
		
		panel.add(btnEditar).setBounds(x,y+=110,ancho,a);
		panel.add(btnGuardar).setBounds(x+=ancho+20,y,ancho,a);
		panel.add(btnDeshacer).setBounds(x+=ancho+20,y,ancho,a);
		panel.add(btnFiltro).setBounds(x+=195,y,ancho+60,a);
		
		lblTotal.setFont(new java.awt.Font("Algerian",0,60));
		lblRango.setFont(new java.awt.Font("SansSerif",8,14));
		lblEtiquetaRango.setFont(new java.awt.Font("SansSerif",8,14));	
		lblEtiquetaRango.setForeground(Color.blue);
		lblRango.setForeground(Color.blue);
		
		
		btnFiltro.addActionListener(filtro);
		btnEditar.addActionListener(Editar);
		btnGuardar.addMouseListener(guardar);
		btnDeshacer.addActionListener(deshacer);
		
                  
		txtCantidad.addKeyListener(validaNumericoConPunto);
		txtDescuento.addKeyListener(valida_numeric);
		
//      asigna el foco al JTextField deseado al arrancar la ventana
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){
                btnFiltro.requestFocus();
             }
        });
        
		cont.add(panel);
		
		Obj_Empleados re = new Obj_Empleados();
		re = re.buscar(Integer.parseInt(empleado));
	
		folio_empleado= re.getFolio();
		txtFolio_Empleado.setText(folio_empleado+"");
		txtNombre_Completo.setText(re.getNombre()+" "+re.getAp_paterno()+" "+re.getAp_materno()+"");	
		lblRango.setText(rango_prestamo[re.getPrestamo()]);
		txtAbonos.setText("0");
		txtSaldo.setText("0");
		 
   	 
		panelEnabledTrue();
		
		String Rango =rango_prestamo[re.getPrestamo()];
		Rango = Rango.replace(" - ", "-");
		String[] splits = Rango.split("-");
		
		double uno = Double.parseDouble(splits[0]);
		double dos = Double.parseDouble(splits[1]);

		rangoIn  = uno;
		rangoFin = dos;
								
		String[][] Tabla = getMatriz(Integer.parseInt(txtFolio_Empleado.getText()));
		Object[] fila = new Object[tabla.getColumnCount()];
		for(int i=0; i<Tabla.length; i++){
			modelo.addRow(fila); 
			for(int j=0; j<tabla.getColumnCount(); j++){
				modelo.setValueAt(Tabla[i][j]+"", i,j);
			}
		}
		
		if(tabla.getRowCount() != 0){
			try {
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(modelo.getValueAt(0,1)+"");
				txtCalendario.setDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtCantidad.setText(modelo.getValueAt(0, 2)+"");
			txtDescuento.setText(modelo.getValueAt(0, 3)+"");
			txtSaldo.setText(modelo.getValueAt(0, 4)+"");
			txtAbonos.setText(modelo.getValueAt(0,5)+"");
			
			
			if(modelo.getValueAt(0, 6).equals("VIGENTE")){
						cmbStatus.setSelectedIndex(0);
					}else{
						cmbStatus.setSelectedIndex(1);
			}
			
			 if(modelo.getValueAt(0, 7).equals("CONTABLE")){
					cmbtipo_prestamo.setSelectedIndex(0);
				}else{
					cmbtipo_prestamo.setSelectedIndex(1);
			 }
			 

			
			panelEnabledFalse();
			btnGuardar.setEnabled(false);
		}
		
		
         ///buscar F2
         getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "filtro");
             getRootPane().getActionMap().put("filtro", new AbstractAction(){
                 public void actionPerformed(ActionEvent e)
                 {             	  btnFiltro.doClick();
               	    }
            });
             
         
		///deshacer con escape
	     getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
	     	getRootPane().getActionMap().put("escape", new AbstractAction(){
	     		public void actionPerformed(ActionEvent e)
	      		{                 	    btnDeshacer.doClick();
	    	    }
	  });
  	///guardar con control+G
      getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G,Event.CTRL_MASK),"guardar");
//        getRootPane().getActionMap().put("guardar", new AbstractAction(){
//           public void actionPerformed(ActionEvent e)
//           {                 	    btnGuardar.doClick();
//         	    }
//      });
    ///guardar con F12
       getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "guardar");
           getRootPane().getActionMap().put("guardar", new AbstractAction(){
               public void actionPerformed(ActionEvent e)
               {                 	    btnGuardar.doClick();
                 	    }
          });
           
    ///editar con F10
           getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "editar");
               getRootPane().getActionMap().put("editar", new AbstractAction(){
                   public void actionPerformed(ActionEvent e)
                   {                 	    btnEditar.doClick();
	                    	    }
              });
    ///editar con Ctrl+E
           getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E,Event.CTRL_MASK), "editar");
               getRootPane().getActionMap().put("editar", new AbstractAction(){
                   public void actionPerformed(ActionEvent e)
                   {                 	    btnEditar.doClick();
	                    	    }
              });
				
		this.setSize(650,330);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	
	        		panelEnabledFalse();
	        		btnGuardar.setEnabled(false);
	        		int fila = tabla.getSelectedRow();
	        		
	        		try {
	    				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(modelo.getValueAt(fila,1)+"");
	    				txtCalendario.setDate(date);
	    			} catch (ParseException e1) {
	    				e1.printStackTrace();
	    			}
	    			txtCantidad.setText(modelo.getValueAt(fila, 2)+"");
	    			txtDescuento.setText(modelo.getValueAt(fila, 3)+"");
	    			if(modelo.getValueAt(fila, 6).equals("VIGENTE")){
	    				cmbStatus.setSelectedIndex(0);
	    			}else{
	    				cmbStatus.setSelectedIndex(1);
	    				 }
	    			
	    			if(modelo.getValueAt(fila, 7).equals("CONTABLE")){
	    				cmbtipo_prestamo.setSelectedIndex(0);
	    			}else{
	    				cmbtipo_prestamo.setSelectedIndex(1);
	    				 }
	        }
        });
    }
	
	MouseListener guardar = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
	    	 
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
				return;
			}else{
				if((Double.parseDouble(txtCantidad.getText())-Double.parseDouble(txtAbonos.getText()))   > rangoFin){
					JOptionPane.showMessageDialog(null,"Se Le Quiere Poner Un Prestamo De  Cantidad-Total Abonos="+(Double.parseDouble(txtCantidad.getText())-Double.parseDouble(txtAbonos.getText()))+
							                   "  \n y Solo Tiene Autorizado Para Prestamo La Cantidad Maxima De $"+rangoFin+
							                   " \n Avise Al Encargado De Desarrollo Humano Si Se Le Autoriza Subir El A", "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
					return;
				}
				if(Double.parseDouble(txtDescuento.getText())>Double.parseDouble(txtCantidad.getText())){
					JOptionPane.showMessageDialog(null,"El Descuento Es Mayor Que El Prestamo \n Descuento:"+Double.parseDouble(txtDescuento.getText())+" Cantidad Prestamo:"+Double.parseDouble(txtCantidad.getText()), "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
					return;
				}
				if(Double.parseDouble(txtCantidad.getText())<Double.parseDouble(txtAbonos.getText())){
					JOptionPane.showMessageDialog(null,"La Cantidad No Puede Ser Menor Que La Suma De Los Abonos \n Cantidad:"+Double.parseDouble(txtCantidad.getText())+" Total Abonos:"+Double.parseDouble(txtAbonos.getText()), "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
					return;
				}
				
				
				Obj_Prestamos pres = new Obj_Prestamos();
				
				switch(tabla.getRowCount()){
					case 0: 
						if(Double.parseDouble(txtDescuento.getText()) > Double.parseDouble(txtCantidad.getText())){
							JOptionPane.showMessageDialog(null, "El Descuento:"+Double.parseDouble(txtDescuento.getText())+" Es Mayor Que La Cantidad:"+Double.parseDouble(txtCantidad.getText()), "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
							return;	
						}else{
							pres.setFolio(Integer.parseInt(txtFolio_Empleado.getText()));
							pres.setFolio_empleado(Integer.parseInt(txtFolio_Empleado.getText()));
							pres.setNombre_Completo(txtNombre_Completo.getText());
							pres.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
							pres.setCantidad(Double.parseDouble(txtCantidad.getText()));
							pres.setDescuento(Double.parseDouble(txtDescuento.getText()));
							pres.setSaldo(Double.parseDouble(txtCantidad.getText()));
							pres.setStatus(cmbStatus.getSelectedIndex()+1);
							pres.setStatus_descuento(cmbStatus.getSelectedIndex()+1);
							pres.setTipo_prestamo(cmbtipo_prestamo.getSelectedIndex());
			
							pres.guardar();
							
							JOptionPane.showMessageDialog(null,"El registro se guardo exitosamente", "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/aplicara-el-dialogo-icono-6256-32.png"));
							
							if(pres.getStatus_descuento()==1){
								Object[] fila = new Object[tabla.getColumnCount()]; 
								try {
									Obj_Prestamos maximo = new Obj_Prestamos().maximo();
									
									fila[0]=maximo.getFolio();
									fila[1]=new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate());
									fila[2]=txtCantidad.getText();
									fila[3]=txtDescuento.getText();
									fila[4]=txtCantidad.getText();
									fila[5]=0.00;
									
									switch(cmbStatus.getSelectedIndex()){
										case 0: fila[6]="VIGENTE";break;	
										case 1: fila[6]="CANCELADO TEMPORAL";break;
									}
									switch(cmbtipo_prestamo.getSelectedIndex()){
										case 0: fila[7]="CONTABLE";break;	
										case 1: fila[7]="NO CONTABLE";break;
									}
									modelo.addRow(fila); 	
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						}
						
						break;
					case 1: 
						if(Double.parseDouble(txtDescuento.getText()) > Double.parseDouble(modelo.getValueAt(0,4)+"")){
							JOptionPane.showMessageDialog(null,"El Descuento Que Quiere Aplicar Es Mayor Que Con Lo Que Salda La Cuenta", "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
							return;
						}
						if(Double.parseDouble(txtCantidad.getText()) < Double.parseDouble(txtDescuento.getText()) || 
								Double.parseDouble(txtCantidad.getText()) == 0 ){
							JOptionPane.showMessageDialog(null,"No Es Posible Agregar Una Cantidad Menor Que El Descuento", "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
							return;
						}					
						if(Double.parseDouble(txtCantidad.getText()) == 0 ){
							JOptionPane.showMessageDialog(null,"La Cantidad Tiene Valor Cero", "Mensaje", JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
							return;
						}
						if(JOptionPane.showConfirmDialog(null, "Desea Actualizar el registro existente ?") == JOptionPane.YES_OPTION) {
							pres.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(txtCalendario.getDate()));
							pres.setCantidad(Double.parseDouble(txtCantidad.getText()));
							pres.setDescuento(Double.parseDouble(txtDescuento.getText()));
							pres.setStatus(cmbStatus.getSelectedIndex()+1);
							pres.setTipo_prestamo(cmbtipo_prestamo.getSelectedIndex());
						
							pres.actualizar(Integer.parseInt(modelo.getValueAt(0,0)+""));
										
							int filas=  tabla.getRowCount();
							while(filas > 0){
								modelo.removeRow(0);
								filas--;
							}
										
							String[][] Tabla = getMatriz(Integer.parseInt(txtFolio_Empleado.getText()));
							Object[] fila = new Object[tabla.getColumnCount()]; 
							for(int i=0; i<Tabla.length; i++){
								modelo.addRow(fila); 
								for(int j=0; j<7; j++){
									modelo.setValueAt(Tabla[i][j]+"", i,j);
								}
							}
						}
						break;
				}
				panelEnabledFalse();
			}
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	
	ActionListener filtro = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new Cat_Prestamos().setVisible(true);
			
		}
	};	
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new Cat_Filtro_De_Prestamos(empleado).setVisible(true);
			
		panelEnabledFalse();	
		}
	};	
	
   ActionListener Editar = new ActionListener(){
			public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			txtCantidad.requestFocus();
			btnGuardar.setEnabled(true);
		}
	};
	
	public void panelEnabledTrue(){	
		txtCantidad.setEnabled(true);
		txtDescuento.setEnabled(true);
		cmbStatus.setEnabled(true);
		cmbtipo_prestamo.setEnabled(true); 
		txtCalendario.setEnabled(true);
		txtAbonos.setEnabled(false);
		txtSaldo.setEnabled(false);

	}
	
	public void panelEnabledFalse(){	
		txtCantidad.setEnabled(false);
		txtDescuento.setEnabled(false);
		cmbStatus.setEnabled(false);
		cmbtipo_prestamo.setEnabled(false);
		txtCalendario.setEnabled(false);
		txtSaldo.setEnabled(false);
		txtAbonos.setEnabled(false);
	}
	
	KeyListener numerico_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();

		   if(((caracter < '0') ||
		        (caracter > '9')) &&
		        (caracter != KeyEvent.VK_BACK_SPACE)){
		    	e.consume(); 
		    }			
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
		
	KeyListener validaNumericoConPunto = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){    	
		    	String texto = txtCantidad.getText().toString();
				if (texto.indexOf(".")>-1) e.consume();
			} 		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}							
	};
	
	KeyListener valida_numeric = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.' )){
		    	e.consume();
		    	}
		    	
		   if (caracter==KeyEvent.VK_PERIOD){    	
		    	String texto = txtDescuento.getText().toString();
				if (texto.indexOf(".")>-1) e.consume();
			} 		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}							
	};
	
	private String validaCampos(){
		String error="";
		String fechaNull = txtCalendario.getDate()+"";
		if(txtNombre_Completo.getText().equals(""))error+= "Nombre Completo\n";
		if(txtCantidad.getText().equals(""))error+= "Cantidad\n";
		if(txtDescuento.getText().equals(""))error+= "Descuento\n";
		if(fechaNull.equals("null"))error+= "Fecha\n";
				
		return error;
	}

	public String[][] getMatriz(int folio_empleado){
		
		String qry = "exec sp_select_prestamo "+folio_empleado;
		System.out.println(qry);
		
		String[][] Matriz = new String[getFilas(qry)][8];
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery(qry);
			int i=0;
			while(rs.next()){
				
					DecimalFormat decimalFormat = new DecimalFormat("#0.00");
										
					Matriz[i][0] = rs.getInt(1)+"";
					Matriz[i][1] = rs.getString(2).trim();
					Matriz[i][2] = decimalFormat.format(Double.parseDouble(rs.getString(3)))+"";
					Matriz[i][3] = decimalFormat.format(Double.parseDouble(rs.getString(4)))+"";
					Matriz[i][4] = decimalFormat.format(Double.parseDouble(rs.getString(5)))+"";
					Matriz[i][5] = decimalFormat.format(Double.parseDouble(rs.getString(6)))+"";
					Matriz[i][6] = rs.getString(7);
					Matriz[i][7] = rs.getString(8);
				
					i++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    return Matriz; 
	}
	
	public int getFilas(String qry){
		int filas=0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}
	
	public void suma(){
		float suma = 0;
		
		for(int i=0;i<modelo.getRowCount(); i++) {
			float datos= Float.parseFloat(modelo.getValueAt(i,4).toString());
			suma=(suma+datos); 
		} 
		
		lblTotal.setText("$ "+String.valueOf(suma));
	}
	public float getDescuentoPrest(int folio){
		float valor = 0;
		try {
			
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery("select sum(descuento)as 'descuento' from tb_abono where folio_empleado = "+folio+" and status = 1");
			while(rs.next()){
				valor = rs.getFloat(1);			
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor;
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Filtro_De_Prestamos("1").setVisible(true);
		}catch(Exception e){	}
	}

	
}
