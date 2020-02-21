package libraryProgram.dao;

import java.util.ArrayList;

import project.library.dto.CostDTO;

public interface CostDAO {

    // Cost    
    public CostDTO selectCost(int price);
    
    public ArrayList<CostDTO> selectCost();
              
}