import javax.sql.rowset.FilteredRowSet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    static Path commandsFilepath;
    static Path outputFilepath;
    static List<Vehicle> vehicles = new ArrayList<>();
    static boolean printToOutputFile = true;

    public static void main(String[] args) {
        commandsFilepath = Paths.get(args[0]);
        outputFilepath = Paths.get(args[1]);

        // DON'T CHANGE LINES BELOW, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        ByteArrayOutputStream baos = null;
        if (printToOutputFile) {
            baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
        }
        // DON'T CHANGE THESE LINES, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        try {
            List<String> lines = Files.readAllLines(commandsFilepath);
            for (String s : lines) {
                String[] line = s.split(" ");
                if (Objects.equals(line[0],"ADD")){
                    if (Objects.equals(line[2], "PLANE")){
                        if (Objects.equals(line[3], "Boeing")) { //add vehicle plane
                            Plane plane = new Plane();
                            plane.model = line[3];
                            plane.identifier = line[4];
                            plane.capacity = Integer.parseInt(line[5]);
                            plane.businessSeatCount = Integer.parseInt(line[6]);

                            List<String> labels = new ArrayList<String>(
                                    Arrays.asList("A","B","C","D","E","F"));
                            for (int i = 1;i<=Integer.parseInt(line[5])/6;i++){
                                for (int j = 0;j<6;j++){
                                    if (i<=Integer.parseInt(line[6])/6){
                                        BusinessPlaneSeat businessPlaneSeat = new BusinessPlaneSeat();
                                        businessPlaneSeat.label = i+labels.get(j);
                                        plane.seatList.add(businessPlaneSeat);
                                    }
                                    else{
                                        PlaneSeat planeSeat = new PlaneSeat();
                                        planeSeat.label = i+labels.get(j);
                                        plane.seatList.add(planeSeat);
                                    }
                                }
                            }
                            vehicles.add(plane);
                            System.out.printf("Vehicle was added. Plane (%s), model: %s, capacity: %s, businessSeatCount: %s\n", plane.identifier,
                                    plane.model, plane.capacity, plane.businessSeatCount);

                        }
                        else if (Objects.equals(line[3],"Airbus")){
                            LargePlane largePlane = new LargePlane();
                            largePlane.model = line[3];
                            largePlane.identifier = line[4];
                            largePlane.capacity = Integer.parseInt(line[5]);
                            largePlane.businessSeatCount = Integer.parseInt(line[6]);

                            List<String> labels = new ArrayList<String>(
                                    Arrays.asList("A","B","C","D","E","F","G","H"));
                            for (int i = 1;i<=Integer.parseInt(line[5])/8;i++){
                                for (int j = 0;j<8;j++){
                                    if (i<=Integer.parseInt(line[6])/8){
                                        BusinessPlaneSeat businessPlaneSeat = new BusinessPlaneSeat();
                                        businessPlaneSeat.label = i+labels.get(j);
                                        largePlane.seatList.add(businessPlaneSeat);
                                    }
                                    else{
                                        PlaneSeat planeSeat = new PlaneSeat();
                                        planeSeat.label = i+labels.get(j);
                                        largePlane.seatList.add(planeSeat);
                                    }
                                }
                            }
                            vehicles.add(largePlane);
                            System.out.printf("Vehicle was added. LargePlane (%s), model: %s, capacity: %s, businessSeatCount: %s\n",largePlane.identifier,
                                    largePlane.model,largePlane.capacity,largePlane.businessSeatCount);
                        }
                    }
                    if (Objects.equals(line[2],"BUS")){
                        if (Objects.equals(line[3],"Tourismo")||Objects.equals(line[3],"Fortuna")){
                            Bus bus = new Bus();
                            bus.model = line[3];
                            bus.identifier = line[4];
                            bus.capacity = Integer.parseInt(line[5]);


                            for (int i = 1;i<Integer.parseInt(line[5])+1;i++){
                                BusSeat busSeat = new BusSeat();
                                busSeat.label = String.valueOf(i);
                                bus.seatList.add(busSeat);
                            }
                            vehicles.add(bus);
                            System.out.printf("Vehicle was added. Bus (%s), model: %s, capacity: %s\n",bus.identifier,
                                    bus.model,bus.capacity);

                        }
                        else if (Objects.equals(line[3],"Neoplan")||Objects.equals(line[3],"Starliner")){
                            TwoPlusOneBus twoPlusOneBus = new TwoPlusOneBus();
                            twoPlusOneBus.model = line[3];
                            twoPlusOneBus.identifier = line[4];
                            twoPlusOneBus.capacity = Integer.parseInt(line[5]);

                            for (int i = 1;i<Integer.parseInt(line[5])+1;i++){
                                if (i%3==1){
                                    BusinessBusSeat businessBusSeat = new BusinessBusSeat();
                                    businessBusSeat.label = String.valueOf(i);
                                    twoPlusOneBus.seatList.add(businessBusSeat);
                                }
                                else {
                                    BusSeat busSeat = new BusSeat();
                                    busSeat.label = String.valueOf(i);
                                    twoPlusOneBus.seatList.add(busSeat);
                                }

                            }
                            vehicles.add(twoPlusOneBus);
                            System.out.printf("Vehicle was added. TwoPlusOneBus (%s), model: %s, capacity: %s\n",twoPlusOneBus.identifier,
                                    twoPlusOneBus.model,twoPlusOneBus.capacity);
                        }

                    }
                }
                else if (Objects.equals(line[0],"SET")){
                    if (Objects.equals(line[2],"SEAT")){
                        for (Vehicle v:vehicles) {
                            if (Objects.equals(line[6], v.identifier)){
                                for (Seat sea:v.seatList) {
                                    sea.price = Integer.parseInt(line[4]);
                                }
                            }
                        }
                        System.out.printf("Basic seat price was set to %s TL for %s.\n",line[4],line[6]);
                    }
                    else if (Objects.equals(line[2],"BAGGAGE"))
                    {
                        for (Vehicle v:vehicles) {
                            if (Objects.equals(line[6], v.identifier)){
                                for (Seat sea:v.seatList) {
                                    ((PlaneSeat) sea).baggageLimit = Integer.parseInt(line[4]);
                                }
                            }
                        }
                        System.out.printf("Basic baggage limit was set to %s kg for %s.\n",line[4],line[6]);
                    }
                }
                else if (Objects.equals(line[0],"BOOK")) {
                    if (Objects.equals(line[1], "PLANESEAT")) {
                        Plane plane;
                        LargePlane largePlane;
                        for (Vehicle vehic : vehicles) {
                            if (Objects.equals(vehic.identifier, line[4])) {
                                if (Objects.equals(vehic.model, "Boeing"))
                                    plane = null;
                                plane = (Plane) vehic;
                                plane.bookSeat(line[2]);
                            } else if (Objects.equals(vehic.model, "Airbus")) {
                                largePlane = null;
                                largePlane = (LargePlane) vehic;
                                largePlane.bookSeat(line[2]);
                            }
                        }
                    } else if (Objects.equals(line[1], "BUSSEAT")) {
                        Bus bus;
                        TwoPlusOneBus twoPlusOneBus;
                        for (Vehicle v:vehicles) {
                            if (Objects.equals(v.identifier, line[5])){
                                if (Objects.equals(v.model, "Tourismo") || Objects.equals(v.model, "Fortuna")){
                                    bus = (Bus) v;
                                    char c = line[3].charAt(0);
                                    ((Bus) v).bookSeat(line[2],c);
                                }
                                else if (Objects.equals(v.model, "Neoplan") || Objects.equals(v.model, "Starliner")){
                                    bus = (Bus) v;
                                    char c = line[3].charAt(0);
                                    ((Bus) v).bookSeat(line[2],c);
                                }
                            }
                        }
                    }
                }
                else if (Objects.equals(line[0],"PRINT")) {
                    if (Objects.equals(line[1], "SEATTABLE")) {
                        Bus bus;
                        TwoPlusOneBus twoPlusOneBus;
                        Plane plane;
                        LargePlane largePlane;
                        for (Vehicle vehi : vehicles) {
                            if (Objects.equals(vehi.identifier, line[2])) {
                                if (Objects.equals(vehi.model, "Tourismo") || Objects.equals(vehi.model, "Fortuna")) {
                                    bus = (Bus) vehi;
                                    bus.printSeatsTable();
                                } else if (Objects.equals(vehi.model, "Neoplan") || Objects.equals(vehi.model, "Starliner")) {
                                    twoPlusOneBus = (TwoPlusOneBus) vehi;
                                    twoPlusOneBus.printSeatsTable();
                                } else if (Objects.equals(vehi.model, "Boeing")) {
                                    plane = (Plane) vehi;
                                    plane.printSeatsTable();
                                } else if (Objects.equals(vehi.model, "Airbus")) {
                                    largePlane = (LargePlane) vehi;
                                    largePlane.printSeatsTable();
                                }
                            }
                        }
                    } else if (Objects.equals(line[1], "SEATSUMMARY")) {
                        Bus bus;
                        TwoPlusOneBus twoPlusOneBus;
                        Plane plane;
                        LargePlane largePlane;
                        BusSeat seat;
                        BusinessBusSeat businessBusSeat;
                        PlaneSeat planeSeat;
                        BusinessPlaneSeat businessPlaneSeat;
                        for (Vehicle vehi : vehicles) {
                            if (Objects.equals(vehi.identifier, line[2])) {
                                if (Objects.equals(vehi.model, "Tourismo") || Objects.equals(vehi.model, "Fortuna")) {
                                    bus = (Bus) vehi;
                                    System.out.print("### START OF SEAT SUMMARIES ###\n");
                                    System.out.print(bus.toString()+"\n");
                                    for (Seat sea:bus.seatList) {
                                        System.out.print(sea.getSummary()+"\n");
                                    }
                                } else if (Objects.equals(vehi.model, "Neoplan") || Objects.equals(vehi.model, "Starliner")) {
                                    twoPlusOneBus = (TwoPlusOneBus) vehi;
                                    System.out.print("### START OF SEAT SUMMARIES ###\n");
                                    System.out.print(twoPlusOneBus.toString()+"\n");
                                    for (Seat sea:twoPlusOneBus.seatList) {
                                        System.out.print(sea.getSummary()+"\n");
                                    }
                                    /*for (int i = 0; i < twoPlusOneBus.capacity; i++) {
                                        if (i % 3 == 0) {
                                            businessBusSeat = (BusinessBusSeat) twoPlusOneBus.seatList.get(i);
                                            System.out.println(businessBusSeat.getSummary());
                                        } else {
                                            seat = (BusSeat) twoPlusOneBus.seatList.get(i);
                                            System.out.println(seat.getSummary());
                                        }
                                    }*/

                                } else if (Objects.equals(vehi.model, "Boeing")) {
                                    plane = (Plane) vehi;
                                    System.out.print("### START OF SEAT SUMMARIES ###"+"\n");
                                    System.out.print(plane.toString()+"\n");
                                    for (Seat sea:plane.seatList) {
                                        System.out.print(sea.getSummary()+"\n");
                                    }
                                    /*for (int i = 0; i < plane.capacity; i++) {
                                        if (i < plane.businessSeatCount) {
                                            planeSeat = (PlaneSeat) plane.seatList.get(i);
                                            System.out.println(planeSeat.getSummary());
                                        } else {
                                            System.out.println("########################");
                                            businessPlaneSeat = (BusinessPlaneSeat) plane.seatList.get(i);
                                            System.out.println(businessPlaneSeat.getSummary());
                                        }
                                    }*/

                                } else if (Objects.equals(vehi.model, "Airbus")) {
                                    largePlane = (LargePlane) vehi;
                                    System.out.print("### START OF SEAT SUMMARIES ###"+"\n");
                                    System.out.print(largePlane.toString()+"\n");
                                    for (Seat sea:largePlane.seatList) {
                                        System.out.print(sea.getSummary()+"\n");
                                    }
                                    /*for (int i = 0; i < largePlane.capacity; i++) {
                                        if (i < largePlane.businessSeatCount) {
                                            planeSeat = (PlaneSeat) largePlane.seatList.get(i);
                                            System.out.println(planeSeat.getSummary());
                                        } else {
                                            businessPlaneSeat = (BusinessPlaneSeat) largePlane.seatList.get(i);
                                            System.out.println(businessPlaneSeat.getSummary());
                                        }
                                    }*/

                                }
                                System.out.println("### END OF SEAT SUMMARIES ###");
                            }
                        }
                    }
                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        }


        // DO YOUR WORK HERE...

        // DON'T CHANGE LINES BELOW, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        if (printToOutputFile) {
            try {
                Files.write(outputFilepath, Arrays.asList(baos.toString().trim().split("\n")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // DON'T CHANGE THESE LINES, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
    }

}