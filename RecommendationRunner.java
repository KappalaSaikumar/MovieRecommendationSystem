import java.util.*;
import java.io.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
    private Random myRandom;
    private int numSimilarRaters;
    private int minimalRaters;

    public RecommendationRunner(){
        myRandom = new Random();
        numSimilarRaters = 15;
        minimalRaters = 3;
    }
    
    public ArrayList<String> getItemsToRate(){
        Random rand=new Random();
        ArrayList<String> movie=MovieDatabase.filterBy(new YearAfterFilter(2015));
        ArrayList<String> movies=new ArrayList<String>();
        for(int i=0;i<10;i++){
            int idx=rand.nextInt(movie.size());
            movies.add(movie.get(idx));
        }
        System.out.println(movies.size());
        return movies;
    }
    
    public void printRecommendationsFor(String webRaterID){
        FourthRatings fr=new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        try{
            ArrayList<Rating> rec=fr.getSimilarRatingsByFilter(webRaterID,numSimilarRaters,minimalRaters,new TrueFilter());
        }
        catch(java.lang.IndexOutOfBoundsException a){
            System.out.println("Sorry there are no suggestions matching your ratings");
        }
        finally{
            ArrayList<Rating> rec=fr.getSimilarRatingsByFilter(webRaterID,numSimilarRaters,minimalRaters,new TrueFilter());
        if(rec.size()==0){
            System.out.println("Recommendation List:");
            System.out.println("Not Found");
        }
        else{
            String header = ("<table> <tr> <th>Title</th> <th>Year of release</th> <th>Rating</th> </tr>");
            String body = "";
            int num;
            if(rec.size()>=15){num=20;}
            else{num=rec.size();}
            for(int i=0;i<num;i++){
                int idx=myRandom.nextInt(rec.size());
                if(!RaterDatabase.getRater(webRaterID).hasRating(rec.get(idx).getItem())){
                    body+="<tr> <td>" + MovieDatabase.getTitle(rec.get(idx).getItem())+"</td> <td>"+ MovieDatabase.getYear(rec.get(idx).getItem())+"</td> <td>"+rec.get(idx).getValue()+"</td> </tr>";
                }
            }
            System.out.println(header+body+"</table>");
        }
    }
    }
}
