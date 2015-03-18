package Cat_Auditoria;

import java.awt.Container;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.table.DefaultTableModel;

import Conexiones_SQL.Connexion;
import Obj_Auditoria.Obj_Denominaciones;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Denominaciones extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,5)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JTextField txtFolio = new JTextField();
	JTextField txtDenominacion = new JTextField();
	JTextField txtValorD = new JTextField();
	
	String divisa[] = new Obj_Denominaciones().Combo_Denominaciones();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbMoneda = new JComboBox(divisa);
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnDeshacer = new JButton("Deshacer",new ImageIcon("imagen/deshacer16.png"));
	JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
	JButton btnEditar = new JButton("Editar",new ImageIcon("imagen/editara.png"));
	JButton btnNuevo = new JButton("Nuevo",new ImageIcon("imagen/Nuevo.png"));
	JButton btnSalir = new JButton("Salir",new ImageIcon("imagen/salir16.png"));
	
	public Cat_Denominaciones(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/dinero-icono-8797-32.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Denominaciones"));
		
		this.setTitle("Denominaciones");
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho+10,y,ancho-10,20);
		
		panel.add(chStatus).setBounds(x+(ancho*2)+20,y,70,20);
		
		panel.add(new JLabel("Denominacion:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtDenominacion).setBounds(ancho+10,y,ancho+ancho-10,20);
		panel.add(btnNuevo).setBounds(x+290,y,ancho,20);
		
		panel.add(new JLabel("Valor denominacion:")).setBounds(x,y+=30,ancho+20,20);
		panel.add(txtValorD).setBounds(ancho+10,y,ancho+ancho-10,20);
		
		panel.add(new JLabel("Moneda:")).setBounds(x,y+=30,ancho,20);
		panel.add(cmbMoneda).setBounds(ancho+10,y,ancho+ancho-10,20);
		panel.add(btnEditar).setBounds(x+290,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho+85,y+=30,ancho,20);
		panel.add(btnSalir).setBounds(x+80,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+290,y,ancho,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho*4,20,380,150);
		
		chStatus.setSelected(true);
		chStatus.setEnabled(false);
		txtFolio.setEditable(false);
		txtDenominacion.setEditable(false);
		txtValorD.setEditable(false);
		cmbMoneda.setEnabled(false);
		
		txtDenominacion.requestFocus();
		txtFolio.addKeyListener(numerico_action);
		cmbMoneda.addKeyListener(guardar_action);
		
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		btnEditar.setEnabled(false);
		cont.add(panel);
		
		agregar(tabla);
		
		///deshacer con escape
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
          getRootPane().getActionMap().put("escape", new AbstractAction(){
         public void actionPerformed(ActionEvent e)
         {                 	    btnDeshacer.doClick();
       	    }
     });
     	///guardar con control+G
         getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G,Event.CTRL_MASK),"guardar");
           getRootPane().getActionMap().put("guardar", new AbstractAction(){
              public void actionPerformed(ActionEvent e)
              {                 	    btnGuardar.doClick();
            	    }
         });
       ///guardar con F12
          getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "guardar");
              getRootPane().getActionMap().put("guardar", new AbstractAction(){
                  public void actionPerformed(ActionEvent e)
                  {                 	    btnGuardar.doClick();
                    	    }
             });
              ///nuevo con F9
              getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "nuevo");
               getRootPane().getActionMap().put("nuevo", new AbstractAction(){
                   public void actionPerformed(ActionEvent e)
                   {                 	    btnNuevo.doClick();
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
               
           ///nuevo con control+N
              getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N,Event.CTRL_MASK),"nuevo");
                getRootPane().getActionMap().put("nuevo", new AbstractAction(){
                        public void actionPerformed(ActionEvent e)
                    {                 	    btnNuevo.doClick();
	                    	    }
              });
                
                render();
		
		this.setSize(820,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void render(){
		//		tipo de valor = imagen,chb,texto
//		tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
    
		tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("numerico","derecha","Arial","negrita",10)); 
		tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",10)); 
		tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",10));
		tabla.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",10));
		tabla.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",10));
		
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();
		tabla.getTableHeader().setReorderingAllowed(false) ;
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(30);
		tabla.getColumnModel().getColumn(0).setMinWidth(30);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Denominacion");
		tabla.getColumnModel().getColumn(1).setMinWidth(100);
		tabla.getColumnModel().getColumn(1).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setHeaderValue("# Denominacion");
		tabla.getColumnModel().getColumn(2).setMinWidth(90);
		tabla.getColumnModel().getColumn(2).setMaxWidth(90);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Moneda");
		tabla.getColumnModel().getColumn(3).setMinWidth(60);
		tabla.getColumnModel().getColumn(3).setMaxWidth(60);
		tabla.getColumnModel().getColumn(4).setHeaderValue("Status");
		tabla.getColumnModel().getColumn(4).setMinWidth(30);
		tabla.getColumnModel().getColumn(4).setMaxWidth(30);
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("exec sp_select_alta_denominaciones");
			
			while (rs.next())
			{ 
			   String [] fila = new String[5];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim();
			   fila[3] = rs.getString(4).trim();
			   fila[4] = rs.getString(5).trim();
			   
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	@SuppressWarnings("unused")
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		int id = Integer.parseInt(modelo.getValueAt(fila,0)+"");
	        
						Obj_Denominaciones denominaciones = new Obj_Denominaciones().buscar(id);
						
						txtFolio.setText(id+"");
						txtDenominacion.setText(modelo.getValueAt(fila,1)+"");
						txtValorD.setText(modelo.getValueAt(fila,2)+"");
						cmbMoneda.setSelectedItem(modelo.getValueAt(fila,3)+"");
						btnEditar.setEnabled(true);
						btnNuevo.setEnabled(false);
						txtDenominacion.setEditable(false);
						txtValorD.setEditable(false);
						
						if(Integer.parseInt(modelo.getValueAt(fila,4)+"")!=0){
							chStatus.setSelected(true);
						}else{
							chStatus.setSelected(false);
						}
						
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Folio Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
			if(txtDenominacion.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El Nombre De La Denominacion Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Denominaciones denominaciones = new Obj_Denominaciones().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(denominaciones.getFolio() == Integer.parseInt(txtFolio.getText())){
					
					if(cmbMoneda.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "Seleccione un Tipo de Moneda", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){

							int nroFila = tabla.getSelectedRow();
							denominaciones.setDenominacion(txtDenominacion.getText()+"");
							denominaciones.setValor_denominacion(Float.parseFloat(txtValorD.getText()+""));							
							denominaciones.setMoneda(cmbMoneda.getSelectedItem()+"");
							denominaciones.setStatus(chStatus.isSelected());
							btnNuevo.setEnabled(true);
							denominaciones.actualizar(Integer.parseInt(txtFolio.getText()+""));
							
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txtDenominacion.getText(),nroFila,1);
							modelo.setValueAt(txtValorD, nroFila, 2);
							modelo.setValueAt(cmbMoneda.getSelectedItem()+"", nroFila, 3);
							
							if(denominaciones.isStatus()==true){
								modelo.setValueAt(1, nroFila, 4);
							}else{
								modelo.setValueAt(0, nroFila, 4);
							}
		
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}else{
						return;
					}
				}else{
					if(txtDenominacion.getText().equals("")){
						JOptionPane.showMessageDialog(null, "El Nombre De La Denominacion Es Requerido", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
					}
					if(cmbMoneda.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "Seleccione un Tipo de Moneda", "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						denominaciones = denominaciones.buscar(Integer.parseInt(txtFolio.getText()));
						
						denominaciones.setDenominacion(txtDenominacion.getText());
						denominaciones.setValor_denominacion(Float.parseFloat(txtValorD.getText()+""));
						denominaciones.setMoneda(cmbMoneda.getSelectedItem()+"");
						denominaciones.setStatus(chStatus.isSelected());
						denominaciones.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						fila[0]=txtFolio.getText();
						fila[1]=txtDenominacion.getText();
						fila[2]=txtValorD.getText();
						fila[3]=cmbMoneda.getSelectedItem()+"";
						
						if(denominaciones.isStatus()==true){
							fila[4]=1;
						}else{
							fila[4]=0;
						}	
						modelo.addRow(fila); 
						
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}
			txtDenominacion.requestFocus();
			panelLimpiar();
			panelEnabledFalse();
		}
	};
	
	KeyListener guardar_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnGuardar.doClick();
			}
		}
	};
	
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
		    	String texto =cmbMoneda.getSelectedIndex()+"";

				if (texto.indexOf(".")>0) e.consume();
			}
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
		
	};
	
	@SuppressWarnings("unused")
	private String validaCampos(){
		String error="";
		if(txtDenominacion.getText().equals("")) 			error+= "Nombre\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Denominaciones denominaciones = new Obj_Denominaciones().buscar_nuevo();
			if(denominaciones.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(denominaciones.getFolio()+1+"");
				txtDenominacion.requestFocus();
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtDenominacion.requestFocus();
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			panelLimpiar();
			panelEnabledFalse();
			
			txtFolio.setText("");
			txtDenominacion.requestFocus();
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(false);
			chStatus.setSelected(true);
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			btnEditar.setEnabled(false);
			btnNuevo.setEnabled(false);
			
		}		
	};
	
	public void panelEnabledFalse(){	
		txtDenominacion.setEditable(false);
		txtValorD.setEnabled(false);
		cmbMoneda.setEnabled(false);
	}		
	
	public void panelEnabledTrue(){	
		txtDenominacion.setEditable(true);
		txtValorD.setEditable(true);
		txtValorD.setEnabled(true);
		cmbMoneda.setEnabled(true);
	}
	
	public void panelLimpiar(){
		txtFolio.setText("");
		txtDenominacion.setText("");
		txtValorD.setText("");
		cmbMoneda.setSelectedIndex(0);
	}
}