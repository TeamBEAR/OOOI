package parser; // The generated parser will belong to package "parser"

import beaver.Symbol;
import beaver.Scanner;

import parser.BearParser.Terminals ;

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
Uppercase = [A-Z]
Lowercase = [a-z]
Letter = {Uppercase} | {Lowercase}
Digit = [0-9]
Alphanumeric = {Letter}|{Digit}
Other  = [_]

// identifier pattern
A_ID = {Uppercase}({Alphanumeric}|{Other})+
F_ID = ({Lowercase}|{Other})+

%%

// discard whitespace information
{WhiteSpace}  { }

// define tokens

    and            {return sym(Terminals.CONJUNCTION);}
    et              {return sym(Terminals.CONJUNCTION);}
    continuer   {return sym(Terminals.RESERVED);}
    fermer       {return sym(Terminals.RESERVED);}
    continue     {return sym(Terminals.RESERVED);}
    exit            {return sym(Terminals.RESERVED);}
    new           {return sym(Terminals.NEW);}
    nouveau    {return sym(Terminals.NEW);}
    Agent        {return sym(Terminals.TYPE_AGENT);}
    ","               {return sym(Terminals.COMMA);}
    ";"               {return sym(Terminals.SEMICOLON);}
    ":"               {return sym(Terminals.COLON);}
    {A_ID}       {return sym(Terminals.A_ID);}
    {F_ID}        {return sym(Terminals.F_ID);}
    <<EOF>>    { return sym(Terminals.EOF); }

