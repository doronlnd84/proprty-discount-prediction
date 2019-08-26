package dl.projects.propertydeal.model;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Deal {
    @Id
    @SerializedName("KEYVALUE")
    private String id;

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    @SerializedName("TYPE")
    private Integer type;

    @SerializedName("DEALDATE")
    private String dealDate;
    @SerializedName("DEALDATETIME")
    private String dealDateTime;
    @SerializedName("FULLADRESS")
    private String fullAddress;

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @SerializedName("DISPLAYADRESS")
    private String displayAdress;

    public String getDisplayAdress() {
        return displayAdress;
    }

    public void setDisplayAdress(String displayAdress) {
        this.displayAdress = displayAdress;
    }

    @SerializedName("GUSH")
    private String gush;
    @SerializedName("DEALNATUREDESCRIPTION")
    private String dealNatureDescription;


    @SerializedName("ASSETROOMNUM")
    private String assetRoomNumber;



    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    @SerializedName("FLOORNO")
    private String floorNo;

    @SerializedName("DEASSETROOMNUMALNATURE")
    private String deassetRoomNumalNature;

    @SerializedName("DEALAMOUNT")
    private String dealAmaount;

    @SerializedName("NEWPROJECTTEXT")
    private String newProjectText;

    @SerializedName("PROJECTNAME")
    private String projectName;

    @SerializedName("BUILDINGYEAR")
    private String builsingYear;

    @SerializedName("YEARBUILT")
    private String yearBuilt;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public City getCityCode() {
        return cityCode;
    }

    public void setCityCode(City cityCode) {
        this.cityCode = cityCode;
    }

    @SerializedName("BUILDINGFLOORS")
    private String buildingFloors;

    private String cityName;
    private String  streetName;

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    private String  streetNo;
    private City  cityCode;

    public List<Integer> getFloor() {
        return floor;
    }

    public String getFilteredStreetName() {
        return filteredStreetName;
    }

    public void setFilteredStreetName(String filteredStreetName) {
        this.filteredStreetName = filteredStreetName;
    }

    public String getFilteredCityName() {
        return filteredCityName;
    }

    public void setFilteredCityName(String filteredCityName) {
        this.filteredCityName = filteredCityName;
    }

    public void setFloor(List<Integer> floor) {
        this.floor = floor;
    }

    private List<Integer> floor;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    private String filteredStreetName;
    private String filteredCityName;
}
