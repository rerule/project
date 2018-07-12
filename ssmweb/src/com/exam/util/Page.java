package com.exam.util;
//只需要设置总行数完成初始化
public class Page {
	//当前页
	
	private int currentPage=1;
	
	
	//总页数
	private int totalPages;
	//总行数
	private int totalRows;
	//每页行数
	private int pageRows=2;
	//上一页
	private int prePage;
	//下一页
	private int nextPage;
	
	public Page(){
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		totalPages=totalRows%getPageRows()==0?totalRows/getPageRows():totalRows/getPageRows()+1;
		setTotalPages(totalPages);
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getPrePage() {
		if (currentPage>1) {
			prePage=currentPage-1;
		}else {
			prePage=currentPage;
		}
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		
		if (currentPage<totalPages) {
			
			nextPage=currentPage+1;
		}else {
			nextPage=currentPage;
		}
		System.out.println(nextPage);
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	
}
