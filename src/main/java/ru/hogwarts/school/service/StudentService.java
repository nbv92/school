package ru.hogwarts.school.service;

import org.springframework.web.bind.annotation.GetMapping;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {

    Student add(Student student);

    Student get(Long id);

    Student update(Long id, Student student);

    void delete (Long id);

    List<Student> getByAge (int age);

    List<Student> getByAgeBetween(int ageFrom, int ageTo);

    Faculty getFaculty(Long id);

    public int getStudentsCount();

    public int getAverageAge();

    public List<Student> getLastFive();

    List<String> getAllStudentsStartsWithA();

    Double getAverageAgeWithStreams();



}
