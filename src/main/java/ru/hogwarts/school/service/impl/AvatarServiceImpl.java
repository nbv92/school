package ru.hogwarts.school.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Service
public class AvatarServiceImpl implements AvatarService {

    private final StudentRepository studentRepository;

    private final AvatarRepository avatarRepository;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final int BUFFER_SIZE = 1024;
    public AvatarServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow();
        Path path = saveToDisk(student, avatarFile);
        saveToDd(student, avatarFile, path);
    }

    @Override
    public Avatar findAvatar(Long id) {
        return avatarRepository.findById(id).orElseThrow();
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Avatar findAvatarByStudentId(Long studentId) {
        return
                avatarRepository.findByStudent_id(studentId)
                        .orElse(new Avatar());
    }

    private Path saveToDisk(Student student, MultipartFile avatarFile) throws IOException {
        Path filePath = Path.of(avatarsDir, "student" + student.getId() + "avatar." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);
                BufferedOutputStream bos = new BufferedOutputStream(os, BUFFER_SIZE);
        ) {
            bis.transferTo(bos);
        }
        return filePath;
    }
    private void saveToDd(Student student, MultipartFile avatarFile, Path filePath) throws IOException {
        Avatar avatar = findAvatarByStudentId(student.getId());
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }
}
