//first subclass
import java.util.*;
import java.io.*;

public class FMCpower extends AnimeShows{
	//instance variables
	private String femalecharxpower;

	//contructor
	public FMCpower(String name, String mainCharacter, String mangaka, String picture, String femalecharxpower){
		super(name, mainCharacter, mangaka, picture);
		this.femalecharxpower = femalecharxpower;
	}

	public String toString(){
		return super.toString() + ",||| FMCpower: " + femalecharxpower;
	}

	public int compareTo(FMCpower other){
		return this.femalecharxpower.compareTo(other.femalecharxpower);
	}
}