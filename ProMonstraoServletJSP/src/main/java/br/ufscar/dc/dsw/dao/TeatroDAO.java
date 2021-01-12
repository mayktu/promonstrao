package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Teatro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeatroDAO extends GenericDAO {

    public void insert(Teatro teatro, String senha) {
        String sqlUsuario = "INSERT INTO usuario (email, senha, papel) VALUES (?, ?, ?)";
        String sqlTeatro = "INSERT INTO teatro (id, email, nome, cnpj, cidade) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = this.getConnection();


            try (PreparedStatement statementUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement statementTeatro = conn.prepareStatement(sqlTeatro)) {

                conn.setAutoCommit(false);

                statementUsuario.setString(1, teatro.getEmail());
                statementUsuario.setString(2, senha);
                statementUsuario.setString(3, "TEATRO");
                statementUsuario.executeUpdate();

                ResultSet rs = statementUsuario.getGeneratedKeys();
                long id;
                if (rs.next()) {
                    id = rs.getLong(1);
                } else {
                    conn.rollback();
                    conn.setAutoCommit(true);
                    conn.close();
                    return;
                }

                statementTeatro.setLong(1, id);
                statementTeatro.setString(2, teatro.getEmail());
                statementTeatro.setString(3, teatro.getNome());
                statementTeatro.setString(4, teatro.getCnpj());
                statementTeatro.setString(5, teatro.getCidade());
                statementTeatro.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    throw new RuntimeException(e2);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public List<Teatro> getAll() {
        List<Teatro> lista = new ArrayList<>();

        String sql = "SELECT * from teatro";
        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                Teatro teatro = new Teatro(id, email, nome, cnpj, cidade);
                lista.add(teatro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Teatro getById(Long id) {
        Teatro teatro = null;

        String sql = "SELECT * from teatro WHERE id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");

                teatro = new Teatro(id, email, nome, cnpj, cidade);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teatro;
    }

    public Teatro getByEmail(String email) {
        Teatro teatro = null;

        String sql = "SELECT * from teatro WHERE email = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");

                teatro = new Teatro(id, email, nome, cnpj, cidade);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teatro;
    }

    public void update(Teatro teatro) {
        String sql = "UPDATE teatro SET nome = ?, cnpj = ?, cidade = ? WHERE id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, teatro.getNome());
            statement.setString(2, teatro.getCnpj());
            statement.setString(3, teatro.getCidade());
            statement.setLong(4, teatro.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM usuario where id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Teatro> getAllOrderByCidade() {
        List<Teatro> lista = new ArrayList<>();

        String sql = "SELECT * from teatro ORDER BY cidade";
        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                Teatro teatro = new Teatro(id, email, nome, cnpj, cidade);
                lista.add(teatro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
