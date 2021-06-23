package sg.edu.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.StudentCourseDetails;

public interface StudentCourseDetailsRepository extends JpaRepository<StudentCourseDetails, Long> {

}
