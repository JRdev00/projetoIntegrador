/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bancaria;


import classesEmodelos.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Eduardo
 */
public class ProdutosBanco {
    
        public static void inserirProduto(Produtos produto) throws Exception{
        
        try (Connection con = Conexao.getConnection()){
            PreparedStatement pstmt = con.prepareStatement("insert into produtos (cod_produtos, descricao_produtos, preco_unitario_produtos, unidade_medida_produto) values (?, ?, ?, ?)");
            pstmt.setInt(1, produto.getCodigo());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setString(3, Double.toString(produto.getPrecoUnitario()));
            pstmt.setString(4, produto.getUnidadeMedida());   

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosBanco.class.getName()).log(Level.SEVERE, null, ex);
            throw new erros("Erro ao incluir:\n" + ex.getMessage());
        }
    }
        
        
        public static ArrayList<Produtos> listarProdutos() throws Exception{           
            
        ArrayList<Produtos> resultado = new ArrayList<>();           
            try (Connection con = Conexao.getConnection();){
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from produtos");
                while(rs.next()){
                    Produtos p = new Produtos();
    
                    p.setCodigo(rs.getInt("cod_produtos"));
                    p.setDescricao(rs.getString("descricao_produtos"));
                    p.setPrecoUnitario(rs.getDouble("preco_unitario_produtos"));
                    p.setUnidadeMedida(rs.getString("unidade_medida_produto"));
                   
                    resultado.add(p);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProdutosBanco.class.getName()).log(Level.SEVERE, null, ex);
                throw new erros("Erro ao incluir:\n" + ex.getMessage());
            }
            return resultado;
        }
    
}
