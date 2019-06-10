package com.ramses.pastelariapdv.controller;

import com.ramses.pastelariapdv.db.VendaDao;
import com.ramses.pastelariapdv.mock.VendaMock;
import com.ramses.pastelariapdv.model.Venda;
import java.util.Date;
import java.util.List;

public class VendaController {
    
    //SALVAR
    public static String salvar(Venda venda){
        
        String resposta = null;
        
        //Tenta fazer a insercao
        try{
            //VendaMock.salvar(venda);
            VendaDao.salvar(venda);
        }catch (Exception e){
            e.printStackTrace();
            resposta = "Erro na fonte de dados";
        }
        
        return resposta;
    }
    
    //OBTER
    public static Venda obterUltima() {
        
        //Instancia venda para retornar no fim da funcao
        Venda venda =  new Venda();
        
        try 
        {
            //tenta obter o venda procurado
            //venda = VendaMock.obterUltima();
            venda = VendaDao.obterUltima();
        } 
        catch (Exception e) 
        {
            //em caso de erro imprime erro 
            e.printStackTrace();
        }

        return venda;
        
    }
}
