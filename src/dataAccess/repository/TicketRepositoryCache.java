package dataAccess.repository;

import dataAccess.dbmodel.TicketDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TicketRepositoryCache implements ITicketRepository{
    private ITicketRepository repository;
    private Cache<TicketDTO> cache;

    public TicketRepositoryCache(ITicketRepository ticketRepository){
        this.repository = ticketRepository;
        this.cache = new Cache<TicketDTO>();
    }

    @Override
    public List<TicketDTO> findAll() {
        if(cache.hasResult())
            return cache.load();
        List<TicketDTO> tickets = repository.findAll();
        cache.save(tickets);
        return tickets;
    }

    @Override
    public TicketDTO findById(int id) {
        if(cache.hasResult()) {
            List<TicketDTO> tickets = cache.load();
            return tickets.stream().filter(t -> t.getTicketId() == id).findFirst().get();
        } else
            return repository.findById(id);
    }

    @Override
    public TicketDTO findBySeat(int showId, int seatId) {
        if(cache.hasResult()) {
            List<TicketDTO> tickets = cache.load();
            TicketDTO ticket;
            try {
               ticket = tickets.stream().filter(t -> (t.getShowId() == showId && t.getSeatId() == seatId)).findFirst().get();
            } catch (NoSuchElementException e){
                return null;
            }
            return ticket;
        } else
            return repository.findBySeat(showId, seatId);
    }

    @Override
    public List<TicketDTO> findTicketsSold(int showId, boolean sold) {
        if(cache.hasResult()) {
            List<TicketDTO> tickets = cache.load();
            return tickets.stream().filter(t -> (t.getShowId() == showId && t.isBooked() == sold)).collect(Collectors.toList());
        } else
            return repository.findTicketsSold(showId, sold);
    }

    @Override
    public int insert(TicketDTO ticket){
        cache.invalidate();
        return repository.insert(ticket);
    }

    @Override
    public int update(TicketDTO ticket) {
        cache.invalidate();
        return repository.update(ticket);
    }

    @Override
    public int delete(int id) {
        cache.invalidate();
        return repository.delete(id);
    }
}
