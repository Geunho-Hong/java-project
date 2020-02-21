package libraryProgram;

import libraryProgram.dao.DeskDAO;
import libraryProgram.dao.DeskDAOImpl;
import libraryProgram.dao.UserDAO;
import libraryProgram.dao.UserDAOImpl;
import libraryProgram.dto.DeskDTO;
import libraryProgram.dto.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class SeatBtn extends JButton{
	
	private UserDAO udao;
	private DeskDAO ddao;
	
	private Color gray = new Color(240, 240, 240);
	private Color darkGray = new Color(190, 190, 190);
	
	private int startPointX = 10;
	private int startPointY = 10;
	private int defaultWidth = 100;
	private int defaultHeight = 100;
	
	private int gapX = 175;
	private int gapY = 250;
	
	public void SetSeats(JPanel pnl, int seatNumber) {
		
		udao = UserDAOImpl.getInstance();
		ddao = DeskDAOImpl.getInstance();
		
		for(int i = 0; i < seatNumber; i++) {
			
			int x;
			int y;

			String btnName;
			String floor = pnl.getToolTipText();
			
			int rowIncrement = i % 5;
			int columnIncrement = i / 5;
			System.out.println("row : " + rowIncrement + " column : " + columnIncrement);
			
			x = startPointX + gapX * rowIncrement;
			y = startPointY + gapY * columnIncrement;
			
			if(i < 9) {
				btnName = floor + "0" + Integer.toString(i+1);				
			} else {
				btnName = floor + Integer.toString(i+1);
			}
			System.out.println("btnName : " + btnName);
			
			JButton btn = new JButton(btnName);
			checkVacant(btn);
			btn.addActionListener((e)->{
				btnClicked(btn);
			});
			btn.setBounds(x, y, defaultWidth, defaultHeight);
			pnl.add(btn);
		}
	}
	
	private void checkVacant(JButton btn) {
		int index = Integer.parseInt(btn.getText());
		
		System.out.println("index : " + index);
		
		DeskDTO ddto = ddao.selectDesk(index);
		int vacant = ddto.getVacant();
		
		System.out.println(vacant);
		
		if(vacant == 1) {
			btn.setBackground(gray);
		} else {
			btn.setBackground(darkGray);
		}
		
	}
	
	private void btnClicked(JButton btn) {
		
		if(!isLoggedin(btn)) return;
		
		if (btn.getBackground() == gray) {
			
			occupy(btn);
			
		} else {
			
			vacant(btn);
		}
	}

	private boolean isLoggedin(JButton btn) {
		if(Library.sessionUserDTO == null) {
			return false;
		}
		return true;
	}

	private void occupy(JButton btn) {
		
		int isOcc = isOccupiedUser(Library.sessionUserDTO.getId());
		
		if (isOcc == 1) {
			System.out.println("�̹� �¼��� ������� ȸ���Դϴ�.");
			return;
		}
		
		int result = JOptionPane.showConfirmDialog(null, 
				"����Ͻðڽ��ϱ�",
				"��",
				JOptionPane.YES_NO_OPTION);
		
		if (result == 0) {
			int number = Integer.parseInt(btn.getText());			
			Timestamp startAt = new Timestamp(new Date().getTime());

			DeskDTO ddto = new DeskDTO(number, 0, Library.sessionUserDTO.getId(), startAt);
			
			int finalResult = ((DeskDAOImpl)ddao).updateDesk(number, ddto);
			btn.setBackground(darkGray);

			Library.txtSeat.setText(btn.getText());
			Library.txtSeat.repaint();
			
			if(finalResult == 0) {
				System.out.println("�¼� " + btn.getText() + "���� �����߽��ϴ�.");
			} else {
				System.out.println("�¼� ���࿡ �����߽��ϴ�.");
			}
			
			
		}
		
	}

	private int isOccupiedUser(String id) {
		int result = 0;
		String occupied;
		ArrayList<DeskDTO> list = ddao.selectAllDesk();
		
		for (int i = 0; i < list.size(); i++) {
			occupied = list.get(i).getOccupiedId();

			if (Library.sessionUserDTO.getId().equals(occupied)) {
				result = 1;
				break;
			}
		}
		return result;
	}

	private void vacant(JButton btn) {
		
		int index = Integer.parseInt(btn.getText());
		DeskDTO ddto = ddao.selectDesk(index);
		String occupiedId = ddto.getOccupiedId();
		System.out.println(occupiedId);
		
		if (occupiedId.equals(Library.sessionUserDTO.getId())) {
			int result = JOptionPane.showConfirmDialog(null, 
					"��� �����Ͻðڽ��ϱ�",
					"��",
					JOptionPane.YES_NO_OPTION);
			
			if(result == 0) {
				updateHour(index, ddto);
				
				ddto = new DeskDTO(index, 1, null, null);
				int finalResult = ((DeskDAOImpl)ddao).updateDesk(index, ddto);
				
				if(finalResult == 0) {
					System.out.println("�¼� " + btn.getText() + "�� ��� �����մϴ�.");
				} else {
					System.out.println("�¼� ��� ���ῡ �����߽��ϴ�.");
				}
				btn.setBackground(gray);
			}
		}
		
	}


	private void updateHour(int index, DeskDTO ddto) {
		Timestamp startTime = ddto.getStartAt();
		System.out.println("startTime : " + startTime);
		
		Date endTime = new Date();
		long timeDifference = endTime.getTime() - startTime.getTime();
		
		int deductionTime = (int) timeDifference/(60*1000);
		System.out.println("deductionTime : " + deductionTime);
		
		String id = ddto.getOccupiedId();
		UserDTO udto = udao.selectUser(id);
		int recordedTime = udto.getHour();
		int updatedTime = recordedTime - deductionTime;
		
		int result = 0;
		
		if (updatedTime >= 0) {
			result = udao.updateUserHour(id, updatedTime, true);			
		} else {
			result = udao.updateUserHour(id, 0, true);
		}
		
		if (result == 1) {
			System.out.println("�ð� ������ �Ϸ�Ǿ����ϴ�.");
		} else {
			System.out.println("�ð� ���� ����");
		}
		
		udto = udao.selectUser(id);
		Library.sessionUserDTO = udto;
		
		Library.txtSeat.setText(" ");
		Library.txtSeat.repaint();
		Library.txtRemainTime.setText(Integer.toString(Library.sessionUserDTO.getHour()) + "��");
		Library.txtRemainTime.repaint();
		
	}
	
}
