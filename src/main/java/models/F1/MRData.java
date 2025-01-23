package models.F1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MRData{

    @JsonProperty("xmlns")
    private String xmlns;

    @JsonProperty("total")
    private String total;

    @JsonProperty("offset")
    private String offset;

    @JsonProperty("series")
    private String series;

    @JsonProperty("limit")
    private String limit;

    @JsonProperty("DriverTable")
    private DriverTable driverTable;

    @JsonProperty("url")
    private String url;

    public void setXmlns(String xmlns){
        this.xmlns = xmlns;
    }

    public String getXmlns(){
        return xmlns;
    }

    public void setTotal(String total){
        this.total = total;
    }

    public String getTotal(){
        return total;
    }

    public void setOffset(String offset){
        this.offset = offset;
    }

    public String getOffset(){
        return offset;
    }

    public void setSeries(String series){
        this.series = series;
    }

    public String getSeries(){
        return series;
    }

    public void setLimit(String limit){
        this.limit = limit;
    }

    public String getLimit(){
        return limit;
    }

    public void setDriverTable(DriverTable driverTable){
        this.driverTable = driverTable;
    }

    public DriverTable getDriverTable(){
        return driverTable;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    @Override
    public String toString() {
        return "MRData{" +
                "xmlns='" + xmlns + '\'' +
                ", total='" + total + '\'' +
                ", offset='" + offset + '\'' +
                ", series='" + series + '\'' +
                ", limit='" + limit + '\'' +
                ", driverTable=" + driverTable +
                ", url='" + url + '\'' +
                '}';
    }

}