package parser; // The generated parser will belong to package "parser"

import beaver.Symbol;
import beaver.Scanner;

import Parser.Terminals;

%%

// scanner method signature
%public
%final
%class BearScanner 
%extends beaver.Scanner

// the interface between the scanner and the parser is the nextToken() method
%function nextToken
%type beaver.Symbol 
%yylexthrow beaver.Scanner.Exception

// store line and column information in the tokens
%line
%column

// this code will be inlined in the body of the generated scanner class
%{
  private beaver.Symbol sym(short id) {
    return new beaver.Symbol(id, yyline + 1, yycolumn + 1, yylength(), yytext());
  }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace = [ ] | \t | \f | {LineTerminator}

// identifiers
A_ID = [:uppercase:][:jletter:]+
F_ID = [:jletter:]+

%%

// discard whitespace information
{WhiteSpace}  { }

// define tokens
<YYINITIAL> {
    {A_ID}      {return sym();}
    {F_ID}      {return sym();}
    {}
}

"state"       { return sym(Terminals.STATE); }
"trans"       { return sym(Terminals.TRANS); }
{Identifier}  { return sym(Terminals.IDENTIFIER); }
";"           { return sym(Terminals.SEMI); }
":"           { return sym(Terminals.COLON); }
"->"          { return sym(Terminals.ARROW); }
<<EOF>>       { return sym(Terminals.EOF); }