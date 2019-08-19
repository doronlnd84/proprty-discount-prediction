package dl.projects.propertydeal.model;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;

public class Deal {
    @Id
    @SerializedName("KEYVALUE")
    private String id;

    @SerializedName("TYPE")
    private Integer type;

    @SerializedName("DEALDATE")
    private String dealDate;
    @SerializedName("DEALDATETIME")
    private String dealDateTime;
    @SerializedName("FULLADRESS")
    private String fullAddress;
    @SerializedName("DISPLAYADRESS")
    private String displayAdress;
    @SerializedName("GUSH")
    private String gush;
    @SerializedName("DEALNATUREDESCRIPTION")
    private String dealNatureDescription;


    @SerializedName("ASSETROOMNUM")
    private String assetRoomNumber;
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

    @SerializedName("BUILDINGFLOORS")
    private String buildingFloors;



}
