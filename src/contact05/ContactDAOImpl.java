package contact05;

import java.io.*;
import java.util.ArrayList;

public class ContactDAOImpl implements ContactDAO {

    private static String className = ContactDAOImpl.class.getName();

    // 1. private static 자기 자신 타입 멤버 변수 선언
    private static ContactDAOImpl instance = null;

    // 2. private 생성자
    private ContactDAOImpl() {
        // 데이터가 변경된 사항을 저장하기 위해
        // ContactDAOImpl 생성자를 불러오면
        // 폴더 경로와 파일을 생성한다.
        initDataDir();
        initDataFile();
    }

    // 3. public static 메소드 - 인스턴스를 리턴하는 메소드
    public static ContactDAOImpl getInstance(){
        if(instance == null){
            instance = new ContactDAOImpl();
        }
        return instance;
    }

    // 멤버 변수 (필드)
    public ArrayList<ContactVO> list; // list를 사용하기 직전 인스턴스 생성

    //데이터를 저장할 폴더와 파일 이름 정의
    private static final String DATA_DIR ="data"; //상대 경로
    private static final String DATA_FILE = "contact.data";

    // 데이터 폴더와 파일을 사용하는 File 객체 선언
    private File dataDir;
    private File dataFile;

    public int getSize(){
        return list.size();
    }

    // 데이터 폴더가 있는지 검색하고 없으면 새로 생성
    private void initDataDir(){
        dataDir = new File(DATA_DIR); //data라는 파일이름
        System.out.println("폴더 경로 :" + dataDir.getPath());
        System.out.println("절대 경로 :" + dataDir.getAbsolutePath());

        if(!dataDir.exists()){ // 폴더가 없으면
            if(dataDir.mkdir()){
                System.out.println("<폴더 생성 성공>");
            }else{
                System.out.println("<폴더 생성 실패>");
            }
        }else{
            System.out.println("<폴더 이미 존재>");
        }
    }

    //데이터 파일이 있는지 없는지 검사하고, 없는 경우 ArrayList 생성
    //있는 경우 파일에서 데이터를 읽어서 ArrayList를 채움
    private void initDataFile(){
        String filePath = DATA_DIR + File.separator + DATA_FILE;
        dataFile = new File(filePath);
        System.out.println("파일 경로 :" + dataFile.getPath());
        System.out.println("절대 경로 :" +dataFile.getAbsolutePath());

        if(!dataFile.exists()){
            System.out.println("<새로운 데이터 파일 생성>");
            list = new ArrayList<ContactVO>();
        }else{
            System.out.println("<기존 데이터 있음>");
            readDataFromFile();
        }
    }

    private void readDataFromFile(){
        InputStream in = null;
        BufferedInputStream bin = null;
        ObjectInputStream oin = null;

        try{
            in = new FileInputStream("data/contact.data");
            in = new FileInputStream(dataFile);
            bin = new BufferedInputStream(in);
            oin = new ObjectInputStream(bin);

            list = (ArrayList<ContactVO>) oin.readObject();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                oin.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void writeDataToFile(){
        OutputStream out = null;
        BufferedOutputStream bout = null;
        ObjectOutputStream oout = null;

        try{
            out = new FileOutputStream(dataFile);
            bout = new BufferedOutputStream(out);
            oout = new ObjectOutputStream(bout);

            oout.writeObject(list);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                oout.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public int insertContact(ContactVO contactVO) {
        System.out.println(className + "- insert()");
        System.out.println("vo data -" + contactVO);
        list.add(contactVO);
        writeDataToFile();
        return 1;
    }

    @Override
    public ArrayList<ContactVO> selectAll() {
        return list;
    }

    @Override
    public ContactVO selectOne(int index) {
        if(index>=0 && index<list.size()) {
            return list.get(index);
        }else{
            return null;
        }
    }

    @Override
    public int updateContact(ContactVO contactVO, int index) {
        if(index>=0 && index<list.size()) {
            list.set(index, contactVO);
            writeDataToFile();
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int deleteContact(int index) {
       if(index>=0 && index<list.size()){
           list.remove(index);
           writeDataToFile();
           return 1;
       }else{
           return 0;
       }
    }

    @Override
    public int getListSize(){
        return list.size();
    }
}
