import java.util.Objects;

public class Plane extends Vehicle  {
    // Plane class should inherit from Vehicle abstract class and override its abstract methods
    int  businessSeatCount;
    /**
     * @param seatLabel: Seat label as string "13A", "21B" etc..
     * NOTE: If it's a business seat, still it does not start with an * asterisk.
     * Asterisk character is only used during seat table printing.
     */
    public void bookSeat(String seatLabel) {
        Seat seat = null;
        for (Seat sea:this.seatList) {
            if (Objects.equals(sea.label, seatLabel)){
                seat = sea;
            }
        }
        if (seat==null){
            System.out.print("No such seat exists.\n");
        }
        else if (seat.booked){
            System.out.printf("%s cannot be booked, it's already booked.\n",seatLabel);
        }
        else {
            seat.booked = true;
            System.out.printf("%s booked.\n", seatLabel);
        }
    }

    @Override
    public String toString() {
        return "Plane ("+this.identifier+"), model: "+this.model+", capacity: "+this.capacity+", businessSeatCount: "+this.businessSeatCount;
    }

    @Override
    public void printSeatsTable() {
        System.out.print("\n### START OF PLANE INFO ###\n");
        System.out.print(toString()+"\n");
        for (int i = 0;i<this.capacity;i++){
            if (i%6==0){
                System.out.print("\n");
            }
            else if (i%3==0){
                System.out.print("\t\t");
            }
            if (i<this.businessSeatCount){
                if (this.seatList.get(i).booked){
                    System.out.printf("*%s\t[X]\t",this.seatList.get(i).label);
                }
                else {
                    System.out.printf("*%s\t[ ]\t",this.seatList.get(i).label);
                }
            }
            else {
                if (this.seatList.get(i).booked){
                    System.out.printf("%s\t[X]\t",this.seatList.get(i).label);
                }
                else {
                    System.out.printf("%s\t[ ]\t",this.seatList.get(i).label);
                }
            }


        }

        System.out.println("\n### END OF PLANE INFO ###");
    }
}
