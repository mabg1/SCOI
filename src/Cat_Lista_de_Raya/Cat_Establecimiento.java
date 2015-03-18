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
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

@SuppressWarnings("serial")
public class Cat_Establecimiento extends JFrame{
	int foliosiguiente=0;
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio", 9, "Int");
	JTextField txtEstablecimiento= new Componentes().text(new JTextField(), "Establecimiento",250,"String");
	JTextField txtAbreviatura = new Componentes().text(new JTextField(), "Abreviatura", 5, "String");
	JTextField txtSerie = new Componentes().text(new JTextField(), "Serie", 5, "String");
	JTextField txtFolioFiltro = new JTextField();
	JTextField txtUnidadFiltro = new Componentes().text(new JTextField(), "Filtro Por Nombre de Establecimiento", 30, "String");
	
	JTextField txtDomicilio = new Componentes().text(new JTextField(), "Domicilio", 100, "String");
	JTextField txtRazonSocial = new Componentes().text(new JTextField(), "Razon Social", 60, "String");
	JTextField txtRFC = new Componentes().text(new JTextField(), "RFC", 20, "String");
	JTextField txtTelefono = new Componentes().text(new JTextField(), "Telefono", 15, "Int");
	
	String grupocheque[] = {"Sin Grupo","SUPER","FERRE Y REFA","IZACEL"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmb_grupo_cheque = new JComboBox(grupocheque);
	
	String grupocorte[] = new Obj_Establecimiento().Combo_Grupo_Corte();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmb_grupo_corte = new JComboBox(grupocorte);
	
	String grupoPermitirNC[] = {"NO PERMITIR","PERMITIR"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmb_grupo_PermitirNC = new JComboBox(grupoPermitirNC);
	
	String status[] = {"VIGENTE","CANCELADO"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmb_status = new JComboBox(status);
	
	JButton btnBuscar = new JButton("Buscar",new ImageIcon("imagen/buscar.png"));
	JButton btnSalir = new JButton("Salir",new ImageIcon("imagen/salir16.png"));
	JButton btnDeshacer = new JButton("Deshacer",new ImageIcon("imagen/deshacer16.png"));
	JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
	JButton btnEditar = new JButton("Editar",new ImageIcon("imagen/editara.png"));
	JButton btnNuevo = new JButton("Nuevo",new ImageIcon("imagen/Nuevo.png"));
	
	 public static DefaultTableModel modelo = new DefaultTableModel(null,new String[]{"Folio Est. ", "Establecimiento", "Abreviatura","Serie","Grupo Cheque","Estatus","Grupo Corte","Permitir NC","Domicilio","Razon social","RFC","Telefono"}){
	            @SuppressWarnings("rawtypes")
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
	                       java.lang.Object.class  
	        };
	            @SuppressWarnings({ "rawtypes", "unchecked" })
	            public Class getColumnClass(int columnIndex) {
	                    return types[columnIndex];
	            }
	        public boolean isCellEditable(int fila, int columna){
	                    switch(columna){
	                            case 0  : return false; 
	                            case 1  : return false; 
	                            case 2  : return false; 
	                            case 3  : return false; 
	                            case 4  : return false; 
	                            case 5  : return false; 
	                            case 6  : return false; 
	                            case 7  : return false; 
	                            case 8  : return false; 
	                            case 9  : return false; 
	                            case 10 : return false; 
	                            case 11 : return false; 
	                    }
	                     return false;
	             }
	    };
		JTable tabla = new JTable(modelo);
		JScrollPane scrollAsignado = new JScrollPane(tabla);
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cat_Establecimiento(){
		
		
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/folder-home-home-icone-5663-32.png"));
			panel.setBorder(BorderFactory.createTitledBorder("Establecimiento"));
			
			this.setTitle("Establecimientos");
			
			trsfiltro = new TableRowSorter(modelo); 
			tabla.setRowSorter(trsfiltro);
			txtFolioFiltro.setToolTipText("Filtro Por Folio de Establecimiento");
			txtUnidadFiltro.setToolTipText("Filtro Por Nombre de Establecimiento");
			
			int x = 45, y=30, ancho=100;
			
			
			panel.add(new JLabel("Folio:")).setBounds(x-25,y-15,ancho,20);
			panel.add(txtFolio).setBounds(90,y-15,ancho+70,20);
			panel.add(btnBuscar).setBounds(x+(ancho*2)+30,y-15,100,20);
			
			
			panel.add(new JLabel("Nombre:")).setBounds(x-25,y+=15,80,20);
			panel.add(txtEstablecimiento).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("Abreviatura:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(txtAbreviatura).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("Serie:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(txtSerie).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("Grupo Cheq:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(cmb_grupo_cheque).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("Grupo Corte:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(cmb_grupo_corte).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("Permitir NC:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(cmb_grupo_PermitirNC).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("Estatus:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(cmb_status).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("Domicilio:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(txtDomicilio).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("Razon Social:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(txtRazonSocial).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("RFC:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(txtRFC).setBounds(90,y,ancho+70,20);
			
			panel.add(new JLabel("Telefono:")).setBounds(x-25,y+=30,ancho,20);
			panel.add(txtTelefono).setBounds(90,y,ancho+70,20);
			
			panel.add(btnNuevo).setBounds(x+(ancho*2)+30,y-=120,100,20);
			panel.add(btnEditar).setBounds(x+(ancho*2)+30,y+=30,100,20);
			panel.add(btnGuardar).setBounds(x+ancho*2+30,y+=30,100,20);
			
			
			panel.add(btnDeshacer).setBounds(x-25,y+=90,100,20);
			panel.add(btnSalir).setBounds(x+115,y,100,20);
			

			
			panel.add(txtFolioFiltro).setBounds((x*2)+(ancho*3)-5,15,58,20);
			panel.add(txtUnidadFiltro).setBounds((x*2)+(ancho*3)+53,15,240,20);
			
			panel.add(getPanelTabla()).setBounds((x*2)+(ancho*3)-5,35,623,355);
			
			
			txtEstablecimiento.setEditable(false);
			txtAbreviatura.setEditable(false);
			txtSerie.setEditable(false);
			
			cmb_status.setEnabled(false);
			cmb_grupo_cheque.setEnabled(false);
			cmb_grupo_corte.setEnabled(false);
			cmb_grupo_PermitirNC.setEnabled(false);
			
			txtDomicilio.setEditable(false);
			txtRazonSocial.setEditable(false);
			txtRFC.setEditable(false);
			txtTelefono.setEditable(false);
			
			btnEditar.setEnabled(false);
			
			txtFolio.addKeyListener(buscar_action);
			
			btnGuardar.addActionListener(guardar);
			btnSalir.addActionListener(salir);
			btnBuscar.addActionListener(buscar);
			btnDeshacer.addActionListener(deshacer);
			btnNuevo.addActionListener(nuevo);
			btnEditar.addActionListener(editar);
			
			txtFolioFiltro.addKeyListener(opFiltroFolio);
			txtUnidadFiltro.addKeyListener(opFiltrounidad);
			
			txtEstablecimiento.addKeyListener(enterpasaraAbreviatura);
			txtAbreviatura.addKeyListener(enterpasaraunidad);
			
			agregar_de_tabla(tabla);
			cont.add(panel);
			this.setSize(1024,430);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
              ///asigna el foco al filtro
						 this.addWindowListener(new WindowAdapter() {
				                public void windowOpened( WindowEvent e ){
				                	txtUnidadFiltro.requestFocus();
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
	
	private JScrollPane getPanelTabla()	{	
		
			tabla.getTableHeader().setReorderingAllowed(false) ;
			tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
		    this.tabla.getColumnModel().getColumn(0).setMinWidth(58);
		    this.tabla.getColumnModel().getColumn(0).setMaxWidth(58);
		    this.tabla.getColumnModel().getColumn(1).setMinWidth(240);
		    this.tabla.getColumnModel().getColumn(1).setMaxWidth(800);
		    this.tabla.getColumnModel().getColumn(2).setMinWidth(70);
		    this.tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		    this.tabla.getColumnModel().getColumn(3).setMinWidth(60);
		    this.tabla.getColumnModel().getColumn(3).setMaxWidth(100);
		    this.tabla.getColumnModel().getColumn(4).setMinWidth(80);
		    this.tabla.getColumnModel().getColumn(4).setMaxWidth(100);
		    this.tabla.getColumnModel().getColumn(5).setMinWidth(60);
		    this.tabla.getColumnModel().getColumn(5).setMaxWidth(100);
		    
		    this.tabla.getColumnModel().getColumn(6).setMinWidth(100);
		    this.tabla.getColumnModel().getColumn(6).setMaxWidth(150);
		    this.tabla.getColumnModel().getColumn(7).setMinWidth(80);
		    this.tabla.getColumnModel().getColumn(7).setMaxWidth(80);
		    
		    this.tabla.getColumnModel().getColumn(8).setMinWidth(80);
		    this.tabla.getColumnModel().getColumn(8).setMaxWidth(80);
		    
		    this.tabla.getColumnModel().getColumn(9).setMinWidth(80);
		    this.tabla.getColumnModel().getColumn(9).setMaxWidth(80);
		    
		    this.tabla.getColumnModel().getColumn(10).setMinWidth(80);
		    this.tabla.getColumnModel().getColumn(10).setMaxWidth(80);
		    
		    this.tabla.getColumnModel().getColumn(11).setMinWidth(80);
		    this.tabla.getColumnModel().getColumn(11).setMaxWidth(80);
						    
							for(int i=0; i<tabla.getColumnCount(); i++){
								this.tabla.getColumnModel().getColumn(i).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12));
							}
							
							refrestabla();
					 JScrollPane scrol = new JScrollPane(tabla);
				    return scrol; 
	    
	    }
	private void refrestabla(){

		while(tabla.getRowCount()>0){ modelo.removeRow(0);}
		
		Statement s;
		ResultSet rs;
		try {
			Connexion con = new Connexion();
			s = con.conexion().createStatement();
			rs = s.executeQuery("select folio as folio_de_establecimiento "+
								", nombre as establecimiento "+
								", abreviatura "+
								", serie "+
								", case when grupo_para_cheque='0'  "+
								"		then (select 'Sin Grupo')  "+
								"	   when grupo_para_cheque='1'  "+
								"		then (select 'SUPER') "+
								"	   when grupo_para_cheque='2' "+ 
								"		then (select 'FERRE Y REFA') "+
								"	   when grupo_para_cheque='3'  "+
								"		then (select 'IZACEL')  "+
								"	END as grupo_para_cheque "+
								", case when tb_establecimiento.status='1'  "+
								"		then (select 'VIGENTE')  "+
								"	   when tb_establecimiento.status=0  "+
								"		then (select 'CANCELADO')  "+
								"	end as estatus  "+
								" , isnull(tb_grupos_para_cortes.grupo_para_cortes,'SELECCIONE UNA OPCION') as grupo_para_cortes"+
								", case when permitir_nc=0  "+
								"		then (select 'NO PERMITIR')  "+
								"	   ELSE (select 'PERMITIR')  "+
								"	end as permitir_nc,  " +
								" domicilio, " +
								" razon_social, " +
								" rfc, " +
								" telefono "+
						"from tb_establecimiento  " +
						"left outer join tb_grupos_para_cortes on tb_grupos_para_cortes.folio_grupo_para_cortes=tb_establecimiento.folio_grupo_para_cortes "+
						"order by nombre asc ");
			
			while (rs.next())
			{ 
			   String [] fila = new String[12];
			   fila[0] = rs.getString(1).trim();
			   fila[1] = rs.getString(2).trim();
			   fila[2] = rs.getString(3).trim(); 
			   fila[3] = rs.getString(4).trim(); 
			   fila[4] = rs.getString(5).trim(); 
			   fila[5] = rs.getString(6).trim(); 
			   fila[6] = rs.getString(7).trim(); 
			   fila[7] = rs.getString(8).trim();
			   fila[8] = rs.getString(9).trim();
			   fila[9] = rs.getString(10).trim();
			   fila[10] = rs.getString(11).trim();
			   fila[11] = rs.getString(12).trim();
			   modelo.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en Cat_Establecimientos en la funcion refrestabla  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
		}
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
	
	KeyListener opFiltrounidad = new KeyListener(){
		@SuppressWarnings("unchecked")
		public void keyReleased(KeyEvent arg0) {
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtUnidadFiltro.getText().toUpperCase().trim(), 1));
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
	
	KeyListener enterpasaraunidad = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				txtEstablecimiento.requestFocus();
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
	
	ActionListener salir = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	};
	
	
	ActionListener deshacer = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			txtFolio.setEditable(true);
			txtFolio.requestFocus();
			btnNuevo.setEnabled(true);
			btnEditar.setEnabled(true);
			
			txtFolio.setText("");
			txtEstablecimiento.setText("");
			txtAbreviatura.setText("");
			txtSerie.setText("");
			
			txtDomicilio.setText("");
			txtRazonSocial.setText("");
			txtRFC.setText("");
			txtTelefono.setText("");
			
			cmb_grupo_cheque.setSelectedIndex(0);
			cmb_status.setSelectedIndex(1);
			
			cmb_grupo_corte.setSelectedIndex(0);
			cmb_grupo_PermitirNC.setSelectedIndex(0);
			
			txtEstablecimiento.setEditable(false);
			txtAbreviatura.setEditable(false);
			txtSerie.setEditable(false);
			
			txtDomicilio.setEditable(false);
			txtRazonSocial.setEditable(false);
			txtRFC.setEditable(false);
			txtTelefono.setEditable(false);
			
			cmb_grupo_cheque.setEnabled(false);
			cmb_grupo_corte.setEnabled(false);
			cmb_grupo_PermitirNC.setEnabled(false);
			cmb_status.setEnabled(false);
		}
	};
	
	ActionListener editar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "No hay registro que Editar","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				txtFolio.setEditable(false);
				txtEstablecimiento.setEditable(true);
				txtAbreviatura.setEditable(true);
				txtSerie.setEditable(true);
				
				txtDomicilio.setEditable(true);
				txtRazonSocial.setEditable(true);
				txtRFC.setEditable(true);
				txtTelefono.setEditable(true);
				
				cmb_grupo_cheque.setEnabled(true);
				cmb_grupo_corte.setEnabled(true);
				cmb_grupo_PermitirNC.setEnabled(true);
				cmb_status.setEnabled(true);
				
				btnEditar.setEnabled(false);
				btnNuevo.setEnabled(true);
				btnEditar.setEnabled(false);
				btnGuardar.setEnabled(true);
				txtEstablecimiento.requestFocus(true);
			}
		}
	};
	
	ActionListener buscar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(txtFolio.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Ingrese el No. de Folio","Error",JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				try {
					Obj_Establecimiento Establecimiento = new Obj_Establecimiento().buscar(Integer.parseInt(txtFolio.getText()));
					if(Establecimiento.getFolio() != 0){
						
						txtFolio.setText(Establecimiento.getFolio()+"");
						txtEstablecimiento.setText(Establecimiento.getEstablecimiento()+"");
						txtAbreviatura.setText(Establecimiento.getAbreviatura()+"");
						txtSerie.setText(Establecimiento.getSerie()+"");
						
						txtDomicilio.setText(Establecimiento.getDomicilio());
						txtRazonSocial.setText(Establecimiento.getRazon_social());
						txtRFC.setText(Establecimiento.getRfc());
						txtTelefono.setText(Establecimiento.getTelefono());
						
						cmb_grupo_cheque.setSelectedIndex(Establecimiento.getGrupo_cheque());
						cmb_grupo_corte.setSelectedIndex(Establecimiento.getGrupo_cheque());
						cmb_grupo_PermitirNC.setSelectedIndex(Establecimiento.getGrupo_cheque());
						cmb_status.setSelectedIndex(Establecimiento.getStatus());
						
						btnNuevo.setEnabled(true);
						btnEditar.setEnabled(true);
						
						txtFolio.setEditable(false);
						txtEstablecimiento.setEditable(false);
						txtAbreviatura.setEditable(false);
						txtSerie.setEditable(false);
						
						txtDomicilio.setEditable(false);
						txtRazonSocial.setEditable(false);
						txtRFC.setEditable(false);
						txtTelefono.setEditable(false);
						
						cmb_grupo_cheque.setEnabled(false);
						cmb_grupo_corte.setEnabled(false);
						cmb_grupo_PermitirNC.setEnabled(false);
						cmb_status.setEnabled(false);
						
						txtFolio.requestFocus();
					} else{
						JOptionPane.showMessageDialog(null, "El Registro no existe","Error",JOptionPane.WARNING_MESSAGE);
						return;
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} 			
			}
		}
	};
	
	private void agregar_de_tabla(final JTable tbl) {
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	if(e.getClickCount()==1){
	        		int fila = tabla.getSelectedRow();
	        		Object id = tabla.getValueAt(fila,0).toString().substring(0,tabla.getValueAt(fila,0).toString().length());
	        
						txtFolio.setText(id+"");
						txtEstablecimiento.setText(tabla.getValueAt(fila,1).toString().substring(0,tabla.getValueAt(fila,1).toString().length()));
						txtAbreviatura.setText(tabla.getValueAt(fila,2).toString().substring(0,tabla.getValueAt(fila,2).toString().length()));
						txtSerie.setText(tabla.getValueAt(fila,3).toString().substring(0,tabla.getValueAt(fila,3).toString().length()));
						cmb_grupo_cheque.setSelectedItem(tabla.getValueAt(fila,4).toString().substring(0,tabla.getValueAt(fila,4).toString().length()));
						cmb_status.setSelectedItem(tabla.getValueAt(fila,5).toString().substring(0,tabla.getValueAt(fila,5).toString().length()));
						cmb_grupo_corte.setSelectedItem(tabla.getValueAt(fila,6).toString().substring(0,tabla.getValueAt(fila,6).toString().length()));
						cmb_grupo_PermitirNC.setSelectedItem(tabla.getValueAt(fila, 7).toString().substring(0, tabla.getValueAt(fila, 7).toString().length()));
						
						txtDomicilio.setText(tabla.getValueAt(fila,8).toString().substring(0,tabla.getValueAt(fila,8).toString().length()));
						txtRazonSocial.setText(tabla.getValueAt(fila,9).toString().substring(0,tabla.getValueAt(fila,9).toString().length()));
						txtRFC.setText(tabla.getValueAt(fila,10).toString().substring(0,tabla.getValueAt(fila,10).toString().length()));
						txtTelefono.setText(tabla.getValueAt(fila,11).toString().substring(0,tabla.getValueAt(fila,11).toString().length()));
						
						txtFolio.setEditable(false);
						txtEstablecimiento.setEditable(false);
						txtAbreviatura.setEditable(false);
						txtSerie.setEditable(false);
						
						txtDomicilio.setEditable(false);
						txtRazonSocial.setEditable(false);
						txtRFC.setEditable(false);
						txtTelefono.setEditable(false);
						
						cmb_grupo_cheque.setEnabled(false);
						cmb_grupo_corte.setEnabled(false);
						cmb_grupo_PermitirNC.setEnabled(false);
						cmb_status.setEnabled(false);
						
						btnEditar.setEnabled(true);
						
						txtEstablecimiento.requestFocus();
						 
	        	}
	        }
        });
    }
	
