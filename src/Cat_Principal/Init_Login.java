package Cat_Principal;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
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
import java.util.ArrayList;
import java.util.Vector;



import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import Cat_Auditoria.Cat_Retiros_A_Cajeros;
import Cat_Checador.Cat_Checador;
import Cat_Checador.Cat_Solicitud_De_Empleados;
import Cat_Evaluaciones.Cat_Captura_De_Cuadrante_Por_Nivel_Jerarquico;
import Cat_Evaluaciones.Cat_Captura_Del_Cuadrante_Personal;
import Cat_Lista_de_Raya.Cat_Captura_De_Fuente_De_Sodas_De_Cajeras;
import Cat_Lista_de_Raya.Cat_Deducciones_Y_Percepciones_De_Lista_De_Raya;
import Cat_Lista_de_Raya.Cat_Depositos_A_Bancos;
import Cat_Lista_de_Raya.Cat_Diferencia_De_Cortes;
import Cat_Lista_de_Raya.Cat_Empleados;
import Cat_Lista_de_Raya.Cat_Lista_De_Comparacion_De_Fuente_De_Sodas;
import Cat_Lista_de_Raya.Cat_Prestamos;
import Cat_Lista_de_Raya.Cat_Revision_De_Lista_Raya;
import Cat_Lista_de_Raya.Cat_Traspaso_A_Cobro_De_Fuente_De_Sodas_AUXF;
import Cat_Lista_de_Raya.Cat_Traspaso_A_Cobro_De_Fuente_De_Sodas_DH;
import Conexiones_SQL.Connexion;
import Obj_Administracion_del_Sistema.Obj_MD5;
import Obj_Administracion_del_Sistema.Obj_Usuario;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;
import Obj_Principal.Obj_Menus;



@SuppressWarnings("serial")
public class Init_Login extends JFrame{

	
	public Container cont = getContentPane();
	public JLayeredPane panel = new JLayeredPane();
	
	/* BOTON DEPOSITOS A BANCO */
	JButton btnBanco= new JButton(new ImageIcon("imagen/banco.png"));
	JLabel lblBanco= new JLabel("Depositos");
	JLabel lblBanco2= new JLabel("Bancos");
	
	/* BOTON DEDUCCIONES Y PRECEPCIONES */
	JButton btnInasistencia= new JButton(new ImageIcon("imagen/percepciones_y_deducciones64.png"));
	JLabel lblInasistencia2= new JLabel("Deducciones y");
	JLabel lblInasistencia3= new JLabel("Percepciones");
	
	/* BOTON DIFERENCIA DE CORTES */
	JButton btnCaja= new JButton(new ImageIcon("imagen/caja2.png"));
	JLabel lblCaja2= new JLabel("Diferencia de");
	JLabel lblCaja3= new JLabel("Cortes");
	
	/* BOTON FUENTE DE SODAS DH*/
	JButton btnFsRH= new JButton(new ImageIcon("imagen/fsRH.png"));
	JLabel lblFsRH2= new JLabel("Fuente de Sodas");
	JLabel lblFsRH3= new JLabel("DH");
	
	/* BOTON FUENTE DE SODAS AUX FIN */
	JButton btnFsAux= new JButton(new ImageIcon("imagen/fsAux.png"));
	JLabel lblFsAux2= new JLabel("Fuente de Sodas");
	JLabel lblFsAux3= new JLabel("Auxiliar y Finanzas");
	
	/* BOTON RETIROS CAJERAS */
	JButton btnRetirosCajeras= new JButton(new ImageIcon("imagen/boveda-de-dinero-en-efectivo-de-seguridad-icono-6192-64.png"));
	JLabel lblPExtras1= new JLabel("Retiros A");
	JLabel lblPExtras2= new JLabel("Cajeras");
	
	/* BOTON PRESTAMOS */
	JButton btnPrestamo= new JButton(new ImageIcon("imagen/prestamo.png"));
	JLabel lblPrestamo2= new JLabel("Prestamos");
	
	/* BOTON ALTA EMPLEADOS */
	JButton btnAltaEmp= new JButton(new ImageIcon("imagen/altaEmp.png"));
	JLabel lblAltaEmp2= new JLabel("Alta");
	JLabel lblAltaEmp3= new JLabel("Empleados");
	
	/* BOTON SOLICITUDES */
	JButton btnSolicitudes= new JButton(new ImageIcon("imagen/Solicitud-64.png"));
	JLabel lblSolicitudes= new JLabel("Solicitudes");
	
	/* BOTON LISTA DE RAYA */
	JButton btnListaRaya= new JButton(new ImageIcon("imagen/listaR.png"));
	JLabel lblListaRaya2= new JLabel("Lista de");
	JLabel lblListaRaya3= new JLabel("Raya");
	
	/* BOTON LISTA DE FIRMAS */
	JButton btnListaFirma= new JButton(new ImageIcon("imagen/listaF.png"));
	JLabel lblListaFirma2= new JLabel("Lista de");
	JLabel lblListaFirma3= new JLabel("Firmas");
	
	/* BOTON LISTA DE COMPARACION DE FUENTE DE SODAS */
	JButton btnListaComparacion= new JButton(new ImageIcon("imagen/comparacion.png"));
	JLabel lblListaComparacion2= new JLabel("Lista de");
	JLabel lblListaComparacion3= new JLabel("Comparacion FS");
	
	/* BOTON CHECADOR */
	JButton btnChecador= new JButton(new ImageIcon("imagen/checador.png"));
	JLabel lblListaChecador2= new JLabel("Checador");
	
	/*BOTON SALIR DE SCOI*/
	JButton btnCerrar= new JButton(new ImageIcon("imagen/salir_Scoi-3964-64.png"));
	JLabel lblListaCerrar= new JLabel("Cerrar SCOI");
	
	
	/* BOTON CAPTURA DE CUADRANTE PERSONAL */
	JButton btnCuadrantepersonal= new JButton(new ImageIcon("imagen/cuadrante_personal-64.png"));
	JLabel lblCuadrantepersonal = new JLabel("Captura de");
	JLabel lblCuadrantepersonal2 = new JLabel("Cuadrante Personal");
	
