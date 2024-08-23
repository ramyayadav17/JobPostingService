package com.example.DTO;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import io.swagger.models.auth.In;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingDTO {

    private Long jobId;
    private String title;
    private String description;
    private String location;
    private String company;
    private String salaryRange;

    private List<String> requiredSkills;

}
