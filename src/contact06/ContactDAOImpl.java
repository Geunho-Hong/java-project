package contact06;

import oracle.jdbc.OracleDriver;

import java.sql.*;
import java.util.ArrayList;

public class ContactDAOImpl implements ContactDAO{

    private static String className = ContactDAOImpl.class.getName();

    // JDBC 연동
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl"; // 접속할 오라클 DB 경로
    public static final String USER = "scott";
    public static final String PASSWORD = "tiger";

    public static final String TABLE_NAME = "ex_contact";
    public static final String COL_CID = "cid";
    public static final String COL_NAME ="name";
    public static final String COL_PHONE ="phone";
    public static final String COL_EMAIL = "email";

    public static Connection conn;
    public static Statement stmt;
    public static PreparedStatement pstmt;
    public static ResultSet rs;


    // 1. private static 자기 자신 타입 멤버 변수 선언
    private static ContactDAOImpl instance = null;

    // 2. private 생성자
    private ContactDAOImpl() {
        // DB 드라이버 등록(메모리 로드)
        try{
            conn = null;
            stmt = null;
            pstmt = null;

            DriverManager.registerDriver(new OracleDriver());
            System.out.println("Driver Load Success!");
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("DB Connection Success!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // 3. public static 메소드 - 인스턴스를 리턴하는 메소드
    public static ContactDAOImpl getInstance(){
        if(instance == null){
            instance = new ContactDAOImpl();
        }
        return instance;
    }

    @Override
    public int insertContact(ContactVO contactVO) {
        // TODO : db에 데이터 저장 (vo)
        int result = 0;

        try {
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String sql_insert = "insert into " + TABLE_NAME
                    + " values (contact_pk.nextval,?,?,?)";
            pstmt = conn.prepareStatement(sql_insert);
            pstmt.setString(1,contactVO.getName());
            pstmt.setString(2,contactVO.getPhoneNumber());
            pstmt.setString(3,contactVO.getEmail());
            pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                pstmt.close();
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return 1;
    }

    @Override
    public ContactVO selectOne(int index) {
        // TODO : index 번호로 데이터 검색하여 결과 리턴
        ContactVO contactVO = null;
        try{
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String selectOne = "select " + COL_NAME + "," + COL_PHONE
                    +","+COL_EMAIL+ " from " + TABLE_NAME + " where " +" = ?";
            pstmt = conn.prepareStatement(selectOne);

            pstmt.setInt(1,index);
            // 조회한 인덱스를 rs에 담음
            rs = pstmt.executeQuery();
            if(rs.next()){
                String name = rs.getString(COL_NAME); //name hong
                String phone = rs.getString(COL_PHONE); //phone  8159
                String email = rs.getString(COL_EMAIL); //email n1
                contactVO = new ContactVO(name,phone,email);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                pstmt.close();
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return contactVO;
    }

    @Override
    public ArrayList<ContactVO> selectAll() {
        // TODO : DB에서 연락처 전체 가져오기
        ArrayList<ContactVO> list = new ArrayList<ContactVO>();
        try{
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String selectAll = "select * from " + TABLE_NAME;
            pstmt = conn.prepareStatement(selectAll);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString(COL_NAME);
                String phone = rs.getString(COL_PHONE);
                String email = rs.getString(COL_EMAIL);
                ContactVO contactVO = new ContactVO(name,phone,email);
                list.add(contactVO);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                pstmt.close();
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }


    @Override
    public int updateContact(ContactVO contactVO, int index) {
        // TODO : index 번호로 검색한 위치에 데이터 업데이트
        int result = 0;
        try{
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String sql_update = "update " + TABLE_NAME + " set "
                    + COL_NAME + " = ?, " + COL_PHONE + " = ?, " + COL_EMAIL + " = ? "+
                    " where " + COL_CID + " = ?";
            pstmt = conn.prepareStatement(sql_update);
            pstmt.setString(1,contactVO.getName());
            pstmt.setString(2,contactVO.getPhoneNumber());
            pstmt.setString(3,contactVO.getEmail());
            pstmt.setInt(4,index);
            pstmt.executeQuery();


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                pstmt.close();
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        result = 1;
        return result;
    }

    @Override
    public int deleteContact(int index) {
       // TODO : index로 데이터 삭제
        int result = 0;
        try{
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String sql_delete = "delete from " + TABLE_NAME + " where " + COL_CID + " = ? ";
            pstmt = conn.prepareStatement(sql_delete);
            pstmt.setInt(1,index);
            pstmt.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                pstmt.close();
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        result = 1;
        return result;
    }

}
