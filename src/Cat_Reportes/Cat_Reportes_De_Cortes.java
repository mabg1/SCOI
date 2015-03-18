package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import Conexiones_SQL.Generacion_Reportes;
import Obj_Administracion_del_Sistema.Obj_Usuario;
import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Reportes_De_Cortes extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio del Corte", 10, "String");
	
	JButton btncortedelfolio = new JButton("",new ImageIcon("imagen/bolsa-de-dinero-en-efectivo-icono-6673-16.png"));
	JButton btnlistadocortesdia = new JButton("",new ImageIcon("imagen/Calendar.png"));
	JButton btnlistadocortesExportar = new JButton("",new ImageIcon("imagen/hoja-de-calculo-excel-icono-5223-16.png"));

	
	JButton btngenerar = new JButton("Generar",new ImageIcon("imagen/buscar.png"));
	
	JDateChooser cfecha = new JDateChooser();
	
	int tipo_Reporte = 0;
	
	public Cat_Reportes_De_Cortes(){
		setSize(305,350);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Reportes De Cortes");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/bolsa-de-dinero-en-efectivo-icono-6673-32.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Seleccione el Tipo de Reporte Folio-Fecha"));
	
		btncortedelfolio.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Corte Por Folio</p></CENTER></FONT>" +
				"</html>");
		
		btnlistadocortesdia.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Cortes Por Fecha     </p></CENTER></FONT>" +
				"</html>");	
		
		btnlistadocortesExportar.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Cortes Por Fecha</p></CENTER>" +
				"		<CENTER><p>Para Exportar</p></CENTER></FONT>" +
				"</html>");	
		
		
		panel.add(btncortedelfolio).setBounds(20,25,260,30);
		panel.add(btnlistadocortesdia).setBounds(20,75,260,30);
		panel.add(btnlistadocortesExportar).setBounds(20,125,260,40);
		
		panel.add(new JLabel("Folio:")).setBounds(20,180,200,20);		
		panel.add(txtFolio).setBounds(80,180,195,20);
		panel.add(new JLabel("Fecha:")).setBounds(20,220,200,20);
		
		panel.add(cfecha).setBounds(80,220,195,20);
		
		
		panel.add(btngenerar).setBounds(100,275,120,30);
	    
	    txtFolio.setEditable(false);
	    cfecha.setEnabled(false);
	    btngenerar.setEnabled(false);
	    
		cont.add(panel);
		btngenerar.addActionListener(opGenerar);
		btncortedelfolio.addActionListener(opReporte_Por_Folio);
		btnlistadocortesdia.addActionListener(opReporte_Por_Fecha);
		btnlistadocortesExportar.addActionListener(opReporte_Cortes_Pendientes);

	}
	ActionListener opReporte_Por_Folio = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(true);
			cfecha.setEnabled(false);
			btngenerar.setEnabled(true);
			tipo_Reporte=1;
			cfecha.setDate(null);
		}
	};
	
	ActionListener opReporte_Por_Fecha = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(false);
			cfecha.setEnabled(true);
			btngenerar.setEnabled(true);
			tipo_Reporte=2;
			txtFolio.setText("");
		}
	};

	ActionListener opReporte_Cortes_Pendientes = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(false);
			cfecha.setEnabled(true);
			btnlistadocortesExportar.setEnabled(true);
			btngenerar.setEnabled(true);
			tipo_Reporte=3;
			txtFolio.setText("");
		}
	};
	
	public String validar_fechas(){
		String error = "";
		@SuppressWarnings("unused")
		String fechafinalNull = cfecha.getDate()+"";
	    if(cfecha.equals("null"))error+= "Fecha\n";
		return error;
	}
	
	ActionListener opGenerar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String basedatos="2.26";
			String vista_previa_reporte="no";
			int vista_previa_de_ventana=0;
			String comando="";
			String reporte = "";
			
			if(tipo_Reporte==1){
						if(!txtFolio.getText().equals("")){
								new Cat_Reporte_De_Corte_De_Caja(txtFolio.getText()+"");
						}else{
							JOptionPane.showMessageDialog(null,"Debe de Teclear un Folio: ","Aviso!", JOptionPane.WARNING_MESSAGE);
							return;		
					    }
			}else{
				 String fechaNull = cfecha.getDate()+"";
				   if(fechaNull.equals("null")){
						JOptionPane.showMessageDialog(null,"Necesita Selecionar una Fecha o la Fecha tecleada es Incorrecta","Mensaje",JOptionPane.WARNING_MESSAGE);
						return;
					   }else{
						   Obj_Usuario usuario = new Obj_Usuario().LeerSession();
						   
						   if(tipo_Reporte==3){
							   reporte = "Obj_Reporte_De_Cortes_Del_Dia_Para_Exportar.jrxml";
						   }else{
							   reporte = "Obj_Reporte_De_Cortes_Del_Dia.jrxml";
						   }
						   String fecha = new SimpleDateFormat("dd/MM/yyyy").format(cfecha.getDate());
							 comando = "exec sp_Reporte_De_Cortes_Del_Dia '"+usuario.getNombre_completo()+"','"+fecha+"'" ;
							 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
					   }
			}
		}
	};
	
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_De_Cortes().setVisible(true);
		}catch(Exception e){	}
	}
}
