package br.com.lab.impacta.investment.service.impl;

import br.com.lab.impacta.investment.model.Investment;
import br.com.lab.impacta.investment.model.Product;
import br.com.lab.impacta.investment.repository.InvestmentRepository;
import br.com.lab.impacta.investment.repository.ProductRepository;
import br.com.lab.impacta.investment.service.InvestmentService;
import br.com.lab.impacta.investment.service.facade.AccountFacade;
import br.com.lab.impacta.investment.service.facade.valueObject.AccountBalanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountFacade accountFacade;

    @Override
    public Investment invest(Long productId, Long accountId, Double valueInvestment) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty())
            throw new RuntimeException("Retornar erro de negocio");

        Investment investment = new Investment(productId, accountId, valueInvestment);

        AccountBalanceVO accountBalanceVO = accountFacade.getAccountBalanceById(accountId);

        if (!investment.sufficientBalanceForInvestment(accountBalanceVO.getBalance()))
            throw new RuntimeException("Mensagem de erro aqui");

        if (!investment.verifyProductPrivateOrDefaultForInvestment(accountBalanceVO.getBalance(), product.get()))
            throw new RuntimeException("Retornar erro");

        boolean isDebited = accountFacade.debitAccount(accountId, valueInvestment);

        if (!isDebited)
            throw new RuntimeException("Retornar erro");

        investmentRepository.save(investment);

        return investment;
    }
}
