package Cat_Reportes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;
import Cat_Lista_de_Raya.Cat_Filtro_De_Listas_De_Raya_Pasadas;
import Conexiones_SQL.Generacion_Reportes;
import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Reportes_De_Fuente_De_Sodas extends JFrame{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio del Corte", 15, "String");
	
	JButton btnReporte_porfolio = new JButton("",new ImageIcon("imagen/fast-food-icon16.png"));
	JButton btnReporte_porfecha = new JButton("",new ImageIcon("imagen/Calendar.png"));
	JButton btnReporte_actual = new JButton("",new ImageIcon("imagen/plan-icono-5073-16.png"));
    JButton btnSeleccionLR =new JButton("",new ImageIcon ("imagen/Filter-List-icon16.png"));
	JButton btngenerar = new JButton("Generar",new ImageIcon("imagen/buscar.png"));
	JDateChooser cfecha = new JDateChooser();
	
	int tipo_Reporte = 0;
	
	public Cat_Reportes_De_Fuente_De_Sodas(){
		setSize(305,330);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Reportes De Fuente De Sodas");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/fast-food-icon32.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Seleccione el Tipo de Reporte"));
	
		btnReporte_porfolio.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Fuente De Sodas De Lista De Raya Por Folio </p></CENTER></FONT>" +
				"</html>");
		
		btnReporte_porfecha.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Fuente De Sodas Sin Cobro Por Fecha </p></CENTER></FONT>" +
				"</html>");	
		
		btnReporte_actual.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Fuente De Sodas De Lista De Raya Actual </p></CENTER></FONT>" +
				"</html>");	
		
		panel.add(btnReporte_porfolio).setBounds(20,25,260,40);
		panel.add(btnReporte_porfecha).setBounds(20,75,260,40);
		panel.add(btnReporte_actual).setBounds(20,125,260,40);
		
		panel.add(new JLabel("Folio:")).setBounds(30,180,200,20);		
		panel.add(txtFolio).setBounds(80,180,175,20);
		panel.add(btnSeleccionLR).setBounds(255,180,20,20);
		panel.add(new JLabel("Fecha:")).setBounds(30,220,200,20);
		panel.add(cfecha).setBounds(80,220,195,20);
		panel.add(btngenerar).setBounds(100,250,120,30);
	    
	    txtFolio.setEditable(false);
	    cfecha.setEnabled(false);
	    btngenerar.setEnabled(false);
	    btnSeleccionLR.setEnabled(false);
	    
		cont.add(panel);
		btngenerar.addActionListener(opGenerar);
		btnReporte_porfolio.addActionListener(opReporte_Por_Folio);
		btnReporte_porfecha.addActionListener(opReporte_Por_Fecha);
		btnReporte_actual.addActionListener(opReporte_Actual);
		btnSeleccionLR.addActionListener(opfiltroLR);
		
     	///filtro de Listas de Raya
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "buscarLR");
          getRootPane().getActionMap().put("buscarLR", new AbstractAction(){
         public void actionPerformed(ActionEvent e)
         {                 	    btnSeleccionLR.doClick();
       	    }
     });
	}
	
	ActionListener opReporte_Por_Folio = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(true);
			cfecha.setEnabled(false);
			btngenerar.setEnabled(true);
			btnSeleccionLR.setEnabled(true);
			tipo_Reporte=1;
			cfecha.setDate(null);
			txtFolio.requestFocus();
		}
	};
	
	ActionListener opReporte_Por_Fecha = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(false);
			cfecha.setEnabled(true);
			btngenerar.setEnabled(true);
			btnSeleccionLR.setEnabled(false);
			tipo_Reporte=2;
			txtFolio.setText("");
		}
	};

	ActionListener opReporte_Actual = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(false);
			cfecha.setEnabled(false);
			btngenerar.setEnabled(false);
			btnSeleccionLR.setEnabled(false);
			txtFolio.setText("");
			cfecha.setDate(null);
			Reporte_De_Fuente_De_Sodas();
		}
	};
	
	ActionListener opfiltroLR = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			 new Cat_Filtro_De_Listas_De_Raya_Pasadas(1).setVisible(true);
		}
	};
	
	public String validar_fechas(){
		String error = "";
		@SuppressWarnings("unused")
		String fechafinalNull = cfecha.getDate()+"";
	    if(cfecha.equals("null"))error+= "Fecha\n";
		return error;
	}
	
	String basedatos="2.26";
	String vista_previa_reporte="no";
	int vista_previa_de_ventana=0;
	String comando="";
	String reporte = "";
	
	public  void obtiene_lista_de_raya_selecionada(final Integer folio){
	     String	Foliorecibido = folio+"";
		 comando = "exec sp_Reporte_De_Fuente_De_Sodas_De_Lista_De_Raya_Por_Folio "+Foliorecibido  ;
		 reporte="Obj_Reporte_De_Fuente_De_Sodas.jrxml";
		 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
			txtFolio.setText(Foliorecibido);
			return;	
	}
	
	ActionListener opGenerar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(tipo_Reporte==1){
						if(!txtFolio.getText().equals("")){
							 comando ="exec sp_Reporte_De_Fuente_De_Sodas_De_Lista_De_Raya_Por_Folio "+Integer.valueOf(txtFolio.getText());
							 reporte="Obj_Reporte_De_Fuente_De_Sodas.jrxml";
							 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
						}else{
							JOptionPane.showMessageDialog(null,"Debes de Teclear Un Folio de Lista de Raya \n O Seleccionarla de la Lista  Siguiente ","Aviso!", JOptionPane.WARNING_MESSAGE);
							 new Cat_Filtro_De_Listas_De_Raya_Pasadas(1).setVisible(true);
							return;		
					    }
			}else{
				 String fechaNull = cfecha.getDate()+"";
				   if(fechaNull.equals("null")){
						JOptionPane.showMessageDialog(null,"Necesita Selecionar una Fecha o la Fecha tecleada es Incorrecta","Mensaje",JOptionPane.WARNING_MESSAGE);
						return;
					   }else{
						     String fecha = new SimpleDateFormat("dd/MM/yyyy").format(cfecha.getDate());
							 comando = "exec sp_Reporte_De_Fuente_De_Sodas_Sin_Cobro_En_lista_De_Raya'"+fecha+"'" ;
							 reporte="Obj_Reporte_De_Fuente_De_Sodas_de_Empleados_Sin_Cobro_En_Listas_de_Raya.jrxml";
							 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
					   }
			}
		}
	};
	
	
		public void Reporte_De_Fuente_De_Sodas() {
		 comando = "exec sp_Reporte_De_Fuente_De_Sodas_De_Lista_De_Raya_Actual " ;
		 reporte="Obj_Reporte_De_Fuente_De_Sodas.jrxml";
		 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
		}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_De_Fuente_De_Sodas().setVisible(true);
		}catch(Exception e){	}
	}
}
