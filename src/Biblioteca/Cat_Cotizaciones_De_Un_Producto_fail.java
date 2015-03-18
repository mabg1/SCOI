package Biblioteca;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;


import com.toedter.calendar.JDateChooser;


import Cat_Compras.Cat_Filtro_De_Busqueda_De_Productos;
import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Cotizaciones_De_Un_Producto_fail extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtcod_prod = new Componentes().text(new JTextField(), "Codigo del Producto", 15, "String");
	JButton btnBuscar_Producto = new JButton("",new ImageIcon("imagen/Filter-List-icon16.png"));
	
	JLabel Marcoproveedor1= new JLabel();
	JTextField txtProveedor1 = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
	JTextField txtFoliocompra1 = new Componentes().text(new JTextField(), "Folio De La Compra",50, "String");
	JTextField txtUlt_Fecha_Comp_prov1 = new Componentes().text(new JTextField(), "Fecha De La Compra",50, "String");
	JTextField txtUlt_Costo_prov1 = new Componentes().text(new JTextField(), "Ultimo Costo De La Compra",50, "String");
	JTextField txtUlt_Compra_prov1 = new Componentes().text(new JTextField(), "Cantidad De La Compra",50, "String");
	JTextField txtCosto_Nuevo_prov1 = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",50, "String");
	JTextField txtCondicion_Cant_compra_prov1 = new Componentes().text(new JTextField(), "Cantidad Minima de Compra Para Respetar Costo",150, "String");

	JLabel Marcoproveedor2= new JLabel();
	JTextField txtProveedor2 = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
	JTextField txtFoliocompra2 = new Componentes().text(new JTextField(), "Folio De La Compra",50, "String");
	JTextField txtUlt_Fecha_Comp_prov2 = new Componentes().text(new JTextField(), "Fecha De La Compra",50, "String");
	JTextField txtUlt_Costo_prov2 = new Componentes().text(new JTextField(), "Ultimo Costo De La Compra",50, "String");
	JTextField txtUlt_Compra_prov2 = new Componentes().text(new JTextField(), "Cantidad De La Compra",50, "String");
	JTextField txtCosto_Nuevo_prov2 = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",50, "String");
	JTextField txtCondicion_Cant_compra_prov2 = new Componentes().text(new JTextField(), "Cantidad Minima de Compra Para Respetar Costo",150, "String");
	
	JLabel Marcoproveedor3= new JLabel();
	JTextField txtProveedor3 = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
	JTextField txtFoliocompra3 = new Componentes().text(new JTextField(), "Folio De La Compra",50, "String");
	JTextField txtUlt_Fecha_Comp_prov3 = new Componentes().text(new JTextField(), "Fecha De La Compra",50, "String");
	JTextField txtUlt_Costo_prov3 = new Componentes().text(new JTextField(), "Ultimo Costo De La Compra",50, "String");
	JTextField txtUlt_Compra_prov3 = new Componentes().text(new JTextField(), "Cantidad De La Compra",50, "String");
	JTextField txtCosto_Nuevo_prov3 = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",50, "String");
	JTextField txtCondicion_Cant_compra_prov3 = new Componentes().text(new JTextField(), "Cantidad Minima de Compra Para Respetar Costo",150, "String");
	
	
	JLabel Marcoproveedor4= new JLabel();
	JTextField txtProveedor4 = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
	JTextField txtFoliocompra4 = new Componentes().text(new JTextField(), "Folio De La Compra",50, "String");
	JTextField txtUlt_Fecha_Comp_prov4 = new Componentes().text(new JTextField(), "Fecha De La Compra",50, "String");
	JTextField txtUlt_Costo_prov4 = new Componentes().text(new JTextField(), "Ultimo Costo De La Compra",50, "String");
	JTextField txtUlt_Compra_prov4 = new Componentes().text(new JTextField(), "Cantidad De La Compra",50, "String");
	JTextField txtCosto_Nuevo_prov4 = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",50, "String");
	JTextField txtCondicion_Cant_compra_prov4 = new Componentes().text(new JTextField(), "Cantidad Minima de Compra Para Respetar Costo",150, "String");
	
	JLabel Marcoproveedor5= new JLabel();
	JTextField txtProveedor5 = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
	JTextField txtFoliocompra5 = new Componentes().text(new JTextField(), "Folio De La Compra",50, "String");
	JTextField txtUlt_Fecha_Comp_prov5 = new Componentes().text(new JTextField(), "Fecha De La Compra",50, "String");
	JTextField txtUlt_Costo_prov5 = new Componentes().text(new JTextField(), "Ultimo Costo De La Compra",50, "String");
	JTextField txtUlt_Compra_prov5 = new Componentes().text(new JTextField(), "Cantidad De La Compra",50, "String");
	JTextField txtCosto_Nuevo_prov5 = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",50, "String");
	JTextField txtCondicion_Cant_compra_prov5 = new Componentes().text(new JTextField(), "Cantidad Minima de Compra Para Respetar Costo",150, "String");
	
	
	JLabel Marcoproveedor6= new JLabel();
	JTextField txtProveedor6 = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
	JTextField txtFoliocompra6 = new Componentes().text(new JTextField(), "Folio De La Compra",50, "String");
	JTextField txtUlt_Fecha_Comp_prov6 = new Componentes().text(new JTextField(), "Fecha De La Compra",50, "String");
	JTextField txtUlt_Costo_prov6 = new Componentes().text(new JTextField(), "Ultimo Costo De La Compra",50, "String");
	JTextField txtUlt_Compra_prov6 = new Componentes().text(new JTextField(), "Cantidad De La Compra",50, "String");
	JTextField txtCosto_Nuevo_prov6 = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",50, "String");
	JTextField txtCondicion_Cant_compra_prov6 = new Componentes().text(new JTextField(), "Cantidad Minima de Compra Para Respetar Costo",150, "String");
	
	JLabel Marcoproveedor7= new JLabel();
	JTextField txtProveedor7 = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
	JTextField txtFoliocompra7 = new Componentes().text(new JTextField(), "Folio De La Compra",50, "String");
	JTextField txtUlt_Fecha_Comp_prov7 = new Componentes().text(new JTextField(), "Fecha De La Compra",50, "String");
	JTextField txtUlt_Costo_prov7 = new Componentes().text(new JTextField(), "Ultimo Costo De La Compra",50, "String");
	JTextField txtUlt_Compra_prov7 = new Componentes().text(new JTextField(), "Cantidad De La Compra",50, "String");
	JTextField txtCosto_Nuevo_prov7 = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",50, "String");
	JTextField txtCondicion_Cant_compra_prov7 = new Componentes().text(new JTextField(), "Cantidad Minima de Compra Para Respetar Costo",150, "String");
	
	
	JLabel Marcoproveedor8= new JLabel();
	JTextField txtProveedor8 = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
	JTextField txtFoliocompra8 = new Componentes().text(new JTextField(), "Folio De La Compra",50, "String");
	JTextField txtUlt_Fecha_Comp_prov8 = new Componentes().text(new JTextField(), "Fecha De La Compra",50, "String");
	JTextField txtUlt_Costo_prov8 = new Componentes().text(new JTextField(), "Ultimo Costo De La Compra",50, "String");
	JTextField txtUlt_Compra_prov8 = new Componentes().text(new JTextField(), "Cantidad De La Compra",50, "String");
	JTextField txtCosto_Nuevo_prov8 = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",50, "String");
	JTextField txtCondicion_Cant_compra_prov8 = new Componentes().text(new JTextField(), "Cantidad Minima de Compra Para Respetar Costo",150, "String");
	
	JLabel Marcoproveedor9= new JLabel();
	JTextField txtProveedor9 = new Componentes().text(new JTextField(), "Nombre Del Proveedor",150, "String");
	JTextField txtFoliocompra9 = new Componentes().text(new JTextField(), "Folio De La Compra",50, "String");
	JTextField txtUlt_Fecha_Comp_prov9 = new Componentes().text(new JTextField(), "Fecha De La Compra",50, "String");
	JTextField txtUlt_Costo_prov9 = new Componentes().text(new JTextField(), "Ultimo Costo De La Compra",50, "String");
	JTextField txtUlt_Compra_prov9 = new Componentes().text(new JTextField(), "Cantidad De La Compra",50, "String");
	JTextField txtCosto_Nuevo_prov9 = new Componentes().text(new JTextField(), "Costo Nuevo Del Proveedor",50, "String");
	JTextField txtCondicion_Cant_compra_prov9 = new Componentes().text(new JTextField(), "Cantidad Minima de Compra Para Respetar Costo",150, "String");
	
	
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	
	
	JDateChooser cfecha = new JDateChooser();
	
	String codigo_producto = "";
	
	public Cat_Cotizaciones_De_Un_Producto_fail(int cod_prod){
		
		if(cod_prod==0){
			codigo_producto="";
		}else{
			codigo_producto=cod_prod+"";
			};
		
		setSize(1022,768);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Reportes De Fuente De Sodas");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Filter-List-icon16.png"));
		blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
		panel.setBorder(BorderFactory.createTitledBorder(blackline,"Seleccione el Tipo de Reporte"));
		
		Marcoproveedor1.setBorder(BorderFactory.createTitledBorder(blackline,"Proveedor 1"));
		Marcoproveedor2.setBorder(BorderFactory.createTitledBorder(blackline,"Proveedor 2"));
		Marcoproveedor3.setBorder(BorderFactory.createTitledBorder(blackline,"Proveedor 3"));
		Marcoproveedor4.setBorder(BorderFactory.createTitledBorder(blackline,"Proveedor 4"));
		Marcoproveedor5.setBorder(BorderFactory.createTitledBorder(blackline,"Proveedor 5"));
		Marcoproveedor6.setBorder(BorderFactory.createTitledBorder(blackline,"Proveedor 6"));
		Marcoproveedor7.setBorder(BorderFactory.createTitledBorder(blackline,"Proveedor 7"));
		Marcoproveedor8.setBorder(BorderFactory.createTitledBorder(blackline,"Proveedor 8"));
		Marcoproveedor9.setBorder(BorderFactory.createTitledBorder(blackline,"Proveedor 9"));
		
		
	
