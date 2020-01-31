package contact05;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.handler.MessageContext.Scope;
import javax.swing.JTable;
import javax.swing.JList;

public class ContactGui {

	private JFrame frame;
	private JLabel lblNewLabel, lblName, lblPhone, lblEmail;
	private JButton btnInput, btnUpdate, btnDelete, btnAllSearch, btnSearch;
	private JTextArea txtName, txtPhone, txtEmail, txtIndex;
	private JTextArea log, search;
	private static ContactDAO contactDAO;

	/**
	 * 
	 * Swing Table을 사용하기 위한 멤버 변수 선언
	 */
	private JTable table;
	private String[] colNames = {"NO","이름","연락처","이메일"};
	private Object[] records = new Object[colNames.length];
	private DefaultTableModel tableModel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactGui window = new ContactGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContactGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Font font1 = new Font("굴림", Font.BOLD, 35);
		Font btnFont = new Font("굴림", Font.PLAIN, 25);
		contactDAO = ContactDAOImpl.getInstance();

		lblNewLabel = new JLabel("연락처 Version 0.5");
		lblNewLabel.setFont(font1);
		lblNewLabel.setBounds(12, 10, 410, 47);
		frame.getContentPane().add(lblNewLabel);

		lblName = new JLabel("이름");
		lblName.setFont(font1);
		lblName.setBounds(12, 70, 410, 47);
		frame.getContentPane().add(lblName);

		txtName = new JTextArea();
		txtName.setBounds(180, 70, 300, 47);
		frame.getContentPane().add(txtName);

		lblPhone = new JLabel("전화번호");
		lblPhone.setFont(font1);
		lblPhone.setBounds(12, 130, 410, 47);
		frame.getContentPane().add(lblPhone);

		txtPhone = new JTextArea();
		txtPhone.setBounds(180, 130, 300, 47);
		frame.getContentPane().add(txtPhone);

		lblEmail = new JLabel("이메일");
		lblEmail.setFont(font1);
		lblEmail.setBounds(12, 200, 410, 47);
		frame.getContentPane().add(lblEmail);

		txtEmail = new JTextArea();
		txtEmail.setBounds(180, 200, 300, 47);
		frame.getContentPane().add(txtEmail);
	
		btnInput = new JButton("등록");
		btnInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertContact();
			}
		});
	

		btnInput.setFont(btnFont);
		btnInput.setBounds(12, 270, 100, 50);
		frame.getContentPane().add(btnInput);

		btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectContactByIndex();

			}
		});
		
		btnSearch.setFont(btnFont);
		btnSearch.setBounds(120, 270, 100, 50);
		frame.getContentPane().add(btnSearch);

		txtIndex = new JTextArea("번호 입력");
		txtIndex.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearTextField();
			}
		});
		txtIndex.setBounds(240, 270, 100, 50);
		txtIndex.setFont(new Font("굴림", Font.ITALIC, 22));
		frame.getContentPane().add(txtIndex);

		btnAllSearch = new JButton("전체 검색");
		btnAllSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectAllContacts();

			}
		});
		
		btnAllSearch.setFont(btnFont);
		btnAllSearch.setBounds(230, 330, 150, 50);
		frame.getContentPane().add(btnAllSearch);

		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteContact();
			}
		});
		btnDelete.setFont(btnFont);
		btnDelete.setBounds(120, 330, 100, 50);
		frame.getContentPane().add(btnDelete);

		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateContact();
			}
		});
		btnUpdate.setFont(btnFont);
		btnUpdate.setBounds(12, 330, 100, 50);
		frame.getContentPane().add(btnUpdate);

		search = new JTextArea("검색 및 전체 검색 내용 출력");
		search.setFont(new Font("굴림", Font.ITALIC, 22));
		search.setBounds(12, 400, 600, 150);
		frame.getContentPane().add(search);

		log = new JTextArea("로그 및 변경사항 출력");
		log.setFont(new Font("굴림", Font.ITALIC, 22));
		log.setBounds(12, 580, 600, 150);
		frame.getContentPane().add(log);
		
		//scrollPane -> 을 setViewPortView 로 만들기 
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 580, 600, 150);
		frame.add(scrollPane_1);
		scrollPane_1.setViewportView(log);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 400, 600, 150);
		frame.add(scrollPane_2);
		scrollPane_2.setViewportView(search);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(650,281,248,450);
		frame.add(scrollPane_3);
		
		// 테이블 기본 초기화
		tableModel = new DefaultTableModel(colNames,0);
		table = new JTable(tableModel);
		table.setFont(new Font("굴림",Font.PLAIN,15));
		scrollPane_3.setViewportView(table);
		

		
	}
	
	private void insertContact() {
		String name = txtName.getText();
		String phone = txtPhone.getText();
		String email = txtEmail.getText();
		
		if(name.equals("")) {
			log.setText("[연락처 저장] : 이름을 입력해 주세요");
			return;
		}
		
		ContactVO contactVO = new ContactVO(name, phone, email);
		log.setText(contactVO.toString());
		int result = contactDAO.insertContact(contactVO);
		
		if(result>0) {
			System.out.println("연락처 등록 성공!");
		}else {
			System.out.println("연락처 등록 실패!");
		}
	}
	
	private void clearTextField() {
		txtName.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
	}
	
	private void selectContactByIndex() {
		try {
			int index = Integer.parseInt(txtIndex.getText());
			ContactVO contactVO = contactDAO.selectOne(index);
			log.setText(contactVO.toString());
		}catch(NullPointerException e) {
			log.setText("연락처가 존재하지 않습니다 ! ");
		}catch(NumberFormatException e) {
			log.setText("인덱스를 양수로 입력하세요");
		}
	}
	
	private void updateContact() {
		String name = txtName.getText();
		String phone = txtPhone.getText();
		String email = txtEmail.getText();
		int index = 0;
		
		try {
			index = Integer.parseInt(txtIndex.getText());
		}catch (NumberFormatException e) {
			System.out.println("인덱스는 양수를 입력하세요 !");
		}
		
		if(name.equals("")) {
			log.setText("[연락처 저장] : 이름을 입력해 주세요");
			return;
		}
		
		ContactVO contactVO = new ContactVO(name,phone,email);
		int result = contactDAO.updateContact(contactVO, index);
		if(result > 0) {
			log.setText("연락처 수정 성공 !");
			clearTextField();
		}else {
			log.setText("연락처 is Empty!");
		}
		
	}
	
	private void deleteContact() {
		int index = Integer.parseInt(txtIndex.getText());
		int result = contactDAO.deleteContact(index);
		if(result > 0 ) {
			log.setText("연락처 삭제 성공!");
		}else {
			log.setText(index + " 번 연락처가 존재하지 않습니다");
		}
	}
	
	private void selectAllContacts() {
		tableModel.setNumRows(0);
		ArrayList<ContactVO> contactList = contactDAO.selectAll();
		if(contactList.size() == 0) {
			log.setText("연락처 is Empty");
			return;
		}
		StringBuilder sb = new StringBuilder();
		int cnt =0;
		for(ContactVO contactVO : contactList) {
			sb.append(contactVO.toString());
			cnt++;
			records[0] = cnt;
			records[1] = contactVO.getName();
			records[2] = contactVO.getPhoneNumber();
			records[3] = contactVO.getEmail();
			tableModel.addRow(records);
		}
		search.setText(sb.toString());
	}
	
	
}
