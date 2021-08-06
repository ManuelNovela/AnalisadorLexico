/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorLexico;

import java.io.File;

/**
 *
 * @author Administrator
 */
public class RunAnaLex {
     public static void main(String[] args) {
         generarLexer();
         //AnaLex anaLex = new AnaLex();
         //anaLex.AnaLex();
         
    }
     
    // GERAR O ANALISADOR LEXICO .JAVA APARTIR DO .FLEX
    public static void generarLexer(){
        
        String basePath = System.getProperty("user.dir");
        String rota = basePath+"/src/analizadorLexico/Regras_Lexicas.flex";
        
        File archivo = new File(rota);
        JFlex.Main.generate(archivo);
    }
}
