package com.example.skeleton.reports;

import com.example.skeleton.util.UtilClass;
import org.springframework.stereotype.Component;

@Component
public class ReportData {

    UtilClass utilClass;

    public ReportData(UtilClass utilClass) {
        this.utilClass = utilClass;
    }

}
