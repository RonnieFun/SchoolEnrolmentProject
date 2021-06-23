package sg.edu.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Courses;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

}
