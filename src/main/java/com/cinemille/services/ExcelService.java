package com.cinemille.services;

import com.cinemille.models.Film;
import com.cinemille.models.FilmScreening;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    public List<FilmScreening> readScreenings() throws IOException {
        List<FilmScreening> filmScreenings = new ArrayList<>();

        ClassPathResource classPathResource = new ClassPathResource("FilmScreening.xlsx");
        try (InputStream is = classPathResource.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Skip header row
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                FilmScreening filmScreening = new FilmScreening();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0 -> filmScreening.setFilmId(Double.valueOf(currentCell.getNumericCellValue()).intValue());
                        case 1 -> filmScreening.setRoom(Double.valueOf(currentCell.getNumericCellValue()).intValue());
                        case 2 -> filmScreening.setDate(currentCell.getDateCellValue().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime());
                        default -> {
                        }
                    }
                    cellIdx++;
                }

                filmScreenings.add(filmScreening);
            }
        }

        return filmScreenings;
    }

    public List<Film> readFilms() throws IOException {
        List<Film> films = new ArrayList<>();

        ClassPathResource classPathResource = new ClassPathResource("FilmScreening.xlsx");
        try (InputStream is = classPathResource.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Skip header row
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Film film = new Film();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 3 -> film.setId(Double.valueOf(currentCell.getNumericCellValue()).intValue());
                        case 4 -> film.setTitle(currentCell.getStringCellValue());
                        case 5 -> film.setDescription(currentCell.getStringCellValue());
                        case 6 -> film.setDuration(Double.valueOf(currentCell.getNumericCellValue()).intValue());
                        case 7 -> film.setStartDate(currentCell.getDateCellValue().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate());
                        case 8 -> film.setWeeksNumber(Double.valueOf(currentCell.getNumericCellValue()).intValue());
                        default -> {
                        }
                    }
                    cellIdx++;
                }

                films.add(film);
            }
        }

        return films;
    }
}
