package aston.project.servlet.mapper;

import aston.project.servlet.dto.PhoneNumberOutGoingDto;
import aston.project.servlet.dto.PhoneNumberUpdateDto;
import aston.project.servlet.dto.PhoneNumberIncomingDto;
import aston.project.model.PhoneNumber;

import java.util.List;

public interface PhoneNumberDtoMapper {
    PhoneNumber map(PhoneNumberIncomingDto phoneNumberIncomingDto);

    PhoneNumberOutGoingDto map(PhoneNumber phoneNumber);

    List<PhoneNumberOutGoingDto> map(List<PhoneNumber> phoneNumberList);

    List<PhoneNumber> mapUpdateList(List<PhoneNumberUpdateDto> phoneNumberUpdateList);

    PhoneNumber map(PhoneNumberUpdateDto phoneNumberIncomingDto);
}
