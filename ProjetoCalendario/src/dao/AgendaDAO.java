package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Agenda;
import enums.TipoStatus;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {

    private Connection conn;

    public AgendaDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastrar(Agenda agenda) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO agenda (id_usuario, nome, descricao, tp_status) VALUES (?, ?, ?, ?)");

            st.setInt(1, agenda.getIdUsuario());
            st.setString(2, agenda.getNome());
            st.setString(3, agenda.getDescricao());
            st.setString(4, agenda.getStatus().name());

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

    public Agenda buscarPorId(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM agenda WHERE id = ?  and tp_status = 'ATIVO'");
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Agenda agenda = new Agenda();

                agenda.setId(rs.getInt("id"));
                agenda.setIdUsuario(rs.getInt("id_usuario"));
                agenda.setNome(rs.getString("nome"));
                agenda.setDescricao(rs.getString("descricao"));
                agenda.setStatus(TipoStatus.valueOf(rs.getString("tp_status")));

                return agenda;
            }

            return null;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }

    public void atualizar(Agenda agenda) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE agenda SET id_usuario = ?, nome = ?, descricao = ?, tp_status = ? WHERE id = ?");

            st.setInt(1, agenda.getIdUsuario());
            st.setString(2, agenda.getNome());
            st.setString(3, agenda.getDescricao());
            st.setString(4, agenda.getStatus().name());
            st.setInt(5, agenda.getId());

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

    public void excluir(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM agenda WHERE id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

    public List<Agenda> buscarTodos(int idUsuario) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM agenda WHERE id_usuario = ?  and tp_status = 'ATIVO'");
            st.setInt(1, idUsuario);
            rs = st.executeQuery();
            List<Agenda> agendas = new ArrayList();

            while (rs.next()) {
                Agenda agenda = new Agenda();

                agenda.setId(rs.getInt("id"));
                agenda.setIdUsuario(rs.getInt("id_usuario"));
                agenda.setNome(rs.getString("nome"));
                agenda.setDescricao(rs.getString("descricao"));
                agenda.setStatus(TipoStatus.valueOf(rs.getString("tp_status")));

                agendas.add(agenda);
            }

            return agendas;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }

}
