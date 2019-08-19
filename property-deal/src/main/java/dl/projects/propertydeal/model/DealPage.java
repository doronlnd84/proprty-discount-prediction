package dl.projects.propertydeal.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DealPage {

    @SerializedName("IsSpecificAddressResult")
    private boolean isSpecificAddressResult;

    @SerializedName("PageNumber")
    private int pageNumber;

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    @SerializedName("IsLastPage")
    private boolean isLastPage;
    @SerializedName("SpecificAddressData")
    private  String specificAddressData;

    ArrayList<Deal> AllResults;

    public ArrayList<Deal> getAllResults() {
        return AllResults;
    }

    public void setAllResults(ArrayList<Deal> allResults) {
        AllResults = allResults;
    }
}
