    package com.alexis.school.repository;

    import com.alexis.school.model.School;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface SchoolRepository extends JpaRepository<School, Long> {
    }