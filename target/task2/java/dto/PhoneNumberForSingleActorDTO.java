package dto;

public class PhoneNumberForSingleActorDTO {
    private String number;

    public PhoneNumberForSingleActorDTO() {
    }

    public PhoneNumberForSingleActorDTO(String actorName, String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
