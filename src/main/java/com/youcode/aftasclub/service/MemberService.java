package com.youcode.aftasclub.service;

import com.youcode.aftasclub.dto.MemberDTO;
import com.youcode.aftasclub.exception.DuplicateRegistrationException;
import com.youcode.aftasclub.model.Member;

public interface MemberService {


    public void registerMember(MemberDTO member) throws DuplicateRegistrationException;
}
