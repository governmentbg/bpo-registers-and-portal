package bg.duosoft.bpo.common.web.config.jackson;

import bg.duosoft.bpo.common.web.deserializer.EmptyStringDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Configuration
public class JacksonObjectMapperConfig {

    private static final String dateFormat = "dd.MM.yyyy";
    private static final String dateTimeFormat = "dd.MM.yyyy HH:mm:ss";
    private static final String isoDateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss";

    private static final Pattern dateTimePattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}:\\d{2}");
    private static final Pattern isoDateTimePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);

            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));

            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
            builder.deserializers(new MultiFormatLocalDateTimeDeserializer());
        };
    }

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder mapperBuilder) {
        ObjectMapper objectMapper = mapperBuilder.build();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new EmptyStringDeserializer());
        objectMapper.registerModule(module);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

    public static class MultiFormatLocalDateTimeDeserializer extends LocalDateTimeDeserializer {

        public MultiFormatLocalDateTimeDeserializer() {
            super(DateTimeFormatter.ofPattern(isoDateTimeFormat));
        }

        @Override
        public LocalDateTime deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws java.io.IOException {
            String date = p.getText();

            if (date == null || date.trim().isEmpty()) {
                return null;
            }

            date = date.trim();

            if (isoDateTimePattern.matcher(date).matches()) {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(isoDateTimeFormat));
            } else if (dateTimePattern.matcher(date).matches()) {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateTimeFormat));
            } else {
                throw new java.io.IOException("Unrecognized date format: " + date);
            }
        }
    }
}
