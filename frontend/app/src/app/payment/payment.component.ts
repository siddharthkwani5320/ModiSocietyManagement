import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})

export class PaymentComponent {
  paymentForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.paymentForm = this.fb.group({
      amount: [null, [Validators.required, Validators.min(1)]]
    });
  }

  onSubmit(): void {
    if (this.paymentForm.valid) {
      const amount = this.paymentForm.get('amount')?.value;

      this.http.get(`http://localhost:8080/api/payment?amount=${amount}`).subscribe(response =>{
          console.log(response)
          var data:any = response;
          window.location.href = data.url
      });
    }
  }

  makePayment() {
    const amount = 500; // Amount in paisa (500 = 5 INR)


  }
}

