package br.com.wareysis.service.expense;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.domain.expense.ExpenseEntryId;
import br.com.wareysis.domain.expense.ExpenseInstallment;
import br.com.wareysis.dto.expense.ExpenseInstallmentDetailsDto;
import br.com.wareysis.dto.expense.ExpenseInstallmentDto;
import br.com.wareysis.exception.expense.ExpenseInstallmentException;
import br.com.wareysis.mapper.expense.ExpenseInstallmentMapper;
import br.com.wareysis.repository.expense.ExpenseInstallmentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class ExpenseInstallmentService extends AbstractService {

    @Inject
    ExpenseInstallmentRepository repository;

    @Inject
    ExpenseEntryService expenseEntryService;

    @Inject
    ExpenseInstallmentMapper mapper;

    @Transactional
    public ExpenseInstallmentDto create(ExpenseInstallmentDto installmentDto) {

        // Verificar usuário existe
        userService.validateUser(installmentDto.userId());

        // Verificar se despesa existe
        ExpenseEntryId expenseEntryId = new ExpenseEntryId(installmentDto.entryId(), installmentDto.userId(), installmentDto.entryDate());
        expenseEntryService.validateExpenseEntryExists(expenseEntryId);

        List<ExpenseInstallment> expenseInstallmentList = mapper.toEntity(installmentDto);

        expenseInstallmentList.forEach(expenseInstallment -> {
            expenseInstallment.id.setId(UUID.randomUUID());
            expenseInstallment.setAmountPaid(BigDecimal.ZERO);
            repository.persist(expenseInstallment);
        });

        return mapper.toDto(expenseInstallmentList);
    }

    @Transactional
    public ExpenseInstallmentDto update(ExpenseInstallmentDto installmentDto) {

        // Verificar usuário existe
        userService.validateUser(installmentDto.userId());

        // Verificar se despesa existe
        ExpenseEntryId expenseEntryId = new ExpenseEntryId(installmentDto.entryId(), installmentDto.userId(), installmentDto.entryDate());
        expenseEntryService.validateExpenseEntryExists(expenseEntryId);

        installmentDto.installmentList().forEach(installmentDetails -> {
            ExpenseInstallment installment = findByUUID(installmentDetails.uuid());
            updateInstallmentDetails(installment, installmentDetails);

        });

        repository.flush();

        return mapper.toDto(repository.findAllByExpenseEntryId(expenseEntryId).stream().toList());

    }

    public ExpenseInstallment findByUUID(UUID uuid) {

        return repository.findByUUID(uuid)
                .orElseThrow(() -> new ExpenseInstallmentException(messageService.getMessage("expense.installment.not.found", uuid), Status.BAD_REQUEST));
    }

    private void updateInstallmentDetails(ExpenseInstallment installment, ExpenseInstallmentDetailsDto installmentDetails) {

        if (installmentDetails.amount() != null) {
            installment.setAmount(installmentDetails.amount());
        }
        if (installmentDetails.statusType() != null) {

            installment.setStatusType(installmentDetails.statusType());
        }
        if (installmentDetails.amountPaid() != null) {

            installment.setAmountPaid(installmentDetails.amountPaid());
        }

    }

}
