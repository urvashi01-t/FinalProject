import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeserviceService } from '../employeeservice.service';
import { CustomerBill } from '../customer-bill';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {

paidBills!:CustomerBill[];
SelectedBill!:number;

  constructor(    private route: Router,
    private customerService : EmployeeserviceService
) { }

  ngOnInit(): void 
  {
      this.loadPaidBills();
}
navigateToChoicePayment(billId:number):void{
  this.SelectedBill=billId;
      sessionStorage.setItem("billId",""+this.SelectedBill);
  this.customerService.Success(this.SelectedBill).subscribe((resonse)=>{
      console.log(resonse);
  });
this.route.navigate(['dashboard']);  
}

loadPaidBills() {
  this.customerService.loadPaidBills().subscribe((response) => {
    console.log(response); 
    this.paidBills=response;
    console.log("tha par hu mai");  
  });
}
  

}
