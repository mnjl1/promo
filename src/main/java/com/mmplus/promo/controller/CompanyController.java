package com.mmplus.promo.controller;

import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.service.CompanyService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mmplus.promo.service.CompanyRepositoryUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/manager")
public class CompanyController {

    private final CompanyRepositoryUserDetailsService companyRepositoryUserDetailsService;
    private final CompanyService companyService;


    public CompanyController(CompanyRepositoryUserDetailsService companyRepositoryUserDetailsService,
                             CompanyService companyService) {
        this.companyRepositoryUserDetailsService = companyRepositoryUserDetailsService;
        this.companyService = companyService;
    }

    @GetMapping("/upload-excel-company-list-file")
    public String showUploadFilePage(){
        return "upload-excel-company-list-file";
    }

    @PostMapping("/upload-excel-company-list-file")
    public String uploadExcelCompanyList(@RequestParam ("companyListFile") MultipartFile multipartFile )
            throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (Row currentRow : sheet) {

            String tempContractNumber = currentRow.getCell(0).getStringCellValue();

            if (!companyService.companyContractExists(tempContractNumber)){
                Company company = new Company();
                company.setContractNumber(tempContractNumber);
                company.setZkpo(((int) currentRow.getCell(1).getNumericCellValue()));
                company.setCompanyName(currentRow.getCell(2).getStringCellValue());
                companyRepositoryUserDetailsService.saveOrUpdate(company);
            }
        }

        return "redirect:/";
    }

    @GetMapping("/show-all-companies")
    public String showAllCompanies(Model model){
        model.addAttribute("allcompanieslist", companyRepositoryUserDetailsService.findAll());
        return "show-all-companies";
    }

}
