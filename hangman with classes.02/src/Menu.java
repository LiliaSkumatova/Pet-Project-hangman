import java.io.IOException;

public abstract class Menu {

	public abstract Player chooseThePlayer();
	public abstract Player newPlayerRecord() throws IOException;
	public abstract void modifyPlayerRecord() throws IOException;
	public abstract void deletePlayerRecord() throws IOException;
	public abstract void sortRecords();
	public abstract void searchRecords() throws IOException;
	public abstract void exit();
	
}
