package com.ex.reimbursementapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test") // /<context>/test
public class TestController {

@GetMapping
    public String getTestMsg(){
    return "Helloooooooo World";
}

@GetMapping(path = "{id}")
    public ResponseEntity getTestMsgById(@PathVariable Integer id){
    return ResponseEntity.ok("Your request message "+ id);
}


@GetMapping("search") // test/search?startsWith=hey
    public ResponseEntity searchForMsg(@RequestParam String startsWith){
    return ResponseEntity.ok("Filtering messages that start with "+ startsWith);
}
}
