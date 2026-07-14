package bg.duosoft.bpo.common.service.validator;


import bg.duosoft.bpo.common.util.mimetype.MimeTypeUtils;

import java.util.List;

public class AttachmentValidator {

    public static boolean validate(List<String> mimeTypes, byte[] bytes, String fileName) {
        String mimeType = MimeTypeUtils.guessMimeFromBytes(bytes, fileName);
        return mimeTypes.contains(mimeType);
    }
}
