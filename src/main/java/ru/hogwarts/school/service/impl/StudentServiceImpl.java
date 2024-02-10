package ru.hogwarts.school.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final Map<Long, Student> students = new HashMap<>();

    private static long currentId = 1;

    @PostConstruct
    public void initStudents() {
        add(new Student("Nino",23));
        add(new Student("Set", 54));
    }

    @Override
    public Student add(Student student) {
        student.setId(currentId++);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student get(Long id) {
        return students.get(id);
    }

    @Override
    public Student update(Long id, Student student) {
        Student studentFromStorage = students.get(id);
        studentFromStorage.setName(student.getName());
        studentFromStorage.setAge(student.getAge());
        return studentFromStorage;
    }

    @Override
    public Student delete(Long id) {
        return students.remove(id);
    }

    @Override
    public List<Student> getByAge(int age) {
        return students.values()
                .stream()
                .filter(it ->it.getAge() == age)
                .collect(Collectors.toList());
    }
}
