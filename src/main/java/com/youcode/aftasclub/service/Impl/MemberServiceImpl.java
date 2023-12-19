package com.youcode.aftasclub.service.Impl;

import com.youcode.aftasclub.ToolKit.EntityDtoConverter;
import com.youcode.aftasclub.dto.MemberDTO;
import com.youcode.aftasclub.exception.DuplicateRegistrationException;
import com.youcode.aftasclub.exception.MemberNotFoundException;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.repository.MemberRepository;
import com.youcode.aftasclub.service.CompetitionService;
import com.youcode.aftasclub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private  MemberRepository memberRepository;

    EntityDtoConverter entityDtoConverter = new EntityDtoConverter();

    CompetitionServiceImpl competitionService ;

    Date currentDate = Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC));


    @Override
    public void registerMember(MemberDTO member) throws DuplicateRegistrationException {
        //TODO : make this condition check if the member exists by last name and first name
        //TODO : this could bug the code , go through it if something goes wrong

        if (memberRepository.existsMemberByLastName(member.getLastName()) != null) {
            throw new DuplicateRegistrationException("Member already exists");
        }

        try {
            String r = CompetitionServiceImpl.generateRandomString(8);
            member.setMembershipNumber(r);
            member.setDateOfJoining(currentDate);
//            member.setDateOfJoining(new Date());
            Member member1 = EntityDtoConverter.convertMemberToEntity(member);
            memberRepository.save(member1);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while registering member", e);
        }
    }

    @Override
    public Member getMemberById(Long id) {

        try{
            Member member = memberRepository.findById(id).get();
            return member ;
        }catch(Exception e){
            System.out.println("error caused by "+e);
            throw new MemberNotFoundException("Error caused by "+e);
        }
    }



//    @Override
//    public Member getMemberByCode(String code) {
//        try{
//            Member member = memberRepository.findByIdentityNumber(code);
//            return member ;
//        }catch(Exception e){
//            System.out.println("error caused by "+e);
//            throw new MemberNotFoundException("Error caused by "+e);
//        }
//    }
    @Override
    public MemberDTO getMemberByCode(String code) {
        try {
            Member member = memberRepository.findByIdentityNumber(code);
            // Map the Member entity to MemberDTO
            MemberDTO memberDTO = mapToMemberDTO(member);
            return memberDTO;
        } catch (Exception e) {
            System.out.println("Error caused by " + e);
            throw new MemberNotFoundException("Error caused by " + e);
        }
    }

    // Map Member entity to MemberDTO
    private MemberDTO mapToMemberDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setFirstName(member.getFirstName());
        memberDTO.setLastName(member.getLastName());
        memberDTO.setDateOfJoining(member.getAccessDate());
        memberDTO.setNationality(member.getNationality());
        memberDTO.setMembershipNumber(member.getIdentityNumber());
//        memberDTO.setMembershipNumber();
        // Map other fields as needed

        return memberDTO;
    }




    public Member findMembersByIdentityNumber(String identityNumber) {

        try{
            Member members = memberRepository.findByIdentityNumber(identityNumber);
            return members;
        }catch(Exception e){
            System.out.println("error caused by "+e);
            throw new MemberNotFoundException("Members not found with identity number => "+e);
        }

    }




    @Override
    public void updateMember(Long id, Member updatedMember) {
        try {
            Member member = memberRepository.findById(id)
                    .orElseThrow(() -> new MemberNotFoundException("Member not found"));

            if (updatedMember.getFirstName() != null) {
                member.setFirstName(updatedMember.getFirstName());
            }
            if (updatedMember.getLastName() != null) {
                member.setLastName(updatedMember.getLastName());
            }
            if (updatedMember.getAccessDate() != null) {
                member.setAccessDate(updatedMember.getAccessDate());
            }
            if (updatedMember.getNationality() != null) {
                member.setNationality(updatedMember.getNationality());
            }

            memberRepository.save(member);
        } catch (Exception e) {
            System.out.println("Error caused by " + e);
            throw new MemberNotFoundException("Error caused by " + e);
        }
    }




    @Override
    public void deleteMember(Member member) {

        try{
            memberRepository.delete(member);
        }catch(Exception e){
            System.out.println("error caused by "+e);
            throw new MemberNotFoundException("Error caused by "+e);
        }

    }


}
