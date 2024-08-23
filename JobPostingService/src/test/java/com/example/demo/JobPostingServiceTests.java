package com.example.demo;

import com.example.DTO.JobPostingDTO;
import com.example.model.JobPosting;
import com.example.repository.JobPostingRepository;
import com.example.service.JobPostingService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JobPostingServiceTests {

    @InjectMocks
    private JobPostingService service;

    @Mock
    private JobPostingRepository repository;

    @Test
    void testCreateJobPosting() {
        JobPosting jobPosting = new JobPosting();
		jobPosting.setCompany("analyttica");
		jobPosting.setTitle("Developer role");

		JobPostingDTO jobPostingDTO = new JobPostingDTO();
		jobPostingDTO.setCompany("analyttica");
		jobPostingDTO.setTitle("Developer role");

        when(repository.save(jobPosting)).thenReturn(jobPosting);
        service.createJobPosting(jobPostingDTO);
      //  assertEquals(jobPosting, jobPostingDTO);
    }
}
