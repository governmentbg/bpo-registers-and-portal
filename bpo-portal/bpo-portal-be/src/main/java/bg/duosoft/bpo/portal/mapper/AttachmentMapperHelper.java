package bg.duosoft.bpo.portal.mapper;

import bg.duosoft.bpo.portal.entity.FeedbackAttachmentEntity;
import bg.duosoft.bpo.portal.entity.FeedbackAttachmentEntityId;
import bg.duosoft.bpo.portal.entity.FeedbackEntity;
import bg.duosoft.bpo.publik.core.dto.common.FileStoreEntryDTO;
import bg.duosoft.bpo.publik.core.entity.common.EAttachment;
import bg.duosoft.bpo.publik.core.mapper.common.FileStoreEntryToAttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttachmentMapperHelper {
    @Autowired
    private FileStoreEntryToAttachmentMapper fileStoreEntryToAttachmentMapper;


    public List<FileStoreEntryDTO> initDtoAttachments(List<FeedbackAttachmentEntity> attachments) {
        List<FileStoreEntryDTO> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(attachments)) {
            List<EAttachment> eAttachments = attachments.stream()
                    .map(FeedbackAttachmentEntity::getAttachment)
                    .toList();
            result.addAll(fileStoreEntryToAttachmentMapper.toFileStoreEntries(eAttachments));
        }
        return result;
    }


    public void fillAttachmentsOnAfterEntity(List<FileStoreEntryDTO> attachments, FeedbackEntity target) {
        List<EAttachment> attachmentEntities = fileStoreEntryToAttachmentMapper.toAttachmentEntities(attachments);
        if (!CollectionUtils.isEmpty(attachmentEntities)) {
            if (CollectionUtils.isEmpty(target.getAttachments())) {
                target.setAttachments(new ArrayList<>());
            }
            attachmentEntities.forEach(a -> {
                FeedbackAttachmentEntity feedbackAttachmentEntity = new FeedbackAttachmentEntity();
                feedbackAttachmentEntity.setId(new FeedbackAttachmentEntityId());
                feedbackAttachmentEntity.getId().setAttachmentId(a.getId());
                feedbackAttachmentEntity.getId().setFeedbackId(target.getId());
                feedbackAttachmentEntity.setAttachment(a);
                feedbackAttachmentEntity.setFeedback(target);
                target.getAttachments().add(feedbackAttachmentEntity);
            });
        }
    }

}
