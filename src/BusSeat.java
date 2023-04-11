public class BusSeat extends Seat {
    char gender = 'x' ;

    @Override
    public String toString() {
        return this.label ;
    }
    public int getPrice() {
        return price;
    }
    @Override
    public String getSummary() {
        if (this.booked){
            return "BusSeat - Label: "+this.label+" - Price: "+this.price+" TL - Booked: true ("+this.gender+") ";
        }
        return "BusSeat - Label: "+this.label+" - Price: "+this.price+" TL - Booked: false";

    }
}
