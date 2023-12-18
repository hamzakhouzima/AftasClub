package com.youcode.aftasclub.ToolKit;

import com.youcode.aftasclub.dto.MemberDTO;
import com.youcode.aftasclub.model.Member;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.sql.Date;

public class EntityDtoConverter {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D, E> D convertToDto(E entity, Class<D> dtoClass) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper.map(entity, dtoClass);
    }

    public static <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }



    public static Member convertMemberToEntity(MemberDTO dto) {
        Member entity = new Member();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
//        entity.setIdentificationDocument(dto.getIdentificationDocument());
        entity.setNationality(dto.getNationality());
        entity.setIdentityNumber(dto.getMembershipNumber());
        entity.setAccessDate(dto.getDateOfJoining());
        // Set any additional fields here
        return entity;
    }


}
