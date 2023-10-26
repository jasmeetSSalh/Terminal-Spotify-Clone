// Name: Jasmeet Salh
// Student Id: 501159744

import java.util.ArrayList;

// An Podcast, type of radio show


public class Podcast extends AudioContent 
{
    public static final String TYPENAME = "PODCAST";

    private ArrayList<String> host;
    private ArrayList<Season> seasons;

    private int currentEpisode = 0;
    private int currentSeason = 0;

    // Default constructor for Podcast
    public Podcast(String title, int year, String id, String type, String audioFile, int length, ArrayList<String> host, ArrayList<Season> seasons) {
        super(title, year, id, type, audioFile, length);
        this.host = host;
        this.seasons = seasons;
        
    }

    public String getType() {                                       // return the type of the podcast
        return TYPENAME;
        
    }

    public void printInfo() {
        super.printInfo();
        System.out.print("Host: ");
        for (int i = 0; i < host.size(); i++) {
            if(i==0){
                System.out.print(host.get(i) + ", ");               //prints the first host
            } else{
                System.out.print(host.get(i) + " " + "\n");         //prints the rest of the hosts
            }
        }
        System.out.println("Number of Seasons: " + getNumberOfSeasons());
    }

   public boolean equals(Object other) {                                        // checks if the podcast is equal to another podcast
       return super.equals(other) && host.equals(((Podcast) other).host);
   }

    public ArrayList<Season> getSeasons() {                                     // return the seasons
        return seasons;
    }

    public int getNumberOfSeasons() {                                           // return the number of seasons
        return seasons.size();
    }


    public int getNumberOfEpisodes(int season) {
        return seasons.get(season).episodeFiles.size();                          // return the number of episodes in a specific season
    }

    public void printPodcastEpisodes(int season, int episode) {
        var episodeTitle = seasons.get(season).episodeTitles.get(episode);      //gets the episode title of a specific podcast and of the specific season
        setAudioFile(episodeTitle);                                             //sets the audio file to the episode title
        super.play();                                                           //plays the audio file using the super class method
    }


    public void playPodcast(int SeasonIndex, int EpisodeIndex){
        this.currentEpisode = EpisodeIndex-1;                                               //sets the current episode to the episode index -1
        this.currentSeason = SeasonIndex-1;                                                 //sets the current season to the season index -1
        var episodeToPlay = seasons.get(currentSeason).episodeFiles.get(currentEpisode);    //gets the episode file of the current season and episode
        setAudioFile(episodeToPlay);                                                        //sets the audio file to the episode file
        super.play();                                                                       //plays the audio file using the super class method by accessing the audioFile
    }

}