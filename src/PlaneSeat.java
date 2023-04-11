public class PlaneSeat extends Seat {
    /** PlaneSeat class should inherit from Seat abstract class
     *  This should override getSummary(), toString() methods
     *  Also it should store baggage limit as an integer property.
     */
    int baggageLimit;
    @Override
    public String toString() {
        return this.label;
    }

    public int getPrice() {
        return price;
    }
    public int getBaggageLimit() {
        return this.baggageLimit;
    }

    @Override
    public String getSummary() {
        return "PlaneSeat - Label: "+this.label+" - Price: "
                +this.getPrice()+" TL - Baggage Limit: "+this.getBaggageLimit()+" kg - Booked: "+this.booked;
    }
}