	ActionListener nuevo = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			 busqueda_proximo_folio();
			
			if(foliosiguiente != 0){
				btnDeshacer.doClick();
				btnGuardar.setEnabled(true);
				
				txtEstablecimiento.setEditable(true);
				txtAbreviatura.setEditable(true);
				txtSerie.setEditable(true);
				
				txtDomicilio.setEditable(true);
				txtRazonSocial.setEditable(true);
				txtRFC.setEditable(true);
				txtTelefono.setEditable(true);
				
				txtFolio.setText( busqueda_proximo_folio()+"");
				txtFolio.setEditable(false);
				txtEstablecimiento.requestFocus();
				btnEditar.setEnabled(false);
				
				cmb_grupo_cheque.setSelectedIndex(0);
				cmb_grupo_corte.setSelectedIndex(0);
				cmb_grupo_PermitirNC.setSelectedIndex(0);
				cmb_status.setSelectedIndex(1);
				
                cmb_grupo_cheque.setEnabled(true);
                cmb_grupo_corte.setEnabled(true);
                cmb_grupo_PermitirNC.setEnabled(true);
				cmb_status.setEnabled(true);
			}else{
				btnDeshacer.doClick();
				btnGuardar.setEnabled(true);
				
				txtEstablecimiento.setEditable(true);
				txtAbreviatura.setEditable(true);
				txtSerie.setEditable(true);
				
				txtDomicilio.setEditable(true);
				txtRazonSocial.setEditable(true);
				txtRFC.setEditable(true);
				txtTelefono.setEditable(true);
				
				txtFolio.setText(1+"");
				txtFolio.setEditable(false);
				txtEstablecimiento.requestFocus();
				btnEditar.setEnabled(false);
				
				cmb_grupo_cheque.setSelectedIndex(0);
				cmb_grupo_corte.setSelectedIndex(0);
				cmb_grupo_PermitirNC.setSelectedIndex(0);
				cmb_status.setSelectedIndex(1);
				
                cmb_grupo_cheque.setEnabled(true);
                cmb_grupo_corte.setEnabled(true);
                cmb_grupo_PermitirNC.setEnabled(true);
				cmb_status.setEnabled(true);
			}
		}
	};
	
	ActionListener guardar = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
				try {
					if(validaCampos()!="") {
						JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n "+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
						return;
							} else{
												Obj_Establecimiento Establecimiento = new Obj_Establecimiento().buscar(Integer.parseInt(txtFolio.getText()));
												if(Establecimiento.getFolio() == Integer.parseInt(txtFolio.getText())){
													if(JOptionPane.showConfirmDialog(null, "El registro ya existe, ¿desea cambiarlo?") == 0){
														if(validaCampos()!="") {
															JOptionPane.showMessageDialog(null, "los siguientes campos son requeridos:\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
															return;
														}else{
															Establecimiento.setEstablecimiento(txtEstablecimiento.getText().toLowerCase().toString());
															Establecimiento.setAbreviatura(txtAbreviatura.getText().toLowerCase().toString());
															Establecimiento.setSerie(txtSerie.getText().toLowerCase().toString());
							  							    Establecimiento.setGrupo_cheque(cmb_grupo_cheque.getSelectedIndex());
							  							    Establecimiento.setGrupo_cortes(cmb_grupo_corte.getSelectedIndex());
							  							    Establecimiento.setGrupo_permitir_nc(cmb_grupo_PermitirNC.getSelectedIndex());
							  							    
							  							    Establecimiento.setDomicilio(txtDomicilio.getText().toLowerCase().toString());
								  							Establecimiento.setRazon_social(txtRazonSocial.getText().toLowerCase().toString());
								  							Establecimiento.setRfc(txtRFC.getText().toLowerCase().toString());
								  							Establecimiento.setTelefono(txtTelefono.getText().toLowerCase().toString());
							  							
															switch(cmb_status.getSelectedIndex()){
																		case 0: Establecimiento.setStatus(1); break;
																		case 1: Establecimiento.setStatus(0); break;	}
																
																			if(Establecimiento.actualizar(Integer.parseInt(txtFolio.getText()))){
																						refrestabla();
																JOptionPane.showMessageDialog(null,"El registró se actualizó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
																btnDeshacer.doClick();
																txtFolio.setEditable(true);
																txtFolio.requestFocus();
																return;
															}else{
																JOptionPane.showMessageDialog(null, "El registro no se actualizó", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
																return;
															}
														}
													}else{
														return;
													}
												}else{
													
													Obj_Establecimiento NombreEstablecimiento = new Obj_Establecimiento().buscar_existe_nombre_establecimiento(txtEstablecimiento.getText().toString().toUpperCase().trim());
													if(NombreEstablecimiento.getEstablecimiento().trim().equals(txtEstablecimiento.getText().toString().toUpperCase().trim())){
														JOptionPane.showMessageDialog(null, ">>El Nombre de Este Establecimiento<< Ya Existe con el folio: "+NombreEstablecimiento.getFolio() +"\n      No se Pueden Guardar Nombres Duplicados" , "Error al guardar registro",  JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
														return;
													 }else{
														    Obj_Establecimiento AbreviaturaEstablecimiento = new Obj_Establecimiento().buscar_existe_abreviatura_establecimiento(txtAbreviatura.getText().toString().toUpperCase().trim());
															System.out.println(AbreviaturaEstablecimiento.getAbreviatura().trim());
															System.out.println(txtAbreviatura.getText().toString().toUpperCase().trim());
														    if(AbreviaturaEstablecimiento.getAbreviatura().trim().equals(txtAbreviatura.getText().toString().toUpperCase().trim())){
																JOptionPane.showMessageDialog(null, ">>La Abreviatura de Este Establecimiento<< Ya Existe en el folio: "+AbreviaturaEstablecimiento.getFolio() +"\n      No se Pueden Guardar Abreviaturas Duplicadas" , "Error al guardar registro",  JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
																return;
															 }else{
																    Obj_Establecimiento SerieEstablecimiento = new Obj_Establecimiento().buscar_existe_serie_establecimiento(txtSerie.getText().toString().toUpperCase().trim());
																	if(SerieEstablecimiento.getSerie().trim().equals(txtSerie.getText().toString().toUpperCase().trim())){
																		JOptionPane.showMessageDialog(null, ">>La Serie de Este Establecimiento<< Ya Existe en el folio: "+SerieEstablecimiento.getFolio() +"\n      No se Pueden Guardar Series Duplicadas" , "Error al guardar registro",  JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
																		return;
																	 }else{
																 
																		Establecimiento.setEstablecimiento(txtEstablecimiento.getText().toLowerCase().toString().trim());
																		Establecimiento.setAbreviatura(txtAbreviatura.getText().toLowerCase().toString().trim());
																		Establecimiento.setSerie(txtSerie.getText().toLowerCase().toString().trim());
																		Establecimiento.setGrupo_cheque(cmb_grupo_cheque.getSelectedIndex());
																		Establecimiento.setGrupo_cortes(cmb_grupo_corte.getSelectedIndex());
																		Establecimiento.setGrupo_permitir_nc(cmb_grupo_PermitirNC.getSelectedIndex());
																		
										  							    Establecimiento.setDomicilio(txtDomicilio.getText().toLowerCase().toString());
											  							Establecimiento.setRazon_social(txtRazonSocial.getText().toLowerCase().toString());
											  							Establecimiento.setRfc(txtRFC.getText().toLowerCase().toString());
											  							Establecimiento.setTelefono(txtTelefono.getText().toLowerCase().toString());
																		
																		switch(cmb_status.getSelectedIndex()){
																					case 0: Establecimiento.setStatus(1); break;
																					case 1: Establecimiento.setStatus(0); break;	}
																			
																				if(Establecimiento.guardar()){
																					refrestabla();
																					
																					JOptionPane.showMessageDialog(null,"El registró se guardó de forma segura","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
																					btnDeshacer.doClick();
																					txtFolio.setEditable(true);
																					txtFolio.requestFocus();
																					return;
																					
																				}else{
																					JOptionPane.showMessageDialog(null, "El registro no se guardó", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
																					return;
																			}
																	 }					
												}
									    }
							      }
				               }
						 } catch (NumberFormatException e1) {
							e1.printStackTrace();
						} 				
							
		}
	};
	
	public int  busqueda_proximo_folio() {
		Connexion con = new Connexion();
		String query = "select max(folio)+1 as 'Maximo' from tb_establecimiento ";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				 foliosiguiente =(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			JOptionPane.showMessageDialog(null, "Error en Cat_Establecimiento  en la funcion busqueda_proximo_folio()"+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
			return foliosiguiente ;
		}
		finally{
			 if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error en Cat_Establecimiento  en la funcion busqueda_proximo_folio()"+e.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} }
		}
		return foliosiguiente;
			}

	
	private String validaCampos(){
		String error="";
		if(txtEstablecimiento.getText().equals("")) 		error+= "Nombre de Establecimiento\n";
		if(txtAbreviatura.getText().equals("")) 		error+= "Abreviatura\n";
		if(txtSerie.getText().equals("")) 		error+= "Serie del Establecimiento\n";
		if(cmb_grupo_cheque.getSelectedIndex()==0) 		error+= "Grupo Para Cheque\n";
		if(cmb_grupo_corte.getSelectedIndex()==0) 		error+= "Grupo Para Cortes\n";
		
		if(txtDomicilio.getText().equals("")) 		error+= "Domicilio\n";
		if(txtRazonSocial.getText().equals("")) 		error+= "Razon social\n";
		if(txtRFC.getText().equals("")) 		error+= "RFC\n";
		if(txtTelefono.getText().equals("")) 		error+= "Telefono\n";
		
		return error;
	}
	
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Establecimiento().setVisible(true);
		}catch(Exception e){	}
	}
	
}
