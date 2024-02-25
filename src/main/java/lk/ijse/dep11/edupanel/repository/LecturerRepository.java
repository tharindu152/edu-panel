package lk.ijse.dep11.edupanel.repository;

import lk.ijse.dep11.edupanel.entity.Lecturer;
import lk.ijse.dep11.edupanel.converter.LecturerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

    // find + Subject + By + Predicate
    // query + Subject + By + Predicate
    // read + Subject + By + Predicate
    // get + Subject + By + Predicate
    List<Lecturer> findLecturersByType(LecturerType type);

     @Query("SELECT l FROM Lecturer l WHERE l.type = lk.ijse.dep11.edupanel.converter.LecturerType.FULL_TIME")
    List<Lecturer> findFullTimeLecturers();

     @Query("SELECT l FROM Lecturer l WHERE l.type = lk.ijse.dep11.edupanel.converter.LecturerType.VISITING")
    List<Lecturer> findVisitingLectures();
}
