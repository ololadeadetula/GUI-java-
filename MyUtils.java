Public class MyUtils{
	public static String readFile( String filename){
		In file = new In(filename);
		return file.readAll();
	}
}
