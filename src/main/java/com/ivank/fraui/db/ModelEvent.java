package com.ivank.fraui.db;

import com.bedivierre.eloquent.model.DBModel;

public class ModelEvent extends DBModel {
    public int id;
    public String uuid;
    public String camera_id;
    public String image;
    public String plugin_id;

    @Override
    public String getTable() {
        return "event";
    }

    public ModelEvent() {
    }
}
