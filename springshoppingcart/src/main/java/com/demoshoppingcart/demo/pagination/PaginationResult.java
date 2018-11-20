package com.demoshoppingcart.demo.pagination;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;

public class PaginationResult<E> {

	private int totalRecords;
	private int currentPage;
	private List<E> lists;
	private int maxResult;
	private int totalPages;
	
	private int maxNaginationPage;
	
	private List<Integer> navigationPages;
	
	public PaginationResult(Query<E> query,int page,int maxResult,int maxNavigationPage) {
		
		final int pageIndex = page - 1 < 0 ? 0 : page - 1;
		
		int formRecordIndex = pageIndex* maxResult;
		int maxRecordIndex = formRecordIndex + maxResult;
		
//		ScrollableResults scrollableResults = query.scroll(ScrollMode.SCROLL_INSENSITIVE);
		ScrollableResults resultScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE);
		List<E> results = new ArrayList<>();
		boolean hasResult = resultScroll.first();
		
		if(hasResult) {
			// cuong den  vi tri
//			hasResult = scrollableResults.scroll(formRecordIndex);
			hasResult = resultScroll.scroll(formRecordIndex);
			if(hasResult) {
				do {
//					E record = (E) scrollableResults.get(0);
//					@SuppressWarnings("unchecked")
					E record =  (E) resultScroll.get();
					results.add(record);
					
				} while (resultScroll.next()&&
						resultScroll.getRowNumber() >=formRecordIndex &&
								resultScroll.getRowNumber() < maxRecordIndex);
			}
//			chuyen den ban ghi cuoi
			resultScroll.last();
		}
//		tong so trang ghi
		this.totalRecords = resultScroll.getRowNumber() + 1;
		this.currentPage = pageIndex + 1;
		this.lists = results;
		this.maxResult = maxResult;
		
		if(this.totalRecords % this.maxResult == 0) {
			this.totalPages = this.totalRecords / this.maxResult;
		}else {
			this.totalPages = (this.totalRecords / this.maxResult) + 1;
		}
		this.calcNavigationPages();
	}
	
	public void calcNavigationPages() {
		this.navigationPages = new ArrayList<Integer>();
		
		int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage; 
		int begin = current - this.maxNaginationPage/2;
		int end = current + this.maxNaginationPage/2;
	
		navigationPages.add(1);
		if(begin > 2) {
			navigationPages.add(-1);
		}
		for(int i = begin; i < end ; i++) {
			if(i>1 && i<this.totalPages) {
				navigationPages.add(i);
			}
		}
		if(end <this.totalPages - 2) {
				navigationPages.add(-1);
		}
		navigationPages.add(this.totalPages);
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	

	public int getCurrentPage() {
		return currentPage;
	}

	

	public List<E> getLists() {
		return lists;
	}


	public int getMaxResult() {
		return maxResult;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}

}
