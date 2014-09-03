package com.example.mymodule.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Generator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "generated");

        Entity point = schema.addEntity("Point");
        point.addIdProperty();
        point.addStringProperty("title");
        point.addDoubleProperty("lat");
        point.addDoubleProperty("lng");

        new DaoGenerator().generateAll(schema, args[0]);
    }
}
