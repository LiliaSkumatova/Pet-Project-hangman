import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

	ArrayList<Player> records;
	MainMenu mainMenu;
	Player currentPlayer;
	private static final String PLAYER_FILENAME = "players.txt";
	private static final String WORDS_FILENAME = "words.txt";


	public Game(){
		records = getAllRecords();
		mainMenu = new MainMenu(records);
		
	}
	
//	public Game(Player currentPlayer){
//		mainMenu = new MainMenu();
//		this.currentPlayer = currentPlayer;
//	}
	public void startGame(Player currentPlayer) {

		this.currentPlayer = currentPlayer;
		playGame();

		
	}

	public void startGame(BasicPlayer player) {
		
		currentPlayer = player;
		
		
		if(currentPlayer != null) {
			
			System.out.println();
			currentPlayer.printName();
			System.out.println();
			//printPLayerDataBaseByName
			currentPlayer.printPlayerDataBaseByName();
			System.out.println();
			
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
					playGame();

					break;
				case 2:
					// exit to main menu
					startGame = false;
					break;
				default:
					System.out.println("Wrong Input!!");

				}//end switch
			}//end while
		}
		
		
		
		
		
	}

	public void playGame() {
		//the char array of size 26 because the English alphabet has 26 letter in our case it the max size we need to keep all guessed letters 
				char[] userCharactersGuesses = new char[26];
				int numberCharactersUserTried = 0;
				Random random = new Random();
				// getting random word from array words index is a random
				Reader readWords = new Reader(WORDS_FILENAME);
				readWords.readingWords();
				String theRandomWord = readWords.getWords()[random.nextInt(readWords.getWords().length)];
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

						currentPlayer.updateWins();
//						boolean flag = false;
//						int index = 0;
//						for (int i = 0; i < currentPlayer.
//								names.length; i++) 
//						{
//
//							if (currentPlayer.equals(names[i])) 
//							{
//								flag = true;
//								index = i;
//							}//end if
//						}//end for
//						if (flag) 
//						{
//							wins[index] = wins[index] + 1;
//						}//end if
						//updateRecords method
						
//						TODO
						updateRecords();
						//printPlayerDataBaseByName
						currentPlayer.printPlayerDataBaseByName();

					}//end if
					// check if player losses
					if (wrongAnswers == totalWrongAnswers) 
					{
						System.out.println("###################################################");
						System.out.println("                  YOU LOSE                  ");

						currentPlayer.updateLosses();
						updateRecords();
						currentPlayer.printPlayerDataBaseByName();
					}//end if
				}//end while

		
	}
	private void updateRecords() {
		//everything in records
		//new info in player
		Writer writer = new Writer(PLAYER_FILENAME);
		writer.updateRecords(records,currentPlayer);
		
		
//		Reader readPlayers = new Reader(PLAYER_FILENAME);
//		records = readPlayers.readingPlayersInfo();
		
//		Reader reader = new Reader(PLAYER_FILENAME);
//		reader.readingPlayersInfo();
//		int index = 0;
//		boolean flag = false;
//		for (int i = 0; i < reader.getNames().length; i++) {
//			if(currentPlayer.retrieveName().equals(reader.getNames()[i])) {
//				flag=true;
//				index = i;
//			}
//		}
//		if(flag) {
//			reader.setWins(null);
//		}
//	
////		Writer writer = new Writer(PLAYER_FILENAME, currentPlayer);
//		
//		
	}
	
	public ArrayList<Player> getAllRecords() {
		Reader readPlayers = new Reader(PLAYER_FILENAME);
		return readPlayers.readingPlayersInfo();
	}

	public void printTheWordToTheConsole(int numerCharactersUserTried, String theRandomWord,
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
	public boolean checkIfLetterUnique(char[] userCharactersGuesses, int numberCharactersUserTried,
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
	}

	
	public List<Player> getRecords() {
		return records;
	}

	public void setRecords(ArrayList<Player> records) {
		this.records = records;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}
	
	
	
}
	
