package dl.projects.propertydeal.model;

public enum City {

    TLV (5000),
    Jerusalem (3000),
    Modiin(1200),
    Haifa (4000),
    BeerSheva (9000),
    PetaTikva (7900),
    Rishon (8300),
      Netanya (7400),
       BneBrak (6100),
    RamatGan(8600),
      Holon(6600),
        BatYam(6200),
        Arad(2560),
       Dimona(2200),
        Naharia(9100),
         Ashdod(70),
        Ashkelon(7100),
         Netivot(246),
    Acco(7600),
    Ariel(3570),
    Elkana(3560),
    EtzEfraim(3778),
    Qazrin(4100),
    KiriatShmona(2800),
    Efrat(3650),
    Elazar(3618),
    GivatShmuel(681),
    Gedera(2550),
    Eilat(2600);



    private final int cityCode;

    private City(int cityCode) {
        this.cityCode = cityCode;
    }
    public int getCode() {
        return this.cityCode;
    }
}
