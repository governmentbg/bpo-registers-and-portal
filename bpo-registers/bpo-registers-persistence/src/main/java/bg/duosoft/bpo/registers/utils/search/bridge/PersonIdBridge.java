package bg.duosoft.bpo.registers.utils.search.bridge;

import bg.duosoft.bpo.registers.entity.ipobject.EIpPersonToIpObjectId;
import org.hibernate.search.engine.backend.document.DocumentElement;
import org.hibernate.search.mapper.pojo.bridge.PropertyBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.PropertyBridgeWriteContext;

/**
 * User: ggeorgiev
 * Date: 27.04.2022
 * Time: 15:41
 */
public class PersonIdBridge implements PropertyBridge<EIpPersonToIpObjectId> {
    @Override
    public void write(DocumentElement documentElement, EIpPersonToIpObjectId eIpPersonToIpObjectId, PropertyBridgeWriteContext propertyBridgeWriteContext) {

        documentElement.addValue("ipObject.persons.id." + eIpPersonToIpObjectId.getPersonRole(), eIpPersonToIpObjectId.getPersonId().toString());
    }
}
