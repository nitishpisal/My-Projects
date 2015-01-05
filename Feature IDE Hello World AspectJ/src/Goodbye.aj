
public aspect Goodbye {
	before() : call( * Main.print() ) {
		System.out.print( "Goodbye" ) ;
	}
}