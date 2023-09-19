package dao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.LocalDateTypeAdapter;
import dao.MovieDao;
import model.Movie;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieDaoImpl implements MovieDao {
    // TODO: 14.09.2023 положить фильм в указанной JSON файл
    @Override
    public void writeToFile(String path, List<Movie> movies) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
            String json = gson.toJson(movies);
            try (FileWriter fileWriter = new FileWriter(path)) {
                fileWriter.write(json);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("успешно!!!");

    }

    // TODO: 14.09.2023 вернуть список фильмов из JSON файла (путь указан в параметре метода)
    @Override
    public List<Movie> readFromFile(String path) {

        StringBuilder stringBuilder = new StringBuilder();
        List<Movie> movieList = new ArrayList<>();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
        try {
            FileReader fileReader = new FileReader(path);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
            Movie[] movies = gson.fromJson(String.valueOf(stringBuilder), Movie[].class);
            movieList.addAll(List.of(movies));
        } catch (Exception p) {
            System.out.println(p.getMessage());
        }
        return movieList;


    }
}