package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import Conexiones_SQL.Connexion;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;
import Obj_Renders.tablaRenderer;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Reportes_De_Ventas extends JFrame {
	
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	Connexion con = new Connexion();
	
	
	JDateChooser c_inicio = new JDateChooser();
	JDateChooser c_final = new JDateChooser();
	
	String[] inicioDefault ="0:00:00".split(":");
	String[] finDefault ="23:59:59".split(":");
	
	SpinnerDateModel TiempoInicio =  new SpinnerDateModel();
	  JSpinner sphora_inicio = new JSpinner(TiempoInicio);                                         
	  JSpinner.DateEditor datoini = new JSpinner.DateEditor(sphora_inicio,"H:mm");
	SpinnerDateModel TiempoFin =  new SpinnerDateModel();
	  JSpinner sphora_fin = new JSpinner(TiempoFin);                                         
	  JSpinner.DateEditor datofin = new JSpinner.DateEditor(sphora_fin,"H:mm");
	  
	String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento201();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
	
	String operador[] = {"Igual","Esta en lista","Menor que","Mayor que","Diferente"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox cmbOperadorFiltro_Productos = new JComboBox(operador);

	
	JButton btnBuscar_Producto = new JButton("",new ImageIcon("imagen/Filter-List-icon16.png"));
	JButton btn_buscar = new JButton  ("Buscar",new ImageIcon("imagen/buscar.png"));
	JButton btnFiltroproducto = new JButton(new ImageIcon("Imagen/Filter-List-icon16.png"));
	JButton btnLimpiarFiltroproducto = new JButton(new ImageIcon("Imagen/clear-brush-broom-sweeping-change-icone-7230-16.png"));
	
	JLabel JLBlinicio= new JLabel(new ImageIcon("Imagen/iniciar-icono-4628-16.png") );
	JLabel JLBfin= new JLabel(new ImageIcon("Imagen/acabado-icono-7912-16.png") );
	JLabel JLBrelog= new JLabel(new ImageIcon("Imagen/horas-reloj-icono-9852-16.png") );
	JLabel JLBrelog2= new JLabel(new ImageIcon("Imagen/horas-reloj-icono-9852-16.png") );
	JLabel JLBestablecimiento= new JLabel(new ImageIcon("Imagen/folder-home-home-icone-5663-16.png") );
	
	
	JTextField txtcod_prod = new Componentes().text(new JTextField(), "Codigo del Producto", 15, "String");
	JTextField txtFiltroproducto = new JTextField("Todos Los Productos");
	
	
	DefaultTableModel modelo_ventas = new DefaultTableModel(null,
            new String[]{"Establecimiento","Exist","Vnt Pzs","Dias Vent","Vent/Dias","Exist/PrVtD","Fecha Agotado"}
			){
	     @SuppressWarnings("rawtypes")
		Class[] types = new Class[]{
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class,
	    	java.lang.String.class
                                    };
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
             return types[columnIndex];
         }
         public boolean isCellEditable(int fila, int columna){
        	 switch(columna){
        	 	case 0 : return false; 
        	 	case 1 : return false; 
        	 	case 2 : return false; 
        	 	case 3 : return false; 
        	 	case 4 : return false;
        	 	case 5 : return false;
        	 	case 6 : return false;
        	 } 				
 			return false;
 		}
	};
    JTable tabla = new JTable(modelo_ventas);
    JScrollPane scrollExist_Estab = new JScrollPane(tabla);
    
    
	
	Border blackline, etched, raisedbevel, loweredbevel, empty;
	

	
	public Cat_Reportes_De_Ventas(){
		cont.add(panel);
		setSize(1024,768);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/asistencia-comunitaria-icono-9465-32.png"));
		setTitle("Reportes de  Ventas");
		panel.setBorder(BorderFactory.createTitledBorder("Reportes de Venta"));
		
		int x=15 ;
		int y=20 ;
		int l=100;
		int a=20;

		panel.add(new JLabel("Fecha Inicio:")).setBounds(x,y,l,a);
		panel.add(JLBlinicio).setBounds(x+=60,y,a,a);
		panel.add(c_inicio).setBounds(x+=20,y,l-10,a);
		panel.add(sphora_inicio).setBounds(x+=95,y,l-50,a);
		panel.add(JLBrelog).setBounds(x+=50,y,a,a);
		
		panel.add(new JLabel("Fecha Final:")).setBounds(x+=40,y,l,a);
		panel.add(JLBfin).setBounds(x+=60,y,a,a);
		panel.add(c_final).setBounds(x+=20,y,l-10,a);
		panel.add(sphora_fin).setBounds(x+=95,y,l-50,a);
		panel.add(JLBrelog2).setBounds(x+=50,y,a,a);
		
	    panel.add(new JLabel("Establecimiento:")).setBounds(x+=40,y,l+50,a);
	    panel.add(JLBestablecimiento).setBounds(x+=75,y,a,a);
		panel.add(cmbEstablecimiento).setBounds(x+=20,y,l+70,a);
		
	    panel.add(btn_buscar).setBounds(x+=200,y,l,a);
		
		
		x=15;
		panel.add(new JLabel("Filtro Productos:")).setBounds(x,y+=30,l-20,a);
		panel.add(cmbOperadorFiltro_Productos).setBounds(x+=80,y,l-12,a);
		
        panel.add(txtFiltroproducto).setBounds(x+=90,y,l*3,a);
        panel.add(btnFiltroproducto).setBounds(x+=300,y,a,a);
        panel.add(btnLimpiarFiltroproducto).setBounds(x+=23,y,a,a);
       
        panel.add(Tabla()).setBounds(10,y+=50,1000,600);
        
    
		btn_buscar.addActionListener(op_generar);
		btnLimpiarFiltroproducto.addActionListener(limpiar_filtro_productos);
		btnFiltroproducto.addActionListener(filtro_productos);
		
		tiempodefault();
		
	}
	
	public void render_tabla(){
		//		tabla.getColumnModel().getColumn(# columna).setCellRenderer(new CellRenderer("tipo_de_valor","alineacion","tipo_de_letra","negrita",# tamanio_fuente));
				
tabla.getColumnModel().getColumn(0).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",10)); 
tabla.getColumnModel().getColumn(1).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
tabla.getColumnModel().getColumn(2).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
tabla.getColumnModel().getColumn(3).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
tabla.getColumnModel().getColumn(4).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
tabla.getColumnModel().getColumn(5).setCellRenderer(new tablaRenderer("texto","derecha","Arial","normal",12));
tabla.getColumnModel().getColumn(6).setCellRenderer(new tablaRenderer("texto","izquierda","Arial","normal",12));
			}
	
	
private JScrollPane Tabla()	{		
	
this.tabla.getTableHeader().setReorderingAllowed(false) ;
this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	
tabla.getColumnModel().getColumn(0).setMaxWidth(108);
tabla.getColumnModel().getColumn(0).setMinWidth(108);
tabla.getColumnModel().getColumn(1).setMaxWidth(55);
tabla.getColumnModel().getColumn(1).setMinWidth(55);
tabla.getColumnModel().getColumn(2).setMaxWidth(60);
tabla.getColumnModel().getColumn(2).setMinWidth(60);
tabla.getColumnModel().getColumn(3).setMaxWidth(55);
tabla.getColumnModel().getColumn(3).setMinWidth(55);
tabla.getColumnModel().getColumn(4).setMaxWidth(60);
tabla.getColumnModel().getColumn(4).setMinWidth(60);
tabla.getColumnModel().getColumn(5).setMaxWidth(60);
tabla.getColumnModel().getColumn(5).setMinWidth(60);
tabla.getColumnModel().getColumn(6).setMaxWidth(120);
tabla.getColumnModel().getColumn(6).setMinWidth(120);
	
	 JScrollPane scrol = new JScrollPane(tabla);
    return scrol; 
}

public void Llenar_Tabla (){
Statement s;
ResultSet rs;
//String cod_producto=txtcod_prod.getText().trim().toUpperCase()+"";

try {
	s = con.conexion_IZAGAR().createStatement();

	
	rs=null;
	
							rs = s.executeQuery(" Select 1,2,3,4,5,6,7"
									

									
									)           ;
      
 System.out.println(rs);
	while (rs.next())
	{ 
	   Object [] fila = new Object[7];
	   fila[0] = rs.getString(1).trim();
	   fila[1] = rs.getString(2).trim();
	   fila[2] = rs.getString(3).trim();
	   fila[3] = rs.getString(4).trim();
	   fila[4] = rs.getString(5).trim();
	   fila[5] = rs.getString(6).trim();
	   fila[6] = rs.getString(7).trim();
	   modelo_ventas.addRow(fila); 
	}	
} catch (SQLException e1) {
	e1.printStackTrace();
	JOptionPane.showMessageDialog(null, "Error en Cat_Reporte_de_Ventas en la funcion Llenar_Tabla  SQLException: "+e1.getMessage(), "Avisa al Administrador", JOptionPane.ERROR_MESSAGE);
}
}

	@SuppressWarnings("deprecation")
	public void tiempodefault(){
		sphora_inicio.setValue(new Time(Integer.parseInt(inicioDefault[0]),Integer.parseInt(inicioDefault[1]),Integer.parseInt(inicioDefault[2])));
		sphora_inicio.setEditor(datoini);
		sphora_fin.setValue(new Time(Integer.parseInt(finDefault[0]),Integer.parseInt(finDefault[1]),Integer.parseInt(finDefault[2])));
		sphora_fin.setEditor(datofin);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void Reporte_de_Asistencia_completo(String fecha_inicio, String fecha_final,String Establecimiento,String Departamento,String folios_empleados){
		String query = "exec sp_Reporte_De_Asistencia_Por_Establecimiento '"+fecha_inicio+"','"+fecha_final+"','"+Establecimiento+"','"+Departamento+"','"+folios_empleados+"'";
		Statement stmt = null;
		try {
			
			stmt =  new Connexion().conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
			JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_De_Asistencia_Por_Establecimiento.jrxml");
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
			JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
			JasperViewer.viewReport(print, false);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			JOptionPane.showMessageDialog(null, "Error En Cat_Reporte_General_de_Asistencia_Por_Establecimiento ", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
	}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void Reporte_de_Asistencia_establecimiento(String fecha_inicio, String fecha_final,String Establecimiento,String Departamento,String folios_empleados){
		String query = "exec sp_Reporte_General_de_Asistencia_Por_Establecimiento '"+fecha_inicio+"','"+fecha_final+"','"+Establecimiento+"','"+Departamento+"','"+folios_empleados+"'";
		Statement stmt = null;
		try {
			
			stmt =  new Connexion().conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
			JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+"\\src\\Obj_Reportes\\Obj_Reporte_de_Asistencia_Por_Establecimiento_Sin_Observaciones.jrxml");
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
			JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), resultSetDataSource);
			JasperViewer.viewReport(print, false);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			JOptionPane.showMessageDialog(null, "Error En Cat_Reporte_General_de_Asistencia_Por_Establecimiento ", "Error !!!", JOptionPane.WARNING_MESSAGE,new ImageIcon("Iconos//critica.png"));
	}
	}
	
	
	
	
	
	
	ActionListener op_generar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(validar_fechas().equals("")){
				
				Llenar_Tabla ();
				render_tabla ();
				
//				String fecha_inicio = new SimpleDateFormat("dd/MM/yyyy").format(c_inicio.getDate())+" 00:00:00";
//				String fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(c_final.getDate())+" 23:59:59";
//				String Establecimiento = cmbEstablecimiento.getSelectedItem().toString();
//				String Departamento = cmbDepartamento.getSelectedItem().toString();
//				String folios_empleados = "Selecciona un Empleado";

//				if(c_inicio.getDate().before(c_final.getDate())){
//					Reporte_de_Asistencia_establecimiento(fecha_inicio,fecha_final,Establecimiento,Departamento,folios_empleados);
//
//					
//				}else{
//					JOptionPane.showMessageDialog(null,"El Rango de Fechas Esta Invertido","Aviso!", JOptionPane.WARNING_MESSAGE);
//					return;
//				}
				
			}else{
				JOptionPane.showMessageDialog(null,"Los siguientes campos están vacíos: "+validar_fechas(),"Aviso!", JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
	};
	
	
	ActionListener filtro_productos = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
//			new Cat_Filtro_De_Productos().setVisible(true);

		}
	};
	
	
	ActionListener limpiar_filtro_productos = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
            txtFiltroproducto.setText("Todos Los Productos");
		}
	};
	
	
	public String validar_fechas(){
		String error = "";
		String fechainicioNull = c_inicio.getDate()+"";
		String fechafinalNull = c_final.getDate()+"";
	    if(fechainicioNull.equals("null"))error+= "Fecha  inicio\n";
		if(fechafinalNull.equals("null"))error+= "Fecha Final\n";
		
		return error;
	}
	
