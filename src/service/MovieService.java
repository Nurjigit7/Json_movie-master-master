package service;

import model.Movie;
import model.enums.Genre;

import java.util.List;

public interface MovieService {
    Movie findById(int movieId);

    Movie findByName(String name);

    List<Movie> sortByDate();

    // TODO: 14.09.2023 вернуть отсортированный лист по дате создание

    List<Movie> filterByGenre(Genre genre);

    void updateById(int movieId, Movie movie);
}
