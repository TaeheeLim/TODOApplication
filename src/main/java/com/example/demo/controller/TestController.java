package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String testController() {
        return "hello Nkia";
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("hello world! im a responsedto");

        ResponseDTO<String> response = ResponseDTO.<String>builder()
                .data(list)
                .build();

        return response;
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        ArrayList<String> list = new ArrayList<>();
        list.add("hello world! im responseentity. and you got 400!");
        ResponseDTO<String> response = ResponseDTO.<String>builder()
                .data(list)
                .build();

        return ResponseEntity.badRequest().body(response);
    }
}
