package Polygon.Difference;

import com.vividsolutions.jts.geom.Polygon;import java.lang.String;

/**
 * Created by Goolos on 13.07.2014.
 */
public class PolygonWithName {
    private String mName;
    private Polygon mPolygon;

    public PolygonWithName(String name, Polygon polygon)
    {
        mName = name;
        mPolygon =  polygon;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setPolygon(Polygon mPoligon) {
        this.mPolygon = mPoligon;
    }

    public Polygon getPolygon() {

        return mPolygon;
    }
}
