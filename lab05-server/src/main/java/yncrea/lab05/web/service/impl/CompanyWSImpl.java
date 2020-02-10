package yncrea.lab05.web.service.impl;

import yncrea.lab05.contract.dto.CompanyDTO;
import yncrea.lab05.contract.facade.CompanyWS;

import javax.inject.Named;
import javax.jws.WebService;
import java.util.Collection;

@Named("companyWS")
@WebService(endpointInterface = "yncrea.lab05.contract.facade.CompanyWS")
public class CompanyWSImpl implements CompanyWS {
    @Override
    public Collection<CompanyDTO> getAllCompanies() {
        return null;
    }

    @Override
    public void saveCompany(CompanyDTO companyDTO) {

    }
}
