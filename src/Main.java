
import dao.impl.MovieDaoImpl;
import model.Movie;
import model.enums.Genre;
import service.impl.MovieServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO: 14.09.2023 для началы работы необходимо положить 5 фильмов в JSON файл
        //  (для этого используйте метод writeToFile(String path, Movie movie); из класса MovieDao
        List<Movie> movieList = new ArrayList<>();
        MovieDaoImpl movieDao = new MovieDaoImpl();
        Movie movie = new Movie(5, "Avatar", Genre.ACTION, LocalDate.of(2023, 4, 22));
        movieList.add(new Movie(1, "Авамен", Genre.FANTASY, LocalDate.of(2022, 7, 15)));
        movieList.add(new Movie(2, "Паддингтона", Genre.COMEDY, LocalDate.of(2015, 8, 15)));
        movieList.add(new Movie(3, "Люби их всех", Genre.DRAMA, LocalDate.of(2018, 5, 18)));
        movieList.add(new Movie(4, "Путь к себе", Genre.DRAMA, LocalDate.of(2022, 7, 13)));
        movieDao.writeToFile("movie.json", movieList);
        MovieServiceImpl movieService = new MovieServiceImpl();
//        System.out.println(movieService.findById(1));
//        System.out.println(movieService.findByName("Паддингтона"));
//        System.out.println(movieService.sortByDate());
//        System.out.println(movieService.filterByGenre(Genre.DRAMA));
        movieService.updateById(1, movie);

    }
}