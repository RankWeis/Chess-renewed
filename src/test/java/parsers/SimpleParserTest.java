package parsers;

import org.junit.Assert;
import org.junit.Test;

import board.Location;

public class SimpleParserTest {

	@Test
	public void doInvalidChecks() {
		Assert.assertFalse( SimpleParser.matchesFormat( "Re1" ) );
		Assert.assertFalse( SimpleParser.matchesFormat( "e4" ) );
	}

	@Test
	public void doValidChecks() {
		Assert.assertTrue( SimpleParser.matchesFormat( "e2 e4" ) );
		Location[] locs = SimpleParser.parse( "e2 e4" );
		Assert.assertTrue( locs[0].equals( new Location( 1, 4 ) ) );
		Assert.assertTrue( locs[1].equals( new Location( 3, 4 ) ) );
		Assert.assertTrue( SimpleParser.matchesFormat( "E2 e4" ) );
		locs = SimpleParser.parse( "E2 e4" );
		Assert.assertTrue( locs[0].equals( new Location( 1, 4 ) ) );
		Assert.assertTrue( locs[1].equals( new Location( 3, 4 ) ) );
		Assert.assertTrue( SimpleParser.matchesFormat( "H7 A1" ) );
	}
}
