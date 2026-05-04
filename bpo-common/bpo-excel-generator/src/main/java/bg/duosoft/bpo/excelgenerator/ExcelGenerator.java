package bg.duosoft.bpo.excelgenerator;

import bg.duosoft.bpo.excelgenerator.data.CellConfiguration;
import bg.duosoft.bpo.excelgenerator.data.ExcelSheet;
import bg.duosoft.bpo.excelgenerator.data.ExcelHeaderColumn;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * User: ggeorgiev
 * Date: 11.10.2024
 * Time: 15:00
 */
public class ExcelGenerator {
    private Workbook workbook;
    private boolean xls;

    public ExcelGenerator() {
        xls = false;
    }

    public ExcelGenerator(boolean xls) {
        this.xls = xls;
    }

    public void addSheetData(ExcelSheet sheet) {
        initWorkbookIfNotInitialized();


        Sheet results = createSheet(sheet);
        int currentRow = sheet.getStartRow();
        int startCol = sheet.getStartCol();

        if (sheet.getHeaders() != null) {
            Row headerRow = results.createRow(currentRow);
            int currentCol = startCol;
            for (ExcelHeaderColumn header : sheet.getHeaders()) {
                header = getHeader(sheet, header.getResultColIndex()).orElseThrow();
                CellStyle headerStyle = createCellStyle(workbook, header.getHeaderCellConfiguration());
                Cell cell = headerRow.createCell(currentCol);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(header.getHeaderLabel());
                if (header.getWidth() != null && header.getWidth() > 0) {
                    results.setColumnWidth(currentCol, header.getWidth());
                }
                currentCol++;
            }
            currentRow++;
        }
        Map<Integer, CellStyle> cellStylePerColumnMap = new HashMap<>();
        for (List<Object> rowData : sheet.getData()) {
            Row row = results.createRow(currentRow);
            int currentColNumber = startCol;
            if (!CollectionUtils.isEmpty(rowData)) {
                for (int col = 0; col < rowData.size(); col++) {
                    Optional<ExcelHeaderColumn> header = getHeader(sheet, col);
                    if (header.isEmpty()) {
                        continue;
                    }
                    Object data = rowData.get(col);
                    Cell cell = row.createCell(currentColNumber++);
                    CellStyle cellStyle;
                    if (Objects.equals(true, header.get().getUniqueStylePerCell())) {
                        cellStyle = createCellStyle(workbook, header.get().getDataCellConfiguration());
                    } else {
                        cellStylePerColumnMap.computeIfAbsent(col, k -> createCellStyle(workbook, header.get().getDataCellConfiguration()));
                        cellStyle = cellStylePerColumnMap.get(col);
                    }
                    cell.setCellStyle(cellStyle);
                    setCellValue(data, cell);
                }
            }
            currentRow++;

        }
    }

    private void setCellValue(Object data, Cell cell) {
        if (Objects.isNull(data)) {
            cell.setCellValue("");
        } else if (data instanceof String stringVal) {
            cell.setCellValue(stringVal);
        } else if (data instanceof Integer integerVal) {
            cell.setCellValue(integerVal);
        } else if (data instanceof Double doubleVal) {
            cell.setCellValue(doubleVal);
        } else if (data instanceof LocalDateTime localDateTimeVal) {
            cell.setCellValue(localDateTimeVal);
            setDateFormat(workbook, cell, "dd.mm.yyyy hh:mm:ss");
        } else if (data instanceof LocalDate localDateVal) {
            cell.setCellValue(localDateVal);
            setDateFormat(workbook, cell, "dd.mm.yyyy");
        } else if (data instanceof BigDecimal val) {
            cell.setCellValue(val.doubleValue());
        } else if (data instanceof byte[] byteArr) {
            addImageToCell(workbook, byteArr, cell);
        } else {
            throw new IllegalArgumentException("Unrecognized value type found during excel generation in column " + data);
        }
    }

    public void generate(OutputStream os) throws IOException {
        if (workbook != null) {
            workbook.write(os);
        }
    }

