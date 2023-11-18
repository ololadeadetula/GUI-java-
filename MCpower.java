//Question 3
public class MCpower extends FMC {
	//instance variables
	private String malecharxpower;


	//contructor
	public MCpower(String name, String mainCharacter, String mangaka, String picture, String femalecharcter, String malecharxpower){
		super(name, mainCharacter, mangaka, picture, femalecharcter);
		this.malecharxpower = malecharxpower;
	}

	// @Override
	public String toString(){
		return super.toString() + ",||| MCpower: " + malecharxpower;
	}
}

