/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebook.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 */
@Named(value = "reportBean")
@SessionScoped
public class ReportBean implements Serializable {

    private List<Object[]> topSalesReport;
    private List<Object[]> topClientsReport;
    private List<Object[]> topSellerReport;
    private BigDecimal totalSales;
    private BigDecimal totalGST;
    private BigDecimal totalSub;
    private BigDecimal totalDiscount;
    private Date startDate;
    private Date endDate;
    private int reportID;

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Object[]> getTopSalesReport() {
        return topSalesReport;
    }

    public void setTopSalesReport(List<Object[]> topSalesReport) {
        this.topSalesReport = topSalesReport;
    }

    public List<Object[]> getTopClientsReport() {
        return topClientsReport;
    }

    public void setTopClientsReport(List<Object[]> topClientsReport) {
        this.topClientsReport = topClientsReport;
    }

    public List<Object[]> getTopSellerReport() {
        return topSellerReport;
    }

    public void setTopSellerReport(List<Object[]> topSellerReport) {
        this.topSellerReport = topSellerReport;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public BigDecimal getTotalGST() {
        return totalGST;
    }

    public void setTotalGST(BigDecimal totalGST) {
        this.totalGST = totalGST;
    }

    public BigDecimal getTotalSub() {
        return totalSub;
    }

    public void setTotalSub(BigDecimal totalSub) {
        this.totalSub = totalSub;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

}
