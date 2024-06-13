package dto;

public class PhoneNumberUpdateDTO {
    private String number;

    public PhoneNumberUpdateDTO() {
    }

    public PhoneNumberUpdateDTO(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
