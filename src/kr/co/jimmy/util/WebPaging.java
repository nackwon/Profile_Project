package kr.co.jimmy.util;
		
public class WebPaging {
		
	private int listcount;
	private int startPage;
	private int endPage;
	private int page;
	private int totalPage;	
	
	public void Paging(int totalCount) {
		
		int[] PagingInfo;
		listcount = 5;
		totalPage = totalCount / listcount;
		
		if (totalPage % listcount > 0) {
			totalPage++;
		} 
		
		if (totalPage < page) {
			page = totalPage;
		}
		
		startPage = ((page - 1) / listcount) * listcount + 1;
		endPage = startPage + listcount - 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}	
}		
