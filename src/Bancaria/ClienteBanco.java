/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bancaria;

import classesEmodelos.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Eduardo
 */
public class ClienteBanco {
    
        public static void inserirExemplos(Clientes clientes) throws Exception{
        
        try (Connection con = Conexao.getConnection()){
            PreparedStatement pstmt = con.prepareStatement("insert into clientes (cpf_clientes, nome_clientes, telefone_clientes, tipo_telefone_clientes, cep_cliente, bairro_cliente, rua_cliente, complemento_cliente, uf_cliente, cidade_cliente) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, "70710276176");
            pstmt.setString(2, "Carlos Eduardo Sousa Lopes");
            pstmt.setString(3, "993319616");
            pstmt.setString(4, "Celular");   
            pstmt.setString(5, "605123");
            pstmt.setString(6, "Santa genoveva");
            pstmt.setString(7, "Serra Dourada");
            pstmt.setString(8, "Quadra 122 e Lote 70");
            pstmt.setString(9, "Goias");
            pstmt.setString(10, "Goiania");
                        
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            //throw new SpiderException("Erro ao incluir:\n" + ex.getMessage());
        }
    }
    
    
    

    public static void inserir(Clientes clientes) throws Exception{
        
        try (Connection con = Conexao.getConnection()){
            PreparedStatement pstmt = con.prepareStatement("insert into clientes (cpf_clientes, nome_clientes, telefone_clientes, tipo_telefone_clientes, cep_cliente, bairro_cliente, rua_cliente, complemento_cliente, uf_cliente, cidade_cliente) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, clientes.getCpfCliente());
            pstmt.setString(2, clientes.getNomeCliente());
            pstmt.setString(3, clientes.getTelefoneCliente());
            pstmt.setString(4, clientes.getTipoTelefone());   
            pstmt.setString(5, clientes.getCepCliente());
            pstmt.setString(6, clientes.getBairro());
            pstmt.setString(7, clientes.getRua());
            pstmt.setString(8, clientes.getComplemento());
            pstmt.setString(9, clientes.getUF());
            pstmt.setString(10, clientes.getCidade());
                        System.out.println("e aqui");
            pstmt.executeUpdate();
            System.out.println("Foi?");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBanco.class.getName()).log(Level.SEVERE, null, ex);
            throw new erros("Erro ao incluir:\n" + ex.getMessage());
        }
    }
    
        public static ArrayList<Clientes> listar() throws erros{           
            
            ArrayList<Clientes> resultado = new ArrayList<>();           
        try (Connection con = Conexao.getConnection();){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from clientes");
            while(rs.next()){
                Clientes p = new Clientes();
    
                p.setCpfCliente(rs.getString("cpf_clientes"));
                p.setNomeCliente(rs.getString("nome_clientes"));
                p.setTelefoneCliente(rs.getString("telefone_clientes"));
                p.setTipoTelefone(rs.getString("tipo_telefone_clientes"));
                p.setCepCliente(rs.getString("cep_cliente"));
                p.setBairro(rs.getString("bairro_cliente"));
                p.setRua(rs.getString("rua_cliente"));
                p.setComplemento(rs.getString("complemento_cliente"));
                p.setUF(rs.getString("uf_cliente"));
                p.setCidade(rs.getString("cidade_cliente"));
                resultado.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBanco.class.getName()).log(Level.SEVERE, null, ex);
            throw new erros("Erro ao incluir:\n" + ex.getMessage());
        }
        return resultado;
    }
    
    
    
    
}
