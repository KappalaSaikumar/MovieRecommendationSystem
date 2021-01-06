
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAvgRatings(){
        FourthRatings tr=new FourthRatings();
        //System.out.println("num of raters "+tr.getRaterSize());
        MovieDatabase m=new MovieDatabase();
        m.initialize("ratedmovies_short.csv");
        System.out.println("num of movies "+m.size());
        System.out.println("found "+tr.getAverageRatings(1).size()+" movies");
        ArrayList<Rating> r=tr.getAverageRatings(1);
        for(int i=0;i<r.size();i++){
            System.out.println(m.getTitle(r.get(i).getItem())+" "+r.get(i).getValue());
        }
        
    }
    //9346077143
    public void printAverageRatingsByYear (){
        FourthRatings tr=new FourthRatings();
        //System.out.println("num of raters "+tr.getRaterSize());
        MovieDatabase m=new MovieDatabase();
        m.initialize("ratedmovies_short.csv");
        System.out.println("num of movies "+m.size());
        
        AllFilters f=new AllFilters();
        //f.addFilter(new YearAfterFilter(1980));
        f.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> d=tr.getAverageRatingsByFilter(1,f);
        for(int i=0;i<d.size();i++){
            System.out.println(m.getTitle(d.get(i).getItem())+" "+d.get(i).getValue()+" "+m.getYear(d.get(i).getItem())+" "+m.getGenres(d.get(i).getItem()));
        }
   
    }
    
    public void printSimilarRatings (){
        MovieDatabase.nullify();
        RaterDatabase.nullify();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("raters "+RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("num of movies "+MovieDatabase.size());
        
        FourthRatings fr=new FourthRatings();
        ArrayList<Rating> movies=fr.getSimilarRatings("71",20,5);
        //System.out.println(fr.getSimilarities("65"));
        //System.out.println(movies);
        for(Rating r:movies){
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());
        }
        
    }
    
    public void printSimilarRatingsByDirector() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("raters "+RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("num of movies "+MovieDatabase.size());
        
        FourthRatings fr=new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter (new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        ArrayList<Rating> movies=fr.getSimilarRatingsByFilter("120",10,2,filter);
        for(Rating r:movies){
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+MovieDatabase.getDirector(r.getItem())+r.getValue());
        }
        //printSimilarRatings("ratedmoviesfull.csv","ratings.csv", "120", 2, 10, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("raters "+RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("num of movies "+MovieDatabase.size());
        
        FourthRatings fr=new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter (new GenreFilter("Drama"));
        filter.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> movies=fr.getSimilarRatingsByFilter("168",10,3,filter);
        for(Rating r:movies){
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("raters "+RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("num of movies "+MovieDatabase.size());
        
        FourthRatings fr=new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter (new YearAfterFilter(1975));
        filter.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> movies=fr.getSimilarRatingsByFilter("314",10,5,filter);
        for(Rating r:movies){
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());
        }
        //printSimilarRatings("ratedmoviesfull.csv","ratings.csv", "314", 5, 10, filter);
    }
    
    
    
}
