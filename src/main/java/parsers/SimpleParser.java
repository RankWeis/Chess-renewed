package parsers;

import java_cup.runtime.Symbol;

import java.io.File;
import java.io.FileReader;

class SimpleParser {

    public static void main(String[] args) {
        File f = new File("C:\\Users\\Kian\\Documents\\fakepgn.txt");
        try(FileReader fReader = new FileReader(f)) {
            parser p = new parser( new PGNLexer( fReader));
            Symbol s = p.parse();
            System.out.println(s.toString());
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

}