	/* BOTON CAPTURA DE CUADRANTE EQUIPO */
    JButton btnCuadranteequipo = new JButton(new ImageIcon("imagen/cuadrante_equipo-64.png"));
	JLabel lblCuadranteequipo = new JLabel("Captura de");
	JLabel lblCuadranteequipo2 = new JLabel("Cuadrante De Equipo");
	
	/* BOTON CAPTURA DE FUENTE DE SODAS CAJERAS */
	JButton btnFuenteSodasCajeras= new JButton(new ImageIcon("imagen/captura_fuente_de_Sodas_64.png"));
	JLabel lblFuente_sodascajeras = new JLabel("Captura Fsodas");
	JLabel lblFuente_sodascajeras2 = new JLabel("por Cajeras");
	
	/* BOTONES,ETIQUETAS Y TEXTFIELDS DEL LOGIN*/
    JLabel lblfolio=new JLabel("Folio:");
    JLabel lblusuario=new JLabel("Usuario:");	
    JLabel lblcontrasena=new JLabel("Contraseña:");	

    JLabel lblcontrasena_Actual=new JLabel("Actual:");
    JLabel lblcontrasena_Nueva=new JLabel("Nueva:");	
    JLabel lblcontrasena_Confirmar=new JLabel("Confirmar:");	
	
	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio", 50, "Int");
	JTextField txtUsuario = new JTextField("");
	JPasswordField txtContrasena = new Componentes().textPassword(new JPasswordField(), "Contraseña", 50);
 	
	JPasswordField txtContrasenaActual = new Componentes().textPassword(new JPasswordField(), "Contraseña Actual", 50); 
	JPasswordField txtContrasenaNueva = new Componentes().textPassword(new JPasswordField(), "Contraseña Nueva", 50);
	JPasswordField txtContrasenaConfirmar = new Componentes().textPassword(new JPasswordField(), "Confirmar Contraseña", 50);
	
	JButton btnSalir = new JButton("Salir",new ImageIcon("imagen/logout-icone-6625-16.png"));
	JButton btnAceptar = new JButton("Entrar");
	JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
	JButton btnCambiarContrasena =new JButton ("Cambiar Contraseña", new ImageIcon("imagen/signo-kgpg-icono-4248-16.png"));
	
	JButton btnGuardarContrasena =new JButton ("Guardar Contraseña");
	JButton btnValidarContrasena =new JButton ("Validar Contraseña",new ImageIcon ("imagen/Key.png"));
	
	JLabel lblLogo = new JLabel(new ImageIcon("imagen/LogPrincipal.png"));
	
	
	
	// DECLARAMOS EL OBJETO RUNTIME PARA EJECUTAR APLICACIONES DE WINDOWS
	Runtime R = Runtime.getRuntime();
	
	public Init_Login(){
		int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
		int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
		
//      asigna el foco al JTextField deseado al arrancar la ventana
        this.addWindowListener(new WindowAdapter() {
                public void windowOpened( WindowEvent e ){
                txtFolio.requestFocus();
             }
        });
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/* MANEJO DE LA RESOLUCIONES */ 
		Resolucion(ancho, alto);
		
		
		// buscar usuario con F2
	    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	       KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "filtro");
	    
	    getRootPane().getActionMap().put("filtro", new AbstractAction(){
		        public void actionPerformed(ActionEvent e)
		        {
		        	new Cat_Seleccion_De_Usuario().setVisible(true);
		        }
	    });
		
		btnBuscar.addActionListener(opBuscar);
		txtFolio.addKeyListener(enterBuscar);
		txtContrasena.addKeyListener(enterIn);
		txtContrasenaActual.addKeyListener(entervalidar);

		txtContrasenaNueva.addKeyListener(entervalidar);
		txtContrasenaConfirmar.addKeyListener(entervalidar);
		
		deshabilitarCambiarContrasena();
		cargar_usuariotrue();
		
		btnCambiarContrasena.addActionListener(opCambiarContrasena);
		btnValidarContrasena.addActionListener(opValidarContrasena);
		btnGuardarContrasena.addActionListener(opGuardarContrasena);
		txtUsuario.setEditable(false);
		btnAceptar.setEnabled(false);
		btnBuscar.setEnabled(true);
		
		btnBanco.addActionListener(Opciones);
		btnInasistencia.addActionListener(Opciones);
		btnSolicitudes.addActionListener(Opciones);
		btnCaja.addActionListener(Opciones);
		btnFsRH.addActionListener(Opciones);
		btnFsAux.addActionListener(Opciones);
		btnRetirosCajeras.addActionListener(Opciones);
		btnPrestamo.addActionListener(Opciones);
		btnAltaEmp.addActionListener(Opciones);
		btnCuadrantepersonal.addActionListener(Opciones);
		btnCuadranteequipo.addActionListener(Opciones);
		btnListaRaya.addActionListener(Opciones);
		btnListaFirma.addActionListener(Opciones);
		btnFuenteSodasCajeras.addActionListener(Opciones);
		btnListaComparacion.addActionListener(Opciones);
		btnChecador.addActionListener(Opciones);
		btnCerrar.addActionListener(Opciones);
		btnCuadranteequipo.addActionListener(Opciones);
		
