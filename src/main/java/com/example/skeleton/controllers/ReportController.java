package com.example.skeleton.controllers;

import com.example.skeleton.api.ErrorMessage;
import com.example.skeleton.dto.ReportServiceDTO;
import com.example.skeleton.exception.ValidationException;
import com.example.skeleton.services.ReportService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.skeleton.enums.ResponseStatus;
@RestController
@CrossOrigin
@RequestMapping("/v1/reports")
public class ReportController {

    ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping({"/memberDetails", "/memberDetails/{email}"})
    public ResponseEntity<?> memberDetails(@PathVariable(required = false) String email){

        try {
            if(email == null){
                ReportServiceDTO m = reportService.memberDetails();

                return ResponseEntity.ok().headers(m.getHttpHeaders()).contentType(m.getContentType())
                        .body(new InputStreamResource(m.getInputStream()));

            }else{
                return new ResponseEntity<>(reportService.emailMemberDetails(email), HttpStatus.OK);
            }

        } catch (ValidationException e) {
            return new ResponseEntity<>(new ErrorMessage(e.getMessage(), ResponseStatus.FAILURE.name()), HttpStatus.BAD_REQUEST);
        }

    }


}
