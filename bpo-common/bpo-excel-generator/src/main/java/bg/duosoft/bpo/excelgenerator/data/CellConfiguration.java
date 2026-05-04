package bg.duosoft.bpo.excelgenerator.data;

import lombok.Builder;
import lombok.Getter;

/**
 * User: ggeorgiev
 * Date: 28.10.2024
 * Time: 14:09
 */
@Builder
@Getter
public class CellConfiguration {
    private Boolean bold;
    private Boolean italic;
    private Boolean underline;
    private Integer fontHeight;
    private Boolean wordWrap;
    private BorderStyle topBorder;
    private BorderStyle leftBorder;
    private BorderStyle rightBorder;
    private BorderStyle bottomBorder;
    public static class CellConfigurationBuilder {
        public CellConfigurationBuilder border(BorderStyle border) {
            this.bottomBorder = border;
            this.topBorder = border;
            this.leftBorder = border;
            this.rightBorder = border;
            return this;
        }
    }
}
