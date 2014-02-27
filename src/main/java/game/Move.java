package game;

import pieces.Piece;
import board.Tile;

class Move {

    private Piece movedPiece;
    private Piece pieceTaken;
    private Piece promotedPiece;
    private Tile  lastTile;
    private Tile  newTile;

    private Move( Piece movedPiece, Piece pieceTaken, Piece promotedPiece,
            Tile lastTile, Tile newTile ) {
        this.movedPiece = movedPiece;
        this.pieceTaken = pieceTaken;
        this.promotedPiece = promotedPiece;
        this.lastTile = lastTile;
        this.newTile = newTile;
    }

    public Move( Piece movedPiece, Piece pieceTaken, Tile lastTile, Tile newTile ) {
        this( movedPiece, pieceTaken, null, lastTile, newTile );
    }

    public Move( Piece movedPiece, Tile lastTile, Tile newTile ) {
        this( movedPiece, null, null, lastTile, newTile );
    }

    public Tile getLastTile() {
        return lastTile;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public Tile getNewTile() {
        return newTile;
    }

    public Piece getPieceTaken() {
        return pieceTaken;
    }

    public Piece getPromotedPiece() {
        return promotedPiece;
    }

    public void setLastTile( Tile lastTile ) {
        this.lastTile = lastTile;
    }

    public void setMovedPiece( Piece movedPiece ) {
        this.movedPiece = movedPiece;
    }

    public void setNewTile( Tile newTile ) {
        this.newTile = newTile;
    }

    public void setPieceTaken( Piece pieceTaken ) {
        this.pieceTaken = pieceTaken;
    }

    public void setPromotedPiece( Piece promotedPiece ) {
        this.promotedPiece = promotedPiece;
    }

}
