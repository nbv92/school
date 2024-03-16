package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {

    Student add(Student student);

    Student get(Long id);

    Student update(Long id, Student student);

    void delete (Long id);

    List<Student> getByAge (int age);

    List<Student> getByAgeBetween(int ageFrom, int ageTo);

}
