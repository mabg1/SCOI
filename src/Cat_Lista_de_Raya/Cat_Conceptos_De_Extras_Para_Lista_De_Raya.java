package Cat_Lista_de_Raya;

import java.awt.Container;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Conexiones_SQL.Connexion;
import Obj_Lista_de_Raya.Obj_Conceptos_De_Extras_Para_Lista_De_Raya;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Conceptos_De_Extras_Para_Lista_De_Raya extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	Connexion con = new Connexion();
	
	DefaultTableModel modelo       = new DefaultTableModel(0,4)	{
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	JTable tabla = new JTable(modelo);
	JScrollPane panelScroll = new JScrollPane(tabla);
	
	JTextField txtFolioFiltro = new JTextField();
	JTextField txtConceptoFiltro = new JTextField();
	@SuppressWarnings("rawtypes")
	private TableRowSorter trsfiltro;
	
	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio", 9, "Int");
	JTextField txtConcepto = new Componentes().text(new JTextField(), "Concepto",40, "String");
	JTextField txtAbreviatura = new Componentes().text(new JTextField(), "Abreviatura",20, "String");
	
	String status[] = {"VIGENTE","CANCELADO"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmb_status = new JComboBox(status);
		
	JButton btnBuscar = new JButton("Buscar",new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir",new ImageIcon("imagen/salir16.png"));
	JButton btnDeshacer = new JButton("Deshacer",new ImageIcon("imagen/deshacer16.png"));
	JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
	JButton btnEditar = new JButton("Editar",new ImageIcon("imagen/editara.png"));
	JButton btnNuevo = new JButton("Nuevo",new ImageIcon("imagen/Nuevo.png"));
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Conceptos_De_Extras_Para_Lista_De_Raya(){
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/las-preferencias-de-tema-de-escritorio-icono-8603-32.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Alta De Conceptos De Extras Para Lista De Raya"));
		
		this.setTitle("Conceptos De Extras Para Lista De Raya");
		
		trsfiltro = new TableRowSorter(modelo); 
		tabla.setRowSorter(trsfiltro);
		
		txtFolioFiltro.setToolTipText("Filtro Por Folio");
		txtConceptoFiltro.setToolTipText("Filtro Por Concepto");
		
		int x = 15, y=30, ancho=100;
		
		panel.add(new JLabel("Folio:")).setBounds(x,y,ancho,20);
		panel.add(txtFolio).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnBuscar).setBounds(x+270,y,ancho,20);
		
		
		
		panel.add(new JLabel("Concepto:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtConcepto).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnNuevo).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("Abreviatura:")).setBounds(x,y+=30,ancho,20);
		panel.add(txtAbreviatura).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnEditar).setBounds(x+270,y,ancho,20);
		
		panel.add(new JLabel("Estatus:")).setBounds(x,y+=30,ancho,20);
		panel.add(cmb_status).setBounds(ancho-20,y,ancho+ancho,20);
		panel.add(btnGuardar).setBounds(x+270,y,ancho,20);
		
		panel.add(btnDeshacer).setBounds(x,y+=30,ancho,20);
		panel.add(btnSalir).setBounds(x+165,y,ancho,20);

		
		panel.add(txtFolioFiltro).setBounds(x+ancho+x+40+ancho+ancho+30,20,71,20);
		panel.add(txtConceptoFiltro).setBounds(x+ancho+x+40+ancho+ancho+30+71,20,160,20);
		panel.add(getPanelTabla()).setBounds(x+ancho+x+40+ancho+ancho+30,40,ancho+280,120);
		
		cmb_status.setEnabled(false);
		txtConcepto.setEditable(false);
		txtAbreviatura.setEditable(false);
		
		txtFolio.requestFocus();
		txtFolio.addKeyListener(buscar_action);
//	----------------------------------------------------------------------------------------------------------------	
		btnGuardar.addActionListener(guardar);
		btnSalir.addActionListener(cerrar);
		btnBuscar.addActionListener(buscar);
		btnDeshacer.addActionListener(deshacer);
		btnNuevo.addActionListener(nuevo);
		btnEditar.addActionListener(editar);
		btnEditar.setEnabled(false);
		
		txtFolioFiltro.addKeyListener(opFiltroFolio);
		txtConceptoFiltro.addKeyListener(opFiltroNombre);
		
		cont.add(panel);
		agregar(tabla);
		 llenar_tabla();
		 render();
		 
		 txtConcepto.addKeyListener(enterpasaraAbreviatura);
		
		this.setSize(800,210);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		 ///asigna el foco al filtro
		 this.addWindowListener(new WindowAdapter() {
               public void windowOpened( WindowEvent e ){
               	txtFolio.requestFocus();
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
	
	}
	
	KeyListener opFiltroFolio = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioFiltro.getText(), 0));
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
	
	KeyListener opFiltroNombre = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtConceptoFiltro.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}		
	};
	
	KeyListener enterpasaraAbreviatura = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				txtAbreviatura.requestFocus();
			}
		}
	};
	
	
	private JScrollPane getPanelTabla()	{		

		tabla.getTableHeader().setReorderingAllowed(false) ;
//		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Concepto");
		tabla.getColumnModel().getColumn(1).setMinWidth(180);
		tabla.getColumnModel().getColumn(1).setMaxWidth(180);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Abreviatura");
		tabla.getColumnModel().getColumn(2).setMinWidth(70);
		tabla.getColumnModel().getColumn(2).setMaxWidth(70);
		tabla.getColumnModel().getColumn(3).setHeaderValue("Estatus");
		tabla.getColumnModel().getColumn(3).setMinWidth(80);
		tabla.getColumnModel().getColumn(3).setMaxWidth(80);
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	public void llenar_tabla(){
		Statement s;
		ResultSet rs;
		try {
			s = con.conexion().createStatement();
			rs = s.executeQuery("select folio_concepto_extra,concepto_extra,abreviatura,case when status=1 then 'VIGENTE' ELSE 'CANCELADO' END as status from tb_conceptos_de_extra_de_lista_de_raya " +
					"  order by concepto_extra");
			
			while (rs.next())
			{ 
			   String [] fila = new String[4];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim(); 
			   fila[3] = rs.getString(4).trim(); 
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar la tabla de Conceptos de extra de lista de raya:\n", "Error al cargar la tabla"+e1, JOptionPane.ERROR_MESSAGE,new ImageIcon("Iconos//critica.png"));
		}
	}
	public void render(){
		for(int i=0; i<tabla.getColumnCount(); i++){
			this.tabla.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12));
		}
	}
	
	
	
	private void agregar(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		
	        		int fila = tabla.getSelectedRow();
	        		Object id = tabla.getValueAt(fila,0);
	        
						txtFolio.setText(id+"");
						txtConcepto.setText(tabla.getValueAt(fila,1)+"");
						txtAbreviatura.setText(tabla.getValueAt(fila,2)+"");
						btnEditar.setEnabled(true);
						cmb_status.setSelectedItem(tabla.getValueAt(fila,3)+"");
	        	}
	        }
        });
    }
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "El folio es requerido \n", "Aviso", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
			}else{			
				Obj_Conceptos_De_Extras_Para_Lista_De_Raya concepto = new Obj_Conceptos_De_Extras_Para_Lista_De_Raya().buscar(Integer.parseInt(txtFolio.getText()));
				
				if(concepto.getFolio() == Integer.parseInt(txtFolio.getText())){
					if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
						if(validaCampos()!="") {
							JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
							return;
						}else{
							int nroFila = tabla.getSelectedRow();
							
							concepto.setConcepto(txtConcepto.getText().toUpperCase());
							concepto.setAbreviatura(txtAbreviatura.getText().toUpperCase());
							concepto.setStatus(cmb_status.getSelectedItem()+"");
							
							concepto.actualizar(Integer.parseInt(txtFolio.getText()));
							
							modelo.setValueAt(txtFolio.getText(),nroFila,0);
							modelo.setValueAt(txtConcepto.getText(),nroFila,1);
							modelo.setValueAt(txtAbreviatura.getText(), nroFila, 2);
							
							 while(tabla.getRowCount()>0){
									modelo.removeRow(0);  }
							 llenar_tabla();
							 render();
							 
							panelLimpiar();
							panelEnabledFalse();
							txtFolio.setEditable(true);
							txtFolio.requestFocus();
						}
						
						JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen//aplicara-el-dialogo-icono-6256-32.png"));
					}
				}else{
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Falta Alimentar Datos", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
					}else{
						concepto.setConcepto(txtConcepto.getText());
						concepto.setAbreviatura(txtAbreviatura.getText());
						concepto.setStatus(cmb_status.getSelectedItem()+"");
						concepto.guardar();
						

						 
						txtFolio.setEditable(true);
						txtFolio.requestFocus();
						if(concepto.guardar()){
							Object[] fila = new Object[tabla.getColumnCount()]; 
							
							fila[0]=txtFolio.getText();
							fila[1]=txtConcepto.getText();
							fila[2]=txtAbreviatura.getText();
							modelo.addRow(fila); 
							
							panelLimpiar();
							panelEnabledFalse();
							
							 while(tabla.getRowCount()>0){
									modelo.removeRow(0);  }
							 llenar_tabla();
							 render();
						JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Imagen//aplicara-el-dialogo-icono-6256-32.png"));
						}else{
						JOptionPane.showMessageDialog(null, "Error en Cat_Conceptos_De_Extras_Para_Lista_De_Rata  en la funcion [ Guardar_Concepto_Extra ] \n Insert  SQLException: sp_insert_puesto","Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
						}
						}
				}
			}			
		}
	};
	
	KeyListener buscar_action = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
			}
		}
	};
	
	ActionListener buscar = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
			Obj_Conceptos_De_Extras_Para_Lista_De_Raya concepto_extra = new Obj_Conceptos_De_Extras_Para_Lista_De_Raya();
			concepto_extra = concepto_extra.buscar(Integer.parseInt(txtFolio.getText()));
			
			if(concepto_extra.getFolio() != 0){
			
			txtFolio.setText(concepto_extra.getFolio()+"");
			txtConcepto.setText(concepto_extra.getConcepto()+"");
			txtAbreviatura.setText(concepto_extra.getAbreviatura()+"");
			if(concepto_extra.getStatus().equals("VIGENTE")){cmb_status.setSelectedIndex(0);}
			else{cmb_status.setSelectedIndex(1);}
			
			btnNuevo.setEnabled(false);
			btnEditar.setEnabled(true);
			panelEnabledFalse();
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			
			}
			else{
				JOptionPane.showMessageDialog(null, "El Registro no existe","Aviso",JOptionPane.WARNING_MESSAGE);
				return;
				}
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
		if(txtConcepto.getText().equals("")) 			error+= "Concepto\n";
		if(txtAbreviatura.getText().equals(""))		error+= "Abreviatura\n";
				
		return error;
	}
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Obj_Conceptos_De_Extras_Para_Lista_De_Raya concepto_extra = new Obj_Conceptos_De_Extras_Para_Lista_De_Raya().buscar_nuevo();
			if(concepto_extra.getFolio() != 0){
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(concepto_extra.getFolio()+1+"");
				txtFolio.setEditable(false);
				cmb_status.setEditable(true);
				txtConcepto.requestFocus();
			}else{
				panelLimpiar();
				panelEnabledTrue();
				txtFolio.setText(1+"");
				txtFolio.setEditable(false);
				txtConcepto.requestFocus();
			}
		}
	};
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			panelLimpiar();
			panelEnabledFalse();
			txtFolio.setEditable(true);
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(false);
			cmb_status.setSelectedIndex(0);
			
			txtFolio.requestFocus();
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			panelEnabledTrue();
			txtFolio.setEditable(false);
			btnEditar.setEnabled(false);
			btnNuevo.setEnabled(true);
		}		
	};
	
	public void panelEnabledFalse(){	
		txtFolio.setEditable(false);
		txtConcepto.setEditable(false);
		txtAbreviatura.setEditable(false);
		cmb_status.setEditable(false);
	}		
	
	public void panelEnabledTrue(){	
		txtFolio.setEditable(true);
		txtConcepto.setEditable(true);
		txtAbreviatura.setEditable(true);
		cmb_status.setEnabled(true);
	}
	
	public void panelLimpiar(){	
		txtFolio.setText("");
		txtConcepto.setText("");
		txtAbreviatura.setText("");
		cmb_status.setSelectedIndex(0);
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Conceptos_De_Extras_Para_Lista_De_Raya().setVisible(true);
		}catch(Exception e){	}
	}
}