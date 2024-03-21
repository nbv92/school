package ru.hogwarts.school.dto;

import java.util.Objects;

public class AvatarDTO {

    private Long id;

    private long fileSize;

    private String mediaType;

    private long sudentId;

    public AvatarDTO (Long id, long fileSize, String mediaType, long studentId) {
        this.id=id;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.sudentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public AvatarDTO() {
    }

    public long getSudentId() {
        return sudentId;
    }

    public void setSudentId(long sudentId) {
        this.sudentId = sudentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvatarDTO avatarDTO = (AvatarDTO) o;
        return fileSize == avatarDTO.fileSize && sudentId == avatarDTO.sudentId && Objects.equals(id, avatarDTO.id) && Objects.equals(mediaType, avatarDTO.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileSize, mediaType, sudentId);
    }

    @Override
    public String toString() {
        return "AvatarDTO{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", sudentId=" + sudentId +
                '}';
    }
}
