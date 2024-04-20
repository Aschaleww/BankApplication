package bankapp.bankapplication.Repository;

import bankapp.bankapplication.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AccountRepository extends JpaRepository<Account, Long> {
}
