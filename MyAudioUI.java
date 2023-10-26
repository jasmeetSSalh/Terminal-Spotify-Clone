// Name: Jasmeet Salh
// Student Id: 501159744

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
 		AudioContentStore store = new AudioContentStore();													// Create the store
		
		Library mylibrary = new Library();    																// Create my music mylibrary

		ArrayList<AudioContent> currentlyDownloadedContent = new ArrayList<AudioContent>();					// ArrayList that stores the currently downloaded content


		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print(">");
				// try block used to catch exceptions
				// Process keyboard actions
				while (scanner.hasNextLine())
				{
					try{
						String action = scanner.nextLine();

						if (action == null || action.equals("")) 
						{
							System.out.print("\n>");
							continue;
						}
						else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
							return;
						
						else if (action.equalsIgnoreCase("STORE"))	// List all songs
						{
							store.listAll(); 
						}
						else if (action.equalsIgnoreCase("SONGS"))	// List all songs
						{
							mylibrary.listAllSongs(); 
						}
						else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
						{
							mylibrary.listAllAudioBooks(); 
						}
						else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
						{
							mylibrary.listAllPodcasts(); 
						}
						else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
						{
							mylibrary.listAllArtists(); 
						}
						else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
						{
							mylibrary.listAllPlaylists(); 
						}
						// Download audiocontent (song/audiobook/podcast) from the store 
						// Specify the index of the content
						else if (action.equalsIgnoreCase("DOWNLOAD")) 
						{
							System.out.print("From store content index: ");  		// print syntax
							int startIndex = scanner.nextInt(); 					// get the number the user types

							System.out.print("To store content index: "); 		// print syntax
							int endIndex = scanner.nextInt(); 						// get the number the user types


							for (int i = startIndex; i < endIndex+1; i++) {
								AudioContent content = store.getContent(i); 		// get the content from the store
								
								currentlyDownloadedContent.add(content);			// add the content to the currently downloaded content
							}

							mylibrary.download(currentlyDownloadedContent);	
							currentlyDownloadedContent.clear();						// download the content to the library
												
						}
						// Get the *library* index (index of a song based on the songs list)
						// of a song from the keyboard and play the song 
						else if (action.equalsIgnoreCase("PLAYSONG")) 
						{
							// Print error message if the song doesn't exist in the library
							System.out.print("Song Number: ");  		// print syntax 
							int libraryIndex = scanner.nextInt(); 		// get the number the user types
							mylibrary.playSong(libraryIndex); 			// calls playsong in user specified index
						}
						// Print the table of contents (TOC) of an audiobook that
						// has been downloaded to the library. Get the desired book index
						// from the keyboard - the index is based on the list of books in the library
						else if (action.equalsIgnoreCase("BOOKTOC")) 
						{
						// Print error message if the book doesn't exist in the library
							System.out.print("Audio Book Number: ");    // print syntax 
							int AudioBookIndex = scanner.nextInt(); 	  // get the number the user types
							mylibrary.printAudioBookTOC(AudioBookIndex);  // calls playsong in user specified index
						}
						// Similar to playsong above except for audio book
						// In addition to the book index, read the chapter 
						// number from the keyboard - see class Library
						else if (action.equalsIgnoreCase("PLAYBOOK")) 
						{

							System.out.print("Audio Book Number: ");  // print syntax 
							int AudioBookIndex = scanner.nextInt(); 	// get the number the user types
							System.out.println();						// new line
							System.out.print("Chapter: ");  			// print syntax 
							int ChapterIndex = scanner.nextInt(); 		// get the number the user types
							System.out.println();
							mylibrary.playAudioBook(AudioBookIndex, ChapterIndex); // calls playsong in user specified index
						}
						// Print the episode titles for the given season of the given podcast
						// In addition to the podcast index from the list of podcasts, 
						// read the season number from the keyboard
						// see class Library for the method to call
						else if (action.equalsIgnoreCase("PODTOC")) 
						{
							System.out.print("Podcast Number: ");  	// print syntax 
							int PodcastIndex = scanner.nextInt(); 		// get the number the user types
							System.out.println();						// new line
							System.out.print("Season: ");  			// print syntax
							int SeasonIndex = scanner.nextInt(); 		// get the number the user types

							mylibrary.printPodcastEpisodes(PodcastIndex, SeasonIndex); // calls playsong in user specified index
						}
						// Similar to playsong above except for podcast
						// In addition to the podcast index from the list of podcasts, 
						// read the season number and the episode number from the keyboard
						// see class Library for the method to call
						else if (action.equalsIgnoreCase("PLAYPOD")) 
						{
							System.out.print("Audio Book Number: ");  // print syntax 
							int PodcastIndex = scanner.nextInt(); 		// get the number the user types
							System.out.println();						// new line
							System.out.print("Season: ");  			// print syntax
							int SeasonIndex = scanner.nextInt(); 		// get the number the user types
							System.out.println();						// new line
							System.out.print("Episode: ");  			// print syntax
							int EpisodeIndex = scanner.nextInt(); 		// get the number the user types
							System.out.println();						// new line

							mylibrary.playPodcast(PodcastIndex, SeasonIndex, EpisodeIndex); // calls playsong in user specified index

						}
						// Specify a playlist title (string) 
						// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
						// see class Library for the method to call
						else if (action.equalsIgnoreCase("PLAYALLPL")) 
						{
							System.out.print("Playlist Title: ");    	// print syntax 
							String playlistTitle = scanner.nextLine(); 	// gets the playlist title
							mylibrary.playPlaylist(playlistTitle);	   	// call the method in library to play the playlist

							// print all the songs in the playlist, whereas the PLAYPL just lists the song in the list

						}
						// Specify a playlist title (string) 
						// Read the index of a song/audiobook/podcast in the playist from the keyboard 
						// Play all the audio content 
						// see class Library for the method to call
						else if (action.equalsIgnoreCase("PLAYPL")) 
						{
							System.out.print("Playlist Title: ");      			// print syntax 
							String playlistTitle = scanner.nextLine();   			// gets the playlist title
							System.out.print("Content Number: ");		 			// print syntax 
							int contentNumber = scanner.nextInt();		 			// gets the content number
							mylibrary.playPlaylist(playlistTitle, contentNumber-1); // call the method in library to play the playlist
						}
						// Delete a song from the list of songs in mylibrary and any play lists it belongs to
						// Read a song index from the keyboard
						// see class Library for the method to call
						else if (action.equalsIgnoreCase("DELSONG")) 
						{
							System.out.print("Library Song #: ");   	// prints library song
							int contentToDelNumber = scanner.nextInt(); // gets the content to delete number
							mylibrary.deleteSong(contentToDelNumber);	// calls the method in library to delete the song
						}
						// Read a title string from the keyboard and make a playlist
						// see class Library for the method to call
						else if (action.equalsIgnoreCase("MAKEPL")) 
						{
							System.out.print("Playlist Title: ");  	// print syntax 
							String titleString = scanner.next(); 		// gets the playlist title
							mylibrary.makePlaylist(titleString);	 	// calls the method in library to make the playlist
						}
						// Print the content information (songs, audiobooks, podcasts) in the playlist
						// Read a playlist title string from the keyboard
						// see class Library for the method to call
						else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
						{
							System.out.print("PLaylist Title: ");   		// pprint syntax 
							String playlistStringName = scanner.next();		// gets the playlist title
							mylibrary.printPlaylist(playlistStringName);	// calls the method in library to print the playlist
						}
						// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
						// Read the playlist title, the type of content ("song" "audiobook" "podcast")
						// and the index of the content (based on song list, audiobook list etc) from the keyboard
						// see class Library for the method to call
						else if (action.equalsIgnoreCase("ADDTOPL")) 
						{
							System.out.print("Playlist Title: ");                           // print syntax 
							String titleString = scanner.next();						  	  // gets the playlist title
							System.out.println();											  // prints a new line
							System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");  // print syntax 
							String contentTypeInput = scanner.next().toUpperCase();		  	  // gets the content type 
							System.out.println();								              // prints a new line	
							System.out.print("Library Content #: ");						  // print syntax 
							int contentNumberInput = scanner.nextInt();						  // gets the content number
							mylibrary.addContentToPlaylist(contentTypeInput, contentNumberInput, titleString); 		// calls the method in library to add content to playlist
						}
						// Delete content from play list based on index from the playlist
						// Read the playlist title string and the playlist index
					// see class Library for the method to call
						else if (action.equalsIgnoreCase("DELFROMPL")) 
						{
							System.out.print("Playlist Title: ");                         // print syntax 
							String playlistTitle = scanner.next(); 							// gets the playlist title
							System.out.print("Content Number: ");							// print syntax 
							int contentNumber = scanner.nextInt();							// gets the content number
							mylibrary.delContentFromPlaylist(contentNumber, playlistTitle);	// calls the method in library to delete content from playlist
						}
						
						else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
						{
							mylibrary.sortSongsByYear(); 	// calls the method in library to sort songs by year
						}
						else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
						{
							mylibrary.sortSongsByName(); 	// calls the method in library to sort songs by name
						}
						else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
						{
							mylibrary.sortSongsByLength(); // calls the method in library to sort songs by length
						}
					
						else if(action.equalsIgnoreCase("SEARCH")){
							System.out.print("Title: ");								// print syntax
							String title = scanner.nextLine();							// gets the title
							store.searchSong(title);									// calls the method in store to search for song title
						}
						else if(action.equalsIgnoreCase("SEARCHA"))
						{ 
							System.out.print("Artist: ");								//	print syntax
							String artist = scanner.nextLine();							//	gets the artist
							store.searchArtist(artist);									// calls the method in store to search for artist
						} 
						else if(action.equalsIgnoreCase("SEARCHG"))
						{ 
							System.out.print("Genre [ROCK, POP, CLASSICAL, JAZZ, HIPHOP, RAP]: ");		// print syntax
							String genre = scanner.nextLine();												// gets the genre
							Song.Genre genreCasted = Song.Genre.valueOf(genre);								// casts the genre
							store.searchGenre(genreCasted);													// calls the method in store to search for genre
						}

						else if(action.equalsIgnoreCase("DOWNLOADA"))
						{
							System.out.print("Artist: ");								//	print syntax
							String artist = scanner.nextLine();							//	gets the artist
							mylibrary.download(store.downloadArtist(artist));			// calls the method in store to download all songs from artist
						} 

						else if(action.equalsIgnoreCase("DOWNLOADG"))
						{
							System.out.print("Genre [ROCK, POP, CLASSICAL, JAZZ, HIPHOP, RAP]: ");		// print syntax
							String genre = scanner.nextLine();													// gets the genre
							Song.Genre genreCasted = Song.Genre.valueOf(genre);								// casts the genre
							mylibrary.download(store.downloadGenre(genreCasted));							// calls the method in store to download all songs from genre
						}

						else if(action.equalsIgnoreCase("SEARCHP")){
							System.out.print("Search Anything: ");											// print syntax
							String search = scanner.nextLine();												// gets the search
							store.searchAnything(search);													// calls the method in store to search for anything
						}

						System.out.print("\n>");															// prints syntax		
					
					} catch (Exception e) {
						// catchses any exception in the program
						// and continues the program by resuming the loop
		
						System.out.println("Error: " + e.getMessage());											// prints error message
						System.out.print(">");																// prints the prompt
					}

				}
	
			} 
	}
}
