package ge.bog.bank.backend.service.transaction;

import ge.bog.bank.backend.entitiy.AccountEntity;
import ge.bog.bank.backend.entitiy.TransactionEntity;
import ge.bog.bank.backend.model.TransferDto;
import ge.bog.bank.backend.repository.TransactionRepository;
import ge.bog.bank.backend.repository.UserRepository;
import ge.bog.bank.backend.util.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionServiceDB implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceDB(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public TransactionEntity transferMoney(Long usernameFrom, Long usernameTo, BigDecimal amount) {
        boolean valid = // validate if usernameFrom is valid
                TransactionValidator.validateTransaction(usernameFrom, usernameTo, amount);
        if (valid) {
            // main logic
            SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            System.out.println("Session created");

            Transaction tx = session.beginTransaction();

//            session.save(cart);
//            session.save(item1);
//            session.save(item2);

            tx.commit();
        }
        return null;
    }

    @Override
    public TransactionEntity transferMoney(TransferDto transferDto) {
        return transferMoney(transferDto.getAccFrom(),
                transferDto.getAccTo(),
                transferDto.getAmount());
    }
}
