/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.model;

/**
 *
 * @author POOL_LAPTOP
 */
public class Pagination {

    private int countRecordAll;
    private int pageCurrent;
    private int paginLimit = 4;
    private int recordCurrent;
    private int recordLimit;
    private int pages;
    private int pageEnd;
    private int pageBegin;

    public Pagination(int countRecordAll,int limit, int offset) {
        this.countRecordAll = countRecordAll;
        this.pages = countRecordAll / limit;
        this.pageCurrent = (offset / limit);        
        this.recordCurrent = offset;
        this.recordLimit = limit;
        this.pageBegin = ((pageCurrent - this.paginLimit) < 0 ? 0 : (pageCurrent - this.paginLimit));
        this.pageEnd = this.paginLimit + pageCurrent;
    }
    
    public Pagination(int countRecordAll,int limit, int offset,int paginLimit) {
        this.countRecordAll = countRecordAll;
        this.pages = countRecordAll / limit;
        this.pageCurrent = (offset / limit);
        this.paginLimit = (paginLimit/2);
        this.recordCurrent = offset;
        this.recordLimit = limit;
        this.pageBegin = ((pageCurrent - this.paginLimit) < 0 ? 0 : (pageCurrent - this.paginLimit));
        this.pageEnd = this.paginLimit + pageCurrent;
    }

    public int getCountRecordAll() {
        return countRecordAll;
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public int getPaginLimit() {
        return paginLimit;
    }

    public int getRecordCurrent() {
        return recordCurrent;
    }

    public int getRecordLimit() {
        return recordLimit;
    }

    public int getPages() {
        return pages;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public int getPageBegin() {
        return pageBegin;
    }
    

}
