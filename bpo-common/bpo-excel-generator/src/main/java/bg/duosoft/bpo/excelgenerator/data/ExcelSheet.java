package bg.duosoft.bpo.excelgenerator.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * User: ggeorgiev
 * Date: 11.10.2024
 * Time: 15:09
 */
@Builder
@Getter
public class ExcelSheet {
    private int startRow;
    private int startCol;
    private String label;
    private List<ExcelHeaderColumn> headers;
    private List<List<Object>> data;
    private Boolean freezeHeaders;
    private Boolean hideGridlines;
    private Boolean uniqueStylePerCell;

    private CellConfiguration headerCellConfiguration;
    private CellConfiguration dataCellConfiguration;

}
