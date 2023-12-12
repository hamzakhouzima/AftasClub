package com.youcode.aftasclub.service.Impl;

import com.youcode.aftasclub.ToolKit.EntityDtoConverter;
import com.youcode.aftasclub.dto.MemberDTO;
import com.youcode.aftasclub.exception.DuplicateRegistrationException;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.repository.MemberRepository;
import com.youcode.aftasclub.service.MemberService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private  MemberRepository memberRepository;

    EntityDtoConverter entityDtoConverter = new EntityDtoConverter();

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void registerMember(MemberDTO member) throws DuplicateRegistrationException {
        try{
            memberRepository.save(EntityDtoConverter.convertToEntity(member , Member.class));
        }  catch(Exception e){

            throw new DuplicateRegistrationException("Error caused by "+e);

        }

    }




}
