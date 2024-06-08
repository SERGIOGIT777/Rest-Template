package aston.project.service;

import aston.project.servlet.dto.PhoneNumberIncomingDto;
import aston.project.servlet.dto.PhoneNumberOutGoingDto;
import aston.project.servlet.dto.PhoneNumberUpdateDto;
import aston.project.exception.NotFoundException;

import java.util.List;

public interface PhoneNumberService {
    PhoneNumberOutGoingDto save(PhoneNumberIncomingDto phoneNumber);

    void update(PhoneNumberUpdateDto phoneNumber) throws NotFoundException;

    PhoneNumberOutGoingDto findById(Long phoneNumberId) throws NotFoundException;

    List<PhoneNumberOutGoingDto> findAll();

    boolean delete(Long phoneNumberId);
}
