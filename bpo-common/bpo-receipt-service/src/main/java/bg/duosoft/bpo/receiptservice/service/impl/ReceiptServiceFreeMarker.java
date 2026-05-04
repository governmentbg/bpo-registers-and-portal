package bg.duosoft.bpo.receiptservice.service.impl;

import bg.duosoft.bpo.receiptservice.service.ReceiptService;
import bg.duosoft.bpo.receiptservice.util.ResourceBundleMethod;
import bg.duosoft.bpo.receiptservice.util.image.ImageElementReplacer;
import bg.duosoft.bpo.receiptservice.util.image.ImageProvider;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class ReceiptServiceFreeMarker implements ReceiptService {

    private final Configuration freemarkerConfig;
    private final ResourceLoader resourceLoader;
    private final ImageProvider imageProvider;

    public byte[] generateReceiptByteArray(Map<String, Object> receiptData, String templateName, Locale locale) throws Exception {
        Template template = freemarkerConfig.getTemplate(templateName, locale);
        StringWriter stringWriter = new StringWriter();

        receiptData.put("messages", getAllMessagesAsProperties(locale));
        receiptData.put("resourceBundle", new ResourceBundleMethod());

        template.process(receiptData, stringWriter);
        String htmlContent = stringWriter.toString();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.getSharedContext().setReplacedElementFactory(new ImageElementReplacer(new ITextRenderer().getSharedContext().getReplacedElementFactory(), imageProvider));
//            renderer.getFontResolver().addFont("fonts/NotoSans-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //for Cyrillic, Greek, Latin, etc.
            renderer.getFontResolver().addFont("fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new Exception("Error generating PDF", e);
        }
    }

    private Properties getAllMessagesAsProperties(Locale locale) {
        Properties properties = new Properties();
        String basename = "classpath:messages";

        String localeSuffix = locale.getLanguage();
        String filename = basename + (localeSuffix.isEmpty() ? "" : "_" + localeSuffix) + ".properties";

        try (InputStream inputStream = resourceLoader.getResource(filename).getInputStream()) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
