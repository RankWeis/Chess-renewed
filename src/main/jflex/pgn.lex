/*-***
 * To read PGN notation.
 */

package parsers;
import java_cup.runtime.*;

%%


/*-*
 * LEXICAL FUNCTIONS:
 */

%cup
%line
%column
%unicode
%class PGNLexer

%{
  StringBuffer string = new StringBuffer();

Symbol newSym(int tokenId) {
    return new Symbol(tokenId, yyline, yycolumn);
}

Symbol newSym(int tokenId, Object value) {
    return new Symbol(tokenId, yyline, yycolumn, value);
}

%}


/*-*
 * PATTERN DEFINITIONS:
 */
ALPHA=[A-Za-z]
DIGIT=[0-9]
castleKingSide          = (O-O-O)
castleQueenSide         = (O-O)
score           = 1\/2\-1\/2|1-0|0-1
/*
If these letters don't work out, these ones will.
[^\n\"\]\}\r\t]
[A-Za-z0-9_\,\\\/\-\.|\"]+
*/
STRING_LETTERS = [^\n\"\]\}\r\t]+
STRING_TEXT= {STRING_LETTERS}([\ ]?{STRING_LETTERS})*
commonMove = {ALPHA}{DIGIT}
specifyPiece = {ALPHA}{commonMove}
takingPiece = {ALPHA}x{commonMove}
takingPieceLongForm = {specifyPiece}x{commonMove}
removingAmbiguity = {ALPHA}({ALPHA}|{DIGIT}){commonMove}
removingAmbiguityAndTaking = {ALPHA}({ALPHA}|{DIGIT})x{commonMove}
promotion = {commonMove}={ALPHA}
algebraicMerged       = {promotion}|{commonMove}|{specifyPiece}|{takingPiece}|{takingPieceLongForm}|{removingAmbiguity}|{removingAmbiguityAndTaking}
algebraic = {algebraicMerged}[\+]?
WhiteSpace      = {LineTerminator} | [ \t\f]
LineTerminator  = [\r|\n|\r\n]


%state BRACKETED
%state COMMENTS
%%
/**
 * LEXICAL RULES:
 */

\[             {  yybegin(BRACKETED); return newSym(sym.LBRACKET); }
\{              { yybegin(COMMENTS); return newSym(sym.LCURLY); }

({DIGIT}+\.)    { return newSym(sym.NUMBER, yytext()); }
{score}         { return newSym(sym.SCORE, yytext()); }
{algebraic}     { return newSym(sym.ALGNOT, yytext()); }
{castleKingSide}        { return newSym(sym.KING_CASTLE); }
{castleQueenSide}       { return newSym(sym.QUEEN_CASTLE); }


<BRACKETED> {
    \"({STRING_TEXT})*\" {
        String str = yytext().trim().substring(1,yytext().length() - 1);
        return newSym(sym.QUOTESTR, str);
    }

    {STRING_TEXT} {
        String str = yytext().trim();
        return newSym(sym.STRING, str);
    }

    \]             { yybegin(YYINITIAL); return newSym(sym.RBRACKET); }
}

<COMMENTS> {
    {STRING_TEXT} {
        return newSym(sym.COMMENT, yytext().trim());
    }

    \} {yybegin(YYINITIAL); return newSym(sym.RCURLY); }
}

{WhiteSpace}*    { /* Do nothing. */ }

[^]                              { throw new Error("Illegal character <"+
                                                    yytext()+">"); }