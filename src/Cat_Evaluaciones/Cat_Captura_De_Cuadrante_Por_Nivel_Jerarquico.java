package Cat_Evaluaciones;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Obj_Principal.Componentes;

@SuppressWarnings("serial")
public class Cat_Captura_De_Cuadrante_Por_Nivel_Jerarquico extends Cat_Filtro_De_Cuadrante_De_Empleados_Dependientes{
	
	public Cat_Captura_De_Cuadrante_Por_Nivel_Jerarquico(){
		tabla.removeMouseListener(opListenerTable);
		tabla.addMouseListener(opAbrirCuadrante);
	}
	
	MouseListener opAbrirCuadrante = new MouseListener() {
		public void mouseReleased(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2){
    			int fila = tabla.getSelectedRow();
    			Object Nombre = tabla.getValueAt(fila,1).toString().trim();
    			dispose();
    			new Cat_Captura_Del_Cuadrante_Personal(new Componentes().getTextProcesa(String.valueOf(Nombre))).setVisible(true);
        	}
		}
	};

}
