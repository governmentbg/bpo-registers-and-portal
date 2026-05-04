package bg.duosoft.bpo.registers.utils.search.bridge;


import bg.duosoft.bpo.registers.entity.ipobject.EIpPatentIpcClassId;
import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

/**
 * User: ggeorgiev
 * Date: 04.05.2022
 * Time: 15:50
 */
public class PatentIpcClassBridge implements ValueBridge<EIpPatentIpcClassId, String> {
    @Override
    public String toIndexedValue(EIpPatentIpcClassId id, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        String value = id.getSectionCode() + id.getClassCode() + id.getSubclassCode() + id.getGroupCode() + id.getSubgroupCode() + "-" + id.getEditionCode();
        return value;
    }
}
