package Obj_Checador;

import Conexiones_SQL.BuscarSQL;

public class Obj_Reloj_Sincronizado_Servidor {
	private int hora;
	private int minuto;
	private int segundo;
	
	public Obj_Reloj_Sincronizado_Servidor(){
		this.hora=0; this.minuto=0; this.segundo=0;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}
	
	public int[] get_hora_minuto_segundo(){
		return new BuscarSQL().hora_minut_segundo();
	}

}