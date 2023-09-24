import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class EmployeeserviceService {

  constructor(private employeeHttp : HttpClient) { }
baseUrl:string="http://localhost:9090/"



  Success(id:any): Observable<string>{
    return (this.employeeHttp.get<string>(`${this.baseUrl}pendingTosucess/${id}`));
  }

  // getallBill(){
  //   return this.customerHttp.get(`${this.baseUrl}getAlltranction`);
  
  // }

  // loadPaidBills (userId: number): Observable<any> {
  //   return this.customerHttp.get(`${this.baseUrl}getAlltranctionByIdForSucess/${userId}`);
  // }

  loadPaidBills(): Observable<any> {
    return this.employeeHttp.get(`${this.baseUrl}getSavePaymentById`);
  }

 loadUnPaidBills(): Observable<any> {
    return this.employeeHttp.get(`${this.baseUrl}getAllPendingBills`);
  }
 
  // InvoiceComponent(userId:number,SelectedBill:number):Observable<any> {
  //   return this.customerHttp.get(`${this.baseUrl}getAlltranctionBillInvoice/${userId}/${SelectedBill}`);
  // }
  // PaymentMethodMode(userId:number,SelectedBill:number,paymnetMethod:number):Observable<string> {
  //   return this.customerHttp.get<string>(`${this.baseUrl}setAlltranctionByIdAndPaymentId/${userId}/${SelectedBill}/${paymnetMethod}`);
  // }
  // ConformPayment(userId:number,SelectedBill:number){
  //   return this.customerHttp.get<string>(`${this.baseUrl}getAlltranctionPaymentStatusUpdate/${userId}/${SelectedBill}`);
  // }
  
}



