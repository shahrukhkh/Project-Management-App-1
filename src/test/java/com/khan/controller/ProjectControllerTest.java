package com.khan.controller;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khan.model.Project;
import com.khan.repository.ProjectRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateProject() throws Exception {
        Project project = new Project();
        project.setName("New Project");
        project.setDescription("Description of new project");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(10));

        mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Project"));
    }

    @Test
    void testGetAllProjects() throws Exception {
        mockMvc.perform(get("/projects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetProjectById() throws Exception {
        Project project = new Project();
        project.setName("Project by ID");
        project.setDescription("Description");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(10));
        project = projectRepository.save(project);

        mockMvc.perform(get("/projects/" + project.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Project by ID"));
    }

    @Test
    void testUpdateProject() throws Exception {
        Project project = new Project();
        project.setName("Old Project");
        project.setDescription("Old Description");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(10));
        project = projectRepository.save(project);

        project.setName("Updated Project");

        mockMvc.perform(put("/projects/" + project.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Project"));
    }

    @Test
    void testDeleteProject() throws Exception {
        Project project = new Project();
        project.setName("Delete Project");
        project.setDescription("Description");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(10));
        project = projectRepository.save(project);

        mockMvc.perform(delete("/projects/" + project.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
