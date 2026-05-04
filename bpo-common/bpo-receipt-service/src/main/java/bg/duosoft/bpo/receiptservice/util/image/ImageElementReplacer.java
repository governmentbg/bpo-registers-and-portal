package bg.duosoft.bpo.receiptservice.util.image;

import com.lowagie.text.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

import java.awt.image.BufferedImage;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class ImageElementReplacer implements ReplacedElementFactory {

    private final ReplacedElementFactory superFactory;

    private final ImageProvider imageProvider;

    private Image getImage(Element element) {
        String bucket = element.getAttribute("data-bucket");
        String fullPath = element.getAttribute("data-fullPath");

        BufferedImage bufferedImage = imageProvider.getImage(bucket, fullPath);

        if (Objects.nonNull(bufferedImage)) {
            try {
                return Image.getInstance(bufferedImage, null);

            } catch (Exception e) {
                log.error("SEVERE: Problem replacing image from the Minio content repository: bucket="
                        + bucket + "; fullPath=" + fullPath + "; " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public ReplacedElement createReplacedElement(LayoutContext layoutContext, BlockBox blockBox,
                                                 UserAgentCallback userAgentCallback, int cssWidth, int cssHeight) {
        Element element = blockBox.getElement();
        if (Objects.isNull(element)) {
            return null;
        }
        if (isImageElement(element)) {
            Image image = getImage(element);
            return new ITextImageElement(scaleImage(image, cssWidth, cssHeight));
        }
        // for other elements use the default factory...
        return this.superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight);
    }

    @Override
    public void reset() {
        this.superFactory.reset();
    }

    @Override
    public void remove(Element e) {
        this.superFactory.remove(e);
    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener listener) {
        this.superFactory.setFormSubmissionListener(listener);
    }

    public static boolean isImageElement(Element element) {
        return (Objects.nonNull(element) && "img".equals(element.getNodeName()) && element.hasAttribute("data-bucket") && element.hasAttribute("data-fullPath"));
    }

    public static FSImage scaleImage(Image image, int cssWidth, int cssHeight) {
        final FSImage fsImage = new ITextFSImage(image);

        if ((cssWidth != -1) || (cssHeight != -1)) {
            fsImage.scale(cssWidth, cssHeight);
        }

        return fsImage;
    }

}