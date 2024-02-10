package ru.hogwarts.school.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import ru.hogwarts.school.service.FacultyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    private static long currentId = 1;

    @PostConstruct
    public void initFacultys() {
        add(new Faculty("Gryffindor","Red"));
        add(new Faculty("Hufflepuff","Yellow"));
        add(new Faculty("Ravenclaw","Blue"));
        add(new Faculty("Slytherin","Green"));
    }

    @Override
    public Faculty add(Faculty faculty) {
        faculty.setId(currentId++);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty get(Long id) {
        return facultyMap.get(id);
    }

    @Override
    public Faculty update(Long id, Faculty Faculty) {
        Faculty facultyFronStorage = facultyMap.get(id);
        facultyFronStorage.setColor(facultyFronStorage.getColor());
        facultyFronStorage.setName(facultyFronStorage.getName());
        return facultyFronStorage;
    }

    @Override
    public Faculty delete(Long id) {
        return facultyMap.remove(id);
    }

    @Override
    public List<Faculty> getByColor(String color) {
        return facultyMap.values()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
