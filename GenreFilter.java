
public class GenreFilter implements Filter{
    private String genre;
    
    public GenreFilter(String g){
        genre=g;
    }
    
    public boolean satisfies(String id){
        if(MovieDatabase.getGenres(id).indexOf(genre)==-1){return false;}
        return true;
    }

}
