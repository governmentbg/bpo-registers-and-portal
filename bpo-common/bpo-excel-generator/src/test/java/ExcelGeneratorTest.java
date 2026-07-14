import bg.duosoft.bpo.excelgenerator.ExcelGenerator;
import bg.duosoft.bpo.excelgenerator.data.BorderStyle;
import bg.duosoft.bpo.excelgenerator.data.CellConfiguration;
import bg.duosoft.bpo.excelgenerator.data.ExcelHeaderColumn;
import bg.duosoft.bpo.excelgenerator.data.ExcelSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * User: ggeorgiev
 * Date: 11.10.2024
 * Time: 16:04
 */

public class ExcelGeneratorTest {
    @Test
    @Ignore
    public void test5() throws IOException {
        ExcelGenerator generator = new ExcelGenerator();

        List<ExcelHeaderColumn> headers = new ArrayList<>();
        headers.add( ExcelHeaderColumn.builder()
                .width(20_000)
                .headerLabel( "test")
                .resultColIndex(0)
                .build());
        headers.add(ExcelHeaderColumn.builder().width(10_000).headerLabel( "test2").resultColIndex(2).dataCellConfiguration(CellConfiguration.builder().fontHeight(22).underline(true).build()).build());
        headers.add(ExcelHeaderColumn
                .builder()
                .width(10_000)
                .headerLabel( "datetime")
                .resultColIndex(3)
                .headerCellConfiguration(CellConfiguration.builder()
                        .bold(true)
                        .fontHeight(12)
                        .border(BorderStyle.DOUBLE)
                        .build())
                .dataCellConfiguration(CellConfiguration.builder()
                        .border(BorderStyle.HAIR)
                        .fontHeight(10)
                        .build())
                .build());
        headers.add(ExcelHeaderColumn.builder().width(10_000).headerLabel("date").resultColIndex(4).build());
        headers.add(ExcelHeaderColumn.builder().resultColIndex(5).headerLabel("date-time").uniqueStylePerCell(true).build());
        List<List<Object>> data = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().withNano(0);
        data.add(Arrays.asList("1", "2", 3, null, now.toLocalDate(), now.toLocalDate()));
        data.add(Arrays.asList("4", "5", 6, now, null, now));
        ExcelSheet sheet =
                ExcelSheet
                .builder()
                .label("test")
                .startCol(2)
                .startRow(3)
                .headers(headers)
                .data(data)
                .freezeHeaders(true)
                .hideGridlines(false)
                .dataCellConfiguration(CellConfiguration.builder()
                        .bold(true)
                        .italic(true)
                        .bottomBorder(BorderStyle.DASH_DOT)
                        .topBorder(BorderStyle.DASH_DOT)
                        .leftBorder(BorderStyle.DASH_DOT)
                        .rightBorder(BorderStyle.DASH_DOT)
                        .build())
                .headerCellConfiguration(CellConfiguration.builder()
                        .fontHeight(20)
                        .underline(true)
                        .bottomBorder(BorderStyle.DASH_DOT)
                        .topBorder(BorderStyle.DASH_DOT)
                        .leftBorder(BorderStyle.DASH_DOT)
                        .rightBorder(BorderStyle.DASH_DOT)
                        .build())
                .build();
        generator.addSheetData(sheet);
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            generator.generate(bos);
            Files.write(Paths.get("d:/tempp.xlsx"), bos.toByteArray());
            System.out.println("Done...");
        }

    }
    @Test
    public void testReportWithHeaders() throws IOException {
        ExcelGenerator generator = new ExcelGenerator();
        List<ExcelHeaderColumn> headers = new ArrayList<>();
        headers.add( ExcelHeaderColumn.builder().width(20_000).headerLabel( "test").resultColIndex(0).build());
        headers.add(ExcelHeaderColumn.builder().width(10_000).headerLabel( "test2").resultColIndex(2).build());
        headers.add(ExcelHeaderColumn.builder().width(10_000).headerLabel( "datetime").resultColIndex(3).build());
        headers.add(ExcelHeaderColumn.builder().width(10_000).headerLabel( "date").resultColIndex(4).build());
        List<List<Object>> data = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().withNano(0);
        data.add(Arrays.asList("1", "2", 3, now, now.toLocalDate()));
        data.add(Arrays.asList("4", "5", 6, now, now.toLocalDate()));
        ExcelSheet sheet = ExcelSheet
                .builder()
                .label("test")
                .startCol(2)
                .startRow(3)
                .headers(headers)
                .data(data)
                .build();
        generator.addSheetData(sheet);
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            generator.generate(bos);

            XSSFWorkbook wb = new XSSFWorkbook(new ByteArrayInputStream(bos.toByteArray()));
            Sheet wbSheet = wb.getSheet("test");

            Assert.assertEquals(20_000, wbSheet.getColumnWidth(2));
            Assert.assertEquals(10_000, wbSheet.getColumnWidth(3));

            Row header = wbSheet.getRow(3);
            Assert.assertEquals("test", header.getCell(2).getStringCellValue());
            Assert.assertEquals("test2", header.getCell(3).getStringCellValue());
            Assert.assertEquals("datetime", header.getCell(4).getStringCellValue());
            Assert.assertEquals("date", header.getCell(5).getStringCellValue());

            Row firstRow = wbSheet.getRow(4);
            Assert.assertEquals("1", firstRow.getCell(2).getStringCellValue());
            Assert.assertEquals(3, firstRow.getCell(3).getNumericCellValue(), 0.0001);
            Assert.assertEquals(now, firstRow.getCell(4).getLocalDateTimeCellValue());
            Assert.assertEquals(now.toLocalDate().atStartOfDay(), firstRow.getCell(5).getLocalDateTimeCellValue());

            Row secondRow = wbSheet.getRow(5);
            Assert.assertEquals("4", secondRow.getCell(2).getStringCellValue());
            Assert.assertEquals(6, secondRow.getCell(3).getNumericCellValue(), 0.0001);
            Assert.assertEquals(now, secondRow.getCell(4).getLocalDateTimeCellValue());
            Assert.assertEquals(now.toLocalDate().atStartOfDay(), secondRow.getCell(5).getLocalDateTimeCellValue());

        };
    }
    @Test
    public void testReportWithoutHeaders() throws IOException {
        ExcelGenerator generator = new ExcelGenerator();


        List<List<Object>> data = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().withNano(0);
        data.add(Arrays.asList("1", "2", 3, now, now.toLocalDate()));
        data.add(Arrays.asList("4", "5", 6, now, now.toLocalDate()));
        ExcelSheet sheet = ExcelSheet.builder().data(data).build();
        generator.addSheetData(sheet);
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            generator.generate(bos);

            XSSFWorkbook wb = new XSSFWorkbook(new ByteArrayInputStream(bos.toByteArray()));
            Sheet wbSheet = wb.getSheet("Sheet0");
            assertNotNull(wbSheet);
            Row row = wbSheet.getRow(0);
            Assert.assertEquals("1", row.getCell(0).getStringCellValue());
            Assert.assertEquals("2", row.getCell(1).getStringCellValue());
            Assert.assertEquals(3, row.getCell(2).getNumericCellValue(), 0.0001);
            Assert.assertEquals(now, row.getCell(3).getLocalDateTimeCellValue());
            Assert.assertEquals(now.toLocalDate().atStartOfDay(), row.getCell(4).getLocalDateTimeCellValue());

            row = wbSheet.getRow(1);
            Assert.assertEquals("4", row.getCell(0).getStringCellValue());
            Assert.assertEquals("5", row.getCell(1).getStringCellValue());
            Assert.assertEquals(6, row.getCell(2).getNumericCellValue(), 0.0001);
            Assert.assertEquals(now, row.getCell(3).getLocalDateTimeCellValue());
            Assert.assertEquals(now.toLocalDate().atStartOfDay(), row.getCell(4).getLocalDateTimeCellValue());

        }
    }
}
