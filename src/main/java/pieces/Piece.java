package pieces;

import java.util.ArrayList;

import board.Board;
import board.Location;
import board.Tile;

public final class Piece {

	static Board	board		= new Board( );

	public static Board getBoard() {
		return board;
	}
	public static void resetBoard() {
		board = new Board( );
	}

	public static void setBoard(Board board) {
		Piece.board = board;
	}

	final Type		type;

	final Color		color;

	private boolean	firstMove	= true;
	Location		location;

	private Tile	lastTile	= null;

	public Piece(Type type, Color color) {
		this( type, color, null );
	}

	public Piece(Type type, Color color, Location loc) {
		this.type = type;
		this.color = color;
		this.location = loc;
	}

	public ArrayList<Tile> getAllAttacks() {
		return type.getLegalMoves( board, color, location, true );
	}

	public ArrayList<Tile> getAllMoves() {

		ArrayList<Tile> ret = type.getLegalMoves( board, color, location );
		return ret;
	}

	public Color getColor() {
		return color;
	}

	public Tile getLastTile() {
		return lastTile;
	}

	public Location getLocation() {
		return location;
	}

	public Type getType() {
		return type;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	public void setLastTile(Tile lastTile) {
		this.lastTile = lastTile;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
