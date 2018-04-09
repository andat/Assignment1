package business.service;

import business.model.SeatModel;

import java.util.List;

public interface ISeatService {
    public List<SeatModel> findAll();
    public SeatModel findById(int id);
    public boolean addAllSeats();
    public boolean editSeat(SeatModel seat);
    public boolean deleteSeat(int id);
}