		btnBanco.setEnabled(false);
		btnInasistencia.setEnabled(false);
		btnCaja.setEnabled(false);
		btnFsRH.setEnabled(false);
		btnFsAux.setEnabled(false);
		btnRetirosCajeras.setEnabled(false);
		btnPrestamo.setEnabled(false);
		btnAltaEmp.setEnabled(false);
		btnSolicitudes.setEnabled(false);
		btnListaRaya.setEnabled(false);
		btnListaFirma.setEnabled(false);
		btnListaComparacion.setEnabled(false);
		btnCuadrantepersonal.setEnabled(false);
		btnCuadranteequipo.setEnabled(false);
		btnFuenteSodasCajeras.setEnabled(false);
		btnChecador.setEnabled(false);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	public void Resolucion(int ancho, int alto){
		
//		Cat_Reloj_Sincronizado_Servidor reloj =new Cat_Reloj_Sincronizado_Servidor();
		
		if(ancho >= 1280){
			
//			reloj.lblHora.setFont(new java.awt.Font("Algerian",0,70));
//			panel.add(reloj.lblHora).setBounds(1030,230,400,100);
			
			panel.add(lblLogo).setBounds(920,0,400,218);
			
			int   x = 30  ,y = 40, z = 65;
			int  zl = 120 ,w = 20;
					
			/* COLUMNA 1 *//////////////////////////////////////////////////////
			panel.add(btnFuenteSodasCajeras).setBounds   (x     ,y     ,z,z);
			panel.add(btnRetirosCajeras).setBounds       (x     ,y+=115,z,z);
			panel.add(btnFsAux).setBounds                (x     ,y+=115,z,z);	
			panel.add(btnFsRH).setBounds                 (x     ,y+=115,z,z);
			panel.add(btnInasistencia).setBounds         (x     ,y+=115,z,z);
			

			y = 60;
			panel.add(lblFuente_sodascajeras).setBounds  (x+=75 ,y     ,zl,w);
			panel.add(lblFuente_sodascajeras2).setBounds (x     ,y+10  ,zl,w);
			panel.add(lblPExtras1).setBounds             (x     ,y+=115,zl,w);
			panel.add(lblPExtras2).setBounds             (x     ,y+10  ,zl,w);
			panel.add(lblFsAux2).setBounds               (x     ,y+=115,zl,w);
			panel.add(lblFsAux3).setBounds               (x     ,y+10  ,zl,w);
			panel.add(lblFsRH2).setBounds                (x     ,y+=115,zl,w);
			panel.add(lblFsRH3).setBounds                (x     ,y+10  ,zl,w);
			panel.add(lblInasistencia2).setBounds        (x     ,y+=115,zl,w);
			panel.add(lblInasistencia3).setBounds        (x     ,y+10  ,zl,w);

			
			/* COLUMNA 2 *//////////////////////////////////////////////////////
			y=40;			
			panel.add(btnBanco).setBounds                (x+=140,y     ,z,z);
			panel.add(btnCaja).setBounds                 (x     ,y+=115,z,z);
			panel.add(btnPrestamo).setBounds             (x     ,y+=115,z,z);
			panel.add(btnListaComparacion).setBounds     (x     ,y+=115,z,z);
			panel.add(btnListaRaya).setBounds            (x     ,y+=115,z,z);
			
			y = 60;		
			panel.add(lblBanco).setBounds                (x+=75 ,y     ,zl,w);
			panel.add(lblBanco2).setBounds               (x     ,y+10  ,zl,w);
			panel.add(lblCaja2).setBounds                (x     ,y+=115,zl,w);
			panel.add(lblCaja3).setBounds                (x     ,y+10  ,zl,w);
			panel.add(lblPrestamo2).setBounds            (x     ,y+=115,zl,w);
			panel.add(lblListaComparacion2).setBounds    (x     ,y+=115,zl,w);
			panel.add(lblListaComparacion3).setBounds    (x     ,y+10  ,zl,w);
			panel.add(lblListaRaya2).setBounds           (x     ,y+=115,zl,w);
			panel.add(lblListaRaya3).setBounds           (x     ,y+10  ,zl,w);
			
			/* COLUMNA 3 *///////////////////////////////////////////////////////
			y=40;			
			panel.add(btnAltaEmp).setBounds              (x+=140, y    ,z,z);
			panel.add(btnSolicitudes).setBounds          (x     ,y+=115,z,z);
			panel.add(btnCuadrantepersonal).setBounds    (x     ,y+=115,z,z);
			panel.add(btnCuadranteequipo).setBounds      (x     ,y+=115,z,z);
			panel.add(btnChecador).setBounds             (x     ,y+=115,z,z);
			
			y = 60;	
			panel.add(lblAltaEmp3).setBounds             (x+=75 ,y     ,zl,w);
			panel.add(lblSolicitudes).setBounds          (x     ,y+=115,zl,w);
			panel.add(lblCuadrantepersonal).setBounds    (x     ,y+=115,zl,w);
			panel.add(lblCuadrantepersonal2).setBounds   (x     ,y+10  ,zl,w);
			panel.add(lblCuadranteequipo).setBounds      (x     ,y+=115,zl,w);
			panel.add(lblCuadranteequipo2).setBounds     (x     ,y+10  ,zl,w);
			panel.add(lblListaChecador2).setBounds       (x     ,y+=115,zl,w);

			/* COLUMNA 4 *///////////////////////////////////////////////////////
			y=40;
			panel.add(btnCerrar).setBounds               (x+=140,y     ,z,z);

			y = 60;
			panel.add(lblListaCerrar).setBounds          (x+=75 ,y     ,zl,w);
						
			/*LOGIN*////////////////////////////////////////////////////////////
			y=490;
			panel.add(lblfolio).setBounds                (x+=200,y     ,100,w);
			panel.add(lblcontrasena_Actual).setBounds    (x     ,y     ,100,w);
			panel.add(lblusuario).setBounds              (x     ,y+=30 ,100,w);
			panel.add(lblcontrasena_Nueva).setBounds     (x     ,y     ,100,w);
			panel.add(lblcontrasena).setBounds           (x     ,y+=30 ,100,w);
			panel.add(lblcontrasena_Confirmar).setBounds (x     ,y     ,100,w);
			
			
			y=490;
			panel.add(txtFolio).setBounds                (x+=60  ,y    ,220,w);
			panel.add(txtContrasenaActual).setBounds     (x     ,y     ,220,w);
			panel.add(txtUsuario).setBounds              (x     ,y+=30 ,220,w);
			panel.add(txtContrasenaNueva).setBounds      (x     ,y     ,220,w);
			panel.add(txtContrasena).setBounds           (x     ,y+=30 ,220,w);
			panel.add(txtContrasenaConfirmar).setBounds  (x     ,y     ,220,w);
			panel.add(btnCambiarContrasena).setBounds    (x     ,y+=30 ,150,w);
			panel.add(btnGuardarContrasena).setBounds    (x     ,y     ,150,w);
			panel.add(btnValidarContrasena).setBounds    (x     ,y     ,150,w);
			panel.add(btnSalir).setBounds                (x+=150,y     ,75 ,w);
			
			y=490;
			panel.add(btnBuscar).setBounds               (x+70  ,y     ,30,w);
					
			cont.add(panel);
			this.setSize(ancho,alto);
		}

		if(ancho == 1024){
			
			ImageIcon tmpIconAux = new ImageIcon("imagen/LogPrincipal.png");
			panel.add(new JLabel(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT)))).setBounds(690,0,400,218);
					
//			reloj.lblHora.setFont(new java.awt.Font("Algerian",0,60));
//			panel.add(reloj.lblHora).setBounds(813,200,400,100);
			
