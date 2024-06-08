package aston.project.service.impl;

import aston.project.servlet.dto.PhoneNumberIncomingDto;
import aston.project.servlet.dto.PhoneNumberOutGoingDto;
import aston.project.servlet.dto.PhoneNumberUpdateDto;
import aston.project.servlet.mapper.PhoneNumberDtoMapper;
import aston.project.model.PhoneNumber;
import aston.project.repository.PhoneNumberRepository;
import aston.project.service.PhoneNumberService;
import aston.project.exception.NotFoundException;
import aston.project.servlet.mapper.impl.PhoneNumberDtoMapperImpl;
import aston.project.repository.impl.PhoneNumberRepositoryImpl;

import java.util.List;

public class PhoneNumberServiceImpl implements PhoneNumberService {
    private final PhoneNumberDtoMapper phoneNumberDtoMapper = PhoneNumberDtoMapperImpl.getInstance();
    private static PhoneNumberService instance;
    private final PhoneNumberRepository phoneNumberRepository = PhoneNumberRepositoryImpl.getInstance();


    private PhoneNumberServiceImpl() {
    }

    public static synchronized PhoneNumberService getInstance() {
        if (instance == null) {
            instance = new PhoneNumberServiceImpl();
        }
        return instance;
    }

    @Override
    public PhoneNumberOutGoingDto save(PhoneNumberIncomingDto phoneNumberDto) {
        PhoneNumber phoneNumber = phoneNumberDtoMapper.map(phoneNumberDto);
        phoneNumber = phoneNumberRepository.save(phoneNumber);
        return phoneNumberDtoMapper.map(phoneNumber);
    }

    @Override
    public void update(PhoneNumberUpdateDto phoneNumberUpdateDto) throws NotFoundException {
        if (phoneNumberRepository.exitsById(phoneNumberUpdateDto.getId())) {
            PhoneNumber phoneNumber = phoneNumberDtoMapper.map(phoneNumberUpdateDto);
            phoneNumberRepository.update(phoneNumber);
        } else {
            throw new NotFoundException("PhoneNumber not found.");
        }
    }

    @Override
    public PhoneNumberOutGoingDto findById(Long phoneNumberId) throws NotFoundException {
        PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId).orElseThrow(() ->
                new NotFoundException("PhoneNumber not found."));
        return phoneNumberDtoMapper.map(phoneNumber);
    }

    @Override
    public List<PhoneNumberOutGoingDto> findAll() {
        List<PhoneNumber> phoneNumberList = phoneNumberRepository.findAll();
        return phoneNumberDtoMapper.map(phoneNumberList);
    }

    @Override
    public boolean delete(Long phoneNumberId) {
        return phoneNumberRepository.deleteById(phoneNumberId);
    }

}
