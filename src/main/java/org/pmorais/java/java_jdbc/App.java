package org.pmorais.java.java_jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.pmorais.java.java_jdbc.modelo.Categoria;
import org.pmorais.java.java_jdbc.modelo.Produto;
import org.pmorais.java.java_jdbc.repositorio.ProdutoRepositorioImpl;
import org.pmorais.java.java_jdbc.repositorio.Repositorio;
import org.pmorais.java.java_jdbc.util.ConexaoBaseDados;

public class App 
{
    public static void main( String[] args ) {
        try (Connection conn = ConexaoBaseDados.getInstance()) {
        	Repositorio<Produto> repositorio = new ProdutoRepositorioImpl();
        	System.out.println("====== Ler ======");
        	repositorio.findAll().forEach(System.out::println);
        	
        	System.out.println("====== Ler por id ======");
        	System.out.println(repositorio.findById(2));
        	
        	System.out.println("====== Inserir novo produto ======");
        	Produto produto = new Produto();
        	produto.setNome("notebook gamer acer nitro 5");
        	produto.setPreco(5000);
        	produto.setDataRegistro(new Date());
        	Categoria categoria = new Categoria();
        	categoria.setId(2);
        	produto.setCategoria(categoria);
       	    repositorio.insert(produto);
        	System.out.println("Produto inserido com sucesso");
        	repositorio.findAll().forEach(System.out::println);
        	
        	System.out.println("====== Atualizar produto ======");
        	produto.setId(3);
        	produto.setNome("Teclado Corsair K95 mecânico");
        	produto.setPreco(800);
        	categoria.setId(4);
        	produto.setCategoria(categoria);
        	repositorio.insert(produto);
        	System.out.println("Produto atualizado com sucesso");
        	repositorio.findAll().forEach(System.out::println);
        	
        	System.out.println("====== Deletar produto ======");
        	repositorio.delete(4);
        	System.out.println("Excluído com sucesso");
        	repositorio.findAll().forEach(System.out::println);
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
    }
}
