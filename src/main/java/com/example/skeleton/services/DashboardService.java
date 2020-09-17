package com.example.skeleton.services;

import com.example.skeleton.api.ResponseMessageObject;
import com.example.skeleton.enums.ResponseStatus;
import com.example.skeleton.util.UtilClass;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    UtilClass utilClass;


    public DashboardService(UtilClass utilClass) {
        this.utilClass = utilClass;
    }

    public ResponseMessageObject dashboard(){

        return ResponseMessageObject.builder().data(utilClass.getDashboardDetails()).message(ResponseStatus.SUCCESS.name()).build();

    }

}
