package bg.duosoft.bpo.common.web.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * User: ggeorgiev
 * Date: 13.02.2024
 * Time: 14:27
 */
@JsonComponent
public class PageJsonSerializer<T> extends JsonSerializer<Page<T>> {
    @Override
    public void serialize(Page<T> page, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject(); // means start, like '{}'

        gen.writeObjectField("content", page.getContent());

        gen.writeBooleanField("empty", page.isEmpty());
        gen.writeBooleanField("first", page.isFirst());
        gen.writeBooleanField("last", page.isLast());
        gen.writeNumberField("number", page.getNumber());
        gen.writeNumberField("numberOfElements", page.getNumberOfElements());
        gen.writeNumberField("size", page.getSize());
        gen.writeNumberField("totalPages", page.getTotalPages());
        gen.writeNumberField("totalElements", page.getTotalElements());

        // mostly duplicate data (pageable.pageSize -> size, pageable.offset -> number, etc.)
        // need special care in `Spring Boot 3.2.0` if `pageable` object is `unpaged`; otherwise error
        if (page.getPageable().isUnpaged()) {
            gen.writeStringField("pageable", "INSTANCE");
        } else {
            gen.writeObjectField("pageable", page.getPageable());
        }

        gen.writeObjectField("sort", page.getSort());

        gen.writeEndObject();
    }
}
