package com.swithus.community.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/manager")
@RequiredArgsConstructor
public class GatheringController {

    @GetMapping("/gathering")
    public void gathering_list(){

    }
}
