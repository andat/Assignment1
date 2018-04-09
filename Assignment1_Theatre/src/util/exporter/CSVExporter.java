package util.exporter;

import business.model.TicketModel;

import java.io.*;
import java.util.List;

public class CSVExporter extends TicketExporter {
    private static final String SEPARATOR = ", ";

    @Override
    public void export(List<TicketModel> entries, String filename) {
        String csvFile = filename + ".csv";
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"));
            bw.write("id, show_id, seat_id, booked");
            bw.newLine();
            for (TicketModel t : entries)
            {
                StringBuffer line = new StringBuffer();
                line.append(t.getId());
                line.append(SEPARATOR);
                line.append(t.getShowid());
                line.append(SEPARATOR);
                line.append(t.getSeatid());
                line.append(SEPARATOR);
                line.append(t.isBooked() ? "true" : "false");
                bw.write(line.toString());
                bw.newLine();
            }
            System.out.println("Wrote csv to " + csvFile);
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
