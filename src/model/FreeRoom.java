package model;

public class FreeRoom extends Room{
    //calls the features from Room class into FreeRoom but changes the values to represent a $0.00 room
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0, roomType, true);
    }

    @Override
    public String toString() {
        return "Free Room: " +
                "Room Number: " + getRoomNumber() + '\'' +
                ", Room Type: " + getRoomType() +
                ", Price: " + getRoomPrice() +
                ", Is Free: " + isFree();
    }
}
