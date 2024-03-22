package ru.hogwarts.school.service.impl;

import jakarta.annotation.PostConstruct;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    /*@PostConstruct
    public void initStudents() {
        add(new Student("Nino",23));
        add(new Student("Set", 54));
    }*/

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        logger.info("add method was invoked!");
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        logger.info("get method was invoked!");
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        logger.info("update method was invoked!");
        return studentRepository.findById(id).map(studentFromDb -> {
            studentFromDb.setName(student.getName());
            studentFromDb.setAge(student.getAge());
            return studentRepository.save(studentFromDb);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {

        logger.info("delete method was invoked!");
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getByAge(int age) {
        logger.info("getByAge method was invoked!");
        return  studentRepository.findAll()
                .stream()
                .filter(it ->it.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getByAgeBetween(int ageFrom, int ageTo) {
        logger.info("getByAgeBetween method was invoked!");
        return studentRepository.findByAgeBetween (ageFrom, ageTo );
    }

    @Override
    public Faculty getFaculty(Long id) {
        logger.info("getFaculty method was invoked!");
        return studentRepository.findById(id)
                .map(Student::getFaculty)
                .orElse(null);
    }

    @Override
    public int getStudentsCount() {
        logger.info("getStudentsCount method was invoked!");
        return studentRepository.getStudentsCount();
    }

    @Override
    public int getAverageAge() {
        logger.info("getAverageAge method was invoked!");
        return studentRepository.getAverageAge();
    }

    @Override
    public List<Student> getLastFive() {
        logger.info("getLastFive method was invoked!");
        return studentRepository.getLastFive();
    }

    @Override
    public List<String> getAllStudentsStartsWithA() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(it -> it.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageAgeWithStreams() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average().orElse(0.0);
    }

    @Override
    public void printSynchronized() {
        List<String> names = studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .toList();

        printSynchronizedName(names.get(0));
        printSynchronizedName(names.get(1));



        new Thread(() -> {
            printSynchronizedName(names.get(2));
            printSynchronizedName(names.get(3));
        }).start();

        new Thread(() -> {
            printSynchronizedName(names.get(4));
            printSynchronizedName(names.get(5));
        }).start();
    }

    @Override
    public void printParallel() {
        List<String> names = studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .toList();

        printName(names.get(0));
        printName(names.get(1));



        new Thread(() -> {
            printName(names.get(2));
            printName(names.get(3));
        }).start();

        new Thread(() -> {
            printName(names.get(4));
            printName(names.get(5));
        }).start();
    }

    private void printName(String name) {
        System.out.println(Thread.currentThread().getName() + ": " + name);

    }

    private synchronized void printSynchronizedName(String name) {
        System.out.println(Thread.currentThread().getName() + ": " + name);

    }
}
