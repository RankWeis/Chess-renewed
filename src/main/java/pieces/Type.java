package pieces;

import java.util.ArrayList;

import movement.MovementBase;
import board.Board;
import board.Location;
import board.Tile;

public enum Type {
	PAWN {

		@Override
		ArrayList<Tile> getLegalMoves(Board board, Color c, Location l,
				boolean attacksOnly) {
			return MovementBase.getMovements( board, c, PAWN, l, attacksOnly );
		}
	},
	ROOK {

		@Override
		ArrayList<Tile> getLegalMoves(Board board, Color c, Location l,
				boolean attacksOnly) {
			return MovementBase.getMovements( board, c, ROOK, l, attacksOnly );
		}
	},
	KNIGHT {

		@Override
		ArrayList<Tile> getLegalMoves(Board board, Color c, Location l,
				boolean attacksOnly) {
			return MovementBase.getMovements( board, c, KNIGHT, l, attacksOnly );
		}
	},
	BISHOP {

		@Override
		ArrayList<Tile> getLegalMoves(Board board, Color c, Location l,
				boolean attacksOnly) {
			ArrayList<Tile> ret = MovementBase.getMovements( board, c, BISHOP,
					l, attacksOnly );
			return ret;
		}
	},
	QUEEN {

		@Override
		ArrayList<Tile> getLegalMoves(Board board, Color c, Location l,
				boolean attacksOnly) {
			return MovementBase.getMovements( board, c, QUEEN, l, attacksOnly );
		}
	},
	KING {

		@Override
		ArrayList<Tile> getLegalMoves(Board board, Color c, Location l,
				boolean attacksOnly) {
			return MovementBase.getMovements( board, c, KING, l, attacksOnly );
		}
	};

	public ArrayList<Tile> getLegalMoves(Board board, Color c, Location l) {
		return getLegalMoves( board, c, l, false );
	}

	abstract ArrayList<Tile> getLegalMoves(Board board, Color c, Location l,
			boolean attacksOnly);
}
