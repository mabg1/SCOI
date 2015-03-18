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
import java.text.DecimalFormat;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import Obj_Auditoria.Obj_Divisas_Y_Tipo_De_Cambio;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Divisas_Y_Tipo_De_Cambio extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();

	DefaultTableModel modelo       = new DefaultTableModel(0,3)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio", 9, "Int");
	JTextField txtNombre_divisas = new Componentes().text(new JTextField(), "Nombre de Divisa", 100, "String");
	JTextField txtValor = new Componentes().text(new JTextField(), "Valor", 20, "Double");
	
	JCheckBox chStatus = new JCheckBox("Status");
	
	JButton btnDeshacer = new JButton("Deshacer",new ImageIcon("imagen/deshacer16.png"));
	JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
	JButton btnEditar = new JButton("Editar",new ImageIcon("imagen/editara.png"));
	JButton btnNuevo = new JButton("Nuevo",new ImageIcon("imagen/Nuevo.png"));
	JButton btnSalir = new JButton("Salir",new ImageIcon("imagen/salir16.png"));
	
	public Cat_Divisas_Y_Tipo_De_Cambio(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/dinero-icono-8797-32.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Divisa y Tipo de Cambio"));
		
		this.setTitle("Divisa y Tipo de Cambio");
		
//		cont.setBackground(new Color(86,161,85));
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho-20,y,ancho,20);
//		panel.add(btnBuscar).setBounds(x+ancho+ancho+10,y,32,20);
		
		panel.add(chStatus).setBounds(x+(ancho*2),y,70,20);
		
		panel.add(new JLabel("Moneda:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtNombre_divisas).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnNuevo).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("Valor:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtValor).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnEditar).setBounds(x+270,y,ancho,20);
		panel.add(btnDeshacer).setBounds(x+ancho+60,y+=30,ancho,20);
		panel.add(btnSalir).setBounds(x-10+60,y,ancho,20);
		panel.add(btnGuardar).setBounds(x+270,y,ancho,20);
		
		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho+30,20,ancho+230,130);
		
;
		
		txtFolio.setEditable(false);
		txtNombre_divisas.setEditable(false);
		txtValor.setEditable(false);
		chStatus.setEnabled(false);
		
		txtFolio.requestFocus();
		txtValor.addKeyListener(guardar_action);
		
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
		
		this.setSize(760,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void render(){
		//		tipo de valor = imagen,chb,texto
//		tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
    
		tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("numerico","derecha","Arial","negrita",14)); 
		tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",14)); 
		tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",14));
		
	}
	
	private JScrollPane getPanelTabla()	{		
		new Connexion();

		tabla.getTableHeader().setReorderingAllowed(false) ;
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Moneda");
		tabla.getColumnModel().getColumn(1).setMinWidth(160);
		tabla.getColumnModel().getColumn(1).setMaxWidth(160);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Valor");
		tabla.getColumnModel().getColumn(2).setMinWidth(80);
		tabla.getColumnModel().getColumn(2).setMaxWidth(80);
		
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select tb_divisas_tipo_de_cambio.folio as [Folio],"+
					 "  tb_divisas_tipo_de_cambio.nombre_divisas as [Nombre], "+
					 "  tb_divisas_tipo_de_cambio.valor as [Valor] "+
					
					"  from tb_divisas_tipo_de_cambio where status=1");
			
			while (rs.next())
			{ 
				DecimalFormat decimalFormat = new DecimalFormat("#0.00");
				String cantidadd = decimalFormat.format(Double.parseDouble(rs.getString(3)));
				float saldo = Float.parseFloat(cantidadd);
			   String [] fila = new String[3];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = saldo+""; 
			   
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
	        
						Obj_Divisas_Y_Tipo_De_Cambio denominaciones = new Obj_Divisas_Y_Tipo_De_Cambio().buscar(id);
						
						txtFolio.setText(id+"");
						txtNombre_divisas.setText(modelo.getValueAt(fila,1)+"");
						txtValor.setText(modelo.getValueAt(fila,2)+"");
						btnEditar.setEnabled(true);
						btnNuevo.setEnabled(false);
						txtNombre_divisas.setEditable(false);
						txtValor.setEditable(false);
						chStatus.setSelected(true);
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				return;
			}
			else{			
				Obj_Divisas_Y_Tipo_De_Cambio divisas = new Obj_Divisas_Y_Tipo_De_Cambio().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(divisas.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						
							int nroFila = tabla.getSelectedRow();
							divisas.setNombre(txtNombre_divisas.getText());
							divisas.setValor(Float.parseFloat(txtValor.getText()));
							divisas.setStatus(chStatus.isSelected());
							divisas.actualizar(Integer.parseInt(txtFolio.getText()));
							btnNuevo.setEnabled(true);
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txtNombre_divisas.getText(),nroFila,1);
							modelo.setValueAt(txtValor.getText(), nroFila, 2);
							
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
						
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}else{
						return;
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						divisas.setNombre(txtNombre_divisas.getText());
						divisas.setValor(Float.parseFloat(txtValor.getText()));
						divisas.setStatus(chStatus.isSelected());
						divisas.guardar();
						
						Object[] fila = new Object[tabla.getColumnCount()]; 
							
						fila[0]=txtFolio.getText();
						fila[1]=txtNombre_divisas.getText();
						fila[2]=txtValor.getText();
						modelo.addRow(fila); 
						
						panelLimpiar();
						panelEnabledFalse();
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
					}
				}
			}			
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
	ActionListener cerrar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
		
	};
	
	private String validaCampos(){
		String error="";
		if(txtNombre_divisas.getText().equals("")) 			error+= "Bono\n";
		if(txtValor.getText().equals(""))		error+= "Valor\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Divisas_Y_Tipo_De_Cambio denominaciones = new Obj_Divisas_Y_Tipo_De_Cambio().buscar_nuevo();
			if(denominaciones.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				btnEditar.setEnabled(false);
				txtFolio.setText(denominaciones.getFolio()+1+"");
				txtNombre_divisas.requestFocus();
				chStatus.setSelected(true);
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtNombre_divisas.requestFocus();
				chStatus.setSelected(true);
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.requestFocus();
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(false);
			chStatus.setSelected(false);
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
		txtFolio.setEditable(false);
		txtNombre_divisas.setEditable(false);
		txtValor.setEditable(false);
		
		txtNombre_divisas.setEditable(false);
	}		
	
	public void panelEnabledTrue(){	
		txtNombre_divisas.setEditable(true);
		txtValor.setEditable(true);
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtNombre_divisas.setText("");
		txtValor.setText("");
		chStatus.setSelected(true);
	}
}