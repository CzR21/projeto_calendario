package entities;

import java.time.LocalDate;
import enums.TipoStatus;

public class Usuario {

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", dataNascimento=" + dataNascimento
				+ ", genero=" + genero + ", foto=" + foto + ", nomeUsuario=" + nomeUsuario + ", senha=" + senha
				+ ", status=" + status + "]";
	}

	private int id;
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	private String genero;
	private String foto;
	private String nomeUsuario;
	private String senha;
	private TipoStatus status;
	
	public Usuario() {
		this.status = TipoStatus.ATIVO;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public TipoStatus getStatus() {
		return status;
	}
	
	public void setStatus(TipoStatus status) {
		this.status = status;
	}
}
