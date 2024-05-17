package com.khan.service;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.khan.model.Project;
import com.khan.repository.ProjectRepository;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    ProjectServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {
        Project project = new Project();
        project.setName("Test Project");
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project created = projectService.createProject(project);
        assertNotNull(created);
        assertEquals("Test Project", created.getName());
    }

    @Test
    void testGetProjectById() {
        Project project = new Project();
        project.setId(1L);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<Project> found = projectService.getProjectById(1L);
        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());
    }

    @Test
    void testUpdateProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Updated Project");
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updated = projectService.updateProject(1L, project);
        assertNotNull(updated);
        assertEquals("Updated Project", updated.getName());
    }

    @Test
    void testDeleteProject() {
        Project project = new Project();
        project.setId(1L);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        doNothing().when(projectRepository).delete(project);

        assertDoesNotThrow(() -> projectService.deleteProject(1L));
        verify(projectRepository, times(1)).delete(project);
    }
}
