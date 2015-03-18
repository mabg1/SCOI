package Obj_Principal;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

import javax.swing.*;

public class Componentes {
	
	public static void main(String args[]){
		File f = new File(System.getProperty("user.dir")+"/bin");
		File[] ficheros = f.listFiles();
		for(File fich : ficheros){
			if(fich.isDirectory() && !fich.getName().startsWith(".") && fich.getName().startsWith("Cat")){
				System.out.println(fich.getName());
			}
		}
	}
	
	public void resetComponents(JLayeredPane panel){
		for(Component comp : panel.getComponents()){
			if(comp instanceof JTextField){
				((JTextField) comp).setText("");
				((JTextField) comp).setEditable(true);
			}
			if(comp instanceof JCheckBox)
				((JCheckBox) comp).setSelected(true);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public void editComponents(String className){
		try {
			Class instance = Class.forName(className);
			Object instanceObject;
			instanceObject = instance.newInstance();
			
			if(instanceObject.getClass().getSuperclass().getName().length() > 0){
				Class extensions = Class.forName(instanceObject.getClass().getSuperclass().getName());
				Object extensionObjects;
				extensionObjects = extensions.newInstance();
				
				for(Field campos : ((Component) extensionObjects).getClass().getDeclaredFields())
					System.out.println("Campos: "+ campos.getName());
			}
			for(Field campos : ((Component) instanceObject).getClass().getDeclaredFields())
				System.out.println("Campos: "+ campos.getName());
			
		} catch (ClassNotFoundException e) {
			System.err.println("Error ClassNotFoundException: " + e.getMessage());
		}catch (InstantiationException e) {
			System.err.println("Error InstantiationException: " + e.getMessage());
		} catch (IllegalAccessException e) {
			System.err.println("Error IllegalAccessException: " + e.getMessage());
		}
	}
	
	public final JPasswordField textPassword(final JPasswordField tmp, final String caption, final int longitud){
		tmp.addKeyListener(new KeyListener() {
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) {
				char character = e.getKeyChar();
				if((int) character == 32)
					e.consume();
				if(getTextProcesa(tmp.getText()).length() >= longitud)
					e.consume();
			}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
		});
		tmp.setToolTipText(caption);
		return tmp;
	}
	
	public final JTextArea textArea(final JTextArea tmp, final String caption, final int longitud){
		tmp.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(getTextProcesa(tmp.getText()).length() >= longitud)
					e.consume();
			}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
		});
		tmp.setToolTipText(caption);
		return tmp;
	}
	
	public final JTextField text(final JTextField tmp, final String caption, final int longitud, final String tipo){
			tmp.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {
					char caracter = e.getKeyChar();
					
					switch (tipo) {
						case "String" :
							if(getTextProcesa(tmp.getText()).length() >= longitud)
								e.consume();
							break;
						case "Int" :
							if(getTextProcesa(tmp.getText()).length() >= longitud)
								e.consume();
							if(((caracter < '0') ||
							        (caracter > '9')) &&
							        (caracter != KeyEvent.VK_BACK_SPACE)){
							    e.consume(); 
							}
							break;
						case "Double" :
							if(getTextProcesa(tmp.getText()).length() >= longitud)
								e.consume();
						    if(((caracter < '0') ||	
						    	(caracter > '9')) && 
						    	(caracter != '.' )){
						    	e.consume();
						    }
						    	
						    if (caracter==KeyEvent.VK_PERIOD)
								if (tmp.getText().indexOf(".")>-1) 
									e.consume();
					
							break;
					}
								
				}
				public void keyReleased(KeyEvent e) {}
				public void keyPressed(KeyEvent e) {}
			});
		
		tmp.setToolTipText(caption);
		return tmp;
	}
	
	public JCheckBox check(final JCheckBox tmp, final String nombre, final boolean select, final String caption){
		tmp.setText(nombre);
			tmp.setSelected(select);
				tmp.setToolTipText(caption);
		return tmp;
	}
	public String getTextProcesa(String texto) {
		StringTokenizer tokens = new StringTokenizer(texto);
	    texto = "";
	    while(tokens.hasMoreTokens())
	    	texto += " "+tokens.nextToken();

	    return texto.toString().trim().toUpperCase();
	}
	
	public String getTextProcesaClean(String input) {
	    String original = "·‡‰ÈËÎÌÏÔÛÚˆ˙˘uÒ¡¿ƒ…»ÀÕÃœ”“÷⁄Ÿ‹—Á«";
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	    
	    for(int i=0; i<original.length(); i++)
	    	input = input.replace(original.charAt(i), ascii.charAt(i));

	    return input;
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	public String existPackageFile(String root){
		try{
			Class instance = Class.forName(root);
			return root;
		}catch(ClassNotFoundException e){
			return "";
		}
	}
	@SuppressWarnings({ "rawtypes", "unused" })
	public String classExiste(String nombre){
		try{
			String claseRoot = "";
			File f = new File(System.getProperty("user.dir")+"/bin");
			File[] ficheros = f.listFiles();
			for(File fich : ficheros){
				if(fich.isDirectory() && !fich.getName().startsWith(".") && fich.getName().startsWith("Cat")){
					String tmp = fich.getName();
					tmp = getTextProcesaClean(tmp+".Cat "+ nombre);
					tmp = tmp.replaceAll(" ", "_");
					if(existPackageFile(tmp).length() > 0)
						claseRoot = tmp;
				}
			}
					
			Class instance = Class.forName(claseRoot);
			return claseRoot;
		}catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null, "No se ha creado la clase [" + nombre + "] Error en Obj_Principal.Componentes.classExiste  ", "Avisa al Administrador", JOptionPane.INFORMATION_MESSAGE);
			return "";
		}
	}
}
