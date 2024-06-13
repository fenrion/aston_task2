package dto;

import entity.PhoneNumber;

import java.util.List;

public class ActorUpdateDTO {
    private String name;
    private List<PhoneNumberUpdateDTO> phoneNumber;

    public ActorUpdateDTO() {
    }

    public ActorUpdateDTO(String name, List<PhoneNumberUpdateDTO> phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PhoneNumberUpdateDTO> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<PhoneNumberUpdateDTO> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
