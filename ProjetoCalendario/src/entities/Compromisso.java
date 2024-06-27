package entities;

import java.util.Date;

import enums.TipoStatus;

public class Compromisso {

    private int id;
    private int idAgenda;
    private int idUsuario;
    private String titulo;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private String local;
    private String dataNotificacao;
    private TipoStatus status;
    private Agenda agenda;

    public Compromisso() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAgenda() {
        return idAgenda;
    }
    
    public Agenda getAgenda() {
        return agenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }
    
    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
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
