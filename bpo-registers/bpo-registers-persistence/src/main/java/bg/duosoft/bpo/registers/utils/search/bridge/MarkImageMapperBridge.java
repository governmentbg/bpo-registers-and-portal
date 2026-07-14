package bg.duosoft.bpo.registers.utils.search.bridge;

import bg.duosoft.bpo.registers.entity.ipobject.EIpMarkAttachment;
import bg.duosoft.bpo.publik.core.enums.AttachmentType;
import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class MarkImageMapperBridge implements ValueBridge<List, Boolean> {
    @Override
    public Boolean toIndexedValue(List eIpMarkAttachments, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        List<EIpMarkAttachment> list = (List<EIpMarkAttachment>) eIpMarkAttachments;
        boolean res = false;
        if (!CollectionUtils.isEmpty(eIpMarkAttachments)) {
            res = list.stream().anyMatch(a -> a.getAttachment().getAttachmentType().getId().equals(AttachmentType.IMAGE.code()));
        }
        return res;
    }
}