			int   x = 10  ,y = 10, z = 65;
			int  zl = 120 ,w = 20;
					
			/* COLUMNA 1 *//////////////////////////////////////////////////////
			panel.add(btnFuenteSodasCajeras).setBounds   (x     ,y     ,z,z);
			panel.add(btnRetirosCajeras).setBounds       (x     ,y+=115,z,z);
			panel.add(btnFsAux).setBounds                (x     ,y+=115,z,z);	
			panel.add(btnFsRH).setBounds                 (x     ,y+=115,z,z);
			panel.add(btnInasistencia).setBounds         (x     ,y+=115,z,z);
			

			y = 30;
			panel.add(lblFuente_sodascajeras).setBounds  (x+=75 ,y     ,zl,w);
			panel.add(lblFuente_sodascajeras2).setBounds (x     ,y+10  ,zl,w);
			panel.add(lblPExtras1).setBounds             (x     ,y+=115,zl,w);
			panel.add(lblPExtras2).setBounds             (x     ,y+10  ,zl,w);
			panel.add(lblFsAux2).setBounds               (x     ,y+=115,zl,w);
			panel.add(lblFsAux3).setBounds               (x     ,y+10  ,zl,w);
			panel.add(lblFsRH2).setBounds                (x     ,y+=115,zl,w);
			panel.add(lblFsRH3).setBounds                (x     ,y+10  ,zl,w);
			panel.add(lblInasistencia2).setBounds        (x     ,y+=115,zl,w);
			panel.add(lblInasistencia3).setBounds        (x     ,y+10  ,zl,w);

			
			/* COLUMNA 2 *//////////////////////////////////////////////////////
			y=10;			
			panel.add(btnBanco).setBounds                (x+=140,y     ,z,z);
			panel.add(btnCaja).setBounds                 (x     ,y+=115,z,z);
			panel.add(btnPrestamo).setBounds             (x     ,y+=115,z,z);
			panel.add(btnListaComparacion).setBounds     (x     ,y+=115,z,z);
			panel.add(btnListaRaya).setBounds            (x     ,y+=115,z,z);
			
			y = 30;		
			panel.add(lblBanco).setBounds                (x+=75 ,y     ,zl,w);
			panel.add(lblBanco2).setBounds               (x     ,y+10  ,zl,w);
			panel.add(lblCaja2).setBounds                (x     ,y+=115,zl,w);
			panel.add(lblCaja3).setBounds                (x     ,y+10  ,zl,w);
			panel.add(lblPrestamo2).setBounds            (x     ,y+=115,zl,w);
			panel.add(lblListaComparacion2).setBounds    (x     ,y+=115,zl,w);
			panel.add(lblListaComparacion3).setBounds    (x     ,y+10  ,zl,w);
			panel.add(lblListaRaya2).setBounds           (x     ,y+=115,zl,w);
			panel.add(lblListaRaya3).setBounds           (x     ,y+10  ,zl,w);
			
			/* COLUMNA 3 *///////////////////////////////////////////////////////
			y=10;			
			panel.add(btnAltaEmp).setBounds              (x+=140, y    ,z,z);
			panel.add(btnSolicitudes).setBounds          (x     ,y+=115,z,z);
			panel.add(btnCuadrantepersonal).setBounds    (x     ,y+=115,z,z);
			panel.add(btnCuadranteequipo).setBounds      (x     ,y+=115,z,z);
			panel.add(btnChecador).setBounds             (x     ,y+=115,z,z);
			
			y = 30;	
			panel.add(lblAltaEmp3).setBounds             (x+=75 ,y     ,zl,w);
			panel.add(lblSolicitudes).setBounds          (x     ,y+=115,zl,w);
			panel.add(lblCuadrantepersonal).setBounds    (x     ,y+=115,zl,w);
			panel.add(lblCuadrantepersonal2).setBounds   (x     ,y+10  ,zl,w);
			panel.add(lblCuadranteequipo).setBounds      (x     ,y+=115,zl,w);
			panel.add(lblCuadranteequipo2).setBounds     (x     ,y+10  ,zl,w);
			panel.add(lblListaChecador2).setBounds       (x     ,y+=115,zl,w);

			/* COLUMNA 4 *///////////////////////////////////////////////////////
			y=10;
			panel.add(btnCerrar).setBounds               (x+=140,y     ,z,z);

			y = 30;
			panel.add(lblListaCerrar).setBounds          (x+=75 ,y     ,zl,w);
						
			/*LOGIN*////////////////////////////////////////////////////////////
			y=400; x=650;
			
			panel.add(lblfolio).setBounds                (x     ,y     ,100,w);
			panel.add(lblcontrasena_Actual).setBounds    (x     ,y     ,100,w);
			panel.add(lblusuario).setBounds              (x     ,y+=30 ,100,w);
			panel.add(lblcontrasena_Nueva).setBounds     (x     ,y     ,100,w);
			panel.add(lblcontrasena).setBounds           (x     ,y+=30 ,100,w);
			panel.add(lblcontrasena_Confirmar).setBounds (x     ,y     ,100,w);
			
			y=400;
			panel.add(txtFolio).setBounds                (x+=60  ,y    ,220,w);
			panel.add(txtContrasenaActual).setBounds     (x     ,y     ,220,w);
			panel.add(txtUsuario).setBounds              (x     ,y+=30 ,220,w);
			panel.add(txtContrasenaNueva).setBounds      (x     ,y     ,220,w);
			panel.add(txtContrasena).setBounds           (x     ,y+=30 ,220,w);
			panel.add(txtContrasenaConfirmar).setBounds  (x     ,y     ,220,w);
			panel.add(btnCambiarContrasena).setBounds    (x     ,y+=30 ,150,w);
			panel.add(btnGuardarContrasena).setBounds    (x     ,y     ,150,w);
			panel.add(btnValidarContrasena).setBounds    (x     ,y     ,150,w);
			panel.add(btnSalir).setBounds                (x+=150,y     ,75 ,w);
			
			y=400;
			panel.add(btnBuscar).setBounds               (x+70  ,y     ,30 ,w);
			