//		btnReporte_porfolio.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
//				"		<CENTER><p>Reporte De Fuente De Sodas De Lista De Raya Por Folio </p></CENTER></FONT>" +
//				"</html>");
		
		int x=10 ;
		int y=20 ;
		int a=20;
		int l=100;
		
		String descripcion="PRUEBA PURE DE TOMATE DE LA X DESODORANTE NIVEA ROLL-ON INVISIBLE BLACK & WHITE PURE 50 ML 4005900036629 PURE DEL";
		double ultimo_costo=999991.99;
		double costo_promedio=999992.99;
		double exist_cedis=999993.99;
		double exist_total=999994.99;
		
		
		panel.add(txtcod_prod).setBounds(x,y,l,a);
		panel.add(btnBuscar_Producto).setBounds(l+=x,y,a,a);
		
		panel.add(new JLabel("<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk><p>"+descripcion+"</p></FONT></html>")).setBounds(l+x+15,y,l+700,a);
		
		panel.add(cfecha).setBounds(x,y+=25,l,a);
		
		panel.add(new JLabel("Ultimo Costo:")).setBounds(x+=l+15,y,l-40,a);
		panel.add(new JLabel("<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk><p>"+ultimo_costo+"</p></FONT></html>")).setBounds(x+=75,y,l-20,a);
		panel.add(new JLabel("Costo Promedio:")).setBounds(x+=l-20,y,l-30,a);
		panel.add(new JLabel("<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk><p>"+costo_promedio+"</p></FONT></html>")).setBounds(x+=85,y,l-20,a);
		panel.add(new JLabel("Existencia Cedis:")).setBounds(x+=l-20,y,l-20,a);
		panel.add(new JLabel("<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk><p>"+exist_cedis+"</p></FONT></html>")).setBounds(x+=85,y,l-20,a);
		panel.add(new JLabel("Existencia Total:")).setBounds(x+=l-20,y,l-20,a);
		panel.add(new JLabel("<html> <FONT FACE="+"arial"+" SIZE=4 COLOR=BLACk><p>"+exist_total+"</p></FONT></html>")).setBounds(x+=85,y,l-20,a);
		
		
		x=10;
		l=327;
		a=160;
		panel.add(Marcoproveedor1).setBounds(x,y+=100,l,a);
		panel.add(txtProveedor1).setBounds(x+5,y+15,l-10,20);
		panel.add(new JLabel("Fecha:")).setBounds(x+5,y+40,l-287,20);
		panel.add(txtUlt_Fecha_Comp_prov1).setBounds(x+55,y+=40,l-227,20);
		
		panel.add(new JLabel("Folio::")).setBounds(x+160,y,l-270,20);
		panel.add(txtFoliocompra1).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("UltCosto:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtUlt_Costo_prov1).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x+160,y,l-270,20);
		panel.add(txtUlt_Compra_prov1).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("CostoNvo:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtCosto_Nuevo_prov1).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("CantCompra:")).setBounds(x+160,y,l-260,20);
		panel.add(txtCondicion_Cant_compra_prov1).setBounds(x+222,y,l-227,20);

		
		x=343;
		y=145;
		panel.add(Marcoproveedor2).setBounds(x,y,l,a);
		panel.add(txtProveedor2).setBounds(x+5,y+15,l-10,20);
		panel.add(new JLabel("Fecha:")).setBounds(x+5,y+40,l-287,20);
		panel.add(txtUlt_Fecha_Comp_prov2).setBounds(x+55,y+=40,l-227,20);
		
		panel.add(new JLabel("Folio::")).setBounds(x+160,y,l-270,20);
		panel.add(txtFoliocompra2).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("UltCosto:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtUlt_Costo_prov2).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x+160,y,l-270,20);
		panel.add(txtUlt_Compra_prov2).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("CostoNvo:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtCosto_Nuevo_prov2).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("CantCompra:")).setBounds(x+160,y,l-260,20);
		panel.add(txtCondicion_Cant_compra_prov2).setBounds(x+222,y,l-227,20);
		
		
		x=675;
		y=145;
		panel.add(Marcoproveedor3).setBounds(x,y,l,a);
		panel.add(txtProveedor3).setBounds(x+5,y+15,l-10,20);
		panel.add(new JLabel("Fecha:")).setBounds(x+5,y+40,l-287,20);
		panel.add(txtUlt_Fecha_Comp_prov3).setBounds(x+55,y+=40,l-227,20);
		
		panel.add(new JLabel("Folio::")).setBounds(x+160,y,l-270,20);
		panel.add(txtFoliocompra3).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("UltCosto:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtUlt_Costo_prov3).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x+160,y,l-270,20);
		panel.add(txtUlt_Compra_prov3).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("CostoNvo:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtCosto_Nuevo_prov3).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("CantCompra:")).setBounds(x+160,y,l-260,20);
		panel.add(txtCondicion_Cant_compra_prov3).setBounds(x+222,y,l-227,20);
		
		
		x=10;
		y=310;
		panel.add(Marcoproveedor4).setBounds(x,y,l,a);
		panel.add(txtProveedor4).setBounds(x+5,y+15,l-10,20);
		panel.add(new JLabel("Fecha:")).setBounds(x+5,y+40,l-287,20);
		panel.add(txtUlt_Fecha_Comp_prov4).setBounds(x+55,y+=40,l-227,20);
		
		panel.add(new JLabel("Folio::")).setBounds(x+160,y,l-270,20);
		panel.add(txtFoliocompra4).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("UltCosto:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtUlt_Costo_prov4).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x+160,y,l-270,20);
		panel.add(txtUlt_Compra_prov4).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("CostoNvo:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtCosto_Nuevo_prov4).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("CantCompra:")).setBounds(x+160,y,l-260,20);
		panel.add(txtCondicion_Cant_compra_prov4).setBounds(x+222,y,l-227,20);
		
		
		x=343;
		y=310;
		panel.add(Marcoproveedor5).setBounds(x,y,l,a);
		panel.add(txtProveedor5).setBounds(x+5,y+15,l-10,20);
		panel.add(new JLabel("Fecha:")).setBounds(x+5,y+40,l-287,20);
		panel.add(txtUlt_Fecha_Comp_prov5).setBounds(x+55,y+=40,l-227,20);
		
		panel.add(new JLabel("Folio::")).setBounds(x+160,y,l-270,20);
		panel.add(txtFoliocompra5).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("UltCosto:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtUlt_Costo_prov5).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x+160,y,l-270,20);
		panel.add(txtUlt_Compra_prov5).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("CostoNvo:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtCosto_Nuevo_prov5).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("CantCompra:")).setBounds(x+160,y,l-260,20);
		panel.add(txtCondicion_Cant_compra_prov5).setBounds(x+222,y,l-227,20);
		
		
		x=675;
		y=310;
		panel.add(Marcoproveedor6).setBounds(x,y,l,a);
		panel.add(txtProveedor6).setBounds(x+5,y+15,l-10,20);
		panel.add(new JLabel("Fecha:")).setBounds(x+5,y+40,l-287,20);
		panel.add(txtUlt_Fecha_Comp_prov6).setBounds(x+55,y+=40,l-227,20);
		
		panel.add(new JLabel("Folio:")).setBounds(x+160,y,l-270,20);
		panel.add(txtFoliocompra6).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("UltCosto:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtUlt_Costo_prov6).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("Cantidad:")).setBounds(x+160,y,l-270,20);
		panel.add(txtUlt_Compra_prov6).setBounds(x+222,y,l-227,20);
		
		panel.add(new JLabel("CostoNvo:")).setBounds(x+5,y+=25,l-270,20);
		panel.add(txtCosto_Nuevo_prov6).setBounds(x+55,y,l-227,20);
		
		panel.add(new JLabel("CantCompra:")).setBounds(x+160,y,l-260,20);
		panel.add(txtCondicion_Cant_compra_prov6).setBounds(x+222,y,l-227,20);
		
		
		
		
		x=10;
		y=520;
		panel.add(Marcoproveedor7).setBounds(x,y,l,a);
		
		panel.add(Marcoproveedor8).setBounds(x+=333,y,l,a);
		
		panel.add(Marcoproveedor9).setBounds(x+=333,y,l,a);
		
		
		

		
		
		agregar_consulta_a_txts();
		txtdefault();

	    
	    cfecha.setEnabled(false);
	    
		cont.add(panel);
		
		btnBuscar_Producto.addActionListener(opBuscar_Producto);
		
     	///filtro de Listas de Raya
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "buscarLR");
          getRootPane().getActionMap().put("buscarLR", new AbstractAction(){
         public void actionPerformed(ActionEvent e)
         {         
        	 btnBuscar_Producto.doClick();
       	    }
     });
		txtcod_prod.setText(codigo_producto+"");
	}

	
	ActionListener opBuscar_Producto = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			new Cat_Filtro_De_Busqueda_De_Productos("Cotizaciones_De_Un_Producto").setVisible(true);
		}
	};
	

	public void agregar_consulta_a_txts() {
		txtProveedor1.setText("Proveedor 1");
		txtFoliocompra1.setText("Folio compra");
		txtUlt_Fecha_Comp_prov1.setText("fecha");
		txtUlt_Costo_prov1.setText("ult costo");
		txtUlt_Compra_prov1.setText("ult compra");
		
		txtProveedor2.setText("Proveedor 2");
		txtFoliocompra2.setText("Folio compra 2");
		txtUlt_Fecha_Comp_prov2.setText("fecha 2");
		txtUlt_Costo_prov2.setText("ult costo 2");
		txtUlt_Compra_prov2.setText("ult compra 2");
		
		txtProveedor3.setText("Proveedor 3");
		txtFoliocompra3.setText("Folio compra 3");
		txtUlt_Fecha_Comp_prov3.setText("fecha 3");
		txtUlt_Costo_prov3.setText("ult costo 3");
		txtUlt_Compra_prov3.setText("ult compra 3");
		
		txtProveedor4.setText("Proveedor 4");
		txtFoliocompra4.setText("Folio compra 4");
		txtUlt_Fecha_Comp_prov4.setText("fecha 4");
		txtUlt_Costo_prov4.setText("ult costo 4");
		txtUlt_Compra_prov4.setText("ult compra 4");
		
		txtProveedor5.setText("Proveedor 5");
		txtFoliocompra5.setText("Folio compra 5");
		txtUlt_Fecha_Comp_prov5.setText("fecha 5");
		txtUlt_Costo_prov5.setText("ult costo 5");
		txtUlt_Compra_prov5.setText("ult compra 5");
		
		txtProveedor6.setText("Proveedor 6");
		txtFoliocompra6.setText("Folio compra 6");
		txtUlt_Fecha_Comp_prov6.setText("fecha 6");
		txtUlt_Costo_prov6.setText("ult costo 6");
		txtUlt_Compra_prov6.setText("ult compra 6");
		
		
	};
	
	public void txtdefault() {
		txtProveedor1.setEditable(false);
		txtFoliocompra1.setEditable(false);
		txtUlt_Fecha_Comp_prov1.setEditable(false);
		txtUlt_Costo_prov1.setEditable(false);
		txtUlt_Compra_prov1.setEditable(false);
		
		txtProveedor2.setEditable(false);
		txtFoliocompra2.setEditable(false);
		txtUlt_Fecha_Comp_prov2.setEditable(false);
		txtUlt_Costo_prov2.setEditable(false);
		txtUlt_Compra_prov2.setEditable(false);
		
		txtProveedor3.setEditable(false);
		txtFoliocompra3.setEditable(false);
		txtUlt_Fecha_Comp_prov3.setEditable(false);
		txtUlt_Costo_prov3.setEditable(false);
		txtUlt_Compra_prov3.setEditable(false);
		
		txtProveedor4.setEditable(false);
		txtFoliocompra4.setEditable(false);
		txtUlt_Fecha_Comp_prov4.setEditable(false);
		txtUlt_Costo_prov4.setEditable(false);
		txtUlt_Compra_prov4.setEditable(false);
		
		txtProveedor5.setEditable(false);
		txtFoliocompra5.setEditable(false);
		txtUlt_Fecha_Comp_prov5.setEditable(false);
		txtUlt_Costo_prov5.setEditable(false);
		txtUlt_Compra_prov5.setEditable(false);
		
		txtProveedor6.setEditable(false);
		txtFoliocompra6.setEditable(false);
		txtUlt_Fecha_Comp_prov6.setEditable(false);
		txtUlt_Costo_prov6.setEditable(false);
		txtUlt_Compra_prov6.setEditable(false);
		
	};
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Cotizaciones_De_Un_Producto_fail(0).setVisible(true);
		}catch(Exception e){	}
	}
}
