package so.bak1an.octoshame.rest.entity;

import java.util.List;

import generated.Point;

/**
 * Created by bak1an on 9/4/14.
 */
public class PointsList {

    private List<Point> points;

    public PointsList(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointsList that = (PointsList) o;

        if (!points.equals(that.points)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return points.hashCode();
    }
}
