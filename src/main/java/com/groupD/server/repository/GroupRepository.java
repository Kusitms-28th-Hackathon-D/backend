package com.groupD.server.repository;

import com.groupD.server.domain.entity.Group;
import com.groupD.server.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
