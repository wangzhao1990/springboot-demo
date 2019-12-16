package com.zhao.demo.common.utils.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class UserInfoModel extends BaseRowModel {

    @ExcelProperty( index = 0)
//    @ExcelProperty(value = "手机号", index = 0)
    private String number;
    @ExcelProperty(index = 1)
    private String merchantName;
    @ExcelProperty(index = 2)
    private String account;
    @ExcelProperty(index = 3)
    private String userName;
    @ExcelProperty(index = 4)
    private String userMobile;
    @ExcelProperty(index = 5)
    private String country;
    @ExcelProperty(index = 6)
    private String industryName;

//    @ExcelProperty(index = 2,format = "yyyy/MM/dd")
//    private Date loanDate;
}