/////////////////////////////////////////////////////////////FILTRO PRODUCTOS///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//public class Cat_Filtro_De_Productos extends JDialog {
//		
//		Container cont = getContentPane();
//		JLayeredPane panel = new JLayeredPane();
//		
//		String operador[] = {"Igual","Esta en lista","Menor que","Mayor que","Diferente"};
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//		JComboBox cmbOperadorEqTrabajo = new JComboBox(operador);
//		
//		JTextField txtComparacionEqTrabajo = new JTextField();
//		JButton btnSeleccionEqTrabajo = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
//		JButton btnLimpiarfiltro_producto = new JButton(new ImageIcon("Iconos/limpiar.png"));
//		JButton btnEnviarEqTrabajo = new JButton("Enviar");
//		
//		JTextArea txaArmadofiltro_productos = new Componentes().textArea(new JTextArea(), "Productos Filtrados Para El Reporte", 240);
//		JScrollPane armadoEqTrabajo = new JScrollPane(txaArmadofiltro_productos);
//		Border border = LineBorder.createGrayLineBorder();
//		
//		public Cat_Filtro_De_Productos(){
//			this.setModal(true);
//			 Constructor();
//		}
//		
//		public void Constructor(){
//
//			this.setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/Filter-List-icon32.png"));
//			panel.setBorder(BorderFactory.createTitledBorder("Filtro de Productos"));
//			this.setTitle("Seleccione Los Productos");
//			
//			btnSeleccionEqTrabajo.setToolTipText("Seleccion de Productos");
//			btnLimpiarfiltro_producto.setToolTipText("Limpiar");
//
//			txaArmadofiltro_productos.setBorder(border);
//			txaArmadofiltro_productos.setLineWrap(true);
//			
//			
//			int y=20;
//			
//			panel.add(new JLabel("Equipo de trabajo ")).setBounds(20,y+=35,60,20);
//			panel.add(cmbOperadorEqTrabajo).setBounds(80,y,100,20);
//			
//			panel.add(txtComparacionEqTrabajo).setBounds(200, y, 140, 20);
//			panel.add(btnSeleccionEqTrabajo).setBounds(350, y, 30, 20);
//			panel.add(btnLimpiarfiltro_producto).setBounds(390, y, 30, 20);
//			
//			panel.add(armadoEqTrabajo).setBounds(20, y+=35, 400, 100);
//			
//			panel.add(btnEnviarEqTrabajo).setBounds(350, y+=110, 70, 20);
//			
////			cmbOperadorEqTrabajo.addActionListener(opCompararEqTrabajo);
//			btnLimpiarfiltro_producto.addActionListener(opLimpiararmado_de_productos);
////			btnSeleccionEqTrabajo.addActionListener(opFiltroEqTrabajo);
////			btnEnviarEqTrabajo.addActionListener(opEnviarEqTrabajo);
////			
//			txtComparacionEqTrabajo.setEditable(false);
////			txaArmadofiltro_productos.setEditable(false);
//			btnSeleccionEqTrabajo.setEnabled(false);
//
//			cont.add(panel);
//			this.setSize(460,280);
//			this.setLocationRelativeTo(null);
//		}
//		
//		ActionListener opLimpiararmado_de_productos = new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				txtComparacionEqTrabajo.setText("");
//				actionAplicar();
//			}
//		};
//		
//
//		public void actionAplicar(){
//			switch(cmbOperadorEqTrabajo.getSelectedIndex()){
//				case 0:  txaArmadofiltro_productos.setText("cod_prod = "+txtComparacionEqTrabajo.getText()); break;
//				case 1:  txaArmadofiltro_productos.setText("cod_prod IN "+txtComparacionEqTrabajo.getText()); break;
//				case 2:  txaArmadofiltro_productos.setText("cod_prod < "+txtComparacionEqTrabajo.getText()); break;
//				case 3:  txaArmadofiltro_productos.setText("cod_prod > "+txtComparacionEqTrabajo.getText()); break;
//				case 4:  txaArmadofiltro_productos.setText("cod_prod <> "+txtComparacionEqTrabajo.getText()); break;
//			}
//	     }
//      }
		
		
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_De_Ventas().setVisible(true);
		}catch(Exception e){	}
	}

}
