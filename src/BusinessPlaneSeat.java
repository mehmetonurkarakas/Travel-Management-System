public class BusinessPlaneSeat extends PlaneSeat{
    /**
     BusinessPlaneSeat class should inherit from PlaneSeat class
     also it should override getBaggageLimit(), getPrice(), getSummary()
     */
    @Override
    public String toString() {
        return "*"+this.label;
    }
    public int getPrice() {
        return (int)(price*1.8);
    }
    public int getBaggageLimit() {
        return baggageLimit*2;
    }

    @Override
    public String getSummary() {
        return "BusinessPlaneSeat - Label: "+this.label+" - Price: "
                +this.getPrice()+" TL - Baggage Limit: "+this.getBaggageLimit()+" kg - Booked: "+this.booked;
    }
}
