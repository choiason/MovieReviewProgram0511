package vo;

public class selectReviewVO {
	
	int    reviewIdx; //�����ε���
	int    moviewIdx; //��ȭ�ε���
	String movieTitle;//��ȭ����
	String userId;    //������
	String reviewText;
	String reviewDate;
	
	public selectReviewVO() {
	
	}
	
	public selectReviewVO(String uId) {
		this.userId = uId;
	}
	
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public int getReviewIdx() {
		return reviewIdx;
	}
	public void setReviewIdx(int reviewIdx) {
		this.reviewIdx = reviewIdx;
	}
	public int getMoviewIdx() {
		return moviewIdx;
	}
	public void setMoviewIdx(int moviewIdx) {
		this.moviewIdx = moviewIdx;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}