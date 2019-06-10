package com.ramses.pastelariapdv.db;

import com.ramses.pastelariapdv.db.ConnectionUtils;
import com.ramses.pastelariapdv.model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDao
{
     public static void salvar(Venda venda) 
        throws SQLException, Exception {
        
        String sql = "INSERT INTO DBPASTELARIA.TBVENDAS(data, qtdItens, "
                + "subtotal, pagamentoDinheiro, pagamentoCartao, nomeCliente, cpfCliente) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?);";
            
            Connection connection = null;
            PreparedStatement preparedStatement = null;
        
            try {
            
            connection = ConnectionUtils.obterConexao();
           
            preparedStatement = connection.prepareStatement(sql);
            
            java.sql.Timestamp data = new java.sql.Timestamp(venda.getData().getTime());
            preparedStatement.setTimestamp(1, data);
            preparedStatement.setInt(2, venda.getItensVenda().size());
            preparedStatement.setFloat(3, venda.getSubtotal());
            preparedStatement.setFloat(4, venda.getPagamentoDinheiro());
            preparedStatement.setFloat(5, venda.getPagamentoCartao());
            preparedStatement.setString(6, venda.getCliente().getNome());
            preparedStatement.setString(7, venda.getCliente().getCpf());
            
            preparedStatement.execute();
        } finally {
            
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static Venda obterUltima() 
        throws SQLException, Exception {
        
        String sql = "SELECT MAX(id) qtdVendas FROM DBPASTELARIA.TBVENDAS";
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            connection = ConnectionUtils.obterConexao();
            preparedStatement = connection.prepareStatement(sql);
            
            result = preparedStatement.executeQuery();
            
            if(result.next()){
                Venda venda = new Venda();
                venda.setId(result.getInt("qtdVendas"));
                
                return venda;
            }
        }finally{
            if(result != null && !result.isClosed()){
                result.close();
            }
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return null;
    } 
    
}

