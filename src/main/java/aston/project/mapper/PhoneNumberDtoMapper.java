package aston.project.mapper;

import aston.project.dto.PhoneNumberOutGoingDto;
import aston.project.dto.PhoneNumberUpdateDto;
import aston.project.dto.PhoneNumberIncomingDto;
import aston.project.model.PhoneNumber;

import java.util.List;

public interface PhoneNumberDtoMapper {
    PhoneNumber map(PhoneNumberIncomingDto phoneNumberIncomingDto);

    PhoneNumberOutGoingDto map(PhoneNumber phoneNumber);

    List<PhoneNumberOutGoingDto> map(List<PhoneNumber> phoneNumberList);

    List<PhoneNumber> mapUpdateList(List<PhoneNumberUpdateDto> phoneNumberUpdateList);

    PhoneNumber map(PhoneNumberUpdateDto phoneNumberIncomingDto);
}
