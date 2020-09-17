package com.example.skeleton.util;

import com.example.skeleton.repositories.MemberRepo;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class UtilClass {

    MemberRepo memberRepo;

    public UtilClass(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    public String formatDate(Date date){
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public Map getDashboardDetails(){

        int members = memberRepo.findAll().size();

        Map<Object, Object> map = new HashMap<>();

        map.put("members",members);

        return map;

    }
}
