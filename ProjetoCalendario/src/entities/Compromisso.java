package entities;

import enums.TipoStatus;

public class Compromisso {

	private int id;
	private int idAgenda;
	private String titulo;
	private String descricao;
	private String dataIncio;
	private String dataFim;
	private String local;
	private String dataNotificacao;
	private TipoStatus status;
	
	public Compromisso() { }
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdAgenda() {
		return idAgenda;
	}
	
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDataIncio() {
		return dataIncio;
	}
	
	public void setDataIncio(String dataIncio) {
		this.dataIncio = dataIncio;
	}
	
	public String getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	public String getDataNotificacao() {
		return dataNotificacao;
	}
	
	public void setDataNotificacao(String dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}
	
	public TipoStatus getStatus() {
		return status;
	}
	
	public void setStatus(TipoStatus status) {
		this.status = status;
	}
}
