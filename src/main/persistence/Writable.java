package persistence;

import org.json.JSONObject;

public interface Writable {

    //effects: returns this as Json object
    JSONObject toJson();
}
