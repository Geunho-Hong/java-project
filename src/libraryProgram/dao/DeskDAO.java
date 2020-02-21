package libraryProgram.dao;

import java.util.ArrayList;

import libraryProgram.dto.DeskDTO;
import project.library.dto.DeskDTO;

public interface DeskDAO {

    // Desk  
	public ArrayList<DeskDTO> selectAllDesk();
	
    public DeskDTO selectDesk(int index);
    
    public int updateDesk(int num, DeskDTO ddto);
   
}