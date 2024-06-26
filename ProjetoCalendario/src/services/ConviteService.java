package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.BancoDados;
import dao.ConviteDAO;
import entities.Convite;
import enums.TipoStatusConvite;
import java.util.List;

public class ConviteService {

    public ConviteService() {
    }

    public static Convite buscarConvitePorId(int id) throws Exception {
        Connection con = BancoDados.conectar();
        try {
            Convite convite = new ConviteDAO(con).buscarPorId(id);
            if (convite != null) {
                return convite;
            } else {
                throw new Exception("Convite não encontrado");
            }
        } finally {
            BancoDados.desconectar();
        }
    }

    public static List<Convite> buscarConvitePorIdUsuario(int id) throws Exception {
        Connection con = BancoDados.conectar();
        try {
            return new ConviteDAO(con).buscarPorIdUsuario(id);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void cadastrarConvite(Convite convite) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new ConviteDAO(con).cadastrar(convite);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void editarConvite(Convite convite) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new ConviteDAO(con).atualizar(convite);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void removerConvite(int id) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new ConviteDAO(con).excluir(id);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void aceitarConvite(Convite convite) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            convite.setStatusConvite(TipoStatusConvite.ACEITO);
            new ConviteDAO(con).atualizar(convite);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void recusarConvite(Convite convite) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            convite.setStatusConvite(TipoStatusConvite.RECUSADO);
            new ConviteDAO(con).atualizar(convite);
        } finally {
            BancoDados.desconectar();
        }
    }
}
