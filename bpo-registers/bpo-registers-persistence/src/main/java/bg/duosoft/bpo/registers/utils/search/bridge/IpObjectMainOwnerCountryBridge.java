package bg.duosoft.bpo.registers.utils.search.bridge;


import bg.duosoft.bpo.publik.core.enums.PersonRole;
import bg.duosoft.bpo.registers.entity.ipobject.EIpPersonToIpObject;
import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class IpObjectMainOwnerCountryBridge implements ValueBridge<List, String> {
    @Override
    public String toIndexedValue(List eIpPersonToIpObjects, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        if (!CollectionUtils.isEmpty(eIpPersonToIpObjects)) {
            List<EIpPersonToIpObject> list = (List<EIpPersonToIpObject>) eIpPersonToIpObjects;
            List<EIpPersonToIpObject> owners = list.stream().filter(r -> r.getRole().getId().equals(PersonRole.OWNER.code())).collect(Collectors.toList());
            if (owners.size() > 0) {
                EIpPersonToIpObject firstOwner = owners.stream().filter(r -> Objects.equals(r.getPersonOrder(), 1)).findFirst().orElse(owners.get(0));
                String ownerCountryCode = firstOwner.getPerson().getNationalityCountryCode().getId();
                return ownerCountryCode;
            } else {
                return null;
            }

        }
        return null;
    }
}
