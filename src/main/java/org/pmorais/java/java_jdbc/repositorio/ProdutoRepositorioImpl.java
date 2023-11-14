package org.pmorais.java.java_jdbc.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.pmorais.java.java_jdbc.modelo.Categoria;
import org.pmorais.java.java_jdbc.modelo.Produto;
import org.pmorais.java.java_jdbc.util.ConexaoBaseDados;

public class ProdutoRepositorioImpl implements Repositorio<Produto>{

	private Connection getConnection() throws SQLException {
		return ConexaoBaseDados.getInstance();
	}
	
	@Override
	public List<Produto> findAll() {
		List<Produto> produtos = new ArrayList<>();
		try(Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT p.*, c.nome as categoria FROM produtos as p "
					+ "inner join categorias as c ON (p.categoria_id = c.id)")){
			
			while(rs.next()) {
				Categoria categoria = instanciarCategoria(rs);
				Produto p = instanciarProduto(rs, categoria);
				produtos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	@Override
	public Produto findById(Integer id) {
		Produto produto = new Produto();
		try(PreparedStatement stmt = getConnection().prepareStatement("SELECT p.*, c.nome as categoria FROM produtos as p "
				+ "inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Categoria categoria = instanciarCategoria(rs);
				produto = instanciarProduto(rs, categoria);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return produto;
	}

	@Override
	public void insert(Produto t) {
		String sql;
		
		if(t.getId() != null && t.getId() > 0) {
			sql = "UPDATE produtos SET nome = ?, preço = ?, categoria_id = ? WHERE id = ?";
		}
		else {
			sql = "INSERT INTO produtos (nome, preço, categoria_id, data_registro) VALUES (?, ?, ?, ?)";
		}
		try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, t.getNome());
			stmt.setInt(2, t.getPreco());
			stmt.setInt(3, t.getCategoria().getId());
			
			if(t.getId() != null && t.getId() > 0) {
				stmt.setInt(4, t.getId());
			}
			else {
				stmt.setDate(4, new Date(t.getDataRegistro().getTime()));
			}
			
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Integer id) {
		try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM produtos WHERE id = ?")){
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private Produto instanciarProduto(ResultSet rs, Categoria categoria) throws SQLException {
		return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getInt("preço"), rs.getDate("data_registro"), categoria);
	}
	
	private Categoria instanciarCategoria(ResultSet rs) throws SQLException {
		return new Categoria(rs.getInt("categoria_id"), rs.getString("categoria"));
	}

}
