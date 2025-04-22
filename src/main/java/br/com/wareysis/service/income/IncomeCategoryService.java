package br.com.wareysis.service.income;

import br.com.wareysis.domain.income.IncomeCategory;
import br.com.wareysis.mapper.income.IncomeCategoryMapper;
import br.com.wareysis.repository.income.IncomeCategoryRepository;
import br.com.wareysis.service.category.AbstractCategoryService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IncomeCategoryService extends AbstractCategoryService<IncomeCategory> {

    @Inject
    public IncomeCategoryService(
            IncomeCategoryRepository repository,
            IncomeCategoryMapper mapper
    ) {

        super.repository = repository;
        super.mapper = mapper;
    }

}