			cont.add(panel);
			this.setSize(ancho,alto);
		}
		if(ancho == 800){
			ImageIcon tmpIconAux = new ImageIcon("imagen/LogPrincipal.png");
			panel.add(new JLabel(new ImageIcon(tmpIconAux.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT)))).setBounds(500,0,400,218);
			
//			reloj.lblHora.setFont(new java.awt.Font("Algerian",0,50));
//			panel.add(reloj.lblHora).setBounds(638,190,400,100);
			
//			getImage().getScaledInstance(230, 195, Image.SCALE_DEFAULT)
			int   x = 10  ,y = 10, z = 65;
			int  zl = 120 ,w = 20;
					
			/* COLUMNA 1 *//////////////////////////////////////////////////////
			panel.add(btnFuenteSodasCajeras).setBounds   (x     ,y     ,z,z);
			panel.add(btnRetirosCajeras).setBounds       (x     ,y+=90,z,z);
			panel.add(btnFsAux).setBounds                (x     ,y+=90,z,z);	
			panel.add(btnFsRH).setBounds                 (x     ,y+=90,z,z);
			panel.add(btnInasistencia).setBounds         (x     ,y+=90,z,z);


			y = 20;
			panel.add(lblFuente_sodascajeras).setBounds  (x+=65 ,y     ,zl,w);
			panel.add(lblFuente_sodascajeras2).setBounds (x     ,y+10  ,zl,w);
			panel.add(lblPExtras1).setBounds             (x     ,y+=90,zl,w);
			panel.add(lblPExtras2).setBounds             (x     ,y+10  ,zl,w);
			panel.add(lblFsAux2).setBounds               (x     ,y+=90,zl,w);
			panel.add(lblFsAux3).setBounds               (x     ,y+10  ,zl,w);
			panel.add(lblFsRH2).setBounds                (x     ,y+=90,zl,w);
			panel.add(lblFsRH3).setBounds                (x     ,y+10  ,zl,w);
			panel.add(lblInasistencia2).setBounds        (x     ,y+=90,zl,w);
			panel.add(lblInasistencia3).setBounds        (x     ,y+10  ,zl,w);

			
			/* COLUMNA 2 *//////////////////////////////////////////////////////
			y=10;			
			panel.add(btnBanco).setBounds                (x+=100,y     ,z,z);
			panel.add(btnCaja).setBounds                 (x     ,y+=90 ,z,z);
			panel.add(btnPrestamo).setBounds             (x     ,y+=90 ,z,z);
			panel.add(btnListaComparacion).setBounds     (x     ,y+=90 ,z,z);
			panel.add(btnListaRaya).setBounds            (x     ,y+=90 ,z,z);
			
			y = 20;		
			panel.add(lblBanco).setBounds                (x+=65 ,y     ,zl,w);
			panel.add(lblBanco2).setBounds               (x     ,y+10  ,zl,w);
			panel.add(lblCaja2).setBounds                (x     ,y+=90 ,zl,w);
			panel.add(lblCaja3).setBounds                (x     ,y+10  ,zl,w);
			panel.add(lblPrestamo2).setBounds            (x     ,y+=90 ,zl,w);
			panel.add(lblListaComparacion2).setBounds    (x     ,y+=90 ,zl,w);
			panel.add(lblListaComparacion3).setBounds    (x     ,y+10  ,zl,w);
			panel.add(lblListaRaya2).setBounds           (x     ,y+=90 ,zl,w);
			panel.add(lblListaRaya3).setBounds           (x     ,y+10  ,zl,w);
			
			/* COLUMNA 3 *///////////////////////////////////////////////////////
			y=10;			
			panel.add(btnAltaEmp).setBounds              (x+=100,y    ,z,z);
			panel.add(btnSolicitudes).setBounds          (x     ,y+=90,z,z);
			panel.add(btnCuadrantepersonal).setBounds    (x     ,y+=90,z,z);
			panel.add(btnCuadranteequipo).setBounds      (x     ,y+=90,z,z);
			panel.add(btnChecador).setBounds             (x     ,y+=90,z,z);
			
			y = 20;	
			panel.add(lblAltaEmp3).setBounds             (x+=65 ,y     ,zl,w);
			panel.add(lblSolicitudes).setBounds          (x     ,y+=90 ,zl,w);
			panel.add(lblCuadrantepersonal).setBounds    (x     ,y+=90 ,zl,w);
			panel.add(lblCuadrantepersonal2).setBounds   (x     ,y+10  ,zl,w);
			panel.add(lblCuadranteequipo).setBounds      (x     ,y+=90 ,zl,w);
			panel.add(lblCuadranteequipo2).setBounds     (x     ,y+10  ,zl,w);
			panel.add(lblListaChecador2).setBounds       (x     ,y+=90 ,zl,w);

			/* COLUMNA 4 *///////////////////////////////////////////////////////
			y=10;
			panel.add(btnCerrar).setBounds               (x+=100,y     ,z,z);

			y = 20;
			panel.add(lblListaCerrar).setBounds          (x+=65 ,y     ,zl,w);
						
			/*LOGIN*////////////////////////////////////////////////////////////
			y=350; x=500;
			
			panel.add(lblfolio).setBounds                (x     ,y     ,100,w);
			panel.add(lblcontrasena_Actual).setBounds    (x     ,y     ,100,w);
			panel.add(lblusuario).setBounds              (x     ,y+=30 ,100,w);
			panel.add(lblcontrasena_Nueva).setBounds     (x     ,y     ,100,w);
			panel.add(lblcontrasena).setBounds           (x     ,y+=30 ,100,w);
			panel.add(lblcontrasena_Confirmar).setBounds (x     ,y     ,100,w);
			
			y=350;
			panel.add(txtFolio).setBounds                (x+=60  ,y    ,220,w);
			panel.add(txtContrasenaActual).setBounds     (x     ,y     ,220,w);
			panel.add(txtUsuario).setBounds              (x     ,y+=30 ,220,w);
			panel.add(txtContrasenaNueva).setBounds      (x     ,y     ,220,w);
			panel.add(txtContrasena).setBounds           (x     ,y+=30 ,220,w);
			panel.add(txtContrasenaConfirmar).setBounds  (x     ,y     ,220,w);
			panel.add(btnCambiarContrasena).setBounds    (x     ,y+=30 ,150,w);
			panel.add(btnGuardarContrasena).setBounds    (x     ,y     ,150,w);
			panel.add(btnValidarContrasena).setBounds    (x     ,y     ,150,w);
			panel.add(btnSalir).setBounds                (x+=150,y     ,75 ,w);
			
			y=325;
			panel.add(btnBuscar).setBounds               (x+35  ,y     ,30 ,w);
			cont.add(panel);
			this.setSize(ancho,alto);
		}
	}
	
	KeyListener enterIn = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnAceptar.doClick();
			}
		}
	};
	
	KeyListener enterBuscar = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				btnBuscar.doClick();
			}
		}
	};
	
	KeyListener entervalidar = new KeyListener() {
		public void keyTyped(KeyEvent e){}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					
						if(e.getSource().equals(txtContrasenaActual)){
							btnValidarContrasena.doClick();
						}
						if(e.getSource().equals(txtContrasenaNueva)){
							txtContrasenaConfirmar.requestFocus();
						}
						if(e.getSource().equals(txtContrasenaConfirmar)){
							btnGuardarContrasena.doClick();
						}
				}
				
		}
	};
	

	ActionListener opBuscar = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			if(txtFolio.getText().length()!=0){
				Obj_Usuario user = new Obj_Usuario().buscar(Integer.parseInt(txtFolio.getText()));
				if(user.getFolio() != 0){
					txtFolio.setEditable(false);
					txtUsuario.setText(user.getNombre_completo());
					txtContrasena.requestFocus(true);
					btnAceptar.setEnabled(true);
					txtContrasena.setEnabled(true);
					
				}else{
					JOptionPane.showMessageDialog(null, "El usuario no existe","Aviso",JOptionPane.WARNING_MESSAGE);
					txtFolio.requestFocus(true);
					return;
				}
			}else{
				JOptionPane.showMessageDialog(null, "Ingrese el Folio del Usuario","Aviso",JOptionPane.WARNING_MESSAGE);
				txtFolio.requestFocus(true);
				new Cat_Seleccion_De_Usuario().setVisible(true);
				return;
			}
		}		
	};
	
	ActionListener opCambiarContrasena = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
                
			
			    txtContrasenaActual.setText("");
			    txtContrasenaNueva.setText("");
			    txtContrasenaConfirmar.setText("");
				txtContrasena.setVisible(false);
				txtFolio.setVisible(false);
				txtUsuario.setVisible(false);
				btnBuscar.setVisible(false);
				btnAceptar.setVisible(false);				
				btnCambiarContrasena.setVisible(false);
				lblfolio.setVisible(false);
				lblusuario.setVisible(false);
				lblcontrasena.setVisible(false);
							
				txtContrasenaActual.setVisible(true);
				txtContrasenaNueva.setVisible(true);
				txtContrasenaConfirmar.setVisible(true);
				
				txtContrasenaActual.setEnabled(true);
				txtContrasenaNueva.setEnabled(false);
				txtContrasenaConfirmar.setEnabled(false);

				btnValidarContrasena.setVisible(true);						
				txtContrasenaActual.requestFocus(true);					
				btnValidarContrasena.setEnabled(true);
				lblcontrasena_Actual.setVisible(true);
				lblcontrasena_Nueva.setVisible(true);	
				lblcontrasena_Confirmar.setVisible(true);
										return;
		}		
	};
	Obj_MD5 algoritmo = new Obj_MD5();
	
	ActionListener opValidarContrasena = new ActionListener(){
		@SuppressWarnings({ "deprecation", "static-access" })
		public void actionPerformed(ActionEvent arg0) {
			if(txtContrasenaActual.getText().length() != 0) {
			
			
			Obj_Usuario user = new Obj_Usuario().buscar(Integer.parseInt(txtFolio.getText()));
			if(!algoritmo.cryptMD5(txtContrasenaActual.getText(), "izagar").trim().toLowerCase().equals(user.getContrasena().trim().toLowerCase())){
				
			JOptionPane.showMessageDialog(null, "La contraseña no es válida...","Aviso",JOptionPane.WARNING_MESSAGE);
			
			}else{
			txtContrasenaActual.setEnabled(false);	
			txtContrasenaNueva.setEnabled(true);
			txtContrasenaConfirmar.setEnabled(true);
			txtContrasenaNueva.requestFocus(true);
			
			btnValidarContrasena.setVisible(false);
			btnGuardarContrasena.setVisible(true);			
			btnGuardarContrasena.setEnabled(true);
			}
		
			}else{
				JOptionPane.showMessageDialog(null, "La contraseña está vacía...","Aviso",JOptionPane.WARNING_MESSAGE);
				txtContrasenaActual.requestFocus(true);
				return;
			}
		}		
	};	
	
	ActionListener opGuardarContrasena = new ActionListener(){
				
		@SuppressWarnings({ "deprecation", "static-access", "unused" })
		public void actionPerformed(ActionEvent arg0) {
			if(txtContrasenaNueva.getText().trim().toLowerCase().equals(txtContrasenaConfirmar.getText().trim().toLowerCase())) {
			String nuevacontrasena = algoritmo.cryptMD5(txtContrasenaNueva.getText().trim().toLowerCase(), "izagar").trim().toLowerCase();
			boolean user = new Obj_Usuario().CambiarContrasena( Integer.valueOf(txtFolio.getText()),nuevacontrasena);
			System.out.println("valido"+nuevacontrasena);
			txtContrasenaActual.setText("");
			txtContrasenaNueva.setText("");
			txtContrasenaConfirmar.setText("");
			deshabilitarCambiarContrasena();
			cargar_usuariotrue();
			btnCambiarContrasena.setVisible(true);

			}else{
				JOptionPane.showMessageDialog(null, "Las contraseñas son diferentes...","Aviso",JOptionPane.WARNING_MESSAGE);
				txtContrasenaNueva.setText("");
				txtContrasenaConfirmar.setText("");
				txtContrasenaNueva.requestFocus(true);
				return;
			}
		}		
	};	
	
	 
		public void deshabilitarCambiarContrasena (){
	btnValidarContrasena.setVisible(false);
	btnCambiarContrasena.setVisible(false);
	txtContrasenaActual.setVisible(false);
	txtContrasenaNueva.setVisible(false);
	txtContrasenaConfirmar.setVisible(false);
	btnGuardarContrasena.setVisible(false);
	btnAceptar.setVisible(false);
	lblcontrasena_Actual.setVisible(false);
	lblcontrasena_Nueva.setVisible(false);
	lblcontrasena_Confirmar.setVisible(false);
		};
		
		public void cargar_usuariotrue(){
			txtContrasena.setVisible(true);
			txtContrasena.setEnabled(false);
			txtFolio.setVisible(true);
			txtUsuario.setVisible(true);
			btnBuscar.setVisible(true);
			btnAceptar.setVisible(false);				
			lblfolio.setVisible(true);
			lblusuario.setVisible(true);
			lblcontrasena.setVisible(true);
			txtContrasena.setText("");
			txtContrasena.requestFocus(true);
			btnAceptar.setEnabled(true);
			txtFolio.requestFocus(true);
			btnBuscar.setEnabled(false);
					};
					
