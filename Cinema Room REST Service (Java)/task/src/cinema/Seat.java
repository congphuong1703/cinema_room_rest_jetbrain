package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {

    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean isPurchased;
    private String token;

    public Seat() {
    }

    public Seat(int row, int column, int price, String token) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.token = token;
    }

    public Seat(int row, int column) {
        if (row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
        this.row = row;
        this.column = column;
    }

    @JsonIgnore
    public boolean isPurchased() {
        return isPurchased;
    }

    @JsonIgnore
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
