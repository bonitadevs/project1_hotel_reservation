package model;

public class Room implements IRoom{

    //implements the features from IRoom interface by creating thr variables to store the values in.
    private String roomNumber;
    private double price;
    private RoomType roomType;
    private boolean isFree;

    public Room(String roomNumber, double price, RoomType roomType, boolean isFree) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.isFree = isFree;
    }

    //this constructor will initialize the state or the values for each room, based on IRoom features.

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }
//the overrides above overrides the methods from the IRoom interface to include the return values of the room features
    @Override
    public String toString() {
        return "Room: " +
                "Room Number:'" + roomNumber + '\'' +
                ", Price:" + price +
                ", Room Type:" + roomType +
                ", Free? :" + isFree +
                '}';
    }
}
