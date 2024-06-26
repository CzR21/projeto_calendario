package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Convite;
import enums.TipoStatus;
import enums.TipoStatusConvite;
import java.util.ArrayList;
import java.util.List;

public class ConviteDAO {

    private Connection conn;

    public ConviteDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastrar(Convite convite) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO convite (id_usuario, id_compromisso, tp_status_convite, tp_status) VALUES (?, ?, ?, ?)");

            st.setInt(1, convite.getIdUsurio());
            st.setInt(2, convite.getIdCompromisso());
            st.setString(3, convite.getStatusConvite().name());
            st.setString(4, convite.getStatus().name());

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

    public Convite buscarPorId(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM convite WHERE id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Convite convite = new Convite();

                convite.setId(rs.getInt("id"));
                convite.setIdUsurio(rs.getInt("id_usuario"));
                convite.setIdCompromisso(rs.getInt("id_compromisso"));
                convite.setStatusConvite(TipoStatusConvite.valueOf(rs.getString("tp_status_convite")));
                convite.setStatus(TipoStatus.valueOf(rs.getString("tp_status")));

                return convite;
            }

            return null;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }

    public List<Convite> buscarPorIdUsuario(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM convite WHERE id_usuario = ? AND tp_status_convite = 'PENDENTE'");
            st.setInt(1, id);

            rs = st.executeQuery();

            List<Convite> convites = new ArrayList();

            if (rs.next()) {
                Convite convite = new Convite();

                convite.setId(rs.getInt("id"));
                convite.setIdUsurio(rs.getInt("id_usuario"));
                convite.setIdCompromisso(rs.getInt("id_compromisso"));
                convite.setStatusConvite(TipoStatusConvite.valueOf(rs.getString("tp_status_convite")));
                convite.setStatus(TipoStatus.valueOf(rs.getString("tp_status")));

                convites.add(convite);
            }

            return convites;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
            BancoDados.desconectar();
        }
    }

    public void atualizar(Convite convite) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE convite SET id_usuario = ?, id_compromisso = ?, tp_status_convite = ?, tp_status = ? WHERE id = ?");

            st.setInt(1, convite.getIdUsurio());
            st.setInt(2, convite.getIdCompromisso());
            st.setString(3, convite.getStatusConvite().name());
            st.setString(4, convite.getStatus().name());
            st.setInt(5, convite.getId());

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }

    public void excluir(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM convite WHERE id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
    }
}
