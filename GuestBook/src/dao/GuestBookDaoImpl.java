package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vo.GuestBookVo;

public class GuestBookDaoImpl implements GuestBookDao {

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "C##KHOH", "1234");
		} catch(ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패");
			e.printStackTrace();
		}
		
		return conn;		
	}
	
	
	@Override
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT no, name, password, content, reg_date " 
						+ "FROM guestbook ORDER BY reg_date DESC";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);	
				String content = rs.getString(4);
				Date regDate = rs.getDate(5);	//java.util.Date
				GuestBookVo vo = new GuestBookVo(no, name, password, content, regDate);
				// 리스트에 추가
				list.add(vo);
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;		
		
	}
	
	@Override
	public int insert(GuestBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO guestbook" +
						"(no, name, password, content, reg_date) " +
						"VALUES(seq_guestbook_no.NEXTVAL, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			
			insertedCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return insertedCount;
	}

	@Override
	public int delete(String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM guestbook " +
					"WHERE lower(password) = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			
			// Query execute
			deletedCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return deletedCount;
	}
}
