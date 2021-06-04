package ge.bog.bank.backend.repository;

import ge.bog.bank.backend.entitiy.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

}
