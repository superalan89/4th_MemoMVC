package memo;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ModelWithDB {
	
	private final String URL ="jdbc:mysql://localhost:3306/memo";
	private final String ID = "root";
	private final String PW = "mysql";
	
	Connection con = null;
	
	// ������
	public ModelWithDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver"); // ����̹��� �������� �ε�
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	// ����
	public void create(Memo memo){
		// 1. �����ͺ��̽� ����
		
		
		// Ư�� ��ǻ�͸� ã������ �ּ� ü��
		// ������ = 213.12.123.132
		// url = naver.com
		// Ư�� ���α׷��� �Ҵ�Ǵ� ����
		// port = 1 ~ 6������..
		//        2000���� ���� �̹� ǥ������ ���ǰ� �ִ�.
		
		// ����
		// ������ + ��Ʈ
		
		
		// ǥ�� ��������
		//http:// ������(�ּ�) : ��Ʈ(80)
		
		// Ư�� ���α׷��� �׼��� �ϱ����� �ּ�ü��
		//���������̸�://������(�ּ�) : ��Ʈ
		
		try (Connection con = DriverManager.getConnection(URL, ID, PW);) {
			// 2. ������ ����
			// 2.1 ���� ����
			String query = " insert into memo(name,content,datetime) values(?,?,?)";
			// 2.2 ������ ���� ������ ���·� ������ش�
			PreparedStatement pstmt = con.prepareStatement(query);
			// 2.3 ����ǥ�� ���� ����
			pstmt.setString(1, memo.name);
			pstmt.setString(2, memo.content);
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			// 2.4 ������ ����
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}// 3. �����ͺ��̽� ��������
	}
	// �б�
	public Memo read(int no){
		Memo memo = new Memo();
		
		// 1. �����ͺ��̽� ����
		try (Connection con = DriverManager.getConnection(URL, ID, PW);) {
			// 2. ������ ����
			// 2.1 ���� ����
			String query = "select * from memo where no = "+no;
			// 2.2  ������ ������ ���� ������ ���·� ������ش�
			Statement stmt = con.createStatement();
			// 2.3 select�� ������� �����ޱ� ���� ������ ����
			ResultSet rs = stmt.executeQuery(query);
			// ������� �ݺ��ϸ鼭 �ϳ��� ���� �� �ִ�
			if(rs.next()){
				memo.no = rs.getInt("no");
				memo.name = rs.getString("name");
				memo.content = rs.getString("content");
				memo.datetime = rs.getLong("datetime");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}// 3. �����ͺ��̽� ��������
		
		return memo;
	}
	// ����
	public void update(Memo memo){
		
	}
	// ����
	public void delete(int no){

	}
	// ���
	public ArrayList<Memo> getList(){
		ArrayList<Memo> list = new ArrayList<>();
		
		// 1. �����ͺ��̽� ����
		try (Connection con = DriverManager.getConnection(URL, ID, PW);) {
			// 2. ������ ����
			// 2.1 ���� ����
			String query = "select * from memo";
			// 2.2  ������ ������ ���� ������ ���·� ������ش�
			Statement stmt = con.createStatement();
			// 2.3 select�� ������� �����ޱ� ���� ������ ����
			ResultSet rs = stmt.executeQuery(query);
			// ������� �ݺ��ϸ鼭 �ϳ��� ���� �� �ִ�
			while(rs.next()){
				Memo memo = new Memo();
				memo.no = rs.getInt("no");
				memo.name = rs.getString("name");
				memo.content = rs.getString("content");
				memo.datetime = rs.getLong("datetime");
				list.add(memo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}// 3. �����ͺ��̽� ��������
		
		return list;
	}
}