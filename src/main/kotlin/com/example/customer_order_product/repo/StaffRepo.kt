package com.example.customer_order_product.repo
import com.example.customer_order_product.dto.IStaffReportDto
import com.example.customer_order_product.models.Staff
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StaffRepo:JpaRepository<Staff, Long> {

    @Query("select s.id as id, s.name as name, s.salary as salary, sum(o.totalAmount) as total " +
            "from Staff s join Order o on s.id = o.staff.id " +
            "where o.paidStatus = true and o.createdDate between cast(:start as date ) and cast(:end as date) " +
            "group by s.id "+
            "order by sum(o.totalAmount) desc")
    fun getTopSellerOf(start:String, end:String):List<IStaffReportDto>?

    @Query("select s.id as id, s.name as name, s.salary as salary, sum(p.paidAmount) as total " +
            "from Staff s join Payment p on s.id = p.staff.id " +
            "where p.createdDate >= cast(concat('01-01-',:year) as date) and p.createdDate <= cast(concat('12-31-', :year) as date) " +
            " group by id " +
            "order by sum(p.paidAmount) desc")
    fun getTopCashier(year:String):List<IStaffReportDto>?

}
