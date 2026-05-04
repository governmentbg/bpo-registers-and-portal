package bg.duosoft.bpo.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequestDTO {
    private Feedback feedback;
    private String recaptchaToken;
}
