package Cat_Administracion_del_Sistema;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;


import Obj_Administracion_del_Sistema.Obj_CheckBoxNode;
import Obj_Administracion_del_Sistema.Obj_CheckBoxNodeEditor;
import Obj_Administracion_del_Sistema.Obj_CheckBoxNodeRenderer;
import Obj_Administracion_del_Sistema.Obj_MD5;
import Obj_Administracion_del_Sistema.Obj_NombreVector;
import Obj_Administracion_del_Sistema.Obj_SubMenus;
import Obj_Administracion_del_Sistema.Obj_Usuario;




@SuppressWarnings("serial")
public class Cat_Usuarios extends JFrame{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	// MENU PRICIPAL ADMINISTRACION DEL SISTEMA  (1)
	String[] Sub_Administracion_del_Sistema = new Obj_Administracion_del_Sistema.Obj_SubMenus().Relacion_de_SubMenus(1);
	Obj_CheckBoxNode Administracion_del_sistema[] = {
		new Obj_CheckBoxNode(Sub_Administracion_del_Sistema[0], false),
		new Obj_CheckBoxNode(Sub_Administracion_del_Sistema[1], false),
		new Obj_CheckBoxNode(Sub_Administracion_del_Sistema[2], false),
		new Obj_CheckBoxNode(Sub_Administracion_del_Sistema[3], false),
		new Obj_CheckBoxNode(Sub_Administracion_del_Sistema[4], false),
	};
	@SuppressWarnings("rawtypes")
	Vector Administracion_del_sistemaVector = new Obj_NombreVector("Administración Del Sistema", Administracion_del_sistema);
	
	// MENU PRINCIPAL AUDITORIA (2)
	String[] Sub_Auditoria = new Obj_SubMenus().Relacion_de_SubMenus(2);
	Obj_CheckBoxNode Auditoria[] = {
		new Obj_CheckBoxNode(Sub_Auditoria[0], false),
	    new Obj_CheckBoxNode(Sub_Auditoria[1], false),
	    new Obj_CheckBoxNode(Sub_Auditoria[2], false),
	    new Obj_CheckBoxNode(Sub_Auditoria[3], false),
	    new Obj_CheckBoxNode(Sub_Auditoria[4], false),
	    new Obj_CheckBoxNode(Sub_Auditoria[5], false),
	    new Obj_CheckBoxNode(Sub_Auditoria[6], false),
	};
	@SuppressWarnings("rawtypes")
	Vector AuditoriaVector = new Obj_NombreVector("Auditoria", Auditoria);
	
	// MENU PRINCIPAL CHECADOR (3)
	String[] Sub_Checador = new Obj_SubMenus().Relacion_de_SubMenus(3);
	Obj_CheckBoxNode Checador[] = {
		new Obj_CheckBoxNode(Sub_Checador[0], false),
	    new Obj_CheckBoxNode(Sub_Checador[1], false),
	    new Obj_CheckBoxNode(Sub_Checador[2], false),
	    new Obj_CheckBoxNode(Sub_Checador[3], false),
	    new Obj_CheckBoxNode(Sub_Checador[4], false),
	    new Obj_CheckBoxNode(Sub_Checador[5], false),
	    new Obj_CheckBoxNode(Sub_Checador[6], false),
	    new Obj_CheckBoxNode(Sub_Checador[7], false),
	    new Obj_CheckBoxNode(Sub_Checador[8], false),
	    new Obj_CheckBoxNode(Sub_Checador[9], false),
	    new Obj_CheckBoxNode(Sub_Checador[10], false),
	    new Obj_CheckBoxNode(Sub_Checador[11], false),
	    new Obj_CheckBoxNode(Sub_Checador[12], false),
	    new Obj_CheckBoxNode(Sub_Checador[13], false),
	    new Obj_CheckBoxNode(Sub_Checador[14], false),
	};
	@SuppressWarnings("rawtypes")
	Vector ChecadorVector = new Obj_NombreVector("Checador", Checador);
	
