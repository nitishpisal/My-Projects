
public aspect Terrible {
	after() : call( * Main.print() ) {
		System.out.print( " Terrible" ) ;
	}
}