    private static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    private void setDateFormat(Workbook wb, Cell cell, String formatStr) {
        CreationHelper createHelper = wb.getCreationHelper();
        short format = createHelper.createDataFormat().getFormat(formatStr);
        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle == null) {
            cellStyle = wb.createCellStyle();
            cell.setCellStyle(cellStyle);
        }
        cellStyle.setDataFormat(format);
    }

    /**
     * if there is some property (for an example fontHeight border and so on)in the header, it's used, otherwise the sheet's property is used
     *
     * @param sheet
     * @param header
     * @return
     */

    private ExcelHeaderColumn recreateHeader(ExcelSheet sheet, ExcelHeaderColumn header) {
        return ExcelHeaderColumn.builder()
                .width(header.getWidth())
                .headerLabel(header.getHeaderLabel())
                .resultColIndex(header.getResultColIndex())
                .uniqueStylePerCell(header.getUniqueStylePerCell() == null ?  sheet.getUniqueStylePerCell() : header.getUniqueStylePerCell())
                .headerCellConfiguration(
                        CellConfiguration.builder()
                                .bold(getCellConfigurationValue(sheet.getHeaderCellConfiguration(), header.getHeaderCellConfiguration(), CellConfiguration::getBold))
                                .italic(getCellConfigurationValue(sheet.getHeaderCellConfiguration(), header.getHeaderCellConfiguration(), CellConfiguration::getItalic))
                                .underline(getCellConfigurationValue(sheet.getHeaderCellConfiguration(), header.getHeaderCellConfiguration(), CellConfiguration::getUnderline))
                                .wordWrap(getCellConfigurationValue(sheet.getHeaderCellConfiguration(), header.getHeaderCellConfiguration(), CellConfiguration::getWordWrap, true))
                                .rightBorder(getCellConfigurationValue(sheet.getHeaderCellConfiguration(), header.getHeaderCellConfiguration(), CellConfiguration::getRightBorder))
                                .leftBorder(getCellConfigurationValue(sheet.getHeaderCellConfiguration(), header.getHeaderCellConfiguration(), CellConfiguration::getLeftBorder))
                                .topBorder(getCellConfigurationValue(sheet.getHeaderCellConfiguration(), header.getHeaderCellConfiguration(), CellConfiguration::getTopBorder))
                                .bottomBorder(getCellConfigurationValue(sheet.getHeaderCellConfiguration(), header.getHeaderCellConfiguration(), CellConfiguration::getBottomBorder))
                                .fontHeight(getCellConfigurationValue(sheet.getHeaderCellConfiguration(), header.getHeaderCellConfiguration(), CellConfiguration::getFontHeight, 12))
                                .build()
                )
                .dataCellConfiguration(CellConfiguration.builder()
                        .bold(getCellConfigurationValue(sheet.getDataCellConfiguration(), header.getDataCellConfiguration(), CellConfiguration::getBold))
                        .italic(getCellConfigurationValue(sheet.getDataCellConfiguration(), header.getDataCellConfiguration(), CellConfiguration::getItalic))
                        .underline(getCellConfigurationValue(sheet.getDataCellConfiguration(), header.getDataCellConfiguration(), CellConfiguration::getUnderline))
                        .wordWrap(getCellConfigurationValue(sheet.getDataCellConfiguration(), header.getDataCellConfiguration(), CellConfiguration::getWordWrap))
                        .rightBorder(getCellConfigurationValue(sheet.getDataCellConfiguration(), header.getDataCellConfiguration(), CellConfiguration::getRightBorder))
                        .leftBorder(getCellConfigurationValue(sheet.getDataCellConfiguration(), header.getDataCellConfiguration(), CellConfiguration::getLeftBorder))
                        .topBorder(getCellConfigurationValue(sheet.getDataCellConfiguration(), header.getDataCellConfiguration(), CellConfiguration::getTopBorder))
                        .bottomBorder(getCellConfigurationValue(sheet.getDataCellConfiguration(), header.getDataCellConfiguration(), CellConfiguration::getBottomBorder))
                        .fontHeight(getCellConfigurationValue(sheet.getDataCellConfiguration(), header.getDataCellConfiguration(), CellConfiguration::getFontHeight))

                        .build())
                .build();
    }

    private <T> T getCellConfigurationValue(CellConfiguration sheetCellConfig, CellConfiguration cellCellConfig,
                                            Function<CellConfiguration, T> function) {
        return getCellConfigurationValue(sheetCellConfig, cellCellConfig, function, null);
    }

    private <T> T getCellConfigurationValue(CellConfiguration sheetCellConfig, CellConfiguration cellCellConfig,
                                            Function<CellConfiguration, T> function, T defaultValue) {
        T res = cellCellConfig == null || function.apply(cellCellConfig) == null ? (sheetCellConfig == null ? null : function.apply(sheetCellConfig)) : function.apply(cellCellConfig);
        if (res == null) {
            return defaultValue;
        }
        return res == null ? defaultValue : res;
    }


    private Optional<ExcelHeaderColumn> getHeader(ExcelSheet sheet, int resultColIndex) {
        //if there are no headers configured, default header is getting created
        ExcelHeaderColumn res = CollectionUtils.isEmpty(sheet.getHeaders()) ? ExcelHeaderColumn.builder().resultColIndex(resultColIndex).build() : sheet.getHeaders().stream().filter(r -> resultColIndex == r.getResultColIndex()).findFirst().orElse(null);
        if (res == null) {
            return Optional.empty();
        }
        return Optional.of(recreateHeader(sheet, res));

    }

    private Workbook initWorkbookIfNotInitialized() {
        if (workbook == null) {
            if (xls) {
                workbook = new HSSFWorkbook();
            } else {
                workbook = new XSSFWorkbook();
            }
        }
        return workbook;
    }

    private CellStyle createCellStyle(Workbook workbook, CellConfiguration configuration) {

        CellStyle style = workbook.createCellStyle();
        if (configuration != null) {
            Font font = workbook.createFont();
            style.setWrapText(Objects.equals(true, configuration.getWordWrap()));
            if (configuration.getFontHeight() != null && configuration.getFontHeight() > 0) {
                font.setFontHeightInPoints(configuration.getFontHeight().shortValue());
            }
            font.setItalic(Objects.equals(configuration.getItalic(), true));
            font.setBold(Objects.equals(configuration.getBold(), true));
            font.setUnderline(Objects.equals(configuration.getUnderline(), true) ? (byte) 1 : (byte) 0);


            setBorder(style::setBorderTop, configuration.getTopBorder());
            setBorder(style::setBorderBottom, configuration.getBottomBorder());
            setBorder(style::setBorderLeft, configuration.getLeftBorder());
            setBorder(style::setBorderRight, configuration.getRightBorder());
            style.setFont(font);
        }
        return style;
    }


    private void setBorder(Consumer<BorderStyle> borderStyleSetter,
                           bg.duosoft.bpo.excelgenerator.data.BorderStyle bs) {
        if (bs != null) {
            borderStyleSetter.accept(BorderStyle.valueOf(bs.code()));
        }
    }
    
    private static void addImageToCell(Workbook workbook, byte[] imageBytes, Cell cell) {
        var sheet = cell.getSheet();
        var drawing = sheet.createDrawingPatriarch();
        int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);

        int col = cell.getColumnIndex();
        int row = cell.getRowIndex();

        // Create an anchor to position the image at the given cell
        ClientAnchor anchor = new XSSFClientAnchor();
        anchor.setCol1(col);
        anchor.setRow1(row);
        anchor.setCol2(col + 1);
        anchor.setRow2(row + 1);
        anchor.setDx1(Units.EMU_PER_PIXEL); // Add padding to prevent border overlap
        anchor.setDy1(Units.EMU_PER_PIXEL);
        anchor.setDx2(-Units.EMU_PER_PIXEL);
        anchor.setDy2(-Units.EMU_PER_PIXEL);
        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_DONT_RESIZE);

        var picture = drawing.createPicture(anchor, pictureIdx);
        double imageWidthInPixels = picture.getImageDimension().getWidth();
        double imageHeightInPixels = picture.getImageDimension().getHeight();
        double columnWidthInPixels = sheet.getColumnWidthInPixels(col);

        double scalingFactor = columnWidthInPixels / imageWidthInPixels;
        double targetRowHeightInPoints = (float) (imageHeightInPixels * scalingFactor * 0.75); // Convert to points for row height

        sheet.getRow(row).setHeightInPoints((float) targetRowHeightInPoints);
    }

    private Sheet createSheet(ExcelSheet sheet) {

        Sheet excelSheet = isEmpty(sheet.getLabel()) ? this.workbook.createSheet() : this.workbook.createSheet(sheet.getLabel());
        if (Objects.equals(true, sheet.getFreezeHeaders())) {
            excelSheet.createFreezePane(0, sheet.getStartRow() + 1);
        }
        if (Objects.equals(true, sheet.getHideGridlines())) {
            excelSheet.setDisplayGridlines(false);
        }

        return excelSheet;
    }

}
