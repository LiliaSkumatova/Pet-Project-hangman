import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainMenu extends Menu {
	
	public BufferedReader reader;
	public ArrayList<Player> records;
	public MainMenu(ArrayList<Player> records) {
		reader = new BufferedReader(new InputStreamReader(System.in));
		this.records = records;
	}
	public void showMenu(Game game) {
		// main menu 
		boolean executingMainMenu = true;
		while (executingMainMenu) 
		{
			int choice = 0;
			String choiceString = "";
			Scanner sc = new Scanner(System.in);
			Util.printMainMenu();
			choiceString = sc.next();
			if(Util.isInteger(choiceString)) 
			{
				choice = Integer.parseInt(choiceString);
			}//end if
			
			switch (choice) 
			{
			case 1:
				//chooseThePlayer method
				if(chooseThePlayer() != null) {
					game.startGame(chooseThePlayer());

				}
				break;
			case 2:
				//newPlayerRecord method
				try {
					if(chooseThePlayer() != null) {
						game.startGame(newPlayerRecord());
					}
				} catch (IOException e) {
					System.out.println("Wrong input, when inputting new player name");
				}
				break;
			case 3:
				//modifyPlayerRecord method
				try {
					modifyPlayerRecord();
				} catch (IOException e) {
					System.out.println("That is not a name");
				}
				break;
			case 4:
				//deletePlayerRecord method
				try {
					deletePlayerRecord();
				} catch (IOException e) {
					System.out.println("Incorrect input for what player to delete");
				}
				break;
			case 5:
				//sortRecords method
				sortRecords();
				break;
			case 6:
				//searchRecords method
				try {
					searchRecords();
				} catch (IOException e) {
					System.out.println("Wrong input for searching a player");
				}
				break;
			case 7:
				// exit
				executingMainMenu = false;
				//printHangmanPicture method
				Util.printHangmanPicture();
				System.exit(0);
				break;
			default:
				System.out.println("Wrong Input!!");

			}//end switch
		}//end while
	}

	@Override
	public Player chooseThePlayer(){
		Reader readPlayers = new Reader("players.txt");
		records = readPlayers.readingPlayersInfo();
		
		
		if(records != null && records.size() >0) {
//			String[] names = readPlayers.getNames();
			String name = "";
			boolean informationInput = true;
			while (informationInput) 
			{
				String border = "#############################################################";
				System.out.println(border);
				// ask for name number
				System.out.printf("\n%20s%s\n", "", "Input the player name or type exit");
//				Util.printPlayers(records);
				for (int i = 0; i < records.size(); i++) 
				{
					// list the players from 1
					String player = (i + 1) + ". " + records.get(i).retrieveName();
					System.out.printf("%15s%s\n", "", player);

				}//end for
				System.out.printf("\n%15s%s", "", "Enter choice:");
				try {
					name = reader.readLine().trim();
				} catch (IOException e) {
					System.out.println("Incorrect input of player name");
				}
				// check empty input
				if (name.equals("")) 
				{
					System.out.println("\nThe player name number can not be empty try again!\n");
					continue;
				}//end of 
				else if (name.toLowerCase().equals("exit")) 
				{
					informationInput = false;

				}//end else if
				else 
				{
					for (int i = 0; i < records.size(); i++) 
					{
						if (name.equals(records.get(i).retrieveName())) 
						{
							informationInput = false;
							return records.get(i);

						}//end if
						if (i == records.size() - 1) 
						{
							System.out.println("\nThe name does not exist, try again!\n");
						}//end if
					}//end for

				}//end else
			}//end while
			
		}
		return null;
		
	}

	@Override
	public Player newPlayerRecord() throws IOException {
		if(records == null || records.size() == 0) {
			Reader readPlayers = new Reader("players.txt");
			records = readPlayers.readingPlayersInfo();
		}
		
		boolean input = true;
		String name = "";
		int losses = 0;
		int wins = 0;

		while (input) 
		{
			System.out.println("If you want to create a new player press: 1");
			System.out.println("If you want to exit press: 2");
			String userInput = reader.readLine().trim();

			if (userInput.equals("1")) 
			{
				boolean informationInput = true;
				while (informationInput) 
				{
					// ask for name
					System.out.print("Enter the Name:");
					name = reader.readLine().trim();
					if (name.equals("")) 
					{
						System.out.println("The player name can not be empty try again from the beginning!!");
						continue;
					}//end if
					else if (Util.checkTheName(name, records)) 
					{
						System.out.println("This player name is already taken!");
						continue;
					}//end else if
					else 
					{
						input = false;
					}//end else

					informationInput = false;
					// save new record to the file
					//writeNewPlayerToFile method
					Player newPlayer = new BasicPlayer(name, wins, losses);
					records.add(newPlayer);
					Writer writer = new Writer("players.txt");
					writer.addNewRecord(records);

					return newPlayer;
				}//end while

			}//end if
			else if (userInput.equals("2")) 
			{
				// exit
				input = false;
			}//end else if
			else 
			{
				System.out.println("Wrong input, try again!");
			}//end else
		}//end while
		return null;
		
	}

	@Override
	public void modifyPlayerRecord() throws IOException {
		boolean input = true;
		while(input) {
			System.out.println("What is the player you wish to modify? or type exit");
			Util.printPlayers(records);
			String namePlayer = reader.readLine();
			int x = Util.checkThePlayer(namePlayer, records);
			if (namePlayer.equals("")) 
			{
				System.out.println("The player name can not be empty try again from the beginning!!");
				continue;
			}//end if
			else if(namePlayer.equals("exit")) {
				input = false;
			}
			else if(x < 0) {
				System.out.println("This player does not exisit");
			}
			else if(x  >= 0) {
				boolean input2 = true;
				while(input2) {
					// Enter modified information
					System.out.println("Enter NEW Name: ");
					String newName = reader.readLine();
					if (newName.equals("")) 
					{
						System.out.println("The player name can not be empty try again from the beginning!!");
						continue;
					}//end if
					else if (Util.checkTheName(newName, records)) 
					{
						System.out.println("This player name is already taken!");
						continue;
					}//end else if
					else 
					{
						input2 = false;
					}//end else
					records.get(x).changeName(newName);
					System.out.println("Enter NEW Wins: ");
					records.get(x).changeWins(Integer.parseInt(reader.readLine()));
					System.out.println("Enter NEW Losses: ");
					records.get(x).changeLosses(Integer.parseInt(reader.readLine()));
				}
				
			}
			
		}
		
	//save this to file	
		Writer writer = new Writer("players.txt");
		writer.addNewRecord(records);
	}

	@Override
	public void deletePlayerRecord() throws IOException {
		boolean input = true;
		while(input) {
			System.out.println("What is the player you wish to delete? or type exit");
			Util.printPlayers(records);
			String namePlayer = reader.readLine();
			int x = Util.checkThePlayer(namePlayer, records);
			if (namePlayer.equals("")) 
			{
				System.out.println("The player name can not be empty try again from the beginning!!");
				continue;
			}//end if
			else if(namePlayer.equals("exit")) {
				input = false;
			}
			else if(x < 0) {
				System.out.println("This player does not exisit");
			}
			else if(x  >= 0) {
				records.remove(x);
				
			}
			
		}
		
	//save this to file	
		Writer writer = new Writer("players.txt");
		writer.addNewRecord(records);
		
	}

	@Override
	public void sortRecords() {
		boolean quit = false;
		while (!quit) 
		{
			int choice = 0;

			// menu
			Scanner sc = new Scanner(System.in);
			System.out.println("1. Sort by Name ");
			System.out.println("2. Sort by Wins ");
			System.out.println("3. Sort by Losses ");
			System.out.println("4. Return to Main Menu");
			System.out.println("What is your choice ");
			choice = sc.nextInt();

			switch (choice) 
			{
			case 1:
				// sortByName method
				
				Collections.sort(records, new SortByName());
				Util.printArrayList(records);
				break;
			case 2:
				// sortByWins method
				Collections.sort(records, new SortByWins());
				Util.printArrayList(records);
				break;
			case 3:
				// sortByLosses method
				Collections.sort(records, new SortByLosses());
				Util.printArrayList(records);
				break;
			case 4:
				quit = true;
				break;
			default:
			}// end switch
		} // end while
	}

	@Override
	public void searchRecords() throws IOException {
		String search = "";
		boolean informationInput = true;
		while (informationInput) 
		{
			System.out.println("Enter the Name to get information or exit! ");
			Util.printPlayers(records);
			search = reader.readLine().trim();
			int y = Util.checkThePlayer(search, records);
			if (search.equals("")) 
			{
				System.out.println("Input cannot be empty!!!");
			} // end if
			else if(search.equals("exit")) 
			{
				informationInput = false;
			}//end else if
			else if(y < 0)
			{
				System.out.println("The player does not exsit");
				
			}else {
				System.out.printf("%-20s %-20s %-20s\n", "Name", "Wins", "Losses");
				System.out.printf("%-20s %-20s %-20s\n", records.get(y).retrieveName()
						, records.get(y).retrieveWins(),
						records.get(y).retrieveLosses());
				}

		} // end while
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}


	
	
}
