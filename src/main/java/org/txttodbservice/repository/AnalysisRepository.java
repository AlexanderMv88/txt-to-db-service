package org.txttodbservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.txttodbservice.entity.Analysis;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}
