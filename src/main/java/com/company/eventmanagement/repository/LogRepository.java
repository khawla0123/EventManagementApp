package com.company.eventmanagement.repository;

import com.company.eventmanagement.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {}
