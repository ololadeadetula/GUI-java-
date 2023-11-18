import java.util.*;
import java.io.*;

public class AnimeShows implements Comparable<AnimeShows>{
	//instance variables
	private int id;
	private String name;
	private String mainCharacter;
	private String mangaka;
	private String picture;


	private static int idCount = 100;
	public static final Comparator<AnimeShows> NAME = new NameComparator();
	public static final Comparator<AnimeShows> MAIN_CHARACTER = new MainCharacterComparator();
	public static final Comparator<AnimeShows> MANGAKA = new MangakaComparator();


	//Constructor 
	public AnimeShows(String name, String mainCharacter, String mangaka, String picture){
		this.id = ++idCount;
		this.name = name;
		this.mainCharacter = mainCharacter;
		this.mangaka = mangaka;
		this.picture = picture; 
	}
	public String toString(){
		return "ID: " + id + "||| Anime Name: " + name + "||| MainCharacter Name : " + mainCharacter + ",||| Mangaka name: " + mangaka;
	}

	public int compareTo(AnimeShows other){
		return this.name.compareTo(other.name);
	}

	public String getPicture(){
		return picture;
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}
	public String getmainCharacter(){
		return mainCharacter;
	}
	public String getMangaka(){
		return mangaka;
	}

	public static AnimeShows[] getAnime(String filename){
		try{
			FileReader fr = new FileReader(filename);
			BufferedReader file = new BufferedReader(fr);
			int size = Integer.parseInt(file.readLine().trim());
			AnimeShows[] anime = new AnimeShows[size];
			System.out.println(size);
			for (int i = 0; i<anime.length; i++){
				String line = file.readLine();
				String[] items = line.split(",");
				if (items[0].trim().equals("ab")){
					anime[i] = new AnimeShows(items[1].trim(), items[2].trim(), items[3].trim(), items[4].trim());
				}else if(items[0].trim().equals("ac")){
					anime[i] = new FMCpower(items[1].trim(), items[2].trim(), items[3].trim(), items[4].trim(), items[5].trim());
		
				}else if(items[0].trim().equals("ad")){
					anime[i] = new FMC(items[1].trim(), items[2].trim(), items[3].trim(), items[4].trim(), items[5].trim());

				}else if(items[0].trim().equals("ae")){
					anime[i] = new MCpower(items[1].trim(), items[2].trim(), items[3].trim(), items[4].trim(), items[5].trim(), items[6].trim());
				}

			}
			return anime;

		}catch(IOException e){
			System.out.print(e);

		}return null;
	}

public static ArrayList<AnimeShows> getAnimelist(String filename){
		ArrayList<AnimeShows> list = new ArrayList<AnimeShows>();
		try{
			FileReader fr = new FileReader(filename);
			BufferedReader file = new BufferedReader(fr);
			int size = Integer.parseInt(file.readLine().trim());
			AnimeShows[] anime = new AnimeShows[size];
			System.out.println(size);
			for (int i = 0; i<anime.length; i++){
				String line = file.readLine();
				// System.out.println("Line: " + line);
				String[] items = line.split(",");
				if(items[0].trim().equals("ab")){
					list.add(new AnimeShows(items[1].trim(), items[2].trim(), items[3].trim(), items[4].trim()));
				}else if(items[0].trim().equals("ac")){
					list.add(new FMCpower(items[1].trim(), items[2].trim(), items[3].trim(), items[4].trim(), items[5].trim()));
		
				}else if(items[0].trim().equals("ad")){
					list.add(new FMC(items[1].trim(), items[2].trim(), items[3].trim(), items[4].trim(), items[5].trim()));

				}else if(items[0].trim().equals("ae")){
					list.add(new MCpower(items[1].trim(), items[2].trim(), items[3].trim(), items[4].trim(), items[5].trim(), items[6].trim()));
				}

			}

		}catch(IOException e){
			System.out.print(e);

		}
		return list;
	}

	public boolean equals(AnimeShows other){
		return this.id == other.id;
	}

	static class NameComparator implements Comparator<AnimeShows>{
		public int compare(AnimeShows e1, AnimeShows e2){
			return e1.name.compareTo(e2.name);
		}
	}

	static class MainCharacterComparator implements Comparator<AnimeShows>{
		public int compare(AnimeShows e1, AnimeShows e2){
			return e1.mainCharacter.compareTo(e2.mainCharacter);
		}
	}
	static class MangakaComparator implements Comparator<AnimeShows>{
		public int compare(AnimeShows e1, AnimeShows e2){
			return e1.mangaka.compareTo(e2.mangaka);
		}
	}

	public static void main(String[] args){
		AnimeShows[] anime = AnimeShows.getAnime("AnimeShow.csv");
		System.out.println(Arrays.toString(anime));
	}
}
