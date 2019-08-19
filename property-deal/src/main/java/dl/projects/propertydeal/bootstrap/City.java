package dl.projects.propertydeal.bootstrap;

public enum City {

    TLV (5000),
    Jerusalem (3000);

    private final int cityCode;

    private City(int cityCode) {
        this.cityCode = cityCode;
    }
    public int getCode() {
        return this.cityCode;
    }
}
