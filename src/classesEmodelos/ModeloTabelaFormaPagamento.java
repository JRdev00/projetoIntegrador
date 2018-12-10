/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesEmodelos;

import classesMatematica.VendaDia;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aluno
 */
public class ModeloTabelaFormaPagamento extends AbstractTableModel{
    
    ArrayList<String> vendas;
    private String[] colunas = {"xi", "ni", "xi.ni","Porcentagem", "(x-X)².ni"};

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
