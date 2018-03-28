package util.exporter;

import business.model.TicketModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLExporter extends TicketExporter {

    @Override
    public void export(List<TicketModel> entries, String filename) {
        String xmlFile = filename + ".xml";

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("sold_tickets");
            doc.appendChild(rootElement);

            int index = 0;
            for(TicketModel t : entries) {
                //ticket element
                Element ticket = doc.createElement("ticket_" + index);
                rootElement.appendChild(ticket);

                //children
                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(t.getId())));
                ticket.appendChild(id);

                Element show = doc.createElement("show_id");
                show.appendChild(doc.createTextNode(Integer.toString(t.getShowid())));
                ticket.appendChild(show);

                Element seat = doc.createElement("seat_id");
                seat.appendChild(doc.createTextNode(Integer.toString(t.getSeatid())));
                ticket.appendChild(seat);

                Element booked = doc.createElement("booked");
                booked.appendChild(doc.createTextNode(Boolean.toString(t.isBooked())));
                ticket.appendChild(booked);

                index++;
            }

            //write data to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFile));

            transformer.transform(source, result);
            System.out.println("Wrote xml to " + xmlFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
