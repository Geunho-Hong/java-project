package libraryProgram.query;

// JDBC���� ���� ��� �� SQL�� ����
public interface DeskQuery {
	// ��� ����
	public static final String TABLE_DESK = "DESK";
	
	// TABLE DESK
	public static final String DESK_NUM = "NUM";
	public static final String DESK_VACANT = "VACANT";
	public static final String DESK_OCCUPIEDID = "OCCUPIED";
	public static final String DESK_STARTAT = "STARTAT";
		
	// DESK DML
	public static final String SQL_SELECT_DESK =
			"SELECT * FROM " + TABLE_DESK + " WHERE " + DESK_NUM + " = ?";
	
	public static final String SQL_SELECT_ALL_DESK =
			"SELECT * FROM " + TABLE_DESK;
	
	public static final String SQL_UPDATE_DESK = 
			"UPDATE " + TABLE_DESK + 
			" SET " + DESK_VACANT + " = ?, "
					+ DESK_OCCUPIEDID + " = ?, " 
					+ DESK_STARTAT + " = ?" +
			" WHERE " + DESK_NUM + " = ?";

}