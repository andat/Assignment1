package business.service;

import business.model.TicketModel;

import java.util.List;

public interface ITicketService {
    public List<TicketModel> findAll();
    public TicketModel findById(int id);
    public boolean changeSeat(TicketModel ticket, int newSeatId);
    public boolean delete(int id);
}
