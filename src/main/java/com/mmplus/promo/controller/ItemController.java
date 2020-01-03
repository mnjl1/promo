package com.mmplus.promo.controller;

import com.mmplus.promo.domain.Item;
import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.repository.CompanyRepository;
import com.mmplus.promo.service.CompanyRepositoryUserDetailsService;
import com.mmplus.promo.service.ItemService;
import com.mmplus.promo.utils.UploadFileHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;

@Controller
@RequestMapping("/manager")
public class ItemController {
    private final ItemService itemService;
    private final CompanyRepositoryUserDetailsService companyRepositoryUserDetailsService;

    public ItemController(ItemService itemService,
                          CompanyRepositoryUserDetailsService companyRepositoryUserDetailsService, CompanyRepository companyRepository) {
        this.itemService = itemService;
        this.companyRepositoryUserDetailsService = companyRepositoryUserDetailsService;
    }

    @GetMapping("/upload-excel-items-list-file")
    public String showUploadItemsExcelFilePage(){
        return "upload-excel-items-list-file";
    }

    @PostMapping("/upload-excel-items-list-file")
    public String uploadItemsList(@RequestParam ("itemsListFile")MultipartFile multipartFile)
            throws Exception{

        XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();

        while (iterator.hasNext()){
            Row currentRow = iterator.next();

            Item item = new Item();
            item.setEan((int) currentRow.getCell(0).getNumericCellValue());
            item.setItemName(currentRow.getCell(1).getStringCellValue());
            item.setStockNumber(String.valueOf(currentRow.getCell(2).getNumericCellValue()));
            item.setCategory(UploadFileHelper.getCategory(
                    String.valueOf((int)currentRow.getCell(3).getNumericCellValue())));

            itemService.saveOrUpdate(item);

            String contractNumber = currentRow.getCell(4).getStringCellValue();
            Company company = companyRepositoryUserDetailsService
                    .findCompanyByContractNumber(contractNumber);

            if (company!=null){
                company.addItem(item);
                item.getCompanies().add(company);
                companyRepositoryUserDetailsService
                        .saveOrUpdate(company);
            }
        }

        return "redirect:/";
    }

    @GetMapping("/show-all-uploaded-items")
    public String showAllItems(Model model){
        model.addAttribute("showAllItems", itemService.findAll());
        return "show-all-uploaded-items";
    }
}
