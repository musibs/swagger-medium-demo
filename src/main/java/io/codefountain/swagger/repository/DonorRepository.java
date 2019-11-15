package io.codefountain.swagger.repository;

import io.codefountain.swagger.model.Donor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends PagingAndSortingRepository<Donor, Long> {
}
