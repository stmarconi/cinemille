package com.cinemille.controllers;
import com.cinemille.dto.DataRequest;
import com.cinemille.dto.FilmDto;
import com.cinemille.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FilmController {

    @Autowired
    FilmService filmService;

    @PostMapping(value = "/films")
    public List<FilmDto> getFilms(@RequestBody DataRequest dataRequest) throws IOException {
        return filmService.getScreening(dataRequest.getStartDate(), dataRequest.getEndDate());
    }
}
