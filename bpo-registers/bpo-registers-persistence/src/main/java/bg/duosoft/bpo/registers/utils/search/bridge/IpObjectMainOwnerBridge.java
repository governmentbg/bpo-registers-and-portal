package bg.duosoft.bpo.registers.utils.search.bridge;


import bg.duosoft.bpo.registers.entity.ipobject.EIpPersonToIpObject;
import bg.duosoft.bpo.publik.core.enums.PersonRole;
import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User: ggeorgiev
 * Date: 29.04.2022
 * Time: 15:44
 */
public class IpObjectMainOwnerBridge  implements ValueBridge<List, String> {
    @Override
    public String toIndexedValue(List eIpPersonToIpObjects, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        if (!CollectionUtils.isEmpty(eIpPersonToIpObjects)) {
            List<EIpPersonToIpObject> list = (List<EIpPersonToIpObject>) eIpPersonToIpObjects;
            List<EIpPersonToIpObject> owners = list.stream().filter(r -> r.getRole().getId().equals(PersonRole.OWNER.code())).collect(Collectors.toList());
            if (owners.size() > 0) {
                EIpPersonToIpObject firstOwner = owners.stream().filter(r -> Objects.equals(r.getPersonOrder(), 1)).findFirst().orElse(owners.get(0));
                String ownerName = firstOwner.getPerson().getName();
                return ownerName;
            } else {
                return null;
            }

        }
        return null;
    }
}
