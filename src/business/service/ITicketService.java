package business.service;

import business.model.TicketModel;

import java.util.List;

public interface ITicketService {
    public List<TicketModel> findAll();
    public TicketModel findById(int id);
    public TicketModel findByUsername(String username);
    public boolean cancelBooking(TicketModel ticket);
    public boolean changeSeat(TicketModel ticket, int seat_id);
    public boolean delete(int id);
}
