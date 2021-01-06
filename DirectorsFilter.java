import java.util.*;
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private List<String> directors;

    public DirectorsFilter (String directorString) {
        directors = Arrays.asList(directorString.split("\\s*,\\s*"));;
    }

    @Override
    public boolean satisfies(String id) {
        String movieDirectors =  MovieDatabase.getDirector(id);
        for (String director : directors) {
            if (movieDirectors.contains(director)) {
                return true;
            }
        }
        return false;
    }

}
