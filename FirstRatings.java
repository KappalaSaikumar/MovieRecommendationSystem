import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovie(String filename){
        ArrayList<Movie> list=new ArrayList<Movie>();
        FileResource fr=new FileResource(filename);
        for(CSVRecord record:fr.getCSVParser()){
            Movie m=new Movie(record.get(0),record.get(1),record.get(2),record.get(4),record.get(5),record.get(3),record.get(7),Integer.parseInt(record.get(6)));
            list.add(m);
        }
        return list;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> raters=new ArrayList<Rater>();
        FileResource fr=new FileResource("data/"+filename);
        for(CSVRecord record:fr.getCSVParser()){
            
            if(container(raters,record)==0){
                Rater m=new EfficientRater(record.get(0));
                m.addRating(record.get(1),Double.parseDouble(record.get(2)));
                raters.add(m);
            }
            else{
                Rater m=getter(raters,record);
                m.addRating(record.get(1),Double.parseDouble(record.get(2)));
            }
            
        }
        return raters;
    }
    
    public int container(ArrayList<Rater> raters,CSVRecord record){
        for(int i=0;i<raters.size();i++){
            if(raters.get(i).getID().equals(record.get(0))){return 1;}
        }
        return 0;
    }
    
    public Rater getter(ArrayList<Rater> raters,CSVRecord record){
        for(int i=0;i<raters.size();i++){
            if(raters.get(i).getID().equals(record.get(0))){return raters.get(i);}
        }
        return null;
    }
    
    public int numforRater(ArrayList<Rater> raters,String ID){
        for(int i=0;i<raters.size();i++){
            if(raters.get(i).getID().equals(ID)){return raters.get(i).getItemsRated().size();}
        }
        return 0;
    }
    
    public String highestratingsbyarater(ArrayList<Rater> a){
        HashMap<String,Integer> b=new HashMap<String ,Integer>();
        for(int i=0;i<a.size();i++){
            if(!b.containsKey(a.get(i).getID())){b.put(a.get(i).getID(),a.get(i).numRatings());}
            
        }
        int max=0;String c="";
        for(String s:b.keySet()){
            if(b.get(s)>max){max=b.get(s);c=s;}
        }
        return "rater "+c+" given "+max;
    }
    
    public int numofratings(ArrayList<Rater> a,String item){
        HashMap<String,Integer> c=new HashMap<String ,Integer>();
        for(int i=0;i<a.size();i++){
            ArrayList<String> b=a.get(i).getItemsRated();
            for(int k=0;k<b.size();k++){
                if(!c.containsKey(b.get(k))){c.put(b.get(k),1);}
                else{c.put(b.get(k),c.get(b.get(k))+1);}
            }
        }
        int max=0;
        for(String s:c.keySet()){
            if(s.equals(item)){max=c.get(s);}
        }
        return max;
    }
    
    public int diffmoviesrated(ArrayList<Rater> a){
        HashMap<String,Integer> c=new HashMap<String ,Integer>();
        for(int i=0;i<a.size();i++){
            ArrayList<String> b=a.get(i).getItemsRated();
            for(int k=0;k<b.size();k++){
                if(!c.containsKey(b.get(k))){c.put(b.get(k),1);}
                //else{c.put(b.get(k),c.get(b.get(k))+1);}
            }
        }
        return c.size();
    }
    
    public String director(ArrayList<Movie> a){
        HashMap<String ,Integer> b=new HashMap<String ,Integer>();
        for(int i=0;i<a.size();i++){
            if(!b.containsKey(a.get(i).getDirector())){
                b.put(a.get(i).getDirector(),1);
            }
            
            else{
                b.put(a.get(i).getDirector(),b.get(a.get(i).getDirector())+1);
                
            }
        }
        
        int max=0;String c="";
        for(String s:b.keySet()){
            if(b.get(s)>max){max=b.get(s);c=s;}
        }
        return max +" "+c;
    }
    
    public void testloadmovie(){
        ArrayList<Movie> a=loadMovie("ratedmoviesfull.csv");
        //System.out.println(a);
        System.out.println(a.size());
        int countgenre=0;
        for(int i=0;i<a.size();i++){
            if(a.get(i).getGenres().indexOf("Comedy")!=-1){countgenre++;}
        }
        System.out.println("num with genre "+countgenre);
        
        int movielen=0;
        for(int i=0;i<a.size();i++){
            if(a.get(i).getMinutes()>150){movielen++;}
        }
        System.out.println("movies with chosen length "+movielen);
        
        System.out.println(director(a));
    }
    
    public void testloadraters(){
        ArrayList<Rater> a=loadRaters("ratings.csv");
        System.out.println("num raters "+a.size());
        for(int i=0;i<a.size();i++){
            //System.out.println(a.get(i).getID()+"gave "+a.get(i).numRatings());
            ArrayList<String> b=a.get(i).getItemsRated();
            for(int k=0;k<b.size();k++){
                //System.out.println(b.get(k)+"got "+a.get(i).getRating(b.get(k)));
            }
        }
        //System.out.println(numforRater(a,"193"));
        
        //System.out.println(highestratingsbyarater(a));
        
        System.out.println(numofratings(a,"1798709"));
        
        System.out.println(diffmoviesrated(a));
    }
}


