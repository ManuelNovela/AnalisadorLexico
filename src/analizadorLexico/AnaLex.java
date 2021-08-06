/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorLexico;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AnaLex {
    public int actualLinha = 0, pointer_scanner =0;
    
    
    public void AnaLex(){
        
        String nomeFicheiro = "Nova_Linguagem/Novo_Codigo.ycarus";
        pointer_scanner = 0;
        
        Token_retorno token_Obtido;
        do{
            pointer_scanner++;
            token_Obtido = analiseLexica(nomeFicheiro);
          
            String output = "<"+token_Obtido.getLexema()+", "+token_Obtido.getToken();
            if( token_Obtido.getAtributo() != -1){
                output += ", "+token_Obtido.getAtributo();
            }
            output += "> - LINHA: "+ actualLinha;
           
            if(token_Obtido.getLexema()!= null){
               System.out.println(output); 
            }
            
        }while( (actualLinha >0 ) && (token_Obtido.getLexema()!= null) );
    }
    
    
    
    public Token_retorno analiseLexica(String nomeArquivo){
        
        Token_retorno retorno = new Token_retorno();
        try {
            
            Reader lector = new BufferedReader(new FileReader(nomeArquivo));
            Regras_Lexicas lexer = new Regras_Lexicas(lector);
            String resultado = "";
            
            
            // Buscar o TOKEN
            Token token = null;
            for (int i = 0; i < pointer_scanner; i++) {
                token = lexer.yylex();
                actualLinha = lexer.GetLine();
            }
           
            
            if (token == null) {
                retorno.setToken(null);
                return retorno;
            }
            
            switch (token) {
                    
                    case Identificador: case Numero: case Palavra_reservada:
                        retorno.setLexema(lexer.lexeme);
                        retorno.setToken(token+"");
                        retorno.setNumeroLinha(actualLinha);
                        retorno.setAtributo(1);
                        break;
                    default:
                        retorno.setLexema(lexer.yytext());
                        retorno.setToken(token+"");
                        retorno.setNumeroLinha(actualLinha);
                    break;
                    
            }
            return retorno;
             
        } catch (IOException ex) {
            Logger.getLogger(AnaLex.class.getName()).log(Level.SEVERE, null, ex);
            return retorno;
        }
        
    }
    
}
