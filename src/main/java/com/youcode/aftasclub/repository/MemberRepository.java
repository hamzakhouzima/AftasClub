package com.youcode.aftasclub.repository;

import com.youcode.aftasclub.model.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

//    Member findByCode(String code);
    Member findMemberByFirstNameAndLastName(String firstName, String lastName);
//TODO :
//    @Query("SELECT m FROM Member m WHERE m.identityNumber = :identityNumber")
//    List<Member> findMembersByIdentityNumber(@Param("identityNumber") String identityNumber);

    Member findByIdentityNumber(String identityNumber);


//    @Query(name = "org.springframework.data.jpa.repository.query.Procedure")
//    @Transactional
//    void update(Long memberId, Member updatedMember);

}
