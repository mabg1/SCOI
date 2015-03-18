package Obj_Renders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class CaveceraTablaRenderer extends DefaultTableCellRenderer {
	
	Color background;
	Color foreground;
	
	Font fuente = null;
	
	private String alineacion="text";
	
	public CaveceraTablaRenderer (Color background, Color foreground, String alineacionTexto,	String tipoDeLetra, String estilo, int tamanio) {
		
		super();
		
		this.background = background;
		this.foreground = foreground;
		
		fuente(tipoDeLetra,estilo,tamanio);
		
		alineacion = alineacionTexto;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
		
//		this.setFont(new Font("arial", Font.BOLD, 25));
		
		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		comp.setBackground(background);
		comp.setForeground(foreground);
		comp.setFont(fuente);
		
		this.alineacionOrizontal( alineacion );
		
		return comp;
	
	}
	
	private void alineacionOrizontal(String alinear) {
		switch(alinear){
			case "centro":	this.setHorizontalAlignment(JLabel.CENTER); break;
			case "derecha":	this.setHorizontalAlignment(JLabel.RIGHT); break;
			default:		this.setHorizontalAlignment(JLabel.LEFT); break;//izquierda
		}
	}
	
	private void fuente(String tipografia,String apariencia,int tamanio) {
		switch(apariencia){
			case "negrita":	fuente = new Font( tipografia,Font.BOLD ,tamanio ); break;
			case "cursiva":	fuente = new Font( tipografia,Font.ITALIC ,tamanio ); break;
			default:		fuente = new Font( tipografia,Font.PLAIN ,tamanio ); break;//normal
		}
	}
}