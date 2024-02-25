package lk.ijse.dep11.edupanel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LecturerTypeConverter implements Converter<String, LecturerType> {

    @Override
    public LecturerType convert(String source) {
        for (LecturerType type : LecturerType.values()) {
            if (type.getType().equalsIgnoreCase(source)){
                return type;
            }
        }
        return null;
    }
}
