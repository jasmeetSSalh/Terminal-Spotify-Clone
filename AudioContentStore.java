// Name: Jasmeet Salh
// Student Id: 501159744
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;


// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{	
		private ArrayList<AudioContent> contents; 																// List all audio content in the store
		
		private HashMap<String, Integer> storeHashMap = new HashMap<String, Integer>();									// HashMap that stores the title of each song or audiobook as a key, and the index of the song or audiobook as a value

		private HashMap<String, ArrayList<Integer>> artistHashMap = new HashMap<String, ArrayList<Integer>>();			// HashMap that stores the artist or author of each song or audiobook as a key, and the index of the song or audiobook as a value

		private HashMap<Song.Genre, ArrayList<Integer>> genreHashMap = new HashMap<Song.Genre, ArrayList<Integer>>();	// HashMap that stores the genre of each song as a key, and the index of the song as a value

		

		public AudioContentStore()
		{
			this.contents = new ArrayList<AudioContent>();											// Create an empty list of audio content
			
		  	// Create some songs audiobooks and podcasts and to store
			
			try {
				File storeReadFile = new File("store.txt");								// reads "store.txt" file 
				Scanner storeFileScanner = new Scanner(storeReadFile);								// creates a scanner to read "store.txt" file

				// while loop goes to through "store.txt" and creates SONG and AUDIOBOOK OBJECTS
				while(storeFileScanner.hasNextLine()){								
					String currentString = storeFileScanner.nextLine();								// reads the first line of "store.txt" file	
					if  (currentString.equals("SONG")){									// if the first line of "store.txt" file is "SONG", then it creates a SONG object
						
						String type = "SONG";
						String id = storeFileScanner.nextLine();
						String title = storeFileScanner.nextLine();
						int year = Integer.parseInt(storeFileScanner.nextLine());
						int length = Integer.parseInt(storeFileScanner.nextLine());
						String artist = storeFileScanner.nextLine();
						String composer = storeFileScanner.nextLine();
						
						String genreString = storeFileScanner.nextLine();
						Song.Genre genre = Song.Genre.valueOf(genreString);
						
						int numLines = Integer.parseInt(storeFileScanner.nextLine());
						String lyrics = "";
						for(int i = 0; i < numLines; i++){
							lyrics += storeFileScanner.nextLine() + "\n";
						}
												
						Song cSong = new Song(title, year, id, type, lyrics, length, artist, composer, genre, lyrics);	// creates a SONG object
						this.contents.add(cSong);																				// adds the SONG object to the contents ArrayList
						
	
					}   else if (currentString.equals("AUDIOBOOK")) {						// if the first line of "store.txt" file is "AUDIOBOOK", then it creates a AUDIOBOOK object
						
						String type = "AUDIOBOOK";
						String id = storeFileScanner.nextLine();									// reads audioBook id
						String title = storeFileScanner.nextLine();									// reads audioBook title
						int year = Integer.parseInt(storeFileScanner.nextLine());					// reads audioBook year	
						int length = Integer.parseInt(storeFileScanner.nextLine());					// reads audioBook length
						String author = storeFileScanner.nextLine();								// reads audioBook author
						String narrator = storeFileScanner.nextLine();								// reads audioBook narrator
						
						int numberOfChapter = storeFileScanner.nextInt();							// reads the number of chapters in the audioBook
						ArrayList<String> chapterTitles = new ArrayList<String>();					// creates an ArrayList that stores the chapter titles

						for (int i = 0; i < numberOfChapter; i++) {									// reads the chapter titles and adds them to the chapterTitles ArrayList
							String currentChapterTitleString = storeFileScanner.nextLine();
							chapterTitles.add(currentChapterTitleString);
						}
						ArrayList<String> chapters = new ArrayList<String>();						// creates an ArrayList that stores the chapters
						
						while(storeFileScanner.hasNextInt()){										// reads the chapters and adds them to the chapters ArrayList
							int numberOfLinesToStore = storeFileScanner.nextInt();
							String chapterAppendString = "";
							for (int i = 0; i < numberOfLinesToStore; i++) {
								String audioBookNextLine = storeFileScanner.nextLine();
								chapterAppendString+=audioBookNextLine+"\n";
							}
							chapters.add(chapterAppendString);
						}

						AudioBook cAudioBook = new AudioBook(title, year, id, type, title, length, author, narrator, chapterTitles, chapters);		// creates a AUDIOBOOK object
						this.contents.add(cAudioBook);																								// adds the AUDIOBOOK object to the contents ArrayList
					}
				
				}
				storeFileScanner.close();																											// closes the scanner
				

				// Stores each SONG or AUDIOBOOK's title as a unique object in a hashmap, so accessing is faster
				for (int i = 0; i < contents.size(); i++) {				// goes through the contents ArrayList, and stores the title of each SONG or AUDIOBOOK as a key, and the index of the SONG or AUDIOBOOK as a value
					String tempKey = contents.get(i).getTitle();		// stores the title of the current SONG or AUDIOBOOK				
					storeHashMap.put(tempKey, i);						// stores the title of the current SONG or AUDIOBOOK as a key, and the index of the SONG or AUDIOBOOK as a value
				}


				// Created a hashmap that keeps track of all the authors and artists in the artistHashMap
				// and of all there creations, which are stored in a ArrayList of indexes of the contents ArrayList

				for (int i = 0; i < contents.size(); i++) {									// goes through the contents ArrayList, and stores the author or artist of each SONG or AUDIOBOOK as a key, and the index of the SONG or AUDIOBOOK as a value
					if(contents.get(i).getType()=="SONG"){									// if the current object is a SONG, then it stores the artist of the SONG as a key, and the index of the SONG as a value
						
						Song currentSong = (Song) contents.get(i);							// stores the current SONG object
						String currentArtistString = currentSong.getArtist();			    // stores the artist of the current SONG object

						if(artistHashMap.containsKey(currentArtistString)){					// if the artistHashMap already contains the artist of the current SONG object, then it adds the index of the current SONG object to the ArrayList of the artist
							artistHashMap.get(currentArtistString).add(i);					// adds the index of the current SONG object to the ArrayList of the artist
						} else {
							ArrayList<Integer> indexArraySong = new ArrayList<Integer>();	// creates a new ArrayList that stores the index of the current SONG object
							indexArraySong.add(i);											// adds the index of the current SONG object to the ArrayList
							artistHashMap.put(currentArtistString, indexArraySong);		    // adds the artist of the current SONG object as a key, and the ArrayList of the index of the current SONG object as a value
						}

					} else if (contents.get(i).getType()=="AUDIOBOOK"){						// if the current object is a AUDIOBOOK, then it stores the author of the AUDIOBOOK as a key, and the index of the AUDIOBOOK as a value
						
						AudioBook currentAudioBook = (AudioBook) contents.get(i);			// stores the current AUDIOBOOK object
						String currentAuthorString = currentAudioBook.getAuthor();			// stores the author of the current AUDIOBOOK object

						if(artistHashMap.containsKey(currentAuthorString)){					// if the artistHashMap already contains the author of the current AUDIOBOOK object, then it adds the index of the current AUDIOBOOK object to the ArrayList of the author
							artistHashMap.get(currentAuthorString).add(i);					// adds the index of the current AUDIOBOOK object to the ArrayList of the author
						} else {	
							ArrayList<Integer> indexArrayAudioBook = new ArrayList<Integer>();	// creates a new ArrayList that stores the index of the current AUDIOBOOK object
							indexArrayAudioBook.add(i);											// adds the index of the current AUDIOBOOK object to the ArrayList
							artistHashMap.put(currentAuthorString, indexArrayAudioBook);		// adds the author of the current AUDIOBOOK object as a key, and the ArrayList of the index of the current AUDIOBOOK object as a value
						}
					}
				}

				// Created a hasmap that keeps track of songs of specific genre in the genreHashMap,
				// and saves each genre's song in a ArrayList of indexes of the contents ArrayList
				
				for (int i = 0; i < contents.size(); i++) {										// goes through the contents ArrayList, and stores the genre of each SONG as a key, and the index of the SONG as a value
					if(contents.get(i).getType().equals("SONG")){						// if the current object is a SONG, because only SONGS have a genre
							Song tempSongGenre = (Song) contents.get(i);						// stores the current SONG object
							Song.Genre currentGenre = tempSongGenre.getGenre();					// stores the genre of the current SONG object

							if(genreHashMap.containsKey(currentGenre)){							// if the genreHashMap already contains the genre of the current SONG object, then it adds the index of the current SONG object to the ArrayList of the genre
								genreHashMap.get(currentGenre).add(i);							// adds the index of the current SONG object to the ArrayList of the genre
							} else {
								ArrayList<Integer> genreIndexs = new ArrayList<Integer>();		// creates a new ArrayList that stores the index of the current SONG object
								genreIndexs.add(i);												// adds the index of the current SONG object to the ArrayList
								genreHashMap.put(currentGenre, genreIndexs);					// adds the genre of the current SONG object as a key, and the ArrayList of the index of the current SONG object as a value
							}
						
					}
				}

				

			} catch (FileNotFoundException e) {															// if the file is not found, then it prints out an error message
				e.getMessage();																			// prints out the error message
			}

		
		}


		
		public AudioContent getContent(int index) throws IndexOutOfBoundsException				// returns the AudioContent object at the specified index
		{	
			if (index < 1 || index > contents.size()){											// if the index is out of bounds, then it throws an exception
				throw new IndexOutOfBoundsException("Index is out of bounds");				// throws an indexOutOfBoundsExceptions 
			}return contents.get(index-1);														// returns the AudioContent object at the specified index
		}
		
		public void listAll() 																	// prints out all the AudioContent objects in the contents ArrayList		
		{
			for (int i = 0; i < contents.size(); i++)											// goes through the contents ArrayList
			{
				int index = i + 1;																// stores the index of the current AudioContent object
				System.out.print("" + index + ". ");											// prints out the index of the current AudioContent object
				contents.get(i).printInfo();													// prints out the information of the current AudioContent object
				System.out.println();															// prints out a new line
			}
		}


		//SEARCH Method that searches for a song by title
		public void searchSong(String title) throws IllegalArgumentException									// searches for a song by title
		{
		
				if (storeHashMap.get(title)==null){																// if the title is null, then it throws an exception
					throw new IllegalArgumentException("No match found "+ title);						// throws an IllegalArgumentException if the title is null
				}
				else {
					int index = storeHashMap.get(title);											// stores the index of the AudioContent object with the specified title
					System.out.print(index+1 + ": " );												// prints out the index of the AudioContent object with the specified title
					contents.get(index).printInfo();												// prints out the information of the AudioContent object with the specified title
				
				}

		}


		//SEARCHA method that searches for a song by artist
		public void searchArtist(String artist) throws IllegalArgumentException									
		{
			if(artistHashMap.get(artist)==null) {												// if the artistHashMap does not contain the artist, then it throws an exception
				throw new IllegalArgumentException("No matches for " + artist);					// throws an IllegalArgumentException if the artist does not exist in the store
			} else {
				ArrayList<Integer> hashMapArrayListIndexes = artistHashMap.get(artist);			// stores the ArrayList of indexes of the AudioContent objects with the specified artist
				for (int i = 0; i < hashMapArrayListIndexes.size(); i++) {						// goes through the ArrayList of indexes of the AudioContent objects with the specified artist	
					System.out.print(hashMapArrayListIndexes.get(i) + 1 + ": ");												// prints out the index of the current - print syntax
					contents.get(hashMapArrayListIndexes.get(i)).printInfo();					// prints out the information of the current AudioContent object
				}
			}
		}


		//SEARCH Method that searches for a song by genre
		public void searchGenre(Song.Genre Genre) {												// searches for a song by genre
			if(genreHashMap.get(Genre)==null){													// if the genreHashMap does not contain the genre, then it throws an exception
				throw new IllegalArgumentException("Genre not found");						// throws an IllegalArgumentException if the genre does not exist in the store		
			} else {
				ArrayList<Integer> hashMapArrayListIndexes = genreHashMap.get(Genre);			// stores the ArrayList of indexes of the AudioContent objects with the specified genre
				for (int i = 0; i < hashMapArrayListIndexes.size(); i++) {						// goes through the ArrayList of indexes of the AudioContent objects with the specified genre
					System.out.print(hashMapArrayListIndexes.get(i) + 1 + ": ");												// prints out the index of the current - print syntax
					contents.get(hashMapArrayListIndexes.get(i)).printInfo();					// prints out the information of the current AudioContent object
				}
			}
		}


		//SEARCHP Method that searches for a song by price
		public void searchAnything(String searchValue) throws IllegalArgumentException									
		{

			boolean flag = false;
			// Iterates through the contents ArrayList and prints out the AudioContent object if the searchValue is contained in the AudioContent object
			for (int i = 0; i < contents.size(); i++) {

				// If the AudioContent object is a Song object, then it casts the AudioContent object to a Song object and checks if the searchValue is contained in the Song object
				if(contents.get(i).getType().equals("SONG")){
					Song tempSong = (Song) contents.get(i);
					if (tempSong.getArtist().contains(searchValue) || tempSong.getTitle().contains(searchValue) || tempSong.getGenre().toString().contains(searchValue) || tempSong.getAudioFile().contains(searchValue) || tempSong.getComposer().contains(searchValue) || tempSong.getLyrics().contains(searchValue) || tempSong.getId().contains(searchValue)) {
						flag = true;
						System.out.print(i+1 + ": ");
						contents.get(i).printInfo();
					}
				} 

				// If the AudioContent object is a AudioBook object, then it casts the AudioContent object to a AudioBook object and checks if the searchValue is contained in the AudioBook object
				else if(contents.get(i).getType().equals("AUDIOBOOK")){
					AudioBook tempAudioBook = (AudioBook) contents.get(i);
					if (tempAudioBook.getAuthor().contains(searchValue) || tempAudioBook.getTitle().contains(searchValue) || tempAudioBook.getAudioFile().contains(searchValue) || tempAudioBook.getNarrator().contains(searchValue) || tempAudioBook.getId().contains(searchValue)) {
						flag = true;
						System.out.print(i+1 + ": ");
						contents.get(i).printInfo();
					} 
					// Checks if the searchValue is contained in the chapters ArrayList of the AudioBook object
					for (int j = 0; j < tempAudioBook.getChapters().size(); j++) {
						if(tempAudioBook.getChapters().get(j).contains(searchValue)){
							flag = true;
							System.out.print(i+1 + ": ");
							contents.get(i).printInfo();
						}
					}
					// Checks if the searchValue is contained in the chapterTitles ArrayList of the AudioBook object
					for (int j = 0; j < tempAudioBook.getChapterTitles().size(); j++) {
						if(tempAudioBook.getChapterTitles().get(j).contains(searchValue)){
							flag = true;
							System.out.print(i+1 + ": ");
							contents.get(i).printInfo();

						}
					}
				} 
			} 
			if(flag == false){
				throw new IllegalArgumentException("No matches for " + searchValue);					// throws an IllegalArgumentException if the searchValue does not exist in the store
			}
		}


		// Download depending on the artist
		// Returns an ArrayList of AudioContent objects with the specified artist's songs or audio books
		// This ArrayList is used to download the songs or audio books of a specified artist by passing the ArrayList to the download method
		public ArrayList<AudioContent> downloadArtist(String artist) {

			ArrayList<AudioContent> returnArrayList = new ArrayList<AudioContent>();					// ArrayList that will be returned

			for (String tempKeyString : artistHashMap.keySet()) {										// goes through the artistHashMap
				if (tempKeyString.equals(artist)) {														// if the current key is equal to the specified artist
					ArrayList<Integer> hashMapArrayListIndexes = artistHashMap.get(tempKeyString);		// stores the ArrayList of indexes of the AudioContent objects with the specified artist

					for (int i = 0; i < hashMapArrayListIndexes.size(); i++) {							// goes through the ArrayList of indexes of the AudioContent objects with the specified artist
						returnArrayList.add(contents.get(hashMapArrayListIndexes.get(i)));				// adds the AudioContent object to the returnArrayList
					}
				}
			}
			return returnArrayList;																		// returns the returnArrayList

		}


		// Download depending on the genre
		// Returns an ArrayList of AudioContent objects with the specified artist's songs or audio books
		// This ArrayList is used to download the songs or audio books, by passing the ArrayList to the download method

		public ArrayList<AudioContent> downloadGenre(Song.Genre currentGenre){							// Returns an ArrayList of AudioContent objects with the specified genre's songs
					
			ArrayList<AudioContent> returnArrayList = new ArrayList<AudioContent>();					// ArrayList that will be returned

			for (Song.Genre tempKeyGenre : genreHashMap.keySet()) {										// goes through the genreHashMap
				if (tempKeyGenre.equals(currentGenre)) {				
					ArrayList<Integer> hashMapArrayListIndexes = genreHashMap.get(tempKeyGenre);		// stores the ArrayList of indexes of the AudioContent objects with the specified genre
					
					for (int i = 0; i < hashMapArrayListIndexes.size(); i++) {							// goes through the ArrayList of indexes of the AudioContent objects with the specified genre
						returnArrayList.add(contents.get(hashMapArrayListIndexes.get(i)));				// adds the AudioContent object to the returnArrayList
					}
				} 
			}
			return returnArrayList;
		}


	
		

}
