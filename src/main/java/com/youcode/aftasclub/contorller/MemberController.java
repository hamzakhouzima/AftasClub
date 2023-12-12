package com.youcode.aftasclub.contorller;


import com.youcode.aftasclub.ToolKit.EntityDtoConverter;
import com.youcode.aftasclub.dto.MemberDTO;
import com.youcode.aftasclub.exception.DuplicateRegistrationException;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.service.Impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.function.ServerResponse.badRequest;

@RestController
public class MemberController  {

@Autowired
private MemberServiceImpl memberService;

    @PostMapping(value="/Register")
    public ResponseEntity<MemberDTO> registerMember(@RequestBody MemberDTO memberDTO) throws DuplicateRegistrationException {
//        try{
            memberService.registerMember(memberDTO);
            return ResponseEntity.ok(memberDTO);
//        }catch(Exception e){
//            System.out.println("error caused by "+e);
////            throw new DuplicateRegistrationException();
//            return (ResponseEntity<MemberDTO>) badRequest().build();
//        }
    }


}
