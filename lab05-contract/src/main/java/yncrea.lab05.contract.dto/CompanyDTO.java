package yncrea.lab05.contract.dto;

public class CompanyDTO {

    private String name;

    public CompanyDTO(String name) {
        this.name = name;
    }

    public CompanyDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
