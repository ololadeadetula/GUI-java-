//second subclass
public class FMC extends AnimeShows{
	//instance variables
	private String femalecharacter;

	//contructor
	public FMC(String name, String mainCharacter, String mangaka, String picture, String femalecharacter){
		super(name, mainCharacter, mangaka, picture);
		this.femalecharacter = femalecharacter;
	}
	public String toString(){
		return super.toString() + ",||| FMC: " + femalecharacter;
	}

	public int compareTo(FMC other){
		return this.femalecharacter.compareTo(other.femalecharacter);
	}
}