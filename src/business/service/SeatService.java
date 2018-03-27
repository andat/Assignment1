package business.service;

import business.model.SeatModel;
import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.SeatDTO;
import dataAccess.repository.ISeatRepository;
import dataAccess.repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;

public class SeatService implements ISeatService {
    private final ISeatRepository repository;
    private static final int NO_OF_ROWS = 20;
    private static final int NO_OF_SEATS_ON_ROW = 30;

    public SeatService(){
        this.repository = new SeatRepository(ConnectionFactory.getSingleInstance());
    }

    public static int getHallCapacity(){
        return NO_OF_ROWS * NO_OF_SEATS_ON_ROW;
    }

    @Override
    public List<SeatModel> findAll(){
        List<SeatDTO> list = repository.findAll();
        List<SeatModel> result = new ArrayList<>();
        for(SeatDTO s : list){
            result.add(new SeatModel(s.getSeatId(), s.getRow(), s.getNumber()));
        }
        return result;
    }

    @Override
    public SeatModel findById(int id) {
        SeatDTO seat = repository.findById(id);
        SeatModel result = new SeatModel(seat.getSeatId(), seat.getRow(), seat.getNumber()) ;
        return result;
    }

    @Override
    public boolean addAllSeats() {
        int count = 0;
        for(int row = 0 ; row < NO_OF_ROWS; row ++) {
            for (int nr = 0; nr < NO_OF_SEATS_ON_ROW; nr++) {
                String r = "R" + row;
                if (repository.insert(new SeatDTO(-1, r, nr)) == -1) {
                    System.out.println("Not all seats were added.");
                    return false;
                }
                count++;
            }
        }
        System.out.println(count + " seats were inserted.");
        return true;
    }

    @Override
    public boolean editSeat(SeatModel seat) {
        int updatedRows = repository.update(new SeatDTO(seat.getId(), seat.getRow(), seat.getNumber()));
        return (updatedRows != 0);
    }

    @Override
    public boolean deleteSeat(int id) {
        int deletedRows = repository.delete(id);
        return (deletedRows != 0);
    }

//    public static void main(String args[]){
//        SeatService ss = new SeatService();
//        System.out.println(ss.addAllSeats());
//    }
}


