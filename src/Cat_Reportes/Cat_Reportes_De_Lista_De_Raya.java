package Cat_Reportes;

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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import Cat_Lista_de_Raya.Cat_Filtro_De_Listas_De_Raya_Pasadas;
import Cat_Lista_de_Raya.Cat_Imprimir_Lista_De_Raya;
import Conexiones_SQL.Generacion_Reportes;
import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Reportes_De_Lista_De_Raya extends JDialog{
	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtFolio = new Componentes().text(new JTextField(), "Folio del Corte", 15, "String");
	
	JButton btnReporte_porfolio = new JButton("",new ImageIcon("imagen/hoja-de-calculo-excel-icono-5223-16.png"));
	JButton btnReporte_porfecha = new JButton("",new ImageIcon("imagen/orange-folder-saved-search-icone-8197-16.png"));
	JButton btnReporte_actual = new JButton("",new ImageIcon("imagen/plan-icono-5073-16.png"));
    JButton btnSeleccionLR =new JButton("",new ImageIcon ("imagen/Filter-List-icon16.png"));
	
	JButton btngenerar = new JButton("Generar",new ImageIcon("imagen/buscar.png"));
	
	int tipo_Reporte = 0;
	
	public Cat_Reportes_De_Lista_De_Raya(){
		setSize(305,330);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Reportes De Lista De Raya");
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagen/plan-icono-5073-16.png"));
		panel.setBorder(BorderFactory.createTitledBorder("Seleccione el Tipo de Reporte"));
	
		btnReporte_porfolio.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Lista De Raya Con Exportador A Excel </p></CENTER></FONT>" +
				"</html>");
		
		btnReporte_porfecha.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Listas De Raya Pasadas</p></CENTER></FONT>" +
				"</html>");	
		
		btnReporte_actual.setText(	"<html> <FONT FACE="+"arial"+" SIZE=3 COLOR=BLACk>" +
				"		<CENTER><p>Reporte De Lista De Raya Actual</p></CENTER></FONT>" +
				"</html>");	
		
		panel.add(btnReporte_porfolio).setBounds(20,25,260,40);
		panel.add(btnReporte_porfecha).setBounds(20,75,260,40);
		panel.add(btnReporte_actual).setBounds(20,125,260,40);
		
		panel.add(new JLabel("Folio:")).setBounds(30,180,200,20);		
		panel.add(txtFolio).setBounds(80,180,175,20);
		panel.add(btnSeleccionLR).setBounds(255,180,20,20);
		panel.add(btngenerar).setBounds(100,250,120,30);
	    
	    txtFolio.setEditable(false);
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
			btnSeleccionLR.setEnabled(false);
			txtFolio.setEditable(false);
			btngenerar.setEnabled(false);
			txtFolio.setText("");
			tipo_Reporte=1;
			new Cat_Imprimir_Lista_De_Raya().setVisible(true);
		}
	};
	
	ActionListener opReporte_Por_Fecha = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(true);
			btngenerar.setEnabled(true);
			btngenerar.setEnabled(true);
			btnSeleccionLR.setEnabled(true);
			tipo_Reporte=2;
			txtFolio.setText("");
			txtFolio.requestFocus();
		}
	};

	ActionListener opReporte_Actual = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			txtFolio.setEditable(false);
			btngenerar.setEnabled(false);
			btnSeleccionLR.setEnabled(false);
			txtFolio.setText("");
			Reporte_De_Lista_De_Raya_Actual();
		}
	};
	
	ActionListener opfiltroLR = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			 new Cat_Filtro_De_Listas_De_Raya_Pasadas(3).setVisible(true);
		}
	};
	
	String basedatos="2.26";
	String vista_previa_reporte="no";
	int vista_previa_de_ventana=0;
	String comando="";
	String reporte = "";
	
	ActionListener opGenerar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if(tipo_Reporte==2){
						if(!txtFolio.getText().equals("")){
							 reporte = "Obj_Reporte_De_Lista_de_Raya_Pasadas.jrxml";
							 comando = "exec sp_Reporte_De_Lista_De_Raya_Pasada "+Integer.valueOf(txtFolio.getText())  ;
							 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
					    }else{
							  JOptionPane.showMessageDialog(null, "El Campo Folio No Debe De Estar Vacio","Aviso", JOptionPane.ERROR_MESSAGE,new ImageIcon("Imagen/usuario-de-alerta-icono-4069-64.png"));
		                      return;
							}
		}
		}
	};
	
		public  void obtiene_lista_de_raya_selecionada(final Integer folio){
			 String	Foliorecibido = folio+"";
			 reporte = "Obj_Reporte_De_Lista_de_Raya_Pasadas.jrxml";
			 comando = "exec sp_Reporte_De_Lista_De_Raya_Pasada "+Foliorecibido  ;
			 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
			 txtFolio.setText(Foliorecibido);
			 return;	
		}
	
		public void Reporte_De_Lista_De_Raya_Actual() {
			 reporte = "Obj_Reporte_De_Lista_De_Raya_Actual.jrxml";
			 comando = "exec sp_Reporte_De_lista_De_Raya_Actual " ;
			 new Generacion_Reportes().Reporte(reporte, comando, basedatos, vista_previa_reporte,vista_previa_de_ventana);
		}

	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Reportes_De_Lista_De_Raya().setVisible(true);
		}catch(Exception e){	}
	}
}
