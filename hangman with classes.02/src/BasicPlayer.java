import java.util.Scanner;

public class BasicPlayer extends Player implements Comparable<BasicPlayer> {

	private String name;
	private int wins;
	private int losses;
	
	public BasicPlayer(String name, int wins, int losses) {
		this.name = name;
		this.wins = wins;
		this.losses = losses;
		
	}
	public BasicPlayer(String name) {
		this.name = name;
		this.wins = 0;
		this.losses = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	@Override
	public void updateWins() {
		this.wins = wins + 1;
	}

	public int getLosses() {
		return losses;
	}

	@Override
	public void updateLosses() {
		this.losses = losses + 1;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", wins=" + wins + ", losses=" + losses + "]";
	}
	@Override
	public void play() {
		//Todo create instants of the game
//		Game game = new Game(this);
//		game.startGame();
	}
	
	@Override
	public void printPlayerDataBaseByName() {
		// TODO add a syso that prints the current players name, wins, and losses
		System.out.printf("%-20s %-20s %-20s\n", "Name", "Wins", "Loses");
		System.out.println("------------------------------------------------------------");
		System.out.printf("%-20s %-20s %-20s\n", name, Integer.toString(wins), Integer.toString(losses));
		
	}
	@Override
	public void printName() {
		System.out.println("################## " + name + " ##################");
		
	}
	@Override
	public String retrieveName() {
		return name;
		
	}
	@Override
	public int retrieveWins() {
		return wins;
	}
	@Override
	public int retrieveLosses() {
		return losses;
	}
	@Override
	public void changeName(String newName) {
		name = newName;
		
	}
	@Override
	public void changeWins(int newWins) {
		wins = newWins;
		
	}
	@Override
	public void changeLosses(int newLosses) {
		losses = newLosses;
		
	}
	@Override
	public int compareTo(BasicPlayer o) {
		int result = this.name.compareTo(o.name);
		return result;
	}
	
	
	
	
	
	
	
	
}
