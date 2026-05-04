package bg.duosoft.bpo.registers.utils.search.bridge;


import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentCpcClassId;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentIpcClassId;
import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

public class PatentCpcClassBridge implements ValueBridge<EIpPatentCpcClassId, String> {
    @Override
    public String toIndexedValue(EIpPatentCpcClassId id, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        String value = id.getSectionCode() + id.getClassCode() + id.getSubclassCode() + id.getGroupCode() + id.getSubgroupCode() + "-" + id.getEditionCode();
        return value;
    }
}
