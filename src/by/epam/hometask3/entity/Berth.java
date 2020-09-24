package by.epam.hometask3.entity;

public class Berth {
    private int id;

    public Berth(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void unloadToStorageContainer(Ship ship) {
        StorageContainer.getInstance().unloadContainers(ship);
    }

    public void loadFromStorageContainer(Ship ship) {
        StorageContainer.getInstance().loadContainers(ship);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Berth berth = (Berth) o;

        return id == berth.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Berth{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
