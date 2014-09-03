package generated;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table POINT.
 */
public class Point {

    private Long id;
    private String title;
    private Double lat;
    private Double lng;

    public Point() {
    }

    public Point(Long id) {
        this.id = id;
    }

    public Point(Long id, String title, Double lat, Double lng) {
        this.id = id;
        this.title = title;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}