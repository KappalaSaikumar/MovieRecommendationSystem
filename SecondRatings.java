import java.util.*;
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile,String ratingsfile){
        FirstRatings fr=new FirstRatings();
        myMovies=fr.loadMovie(moviefile);
        myRaters=fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID (String ID,int minRaters){
        double total=0;double b=0;
        //for(int i=0;i<myMovies.size();i++){
            FirstRatings fr=new FirstRatings();
            if(fr.numofratings(myRaters,ID)>minRaters){
                
                for(int k=0;k<myRaters.size();k++){
                    //System.out.println("yes");
                    ArrayList<String> moviesrated=myRaters.get(k).getItemsRated();
                    for(int a=0;a<moviesrated.size();a++){
                        if(moviesrated.get(a).equals(ID)){total+=myRaters.get(k).getRating(ID);b++;}
                    }
                }
                return total/b;
            }
            
        //}
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minRaters){
        ArrayList<Rating> r=new ArrayList<Rating>();
        for(int i=0;i<myMovies.size();i++){
            if(getAverageByID(myMovies.get(i).getID(),minRaters)!=0){
                Rating ra=new Rating(myMovies.get(i).getID(),getAverageByID(myMovies.get(i).getID(),minRaters));
                r.add(ra);
            }
        }
        
        return r;
    }
    
    public String getTitle(String ID){
        for(int i=0;i<myMovies.size();i++){
            if(myMovies.get(i).getID().equals(ID)){return myMovies.get(i).getTitle();}
        }
        
        return "NOT FOUND";
    }
    
    public String getID(String title){
        for(int i=0;i<myMovies.size();i++){
            if(myMovies.get(i).getTitle().equals(title)){return myMovies.get(i).getID();}
        }
        return "NO SUCH FOUND";
    }
    
    public void printAverageRatings (){
        ArrayList<Rating> a=getAverageRatings(12);
        Collections.sort(a);
        System.out.println(a.size());
        for(int i=0;i<3;i++){
            //System.out.println(a.size());
            System.out.println(a.get(i).getValue()+" "+getTitle(a.get(i).getItem()));
        }
    }
    
}


