package bg.duosoft.bpo.excelgenerator.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExcelHeaderColumn {
    private Integer width;
    private String headerLabel;
    /**
    index (starting from 0) of the data
     */
    private Integer resultColIndex;
    private Boolean uniqueStylePerCell;
    private CellConfiguration headerCellConfiguration;
    private CellConfiguration dataCellConfiguration;
}