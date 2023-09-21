package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeatResponse {

    @JsonProperty("returned_ticket")
    private Seat returnTicket;


    public SeatResponse(Seat returnTicket) {
        this.returnTicket = returnTicket;
    }

    public SeatResponse() {
    }

    public Seat getReturnTicket() {
        return returnTicket;
    }

    public void setReturnTicket(Seat returnTicket) {
        this.returnTicket = returnTicket;
    }
}
