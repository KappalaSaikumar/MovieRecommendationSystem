import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAvgRatings(){
        ThirdRatings tr=new ThirdRatings();
        System.out.println("num of raters "+tr.getRaterSize());
        MovieDatabase m=new MovieDatabase();
        m.initialize("ratedmoviesfull.csv");
        System.out.println("num of movies "+m.size());
        System.out.println("found "+tr.getAverageRatings(35).size()+" movies");
        ArrayList<Rating> r=tr.getAverageRatings(1);
        for(int i=0;i<r.size();i++){
            //System.out.println(m.getTitle(r.get(i).getItem())+" "+r.get(i).getValue());
        }
        
    }
    
    public void printAverageRatingsByYear (){
        ThirdRatings tr=new ThirdRatings();
        //System.out.println("num of raters "+tr.getRaterSize());
        MovieDatabase m=new MovieDatabase();
        m.initialize("ratedmoviesfull.csv");
        //System.out.println("num of movies "+m.size());
        
        /*Filter year=new YearAfterFilter(2000);
        System.out.println("found "+tr.getAverageRatingsByFilter(20,year).size()+" movies");
        ArrayList<Rating> r=tr.getAverageRatingsByFilter(20,year);
        for(int i=0;i<r.size();i++){
            //System.out.println(m.getTitle(r.get(i).getItem())+" "+m.getYear(r.get(i).getItem())+" "+r.get(i).getValue());
        }*/
        
        /*Filter genre=new GenreFilter("Comedy");
        System.out.println("found "+tr.getAverageRatingsByFilter(20,genre).size()+" movies");
        ArrayList<Rating> g=tr.getAverageRatingsByFilter(1,genre);
        for(int i=0;i<g.size();i++){
            //System.out.println(m.getTitle(g.get(i).getItem())+" "+g.get(i).getValue()+" "+m.getGenres(g.get(i).getItem()));
        }*/
        
        /*Filter min=new MinutesFilter(103,135);
        System.out.println("found "+tr.getAverageRatingsByFilter(5,min).size()+" movies");
        ArrayList<Rating> b=tr.getAverageRatingsByFilter(1,min);
        for(int i=0;i<b.size();i++){
            //System.out.println(m.getTitle(b.get(i).getItem())+" "+b.get(i).getValue()+" "+m.getMinutes(b.get(i).getItem()));
        }*/
        
        /*Filter dir=new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        System.out.println("found "+tr.getAverageRatingsByFilter(4,dir).size()+" movies");
        ArrayList<Rating> l=tr.getAverageRatingsByFilter(1,dir);
        for(int i=0;i<l.size();i++){
            //System.out.println(m.getTitle(l.get(i).getItem())+" "+l.get(i).getValue()+" "+m.getDirector(l.get(i).getItem()));
        }*/
        
        AllFilters f=new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new YearAfterFilter(1990));
        ArrayList<Rating> d=tr.getAverageRatingsByFilter(8,f);
        System.out.println(d.size());
        for(int i=0;i<d.size();i++){
            //System.out.println(m.getTitle(d.get(i).getItem())+" "+d.get(i).getValue()+" "+m.getYear(d.get(i).getItem())+" "+m.getGenres(d.get(i).getItem()));
        }
    }
}
