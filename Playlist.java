// Name: Jasmeet Salh
// Student Id: 501159744

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents(){								//prints the contents of the playlist
		for (int i = 0; i < contents.size(); i++) {				//for loop to print the contents of the playlist
			System.out.print(i+1 + ". ");						//prints the index of the audio content object
			contents.get(i).printInfo();						//	uses the printInfo() method of each audio content object
			System.out.println();
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()	{									//plays all the contents of the playlist	
		for (int i = 0; i < contents.size(); i++) {				//for loop to play all the contents of the playlist
			contents.get(i).play();								//uses the play() method of each audio content object		
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		contents.get(index).play();
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		if(this.title.equals(((Playlist)other).title)) {			//checks if the titles of the two playlists are equal
			return true;		
		}
		return false;
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index){
		
		// delete the index item from the current content list and check if it's a valid index value

		if(index<0 || index>contents.size()){
			System.out.println("Invalid Index");					
		} else {
			this.contents.remove(this.contents.get(index));			// get the current playlist and removes the specfic index from current playlist file
		}
	}
	
	
}