//CARGA BOTONES EN TRUE
	@SuppressWarnings("rawtypes")
	public void subMenusbotones(){
			Vector SubMenuVector = new Obj_Menus().getSubmenuNivel (Integer.parseInt(txtFolio.getText()));
			ArrayList<Submenusbtns> lsSubMenus = new ArrayList<Submenusbtns>();
			for(int i=0; i<SubMenuVector.size(); i++){
				String[] tmpSTR = String.valueOf(SubMenuVector.get(i)).split(",");
				lsSubMenus.add(new Submenusbtns(tmpSTR[0], tmpSTR[1], tmpSTR[2]));
				
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 14)
					btnAltaEmp.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 19)
					btnChecador.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 24)
					btnBanco.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 25)
					btnCuadrantepersonal.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 41)
					btnFuenteSodasCajeras.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 44)
					btnFsAux.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 45)
					btnFsRH.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 46)
					btnListaComparacion.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 97)
					btnInasistencia.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 48)
					btnCaja.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 91)
					btnRetirosCajeras.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 50)
					btnPrestamo.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 51)
					btnListaRaya.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 79)
					btnSolicitudes.setEnabled(true);
				if(Integer.valueOf(tmpSTR[0].toString().trim()) == 80)
					btnCuadranteequipo.setEnabled(true);

		                                            }
	}
	
