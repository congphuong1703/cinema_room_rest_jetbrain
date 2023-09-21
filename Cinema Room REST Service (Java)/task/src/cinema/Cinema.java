package cinema;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Cinema {

    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumn;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumn() {
        return totalColumn;
    }

    public void setTotalColumn(int totalColumn) {
        this.totalColumn = totalColumn;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
