package yncrea.lab05.web.service.impl;

import yncrea.lab05.contract.dto.CompanyDTO;
import yncrea.lab05.contract.facade.CompanyWS;
import yncrea.lab05.core.entity.Company;
import yncrea.lab05.core.service.CompanyService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Named("companyWS")
@WebService(endpointInterface = "yncrea.lab05.contract.facade.CompanyWS")
public class CompanyWSImpl implements CompanyWS {

    @Inject
    private CompanyService companyService;


    @Override
    public Collection<CompanyDTO> getAllCompanies() {
        List<CompanyDTO> allCompanies = new ArrayList<>();
        List<Company> allWithProjects = companyService.findAllWithProjects();
        for (Company company : allWithProjects) {
            allCompanies.add(new CompanyDTO(company.getName()));
        }
        return (allCompanies);
    }

    @Override
    public void saveCompany(CompanyDTO companyDTO) {
        Company company = new Company(companyDTO.getName());
        companyService.save(company);
    }
}
