package model.dao;

import java.sql.*;
import java.util.*;

import model.Club;

public class ClubDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ClubDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	/* ���ο� Club ���� */
	public Club create(Club club) throws SQLException {
		String sql = "INSERT INTO CLUB (club_no, deptartment_no, club_name, title, contents, createtime)"
				+ "VALUES (club_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
		Object[] param = new Object[] {club.getDepartment_no(), club.getClub_name(), club.getTitle(), club.getContents()};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"club_no"};		// PK �÷��� �̸�     
		
		try {
			jdbcUtil.executeUpdate(key);  // insert �� ����
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if (rs.next()) {
				int generatedKey = rs.getInt(1);
				club.setClub_no(generatedKey);
			}
			return club;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}
	
	/* �̰� generate key ���ϴ� create
	 public int create(Club club) throws SQLException {
		String sql = "INSERT INTO Club VALUES (?, ?, ?, ?, SYSDATE)";		
		Object[] param = new Object[] {club.getClub_no(), club.getClub_name(), 
					club.getTitle(), club.getContents(),
						(club.getClub_no()!=0) ? club.getClub_no() : null };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}
	 */
	
	
	/* ���� Club ���� */
	public int update(Club club) throws SQLException {
		String sql = "UPDATE CLUB "
					+ "SET deptartment_no=?, club_name=?, title=?, contents=?"
					+ "WHERE club_no=?";
		Object[] param = new Object[] {club.getDepartment_no(), club.getClub_name(), club.getTitle(), 
						club.getContents(), club.getClub_no()};
		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
		
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	/* ���� Club ���� */
	public int remove(int club_no) throws SQLException {
		String sql = "DELETE FROM CLUB WHERE club_no=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {club_no});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	/* DB�� ����� club ��ϵ��� �ҷ���  */
	public List<Club> clubList() throws SQLException {
        String sql = "SELECT club_no, club_name, dept_name"
        		   + "FROM CLUB c LEFT OUTER JOIN DEPARTMENT d ON c.department_no = d.department_no "
        		   + "ORDER BY club_no";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Club> clubList = new ArrayList<Club>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				Club club = new Club(			// Community ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("club_no"),
						rs.getString("club_name"),
						rs.getString("dept_name"));
				clubList.add(club);				// List�� Community ��ü ����
			}		
			return clubList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/* clubNo�� �ش� �Խñ��� �� ������ �ҷ���   Club ������ Ŭ������ �����ؼ� ��ȯ */
	public Club showDetail(int club_no) {
		String sql = "SELECT deptartment_no, club_no, club_name, title, contents, createtime FROM CLUB "
     				+ "WHERE clubNo = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {club_no});	// JDBCUtil�� query���� �Ű� ���� ����
		Club club = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if(rs.next()) {
				club = new Club(
						rs.getInt("deptartment_no"),
						club_no,
						rs.getString("club_name"),
						rs.getString("title"),
						rs.getString("contents"),
						rs.getDate("createtime"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return club;
	}
	
	
	/* userID�� �������  �а� ���� ��õ ���Ƹ��� �ҷ���    Club ������ Ŭ������ �����ؼ� ��ȯ */
	public Club showRecommend(int customer_no) {
		String sql = "SELECT club_name, title, contents, createtime "
					+ "FROM CLUB c LEFT OUTER JOIN CUSTOMER c1 ON c.deptartment_no = c1.deptartment_no "
     				+ "WHERE id = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {customer_no});	// JDBCUtil�� query���� �Ű� ���� ����
		Club club = null;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if(rs.next()) {
				club = new Club(
						rs.getInt("club_no"),
						rs.getInt("deptartment_no"),
						rs.getString("club_name"),
						rs.getString("title"),
						rs.getString("contents"),
						rs.getDate("createtime"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return club;
	}
	
	public boolean existingClub(int club_no) throws SQLException {
		String sql = "SELECT count(*) FROM CLUB WHERE club_no=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {club_no});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}
	
	public Club findClub(int club_no) throws SQLException {
        String sql = "SELECT dept_no, deptartment_no, club_name "
        			+ "FROM CLUB "
        			+ "WHERE club_no=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {club_no});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						
				Club club = new Club(		
					club_no,
					rs.getInt("deptartment_no"),
					rs.getString("club_name"),
					null,
					null,
					null);
				return club;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public List<Club> findClubList() throws SQLException {
		String sql = "SELECT club_no, deptartment_no, club_name"
     		   + "FROM CLUB "
     		   + "ORDER BY club_no";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Club> clubList = new ArrayList<Club>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				Club club = new Club(			// Community ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("club_no"),
						rs.getInt("deptartment_no"),
						rs.getString("club_name"),
						null,
						null,
						null);
				clubList.add(club);				// List�� Community ��ü ����
			}		
			return clubList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 */
	public List<Club> findClubList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT club_no, deptartment_no, club_name " 
        		   + "FROM CLUB "
        		   + "ORDER BY club_no";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<Club> clubList = new ArrayList<Club>();
				do {
					Club club = new Club(			// Community ��ü�� �����Ͽ� ���� ���� ������ ����
							rs.getInt("club_no"),
							rs.getInt("deptartment_no"),
							rs.getString("club_name"),
							null,
							null,
							null);
					clubList.add(club);								// ����Ʈ�� User ��ü ����
				} while ((rs.next()) && (--countPerPage > 0));		
				return clubList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	
	
	
}