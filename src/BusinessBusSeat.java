public class BusinessBusSeat extends BusSeat {
    /** BusinessBusSeat class should inherit from BusSeat class
        It represents single and large seats which exist in 2+1 buses.
        It should override getPrice(), getSummary() and toString() methods.
        toString() method returns a string with * asterisk prefix before its label (e.g. *2A)
     */

    @Override
    public String toString() {
        return "*"+this.label;
    }
    public int getPrice() {
        return (int)(price*1.3);
    }

    @Override
    public String getSummary() {
        if (this.booked){
            return "BusinessBusSeat - Label: "+this.label+" - Price: "+this.getPrice()+" TL - Booked: true ("+this.gender+")";
        }
        return "BusinessBusSeat - Label: "+this.label+" - Price: "+this.getPrice()+" TL - Booked: false";
    }
}
