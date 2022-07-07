package com.fundamentosp.springboot.fundamentos.repository;

import com.fundamentosp.springboot.fundamentos.dto.UserDto;
import com.fundamentosp.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("Select u from User u WHERE u.email = ?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u WHERE u.name LIKE ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByNameContaining(String name);

    //… where x.firstname like ?1
    List<User> findByNameLike(String name);

    //… where x.name = ?1 and x.email = ?2
    Optional<User> findUsersByNameAndAndEmail(String name, String email);

    //… where x.name = ?1 or x.email = ?2
    Optional<User> findUsersByNameOrAndEmail(String name, String email);

    //… where x.birthDate between ?1 and ?2
    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    //… where x.age = ?1 order by x.lastname desc
    List<User> findByNameLikeOrderByIdDesc(String name);

    //… where x.age = ?1 order by x.lastname desc
    List<User> findByNameContainingOrderByIdDesc(String name);

    //Using Named Parameters
    @Query("select new com.fundamentosp.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)" +
            " from User u " +
            "where u.birthDate = :paramBirthDate and u.email = :paramEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("paramBirthDate") LocalDate date, @Param("paramEmail") String email);

    List<User> findAll();
}
