package dto.mapper;

import dto.PhoneNumberForSingleActorDTO;
import dto.PhoneNumberUpdateDTO;
import entity.PhoneNumber;

import java.util.List;

public class PhoneNumberDtoMapper {
    private static PhoneNumberDtoMapper instance;

    private PhoneNumberDtoMapper() {
    }

    public static synchronized PhoneNumberDtoMapper getInstance() {
        if (instance == null) {
            instance = new PhoneNumberDtoMapper();
        }
        return instance;
    }

    public List<PhoneNumberForSingleActorDTO> mapForActorSingle(List<PhoneNumber> phoneNumbers) {
        return phoneNumbers.stream().map(this::mapForActor).toList();
    }

    private PhoneNumberForSingleActorDTO mapForActor(PhoneNumber phoneNumber) {
        PhoneNumberForSingleActorDTO phNumber = new PhoneNumberForSingleActorDTO();
        phNumber.setNumber(phoneNumber.getNumber());
        return phNumber;
    }

    public List<PhoneNumber> mapFromActorSingle(List<PhoneNumberForSingleActorDTO> phonesDTO) {
        return phonesDTO.stream().map(this::mapToPhoneNumber).toList();
    }

    private PhoneNumber mapToPhoneNumber(PhoneNumberForSingleActorDTO phoneNumberDTO) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(phoneNumberDTO.getNumber());
        return phoneNumber;
    }

    public List<PhoneNumber> mapToUpdate(List<PhoneNumberUpdateDTO> phoneNumber) {
        return phoneNumber.stream().map(this::mapToPhNumber).toList();
    }

    private PhoneNumber mapToPhNumber(PhoneNumberUpdateDTO phoneNumber) {
        PhoneNumber ph = new PhoneNumber();
        ph.setNumber(phoneNumber.getNumber());
        return ph;
    }
}
