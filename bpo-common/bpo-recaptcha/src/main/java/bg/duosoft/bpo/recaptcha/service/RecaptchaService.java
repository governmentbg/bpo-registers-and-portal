package bg.duosoft.bpo.recaptcha.service;

import bg.duosoft.bpo.recaptcha.config.BpoRecaptchaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaService {

    @Autowired
    private BpoRecaptchaConfig bpoRecaptchaConfig;


    public boolean verifyRecaptcha(String recaptchaToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?secret=%s&response=%s", bpoRecaptchaConfig.getVerifyUrl(), bpoRecaptchaConfig.getSecretKey(), recaptchaToken);
        GoogleRecaptchaResponse response = restTemplate.postForObject(url, null, GoogleRecaptchaResponse.class);
        return response != null && response.isSuccess();
    }

    private static class GoogleRecaptchaResponse {
        private boolean success;
        private String challenge_ts;
        private String hostname;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getChallenge_ts() {
            return challenge_ts;
        }

        public void setChallenge_ts(String challenge_ts) {
            this.challenge_ts = challenge_ts;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }
    }
}
