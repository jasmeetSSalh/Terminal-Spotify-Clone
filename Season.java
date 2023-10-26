// Name: Jasmeet Salh
// Student Id: 501159744

import java.util.ArrayList;

/*
 * A Season is a collection of episodes of a podcast
 * It contains the episode titles, episode files, and episode lengths
 */


public class Season{

    public ArrayList<String> episodeTitles;         
    public ArrayList<String> episodeFiles;
    public ArrayList<Integer> episodeLengths;

    //constructor with no parameters
    public Season() {
        episodeTitles = new ArrayList<String>();
        episodeFiles = new ArrayList<String>();
        episodeLengths = new ArrayList<Integer>();
    }

    //constructor with all the parameters
    public Season(int seasonNumber, ArrayList<String> episodeTitles, ArrayList<String> episodeFiles, ArrayList<Integer> episodeLengths) {

        this.episodeTitles = episodeTitles;
        this.episodeFiles = episodeFiles;
        this.episodeLengths = episodeLengths;
    }

}