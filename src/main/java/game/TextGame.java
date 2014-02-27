package game;

import java.util.Scanner;

public class TextGame {

    @SuppressWarnings({"DM_DEFAULT_ENCODING"})
    public static void main( String[] args ) {
        Scanner sc = new Scanner( System.in );
        Game game = new Game();
        game.setupGame();
        System.out.println( game.drawBoard() );
        while ( sc.hasNextLine() ) {
            game.movePiece( sc.nextLine() );
            System.out.println( game.drawBoard() );
        }
    }
}
