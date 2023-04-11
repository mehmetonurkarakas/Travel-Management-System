import java.util.Objects;

public class Bus extends Vehicle {
    // Bus class should inherit from Vehicle abstract class and override its abstract methods

    /**
     *
     * @param seatLabel: Seat label as string "13", "21" etc..
     * @param gender: Either "M" or "F" as a char variable
     */
    public void bookSeat(String seatLabel, char gender) {
        BusSeat seat = null;
        BusSeat seat1 =null;

        for (int i = 0;i<this.seatList.size();i++){
            if (Objects.equals(seatList.get(i).label, seatLabel)){
                seat = (BusSeat) seatList.get(i);
                if ((i+1)%2==0){
                    seat1 = (BusSeat) seatList.get(i-1);
                }else {
                    seat1 = (BusSeat) seatList.get(i+1);
                }
            }
        }

        if (seat!=null){
            if (seat.booked){
                System.out.printf("%s cannot be booked, it's already booked.\n",seat.label);
            }
            else if (seat1.gender==gender||seat1.gender=='x'){
                System.out.printf("%s booked.\n", seat.label);
                seat.booked = true;
                seat.gender = gender;
            }
            else {
                System.out.printf("%s cannot be booked because neighbor seat was booked by different gender person.\n",seat.label);
            }
        }
        else {
            System.out.print("No such seat exists.\n");
        }

    }
    @Override
    public String toString() {
        return "Bus ("+this.identifier+"), model: "+this.model+", capacity: "+this.capacity;
    }

    @Override
    public void printSeatsTable() {
        System.out.print("\n### START OF BUS INFO ###\n");
        System.out.print(toString()+"\n");
        for (int i = 0;i<this.capacity;i++){
            if (i%4==0){
                System.out.print("\n");
            }
            else if (i%2==0){
                System.out.print("\t\t");
            }

            if (this.seatList.get(i).booked){
                BusSeat seat;
                seat = (BusSeat)this.seatList.get(i);
                System.out.printf("%s\t[%s]\t",this.seatList.get(i).label,seat.gender);
            }
            else {
                System.out.printf("%s\t[ ]\t",this.seatList.get(i).label);
            }
        }


        System.out.println("\n### END OF BUS INFO ###");
    }
}
