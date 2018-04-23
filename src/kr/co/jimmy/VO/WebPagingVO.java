package kr.co.jimmy.VO;

public class WebPagingVO {

	private int listcount;
	private int startPage;
	private int endPage;
	private int page;
	private int totalPage;

	public int getListcount() {
		return listcount;
	}

	public void setListcount(int listcount) {
		this.listcount = listcount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
