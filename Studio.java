import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;


			
 //Que 4
import java.io.*;

public class Studio{
	public static String readFile(String fileName){
		In file = new In(fileName);
		return file.readAll();
	}
	public static AnimeShow[] getAnime(String fileName){
		AnimeShow[] anime = null;
		try{
			FileReader fr = new FileReader(fileName);
			BufferedReader file = new BufferedReader(fr);
			int size = Integer.parseInt(file.readLine().trim());
			anime = new AnimeShow[size];
			for (int i=0; i<anime.length; i++){
				String line = file.readLine();
				String[] items = line.split(",");
				anime[i] = new AnimeShow(
					items[0],
					items[1],
					items[2],
					items[3]
					);
			}
		}catch(IOException e){
			System.out.println(e);

		}
		return anime;
	}
public static String toString(AnimeShow[] anime){
	StringBuilder str = new StringBuilder();
	for (AnimeShow s: anime){
		str.append(s + "\n");
	}
	return str.toString();

}
}
