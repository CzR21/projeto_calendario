package services;

import java.io.IOException;
import java.sql.*;
import dao.BancoDados;
import dao.UsuarioDAO;
import entities.Usuario;
import java.util.List;

public class UsuarioService {

    public UsuarioService() {
    }

    public static Usuario buscarUsuarioPorId(int id) throws Exception {
        Connection con = BancoDados.conectar();
        try {
            Usuario usuario = new UsuarioDAO(con).buscarPorId(id);
            if (usuario != null) {
                return usuario;
            } else {
                throw new Exception("Usuário não encontrado");
            }
        } finally {
            BancoDados.desconectar();
        }
    }

    public static List<Usuario> buscarTodos(int id) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            return new UsuarioDAO(con).buscarTodos(id);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void cadastrarUsuario(Usuario usuario) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new UsuarioDAO(con).cadastrar(usuario);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void editarUsuario(Usuario usuario) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new UsuarioDAO(con).atualizar(usuario);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static void removerUsuario(int id) throws SQLException, IOException {
        Connection con = BancoDados.conectar();
        try {
            new UsuarioDAO(con).excluir(id);
        } finally {
            BancoDados.desconectar();
        }
    }

    public static Usuario logar(String email, String senha) throws Exception {
        Connection con = BancoDados.conectar();
        try {
            Usuario usuario = new UsuarioDAO(con).logar(email, senha);
            if (usuario != null) {
                return usuario;
            }

            throw new Exception("Email ou senha inválidos");

        } finally {
            BancoDados.desconectar();
        }
    }

}
