package bg.duosoft.bpo.receiptservice.service;

import java.util.Locale;
import java.util.Map;

public interface ReceiptService {
    byte[] generateReceiptByteArray(Map<String, Object> receiptData, String templateName, Locale locale) throws Exception;
}
