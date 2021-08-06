package analizadorLexico;
import static analizadorLexico.Token.*;

%%
%class Regras_Lexicas
%line
%caseless
%ignorecase
%type Token


/***********************************
*          ALFABETO                *
************************************/
Letra=[a-zA-Z_]+
Digito=[0-9]+
Esp_Tab_nLinha=[ ,\t,\r,\n]+

%{
    public String lexeme;
    public int GetLine() { return yyline + 1; }
%}
%%


/***********************************
*          TOKENS                  *
************************************/

// Words

{Esp_Tab_nLinha} {/*Ignore*/}
"//".* {/*Ignore*/}

"\""({Letra}|{Digito}|{Esp_Tab_nLinha})*"\"" {lexeme=yytext(); return Texto_Atribuido;}



//Operacoes
"+" { return Soma; }
"++" { return Incremento; }
"-" { return Subtracao; }
"--" { return Decremento; }
"*" { return Multiplicacao; }
"^" { return Exponenciacao; }
"/" { return Divisao; }
"%" { return Modulo; }
"=" { return Atribuicao; }

//Op. Logicas
"&&"  { return Intercessao; }
"||"  { return Uniao; }
"!" { return Negacao; }

//Comparacao
"!=" { return Diferente; }
"<"  { return Menor; }
"<=" { return Menor_Igual; }
">"  { return Maior; }
">=" { return Maior_Igual; }
"==" { return Igual; }

// Intrucoes, Procedimento e Classes
"(" { return Pc_Abre; }
")" { return Pc_Fecha; }
"[" { return Pr_Abre; }
"]" { return Pr_Fecha; }
"{" { return Ch_Abre; }
"}" { return Ch_Fecha; }
"." { return Ponto; }
"," { return Virgula; }
";" { return Ponto_Virgula; }


/********************************************************************
*   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   
*                  P A L A V R A    R E S E R V A D A               *
*   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
********************************************************************/
INTEIRO |
TEXTO |
FUNCAO |
CLASSE |
PUBLICA |
PRIVADA |
SE |
SENAO |
PARA |
PARACADA |
REPITA |
ATE |
ENQUANTO {lexeme=yytext(); return Palavra_reservada;}

{Letra}({Letra}|{Digito})* {lexeme=yytext(); return Identificador;}
("(-"{Digito}+")")|{Digito}+ {lexeme=yytext(); return Numero;}


. {return ERROR;}