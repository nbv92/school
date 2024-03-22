package ru.hogwarts.school.mapper;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.dto.AvatarDTO;
import ru.hogwarts.school.model.Avatar;

@Component
public class AvatarMapper {
    public AvatarDTO mapToDTO (Avatar avatar) {
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setId(avatar.getId());
        avatarDTO.setMediaType(avatar.getMediaType());
        avatarDTO.setFileSize(avatar.getFileSize());
        avatarDTO.setSudentId(avatar.getStudent().getId());

        return avatarDTO;
    }
}
