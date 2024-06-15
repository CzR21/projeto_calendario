package entities;

import enums.*;

public class Convite {

	private int id;
	private int idUsurio;
	private int idCompromisso;
	private TipoStatusConvite statusConvite;
	private TipoStatus status;
	
	public Convite() { }
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdUsurio() {
		return idUsurio;
	}
	
	public void setIdUsurio(int idUsurio) {
		this.idUsurio = idUsurio;
	}
	
	public int getIdCompromisso() {
		return idCompromisso;
	}
	
	public void setIdCompromisso(int idCompromisso) {
		this.idCompromisso = idCompromisso;
	}
	
	public TipoStatusConvite getStatusConvite() {
		return statusConvite;
	}
	
	public void setStatusConvite(TipoStatusConvite statusConvite) {
		this.statusConvite = statusConvite;
	}
	
	public TipoStatus getStatus() {
		return status;
	}
	
	public void setStatus(TipoStatus status) {
		this.status = status;
	}
}
