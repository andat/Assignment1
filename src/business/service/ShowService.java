package business.service;

import business.model.ShowModel;
import business.model.TicketModel;
import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.ShowDTO;
import dataAccess.dbmodel.TicketDTO;
import dataAccess.repository.*;

import java.util.ArrayList;
import java.util.List;

public class ShowService implements IShowService{
    private final IShowRepository repository;
    private final ITicketRepository ticketRepository;
    private final int maxNoOfTickets;
    private int remainingTickets;

    public ShowService(){
        this.repository = new ShowRepository(ConnectionFactory.getSingleInstance());
        this.ticketRepository = new TicketRepositoryCache(new TicketRepository(ConnectionFactory.getSingleInstance()));
        this.maxNoOfTickets = SeatService.getHallCapacity();
        this.remainingTickets = this.maxNoOfTickets;
    }


    @Override
    public List<ShowModel> findAll() {
        List<ShowDTO> shows = repository.findAll();
        List<ShowModel> list = new ArrayList<>();
        for(ShowDTO s : shows) {
            list.add(new ShowModel(s.getShowId(), s.getTitle(), s.getGenre(), s.getDistribution(),
                        s.getDate(), s.getNoOfTickets()));
        }
        return list;
    }

    @Override
    public ShowModel findById(int id) {
        ShowDTO s = repository.findById(id);
        ShowModel show = new ShowModel(s.getShowId(), s.getTitle(), s.getGenre(), s.getDistribution(),
                s.getDate(), s.getNoOfTickets());
        return show;
    }

    @Override
    public boolean addShow(ShowModel show) {
        ShowDTO s = new ShowDTO(show.getId(), show.getTitle(), show.getGenre(), show.getDistribution(), show.getDate(),
                this.maxNoOfTickets);
        int insertedId = repository.insert(s);
        return (insertedId != -1);
    }

    @Override
    public boolean edit(ShowModel show) {
        ShowDTO s = new ShowDTO(show.getId(), show.getTitle(), show.getGenre(), show.getDistribution(), show.getDate(), show.getNoOfTickets());
        int updatedRows = repository.update(s);
        return (updatedRows != 0);
    }

    @Override
    public boolean delete(int id) {
        int deletedRows = repository.delete(id);
        return (deletedRows != 0);
    }

    @Override
    public boolean sellTicket(TicketModel ticket) throws Exception {
        if (remainingTickets != 0){
            TicketDTO t = new TicketDTO(ticket.getId(), ticket.getShowid(), ticket.getSeatid(), true);
            int insertedId = ticketRepository.insert(t);
            this.remainingTickets --;
            return (insertedId != -1);
        } else{
            throw new Exception("Number of tickets exceeded! Show is sold out!");
        }
    }

    @Override
    public boolean cancelBooking(TicketModel ticket) {
        //change booked to false
        if(ticket.isBooked() == false)
            return false;
        TicketDTO t = new TicketDTO(ticket.getId(), ticket.getShowid(), ticket.getSeatid(), false);
        //update number of remaining tickets
        this.remainingTickets ++;
        int updatedRows = ticketRepository.update(t);
        return (updatedRows != 0);
    }

    @Override
    public List<TicketModel> findSoldTickets(ShowModel show) {
        List<TicketDTO> list = ticketRepository.findTicketsSold(show.getId(), true);
        List<TicketModel> tickets = new ArrayList<>();
        for(TicketDTO t : list){
            tickets.add(new TicketModel(t.getTicketId(), t.getShowId(), t.getSeatId(), t.isBooked()));
        }
        return tickets;
    }

    @Override
    public List<TicketModel> findAvailableTickets(ShowModel show) {
        List<TicketDTO> list = ticketRepository.findTicketsSold(show.getId(), false);
        List<TicketModel> tickets = new ArrayList<>();
        for(TicketDTO t : list){
            tickets.add(new TicketModel(t.getTicketId(), t.getShowId(), t.getSeatId(), t.isBooked()));
        }
        return tickets;
    }

    @Override
    public void exportSoldTickets(ShowModel show, FormatType format) {
        List<TicketModel> sold = this.findSoldTickets(show);
    }
}
