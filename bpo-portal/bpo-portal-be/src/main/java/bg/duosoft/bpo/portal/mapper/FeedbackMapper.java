package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.common.service.mapper.BaseObjectMapper;
import bg.duosoft.bpo.portal.dto.Feedback;
import bg.duosoft.bpo.portal.entity.FeedbackEntity;
import bg.duosoft.bpo.publik.core.dto.common.FileStoreEntryDTO;
import bg.duosoft.bpo.publik.core.entity.nomenclature.EObjectType;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class FeedbackMapper extends BaseObjectMapper<FeedbackEntity, Feedback> {

    @Autowired
    private AttachmentMapperHelper attachmentMapperHelper;

    @Mapping(target = "attachments", ignore = true)
    @Mapping(target = "objectType", source = "objectType.id")
    public abstract Feedback toDto(FeedbackEntity entity);

    @Mapping(target = "attachments", ignore = true)
    @Mapping(target = "objectType", expression = "java(toObjectTypeEntity(feedback.getObjectType()))")
    public abstract FeedbackEntity toEntity(Feedback feedback);

    @AfterMapping
    protected void afterToEntity(Feedback source, @MappingTarget FeedbackEntity target) {
        attachmentMapperHelper.fillAttachmentsOnAfterEntity(source.getAttachments(), target);
    }

    protected EObjectType toObjectTypeEntity(String objectType) {
        if (objectType != null) {
            EObjectType res = new EObjectType();
            res.setId(objectType);
            return res;
        }
        return null;
    }

    @AfterMapping
    protected void afterToDto(FeedbackEntity source, @MappingTarget Feedback target) {
        List<FileStoreEntryDTO> attachments = attachmentMapperHelper.initDtoAttachments(source.getAttachments());
        target.setAttachments(attachments);
    }
}
