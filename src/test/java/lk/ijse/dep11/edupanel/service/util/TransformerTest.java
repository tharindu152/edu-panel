package lk.ijse.dep11.edupanel.service.util;

import lk.ijse.dep11.edupanel.entity.Lecturer;
import lk.ijse.dep11.edupanel.entity.LinkedIn;
import lk.ijse.dep11.edupanel.to.LecturerTO;
import lk.ijse.dep11.edupanel.to.request.LecturerReqTO;
import lk.ijse.dep11.edupanel.converter.LecturerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransformerTest {

    @Autowired
    private Transformer transformer;

    @Test
    void toLecturerTO() {
        Lecturer lecturer = new Lecturer(2,
                "Nuwan",
                "Associate Trainer",
                "BSc in Computing",
                LecturerType.VISITING,
                5);
        lecturer.setLinkedIn(new LinkedIn(lecturer, "https://linkedin.com/nuwan"));
        LecturerTO lecturerTO = transformer.toLecturerTO(lecturer);

        assertEquals(lecturer.getId(), lecturerTO.getId());
        assertEquals(lecturer.getName(), lecturerTO.getName());
        assertEquals(lecturer.getDesignation(), lecturerTO.getDesignation());
        assertEquals(lecturer.getQualifications(), lecturerTO.getQualifications());
        assertEquals(lecturer.getType(), lecturerTO.getType());
        assertEquals(lecturer.getLinkedIn().getUrl(), lecturerTO.getLinkedin());
    }

    @Test
    void fromLecturerTO() {
        LecturerTO lecturerTO = new LecturerTO(5,
                "Nuwan",
                "Senior Trainer",
                "BSc in Computing",
                LecturerType.FULL_TIME,
                6,
                null, "https://linked.in/nuwan-kasun");
        Lecturer lecturer = transformer.fromLecturerTO(lecturerTO);

        assertEquals(lecturerTO.getId(), lecturer.getId());
        assertEquals(lecturerTO.getName(), lecturer.getName());
        assertEquals(lecturerTO.getDesignation(), lecturer.getDesignation());
        assertEquals(lecturerTO.getQualifications(), lecturer.getQualifications());
        assertEquals(lecturerTO.getDisplayOrder(), lecturer.getDisplayOrder());
        assertEquals(lecturerTO.getLinkedin(), lecturer.getLinkedIn().getUrl());
    }

    @Test
    void fromLecturerReqTO() {
        LecturerReqTO lecturerReqTO = new LecturerReqTO("Thisara",
                "Senior Trainer",
                "BSc in Computing",
                LecturerType.FULL_TIME,
                10,
                null,
                "http://linkedin.com/thisara");
        Lecturer lecturer = transformer.fromLecturerReqTO(lecturerReqTO);

        assertEquals(lecturerReqTO.getName(), lecturer.getName());
        assertEquals(lecturerReqTO.getDesignation(), lecturer.getDesignation());
        assertEquals(lecturerReqTO.getQualifications(), lecturer.getQualifications());
        assertEquals(lecturerReqTO.getType(), lecturer.getType());
        assertEquals(lecturerReqTO.getDisplayOrder(), lecturer.getDisplayOrder());
        assertEquals(lecturerReqTO.getLinkedin(), lecturer.getLinkedIn().getUrl());
    }
}