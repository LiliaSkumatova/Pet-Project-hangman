
public abstract class Player {

	public String name;
	public int wins;
	public int losses;
	
	public abstract void play();
	public abstract void printPlayerDataBaseByName();
	public abstract void printName();
	public abstract void updateWins();
	public abstract void updateLosses();
	public abstract String retrieveName();
	public abstract int retrieveWins();
	public abstract int retrieveLosses();
	public abstract void changeName(String newName);
	public abstract void changeWins(int newWins);
	public abstract void changeLosses(int newLosses);
}
