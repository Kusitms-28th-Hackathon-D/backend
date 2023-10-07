package com.groupD.server.repository;

import com.groupD.server.domain.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface SampleRepository extends JpaRepository<Sample, Long> {
    Sample findById(long Id);
}
