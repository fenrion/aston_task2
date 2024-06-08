package service;

import dao.ActorDAO;
import dao.MovieDAO;
import dao.PhoneNumberDAO;
import dto.ActorAllDTO;
import dto.ActorSingleDTO;
import dto.ActorUpdateDTO;
import dto.mapper.ActorDtoMapper;
import entity.Actor;
import entity.Movie;
import entity.PhoneNumber;

import java.util.List;

public class ActorService {
    private final ActorDAO actorDAO = ActorDAO.getInstance();
    private final MovieDAO movieDAO = MovieDAO.getInstance();
    private final PhoneNumberDAO phoneNumberDAO = PhoneNumberDAO.getInstance();
    private static final ActorDtoMapper actorDtoMapper = ActorDtoMapper.getInstance();
    private static ActorService instance;

    private ActorService() {
    }

    public static synchronized ActorService getInstance() {
        if (instance == null) {
            instance = new ActorService();
        }
        return instance;
    }

    /**
     * show all actors
     * @return
     */
    public List<ActorAllDTO> showActors() {
        List<Actor> actors = actorDAO.showActors();
        return actorDtoMapper.mapToAll(actors);
    }

    /**
     * show single actor by id
     * @param index
     * @return
     */
    public ActorSingleDTO showActor(int index) {
        Actor actor = actorDAO.showActor(index);
        List<Movie> movies = movieDAO.showMoviesByActorId(index);
        List<PhoneNumber> phoneNumbers = phoneNumberDAO.showNumbersByActorId(index);
        return actorDtoMapper.mapToSingle(actor, movies, phoneNumbers);
    }

    /**
     * 1. save actor
     * 2. if movieList is not empty then save movieList
     * 3. if phoneNumberList is not empty then save phoneNumberList
     * @param actorDTO
     * @return
     */
    public String saveActor(ActorSingleDTO actorDTO) {
        Actor actor = actorDtoMapper.mapFromSingle(actorDTO);
        int index = actorDAO.saveActor(actor);
        if (actor.getMovieList().size() > 0) {
            movieDAO.saveFromActor(actor, index);
        }
        if (actor.getPhoneNumberList().size() > 0) {
            phoneNumberDAO.saveFromActor(actor, index);
        }
        return "complete!";
    }

    /**
     * 1. update actor
     * 2. update phoneNumberList
     * @param actorUpdateDto
     * @param index
     * @return
     */
    public String update(ActorUpdateDTO actorUpdateDto, int index) {
        Actor actor = actorDtoMapper.mapToUpdate(actorUpdateDto);
        actorDAO.updateActor(actor, index);
        phoneNumberDAO.updateNumber(actor.getPhoneNumberList(), index);
        return "updated!";
    }

    /**
     * 1. remove actor
     * 2. remove actor's movie
     * 3. remove actor's phone numbers
     * @param actorId
     * @return
     */
    public String delete(Integer actorId) {
        actorDAO.removeActor(actorId);
        movieDAO.deleteByActorId(actorId);
        phoneNumberDAO.deleteByActorId(actorId);
        return "removed!";
    }
}
