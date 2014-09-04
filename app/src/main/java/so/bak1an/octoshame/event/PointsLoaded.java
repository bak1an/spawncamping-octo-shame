package so.bak1an.octoshame.event;

import java.util.List;

import generated.Point;

/**
 * Created by bak1an on 9/4/14.
 */
public class PointsLoaded {

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    private List<Point> points;

    public PointsLoaded(List<Point> points) {
        this.points = points;
    }
}
