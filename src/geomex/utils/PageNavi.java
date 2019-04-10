package geomex.utils;

public class PageNavi {

    /*
     * public int itemStartNum = 0; public int itemListCount = 0; public int
     * itemTotal = 0; public int pageNum = 0; public int pageTotalCount = 0;
     * public int pageCount = 0; public int pageStartNum = 0; public int
     * pageEndNum = 0; public int nextPage = 0; public int prePage = 0;
     */

    public int start = 0;
    public int end = 0;
    public int pageSize = 0; //리스트에 보여질 글의 수
    public int pageBlock = 0; //한페지에 보여질 블럭의 수
    public int pageCount = 0; //전체 블럭의 수 계산
    public int startPage = 0; //시작블럭
    public int endPage = 0; //끝블럭
    public int number = 0; // 인덱스계산을 위한 변수
    public int count = 0; //db에 있는 전체 데이터의 수
    public int currentPage = 0;
    public int pageNum = 0;

}
