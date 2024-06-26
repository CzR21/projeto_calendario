package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.AgendaDAO;
import dao.BancoDados;
import entities.Agenda;
import java.util.List;

public class AgendaService {

    public AgendaService() {
    }

    public static Agenda buscarAgendaPorId(int id) throws Exception {
        Connection con = BancoDados.conectar();
        try {
            Agenda agenda = new AgendaDAO(con).buscarPorId(id);
            if (agenda != null) {
                return agenda;
            } else {
                throw new Exception("Agenda não encontrada");
            }
        } finally {
            BancoDados.desconectar();
        }
    }

    public static List<Agenda> buscarAgendasPorIdUsuario(int id) throws SQLException, Exception {
        Connection con = BancoDados.conectar();
        try {
            List<Agenda> agendas = new AgendaDAO(con).buscarTodos(id);
            return agendas;
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void cadastrarAgenda(Agenda agenda) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new AgendaDAO(con).cadastrar(agenda);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void editarAgenda(Agenda agenda) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new AgendaDAO(con).atualizar(agenda);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void removerAgenda(int id) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new AgendaDAO(con).excluir(id);
        } finally {
            BancoDados.desconectar();
        }
    }
}
