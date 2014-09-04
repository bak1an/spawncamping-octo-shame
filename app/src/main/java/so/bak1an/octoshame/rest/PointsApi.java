package so.bak1an.octoshame.rest;

import retrofit.http.GET;
import so.bak1an.octoshame.rest.entity.PointsList;

/**
 * Created by bak1an on 9/3/14.
 */
public interface PointsApi {

    @GET("/points/")
    public PointsList listPoints();

}
