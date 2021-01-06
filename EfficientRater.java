import java.util.*;
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){return true;}
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        Rating rating = myRatings.get(item);
        if (rating != null){
            return rating.getValue();
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s:myRatings.keySet()){
            list.add(myRatings.get(s).getItem());
        }
        
        return list;
    }

}