ActionListener Opciones = new ActionListener(){
		
		public void actionPerformed(ActionEvent click) {
			if(click.getSource().equals(btnAltaEmp))
				new Cat_Empleados().setVisible(true);
			
			if(click.getSource().equals(btnBanco))
				new Cat_Depositos_A_Bancos().setVisible(true);
						
			if(click.getSource().equals(btnCuadrantepersonal))
				new Cat_Captura_Del_Cuadrante_Personal(txtUsuario.getText()).setVisible(true);
			
			if(click.getSource().equals(btnFuenteSodasCajeras))
				new Cat_Captura_De_Fuente_De_Sodas_De_Cajeras().setVisible(true);
			
			if(click.getSource().equals(btnFsAux))
				new Cat_Traspaso_A_Cobro_De_Fuente_De_Sodas_AUXF().setVisible(true);

			if(click.getSource().equals(btnFsRH))
				new Cat_Traspaso_A_Cobro_De_Fuente_De_Sodas_DH().setVisible(true);
			
			if(click.getSource().equals(btnListaComparacion))
				new Cat_Lista_De_Comparacion_De_Fuente_De_Sodas().setVisible(true);

			if(click.getSource().equals(btnInasistencia))
				new Cat_Deducciones_Y_Percepciones_De_Lista_De_Raya().setVisible(true);
			
			if(click.getSource().equals(btnCaja))
				new Cat_Diferencia_De_Cortes().setVisible(true);
			
			if(click.getSource().equals(btnRetirosCajeras))
				new Cat_Retiros_A_Cajeros().setVisible(true);
			
			if(click.getSource().equals(btnPrestamo))
				new Cat_Prestamos().setVisible(true);
			
			if(click.getSource().equals(btnListaRaya))
				new Cat_Revision_De_Lista_Raya().setVisible(true);
			
			if(click.getSource().equals(btnSolicitudes))
				new Cat_Solicitud_De_Empleados().setVisible(true);
			
			if(click.getSource().equals(btnCuadranteequipo))
				new Cat_Captura_De_Cuadrante_Por_Nivel_Jerarquico().setVisible(true);
	
			if(click.getSource().equals(btnChecador))
				new Cat_Checador().setVisible(true);

			if(click.getSource().equals(btnCerrar)){
				dispose();			
			try {
				R.exec("taskkill /f /im javaw.exe");
			} catch (Exception e2){}
			}
		}
	};

	
public class Submenusbtns{
	public int Folio;
	public String Nombre;
	public int Menu_Id;
	public Submenusbtns(String folio, String nombre, String menu_id){
		this.Folio = Integer.valueOf(folio);
		this.Nombre = nombre;
		this.Menu_Id = Integer.valueOf(menu_id);
	}
}



