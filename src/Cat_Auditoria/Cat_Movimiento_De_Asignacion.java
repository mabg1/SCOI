package Cat_Auditoria;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Cat_Movimiento_De_Asignacion extends JDialog{

	Container cont = getContentPane();
	JLayeredPane panel = new JLayeredPane();
	
	JTextField txtEmpleado = new JTextField();
	JTextField txtAsignacion = new JTextField();
	
	JDateChooser fechaInicio = new JDateChooser();
	JDateChooser fechaFinal = new JDateChooser();
	
	JButton btnGuardar = new JButton("Guardar");
	JButton btnDesasignar = new JButton("Desasignar");
	JButton btnCancelar = new JButton("Cancelar");
	
	Border borderLine;
	public Cat_Movimiento_De_Asignacion(){
		this.setModal(true);
		this.setTitle("Rango De Asignacion");
		panel.setBorder(BorderFactory.createTitledBorder(borderLine,"Asinacion"));
		
		int x=15; int y=15;
		
		panel.add(new JLabel("Empleado: ")).setBounds(x, y, 70, 20);
		panel.add(txtEmpleado).setBounds(x+70, y, 320, 20);
		
		panel.add(new JLabel("Asignacion: ")).setBounds(x, y+=25, 70, 20);
		panel.add(txtAsignacion).setBounds(x+70, y, 320, 20);
		
		panel.add(new JLabel("De: ")).setBounds(x+140, y+=25, 30, 20);
		panel.add(fechaInicio).setBounds(x+170, y, 100, 20);
		panel.add(new JLabel("A: ")).setBounds(x+275, y, 30, 20);
		panel.add(fechaFinal).setBounds(x+290, y, 100, 20);
		
		panel.add(btnGuardar).setBounds(x+10, y+=35, 100, 20);
		panel.add(btnDesasignar).setBounds(x+140, y, 100, 20);
		panel.add(btnCancelar).setBounds(x+270, y, 100, 20);
		
		txtEmpleado.setEditable(false);
		txtAsignacion.setEditable(false);
		
		btnDesasignar.addActionListener(opDesasignar);
		btnCancelar.addActionListener(opCancelar);
		
//		try {
//			
//			Obj_Movimiento_De_Asignacion asignacion = new Obj_Movimiento_De_Asignacion().buscarAsignacion(folio, establecimiento);
//			
//			txtEmpleado.setText(asignacion.getEmpleado());
//			txtAsignacion.setText(asignacion.getAsignacion());
//			
//			Date fechaIn = new SimpleDateFormat("dd/MM/yyyy").parse(asignacion.getFechaIn());
//			fechaInicio.setDate(fechaIn);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		cont.add(panel);
		this.setSize(430, 180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	ActionListener opDesasignar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	ActionListener opCancelar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	};
	
	public String validaCampos(){
		String error="";
		
		String fechaIn = fechaInicio.getDate()+"";
		String fechaFin = fechaFinal.getDate()+"";
		
		if(fechaIn.equals("null")) error+="Fecha Inicial\n";
		if(fechaFin.equals("null")) error+="Fecha Final";
		
		return error;
	}
	
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Cat_Asignacion_De_Cajeros().setVisible(true);
		}catch(Exception e){
			System.err.println("Error :"+ e.getMessage());
		}
	}
}
