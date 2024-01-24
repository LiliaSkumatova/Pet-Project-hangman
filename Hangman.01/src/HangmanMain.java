
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HangmanMain {

	// declare variables and arrays
	// payers names
	static String[] names;
	// total wins
	static int[] wins;
	// total losses
	static int[] losses;
	// all words
	static String[] words;
	static String currentPlayer = "";
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static int totalNumberOfRecords = 0;
	static String tempNames;
	static int tempWins;
	static int tempLosses;

	public static void main(String[] args) throws IOException 
	{

		// checks if text file is empty we have to do this in case if user starts with
		// empty DB and add player from menu
		//checkRecordsInTxtFile method
		boolean recordsFlag = checkRecordsInTxtFile();
		if (recordsFlag) 
		{
			readFiles();//comment
		}//end if

		// main menu 
		boolean executingMainMenu = true;
		while (executingMainMenu) 
		{
			int choice = 0;
			String choiceString = "";
			Scanner sc = new Scanner(System.in);
			printMainMenu();
			choiceString = sc.next();
			if(isInteger(choiceString)) 
			{
				choice = Integer.parseInt(choiceString);
			}//end if
			
			switch (choice) 
			{
			case 1:
				//chooseThePlayer method
				chooseThePlayer();

				break;
			case 2:
				//newPlayerRecord method
				newPlayerRecord();
				break;
			case 3:
				//modifyPlayerRecord method
				modifyPlayerRecord();
				break;
			case 4:
				//deletePlayerRecord method
				deletePlayerRecord();
				break;
			case 5:
				//sortRecords method
				sortRecords();
				break;
			case 6:
				//searchRecords method
				searchRecords();
				break;
			case 7:
				// exit
				executingMainMenu = false;
				//printHangmanPicture method
				printHangmanPicture();
				System.exit(0);
				break;
			default:
				System.out.println("Wrong Input!!");

			}//end switch
		}//end while
	}//end main

	 private static boolean isInteger(String choiceString) {
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

	/**
	   * This method is used to search for a specific player by comparing the search to the names array and seeing if 
	   * the output is equal to zero to print out the specific player and their record of wins and losses.
	   */
	private static void searchRecords() throws IOException 
	{
		String search = "";
		boolean informationInput = true;
		while (informationInput) 
		{
			System.out.println("Enter the Name to get list or exit to go back to main menu ");
			search = reader.readLine().trim();
			if (search.equals("")) 
			{
				System.out.println("Input cannot be empty!!!");
			} // end if
			else if(search.equals("exit")) 
			{
				informationInput = false;
			}//end else if
			else
			{
				informationInput = false;
				
			} // end else

		} // end while
		boolean recordFound = false;
		for (int i = 0; i < names.length; i++) 
		{
			// compare search to names in array to find the names
			if (search.compareTo(names[i]) == 0) 
			{
				recordFound = true;
				System.out.printf("%-20s %-20s %-20s\n", "Name", "Wins", "Losses");
				System.out.printf("%-20s %-20s %-20s\n", names[i], Integer.toString(wins[i]),
						Integer.toString(losses[i]));
			}//end if
		} // end for
		if(!recordFound) {
			System.out.println("The player name was not found!");
		}

	}//end searchRecords
		

	
	 /**
	   * This method is used to print out menu that asks for the information to 
	   * be sorted by name, wins, or losses.
	   */
	private static void sortRecords() 
	{
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
				sortByName();
				break;
			case 2:
				// sortByWins method
				sortByWins();
				break;
			case 3:
				// sortByLosses method
				sortByLosses();
				break;
			case 4:
				quit = true;
				break;
			default:
			}// end switch
		} // end while

	}// end sortRecords

	
	 /**
	   * This method is used to sort the array according to the number of losses 
	   * It sorts the array by the player who has the most losses to the least
	   */
	private static void sortByLosses() 
	{
		for (int i = 0; i < losses.length - 1; i++) 
		{
			int min = i;
			for (int j = (i + 1); j < losses.length; j++) 
			{
				if (losses[j] > losses[min]) 
				{
					min = j;
				} // end if
			} // end for
			if (min != i) 
			{
				// swapping
				tempWins = wins[min];
				tempNames = names[min];
				tempLosses = losses[min];
				wins[min] = wins[i];
				names[min] = names[i];
				losses[min] = losses[i];
				wins[i] = tempWins;
				names[i] = tempNames;
				losses[i] = tempLosses;
			} // end if
		} // end for
		// print output array
		System.out.printf("%-20s %-20s %-20s\n", "Losses", "Wins", "Name");
		System.out.println("------------------------------------------------------------");
		for (int i = 0; i < names.length; i++) 
		{
			System.out.printf("%-20s %-20s %-20s\n", Integer.toString(losses[i]), Integer.toString(wins[i]), names[i]);

		}//end for
	}//end sortByLosses

	 /**
	   * This method is used to sort the array according to the number of wins 
	   * It sorts the array by the player who has the most wins to the least
	   */
	private static void sortByWins() 
	{
		for (int i = 0; i < wins.length - 1; i++) 
		{
			int min = i;
			for (int j = (i + 1); j < wins.length; j++) 
			{
				if (wins[j] > wins[min]) 
				{
					min = j;
				} // end if
			} // end for
			if (min != i) 
			{
				// swapping
				tempWins = wins[min];
				tempNames = names[min];
				tempLosses = losses[min];
				wins[min] = wins[i];
				names[min] = names[i];
				losses[min] = losses[i];
				wins[i] = tempWins;
				names[i] = tempNames;
				losses[i] = tempLosses;
			} // end if
		} // end for
		// print output array
		System.out.printf("%-20s %-20s %-20s\n", "Wins", "Losses", "Name");
		System.out.println("------------------------------------------------------------");
		for (int i = 0; i < names.length; i++) 
		{
			System.out.printf("%-20s %-20s %-20s\n", wins[i], losses[i], names[i]);

		}//end for
	}// end sortByWins

	
	 /**
	   * This method is used to sort the array according to the name. It sorts the array in alphabetical order.
	   */
	private static void sortByName() 
	{
		for (int k = 0; k < names.length; k++) 
		{
			for (int j = k + 1; j < names.length; j++) 
			{
				if (names[j] != null) 
				{

					// to compare one string with other strings
					if (names[k].compareTo(names[j]) > 0) 
					{
						// swapping
						tempNames = names[k];
						tempWins = wins[k];
						tempLosses = losses[k];
						names[k] = names[j];
						wins[k] = wins[j];
						losses[k] = losses[j];
						names[j] = tempNames;
						wins[j] = tempWins;
						losses[j] = tempLosses;
					} // end if
				} // end if
				else 
				{
					break;
				} // end else
			} // end for
		} // end for
		
		//printPlayersDataBase method
		printPlayersDataBase();

	}//end sortByName

	 /**
	   * This method is used to modify a specific player by comparing the user's input to the the names array
	   * and seeing if the output is equal to zero.
	   * If it is equal to zero the method then asks for the new name, amount of wins, and losses and replaces the record
	   */
	private static void modifyPlayerRecord() throws NumberFormatException, IOException 
	{
		String namePlayer;
		// flag to hold the array position
		int flag = 0;
		// counter
		int i;
		boolean found = false;
		// ask for input
		System.out.println("What is the player you wish to modify? or type exit");
		namePlayer = reader.readLine();
		for (i = 0; i <= names.length - 1; i++) 
		{
			// If the record exists, mark it
			if (namePlayer.compareTo(names[i]) == 0) 
			{
				//index in names
				flag = i;
				found = true;
			} // end if
		} // end for
		if (found) 
		{
			boolean input = true;
			while (input) 
			{
				// Enter modified information
				System.out.println("Enter NEW Name: ");
				String newName = reader.readLine();
				//checks if player already exists
				boolean userExists = checkTheName(newName);
				if (userExists) 
				{
					System.out.println("This name is already taken!");
					continue;
				}//end if 
				else 
				{
					input = false;
				}//end else
				names[flag] = newName;
				System.out.println("Enter NEW Wins: ");
				wins[flag] = Integer.parseInt(reader.readLine());
				System.out.println("Enter NEW Losses: ");
				losses[flag] = Integer.parseInt(reader.readLine());
				
			}//end while
			
			//printPlayersDataBase method
			printPlayersDataBase();
			//writeFile method
			writeFile(names.length);
		} // end if
		else if (namePlayer.equals("exit"))
		{
			found = false;
		}//end else if
		else {
			System.out.println("Name not found");
		}

	}//end modifyPlayerRecord

	 /**
	   * This method is used to search for a specific player by comparing the delPLayer to names and if the name 
	   * is found deleting it from names, wins, losses, and shifting the array
	   */
	private static void deletePlayerRecord() throws IOException 
	{
		// string to hold user input
		String delPlayer;
		// flag to hold position in array
		int flag = 0;
		// counter
		int i;
		boolean found = false;
		// ask for input
		System.out.print("What is the Player's name you wish to delete? or type exit");
		delPlayer = reader.readLine().trim();
		// loop through the names
		for (i = 0; i <= names.length - 1; i++) 
		{
			// If we find it, mark the spot!
			if (delPlayer.compareTo(names[i]) == 0) 
			{
				flag = i;
				found = true;
			} // end if
		} // end for
		if(found == true)
		{

			for (i = flag; i <= names.length - 2; i++) 
			{
				// shift the arrays
				names[i] = names[i + 1];
				wins[i] = wins[i + 1];
				losses[i] = losses[i + 1];
			} // end for

			// update total
			names[names.length - 1] = null;
			wins[names.length - 1] = 0;
			losses[names.length - 1] = 0;
			//writeFile method where the program does not save values with null
			writeFile(names.length - 1);
			//printPayersDataBase method
			printPlayersDataBase();

		} // end if
		else if (delPlayer.equals("exit")) 
		{
			found = false;
		}//end else if
		else 
		{
			System.out.println("Name not found");
		}//end else

	}//end deletePLayerRecord


	 /**
	   * This method is used to check if txt file has at least one line of code
	   */
	private static boolean checkRecordsInTxtFile() throws IOException 
	{

		//create BufferedReader for input
		BufferedReader br = new BufferedReader(new FileReader("players.txt"));
		String line;
		while ((line = br.readLine()) != null) 
		{
			try 
			{
				// first line has to be int if not throw error
				int totalNumberOfRecords = Integer.valueOf(line);
				return true;
			}//end try
			catch (NumberFormatException e) 
			{
				return false;
			}//end catch

		}//end while

		return false;
	}//end checkRecordInTxtFile

	 /**
	   * This method is used to choose a player
	   * By asking for the name of the player and comparing it to the names array
	   * If the name is equal to a name in the names array the game begins
	   */
	private static void chooseThePlayer() throws IOException 
	{
		// we can not choose players if they are not existing
		if (names != null && names.length > 0) 
		{
			String name = "";
			boolean informationInput = true;
			//create BufferedReader for input
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while (informationInput) 
			{
				// ask for name number
				String border = "#############################################################";
				System.out.println(border);
				System.out.printf("\n%20s%s\n", "", "Input the player name or type exit");
				for (int i = 0; i < names.length; i++) 
				{
					// list the players from 1
					String player = (i + 1) + ". " + names[i];
					System.out.printf("%15s%s\n", "", player);

				}//end for
				System.out.printf("\n%15s%s", "", "Enter choice:");
				name = reader.readLine().trim();
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
					for (int i = 0; i < names.length; i++) 
					{
						if (name.equals(names[i])) 
						{
							informationInput = false;
							currentPlayer = names[i];
							//startTheGame method
							startTheGame();
							break;
						}//end if
						if (i == names.length - 1) 
						{
							System.out.println("\nThe name does not exist, try again!\n");
						}//end if
					}//end for

				}//end else
			}//end while
		}//end if
		else 
		{
			System.out.println("\nThere are no players in a system!\n");
		}//end else

	}//end chooseThePlayer

	 /**
	   * This method is used to print out menu that asks the player if they would
	   * like to start the game or return to the main menu
	   */
	private static void startTheGame() throws IOException 
	{
		//print out player who is currently playing
		System.out.println();
		System.out.println("################## Hi " + currentPlayer + " ##################");
		System.out.println();
		//printPLayerDataBaseByName
		printPlayerDataBaseByName(currentPlayer);
		System.out.println();

		//menu
		boolean startGame = true;
		int choice = 0;
		while (startGame) 
		{
			boolean inputFlag = false;
			while (!inputFlag) 
			{

				System.out.println("To start the game type: 1");
				System.out.println("To go back to main menu type: 2");

				Scanner sc = new Scanner(System.in);
				try 
				{
					String inputTmp = sc.nextLine();
					choice = Integer.parseInt(inputTmp);
					inputFlag = true;
				}//end try
				catch (NumberFormatException e) 
				{
					System.out.println("Wrong Input, has to be a number!");
				}//end catch
			}//end while

			switch (choice) 
			{
			case 1:
				//play method
				play();

				break;
			case 2:
				// exit to main menu
				startGame = false;
				break;
			default:
				System.out.println("Wrong Input!!");

			}//end switch
		}//end while

	}//end startGame

	 /**
	   * This method is used to show player information
	   * creates a random number to choose a random word, starts the game
	   */
	private static void play() throws IOException 
	{
		System.out.println("################## Hi " + currentPlayer + " ##################");
		//printPlayerDataBaseByName method
		printPlayerDataBaseByName(currentPlayer);
		//the char array of size 26 because the English alphabet has 26 letter in our case it the max size we need to keep all guessed letters 
		char[] userCharactersGuesses = new char[26];
		int numberCharactersUserTried = 0;
		Random random = new Random();
		// getting random word from array words index is a random
		String theRandomWord = words[random.nextInt(words.length)];
		// using char array to keep track of right guessed letters
		char tmpWord[] = new char[theRandomWord.length()];
		// for debugging
		int totalWrongAnswers = 6;
		int wrongAnswers = 0;

		//printTheWordToTheConsole method
		printTheWordToTheConsole(numberCharactersUserTried, theRandomWord, userCharactersGuesses, tmpWord);

		while (wrongAnswers < totalWrongAnswers) 
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("Please enter a letter:");
			System.out.println();
			String choice = sc.nextLine().toLowerCase();
			// the input has to be greater then 0 or input is empty
			if (choice.length() > 0) 
			{
				char tmpChar = choice.charAt(0);
				// only if input is letter else wrong input
				if (Character.isLetter(tmpChar)) 
				{
					// checking if letter unique else wrong input
					boolean unique = checkIfLetterUnique(userCharactersGuesses, numberCharactersUserTried, tmpChar);
					if (unique) 
					{
						// add letter to array
						userCharactersGuesses[numberCharactersUserTried] = tmpChar;
						// increment total letters used
						numberCharactersUserTried++;
						// checks if word contains the character
						boolean containsFlag = theRandomWord.contains(Character.toString(tmpChar));
						// prints to the console
						//printTheWordToTheConsole method
						printTheWordToTheConsole(numberCharactersUserTried, theRandomWord, userCharactersGuesses,
								tmpWord);
						// if does not contain increments wrong answer
						if (!containsFlag) 
						{
							wrongAnswers++;
							System.out.println();
							System.out.println("#                                                 #");
							System.out.println("#  The letter: { " + tmpChar + " } doesn't exist in this word   #");
							System.out.println("#                                                 #");
							System.out.println(
									"#  The word has: " + theRandomWord.length() + " letters.                       #");
							System.out
									.println("#  Wrong Attempts: " + wrongAnswers + "                               #");
							System.out.println("#                                                 #");
							System.out.println("###################################################");

						}//end if
						else 
						{
							System.out.println();
							System.out.println("#                                                 #");
							System.out.println("#  The letter: { " + tmpChar + " } exists in this word          #");
							System.out.println("#                                                 #");
							System.out.println(
									"#  The word has: " + theRandomWord.length() + " letters.                       #");
							System.out
									.println("#  Wrong Attemps: " + wrongAnswers + "                               #");
							System.out.println("#                                                 #");
							System.out.println("###################################################");
						}//end else
					}//end if
					else 
					{
						System.out.println("The letter: " + tmpChar + ", already used!!");
					}//end else

				}//end if
				else 
				{
					System.out.println("Wrong input!!");
				}//end else
			}//end if
			else 
			{
				System.out.println("Wrong input!!");
			}//end else
			System.out.println();
			// check if players wins
			if (theRandomWord.equals(new String(tmpWord))) 
			{
				wrongAnswers = totalWrongAnswers + 1;
				System.out.println("###################################################");
				System.out.println("                   WON                  ");

				boolean flag = false;
				int index = 0;
				for (int i = 0; i < names.length; i++) 
				{

					if (currentPlayer.equals(names[i])) 
					{
						flag = true;
						index = i;
					}//end if
				}//end for
				if (flag) 
				{
					wins[index] = wins[index] + 1;
				}//end if
				//updateRecords method
				updateRecords();
				//printPlayerDataBaseByName
				printPlayerDataBaseByName(currentPlayer);

			}//end if
			// check if player losses
			if (wrongAnswers == totalWrongAnswers) 
			{
				System.out.println("###################################################");
				System.out.println("                  YOU LOSE                  ");

				boolean flag = false;
				int index = 0;
				for (int i = 0; i < names.length; i++) 
				{

					if (currentPlayer.equals(names[i])) 
					{
						flag = true;
						index = i;
					}//end if
				}//end for
				if (flag) 
				{
					losses[index] = losses[index] + 1;
				}
				updateRecords();
				printPlayerDataBaseByName(currentPlayer);
			}//end if
		}//end while

	}//end play

	 /**
	   * This method is used to check if the letter has already being guessed
	   * @param userCharactersGuesses the characters the player can guess
	   * @param numberCharactersUserTried the characters the player has already guessed
	   * @param tmpChar
	   */
	private static boolean checkIfLetterUnique(char[] userCharactersGuesses, int numberCharactersUserTried,
			char tmpChar) 
	{
		for (int i = 0; i < numberCharactersUserTried; i++) 
		{
			if (userCharactersGuesses[i] == tmpChar) 
			{
				return false;
			}//end if
		}//end for
		return true;
	}//end checkIfLetterUnique

	 /**
	   * This method is used to create the space and lines to represent the word for 
	   * the player to know how many letters are in the word
	   * @param numerCharactersUserTried the characters the player has already guessed
	   * @param theRandomWord the word the player is guessing
	   * @param userCharactersGuesses the characters the player can guess
	   * @param tmpWord used to output to the console, the right guessed letters or low dashes 
	   */
	private static void printTheWordToTheConsole(int numerCharactersUserTried, String theRandomWord,
			char[] userCharactersGuesses, char[] tmpWord) 
	{

		// contains the word the user is guessing
		char charArrayWord[] = theRandomWord.toCharArray();

		// total number of inpputed letters
		if (numerCharactersUserTried > 0) 
		{
			for (int i = 0; i < numerCharactersUserTried; i++) 
			{
				char tmpChar = userCharactersGuesses[i];
				for (int j = 0; j < charArrayWord.length; j++) 
				{
					if (tmpChar == charArrayWord[j]) 
					{
						tmpWord[j] = tmpChar;
					}//end if
				}//end for
			}//end for
			System.out.println("###################################################");
			System.out.println("#                                                 #");
			String ouputString = "    ";
			for (int k = 0; k < tmpWord.length; k++) 
			{
				if (tmpWord[k] == '\u0000') 
				{
					ouputString = ouputString + " _";
				}//end if 
				else 
				{
					ouputString = ouputString + " " + tmpWord[k];
				}//end else

			}//end for
			System.out.println(ouputString);

		}//end if
		else 
		{
			String ouputString = "    ";
			// the numerCharactersUserTried is less then zero only when game started
			System.out.println();
			System.out.println("###################################################\n");
			System.out.println("The word has: " + theRandomWord.length() + " letters.  ");
			for (int i = 0; i < theRandomWord.length(); i++) 
			{
				ouputString = ouputString + " _";
			}//end for
			System.out.println(ouputString);
			System.out.println();
		}//end else

	}//end printTheWordToTheConsole

	
	 /**
	   * This method is used to read the file players.txt and save the number of records in totalNumberOfRecords
	   * Save the names of players in names[]
	   * Save the number of wins of players in wins[]
	   * Save the number of losses of players in losses[]
	   */
	public static void readFiles() throws IOException 
	{
		//create BufferedReader for input
		BufferedReader reader = new BufferedReader(new FileReader("players.txt"));

		// keeps the number of records
		// first line has the total number of records I need this information to declare
		// the size of arrays
		String line = reader.readLine();
		totalNumberOfRecords = Integer.valueOf(line);
		// using totalNumberOfRecords to initialize arrays because on first line text
		// file have record how many total records
		names = new String[totalNumberOfRecords];
		wins = new int[totalNumberOfRecords];
		losses = new int[totalNumberOfRecords];

		for (int i = 0; i < totalNumberOfRecords; i++) 
		{
			names[i] = reader.readLine();
			wins[i] = Integer.valueOf(reader.readLine());
			losses[i] = Integer.valueOf(reader.readLine());
		}//end for
		reader.close();
		// get all the words from the txt file
		reader = new BufferedReader(new FileReader("words.txt"));
		totalNumberOfRecords = 0;
		line = reader.readLine();
		totalNumberOfRecords = Integer.valueOf(line);
		words = new String[totalNumberOfRecords];

		for (int k = 0; k < totalNumberOfRecords; k++) 
		{
			words[k] = reader.readLine();
		}//end for
		reader.close();
	}//end readFiles

	 /**
	   * This method is used to iterate through the arrays and print them out
	   */
	public static void printPlayersDataBase() 
	{
		System.out.printf("%-20s %-20s %-20s\n", "Name", "Wins", "Losses");
		System.out.println("------------------------------------------------------------");
		for (int i = 0; i < names.length; i++) 
		{
			System.out.printf("%-20s %-20s %-20s\n", names[i], Integer.toString(wins[i]), Integer.toString(losses[i]));

		}//end for
	}//end printPlayersDataBase

	 /**
	   * This method is used to print a specific player and the player's wins and losses
	   * @param name variable used to hold the name of specific player playing
	   */
	public static void printPlayerDataBaseByName(String name) {
		System.out.printf("%-20s %-20s %-20s\n", "Name", "Wins", "Loses");
		System.out.println("------------------------------------------------------------");
		for (int i = 0; i < names.length; i++) 
		{
			if (name.equals(names[i])) 
			{
				
				System.out.printf("%-20s %-20s %-20s\n", names[i], Integer.toString(wins[i]),
						Integer.toString(losses[i]));
			}//end if
		}//end for
	}//end printPlayerDataBaseByName

	
	 /**
	   * This method is used to print out the main menu
	   */
	private static void printMainMenu() 
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

	
	 /**
	   * This method is used to save a new record in the arrays
	   * By asking for the player's name and saving it in name String
	   * saving automatically zero for wins as they haven't played yet
	   * saving automatically zero for losses as they haven't played yet
	   * Then inputing name, wins, losses into writeNewPlayerToFile method
	   */
	public static void newPlayerRecord() throws IOException 
	{
		boolean input = true;
		String name = "";
		String losses = "0";
		String wins = "0";

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
					else if (checkTheName(name)) 
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
					writeNewPlayerToFile(name, wins, losses);
					currentPlayer = name;
					//startTheGame method
					startTheGame();
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

	}//end newPlayerRecord

	 /**
	   * This method is used to check if name already exists
	   * @param name variable that player inputs
	   */
	private static boolean checkTheName(String name) 
	{
		for (int i = 0; i < names.length; i++) 
		{
			if (name.equals(names[i])) 
			{
				return true;
			}//end if
		}//end for

		return false;
	}//end checkTheName 

	
	// saves modified information
	
	 /**
	   * This method is used to write the name, wins, and losses in the file players.txt
	   * @param length is used to determine the length of array
	   */
	public static void writeFile(int length) throws IOException 
	{
		PrintWriter writer;
		// if arrays are empty no reason to write to file
		if (names != null && names.length > 0 && wins != null && wins.length > 0 && losses != null
				&& losses.length > 0) 
		{

			// save to the file from the arrays
			writer = new PrintWriter("players.txt");
			writer.println(length);
			for (int i = 0; i < names.length; i++) 
			{
				if (names[i] != null) 
				{
					//writes the names, wins, and losses in players.txt
					writer.println(names[i]);
					writer.println(wins[i]);
					writer.println(losses[i]);
				}//end if
			}//end for
			writer.close();
			//readFiles method
			readFiles();
		}//end if

	}//end writeFile


	 /**
	   * This method is used to write the name, win, and loss in the file database.txt
	   * @param name the name of player
	   * @param win the number of wins of the player
	   * @param loss the number of losses of the player
	   */
	public static void writeNewPlayerToFile(String name, String win, String loss) throws IOException {
		PrintWriter writer;
		// if arrays are empty no reason to write to file
		if (names != null && names.length > 0 && wins != null && wins.length > 0 && losses != null
				&& losses.length > 0) 
		{

			// save to the file from the arrays
			writer = new PrintWriter("players.txt");
			writer.println(names.length + 1);
			for (int i = 0; i < names.length; i++) 
			{
				//writes the names, wins, and losses in players.txt
				writer.println(names[i]);
				writer.println(wins[i]);
				writer.println(losses[i]);
			}//end for
			//writes the new record the name, win, and loss in players.txt
			writer.println(name);
			writer.println(win);
			writer.println(loss);
			writer.close();
			//readFiles method
			readFiles();
		}//end if
		else 
		{
			// it is first record
			writer = new PrintWriter("players.txt");
			writer.println(1);
			//writes the new record the name, win, and loss in players.txt
			writer.println(name);
			writer.println(win);
			writer.println(loss);
			writer.close();
			//readFiles method
			readFiles();
		}//end else

	}//end writeNewPlayerToFile

	 /**
	   * This method is used to update the number of wins or losses in a players record
	   */
	public static void updateRecords() throws IOException 
	{
		// if arrays are empty no reason to write to file
		if (names.length > 0 && wins.length > 0 && losses.length > 0) 
		{

			// save to the file from the arrays
			PrintWriter writer = new PrintWriter("players.txt");
			writer.println(names.length);
			for (int i = 0; i < names.length; i++) 
			{
				//writes the names, wins, and losses in players.txt
				writer.println(names[i]);
				writer.println(wins[i]);
				writer.println(losses[i]);
			}//end for
			writer.close();
			//readFiles method
			readFiles();
		}//end if
		else 
		{
			System.out.println("There are no records to write to the file!!!");
		}//end else

	}//end updateRecords

	 /**
	   * This method is used to print image of Hangman
	   */
	private static void printHangmanPicture() 
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
}//end class