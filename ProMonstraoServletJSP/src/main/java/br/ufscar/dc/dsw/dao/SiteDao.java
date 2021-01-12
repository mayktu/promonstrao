package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Site;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDao extends GenericDAO {

    public void insert(Site site, String senha) {

        String sqlUsuario = "INSERT INTO usuario (email, senha, papel) VALUES (?, ?, ?)";
        String sqlSite = "INSERT INTO site (id, email, nome, endereco, telefone) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = this.getConnection();


            try (PreparedStatement statementUsuario = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement statementSite = conn.prepareStatement(sqlSite)) {

                conn.setAutoCommit(false);

                statementUsuario.setString(1, site.getEmail());
                statementUsuario.setString(2, senha);
                statementUsuario.setString(3, "SITE");
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

                statementSite.setLong(1, id);
                statementSite.setString(2, site.getEmail());
                statementSite.setString(3, site.getNome());
                statementSite.setString(4, site.getEndereco());
                statementSite.setString(5, site.getTelefone());
                statementSite.executeUpdate();

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

    public List<Site> getAll() {
        List<Site> lista = new ArrayList<>();

        String sql = "SELECT * from site";
        try (Connection conn = this.getConnection();
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String endereco = resultSet.getString("endereco");
                String telefone = resultSet.getString("telefone");
                Site site = new Site(id, email, nome, endereco, telefone);
                lista.add(site);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void delete(long id) {
        String sql = "DELETE FROM usuario where id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Site getById(Long id) {
        Site site = null;

        String sql = "SELECT * from site WHERE id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String endereco = resultSet.getString("endereco");
                String telefone = resultSet.getString("telefone");

                site = new Site(id, email, nome, endereco, telefone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return site;
    }

    public Site getByEmail(String email) {
        Site site = null;

        String sql = "SELECT * from site WHERE email = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                String telefone = resultSet.getString("telefone");

                site = new Site(id, email, nome, endereco, telefone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return site;
    }

    public void update(Site site) {
        String sql = "UPDATE site SET nome = ?, endereco = ?, telefone = ? WHERE id = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, site.getNome());
            statement.setString(2, site.getEndereco());
            statement.setString(3, site.getTelefone());
            statement.setLong(4, site.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
