package bg.duosoft.bpo.registers.utils.search.bridge;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;
import org.springframework.util.ObjectUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: ggeorgiev
 * Date: 04.09.2024
 * Time: 16:54
 */
@Slf4j
public class RegistrationNumberBridge implements ValueBridge<String, Integer> {
    private static final Pattern REG_NUM_PATTERN = Pattern.compile("^[0]*(\\d+)[\\w-]*$");

    @Override
    public Integer toIndexedValue(String value, ValueBridgeToIndexedValueContext context) {
        if (ObjectUtils.isEmpty(value) ) {
            return null;
        }
        Matcher matcher = REG_NUM_PATTERN.matcher(value);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e) {
                log.warn("Cannot parse value {}", value, e);
                return null;
            }
        } else {
            return null;
        }

    }
}
