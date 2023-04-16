package model;

public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();
}

//creates the blueprint and methods that all rooms will be instantiated from, determines the data type of each field