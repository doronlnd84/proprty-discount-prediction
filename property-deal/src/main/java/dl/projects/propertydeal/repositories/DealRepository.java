package dl.projects.propertydeal.repositories;

import dl.projects.propertydeal.model.Deal;
import org.springframework.data.repository.CrudRepository;

public interface DealRepository extends CrudRepository<Deal, String> {
}
