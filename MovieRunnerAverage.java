
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAvgRatings(){
        SecondRatings sr=new SecondRatings();
        System.out.println("num of movies "+sr.getMovieSize());
        System.out.println("num of raters "+sr.getRaterSize());
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr=new SecondRatings();
        sr.printAverageRatings();
    }

}
