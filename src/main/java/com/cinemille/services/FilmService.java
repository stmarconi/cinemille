package com.cinemille.services;

import com.cinemille.dto.FilmDto;
import com.cinemille.models.Film;
import com.cinemille.models.FilmScreening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    @Autowired
    ExcelService excelService;

    public List<FilmDto> getScreening(String startDate, String endDate) throws IOException {
        List<FilmScreening> screenings = excelService.readScreenings().stream().filter(screening ->
                        screening.getDate().isBefore(stringToLocalDate(endDate).atStartOfDay().plusDays(1))
                        && screening.getDate().isAfter(stringToLocalDate(startDate).atStartOfDay())
                        && isFilmAvailable(screening))
                .collect(Collectors.toList());

        return dataMapper(screenings);
    }

    public Film getFilmById(Integer filmId) throws IOException {
        return excelService.readFilms().stream().filter(film -> Objects.equals(film.getId(), filmId)).findFirst().orElse(null);
    }

    private LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    private List<FilmDto> dataMapper(List<FilmScreening> screenings) {

        Map<Integer, FilmDto> groupedData = screenings.stream()
            .collect(Collectors.groupingBy(
                FilmScreening::getFilmId,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        Film film = null;
                        try {
                            film = getFilmById(list.get(0).getFilmId());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Map<Integer, LocalDateTime> dates = list.stream()
                                .collect(Collectors.toMap(
                                        FilmScreening::getRoom,
                                        FilmScreening::getDate
                                ));
                        return new FilmDto(
                                film.getTitle(),
                                film.getDescription(),
                                film.getDuration(),
                                dates
                        );
                    }
                )
            ));

        return groupedData.values().stream().toList();
    }

    private boolean isFilmAvailable(FilmScreening screening) {
        Film film = null;
        try {
            film = getFilmById(screening.getFilmId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screening.getDate().isAfter(film.getStartDate().atStartOfDay())
                && screening.getDate().isBefore(film.getStartDate().plusWeeks(film.getWeeksNumber()).plusDays(1).atStartOfDay());
    }
}
