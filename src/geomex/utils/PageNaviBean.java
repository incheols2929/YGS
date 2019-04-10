package geomex.utils;

public class PageNaviBean {

    public static PageNavi getPageInfo(int page_num, int item_total)
    {
        return getPageInfo(page_num, item_total, 20, 10);
    }

    public static PageNavi getPageInfo(int page_num, int item_total, int item_list_count, int page_count)
    {
        PageNavi pn = new PageNavi();

        pn.start = 0;
        pn.end = 0;
        pn.pageSize = item_list_count;
        pn.pageBlock = page_count;
        pn.pageCount = 0;
        pn.startPage = 0;
        pn.endPage = 0;
        pn.number = 0;
        pn.count = 0;
        pn.currentPage = 0;
        pn.pageNum = page_num;

        pn.currentPage = pn.pageNum;
        pn.start = (pn.currentPage - 1) * pn.pageSize + 1; // 블럭당 보이는 글의 수 시작값
        pn.end = pn.start + pn.pageSize - 1; //블럭당 보이는 글의수 끝값
        pn.count = item_total;
        pn.number = pn.count - (pn.currentPage - 1) * pn.pageSize; //인덱스 값 구하기

        /*
         * pn.itemStartNum = 0; pn.itemListCount = item_list_count; pn.itemTotal
         * = item_total; pn.pageNum = page_num; pn.pageTotalCount = 0;
         * pn.pageCount = page_count; pn.itemStartNum =
         * (pn.pageNum-1)*pn.itemListCount; pn.pageTotalCount =
         * pn.itemTotal/pn.itemListCount
         * -(((pn.itemTotal%pn.itemListCount)!=0)?-1:0); pn.pageStartNum =
         * (pn.pageNum-1) * (pn.pageCount) + 1; pn.pageEndNum = pn.pageStartNum
         * + pn.pageCount - 1; if(pn.pageEndNum >= pn.pageTotalCount)
         * pn.pageEndNum = pn.pageTotalCount; pn.nextPage = pn.pageEndNum +
         * ((pn.pageEndNum==pn.pageTotalCount)?0:1); if(pn.nextPage >=
         * pn.pageTotalCount) pn.nextPage = pn.pageTotalCount; pn.prePage =
         * pn.pageStartNum - 1; if(pn.prePage == 0) pn.prePage = 1;
         */

        return pn;
    }
}
