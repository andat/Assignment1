package business.service;

import business.model.TicketModel;
import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.TicketDTO;
import dataAccess.repository.ITicketRepository;
import dataAccess.repository.TicketRepository;
import dataAccess.repository.TicketRepositoryCache;

import java.util.ArrayList;
import java.util.List;

public class TicketService implements ITicketService{
    private final ITicketRepository cacheRepository;

    public TicketService() {
        this.cacheRepository = new TicketRepositoryCache(new TicketRepository(ConnectionFactory.getSingleInstance()));
    }

    @Override
    public List<TicketModel> findAll() {
        List<TicketDTO> tickets = cacheRepository.findAll();
        List<TicketModel> list = new ArrayList<>();
        for(TicketDTO t : tickets) {
            list.add(new TicketModel(t.getTicketId(), t.getShowId(), t.getSeatId(), t.isBooked()));
        }
        return list;
    }

    @Override
    public TicketModel findById(int id) {
        TicketDTO t = cacheRepository.findById(id);
        TicketModel ticket = new TicketModel(t.getTicketId(), t.getShowId(), t.getSeatId(), t.isBooked());
        return ticket;
    }


    @Override
    public boolean changeSeat(TicketModel ticket, int newSeatId) {
        //if no ticket with new seat id was found, change seat for this ticket
        if(cacheRepository.findBySeat(ticket.getShowid(), newSeatId) == null){
            TicketDTO t = new TicketDTO(ticket.getId(), ticket.getShowid(), newSeatId, ticket.isBooked());
            int updatedRows = cacheRepository.update(t);
            return (updatedRows != 0);
        } else {
            System.out.println("The seat is already booked.");
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        int deletedRows = cacheRepository.delete(id);
        return (deletedRows != 0);
    }
}
