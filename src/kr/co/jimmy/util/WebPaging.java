package kr.co.jimmy.util;

public class WebPaging {
	
	final int listCount = 5;

	public int StartListBoard(int defaultPage) {
		int startListBoard = 0;

		startListBoard = ((defaultPage - 1) / listCount) * listCount + 1;

		return startListBoard;
	}

	public int endListBoard(int startListBoard, int totalPage) {
		int endListBoard = 0;

		endListBoard = startListBoard * listCount - 1;

		if(endListBoard > totalPage)
			endListBoard = totalPage;
		
		return endListBoard;
	}
	
	public int totalPage(int totalCount, int defaultPage) {
		int totalPage = 0;
		
		totalPage = totalCount / listCount;
		
		if(totalCount % listCount > 0)
			totalPage ++;
		
		if(totalPage < defaultPage)
			defaultPage = totalPage;
		
		return totalPage;
	}
}
