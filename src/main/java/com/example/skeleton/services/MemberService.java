package com.example.skeleton.services;

import com.example.skeleton.api.Message;
import com.example.skeleton.api.ResponseMessageList;
import com.example.skeleton.api.ResponseMessageObject;
import com.example.skeleton.enums.ResponseStatus;
import com.example.skeleton.exception.ValidationException;
import com.example.skeleton.models.Member;
import com.example.skeleton.repositories.MemberRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Log4j2
public class MemberService {


    MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    public ResponseMessageList allMembers() {
        return ResponseMessageList.builder().data(memberRepo.findAll()).message(ResponseStatus.SUCCESS.name()).build();
    }


    public Message addMember(Member m) throws ValidationException {

        List<Member> members = memberRepo.findAll();

        for(Member x : members) {

            if(	x.getName().equals(m.getName()) && x.getSurname().equals(m.getSurname())) {

                if( x.getCellNumber().equals(m.getCellNumber()) && x.getEmail().equals(m.getEmail())){

                    throw new ValidationException("Member already exists");

                }else if(x.getCellNumber().equals(m.getCellNumber()) || x.getEmail().equals(m.getEmail())) {

                    throw new ValidationException("Similar Member already exists");

                }
            }

        }
        return ResponseMessageObject.builder().data(memberRepo.save(m)).message(ResponseStatus.SUCCESS.name()).build();

    }

    public Message getMemberByName(String name){
        return ResponseMessageList.builder().data(memberRepo.findByName(name)).message(ResponseStatus.SUCCESS.name()).build();
    }


    public Message memberLikeName(String p){
        return ResponseMessageList.builder().data(memberRepo.findByNameContains(p)).message(ResponseStatus.SUCCESS.name()).build();
    }

    public Message getMemberByID(Long id) {
        return ResponseMessageObject.builder().data(memberRepo.findById(id).orElseThrow()).message(ResponseStatus.SUCCESS.name()).build();
    }

    public Message deleteMember(Long id) throws ValidationException {
        try {
            memberRepo.deleteById(id);
            return ResponseMessageList.builder().message(ResponseStatus.SUCCESS.name()).build();

        }catch(NoSuchElementException e) {
            throw new ValidationException("Trying to delete a member that does not exist");
        }catch(Exception ex){
            ex.printStackTrace();
            throw new ValidationException(ex.getMessage());
        }
    }

    public Message update(Long id, Member p) throws ValidationException {

        try {
            Member member =memberRepo.findById(id).orElseThrow();

            if (p.getName() != null) member.setName(p.getName());
            if (p.getSurname() != null) member.setSurname(p.getSurname());
            if (p.getEmail() != null) member.setEmail(p.getEmail());
            if (p.getAddress() != null) member.setAddress(p.getAddress());
            if (p.getArea() != null) member.setArea(p.getArea());
            if (p.getIDNumber() != null) member.setIDNumber(p.getIDNumber());
            if (p.getPostalCode() != null) member.setPostalCode(p.getPostalCode());
            if (p.getCellNumber()!= null) member.setCellNumber(p.getCellNumber());
            if (p.getHomeNumber()!= null) member.setHomeNumber(p.getHomeNumber());
            if (p.getWorkNumber()!= null) member.setWorkNumber(p.getWorkNumber());
            if(p.getDOB() != null) member.setDOB(p.getDOB());
            member.setPaidJoiningFee(p.isPaidJoiningFee());

            return ResponseMessageObject.builder().data(memberRepo.save(member)).message(ResponseStatus.SUCCESS.name()).build();

        }catch(NoSuchElementException e) {

            log.error("Trying to update a member that does not exist");
            throw new ValidationException("No such member");

        }catch(Exception ex){
            ex.printStackTrace();
            throw new ValidationException(ex.getMessage());
        }
    }

}
