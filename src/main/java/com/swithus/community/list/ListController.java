package com.swithus.community.list;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
@Log4j2
@RequiredArgsConstructor
public class ListController {
    @GetMapping("/myarticle")
    public String myarticle(){

        return "list/myarticle";
    }

    @GetMapping("/mycomment")
    public String mycomment(){

        return "list/mycomment";
    }

    @GetMapping("/mygatheringlist")
    public String mygatheringlist(){

        return "list/mygatheringlist";
    }

    @GetMapping("/mylikelist")
    public String mylikelist(){

        return "list/mylikelist";
    }
}
