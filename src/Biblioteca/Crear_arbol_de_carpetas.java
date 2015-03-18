package Biblioteca;

import java.io.File;

public class Crear_arbol_de_carpetas {

//	folder.mkdir(); // esto crea la carpeta java, y requiere que exista la ruta
//	folder.mkdirs(); // esto crea la carpeta java, independientemente que exista el path completo, si no existe crea toda la ruta necesaria
	
//	if (!folder.isDirectory()) { 
//	// escribimos algo si existe el fichero 
//	System.out.println("b");
//}
//
//if (!folder.isFile()) { 
//	// escribimos algo si existe el fichero 
//	System.out.println("c");
//}
	
	public static void main(String[] args) {
//		optener SO
//		String so = System.getProperty("os.name");
//		System.out.println(so);
		
		String raiz = System.getProperty("user.home").substring(0,2);
		String barras = System.getProperty("file.separator")+System.getProperty("file.separator");
		
		String sPath = raiz + barras + "a.txt";
		System.out.println(sPath);
		
		File folder = new File("c:\\Concentrado_xml_pdf\\2014\\DICIEMBRE\\31\\0517");
		
		// escribimos algo si existe el fichero 
		if (!folder.exists()) { 
			folder.mkdirs();
		}

	}

}
