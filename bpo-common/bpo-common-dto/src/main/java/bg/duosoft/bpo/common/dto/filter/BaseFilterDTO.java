package bg.duosoft.bpo.common.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseFilterDTO implements SortableFilter, PageableFilter {
    private String order;
    private String orderBy;
    private Integer page = this.DEFAULT_PAGE;
    private Integer pageSize = this.DEFAULT_PAGE_SIZE;
}