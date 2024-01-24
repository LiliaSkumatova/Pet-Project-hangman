import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

//	private String[] names;
//	// total wins
//	private int[] wins;
//	// total losses
//	private int[] losses;
//	// all words
	private String[] words;
	public BufferedReader reader;
	private int totalNumberOfRecords;
	
	public Reader(String fileName) {
		setReader(fileName);
		totalNumberOfRecords = getTotalNumOfRecords(); 
	}//end constructor
	
	public int getTotalNumOfRecords() {
		String line = "";
		try {
			line = reader.readLine();
		} catch (IOException e) {
			System.out.println("total number of records does not exist");
		}
		return Integer.valueOf(line);
	}//end method 
	
	public void setReader(String fileName) {
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			
		}
	}//end method
	
	public ArrayList<Player> readingPlayersInfo() {
		ArrayList records = new ArrayList<Player>();
//		names = new String[totalNumberOfRecords];
//		wins = new int[totalNumberOfRecords];
//		losses = new int[totalNumberOfRecords];

		try {
			for (int i = 0; i < totalNumberOfRecords; i++) 
			{
				Player tmpPlayer = new BasicPlayer(reader.readLine(), Integer.valueOf(reader.readLine()), 
						Integer.valueOf(reader.readLine()));
				records.add(tmpPlayer);
			}//end for
			reader.close();
		}catch(IOException e) {
			System.out.println("Player information not found!");
		}
		return records;
		
	}
	
	public void readingWords() {
		try {
			words = new String[totalNumberOfRecords];

			for (int k = 0; k < totalNumberOfRecords; k++) 
			{
				words[k] = reader.readLine();
			}//end for
			reader.close();
		}catch(IOException e) {
			System.out.println("Words not found");
		}
	}

	

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public int getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public void setTotalNumberOfRecords(int totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}

	
	
}
	