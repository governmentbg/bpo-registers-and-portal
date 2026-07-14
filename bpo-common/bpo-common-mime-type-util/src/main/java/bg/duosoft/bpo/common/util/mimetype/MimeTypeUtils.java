package bg.duosoft.bpo.common.util.mimetype;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 09.06.2022
 * Time: 10:40
 */
@Slf4j
public class MimeTypeUtils {

    private final static Set<Set<String>> equivalenceGroups = new HashSet<Set<String>>();
    public static final List<String> imageMimeTypes = Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/webp",
            "image/bmp",
            "image/tiff",
            "image/svg+xml",
            "image/ico",
            "image/heif",
            "image/heic"
    );


    static {
        Set<String> mp3 = new HashSet<>();
        mp3.add("audio/mp3");
        mp3.add("audio/mpeg");
        Set<String> xml = new HashSet<>();
        xml.add("application/xml");
        xml.add("text/xml");
        equivalenceGroups.add(mp3);
        equivalenceGroups.add(xml);
    }

    public static String getFileExtension(byte[] bytes) {
        try {
            TikaConfig tikaConfig = new TikaConfig();
            MediaType mediatype = tikaConfig.getDetector().detect(new ByteArrayInputStream(bytes), new Metadata());
            MimeType mt = MimeTypes.getDefaultMimeTypes().forName(mediatype.toString());
            return mt.getExtension().replace(".", "");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String guessMimeFromBytes(byte[] bytes, String fileName) {
        if (bytes != null) {
            String mimeType = "";
            TikaConfig config = TikaConfig.getDefaultConfig();
            org.apache.tika.mime.MimeTypes repo = config.getMimeRepository();

            Metadata metadata = new Metadata();
            metadata.add(TikaCoreProperties.RESOURCE_NAME_KEY, fileName);
            try (InputStream bai = new ByteArrayInputStream(bytes)) {
                mimeType = repo.detect(bai, metadata).toString();
            } catch (IOException e) {
                log.error("Error guessing mime from byte[]", e);
            }
            log.trace("Guessed mime: " + mimeType);
            return mimeType;
        }
        return "";
    }

    public static String guessMimeFromFilename(String filename) {
        Tika tika = new Tika();
        String mime = tika.detect(filename);
        return mime;
    }

    public static boolean areMimesEquivalent(String mimetype1, String mimetype2) {
        if (mimetype1.equalsIgnoreCase(mimetype2)) {
            return true;
        } else {
            for (Set<String> group : equivalenceGroups) {
                if (group.contains(mimetype1) && group.contains(mimetype2)) {
                    return true;
                }
            }
            return false;
        }
    }
}
