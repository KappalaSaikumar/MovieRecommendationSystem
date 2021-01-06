import java.util.*;
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr=new FirstRatings();
        myRaters=fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID (String ID,int minRaters){
        double total=0;double b=0;
            FirstRatings fr=new FirstRatings();
            if(fr.numofratings(myRaters,ID)>=minRaters){
                
                for(int k=0;k<myRaters.size();k++){
                    ArrayList<String> moviesrated=myRaters.get(k).getItemsRated();
                    for(int a=0;a<moviesrated.size();a++){
                        if(moviesrated.get(a).equals(ID)){total+=myRaters.get(k).getRating(ID);b++;}
                    }
                }
                return total/b;
            }
            
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minRaters){
        ArrayList<String> Movie=MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> r=new ArrayList<Rating>();
        for(int i=0;i<Movie.size();i++){
            if(getAverageByID(Movie.get(i),minRaters)!=0){
                Rating ra=new Rating(Movie.get(i),getAverageByID(Movie.get(i),minRaters));
                r.add(ra);
                
            }
        }
        
        return r;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minRaters,Filter f){
        ArrayList<Rating> r=getAverageRatings(minRaters);
        ArrayList<Rating> a=new ArrayList<Rating>();
        for(int i=0;i<r.size();i++){
            if(f.satisfies(r.get(i).getItem())){a.add(r.get(i));}
        }
        
        return a;
    }
    
    public void printAverageRatings (){
        ArrayList<Rating> a=getAverageRatings(1);
        Collections.sort(a);
        for(int i=0;i<a.size();i++){
            System.out.println(a.get(i).getValue()+" "+a.get(i).getItem());
        }
    }

}