	// MENU PRINCIPAL CONTABILIDAD (4)
	String[] Sub_Contabilidad = new Obj_SubMenus().Relacion_de_SubMenus(4);
	Obj_CheckBoxNode Contabilidad[] = {
		new Obj_CheckBoxNode(Sub_Contabilidad[0], false),
		new Obj_CheckBoxNode(Sub_Contabilidad[1], false),
		new Obj_CheckBoxNode(Sub_Contabilidad[2], false),
		new Obj_CheckBoxNode(Sub_Contabilidad[3], false),
		new Obj_CheckBoxNode(Sub_Contabilidad[4], false),
		new Obj_CheckBoxNode(Sub_Contabilidad[5], false),
		new Obj_CheckBoxNode(Sub_Contabilidad[6], false),
		new Obj_CheckBoxNode(Sub_Contabilidad[7], false),
	};
	@SuppressWarnings("rawtypes")
	Vector ContabilidadVector = new Obj_NombreVector("Contabilidad", Contabilidad);
	
	// MENU PRINCIPAL EVALUACIONES  5
	String[] Sub_Evaluaciones = new Obj_SubMenus().Relacion_de_SubMenus(5);
	Obj_CheckBoxNode Evaluaciones[] = {
		new Obj_CheckBoxNode(Sub_Evaluaciones[0], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[1], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[2], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[3], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[4], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[5], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[6], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[7], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[8], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[9], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[10], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[11], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[12], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[13], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[14], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[15], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[16], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[17], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[18], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[19], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[20], false),
		new Obj_CheckBoxNode(Sub_Evaluaciones[21], false),
	};
	@SuppressWarnings("rawtypes")
	Vector EvaluacionesVector = new Obj_NombreVector("Evaluaciones", Evaluaciones);
	
