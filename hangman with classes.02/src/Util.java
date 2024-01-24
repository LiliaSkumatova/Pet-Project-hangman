import java.util.List;

public class Util {

	 /**
	   * This method is used to check if name already exists
	   * @param name variable that player inputs
	 * @param records 
	   */
	public static boolean checkTheName(String name, List<Player> records) 
	{
		for (int i = 0; i < records.size(); i++) 
		{
			if (name.equals(records.get(i).retrieveName())) 
			{
				return true;
			}//end if
		}//end for

		return false;
	}//end checkTheName 
	
	/**
	   * This method is used to check if name already exists
	   * @param name variable that player inputs
	 * @param records 
	   */
	public static int checkThePlayer(String name, List<Player> records) 
	{
		for (int i = 0; i < records.size(); i++) 
		{
			if (name.equals(records.get(i).retrieveName())) 
			{
				return i;
			}//end if
		}//end for

		return -1;
	}//end checkTheName 
	
	/**
	 * Print the names of the players in the system to the screen
	 */
	public static void printPlayers(List<Player> records) 
	{
		for (int i = 0; i < records.size(); i++) 
		{
			// list the players from 1
			String player = (i + 1) + ". " + records.get(i).retrieveName();
			System.out.printf("%15s%s\n", "", player);

		}//end for
	}
	
	public static void printHangmanPicture() 
	{
		System.out.println("########################## Goodbye ##########################\n\n");
		System.out.println("          ===================                                   ");
		System.out.println("          #                  |                                  ");
		System.out.println("          #                  |                                  ");
		System.out.println("          #                  |                                  ");
		System.out.println("          #                  *                                  ");
		System.out.println("          #               *     *                               ");
		System.out.println("          #               *     *                               ");
		System.out.println("          #                *   *                                ");
		System.out.println("          #                  *                                  ");
		System.out.println("          #                  |                                  ");
		System.out.println("          #                  #                                  ");
		System.out.println("          #                  #                                  ");
		System.out.println("          #                # # #                                ");
		System.out.println("          #               #  #  #                               ");
		System.out.println("          #              #   #   #                              ");
		System.out.println("          #             #    #    #                             ");
		System.out.println("          #                  #                                  ");
		System.out.println("          #                #   #                                ");
		System.out.println("          #               #     #                               ");
		System.out.println("          #              #       #                              ");
		System.out.println("          #             #         #                             ");
		System.out.println("          #             #         #                             ");
		System.out.println("          #                                                     ");
		System.out.println("#############################################################\n");

	}//end printHangmanPicture
	
	public static boolean isInteger(String choiceString) {
		try 
		{
			Integer.parseInt(choiceString);
		}
		catch(NumberFormatException e) 
		{
			return false;
		}
		catch(NullPointerException e) 
		{
			return false;
		}
		 
		return true;
	}//end method isInteger
	
	public static void printMainMenu() 
	{

		//print menu options
		System.out.printf("%5s%s\n", "", "Main Menu:");
		System.out.printf("%1s%s\n", "", "1. Existing Player");
		System.out.printf("%1s%s\n", "", "2. New Player");
		System.out.printf("%1s%s\n", "", "3. Modify Player");
		System.out.printf("%1s%s\n", "", "4. Delete PLayer");
		System.out.printf("%1s%s\n", "", "5. Sort Records");
		System.out.printf("%1s%s\n", "", "6. Search Records");
		System.out.printf("%1s%s\n\n", "", "7. Quit");
		System.out.printf("%1s%s", "", "Enter choice:");

	}//end printMainMenu
	
	public static void printArrayList(List<Player> records) {
		for(int i = 0; i < records.size(); i++) {
			System.out.println(records.get(i));

		}
	}
}
