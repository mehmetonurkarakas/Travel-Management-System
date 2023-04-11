import java.util.Objects;

public class LargePlane extends Plane {
    /** LargePlane class should inherit from Plane class and
     *  override printSeatsTable(), bookSeat(String seatLabel), toString() functions
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
        return "LargePlane ("+this.identifier+"), model: "+this.model+", capacity: "+this.capacity+", businessSeatCount: "+this.businessSeatCount;
    }

    @Override
    public void printSeatsTable() {
        System.out.print("\n### START OF PLANE INFO ###\n");
        System.out.print(toString()+"\n");
        for (int i = 0;i<this.capacity;i++){
            if (i%8==0){
                System.out.print("\n");
            }
            else if (i%2==0&&i%4!=0){
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
