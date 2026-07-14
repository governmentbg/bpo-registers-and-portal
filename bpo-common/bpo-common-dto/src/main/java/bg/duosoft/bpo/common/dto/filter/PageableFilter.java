package bg.duosoft.bpo.common.dto.filter;

public interface PageableFilter {
    Integer DEFAULT_PAGE = 0;
    Integer DEFAULT_PAGE_SIZE = 10;
    Integer PAGE_SIZE_100 = 100;

    Integer getPage();

    Integer getPageSize();

}
