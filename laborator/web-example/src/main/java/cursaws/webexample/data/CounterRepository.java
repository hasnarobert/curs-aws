package cursaws.webexample.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface CounterRepository extends Repository<Counter, Long> {

  Page<Counter> findAll(Pageable pageable);

  Page<Counter> findByName(String name, Pageable pageable);
}
