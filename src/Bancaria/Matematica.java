/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bancaria;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Eduardo
 */
public class Matematica {
    
        public static ArrayList<Integer> PegarRol(java.util.Date datainico, java.util.Date datafim) throws Exception{           
           
        
         
            
        ArrayList<Integer> resultado = new ArrayList<>();  
        
            try (Connection con = Conexao.getConnection();){
                
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dataInicio = df.format(datainico);
                String dataFim = df.format(datafim);
                
                System.out.println(dataInicio);
                
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select count(data_venda), data_venda from venda WHERE data_venda BETWEEN '" + dataInicio + "' AND '"+ dataFim +"' group by data_venda having count(data_venda)>=1 order by 1	");
                while(rs.next()){
                    int p = rs.getInt("count");
    
                    System.out.println("issso é " +p);
                    resultado.add(p);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Matematica.class.getName()).log(Level.SEVERE, null, ex);
                throw new erros("Erro ao incluir :\n" + ex.getMessage());
            }
            return resultado;
        }
        
        public static ArrayList<Double> PegardadosPclasse(java.util.Date datainico, java.util.Date datafim) throws Exception{           
    
        ArrayList<Double> resultado = new ArrayList<>();  
        
            try (Connection con = Conexao.getConnection();){
                
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dataInicio = df.format(datainico);
                String dataFim = df.format(datafim);               
                             
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT SUM(valor_total_venda) , data_venda FROM venda WHERE data_venda BETWEEN '" + dataInicio + "' AND '"+ dataFim +"' GROUP BY data_venda order by 1");
                while(rs.next()){
                    double p = rs.getDouble("sum");
                    resultado.add(p);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Matematica.class.getName()).log(Level.SEVERE, null, ex);
                throw new erros("Erro ao incluir :\n" + ex.getMessage());
            }
            return resultado;
        }
        
        
    
    
    
    
    
    
    
    
    
    
    
}
