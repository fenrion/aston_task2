package dto.mapper;

import dto.*;
import entity.Actor;
import entity.Movie;
import entity.PhoneNumber;

import java.util.Arrays;
import java.util.List;

public class ActorDtoMapper {
    private static ActorDtoMapper instance;
    private static PhoneNumberDtoMapper phoneNumberDtoMapper = PhoneNumberDtoMapper.getInstance();
    private static MovieDtoMapper movieDtoMapper = MovieDtoMapper.getInstance();

    private ActorDtoMapper() {
    }

    public static ActorDtoMapper getInstance() {
        if (instance == null) {
            instance = new ActorDtoMapper();
        }
        return instance;
    }

    public List<ActorAllDTO> mapToAll(List<Actor> actors) {
        return actors.stream().map(this::mapToAllSmall).toList();
    }

    private ActorAllDTO mapToAllSmall(Actor actor) {
        ActorAllDTO actorAllDto = new ActorAllDTO();
        actorAllDto.setId(actor.getId());
        actorAllDto.setName(actor.getName());
        return actorAllDto;
    }

    public ActorSingleDTO mapToSingle(Actor actor, List<Movie> movies, List<PhoneNumber> phoneNumbers) {
        ActorSingleDTO actorSingleDTO = new ActorSingleDTO();
        actorSingleDTO.setName(actor.getName());

        List<MovieForActorSingleDTO> movie = movieDtoMapper.mapForActorSingle(movies);
        actorSingleDTO.setMovieDTOList(movie);

        List<PhoneNumberForSingleActorDTO> phNumbers = phoneNumberDtoMapper.mapForActorSingle(phoneNumbers);
        actorSingleDTO.setPhoneNumberDTOList(phNumbers);
        return actorSingleDTO;
    }

    public Actor mapFromSingle(ActorSingleDTO actorDTO) {
        Actor actor = new Actor();
        actor.setName(actorDTO.getName());

        List<MovieForActorSingleDTO> moviesDTO = actorDTO.getMovieDTOList();
        List<Movie> movies = movieDtoMapper.mapFromActorSingle(moviesDTO);
        for (Movie movie : movies) {
            List<Actor> actorList = Arrays.asList(actor);
            movie.setActorList(actorList);
        }
        actor.setMovieList(movies);

        List<PhoneNumberForSingleActorDTO> phonesDTO = actorDTO.getPhoneNumberDTOList();
        List<PhoneNumber> phoneNumbers = phoneNumberDtoMapper.mapFromActorSingle(phonesDTO);
        for (PhoneNumber phoneNumber : phoneNumbers) {
            phoneNumber.setActor(actor);
        }
        actor.setPhoneNumberList(phoneNumbers);
        return actor;
    }

    public Actor mapToUpdate(ActorUpdateDTO actorUpdateDto) {
        Actor actor = new Actor();
        actor.setName(actorUpdateDto.getName());
        List<PhoneNumber> phoneNumbers = phoneNumberDtoMapper.mapToUpdate(actorUpdateDto.getPhoneNumber());
        actor.setPhoneNumberList(phoneNumbers);
        return actor;
    }
}
