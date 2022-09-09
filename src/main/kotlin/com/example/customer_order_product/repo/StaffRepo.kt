package com.example.customer_order_product.repo
import com.example.customer_order_product.dto.IStaffReportDto
import com.example.customer_order_product.models.Staff
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StaffRepo:JpaRepository<Staff, Long>, BaseRepo<Staff> {

    @Query("select s.id as id, s.name as name, s.salary as salary, sum(o.totalAmount) as total " +
            "from Staff s join Order o on s.id = o.staff.id " +
            "where o.paidStatus = true and o.createdDate between cast(:startDate as date ) and cast(:endDate as date) " +
            "group by s.id "+
            "order by sum(o.totalAmount) desc")
    fun getTopSellerPageableOfDuration(startDate:String, endDate:String, pageable: Pageable): Page<IStaffReportDto>?

    @Query("select s.id as id, s.name as name, s.salary as salary, sum(p.paidAmount) as total " +
            "from Staff s join Payment p on s.id = p.staff.id " +
            "where p.createdDate >= cast(concat('01-01-',:year) as date) and p.createdDate <= cast(concat('12-31-', :year) as date) " +
            "group by id " +
            "order by sum(p.paidAmount) desc")
    fun getAllCashierOfYearOrderIncome(year:String):List<IStaffReportDto>?

    @Query("select s.id as id, s.name as name, s.salary as salary, sum(o.total_amount) as total " +
            "from staff s join \"order\" o on s.id = o.staff_id " +
            "where o.created_date between cast(concat('01-01-',:year) as date) and cast(concat('12-31-', :year) as date) and o.paid_status = 'true' " +
            "group by s.id " +
            "order by total desc " +
            "fetch first :topNumber row only",
        nativeQuery = true)
    fun getTopSellerLimitRowOfYear(year:Int, topNumber:Int):List<IStaffReportDto>?

    @Query("Select s.id as id, s.name as name, s.salary as salary from Staff s")
    fun getAll():List<IStaffReportDto>?
}
