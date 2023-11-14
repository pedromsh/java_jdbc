package org.pmorais.java.java_jdbc.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM produtos")){
			
			while(rs.next()) {
				Produto p = instanciarProduto(rs);
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
		try(PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM produtos WHERE id = ?")){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				produto = instanciarProduto(rs);
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
			sql = "UPDATE produtos SET nome = ?, preço = ? WHERE id = ?";
		}
		else {
			sql = "INSERT INTO produtos (nome, preço, data_registro) VALUES (?, ?, ?)";
		}
		try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, t.getNome());
			stmt.setInt(2, t.getPreco());
			
			if(t.getId() != null && t.getId() > 0) {
				stmt.setInt(3, t.getId());
			}
			else {
				stmt.setDate(3, new Date(t.getDataRegistro().getTime()));
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
	
	private Produto instanciarProduto(ResultSet rs) throws SQLException {
		return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getInt("preço"), rs.getDate("data_registro"));
	}

}
