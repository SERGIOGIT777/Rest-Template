package aston.project.repository;

import aston.project.model.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PhoneNumberRepository extends Repository<PhoneNumber, Long> {
    boolean exitsById(Long id);

    List<PhoneNumber> findAllByUserId(Long userId);

    boolean deleteByUserId(Long userId);

    boolean existsByNumber(String number);

    Optional<PhoneNumber> findByNumber(String number);
}
