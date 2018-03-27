package business.service;

import business.model.ShowModel;
import business.model.TicketModel;

import java.util.List;

public interface IShowService {
    public List<ShowModel> findAll();
    public ShowModel findById(int id);
    public boolean addShow(ShowModel show);
    public boolean edit(ShowModel show);
    public boolean delete(int id);
    public boolean sellTicket(TicketModel ticket) throws Exception;
    public boolean cancelBooking(TicketModel ticket);
    public List<TicketModel> findSoldTickets(ShowModel show);
    public List<TicketModel> findAvailableTickets(ShowModel show);
    public void exportSoldTickets(ShowModel show, FormatType format);

}