	// MENU PRINCIPAL LISTA DE RAYA 6
	String[] Sub_Lista_de_Raya = new Obj_SubMenus().Relacion_de_SubMenus(6);
	Obj_CheckBoxNode Lista_de_Raya[] = {
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[0], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[1], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[2], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[3], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[4], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[5], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[6], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[7], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[8], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[9], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[10], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[11], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[12], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[13], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[14], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[15], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[16], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[17], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[18], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[19], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[20], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[21], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[22], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[23], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[24], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[25], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[26], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[27], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[28], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[29], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[30], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[31], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[32], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[33], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[34], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[35], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[36], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[37], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[38], false),
		new Obj_CheckBoxNode(Sub_Lista_de_Raya[39], false),
	};
	@SuppressWarnings("rawtypes")
	Vector Lista_de_RayaVector = new Obj_NombreVector("Lista De Raya", Lista_de_Raya);
		
	
	
	// MENU PRINCIPAL REPORTES ESPECIALES (7)
		String[] Sub_Reportes_Especiales = new Obj_SubMenus().Relacion_de_SubMenus(7);
		Obj_CheckBoxNode Reportes_Especiales[] = {
			new Obj_CheckBoxNode(Sub_Reportes_Especiales[0], false),
		};
		@SuppressWarnings("rawtypes")
		Vector ReportesEspecialesVector = new Obj_NombreVector("Reportes Especiales", Reportes_Especiales);
		
		
	// MENU PRINCIPAL COMPRAS (8)
			String[] Sub_Compras = new Obj_SubMenus().Relacion_de_SubMenus(8);
			Obj_CheckBoxNode Compras[] = {
				new Obj_CheckBoxNode(Sub_Compras[0], false),
				new Obj_CheckBoxNode(Sub_Compras[1], false),
				new Obj_CheckBoxNode(Sub_Compras[2], false),
			};
			@SuppressWarnings("rawtypes")
			Vector ComprasVector = new Obj_NombreVector("Compras", Compras);
			
	// MENU PRICIPAL PUNTO DE VENTA (9)
	String[] Sub_Punto_de_Venta = new Obj_Administracion_del_Sistema.Obj_SubMenus().Relacion_de_SubMenus(9);
	Obj_CheckBoxNode Punto_de_Venta[] = {
		new Obj_CheckBoxNode(Sub_Punto_de_Venta[0], false),
		new Obj_CheckBoxNode(Sub_Punto_de_Venta[1], false),
	};
	@SuppressWarnings("rawtypes")
	Vector Vector_Punto_De_Venta = new Obj_NombreVector("Punto De Venta", Punto_de_Venta);
		
		////ESTA PARTE SE ORDENA LA POSICION EN LA QUE SE QUIERE EL MENU PRINCIPAL  EN EL ARBOL
	Object rootNodos[] = { Administracion_del_sistemaVector, AuditoriaVector, ChecadorVector, ComprasVector, ContabilidadVector, EvaluacionesVector, 
			Lista_de_RayaVector,ReportesEspecialesVector,Vector_Punto_De_Venta};
	    
	@SuppressWarnings("rawtypes")
	Vector rootVector = new Obj_NombreVector("Permisos", rootNodos);
	 
	JTree tree = new JTree(rootVector);

	JScrollPane scrolltree = new JScrollPane(tree);
	
	Obj_CheckBoxNodeRenderer  renderer = new Obj_CheckBoxNodeRenderer ();	
	
	String[][] Matriz = new Obj_SubMenus().UsuarioMatriz();
	
	DefaultTableModel model = new DefaultTableModel(Matriz,
			new String[]{"Folio", "Nombre", "Usuario"}){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	JScrollPane scrolltable = new JScrollPane(tabla);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	TableRowSorter trsfiltro = new TableRowSorter(model); 
	
	JTextField txtFolioFiltro = new JTextField();
	JTextField txtNombre_CompletoFiltro = new JTextField();
	
    JTextField txtFolio = new JTextField();
	JTextField txtNombre_Completo = new JTextField();
	JPasswordField txtContrasena = new JPasswordField();
	JPasswordField txtContrasena1 = new JPasswordField();
    
	JButton btnFoto = new JButton();
	JButton btnUsuariovigente = new JButton(new ImageIcon("imagen/usuario-icono-vigente7340-64.png"));
	JButton btnNoEsUsuario = new JButton(new ImageIcon("imagen/usuario-icono-noes_usuario9131-64.png"));
	JButton btnNuevo  = new JButton(new ImageIcon("imagen/usuario-icono-editar8476-64.png"));
	JButton btnGuardar = new JButton("Guardar",new ImageIcon("imagen/Guardar.png"));
	
	String establecimiento[] = new Obj_SubMenus().Combo_Usuarios();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbempleado_usuario = new JComboBox(establecimiento);

	JButton btnDefault = new JButton("Contraseña default");
	
	@SuppressWarnings("unchecked")
	public Cat_Usuarios(){
		this.setTitle("Usuarios y Permisos");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Lock.png"));
		
		ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/Iconos/Un.jpg");
 		btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT)));
		
		tabla.setRowSorter(trsfiltro);  
		
		campo.setBorder(BorderFactory.createTitledBorder("Usuarios y Permisos"));
		
		campo.add(scrolltree).setBounds(10,50,350,350);
				
		campo.add(txtFolioFiltro).setBounds(370,20,68,20);
		campo.add(txtNombre_CompletoFiltro).setBounds(440,20,208,20);
		campo.add(btnDefault).setBounds(650,20,150,20);
		campo.add(scrolltable).setBounds(370,50,430,350);	
		
		tabla.getColumnModel().getColumn(0).setMaxWidth(70);
		tabla.getColumnModel().getColumn(0).setMinWidth(70);
		tabla.getColumnModel().getColumn(1).setMaxWidth(210);
		tabla.getColumnModel().getColumn(1).setMinWidth(210);
		
		int y = 415;
		campo.add(new JLabel("Folio:")).setBounds(120,y,90,20);
		campo.add(txtFolio).setBounds(170,y,100,20);

		campo.add(new JLabel("Clonar Permisos del Usuario:")).setBounds(570,y,200,20);
		campo.add(cmbempleado_usuario).setBounds(570, y+20, 200, 20);
		campo.add(new JLabel("Usuario:")).setBounds(120,y+=25,90,20);
		campo.add(txtNombre_Completo).setBounds(170,y,210,20);
		
		campo.add(btnGuardar).setBounds(280,y+=35,100,20);
		campo.add(btnFoto).setBounds(10,y-70,100,95);
		campo.add(btnNoEsUsuario).setBounds(390,415,64,64);
		campo.add(btnUsuariovigente).setBounds(390,415,64,64);
		campo.add(btnNuevo).setBounds(390,415,64,64);
		
		btnNuevo.setVisible(true);
		btnNoEsUsuario.setVisible(false);
	    btnUsuariovigente.setVisible(false);
		cont.add(campo);
		
		btnGuardar.addActionListener(opguardar);
		txtFolioFiltro.addKeyListener(opFiltroFolio);
		txtNombre_CompletoFiltro.addKeyListener(opFiltroNombre);
		tabla.addMouseListener(opMouse);
		
		tree.setCellRenderer(renderer);
		tree.setCellEditor(new Obj_CheckBoxNodeEditor(tree));
		tree.setEditable(true);
		
		txtFolio.setEditable(false);
		txtNombre_Completo.setEditable(false);
		
		btnDefault.setEnabled(false);
		
		btnDefault.addActionListener(contraseniaDefault);
	
		this.setSize(830,550);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	boolean usuario = false;
	ActionListener contraseniaDefault = new ActionListener(){
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e){
			
			
			if(!validaCampos().equals("")){
			    	JOptionPane.showMessageDialog(null,"Seleccione un Usuario","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
			    	return;
			}else{
					usuario = tabla.getValueAt(tabla.getSelectedRow(), 2).equals("")?false:true;
				
					if(usuario){
								if(JOptionPane.showConfirmDialog(null, "Se le asignara cotraseña default al usuario \n"+txtNombre_Completo.getText()+"\n¿desea continuar?") == 0){
									
									String nuevacontrasena = new Obj_MD5().cryptMD5("1234567890", "izagar").trim().toLowerCase();
									 new Obj_Usuario().CambiarContrasena(Integer.valueOf(txtFolio.getText()),nuevacontrasena);
									 
									 JOptionPane.showMessageDialog(null,"La contraseña se restauro correctamente","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
								     return;
								}
					}else{
								JOptionPane.showMessageDialog(null,"El empleado seleccionado no cuenta con permisos de usuario","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
						    	return;
					}
			}
		}
	};

			    	
	MouseAdapter opMouse = new MouseAdapter(){
	
		@SuppressWarnings("rawtypes")
		public void mouseClicked(MouseEvent arg0){
			if(arg0.getClickCount() == 1){
				int fila = tabla.getSelectedRow();
    			int folio_empleado = Integer.valueOf(tabla.getValueAt(fila, 0).toString());
    			Object Nombre_Completo = tabla.getValueAt(fila, 1);
    			
    			btnNoEsUsuario.setVisible(false);
    		    btnUsuariovigente.setVisible(false);
    		    
    		    btnDefault.setEnabled(true);
    		    
				txtFolio.setText(folio_empleado+"");
        		txtNombre_Completo.setText(Nombre_Completo.toString());
        		
               ///cargar foto del empleado///
        		new Obj_Usuario().BuscarUsuario(folio_empleado);
    	 		ImageIcon tmpIconAux = new ImageIcon(System.getProperty("user.dir")+"/tmp/tmp_usuario/usuariotmp.jpg");
    	 		btnFoto.setIcon(new ImageIcon(tmpIconAux.getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT)));
        		
				if(new Obj_Usuario().ExisteUsuario(folio_empleado) == true){
										
					txtFolio.setText(folio_empleado+"");
	        		txtNombre_Completo.setText(Nombre_Completo.toString());
	        		
	        		Obj_Usuario usuario = new Obj_Usuario().BuscarUsuario(folio_empleado);
	    	 		
	        		txtContrasena.setText(usuario.getContrasena());
	        		btnUsuariovigente.setVisible(true);
	        		
	        		Vector catalogo = new Obj_Usuario().returnPermisos(folio_empleado,1);
	        		for(int i = 0; i<Administracion_del_sistema.length; i++){
	        			Administracion_del_sistema[i].setSelected(Boolean.parseBoolean(catalogo.get(i).toString()));
	        		}
	        		tree.collapseRow(0);
	        		
	        		Vector auditoria = new Obj_Usuario().returnPermisos(folio_empleado, 2);
	        		for(int i=0; i <Auditoria.length; i ++){
	        			Auditoria[i].setSelected(Boolean.parseBoolean(auditoria.get(i).toString()));
	        		}
	        		tree.collapseRow(1);
	        		
	        		Vector checador = new Obj_Usuario().returnPermisos(folio_empleado, 3);
	        		for(int i = 0; i<Checador.length; i ++){
	        			Checador[i].setSelected(Boolean.parseBoolean(checador.get(i).toString()));
	        		}
	        		tree.collapseRow(2);
	        		
	        		Vector cuadrantes_alimentacion = new Obj_Usuario().returnPermisos(folio_empleado, 4);
	        		for(int i = 0; i<Contabilidad.length; i ++){
	        			Contabilidad[i].setSelected(Boolean.parseBoolean(cuadrantes_alimentacion.get(i).toString()));
	        		}
	        		tree.collapseRow(3);
	        		
	        		Vector evaluaciones = new Obj_Usuario().returnPermisos(folio_empleado, 5);
	        		for(int i = 0; i<Evaluaciones.length; i ++){
	        			Evaluaciones[i].setSelected(Boolean.parseBoolean(evaluaciones.get(i).toString()));
	        		}
	        		tree.collapseRow(4);
	        		
	        		Vector lista_de_Raya = new Obj_Usuario().returnPermisos(folio_empleado, 6);
	        		for(int i = 0; i<Lista_de_Raya.length; i ++){
	        			Lista_de_Raya[i].setSelected(Boolean.parseBoolean(lista_de_Raya.get(i).toString()));
	        		}
	        		tree.collapseRow(5);
	        		
	        		Vector reportes_especiales = new Obj_Usuario().returnPermisos(folio_empleado, 7);
	        		for(int i = 0; i<Reportes_Especiales.length; i ++){
	        			Reportes_Especiales[i].setSelected(Boolean.parseBoolean(reportes_especiales.get(i).toString()));
	        		}
	        		tree.collapseRow(6);
	        		
	        		Vector compras = new Obj_Usuario().returnPermisos(folio_empleado, 8);
	        		for(int i = 0; i<Compras.length; i ++){
	        			Compras[i].setSelected(Boolean.parseBoolean(compras.get(i).toString()));
	        		}
	        		tree.collapseRow(7);
	        		
	        		Vector punto_de_venta = new Obj_Usuario().returnPermisos(folio_empleado, 9);
	        		for(int i = 0; i<Punto_de_Venta.length; i ++){
	        			Punto_de_Venta[i].setSelected(Boolean.parseBoolean(punto_de_venta.get(i).toString()));
	        		}
	        		tree.collapseRow(8);
	        		
	        		
				}else{
					
					btnNoEsUsuario.setVisible(true);
					
	        		for(int i = 0; i<Administracion_del_sistema.length; i++){
	        			Administracion_del_sistema[i].setSelected(false);
	        		}
	        		tree.collapseRow(0);
	        		
	        		for(int i=0; i <Auditoria.length; i ++){
	        			Auditoria[i].setSelected(false);
	        		}
	        		tree.collapseRow(1);
	        		
	        		for(int i = 0; i<Checador.length; i ++){
	        			Checador[i].setSelected(false);
	        		}
	        		tree.collapseRow(2);
	        		
	        		for(int i = 0; i<Contabilidad.length; i ++){
	        			Contabilidad[i].setSelected(false);
	        		}
	        		tree.collapseRow(3);
	        		
	        		for(int i = 0; i<Evaluaciones.length; i ++){
	        			Evaluaciones[i].setSelected(false);
	        		}
	        		tree.collapseRow(4);
	        		
	        		for(int i = 0; i<Lista_de_Raya.length; i ++){
	        			Lista_de_Raya[i].setSelected(false);
	        		}
	        		for(int i = 0; i<Reportes_Especiales.length; i ++){
	        			Reportes_Especiales[i].setSelected(false);
	        		}
	        		tree.collapseRow(5);
	        		
	        		for(int i = 0; i<Compras.length; i ++){
	        			Compras[i].setSelected(false);
	        		}
	        		tree.collapseRow(6);
	        		
				}        		
			}
		}
	};
	
	ActionListener opguardar = new ActionListener(){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e){
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 0));
			trsfiltro.setRowFilter(RowFilter.regexFilter("", 1));
			if(validaCampos()!="") {
				JOptionPane.showMessageDialog(null, "Necesita Selecionar Un Empleado\n"+validaCampos(), "Error al guardar registro", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
				        return;
			                      } else{ Obj_Usuario usuario = new Obj_Usuario().BuscarUsuario(Integer.valueOf(txtFolio.getText()));
										  Vector subMenus = vectorComponentes(tree);
												if(new Obj_Usuario().ExisteUsuario(Integer.valueOf(txtFolio.getText())) == false){
													
													if(cmbempleado_usuario.getSelectedIndex()==0){
														usuario.setFolio(Integer.parseInt(txtFolio.getText()));
												    	usuario.guardarPermisos(subMenus);
												    	JOptionPane.showMessageDialog(null,"El usuario nuevo se guardó de forma segura con contraseña 1234567890","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
														dispose();
														new Cat_Usuarios().setVisible(true);
													
													}else{
														String empleado_de_clonar = cmbempleado_usuario.getSelectedItem().toString().toUpperCase();
														int folio_empleado = Integer.parseInt(txtFolio.getText());
														usuario.Clonar_permisos(folio_empleado, empleado_de_clonar);
														JOptionPane.showMessageDialog(null,"Al usuario nuevo se clonaron las opciones de forma segura con contraseña 1234567890","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
														dispose();
														new Cat_Usuarios().setVisible(true);
														}
													
													    
										   	} else{									        		
										          if(cmbempleado_usuario.getSelectedIndex()==0){
										        	  		  			if(JOptionPane.showConfirmDialog(null, "El usuario existe, ¿desea actualizarlo?") == 0){
																				usuario.actualizar(Integer.valueOf(txtFolio.getText()), subMenus);
																				JOptionPane.showMessageDialog(null,"El usuario se actualizó correctamente","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
																				dispose();
																				new Cat_Usuarios().setVisible(true);
										        	  		  			}
													                               	}else{
													                               		if(JOptionPane.showConfirmDialog(null, "El usuario existe, ¿desea actualizarlo?") == 0){ 
																							String empleado_de_clonar = cmbempleado_usuario.getSelectedItem().toString().toUpperCase();
																							int folio_empleado = Integer.parseInt(txtFolio.getText());
																							usuario.Clonar_permisos(folio_empleado, empleado_de_clonar);
																							JOptionPane.showMessageDialog(null,"Se Clonaron las Opciones del Usuario Selecionado","Aviso",JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//Exito.png"));
																							dispose();
																							new Cat_Usuarios().setVisible(true);
													                               		}
														     
																		  			
														
										          }
										          
		                 }
		             }
		      }
	};
	
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
			trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_CompletoFiltro.getText().toUpperCase().trim(), 1));
		}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}
		
	};
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector vectorComponentes(JTree tree) {
		Vector lista = new Vector();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)(tree.getModel().getRoot());
		Enumeration enumeration = root.depthFirstEnumeration();

		while (enumeration.hasMoreElements()){
			String input = enumeration.nextElement()+"", extracted;
			extracted = input.substring(input.indexOf('[')+1,input.length()-1);
			int indice = extracted.indexOf('/');        

			if(indice != -1){
				lista.add(extracted);
			}			
		}
		return lista;
	}

	private String validaCampos(){
		String error="";
		if(txtFolio.getText().equals("")) 			error+= "Folio\n";
		if(txtNombre_Completo.getText().equals("")) error+= "Nombre Completo\n";
		return error;
	}
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Usuarios().setVisible(true);
		}catch(Exception e){}
	}
}
