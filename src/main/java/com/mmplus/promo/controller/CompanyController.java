package com.mmplus.promo.controller;

import com.mmplus.promo.domain.profiles.Company;
import org.apache.poi.ss.usermodel.Cell;
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
import java.util.Iterator;

@Controller
@RequestMapping("/manager")
public class CompanyController {

    private final CompanyRepositoryUserDetailsService companyService;

    public CompanyController(CompanyRepositoryUserDetailsService companyService) {
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
            Company company = new Company();

            company.setContractNumber(currentRow.getCell(0).getStringCellValue());
            company.setZkpo(((int) currentRow.getCell(1).getNumericCellValue()));
            company.setCompanyName(currentRow.getCell(2).getStringCellValue());

            companyService.saveOrUpdate(company);
        }

        return "redirect:/";
    }

    @GetMapping("/show-all-companies")
    public String showAllCompanies(Model model){
        model.addAttribute("allcompanieslist", companyService.findAll());
        return "show-all-companies";
    }

}
