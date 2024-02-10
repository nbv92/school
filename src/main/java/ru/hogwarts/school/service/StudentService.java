package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {

    Student add(Student student);

    Student get(Long id);

    Student update(Long id, Student student);

    Student delete (Long id);

    List<Student> getByAge (int age);
}
