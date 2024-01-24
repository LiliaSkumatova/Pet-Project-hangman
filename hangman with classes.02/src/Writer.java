import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Writer {

	public PrintWriter writer;
	
	public Writer(String fileName) {
		setWriter(fileName);
		
		
	}

	private void setWriter(String fileName) {
		try {
			writer = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
	}
	
	public void updateRecords(List<Player> records, Player currentPlayer) {
	
		if(records != null && records.size() != 0) {
			writer.println(records.size());
			
			
			
			for (int i = 0; i < records.size(); i++) {
				if(records.get(i).retrieveName() != null) {
					if(records.get(i).retrieveName().equals(currentPlayer.retrieveName())) {
						records.set(i, currentPlayer);
					}
					writer.println(records.get(i).retrieveName());
					writer.println(records.get(i).retrieveWins());
					writer.println(records.get(i).retrieveLosses());
				}
			}//end for
			writer.close();
			
		}
	}
	public void addNewRecord(List<Player> records) {
		
		if(records != null && records.size() != 0) {
			writer.println(records.size());
			
			
			
			for (int i = 0; i < records.size(); i++) {
				if(records.get(i).retrieveName() != null) {
					
					writer.println(records.get(i).retrieveName());
					writer.println(records.get(i).retrieveWins());
					writer.println(records.get(i).retrieveLosses());
				}
			}//end for
			writer.close();
			
		}
	}
	
}
