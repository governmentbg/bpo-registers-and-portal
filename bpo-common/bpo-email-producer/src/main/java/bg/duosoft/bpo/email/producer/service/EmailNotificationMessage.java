package bg.duosoft.bpo.email.producer.service;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailNotificationMessage implements Serializable {
    private String type;

    private String template;
    private Map<String, String> templateParams;
    private List<String> recipients;
    private List<String> cc;
    private List<String> bcc;
    private List<Integer> attachmentIds;
    private String message;
    private boolean isHtml;
    private String subject;
    private UUID uuid;
}
