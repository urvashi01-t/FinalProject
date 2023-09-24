import { Component, OnInit } from '@angular/core';
import { CustomerBill } from '../customer-bill';
import { Router } from '@angular/router';
import { EmployeeserviceService } from '../employeeservice.service';

@Component({
  selector: 'app-un-paid',
  templateUrl: './un-paid.component.html',
  styleUrls: ['./un-paid.component.scss']
})
export class UnPaidComponent implements OnInit {
  unpaidBills!:CustomerBill[];
  SelectedBill!:number;
  
    constructor(    private route: Router,
      private customerService : EmployeeserviceService
  ) { }
  
    ngOnInit(): void 
    {
        this.loadUnPaidBills();
  }
  
  navigateToChoicePayment(billId:number):void{
  this.SelectedBill=billId;
      sessionStorage.setItem("billId",""+this.SelectedBill);
  this.customerService.Success(this.SelectedBill).subscribe((resonse)=>{
      console.log(resonse);
  });
this.route.navigate(['dashboard']);  
}


  loadUnPaidBills() {
    this.customerService.loadUnPaidBills().subscribe((response) => {
      console.log(response); 
      this.unpaidBills=response;
      console.log(" mai yhi thaUnpaid mai");  
    });
  }
    

}
