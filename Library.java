// Name: Jasmeet Salh
// Student Id: 501159744

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 		songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 	
  	private ArrayList<Podcast> 		podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 		= new ArrayList<AudioBook>();
		playlists  		= new ArrayList<Playlist>();
	  	podcasts		= new ArrayList<Podcast>(); 
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */

	public void download(ArrayList<AudioContent> contentList)
	{
			if(contentList.isEmpty()){
				throw new AudioContentNotFoundException("No Match for content");														// if the list is empty, throw an exception
			} else {

				for(int i = 0; i < contentList.size(); i++){																					// for loop to add the content to the list
					if(contentList.get(i).getType()==Song.TYPENAME){																			// check if the content is a song
						if(songs.contains((Song) contentList.get(i))){
							System.out.println(contentList.get(i).getType() + " " + contentList.get(i).getTitle() + " already in library");		// if the song is already in the list, print that it is already in the list
						} else {
							songs.add((Song) contentList.get(i));																				// if the song is not in the list, add it to the list
							System.out.println(contentList.get(i).getType() + " " + contentList.get(i).getTitle() + " added to library");		// print that the song has been added to the list
						}
					} 
					else if (contentList.get(i).getType()==AudioBook.TYPENAME){																	// check if the content is an audiobook
						if(audiobooks.contains((AudioBook) contentList.get(i))){
							System.out.println(contentList.get(i).getType() + " " + contentList.get(i).getTitle() + " already in library");		// if the audiobook is already in the list, print that it is already in the list
						} else {			
							audiobooks.add((AudioBook) contentList.get(i));																		// if the audiobook is not in the list, add it to the list
							System.out.println(contentList.get(i).getType() + " " + contentList.get(i).getTitle() + " added to library");		// print that the audiobook has been added to the list
						}
					}
					else if (contentList.get(i).getType()==Podcast.TYPENAME){																	// check if the content is a podcast
						if(podcasts.contains((Podcast) contentList.get(i))){
							System.out.println(contentList.get(i).getType() + " " + contentList.get(i).getTitle() + " already in library");		// if the podcast is already in the list, print that it is already in the list
						} else {
							podcasts.add((Podcast) contentList.get(i));																			// if the podcast is not in the list, add it to the list
							System.out.println(contentList.get(i).getType() + " " + contentList.get(i).getTitle() + " added to library");		// print that the podcast has been added to the list
						}
					} 		
				}								
			}
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++) {				// for loop to print the songs in the list
			int index = i + 1;									// index number for the songs to be printed from 1 to n
			System.out.print("" + index + ". ");				// print the index number
			songs.get(i).printInfo();							// print the song information
			System.out.println();								// print a new line
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++) {			// for loop to print the audiobooks in the list
			int index = i + 1;									// index number for the audiobooks to be printed from 1 to n
			System.out.print("" + index + ". ");				//	print the index number
			audiobooks.get(i).printInfo();						// print the audiobook information
			System.out.println();								// print a new line
		}
	}
	
  	// Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		for (int i = 0; i < podcasts.size(); i++) {		// for loop to print the podcasts in the list		
			int index = i + 1;							// index number for the podcasts to be printed from 1 to n
			System.out.print("" + index + ". ");		// print the index number
			podcasts.get(i).printInfo();				// print the podcast information
			System.out.println();			
		}
	}
	
  	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		if(playlists.size()>0){
			for (int i = 0; i < playlists.size(); i++) {				// for loop to print the playlists in the list
				System.out.print(playlists.get(i) + "\n");				// print the playlist name
			}
		}
	}
	
  	// Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList <String> listAllArtistsArray = new ArrayList<String>();			// create a new array list of string
		for (int i = 0; i < songs.size(); i++) {									// for loop to go through the songs array list
			if (!listAllArtistsArray.contains(songs.get(i).getArtist())){			// if the artist is not already in the array list, add it to the array list
				listAllArtistsArray.add(songs.get(i).getArtist());					// add the artist to the array list	
			}
		}
		for (int i = 0; i < listAllArtistsArray.size(); i++) {						// for loop to print the artists in the array list
			System.out.println((i+1)+". "+listAllArtistsArray.get(i));				// print the artists in the array list
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index) throws IndexOutOfBoundsException					
	{
		if(index<1 || index>songs.size()) {												// if the index is out of range
			throw new IndexOutOfBoundsException("Index out of range");				// throw an index out of bounds exception
		} else {

			Song songToRemove = songs.get(index-1);										// get the song to remove
			songs.remove(index-1);														// remove the song from the songs array list
			
			for (int j = 0; j < playlists.size(); j++) {								// for loop to go through the playlists array list
				for (int j2 = 0; j2 < playlists.get(j).getContent().size(); j2++) {		// for loop to go through the content of the current playlist array list
					if(playlists.get(j).getContent().get(j2).equals(songToRemove)) {	// if the song is in the playlist, remove it from the playlist
						playlists.get(j).deleteContent(j2);								// remove the song from the playlist
					}
				}
				
			}
		}
		
					
	}
	
  	//Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs,new SongYearComparator());				// call the SongYearComparator class to sort the songs by year using the Collections.sort() method
	}
  	
	// Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>		// class SongYearComparator that implements the Comparator interface
	{
		public int compare(Song firstSong, Song secondSong){			// compare two songs based on year
			if(firstSong.getYear()>secondSong.getYear()){				// if the first song is newer than the second song, return 1
				return 1;												// return 1 if the first song is newer than the second song
			} return -1;												// return -1 if the first song is older than the second song
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort()
	 	Collections.sort(songs,new SongLengthComparator()); 			// call the SongLengthComparator class to sort the songs by length using the Collections.sort() method
	}

  	// Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>		// class SongLengthComparator that implements the Comparator interface
	{
		public int compare(Song firstSong, Song secondSong) {			// compare two songs based on length
			if(firstSong.getLength()>secondSong.getLength()){			// if the first song is longer than the second song, return 1
				return 1;												// return 1 if the first song is longer than the second song
			} else if(firstSong.getLength()<secondSong.getLength()){
				return -1;												// return -1 if the first song is shorter than the second song
			} else{
				return 0;												// return 0 if the first song is the same length as the second song
			}
		}
	}

	// Sort songs by title 
	public void sortSongsByName()  
	{
	  	// Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);									//sort the songs by title using the Collections.sort() method, no method is needed in 
	}	

	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index) throws IndexOutOfBoundsException				
	{
		if (index < 1 || index > songs.size()){										// if the index is out of range, throw an IndexOutOfBoundsException
			throw new IndexOutOfBoundsException("Index out of range");
		}
		songs.get(index-1).play();													// if the index is in range, play the song
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public void playPodcast(int index, int season, int episode) throws IndexOutOfBoundsException
	{
		if (index < 1 || index > podcasts.size()){													// if the podcast index is out of range
			throw new AudioContentNotFoundException("Podcast Not Found");					// throws the AudioContentNotFoundException error message
		} else{
			if (season < 1 || season > podcasts.get(index - 1).getNumberOfSeasons()){				// if the season index is out of range
				throw new AudioContentNotFoundException("Season Not Found");				// throws the AudioContentNotFoundException error message
			} else{
				if (episode < 1 || episode > podcasts.get(index - 1).getNumberOfEpisodes(season)){	// if the episode index is out of range
					throw new AudioContentNotFoundException("Episode Not Found");			// throws the AudioContentNotFoundException error message
				} else{
					podcasts.get(index - 1).playPodcast(season, episode);							// if the index is in range, play the podcast
				}
			}
		}
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public void printPodcastEpisodes(int PodcastIndex, int SeasonIndex) throws AudioContentNotFoundException
	{
		if(PodcastIndex < 1 || PodcastIndex > podcasts.size()){												// if the podcast index is out of range
			throw new AudioContentNotFoundException("Podcast Not Found");							// throws a AudioContentNotFoundException the error message
		} else{
			if (SeasonIndex < 1 || SeasonIndex > podcasts.get(PodcastIndex-1).getSeasons().size()){			// if the season index is out of range
				throw new AudioContentNotFoundException("Season Not Found");						// throws the error message
			} else{
				for (int i = 0; i < podcasts.get(PodcastIndex - 1).getSeasons().get(SeasonIndex - 1).episodeTitles.size() ; i++) {							// for loop to print the episode titles of the specified podcast of the specified season
					System.out.println("Episode " + (i+1) + ": " + podcasts.get(PodcastIndex - 1).getSeasons().get(SeasonIndex - 1).episodeTitles.get(i));	// print the episode titles of the specified podcast of the specified season, using the get method to get the episode titles from the episodeTitles array list
				}
			}
		}
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) throws AudioContentNotFoundException
	{
		if (index < 1 || index > audiobooks.size()){											// if the index is less than 1 or greater than the size of the audiobooks array list
			throw new AudioContentNotFoundException("Audiobook Not Found");				// throw AudioContentNotFoundException error message
		} else{
			if (chapter < 1 || chapter > audiobooks.get(index - 1).getNumberOfChapters()){		// if the chapter is less than 1 or greater than the number of chapters in the audiobook
				throw new AudioContentNotFoundException("Chapter Not Found");			// throw AudioContentNotFoundException error message
			} else{
				audiobooks.get(index - 1).selectChapter(chapter);								// select the chapter in the specidied audiobook
				audiobooks.get(index - 1).play();												// play the audiobook using the audiobook method play()
			}
		}
		
		
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index) throws AudioContentNotFoundException
	{
		if (index < 1 || index > audiobooks.size()){												// if the index is less than 1 or greater than the size of the audiobooks array list
			throw new AudioContentNotFoundException("Audiobook Chapter Title Not Found");	// throws the error message if audiocontent is not found										
		}
		audiobooks.get(index-1).printTOC();															// print the table of contents of the audiobook
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) throws AudioContentNotFoundException				// make a new playlist and add it to the playlists array list
	{
		for (int i = 0; i < playlists.size(); i++) {										// for loop to go through the playlists array list
			if(playlists.get(i).getTitle().equals(title)){									//	if the title of the playlist is the same as the title of the playlist that is being added
				throw new AudioContentNotFoundException("Playlist Already Exists");	// throws the error message to AudioContentNotFoundException
			}
		}
		playlists.add(new Playlist(title));												// add the new playlist to the playlists array list
		
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title) throws AudioContentNotFoundException					// print the contents of a playlist
	{						
		for (int i = 0; i < playlists.size(); i++) {											// for loop to go through the playlists array list
			if(playlists.get(i).getTitle().equals(title)){										// if the title of the playlist is the same as the title of the playlist that is being added
				playlists.get(i).printContents();												// print the contents of the playlist	
			} else {
				throw new AudioContentNotFoundException("Playlist Does Not Exist");		// throws AudioContentNotFoundException the error message
			}
		}
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) throws AudioContentNotFoundException		
	{					
		for (int i = 0; i < playlists.size(); i++) {										// for loop to go through the playlists array list
			if(playlists.get(i).getTitle().equals(playlistTitle)){							// if the title of the playlist is the same as the title of the playlist that is being added
				playlists.get(i).playAll();													// play all the contents of the playlist
			} else {
				throw new AudioContentNotFoundException(playlistTitle + " Does Not Exist");	// throws the error message to AudioContentNotFoundException
			}
		} 
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL) throws AudioContentNotFoundException
	{	
		if(indexInPL < 1 || indexInPL > playlists.size()){
			throw new AudioContentNotFoundException("Playlist Does Not Exist");
		} else{
			indexInPL = indexInPL - 1;
			for (int i = 0; i < playlists.size(); i++) {										// for loop to go through the playlists array list
				if(playlists.get(i).getTitle().equals(playlistTitle)){							// if the title of the playlist is the same as the title of the playlist that is being added
					playlists.get(i).play(indexInPL);											// play the specific song/audiobook in the playlist
				} else {
					throw new AudioContentNotFoundException(playlistTitle + " Does Not Exist");	// throws the error message to AudioContentNotFoundException
				}
			}
		}
		
		
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	//. for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle) throws IndexOutOfBoundsException{	// add a song/audiobook/podcast from library lists at top to a playlist
		
		boolean checkIfPlaylistTitleIsThere = false;									// boolean to check if the playlist title is there
		int savedindex = 0;																// int to save the index of the playlist			
		
		for (int i = 0; i < playlists.size(); i++) {									// for loop to go through the playlists array list
			if(playlists.get(i).getTitle().equals(playlistTitle)){						// if the title of the playlist is the same as the title of the playlist that is being added
				savedindex = i;															// save the index of the playlist			
				checkIfPlaylistTitleIsThere = true;										// sets the boolean to true		
			}
		}
		
		if(checkIfPlaylistTitleIsThere){															// if the boolean is true		
			switch (type) {																			// switch statement to check the type of the content			
				case Song.TYPENAME:
					if(index < 1 || index > songs.size()){											// if the index is less than 1 or greater than the size of the songs array list
						throw new IndexOutOfBoundsException(playlistTitle + " Does Not Exist");		// throws the error message to IndexOutOfBoundsException
					} else {
						playlists.get(savedindex).addContent(songs.get(index-1));					// add the content to the playlist
					}			
				case AudioBook.TYPENAME:
					if(index < 1 || index > audiobooks.size()){										// if the index is less than 1 or greater than the size of the audiobooks array list
						throw new IndexOutOfBoundsException(playlistTitle + " Does Not Exist");		// throws the error message to IndexOutOfBoundsException
					} else {	
						playlists.get(savedindex).addContent(audiobooks.get(index-1));				// add the content to the playlist
					}
				case Podcast.TYPENAME:
					if(index < 1 || index > podcasts.size()){										// if the index is less than 1 or greater than the size of the podcast array list
						throw new IndexOutOfBoundsException(playlistTitle + " Does Not Exist");		// throws the error message to IndexOutOfBoundsException
					} else {	
						playlists.get(savedindex).addContent(podcasts.get(index-1));				// add the content to the playlist
					}
				default:
					throw new IndexOutOfBoundsException("Type Does Not Exist");					// throws the error message to IndexOutOfBoundsException
			}
		} else {
			throw new AudioContentNotFoundException(playlistTitle + " Does Not Exist");				// throws the error message to AudioContentNotFoundException
		}
		
	}

  	// Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title) throws AudioContentNotFoundException
	{
		if(index < 1 || index > playlists.size()){
			throw new AudioContentNotFoundException("Playlist Does Not Exist");
		} else{
			index = index - 1;
			for (int i = 0; i < playlists.size(); i++) {					// for loop to go through the playlists array list
				if(playlists.get(i).getTitle().equals(title)){				// if the title of the playlist is the same as the title of the playlist that is being added
					playlists.get(i).deleteContent(index);					// delete the content from the playlist, by getting the specific playlist and calling the deleteContent method from the playlist class
				} else {
					throw new AudioContentNotFoundException(title + " Does Not Exist");	// throws the error message
				}
			}
		 }
	}

	public int getNumberOfSongs() {						//	get the number of songs
		return songs.size();							//	return the size of the songs array list
	}

    public int getNumberOfAudioBooks() {				//	get the number of audiobooks
        return audiobooks.size();						//	return the size of the audiobooks array list	
    }

	public int getNumberOfPodcasts() {					//	get the number of podcasts
		return podcasts.size();							//	return the size of the podcasts array list
	}



	// Custom exception classes

	class ContentAlreadyExists extends RuntimeException {				// create a new exception class, if content already exists
		public ContentAlreadyExists(String current){					// constructor
			super(current);												// throw the exception through calling the parent class
		}
	}

	class AudioContentNotFoundException extends RuntimeException{		// create a new exception class, if audio content is not found
		public AudioContentNotFoundException(String current){			// constructor
			super(current);												// throw the exception through calling the parent class
		}
	}



}

