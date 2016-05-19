package com.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MainGenerator {

    private static final String PROJECT_DIR = "/Users/cindy/Documents/work/workspace/Sample/app/src/main/java/com/sample/an/sample/service/db/gen"; //"\\Users\\cindy\\Documents\\work\\workspace\\Sample";//System.getProperty("user.dir");
    public static void main(String[] args) {
        Schema schema = new Schema(1, "greendao.schema");
        schema.enableKeepSectionsByDefault();
        addTables(schema);
        try {
            new DaoGenerator().generateAll(schema, PROJECT_DIR) ;//+ "\\app\\src\\main\\java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addTables(final Schema schema) {
        Entity model = addModel(schema);
    }
    private static Entity addModel(final Schema schema) {
        Entity model = schema.addEntity("KnownSource");
        model.addIdProperty().primaryKey().autoincrement();
        model.addStringProperty("name").notNull();
        return model;
    }
}
