package dataAccess.repository;

import dataAccess.dbmodel.TicketDTO;

import java.util.List;

public class TicketRepositoryCache implements ITicketRepository{
    private ITicketRepository repository;
    private Cache<TicketDTO> cache;

    public TicketRepositoryCache(ITicketRepository ticketRepository){
        this.repository = ticketRepository;
        this.cache = new Cache<TicketDTO>();
    }

    public List<TicketDTO> findAll() {
        if(cache.hasResult())
            return cache.load();
        List<TicketDTO> tickets = repository.findAll();
        cache.save(tickets);
        return tickets;
    }

    public TicketDTO findById(int id) {
        if(cache.hasResult()) {
            List<TicketDTO> tickets = cache.load();
            return tickets.stream().filter(t -> t.getTicketId() == id).findFirst().get();
        } else
            return repository.findById(id);
    }

    public int insert(TicketDTO ticket){
        cache.invalidate();
        return repository.insert(ticket);
    }

    public int update(TicketDTO ticket) {
        cache.invalidate();
        return repository.update(ticket);
    }

    public int delete(int id) {
        cache.invalidate();
        return repository.delete(id);
    }
}
