package bg.duosoft.bpo.registers.utils.search.bridge;


import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

public class IntegerToStringBridge implements ValueBridge<Integer, String> {

    @Override
    public String toIndexedValue(Integer s, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        return s == null ? null : s.toString();
    }
}
