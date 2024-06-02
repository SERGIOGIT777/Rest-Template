package aston.project.mapper.impl;

import aston.project.dto.PhoneNumberIncomingDto;
import aston.project.dto.PhoneNumberOutGoingDto;
import aston.project.dto.PhoneNumberUpdateDto;
import aston.project.dto.UserSmallOutGoingDto;
import aston.project.mapper.PhoneNumberDtoMapper;
import aston.project.model.PhoneNumber;
import aston.project.model.User;

import java.util.List;

public class PhoneNumberDtoMapperImpl implements PhoneNumberDtoMapper {
    private static PhoneNumberDtoMapper instance;

    private PhoneNumberDtoMapperImpl() {
    }

    public static synchronized PhoneNumberDtoMapper getInstance() {
        if (instance == null) {
            instance = new PhoneNumberDtoMapperImpl();
        }
        return instance;
    }

    @Override
    public PhoneNumber map(PhoneNumberIncomingDto phoneDto) {
        return new PhoneNumber(
                null,
                phoneDto.getNumber(),
                null
        );
    }

    @Override
    public PhoneNumberOutGoingDto map(PhoneNumber phoneNumber) {
        return new PhoneNumberOutGoingDto(
                phoneNumber.getId(),
                phoneNumber.getNumber(),
                phoneNumber.getUser() == null ?
                        null :
                        new UserSmallOutGoingDto(
                                phoneNumber.getUser().getId(),
                                phoneNumber.getUser().getFirstName(),
                                phoneNumber.getUser().getLastName()
                        )
        );
    }

    @Override
    public List<PhoneNumberOutGoingDto> map(List<PhoneNumber> phoneNumberList) {
        return phoneNumberList.stream().map(this::map).toList();
    }

    @Override
    public List<PhoneNumber> mapUpdateList(List<PhoneNumberUpdateDto> phoneNumberUpdateList) {
        return phoneNumberUpdateList.stream().map(this::map).toList();
    }

    @Override
    public PhoneNumber map(PhoneNumberUpdateDto phoneDto) {
        return new PhoneNumber(
                phoneDto.getId(),
                phoneDto.getNumber(),
                new User(
                        phoneDto.getUserId(),
                        null,
                        null,
                        null,
                        List.of(),
                        List.of()
                )
        );
    }

}
