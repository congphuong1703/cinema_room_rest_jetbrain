package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class SeatController {

    private static List<Seat> seats = new ArrayList<>();
    int totalRows = 9;
    int totalColumns = 9;

    @GetMapping(produces = "application/json", value = "/seats")
    public Cinema findAllSeats() {

        Cinema cinema = new Cinema();
        if (seats.isEmpty()) {
            for (int i = 0; i < totalRows; i++) {
                for (int j = 0; j < totalColumns; j++) {
                    Seat seat = new Seat(i + 1, j + 1);
                    seats.add(seat);
                }
            }
        }
        cinema.setTotalColumn(totalColumns);
        cinema.setTotalRows(totalRows);
        cinema.setAvailableSeats(seats);

        return cinema;
    }

    @PostMapping(value = "/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat request) {
        Seat seat;
        try {
            if (request.getRow() < 1 || request.getColumn() < 1 || request.getRow() > 9 || request.getColumn() > 9) {
                throw new Exception();
            }
            seat = seats.get((request.getRow() - 1) * 9 + request.getColumn() - 1);
            if (seat.isPurchased()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("The ticket has been already purchased!"));
            } else {
                UUID token = UUID.randomUUID();

                seat.setPurchased(true);
                seat.setToken(token.toString());

                TicketResponse ticketResponse = new TicketResponse(token.toString(), seat);
                return ResponseEntity.ok(ticketResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("The number of a row or a column is out of bounds!"));
        }
    }

    @PostMapping(value = "/return")
    public ResponseEntity<?> refund(@RequestBody TokenRequest request) {
        try {
            if (request.getToken() == null)
                throw new Exception();
            for (Seat seat : seats) {
                if (request.getToken().equals(seat.getToken())) {
                    seat.setPurchased(false);
                    seat.setToken(null);

                    return ResponseEntity.ok(new SeatResponse(seat));
                }
            }
        } catch (Exception e) {

        }
        return ResponseEntity.badRequest().body(new ErrorResponse("Wrong token!"));
    }

    @GetMapping("/stats")
    public ResponseEntity<?> stat(String password) {
        if (password == null) {
            return ResponseEntity.status(401).body(new ErrorResponse("The password is wrong!"));
        }
        int income = 0;
        int availableSeats = 0;
        int purchasedTicket = 0;
        for (Seat seat : seats) {
            if (seat.isPurchased()) {
                income += seat.getPrice();
                purchasedTicket++;
            } else {
                availableSeats++;
            }
        }

        return ResponseEntity.ok(new StatResponse(income, availableSeats, purchasedTicket));
    }
}
