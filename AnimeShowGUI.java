import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;


public class AnimeShowGUI{
	private JButton readfile;
	private JTextField filefield;
	private JTextField inputField;

	private JList contents;
	private JList list;
	private ArrayList<AnimeShows> animes;

	
	private JButton sort;
	private JButton addRecord;
	private JButton delete;

	
	private JRadioButton name;
	private JRadioButton mainCharacter;
	private JRadioButton mangaka;
	private JButton quit;

	private JLabel console;
	private JLabel picture;

	private AnimeShows[] anime;


	public AnimeShowGUI(){
		animes = new ArrayList<AnimeShows>();

		JFrame frame = new JFrame("Ololade Adetula Final Project");
		frame.setSize(1500,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.add(new JLabel("Enter File Name: "));
		filefield = new StudioField("AnimeShow.csv");

		filefield.addActionListener(new FileListener());
		panel.add(filefield);


		panel.add(new JLabel("Input Data: "));
		inputField = new JTextField(10);
		inputField.setBackground(Color.white);
		inputField.setForeground(Color.black);

		AddListener addListener = new AddListener();
		inputField.addActionListener(addListener);
		panel.add(inputField);

		addRecord = new JButton("Add Record");
		addRecord.addActionListener(addListener);
		panel.add(addRecord);
		addRecord.setBackground(Color.blue);
		addRecord.setForeground(Color.white); 

		delete = new JButton("Remove Record");
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				int removeId = Integer.parseInt(inputField.getText().trim());
				int index = getIndex(removeId);
				System.out.println("Indec: " + index);
				if(index != -1){
					AnimeShows removed = animes.remove(index);
					console.setText(removed + "Removed");
				}else{
					console.setText("ID " + removeId + " not found");
				}
				contents.setListData(animes.toArray());
			}
		});
		panel.add(delete);
		frame.add(BorderLayout.NORTH, panel);
		delete.setBackground(Color.blue);
		delete.setForeground(Color.white);
		// frame.add(BorderLayout.NORTH, panel);


		list = new JList();
		frame.add(list);
		console = new JLabel();
		frame.add(BorderLayout.SOUTH, console);

		sort = new MyButton ("Sort");
		sort.addActionListener(new SortListener());
		panel.add(sort);


		name = new StudioRadioButton("Anime Name");
		name.addActionListener(new NamesListener());

		mainCharacter = new StudioRadioButton("Main Character Name");
		mainCharacter.addActionListener(new MainCharacterListener());

		mangaka = new StudioRadioButton("Mangaka");
		mangaka.addActionListener(new MangakaListener());

		quit = new JButton("Quit");
		quit.addActionListener(new QuitListener());
		panel.add(quit);
		quit.setBackground(Color.red);
		quit.setForeground(Color.black);


		//so button can occur once 
		ButtonGroup group = new ButtonGroup();
			group.add(name);
			group.add(mainCharacter);
			group.add(mangaka);

		//adding radio buttons to panel
		panel.add(name);
		panel.add(mainCharacter);
		panel.add(mangaka);
		frame.add(BorderLayout.NORTH, panel);


		contents = new JList();
		contents.addListSelectionListener(new ContentListener());
		picture = new JLabel();
		JSplitPane pane = new JSplitPane(SwingConstants.VERTICAL, contents, picture);
		pane.setDividerLocation(820);
		pane.setBackground(Color.lightGray);
		contents.setFont(new Font("Times New Roman", Font. BOLD, 12));
		frame.add(pane);

		frame.setVisible(true);
	}

	// the array is add
	public int getIndex(int id){
		System.out.println(id);
		System.out.println(anime);
		for(int i=0; i<animes.size(); i++){
			System.out.println("i: " + i);
			AnimeShows ani = animes.get(i);
			System.out.println("Ani: " + ani);
			if (ani.getId() == id){
				return i;
			}
		}
		return -1;
	}



	class AddListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String[] items = inputField.getText().split(",");
			AnimeShows anime = new AnimeShows(items[0].trim(), items[1].trim(),items[2].trim(),items[3].trim());
			animes.add(anime);
			console.setText("Added: " + anime);
			list.setListData(animes.toArray());
			contents.setListData(animes.toArray());
		}
	}

	private void showRandomPicture(){
		int selectedIndex = (int)(Math.random() * animes.size());
		String pictureFile = animes.get(selectedIndex).getPicture();
		picture.setIcon(new ImageIcon(pictureFile));
		contents.setSelectedIndex(selectedIndex);
	}


	public static void main(String[] args){
		AnimeShowGUI gui = new AnimeShowGUI();
		// new AnimeShowGUI();
	}

	
	class FileListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String fileName = filefield.getText().trim();
			animes = AnimeShows.getAnimelist(fileName);
			contents.setListData(animes.toArray());
			showRandomPicture();
		}
	}

	class SortListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			contents.setListData(animes.toArray());
			Collections.sort(animes);
		}
	}

	class NamesListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Collections.sort(animes, AnimeShows.NAME);
			contents.setListData(animes.toArray());
			showRandomPicture();
		}
	}

	
	class MainCharacterListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Collections.sort(animes, AnimeShows.MAIN_CHARACTER);
			contents.setListData(animes.toArray());
			showRandomPicture();
		}
	}

	class MangakaListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Collections.sort(animes, AnimeShows.MANGAKA);
			contents.setListData(animes.toArray());
			showRandomPicture();
		}
	}

	class ContentListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e){
			int selectedIndex = contents.getSelectedIndex();
			String pictureFile = anime[selectedIndex].getPicture();
			picture.setIcon(new ImageIcon(pictureFile));
		}
	}
	class QuitListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.out.println("---Program Ended---");
			System.exit(0);
		}
	}

}//end of music gui class
class StudioField extends JTextField{
	public StudioField(String str){
		super(str);
		setBackground(Color.white);
		setForeground(Color.blue);
	}
}

class StudioRadioButton extends JRadioButton{
	public StudioRadioButton(String str){
		super(str);
		setBackground(Color.white);
		setForeground(Color.black);
	}
}


class TopPanel extends JPanel{
	public TopPanel(){
		setBackground(Color.green);
	}
}

class ContentsArea extends JTextArea{
	public ContentsArea(){
		setBackground(Color.green);
		setForeground(Color.black);
		setEditable(false); 
	}
}


class MyButton extends JButton{
		public MyButton(String str){
			super(str);
			//the background of JButton
			setBackground(Color.blue);
			//the color of the JButton text 
			setForeground(Color.white);
		}
}
