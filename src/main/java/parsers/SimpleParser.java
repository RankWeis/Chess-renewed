package parsers;

import board.Location;

public class SimpleParser {

	private static final String	singleFormat	= "[A-Ha-h][1-8]";

	private static final String	format			= singleFormat + " "
														+ singleFormat;

	public static boolean matchesFormat(String entry) {
		return entry.matches( format );
	}

	public static Location[] parse(String entry) {

		if ( !matchesFormat( entry ) ) {
			return null;
		}

		String[] toFrom = entry.split( " " );
		return new Location[] {
				strToLoc( toFrom[0] ), strToLoc( toFrom[1] )

		};

	}

	private static Location strToLoc(String entry) {
		assert entry.matches( singleFormat );
		int col = entry.toUpperCase( ).charAt( 0 ) - 65;
		int row = Integer.parseInt( entry.substring( 1, 2 ) ) - 1;
		return new Location( row, col );
	}

}
