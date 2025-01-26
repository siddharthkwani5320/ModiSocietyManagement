import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clubhouse',
  templateUrl: './clubhouse.component.html',
  styleUrls: ['./clubhouse.component.css']
})
export class ClubhouseComponent implements OnInit {

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }

  book(activity:string){
    this.http.post('http://localhost:8080/book', {"activity" : activity}).subscribe(
      response => {
        window.alert('Successful Booking');
      },
    );
  }

}
