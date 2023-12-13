package com.youcode.aftasclub.service.Impl;

import com.youcode.aftasclub.ToolKit.EntityDtoConverter;
import com.youcode.aftasclub.dto.MemberDTO;
import com.youcode.aftasclub.exception.DuplicateRegistrationException;
import com.youcode.aftasclub.exception.MemberNotFoundException;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.repository.MemberRepository;
import com.youcode.aftasclub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private  MemberRepository memberRepository;

    EntityDtoConverter entityDtoConverter = new EntityDtoConverter();




    @Override
    public void registerMember(MemberDTO member) throws DuplicateRegistrationException {
        try{
            memberRepository.save(EntityDtoConverter.convertToEntity(member , Member.class));
        }  catch(Exception e){

            throw new DuplicateRegistrationException("Error caused by "+e);

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

    @Override
    public List<Member> getMemberByCode(String code) {
        try{
            List<Member> member = memberRepository.findMembersByIdentityNumber(code);
            return member ;
        }catch(Exception e){
            System.out.println("error caused by "+e);
            throw new MemberNotFoundException("Error caused by "+e);
        }
    }




    public List<Member> findMembersByLastName(String identityNumber) {

        try{
            List<Member> members = memberRepository.findMembersByIdentityNumber(identityNumber);
            return members;
        }catch(Exception e){
            System.out.println("error caused by "+e);
            throw new MemberNotFoundException("Error caused by "+e);
        }

    }




    @Override
    public void updateMember(Long id, Member updatedMember) {
        try{
            memberRepository.findById(id).ifPresent(member -> {

                member.setFirstName(updatedMember.getFirstName());
                member.setLastName(updatedMember.getLastName());
                member.setAccessDate(updatedMember.getAccessDate());
                member.setNationality(updatedMember.getNationality());

                memberRepository.save(member);
            });
        }catch(Exception e){
            System.out.println("error caused by "+e);
            throw new MemberNotFoundException("Error caused by "+e);
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
