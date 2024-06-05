package service;

import dao.ActorDAO;
import dto.ActorDTO;
import entity.Actor;

import java.util.ArrayList;
import java.util.List;

public class ActorService {
    private ActorDAO actorDAO = ActorDAO.getInstance();
    private static ActorService instance;

    private ActorService() {
    }

    public static synchronized ActorService getInstance() {
        if (instance == null) {
            instance = new ActorService();
        }
        return instance;
    }
    public List<ActorDTO> showActors(){
        List<Actor> actorList=actorDAO.showActors();
        List<ActorDTO>actorDTOList = new ArrayList<>(actorList.size());
        for(Actor actor:actorList){
            ActorDTO actorDTO = convertActorToActorDTO(actor);
            actorDTOList.add(actorDTO);
        }
        return actorDTOList;
    }
    private ActorDTO convertActorToActorDTO(Actor actor){
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setName(actor.getName());
        actorDTO.setMovieList(actor.getMovieList());
        actorDTO.setPhoneNumberList(actor.getPhoneNumberList());
        return actorDTO;
    }
}
