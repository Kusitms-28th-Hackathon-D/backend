package com.groupD.server.controller;

import com.groupD.server.domain.dto.SampleResponse;
import com.groupD.server.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RequestMapping("/api/sample")
@RestController
public class SampleController {
private final SampleService sampleService;

    @GetMapping("/get")
    public ResponseEntity<SampleResponse> geturl(@RequestParam long id) throws Exception {
        return new ResponseEntity<>( sampleService.getUrl(id), HttpStatus.OK);
    }
}
