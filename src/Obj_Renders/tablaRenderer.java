package Obj_Renders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class tablaRenderer extends DefaultTableCellRenderer {
  
     private String tipo="text";
     private String alineacion="text";
     
//     tipo de letra: "Arial","Courier New","TimesRoman"
     private Font fuente;
     
     private JLabel label = new JLabel();
     private JCheckBox chb = new JCheckBox();
     
     private ImageIcon salida = new ImageIcon("imagen/Delete.png");
     private ImageIcon entrada = new ImageIcon("imagen/Aplicar.png");
     
     public tablaRenderer( String tipo ,String alineacionTexto,	String tipoDeLetra, String estilo, int tamanio)
     {
         this.tipo = tipo;
         this.alineacion = alineacionTexto;
         
         fuente(tipoDeLetra,estilo,tamanio);
     }
     
     public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column ){   
		
    	 if(row %2 == 0){
				this.setBackground(new java.awt.Color(177,177,190));	
		  }else{
		      this.setBackground(Color.white);
		  }
        
    	 if (selected) {  
        	 this.setBackground(new java.awt.Color(186,143,73));
         }

         if( tipo.toUpperCase().trim().equals("IMAGEN") || tipo.toUpperCase().trim().equals("IMAGENES") || tipo.toUpperCase().trim().equals("ICONO") || tipo.toUpperCase().trim().equals("ICONOS")){
        	
        	 label.setHorizontalAlignment(JLabel.CENTER);
        	 
             if( String.valueOf(value).equals("1")){
                 label.setIcon(entrada);
             }else {
                 label.setIcon(salida);
             }
             return label;
         }
         
         if( tipo.toUpperCase().trim().equals("VENTA")){
         	
				JLabel lbl = new JLabel(value == null? "": value.toString());
				
//				lbl.setFont(new Font("arial", Font.BOLD, 25));
				lbl.setFont(fuente);
				
				lbl.setOpaque(true); 
				lbl.setBackground(new java.awt.Color(182,211,255));
					
					if(selected){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(100,181,255));
					}
					
					lbl.setHorizontalAlignment(JLabel.CENTER);
        	 
             return lbl;
         }
         
         if(tipo.toUpperCase().trim().equals("CHB")){
				
				chb = new JCheckBox("",Boolean.parseBoolean(value.toString()));
				if(row%2==0){
					((JComponent) chb).setOpaque(true); 
					chb.setBackground(new java.awt.Color(177,177,190));	
				}
				if(table.getSelectedRow() == row){
					((JComponent) chb).setOpaque(true); 
					chb.setBackground(new java.awt.Color(186,143,73));
				}
				if(selected){
					((JComponent) chb).setOpaque(true); 
					chb.setBackground(new java.awt.Color(186,143,73));
				}
				((AbstractButton) chb).setHorizontalAlignment(SwingConstants.CENTER);	
				return chb;
			
         }else{
        	 
        	 this.alineacionOrizontal( alineacion );
             this.setText( value.toString() );
             this.setForeground( (selected)?new Color(255,255,255):new Color(32,17,32) ); 
             this.setFont(fuente);            
//             return this;
             
         }
         
         return this;
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