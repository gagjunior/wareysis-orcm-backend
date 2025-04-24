package br.com.wareysis.service.expense;

import java.util.UUID;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.domain.expense.ExpenseInstallment;
import br.com.wareysis.dto.expense.ExpenseInstallmentDto;
import br.com.wareysis.mapper.expense.ExpenseInstallmentMapper;
import br.com.wareysis.repository.expense.ExpenseInstallmentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ExpenseInstallmentService extends AbstractService {

    @Inject
    ExpenseInstallmentRepository repository;

    @Inject
    ExpenseInstallmentMapper mapper;

    @Transactional
    public ExpenseInstallmentDto create(ExpenseInstallmentDto expenseInstallmentDto) {

        ExpenseInstallment expenseInstallment = mapper.toEntity(expenseInstallmentDto);

        expenseInstallment.id.setId(UUID.randomUUID());

        repository.persistAndFlush(expenseInstallment);
        return mapper.toDto(expenseInstallment);
    }

//    @Transactional
//    public ExpenseInstallmentDto update(ExpenseInstallmentDto dto) {
//
//        ExpenseInstallmentId expenseInstallmentId = new ExpenseInstallmentId(dto.uuid(), dto.dueDate());
//
//        ExpenseInstallment expenseInstallment = repository.findByIdOptional(expenseInstallmentId)
//                .orElseThrow(() -> new RuntimeException("Ocorreu um erro ao tentar atualizar a parcela de despesa."));
//
//
//
//    }

}
