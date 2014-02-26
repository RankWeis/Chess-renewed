package board;

import java.util.Comparator;

public class TileComparator implements Comparator<Tile> {

	public int compare(Tile a, Tile b) {

		if ( a.getRow( ) < b.getRow( ) ) {
			return -1;
		} else {
			if ( a.getRow( ) > b.getRow( ) ) {
				return 1;
			}
			if ( a.getRow( ) == b.getRow( ) ) {
				return a.getColumn( ) < b.getColumn( ) ? -1 : 1;
			}
			return 0;
		}
	}

}
