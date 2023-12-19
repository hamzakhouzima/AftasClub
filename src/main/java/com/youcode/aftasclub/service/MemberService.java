package com.youcode.aftasclub.service;

import com.youcode.aftasclub.dto.MemberDTO;
import com.youcode.aftasclub.exception.DuplicateRegistrationException;
import com.youcode.aftasclub.model.Member;

import java.util.List;

public interface MemberService {

    void registerMember(MemberDTO member) throws DuplicateRegistrationException;

    Member getMemberById(Long id);

    MemberDTO getMemberByCode(String code);

    void updateMember(Long id, Member member);

    Member findMembersByIdentityNumber(String identityNumber);

    void deleteMember(Member member);
}
