package bg.duosoft.bpo.portal.controller;

import bg.duosoft.bpo.portal.dto.FeedbackRequestDTO;
import bg.duosoft.bpo.portal.service.FeedbackService;
import bg.duosoft.bpo.recaptcha.annotation.ValidateRecaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping()
    @ValidateRecaptcha
    public void addFeedback(@RequestBody FeedbackRequestDTO feedback) {
        this.feedbackService.saveFeedback(feedback.getFeedback());
    }

}
