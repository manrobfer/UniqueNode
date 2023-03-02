package br.com.multreaders.footer;

import br.com.multreaders.model.Vehicle;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

@Component
public class HeaderFileFooter implements FlatFileFooterCallback {

    private double priceTotal;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.append("-----------------------------------------// Footer //------------------------------");
        writer.append("\n Vehicles total " + priceTotal);

    }

    @BeforeWrite
    public void beforeW(List<Vehicle> vehicles){
        for(Vehicle vehicle: vehicles) {
            priceTotal  += vehicle.getPrice();
        }

    }
}
