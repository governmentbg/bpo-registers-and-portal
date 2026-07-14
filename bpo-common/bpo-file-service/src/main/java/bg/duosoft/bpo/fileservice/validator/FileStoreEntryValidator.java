package bg.duosoft.bpo.fileservice.validator;



import bg.duosoft.bpo.common.service.validator.DefaultValidator;
import bg.duosoft.bpo.common.service.validator.Validator;
import bg.duosoft.bpo.common.util.mimetype.MimeTypeUtils;
import bg.duosoft.bpo.common.util.validation.ValidationError;
import bg.duosoft.bpo.fileservice.property.FileConfig;
import bg.duosoft.bpo.fileservice.dto.FileStoreEntryBaseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 08.06.2022
 * Time: 17:07
 */
@Slf4j
@Component
public class FileStoreEntryValidator extends DefaultValidator<FileStoreEntryBaseDTO> {

    @Autowired
    private Map<String, FileConfig> fileGroupConfig;

    @Override
    public void validate(List<ValidationError> validationErrors, FileStoreEntryBaseDTO fileStoreEntry, Object... args) {
        FileConfig fileConfig = fileGroupConfig.get((String)args[0]);
        String pointer =  (String)args[1];

        if(fileConfig == null){
            log.error("Bad file group "+args[0]);
            throw new RuntimeException("Bad file group "+args[0]);
        }

        rejectIfEmptyString(validationErrors, fileStoreEntry.getBucket(), pointer, "validation.bad.fileStoreEntry.rootDirectory");
        rejectIfEmptyString(validationErrors, fileStoreEntry.getRelativePath(), pointer, "validation.bad.fileStoreEntry.relativePath");
        rejectIfEmptyString(validationErrors, fileStoreEntry.getContentType(), pointer, "validation.bad.fileStoreEntry.contentType");

        if(fileConfig.getMaxSize() > -1 && fileConfig.getMaxSize() < fileStoreEntry.getContent().length){
            validationErrors.add(ValidationError.builder().pointer(pointer).message("validation.file.size").build());
        }
        String guessedMimeFromBytes = MimeTypeUtils.guessMimeFromBytes(fileStoreEntry.getContent(), fileStoreEntry.getFileName());
        if(StringUtils.hasText(fileConfig.getAllowedTypes()) && !fileConfig.getAllowedTypes().contains(guessedMimeFromBytes)){
            validationErrors.add(ValidationError.builder().pointer(pointer).message("validation.file.type").build());
        }
        if(!MimeTypeUtils.areMimesEquivalent(fileStoreEntry.getContentType(), guessedMimeFromBytes)){
            validationErrors.add(ValidationError.builder().pointer(pointer).message("validation.file.type.mismatch").build());
        }
    }
}
