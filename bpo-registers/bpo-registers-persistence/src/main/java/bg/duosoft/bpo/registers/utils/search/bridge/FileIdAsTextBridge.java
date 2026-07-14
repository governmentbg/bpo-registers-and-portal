package bg.duosoft.bpo.registers.utils.search.bridge;


import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeFromIndexedValueContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

public class FileIdAsTextBridge implements ValueBridge<String, String> {
    private final static String DELIM = "/";
    private static final FileIdAsTextBridge INSTANCE = new FileIdAsTextBridge();
    private FileIdAsTextBridge() {

    }
    public static FileIdAsTextBridge getInstance() {
        return INSTANCE;
    }

    @Override
    public String toIndexedValue(String s, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        String[] split = s.split(DELIM);
        if (split.length != 4) {
            throw new RuntimeException("index doesn't contain correct pk");
        }
        return split[3];
    }

    @Override
    public String fromIndexedValue(String value, ValueBridgeFromIndexedValueContext context) {
        return value;
    }
}
