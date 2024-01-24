import java.util.Comparator;

public class SortByWins implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return o1.retrieveWins() - o2.retrieveWins();
	}
}
