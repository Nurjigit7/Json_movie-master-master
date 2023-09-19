package service.impl;

import dao.MovieDao;
import dao.impl.MovieDaoImpl;
import model.Movie;
import model.enums.Genre;
import service.MovieService;

import java.util.*;
import java.util.stream.Collectors;
public class MovieServiceImpl implements MovieService {
    private static final String PATH = "movie.json";
    private static final MovieDao movieDao = new MovieDaoImpl();

    @Override
    // TODO: 14.09.2023 вернуть фильм по ID
    public Movie findById(int movieId) {
        return movieDao.readFromFile(PATH).
                stream().filter(i -> i.getId() == movieId).findFirst().orElseThrow(() -> new RuntimeException("not found id"));
    }

    @Override
    // TODO: 14.09.2023 вернуть фильм по названию
    public Movie findByName(String name) {
        return movieDao.readFromFile(PATH).
                stream().filter(n -> n.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new RuntimeException("not found name"));
    }

    @Override
    // TODO: 14.09.2023 вернуть отсортированный лист по дате создание
    public List<Movie> sortByDate() {
        List<Movie> movieList = new ArrayList<>();
        try {
            movieList.addAll(movieDao.readFromFile(PATH));
            movieList.sort(Comparator.comparing(Movie::getCreateDate));
        } catch (RuntimeException exception) {
            System.out.printf(exception.getMessage());

        }
        return movieList;
    }

    @Override
    // TODO: 14.09.2023 отфилтрововать все фильмы по жанру
    public List<Movie> filterByGenre(Genre genre) {
        return movieDao.readFromFile(PATH)
                .stream()
                .filter(m -> m.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    @Override
    // TODO: 14.09.2023 найти обьект по ID и изменить его состояние
    public void updateById(int movieId, Movie movie) {
        List<Movie> movies = movieDao.readFromFile(PATH);
        Movie oldMovie = movies
                .stream()
                .filter(m -> m.getId() == movieId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Movie with id " + movieId + " not found!!!"));

        oldMovie.setId(movie.getId());
        oldMovie.setName(movie.getName());
        oldMovie.setGenre(movie.getGenre());
        oldMovie.setCreateDate(movie.getCreateDate());
        movies.add(oldMovie);
        System.out.println(movies);
    }
}
