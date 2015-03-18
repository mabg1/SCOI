package Cat_Auditoria;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import Obj_Auditoria.Obj_Movimiento_De_Asignacion;
import Obj_Lista_de_Raya.Obj_Establecimiento;
import Obj_Principal.Componentes;


		@SuppressWarnings("serial")
		public class Cat_Seleccion_De_Asignacion extends JFrame{
			
			Container cont = getContentPane();
			JLayeredPane panel = new JLayeredPane();
			
			JTextField txtAsignacion = new Componentes().text(new JTextField(), "Numero de Asignacion", 15, "Int");
			
			JButton btnBuscar = new JButton(new ImageIcon("Iconos/zoom_icon&16.png"));
			
			String establecimiento[] = new Obj_Establecimiento().Combo_Establecimiento();
			@SuppressWarnings({ "rawtypes", "unchecked" })
			JComboBox cmbEstablecimiento = new JComboBox(establecimiento);
			
			Border blackline;
			public Cat_Seleccion_De_Asignacion(){
				
				this.setTitle("Seleccion de Asignacion");
				
				blackline = BorderFactory.createLineBorder(new java.awt.Color(105,105,105));
				this.panel.setBorder(BorderFactory.createTitledBorder(blackline, "Filtrar"));
				
				int x=20; int y=30; int ancho=110;
				
				
				panel.add(new JLabel("Establecimiento: ")).setBounds( x, y, 100, 20);
				panel.add(cmbEstablecimiento).setBounds( x+90, y, 170, 20);
				
				panel.add(new JLabel("Asignacion: ")).setBounds( x+23, y+=40, ancho, 20);
				panel.add(txtAsignacion).setBounds( x+93, y, ancho+20, 20);
				panel.add(btnBuscar).setBounds( x+230, y, 30, 20);
				
				btnBuscar.addActionListener(opBuscar);
				
				cont.add(panel);
				
				this.setSize(305,140);
				this.setResizable(false);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
			
			ActionListener opBuscar = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					if(cmbEstablecimiento.getSelectedIndex() == 0){
							JOptionPane.showMessageDialog(null, "Seleccione El Establecimiento", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							return;
					}else{
							if(txtAsignacion.getText().equals("")){
								
//							abrir filtri de asignaciones

							}else{
								
								@SuppressWarnings("unused")
								Obj_Movimiento_De_Asignacion asignacion = new Obj_Movimiento_De_Asignacion();
								System.out.println(txtAsignacion.getText().substring(0,2));
								
							}
					}
				}
			};
			
			public static void main(String args[]){
				try{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new Cat_Seleccion_De_Asignacion().setVisible(true);
				}catch(Exception e){
					System.err.println("Error :"+ e.getMessage());
				}
			}
}
