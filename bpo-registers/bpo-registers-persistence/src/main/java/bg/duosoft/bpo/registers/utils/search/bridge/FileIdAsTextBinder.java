package bg.duosoft.bpo.registers.utils.search.bridge;

import org.hibernate.search.mapper.pojo.bridge.binding.ValueBindingContext;
import org.hibernate.search.mapper.pojo.bridge.mapping.programmatic.ValueBinder;

/**
 * User: ggeorgiev
 * Date: 12.02.2024
 * Time: 13:34
 */
public class FileIdAsTextBinder implements ValueBinder {


    @Override
    public void bind(ValueBindingContext<?> context) {
        context.bridge(String.class, FileIdAsTextBridge.getInstance());
    }
}
