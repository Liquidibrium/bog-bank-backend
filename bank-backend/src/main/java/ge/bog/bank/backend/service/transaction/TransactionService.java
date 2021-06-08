package ge.bog.bank.backend.service.transaction;

import ge.bog.bank.backend.model.TransferDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface TransactionService {

    TransferDto transferMoney(Long accIDFrom, Long accIDTo, BigDecimal amount);

    TransferDto transferMoney(TransferDto transferDto);

    TransferDto addMoneyToAccount(Long accIDTo, BigDecimal amount);

}
