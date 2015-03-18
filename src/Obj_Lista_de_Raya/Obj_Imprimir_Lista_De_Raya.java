package Obj_Lista_de_Raya;

import Conexiones_SQL.GuardarSQL;

public class Obj_Imprimir_Lista_De_Raya {
	
	public Obj_Imprimir_Lista_De_Raya(){
		
	}
	public boolean Imprimir(){ return new GuardarSQL().lista_Imprimir(); }

}