//	filtro de Empleados con usuario en SCOI y con status vigente--------------------------------------------------------------------------------
		public class Cat_Seleccion_De_Usuario extends JDialog {
			
			Container cont = getContentPane();
			JLayeredPane campo = new JLayeredPane();
			
			DefaultTableModel model = new DefaultTableModel(0,3){
				public boolean isCellEditable(int fila, int columna){
					if(columna < 0)
						return true;
					return false;
				}
			};
			
			JTable tabla = new JTable(model);
			
			@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		
		JTextField txtFolioFiltroEmpleado = new JTextField();
		JTextField txtNombre_Completo = new JTextField();
		
		String establecimientos[] = new Obj_Establecimiento().Combo_Establecimiento();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cmbEstablecimientos = new JComboBox(establecimientos);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Cat_Seleccion_De_Usuario(){
			
			this.setModal(true);
			
			this.setIconImage(Toolkit.getDefaultToolkit().getImage("imagen/usuario-busquedaicono-4661-64.png"));
			this.setTitle("Filtro de Empleados");
			campo.setBorder(BorderFactory.createTitledBorder("Filtro De Empleado"));
			trsfiltro = new TableRowSorter(model); 
			tabla.setRowSorter(trsfiltro);  
			
			campo.add(getPanelTabla()).setBounds(15,42,450,565);
			
			campo.add(txtFolioFiltroEmpleado).setBounds(15,20,48,20);
			campo.add(txtNombre_Completo).setBounds(64,20,229,20);
			campo.add(cmbEstablecimientos).setBounds(295,20, 148, 20);
			
			agregar(tabla);
			
			cont.add(campo);
			
			txtFolioFiltroEmpleado.addKeyListener(opFiltroFolio);
			txtNombre_Completo.addKeyListener(opFiltroNombre);
			cmbEstablecimientos.addActionListener(opFiltro);
			
			this.setSize(490,650);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			tabla.addKeyListener(seleccionEmpleadoconteclado);
			
		//      asigna el foco al JTextField del nombre deseado al arrancar la ventana
		    this.addWindowListener(new WindowAdapter() {
		            public void windowOpened( WindowEvent e ){
		            	txtNombre_Completo.requestFocus();
		         }
		    });
		      
		//     pone el foco en el txtFolio al presionar la tecla scape
		      getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		         KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "foco");
		      
		      getRootPane().getActionMap().put("foco", new AbstractAction(){
		          @Override
		          public void actionPerformed(ActionEvent e)
		          {
		        	  txtNombre_Completo.setText("");
		              txtNombre_Completo.requestFocus();
		          }
		      });
		      
		//        pone el foco en la tabla al presionar f4
		      getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		         KeyStroke.getKeyStroke(KeyEvent.VK_F4 , 0), "dtabla");
		      
		      getRootPane().getActionMap().put("dtabla", new AbstractAction(){
		          @Override
		          public void actionPerformed(ActionEvent e)
		          {
		        	tabla.requestFocus();
		          }
		      });
			 
			
		}
		private void agregar(final JTable tbl) {
		    tbl.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		        	if(e.getClickCount() == 2){
		    			int fila = tabla.getSelectedRow();
		    			Object folio =  tabla.getValueAt(fila, 0).toString().trim();
		    			dispose();
		    			txtFolio.setText(folio+"");
		    			btnBuscar.doClick();
		        	}
		        }
		    });
		}
		
		KeyListener opFiltroFolio = new KeyListener(){
			@SuppressWarnings("unchecked")
			public void keyReleased(KeyEvent arg0) {
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtFolioFiltroEmpleado.getText(), 0));
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
				trsfiltro.setRowFilter(RowFilter.regexFilter(txtNombre_Completo.getText().toUpperCase().trim(), 1));
			}
			public void keyTyped(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {}		
		};
		
		ActionListener opFiltro = new ActionListener(){
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0){
				if(cmbEstablecimientos.getSelectedIndex() != 0){
					trsfiltro.setRowFilter(RowFilter.regexFilter(cmbEstablecimientos.getSelectedItem()+"", 2));
				}else{
					trsfiltro.setRowFilter(RowFilter.regexFilter("", 2));
				}
			}
		};
		
		private JScrollPane getPanelTabla()	{		
			
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			
			int a=2;
			tabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
			tabla.getColumnModel().getColumn(a).setCellRenderer(tcr);
			
			// Creamos las columnas.
			tabla.getColumnModel().getColumn(0).setHeaderValue("Folio");
			tabla.getColumnModel().getColumn(0).setMaxWidth(50);
			tabla.getColumnModel().getColumn(0).setMinWidth(50);
			tabla.getColumnModel().getColumn(1).setHeaderValue("Nombre Completo");
			tabla.getColumnModel().getColumn(1).setMaxWidth(230);
			tabla.getColumnModel().getColumn(1).setMinWidth(230);
			tabla.getColumnModel().getColumn(2).setHeaderValue("Establecimiento");
			tabla.getColumnModel().getColumn(2).setMaxWidth(150);
			tabla.getColumnModel().getColumn(2).setMinWidth(150);
			
			TableCellRenderer render = new TableCellRenderer() { 
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
				boolean hasFocus, int row, int column) { 
					
					Component componente = null;
					
					switch(column){
						case 0: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}				
							((JLabel) componente).setHorizontalAlignment(SwingConstants.RIGHT);
							break;
						case 1: 
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
							break;
						case 2:
							componente = new JLabel(value == null? "": value.toString());
							if(row %2 == 0){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(177,177,177));	
							}
							if(table.getSelectedRow() == row){
								((JComponent) componente).setOpaque(true); 
								componente.setBackground(new java.awt.Color(186,143,73));
							}
							((JLabel) componente).setHorizontalAlignment(SwingConstants.LEFT);
							break;
					}
					return componente;
				} 
			}; 
			
			tabla.getColumnModel().getColumn(a=0).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render); 
			tabla.getColumnModel().getColumn(a+=1).setCellRenderer(render);
			
			Statement s;
			ResultSet rs;
			try {
				s = new Connexion().conexion().createStatement();
				rs = s.executeQuery("exec sp_select_usuarios_scoi");
				
				while (rs.next())
				{ 
				   String [] fila = new String[3];
				   fila[0] = rs.getString(1)+"  ";
				   fila[1] = "   "+rs.getString(2);
				   fila[2] = "   "+rs.getString(3).trim();
				
				   model.addRow(fila); 
				}	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 JScrollPane scrol = new JScrollPane(tabla);
			   
		    return scrol; 
		}
		
		KeyListener validaCantidad = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e){
				char caracter = e.getKeyChar();				
				if(((caracter < '0') ||	
				    	(caracter > '9')) && 
				    	(caracter != '.' )){
				    	e.consume();
				    	}
			}
			@Override
			public void keyReleased(KeyEvent e) {	
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}	
		};
		
		KeyListener validaNumericoConPunto = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				
			    if(((caracter < '0') ||	
			    	(caracter > '9')) && 
			    	(caracter != '.')){
			    	e.consume();
			    	}
			}
			@Override
			public void keyPressed(KeyEvent e){}
			@Override
			public void keyReleased(KeyEvent e){}
									
		};
		
		KeyListener seleccionEmpleadoconteclado = new KeyListener() {
			@SuppressWarnings("static-access")
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				
				if(caracter==e.VK_ENTER){
				int fila=tabla.getSelectedRow()-1;
				String folio = tabla.getValueAt(fila,0).toString().trim();
					
				txtFolio.setText(folio);
				btnBuscar.doClick();
				dispose();
				}
			}
			@Override
			public void keyPressed(KeyEvent e){}
			@Override
			public void keyReleased(KeyEvent e){}
									
		};
		
}
					
			
}


