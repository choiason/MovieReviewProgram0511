package MovieDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import vo.ReviewVO;
import vo.selectAllVO;
import vo.selectReviewVO;

public class ReviewDao {

	static ReviewDao single = null; // �̱��ϰ�ü����

	public static ReviewDao getInstance() {
		if (single == null) {
			single = new ReviewDao();
		}
		return single;
	}

	private ReviewDao() {
		// �ܺο��� �����Ұ� (�̱���)
	}
	
	//TODO : �������(�ֱԹ�)
	public int updateReview(ReviewVO vo) { 
		Connection connection = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "update review set reviewText=? where reviewIdx = ?";

		try {
			connection = DBConnection.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getReviewIdx());
			pstmt.setString(2, vo.getReviewText());
						
			rs = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}//updateReivew() end
	
	
	//TODO : �������(�ֱԹ�)
	public int DeleteReview(ReviewVO vo) { 
		Connection        connection = null;
		PreparedStatement pstmt      = null;
		int rs = 0;
		String sql = "delete from review where reviewIdx = ? ";

		try {
			connection = DBConnection.getInstance().getConnection();
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getReviewIdx());
			
			rs = pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}//deleteReview() end
	
	
	//TODO : �����ۼ�(�����)
	public int insert_review(ReviewVO vo) {
			
		int res = 0;
		
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into review values(seq_reviewIdx.nextVal, ?, ?, to_char(sysdate, 'YYYY-MM-DD'), ?)";
		
		try {
			conn  = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getMovieIdx());
			pstmt.setString(2, vo.getId());
			//pstmt.setString(3, vo.getReviewDate());
			pstmt.setString(3, vo.getReviewText());
			
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close(); 
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}//insert_review() end
	
	
	//TODO : ������ȸ ��ȭ���� �߰� (�����)
	public List<selectReviewVO> select_Review_List() {

		List<selectReviewVO> list = new ArrayList<selectReviewVO>();

		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		
		String sql = "select * from selectReview order by reviewIdx desc";

		try {
			conn  = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();

			while (rs.next()) {
				
				int    reviewIdx  = rs.getInt("reviewIdx");
				int    movieIdx   = rs.getInt("movieIdx");
				String movieTitle = rs.getString("movieTitle");
				String id         = rs.getString("id");
				String reviewText = rs.getString("reviewText");
				String reviewDate = rs.getString("reviewDate");
				
				selectReviewVO vo = new selectReviewVO();

				vo.setReviewIdx(reviewIdx);
				vo.setMoviewIdx(movieIdx);
				vo.setMovieTitle(movieTitle);
				vo.setUserId(id);
				vo.setReviewText(reviewText);
				vo.setReviewDate(reviewDate);
				
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)    rs.close(); 
				if (pstmt != null) pstmt.close(); 
				if (conn != null)  conn.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}//select_Review_List() end

	
	
	//TODO : �� ���亸���ֱ�(�����)
	public List<selectReviewVO> showMyReview(selectReviewVO vo) {

		List<selectReviewVO> list = new ArrayList<selectReviewVO>();
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "select * from selectReview where id = ?";
		
		try {
			conn  = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getUserId());
			
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int    reviewIdx  = rs.getInt("reviewIdx");
				int    movieIdx   = rs.getInt("movieIdx");
				String movieTitle = rs.getString("movieTitle");
				String reviewText = rs.getString("reviewText");
				String reviewDate = rs.getString("reviewDate");
				
				vo.setReviewIdx(reviewIdx);
				vo.setMoviewIdx(movieIdx);
				vo.setMovieTitle(movieTitle);
				vo.setReviewText(reviewText);
				vo.setReviewDate(reviewDate);
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)    rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}//showMyReview() end

	
	
}	 