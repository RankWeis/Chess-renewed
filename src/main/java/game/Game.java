package game;

import java.util.ArrayList;

import pieces.Color;
import pieces.Piece;
import pieces.Type;
import board.Board;
import board.Location;
import board.Tile;

public class Game {

	private Board			board;
	private Color			currentPlayer;

	private ArrayList<Move> history = new ArrayList<Move>( );

	public void changeCurrentPlayer() {
		this.currentPlayer = currentPlayer.equals( Color.WHITE ) ? Color.BLACK
				: Color.WHITE;
	}

	public String drawBoard() {
		return board.textDraw( );
	}

	public Location getLocation(String algebraic) {
		return Board.convertAlgebraic( algebraic );
	}

	public Type getPieceType(char abbr) {
		switch ( abbr ) {
		case 'P':
		case 'p':
			return Type.PAWN;
		case 'R':
		case 'r':
			return Type.ROOK;
		case 'N':
		case 'n':
			return Type.KNIGHT;
		case 'B':
		case 'b':
			return Type.BISHOP;
		case 'Q':
		case 'q':
			return Type.QUEEN;
		case 'K':
		case 'k':
			return Type.KING;
		default:
			return null;
		}
	}

	public Tile getTile(String algebraic) {
		Location loc = Board.convertAlgebraic( algebraic );
		return board.getSquare( loc );
	}

	public boolean movePiece(String move) {
		String[] fromTo = move.split( " " );
		Piece movePiece;
		Tile toSquare = null;
		if ( fromTo.length > 2 )
			return false;
		for ( String curr : fromTo ) {
			if ( curr.length( ) == 1 ) {
				return false;
			}
		}
		// The first item has a piece named
		if ( fromTo[0].length( ) == 3 ) {
			char fromType = fromTo[0].charAt( 0 );
			Type pieceType = getPieceType( fromType );
			Tile tile = getTile( fromTo[0].substring( 1, 3 ) );

			// The named piece is there to provide clarity
			if ( fromTo.length == 2 ) {
				if ( !tile.getOccupiedPiece( ).getType( ).equals( pieceType ) ) {
					return false;
				} else {
					movePiece = tile.getOccupiedPiece( );
				}

				// The named piece could also be there to reduce ambiguity
			} else {
				ArrayList<Piece> searchSet = board.getColorTypePieces(
						currentPlayer, pieceType );
				ArrayList<Piece> movePieces = new ArrayList<Piece>( );
				for ( Piece p : searchSet ) {
					if ( p.getAllMoves( ).contains( tile ) ) {
						movePieces.add( p );
					}
				}
				if ( movePieces.size( ) > 1 ) {
					return false;
				} else {
					movePiece = movePieces.get( 0 );
				}
			}
			return doMovement( movePiece, tile );
			// The first Piece is only algebraic
		} else {
			Tile tile = getTile( fromTo[0] );
			if ( fromTo.length == 1 ) {
				ArrayList<Piece> p = board.getPiecesAttackingSquare(
						currentPlayer, getTile( fromTo[0] ) );
				if ( p.size( ) == 1 ) {
					return (doMovement( p.get( 0 ), tile ));
				} else {
					return false;
				}
			} else {
				movePiece = tile.getOccupiedPiece( );
			}

		}
		if ( fromTo.length == 2 ) {
			String currentAlg = fromTo[1];
			toSquare = getTile( currentAlg.length( ) == 3 ? currentAlg
					.substring( 1, 3 ) : currentAlg );
		}

		return this.doMovement( movePiece, toSquare );
	}

	public void setupGame() {
		board = new Board( );
		board.setupGame( );
		currentPlayer = Color.WHITE;

	}

	private boolean doMovement(Piece piece, Tile toSquare) {
        if ( piece == null || !this.currentPlayer.equals(piece.getColor())
                || !piece.getAllMoves().contains(toSquare)) {
            return false;
        }

		Piece capturedPiece = toSquare.getOccupiedPiece( );
		Tile oldSquare = board.getSquare( piece.getLocation( ) );
		boolean captured = board.movePiece( piece, toSquare );
		Move move;
		if ( captured ) {
			move = new Move( piece, capturedPiece, oldSquare, toSquare );
		} else {
			move = new Move( piece, oldSquare, toSquare );
		}
        this.changeCurrentPlayer();
		this.history.add(move);
		return true;

	}

}
