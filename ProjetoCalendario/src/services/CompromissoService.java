package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.CompromissoDAO;
import entities.Compromisso;

public class CompromissoService {

	public CompromissoService() { }

    public static Compromisso buscarCompromissoPorId(int id) throws Exception {
        Connection con = BancoDados.conectar();
        try {
            Compromisso compromisso = new CompromissoDAO(con).buscarPorId(id);
            if (compromisso != null) {
                return compromisso;
            } else {
                throw new Exception("Compromisso não encontrado");
            }
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void cadastrarCompromisso(Compromisso compromisso) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new CompromissoDAO(con).cadastrar(compromisso);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void editarCompromisso(Compromisso compromisso) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new CompromissoDAO(con).atualizar(compromisso);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void removerCompromisso(int id) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new CompromissoDAO(con).excluir(id);
        } finally {
            BancoDados.desconectar();
        }
    }
	
}
