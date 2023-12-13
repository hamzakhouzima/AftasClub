package com.youcode.aftasclub.contorller;


import com.youcode.aftasclub.ToolKit.EntityDtoConverter;
import com.youcode.aftasclub.dto.MemberDTO;
import com.youcode.aftasclub.exception.DuplicateRegistrationException;
import com.youcode.aftasclub.model.Member;
import com.youcode.aftasclub.service.Impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.function.ServerResponse.badRequest;

@RestController
public class MemberController  {

@Autowired
private MemberServiceImpl memberService;

    @PostMapping(value = "/Register")
    public ResponseEntity<MemberDTO> registerMember(@RequestBody MemberDTO memberDTO) {
        try {
            memberService.registerMember(memberDTO);
            return ResponseEntity.ok(memberDTO);
        } catch (DuplicateRegistrationException e) {
            System.out.println("Error caused by " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PostMapping("/updateMember/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody Member member) {
        try {
            memberService.updateMember(memberId, member);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            System.out.println("Error caused by " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
