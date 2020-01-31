package contact05;

import java.util.ArrayList;

public interface ContactDAO {

    // 연락처 생성
    public abstract int insertContact(ContactVO contactVO);

    //연락처 수정
    public abstract int updateContact(ContactVO contactVO, int index);

    //연락처 모두 출력
    public abstract ArrayList<ContactVO> selectAll();

    //연락처 출력
    public abstract ContactVO selectOne(int index);

    //연락처 삭제
    public abstract int deleteContact(int index);

    //연락처 길이
    public abstract int getListSize();
}
