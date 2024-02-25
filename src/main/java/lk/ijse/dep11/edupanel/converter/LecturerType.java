package lk.ijse.dep11.edupanel.converter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LecturerType {
    FULL_TIME("full-time"), VISITING("visiting");

    private String type;

    LecturerType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
