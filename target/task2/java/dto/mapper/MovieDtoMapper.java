package dto.mapper;

import dto.MovieForActorSingleDTO;
import entity.Movie;

import java.util.List;

public class MovieDtoMapper {
    private static MovieDtoMapper instance;

    private MovieDtoMapper() {
    }

    public static MovieDtoMapper getInstance() {
        if (instance == null) {
            instance = new MovieDtoMapper();
        }
        return instance;
    }

    public List<MovieForActorSingleDTO> mapForActorSingle(List<Movie> movies) {
        return movies.stream().map(this::mapForActor).toList();
    }

    private MovieForActorSingleDTO mapForActor(Movie movie) {
        MovieForActorSingleDTO mov = new MovieForActorSingleDTO();
        mov.setName(movie.getName());
        return mov;
    }

    public List<Movie> mapFromActorSingle(List<MovieForActorSingleDTO> moviesDTO) {
        return moviesDTO.stream().map(this::mapToMovie).toList();
    }

    private Movie mapToMovie(MovieForActorSingleDTO movieDTO) {
        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        return movie;
    }
}
