package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Compromisso;
import enums.TipoStatus;

public class CompromissoDAO {
	
	private Connection conn;

    public CompromissoDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastrar(Compromisso compromisso) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO compromisso (id_agenda, titulo, descricao, data_inicio, data_fim, local, data_notificacao, tp_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            st.setInt(1, compromisso.getIdAgenda());
            st.setString(2, compromisso.getTitulo());
            st.setString(3, compromisso.getDescricao());
            st.setString(4, compromisso.getDataIncio());
            st.setString(5, compromisso.getDataFim());
            st.setString(6, compromisso.getLocal());
            st.setString(7, compromisso.getDataNotificacao());
            st.setString(8, compromisso.getStatus().name());

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

    public Compromisso buscarPorId(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM compromisso WHERE id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Compromisso compromisso = new Compromisso();

                compromisso.setId(rs.getInt("id"));
                compromisso.setIdAgenda(rs.getInt("id_agenda"));
                compromisso.setTitulo(rs.getString("titulo"));
                compromisso.setDescricao(rs.getString("descricao"));
                compromisso.setDataIncio(rs.getString("data_inicio"));
                compromisso.setDataFim(rs.getString("data_fim"));
                compromisso.setLocal(rs.getString("local"));
                compromisso.setDataNotificacao(rs.getString("data_notificacao"));
                compromisso.setStatus(TipoStatus.valueOf(rs.getString("tp_status")));

                return compromisso;
            }

            return null;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }

    public void atualizar(Compromisso compromisso) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE compromisso SET id_agenda = ?, titulo = ?, descricao = ?, data_inicio = ?, data_fim = ?, local = ?, data_notificacao = ?, tp_status = ? WHERE id = ?");

            st.setInt(1, compromisso.getIdAgenda());
            st.setString(2, compromisso.getTitulo());
            st.setString(3, compromisso.getDescricao());
            st.setString(4, compromisso.getDataIncio());
            st.setString(5, compromisso.getDataFim());
            st.setString(6, compromisso.getLocal());
            st.setString(7, compromisso.getDataNotificacao());
            st.setString(8, compromisso.getStatus().name());
            st.setInt(9, compromisso.getId());

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

    public void excluir(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM compromisso WHERE id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }
}
