package Obj_Auditoria;

public class Obj_Ticket_De_Abono_De_Clientes {

	String fecha;
	String establecimiento;
	String ticket;
	String cajero;
	String cliente;
	float abono;
	
	public Obj_Ticket_De_Abono_De_Clientes(){
		this.fecha= "";		this.establecimiento="";	this.ticket="";		this.cajero="";	
		this.cliente="";	this.abono=0;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getCajero() {
		return cajero;
	}

	public void setCajero(String cajero) {
		this.cajero = cajero;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public float getAbono() {
		return abono;
	}

	public void setAbono(float abono) {
		this.abono = abono;
	}
	
}
