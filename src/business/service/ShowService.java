package business.service;

import business.model.SeatModel;
import business.model.ShowModel;
import business.model.TicketModel;
import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.ShowDTO;
import dataAccess.dbmodel.TicketDTO;
import dataAccess.repository.*;
import util.FormatType;
import util.exporter.TicketExporter;
import util.exporter.ExporterFactory;

import java.util.ArrayList;
import java.util.List;

public class ShowService implements IShowService{
    private final IShowRepository repository;
    private final ITicketRepository ticketRepository;
    private final ExporterFactory exporterFactory;

    public ShowService(){
        this.repository = new ShowRepository(ConnectionFactory.getSingleInstance());
        this.ticketRepository = new TicketRepositoryCache(new TicketRepository(ConnectionFactory.getSingleInstance()));
        this.exporterFactory = new ExporterFactory();
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
        int maxCapacity = SeatService.getHallCapacity();
        ShowDTO s = new ShowDTO(show.getId(), show.getTitle(), show.getGenre(), show.getDistribution(), show.getDate(),
                maxCapacity);
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
    public boolean sellTicket(SeatModel seat, ShowModel show) throws Exception {
        int remaining = getNoOfRemainingTickets(show);
        if (remaining != 0){
            TicketDTO t = this.ticketRepository.findBySeat(seat.getId(), show.getId());
            //if no ticket for this seat yet
            if(t == null) {
                TicketDTO newT = new TicketDTO(-1, show.getId(), seat.getId(), true);
                int insertedId = ticketRepository.insert(newT);
                return (insertedId != -1);
            } else if(t.isBooked())
                return false;
            else {
                TicketDTO soldT = t;
                soldT.setBooked(true);
                int updatedRows = ticketRepository.update(soldT);
                return (updatedRows != 0);
            }
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
    public int getNoOfRemainingTickets(ShowModel show) {
        return (show.getNoOfTickets() - this.findSoldTickets(show).size());
    }

    @Override
    public void exportSoldTickets(ShowModel show, FormatType format, String filename) {
        List<TicketModel> sold = this.findSoldTickets(show);
        TicketExporter ticketExporter = this.exporterFactory.createExporter(format);
        ticketExporter.export(sold, filename);
    }
}
