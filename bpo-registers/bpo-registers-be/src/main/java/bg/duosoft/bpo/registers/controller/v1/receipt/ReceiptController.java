package bg.duosoft.bpo.registers.controller.v1.receipt;

import bg.duosoft.bpo.receiptservice.service.ReceiptService;
import bg.duosoft.bpo.registers.dto.filter.*;
import bg.duosoft.bpo.registers.util.RegisterTypes;
import bg.duosoft.bpo.registers.util.receipt.ReceiptUtilitiesExcel;
import bg.duosoft.bpo.registers.util.receipt.ReceiptUtilitiesPdf;
import bg.duosoft.bpo.registers.util.swagger.Tags;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/api/v1/receipts")
@RequiredArgsConstructor
@Api(tags = Tags.RECEIPTS)
public class ReceiptController {

    private final ReceiptService receiptService;
    private final ReceiptUtilitiesPdf receiptUtilitiesPdf;
    private final ReceiptUtilitiesExcel receiptUtilitiesExcel;

    @GetMapping(value = "/{registerType}/view/{id}/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateViewReceipt(@PathVariable String registerType, @PathVariable String id, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateViewReceiptParams(registerType, id, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.MARK + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateMarkSearchReceipt(@RequestBody MarkFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.GEO_INDICATION + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateGeoSearchReceipt(@RequestBody GeoIndicationFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.PATENT + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generatePatentSearchReceipt(@RequestBody PatentFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.DESIGN + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateDesignSearchReceipt(@RequestBody DesignFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.EU_PATENT + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateEuPatentSearchReceipt(@RequestBody EuPatentFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.UTILITY_MODEL + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateUtilitySearchReceipt(@RequestBody UtilityModelFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.PLANT_BREED + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generatePlantBreedSearchReceipt(@RequestBody PlantBreedFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.SPC + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateSPCSearchReceipt(@RequestBody SpcFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.COMBINED + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateCombinedSearchReceipt(@RequestBody CommonIpObjectFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.AGENT + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateAgentSearchReceipt(@RequestBody AgentFilter filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateAgentSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @PostMapping(value = "/" + RegisterTypes.DECISIONS + "/search/{lang}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateDecisionSearchReceipt(@RequestBody LegalDecisionFilter filter, @PathVariable String lang) {
        ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf = receiptUtilitiesPdf.populateDecisionSearchReceiptParams(filter, lang);
        return getResponseEntityPdf(lang, receiptParamsPdf);
    }

    @NotNull
    private ResponseEntity<?> getResponseEntityPdf(@PathVariable String lang, ReceiptUtilitiesPdf.ReceiptParamsPdf receiptParamsPdf) {
        try {
            byte[] pdfBytes = receiptService.generateReceiptByteArray(receiptParamsPdf.getReceiptData(), receiptParamsPdf.getTemplateName(), Locale.forLanguageTag(lang));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", receiptParamsPdf.getFileName());
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = "Failed to generate receipt for " + receiptParamsPdf.getTemplateName() + ", " + receiptParamsPdf.getFileName();
            log.error(errMsg);
            return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/" + RegisterTypes.MARK + "/search/{lang}/excel")
    public ResponseEntity<?> generateMarkSearchReceiptExcel(@RequestBody MarkFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateSearchReceiptParams(filter, lang, RegisterTypes.MARK);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.GEO_INDICATION + "/search/{lang}/excel")
    public ResponseEntity<?> generateGeoSearchReceiptExcel(@RequestBody GeoIndicationFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateSearchReceiptParams(filter, lang, RegisterTypes.GEO_INDICATION);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.PATENT + "/search/{lang}/excel")
    public ResponseEntity<?> generatePatentSearchReceiptExcel(@RequestBody PatentFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateSearchReceiptParams(filter, lang, RegisterTypes.PATENT);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.DESIGN + "/search/{lang}/excel")
    public ResponseEntity<?> generateDesignSearchReceiptExcel(@RequestBody DesignFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateSearchReceiptParams(filter, lang, RegisterTypes.DESIGN);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.EU_PATENT + "/search/{lang}/excel")
    public ResponseEntity<?> generateEuPatentSearchReceiptExcel(@RequestBody EuPatentFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateSearchReceiptParams(filter, lang, RegisterTypes.EU_PATENT);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.UTILITY_MODEL + "/search/{lang}/excel")
    public ResponseEntity<?> generateUtilitySearchReceiptExcel(@RequestBody UtilityModelFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateSearchReceiptParams(filter, lang, RegisterTypes.UTILITY_MODEL);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.PLANT_BREED + "/search/{lang}/excel")
    public ResponseEntity<?> generatePlantBreedSearchReceiptExcel(@RequestBody PlantBreedFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateSearchReceiptParams(filter, lang, RegisterTypes.PLANT_BREED);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.SPC + "/search/{lang}/excel")
    public ResponseEntity<?> generateSpcSearchReceiptExcel(@RequestBody SpcFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateSearchReceiptParams(filter, lang, RegisterTypes.SPC);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.COMBINED + "/search/{lang}/excel")
    public ResponseEntity<?> generateCombinedSearchReceiptExcel(@RequestBody CommonIpObjectFilterDetailsDTO filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateSearchReceiptParams(filter, lang, RegisterTypes.COMBINED);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.AGENT + "/search/{lang}/excel")
    public ResponseEntity<?> generateAgentSearchReceiptExcel(@RequestBody AgentFilter filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateAgentSearchReceiptParams(filter, lang);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @PostMapping(value = "/" + RegisterTypes.DECISIONS + "/search/{lang}/excel")
    public ResponseEntity<?> generateDecisionSearchReceiptExcel(@RequestBody LegalDecisionFilter filter, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populateDecisionSearchReceiptParams(filter, lang);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    @GetMapping(value = "/person-objects/{lang}/excel")
    public ResponseEntity<?> generatePersonObjectsReceiptExcel(@RequestParam String personName, @PathVariable String lang) {
        ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel = receiptUtilitiesExcel.populatePersonToObjectsReceiptParams(lang, personName);
        return getResponseEntityExcel(receiptParamsExcel, Locale.of(lang));
    }

    private ResponseEntity<?> getResponseEntityExcel(ReceiptUtilitiesExcel.ReceiptParamsExcel receiptParamsExcel, Locale locale) {
        try {
            byte[] bytes = receiptUtilitiesExcel.getReceiptBytes(receiptParamsExcel, locale);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("filename", receiptParamsExcel.getFileName());
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = "Failed to generate receipt for " + receiptParamsExcel.getFileName();
            log.error(errMsg);
            return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

