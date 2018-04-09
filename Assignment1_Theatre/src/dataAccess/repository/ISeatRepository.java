package dataAccess.repository;

import dataAccess.dbmodel.SeatDTO;

import java.util.List;

public interface ISeatRepository {
    public List<SeatDTO> findAll();
    public SeatDTO findById(int id);
    public int insert(SeatDTO seat);
    public int update(SeatDTO seat);
    public int delete(int id);
}
