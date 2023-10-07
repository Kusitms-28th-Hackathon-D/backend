package com.groupD.server.service;

import com.groupD.server.domain.Sample;
import com.groupD.server.domain.dto.SampleResponse;
import com.groupD.server.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SampleService {
private final SampleRepository sampleRepository;
    public SampleResponse getUrl(long id) {
        Sample sample = sampleRepository.findById(id);

        return new SampleResponse(sample);
    }
}
