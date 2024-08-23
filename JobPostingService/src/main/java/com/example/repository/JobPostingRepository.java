package com.example.repository;

import com.example.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    @Query(value = "SELECT * FROM job_posting j WHERE " +
            "j.title LIKE %:keyword% OR j.location LIKE %:location% OR " +
            "EXISTS (SELECT 1 FROM JSON_TABLE(j.required_skills, '$[*]' COLUMNS(skill VARCHAR(255) PATH '$')) AS tmp WHERE skill LIKE %:skill%)",
            nativeQuery = true)
    List<JobPosting> searchJobPostings(@Param("keyword") String keyword,
                                       @Param("location") String location,
                                       @Param("skill") String skill);
}