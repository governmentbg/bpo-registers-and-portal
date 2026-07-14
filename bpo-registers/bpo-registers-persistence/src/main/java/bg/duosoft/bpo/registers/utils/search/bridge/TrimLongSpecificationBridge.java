package bg.duosoft.bpo.registers.utils.search.bridge;

import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeFromIndexedValueContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

import java.nio.charset.StandardCharsets;

/**
 * User: ggeorgiev
 * Date: 04.11.2024
 * Time: 11:43
 */
public class TrimLongSpecificationBridge implements ValueBridge<String, String> {
    private Integer MAX_BYTES_LENGTH = 32700;
    @Override
    public String toIndexedValue(String value, ValueBridgeToIndexedValueContext context) {
        if (value == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        String[] parts = value.split(";");
        for (String part : parts) {
            byte[] bytes = part.getBytes(StandardCharsets.UTF_8);
            if (bytes.length > MAX_BYTES_LENGTH) {
                // Truncate the string to the maximum byte length
                result.append(new String(bytes, 0, MAX_BYTES_LENGTH, StandardCharsets.UTF_8));
            } else {
                result.append(part);
            }
            result.append(";");
        }
        return result.substring(0, result.length() - 1);
    }

    @Override
    public String fromIndexedValue(String value, ValueBridgeFromIndexedValueContext context) {
        return value;
    }

}