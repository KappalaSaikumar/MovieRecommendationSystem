
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int minMinutes;
    private int maxMinutes;
    public MinutesFilter(int a,int b){
        minMinutes=a;
        maxMinutes=b;
    }
    
    public boolean satisfies(String id){
        if((MovieDatabase.getMinutes(id)>=minMinutes)&&(MovieDatabase.getMinutes(id)<=maxMinutes)){
            return true;
        }
        return false;
    }

}
