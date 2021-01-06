import java.util.*;

public class FourthRatings {
    
    
    public double getAverageByID (String ID,int minRaters){
        double total=0;double b=0;
            FirstRatings fr=new FirstRatings();
            ArrayList<Rater> myRaters=RaterDatabase.getRaters();
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
    
    public double dotProduct(Rater me,Rater r){

        ArrayList<String> a=r.getItemsRated();
        ArrayList<String> b=me.getItemsRated();
        ArrayList<String> c=new ArrayList<String>();
        for(String s:a){
            if(!c.contains(s)){c.add(s);}
        }
        ArrayList<Double> ratings1=new ArrayList<Double>();
        for(String s:c){
            double rating=r.getRating(s);
            if(rating==-1){ratings1.add(null);}
            else{
            rating=rating-5.0;
            ratings1.add(rating);}
        }
        
        ArrayList<Double> ratings2=new ArrayList<Double>();
        for(String s:c){
            double rating=me.getRating(s);
            if(rating==-1){ratings2.add(null);}
            else{
            rating=rating-5.0;
            ratings2.add(rating);}
        }
        double dot=0;
        for(int i=0;i<ratings1.size();i++){
            if((ratings1.get(i)!=null)&&(ratings2.get(i)!=null)){
            dot+=ratings1.get(i)*ratings2.get(i);}
        }
        return dot;
    }
    
    public ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> similar=new ArrayList<Rating>();
        for(Rater rater:RaterDatabase.getRaters()){
            if(!rater.getID().equals(id)){
            if(dotProduct(RaterDatabase.getRater(id),rater)>0){
            Rating r=new Rating(rater.getID(),dotProduct(RaterDatabase.getRater(id),rater));
            similar.add(r);}}
        }
        Collections.sort(similar,Collections.reverseOrder());
        return similar;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id,int numsimRatings,int minRaters){
        List<Rating> similar=getSimilarities(id).subList(0,numsimRatings);
        ArrayList<Rating> ret=new ArrayList<Rating>();
        
        for(String MovieID:MovieDatabase.filterBy(new TrueFilter())){
            double total=0.0D;double count=0.0D;
            for(Rating r:similar){
                 Rater rater=RaterDatabase.getRater(r.getItem());
                 //System.out.println(rater.getRating(MovieID));
                    if(rater.hasRating(MovieID)){
                        total+=r.getValue()*rater.getRating(MovieID);
                        
                        count++;
                    }
                }
            
            if(count>=minRaters){
            Rating r=new Rating(MovieID,total/count);
            //System.out.println(total/count);
            ret.add(r);}
        }
        Collections.sort(ret,Collections.reverseOrder());
        return ret;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numsimRatings,int minRaters,Filter f){
        ArrayList<Rating> list=getSimilarRatings(id,numsimRatings,minRaters);
        ArrayList<Rating> ret=new ArrayList<Rating>();
        for(int i=0;i<list.size();i++){
            if(f.satisfies(list.get(i).getItem())){ret.add(list.get(i));}
        }
        Collections.sort(ret,Collections.reverseOrder());
        return ret;
    }
    
    public void printAverageRatings (){
        ArrayList<Rating> a=getAverageRatings(1);
        Collections.sort(a);
        for(int i=0;i<a.size();i++){
            System.out.println(a.get(i).getValue()+" "+a.get(i).getItem());
        }
    }

}
