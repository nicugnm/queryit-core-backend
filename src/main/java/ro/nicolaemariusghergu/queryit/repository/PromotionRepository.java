package ro.nicolaemariusghergu.queryit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ro.nicolaemariusghergu.queryit.model.Promotion;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @NonNull
    @Override
    Optional<Promotion> findById(@NonNull Long id);

    @NonNull
    @Override
    List<Promotion> findAll();

    List<Promotion> findByName(String name);
}
