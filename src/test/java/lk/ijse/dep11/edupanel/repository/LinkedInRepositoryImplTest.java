package lk.ijse.dep11.edupanel.repository;

import lk.ijse.dep11.edupanel.entity.Lecturer;
import lk.ijse.dep11.edupanel.entity.LinkedIn;
import lk.ijse.dep11.edupanel.converter.LecturerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LinkedInRepositoryImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private LinkedInRepository repository;

    @Test
    void save() {
        Lecturer lecturer = new Lecturer("Kasun Sampath",
                "Associate Instructor",
                "BSc (Hons) in Computing",
                LecturerType.VISITING, 0);
        entityManager.persist(lecturer);
        LinkedIn linkedIn = new LinkedIn(lecturer, "http://linkedin.com/kasun-sampath");
        repository.save(linkedIn);

        LinkedIn actualLinkedIn = entityManager.find(LinkedIn.class, lecturer.getId());
        assertEquals(linkedIn, actualLinkedIn);
    }

    @Test
    void update() {
        Lecturer lecturer = new Lecturer("Kasun Sampath",
                "Associate Instructor",
                "BSc (Hons) in Computing",
                LecturerType.VISITING, 0);
        entityManager.persist(lecturer);
        LinkedIn linkedIn = new LinkedIn(lecturer, "http://linkedin.com/kasun-sampath");
        repository.save(linkedIn);
        linkedIn.setUrl("https://linkedin.com/kasun-sampath");
        repository.save(linkedIn);

        LinkedIn dbLinkedIn = entityManager.find(LinkedIn.class, lecturer.getId());
        assertEquals(linkedIn, dbLinkedIn);
    }

    @Test
    void deleteById() {
        Lecturer lecturer = new Lecturer("Kasun Sampath",
                "Associate Instructor",
                "BSc (Hons) in Computing",
                LecturerType.VISITING, 0);
        entityManager.persist(lecturer);
        LinkedIn linkedIn = new LinkedIn(lecturer, "http://linkedin.com/kasun-sampath");
        repository.save(linkedIn);
        repository.deleteById(lecturer.getId());

        LinkedIn dbLinkedIn = entityManager.find(LinkedIn.class, lecturer.getId());
        assertNull(dbLinkedIn);
    }

    @Test
    void existsById() {
        Lecturer lecturer = new Lecturer("Kasun Sampath",
                "Associate Instructor",
                "BSc (Hons) in Computing",
                LecturerType.VISITING, 0);
        entityManager.persist(lecturer);
        LinkedIn linkedIn = new LinkedIn(lecturer, "http://linkedin.com/kasun-sampath");
        repository.save(linkedIn);
        boolean result = repository.existsById(lecturer.getId());
        boolean result2 = repository.existsById(-500);

        assertTrue(result);
        assertFalse(result2);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void count() {
    }
}