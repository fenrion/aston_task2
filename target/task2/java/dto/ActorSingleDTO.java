package dto;

import java.util.List;

public class ActorSingleDTO {
    private String name;
    private List<MovieForActorSingleDTO> movieForActorSingleDTOList;
    private List<PhoneNumberForSingleActorDTO> phoneNumberForSingleActorDTOList;

    public ActorSingleDTO() {
    }

    public ActorSingleDTO(String name, List<MovieForActorSingleDTO> movieForActorSingleDTOList, List<PhoneNumberForSingleActorDTO> phoneNumberForSingleActorDTOList) {
        this.name = name;
        this.movieForActorSingleDTOList = movieForActorSingleDTOList;
        this.phoneNumberForSingleActorDTOList = phoneNumberForSingleActorDTOList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieForActorSingleDTO> getMovieDTOList() {
        return movieForActorSingleDTOList;
    }

    public void setMovieDTOList(List<MovieForActorSingleDTO> movieForActorSingleDTOList) {
        this.movieForActorSingleDTOList = movieForActorSingleDTOList;
    }

    public List<PhoneNumberForSingleActorDTO> getPhoneNumberDTOList() {
        return phoneNumberForSingleActorDTOList;
    }

    public void setPhoneNumberDTOList(List<PhoneNumberForSingleActorDTO> phoneNumberForSingleActorDTOList) {
        this.phoneNumberForSingleActorDTOList = phoneNumberForSingleActorDTOList;
    }
}
